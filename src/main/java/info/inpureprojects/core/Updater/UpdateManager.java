package info.inpureprojects.core.Updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import org.apache.commons.io.IOUtils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import info.inpureprojects.core.API.IUpdateCheck;
import info.inpureprojects.core.API.ReleaseLevel;
import info.inpureprojects.core.INpureCore;

public class UpdateManager {

    private final UThread thread;
    private int lastPoll = 400;
    private boolean alreadyDisplayed = false;

    public UpdateManager(IUpdateCheck check) {
        this.thread = new UThread(check);
        FMLCommonHandler.instance().bus().register(this);
    }

    public void runCheck() {
        this.thread.start();
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent evt) {
        if (evt.phase != TickEvent.Phase.START) {
            return;
        }
        if (this.lastPoll > 0) {
            this.lastPoll--;
            return;
        }
        this.lastPoll = 400;
        if (!this.thread.update.getLevel().equals(ReleaseLevel.PUBLIC)) {
            FMLCommonHandler.instance().bus().unregister(this);
            return;
        }
        if (!this.alreadyDisplayed && this.thread.checkComplete && this.thread.updateAvailable) {
            EntityPlayer player = evt.player;
            player.addChatMessage(
                    (new ChatComponentText(EnumChatFormatting.GOLD + "[" + this.thread.update.getModName() + "]:"))
                            .appendText(
                                    EnumChatFormatting.WHITE + " A new version is available: "
                                            + EnumChatFormatting.AQUA
                                            + this.thread.latestVersion.replace("1.7.10", "")
                                            + EnumChatFormatting.WHITE));
            this.alreadyDisplayed = true;
            FMLCommonHandler.instance().bus().unregister(this);
        }
    }

    public static class UThread extends Thread {

        private final IUpdateCheck update;
        private boolean updateAvailable = false;
        private boolean checkComplete = false;
        private String latestVersion;

        public UThread(IUpdateCheck update) {
            super(update.getModId() + "UpdateCheck");
            this.update = update;
        }

        public void run() {
            super.run();
            try {
                URL u = new URL(this.update.getUpdateUrl());
                BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                List<String> lines = IOUtils.readLines(in);
                this.latestVersion = lines.get(0).split("\\s+")[0];
                INpureCore.proxy.print("Local: " + this.update.getVersion() + ", Remote: " + this.latestVersion);
                if (!this.latestVersion.equals(this.update.getVersion())) {
                    this.updateAvailable = true;
                    INpureCore.proxy.print("Update found for " + this.update.getModName());
                } else {
                    INpureCore.proxy.print("No update found for " + this.update.getModName());
                }
            } catch (Throwable t) {
                t.printStackTrace();
                this.checkComplete = false;
            }
            this.checkComplete = true;
        }
    }
}

package info.inpureprojects.core.NEI.gtfoMicroblocks.Commands;

import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureTooltipConfig;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;


public class CommandReload
    implements ICommand {
    public String getCommandName() {
        return "INpureCore";
    }


    public String getCommandUsage(ICommandSender p_71518_1_) {
        return null;
    }


    public List<String> getCommandAliases() {
        return Arrays.asList("inpurecore");
    }


    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if (Arrays.asList(p_71515_2_).contains("reload")) {
            NEIINpureConfig.startReloadProcess();
        } else if (Arrays.asList(p_71515_2_).contains("tooltips")) {
            if (NEIINpureTooltipConfig.tooltips_enabled) {
                INpureCore.proxy.sendMessageToPlayer("Registry tooltips are now hidden.");
                NEIINpureTooltipConfig.tooltips_enabled = false;
            } else {
                INpureCore.proxy.sendMessageToPlayer("Registry tooltips are now shown.");
                NEIINpureTooltipConfig.tooltips_enabled = true;
            }
        }
    }


    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }


    public List<String> addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return Arrays.asList("reload", "tooltips");
    }


    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }


    public int compareTo(Object o) {
        return 0;
    }
}

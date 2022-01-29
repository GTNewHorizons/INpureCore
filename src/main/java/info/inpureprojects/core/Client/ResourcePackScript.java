package info.inpureprojects.core.Client;

import cpw.mods.fml.common.ModContainer;
import info.inpureprojects.core.API.Scripting.Toc.TocManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


public class ResourcePackScript
    implements IResourcePack {
    private final Set<String> set = new HashSet<String>();
    private final ScriptModContainer script;

    public ResourcePackScript(ModContainer container) {
        this.set.add(container.getModId());
        this.script = (ScriptModContainer) container;
    }


    public InputStream getInputStream(ResourceLocation loc) throws IOException {
        try {
            return new FileInputStream(getFileFromLoc(loc));
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    private File getFileFromLoc(ResourceLocation loc) {
        File firstDir = new File(this.script.getSource(), loc.getResourceDomain());
        TocManager.TableofContents toc = null;
        for (TocManager.TableofContents c : this.script.getCore().getLoadedModules()) {
            if (c.getTitle().equals(loc.getResourceDomain())) {
                toc = c;
                break;
            }
        }
        File resourceDir = new File(firstDir, "/resources");
        File actualFile = new File(resourceDir, "/" + loc.getResourcePath());
        return actualFile;
    }


    public boolean resourceExists(ResourceLocation p_110589_1_) {
        return getFileFromLoc(p_110589_1_).exists();
    }


    public Set getResourceDomains() {
        return this.set;
    }


    public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
        return null;
    }


    public BufferedImage getPackImage() throws IOException {
        return null;
    }


    public String getPackName() {
        return "INpure_ScriptLoader:" + this.set.toArray()[0];
    }
}

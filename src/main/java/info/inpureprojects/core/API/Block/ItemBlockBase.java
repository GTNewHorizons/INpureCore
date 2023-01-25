package info.inpureprojects.core.API.Block;

import info.inpureprojects.core.API.MovedFrom;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

@MovedFrom(mod = "OpenBees")
public abstract class ItemBlockBase extends ItemBlock {
    public ItemBlockBase(Block block) {
        super(block);
    }

    public String getUnlocalizedName(ItemStack stack) {
        return this.field_150939_a.getUnlocalizedName() + "." + stack.getItemDamage();
    }

    public int getMetadata(int meta) {
        return meta;
    }

    public boolean getHasSubtypes() {
        return true;
    }
}

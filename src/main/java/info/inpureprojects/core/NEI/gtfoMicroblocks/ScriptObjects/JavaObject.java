package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class JavaObject {
    public JavaObject() {
        NEIINpureConfig.logger.debug("Setting up Java Library...");
    }

    public int random(int size) {
        return (new Random()).nextInt(size);
    }

    public Block[] ReflectAllBlocks(String clazz) {
        ArrayList<Block> blocks = new ArrayList<Block>();
        try {
            Class<?> c = Class.forName(clazz);
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getType().equals(Block.class) && Modifier.isStatic(f.getModifiers())) {
                    Block b = (Block) f.get(null);
                    if (b != null) {
                        blocks.add(b);
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        NEIINpureConfig.logger.debug(
                "ReflectAllBlocks called. Params: %s. Total non-null blocks reflected: %s",
                clazz, String.valueOf(blocks.size()));
        return blocks.toArray(new Block[blocks.size()]);
    }

    public Item[] ReflectAllItems(String clazz) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            Class<?> c = Class.forName(clazz);
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getType().equals(Item.class) && Modifier.isStatic(f.getModifiers())) {
                    Item b = (Item) f.get(null);
                    if (b != null) {
                        items.add(b);
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        NEIINpureConfig.logger.debug(
                "ReflectAllItems called. Params: %s. Total non-null items reflected: %s",
                clazz, String.valueOf(items.size()));
        return items.toArray(new Item[items.size()]);
    }
}

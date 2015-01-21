package org.civcraft;

import net.minecraft.server.v1_8_R1.Block;
import net.minecraft.server.v1_8_R1.MinecraftKey;
import net.minecraft.server.v1_8_R1.TileEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by isaac on 1/19/2015.
 */
public class EnchantAdjust extends JavaPlugin {
    Method set_block_strength;
    Method set_block_durability;
    public static Logger logger;

    @Override
    public void onLoad() {
        logger = this.getLogger();
        Class[] types = {int.class, String.class, Block.class};
        try {
            Class[] float_type =  {float.class};
            set_block_strength = Block.class.getDeclaredMethod("b", float_type);
            set_block_durability = Block.class.getDeclaredMethod("c", float_type);
            set_block_strength.setAccessible(true);
            set_block_durability.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        try {
            Block to_add = (Block) set_block_durability.invoke(new CustomEnchantingTableBlock(), 5.0F);
            set_block_strength.invoke(to_add, 2000.0F);
            injectBlock(116, "enchanting_table", to_add.c("thisIsATest"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void injectBlock(int id, String name, Block block) {
        try {
            Field classToStringField = TileEntity.class.getDeclaredField("g");
            Field stringToClassField = TileEntity.class.getDeclaredField("f");
            classToStringField.setAccessible(true);
            stringToClassField.setAccessible(true);
            Map classToString = (Map) classToStringField.get(null);
            Map stringToClass = (Map) stringToClassField.get(null);

            stringToClass.put("EnchantTable", CustomEnchantingTableEntity.class);
            classToString.put(CustomEnchantingTableEntity.class, "EnchantTable");

            EnchantAdjust.logger.info("block injected");
            Block.REGISTRY.a(id, new MinecraftKey(name), block);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

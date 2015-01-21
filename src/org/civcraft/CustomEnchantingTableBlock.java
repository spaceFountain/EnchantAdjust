package org.civcraft;

import net.minecraft.server.v1_8_R1.*;

/**
 * Created by isaac on 1/19/2015.
 */
public class CustomEnchantingTableBlock extends BlockEnchantmentTable {
    public CustomEnchantingTableBlock(){
        super();
        EnchantAdjust.logger.info("constructed");
    }
}
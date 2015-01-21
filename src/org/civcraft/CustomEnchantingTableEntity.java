package org.civcraft;

import net.minecraft.server.v1_8_R1.*;

/**
 * Created by isaac on 1/19/2015.
 */
public class CustomEnchantingTableEntity extends TileEntityEnchantTable {
    public CustomEnchantingTableEntity(){
        super();
        EnchantAdjust.logger.info("constructed CustomEnchantingTableEntity");
    }
    public Container createContainer(PlayerInventory paramPlayerInventory, EntityHuman paramEntityHuman){
        EnchantAdjust.logger.info("sent tile inventory");
        return new CustomEnchantingTableInventory(paramPlayerInventory, this.world, this.position);
    }
}

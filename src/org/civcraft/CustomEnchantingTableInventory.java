package org.civcraft;

import net.minecraft.server.v1_8_R1.*;
import net.minecraft.server.v1_8_R1.Enchantment;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.*;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import java.util.*;

/**
 * Created by isaac on 1/19/2015.
 */
public class CustomEnchantingTableInventory extends ContainerEnchantTable {
    World world;
    BlockPosition position;
    Player player;
    private Random k = new Random();


    public CustomEnchantingTableInventory(PlayerInventory playerinventory, World world, BlockPosition blockposition) {
        super(playerinventory, world, blockposition);
        this.world = world;
        this.position = blockposition;
        this.player = (Player)playerinventory.player.getBukkitEntity();
        EnchantAdjust.logger.info(" CustomEnchantingTableInventory constructed");
    }
    public void addSlotListener(ICrafting icrafting) //seems to get called when the inventory is first opened
    {
        EnchantAdjust.logger.info(" addSlotListener");
        icrafting.setContainerData(this, 0, 1);
        icrafting.setContainerData(this, 1, 2);
        icrafting.setContainerData(this, 2, 3);
        icrafting.setContainerData(this, 3, this.f & -16);
        icrafting.setContainerData(this, 4, this.h[0]);
        icrafting.setContainerData(this, 5, this.h[1]);
        icrafting.setContainerData(this, 6, this.h[2]);
    }

    public void b() //seems to get called when the inventory should be updated
    {
        updateInventory();
    }

    public void a(IInventory iinventory) { //seems to get called when a change is made to the inventory
        inventoryChanged(iinventory);
    }

    public boolean a(EntityHuman entityhuman, int i) { //called I think when an item is selected
        return selectedEnchant((Player) entityhuman.getBukkitEntity(),i);
    }
    public void b(EntityHuman entityhuman) { //called I think when the player closes the enchanting table
        EnchantAdjust.logger.info(" b(EntityHuman entityhuman)");
        super.b(entityhuman);
        if(!this.world.isStatic) {
            for(int i = 0; i < this.enchantSlots.getSize(); ++i) {
                ItemStack itemstack = this.enchantSlots.splitWithoutUpdate(i);
                if(itemstack != null) {
                    entityhuman.drop(itemstack, false);
                }
            }
        }

    }

    public ItemStack b(EntityHuman entityhuman, int i) {
        EnchantAdjust.logger.info(" ItemStack b(EntityHuman entityhuman, int i)");
        ItemStack itemstack = null;
        /*Slot slot = (Slot)this.c.get(i);
        if(slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();
            if(i == 0) {
                if(!this.a(itemstack1, 2, 38, true)) {
                    return null;
                }
            } else if(i == 1) {
                if(!this.a(itemstack1, 2, 38, true)) {
                    return null;
                }
            } else if(itemstack1.getItem() == Items.DYE && EnumColor.fromInvColorIndex(itemstack1.getData()) == EnumColor.BLUE) {
                if(!this.a(itemstack1, 1, 2, true)) {
                    return null;
                }
            } else {
                if(((Slot)this.c.get(0)).hasItem() || !((Slot)this.c.get(0)).isAllowed(itemstack1)) {
                    return null;
                }

                if(itemstack1.hasTag() && itemstack1.count == 1) {
                    ((Slot)this.c.get(0)).set(itemstack1.cloneItemStack());
                    itemstack1.count = 0;
                } else if(itemstack1.count >= 1) {
                    ((Slot)this.c.get(0)).set(new ItemStack(itemstack1.getItem(), 1, itemstack1.getData()));
                    --itemstack1.count;
                }
            }

            if(itemstack1.count == 0) {
                slot.set((ItemStack)null);
            } else {
                slot.f();
            }

            if(itemstack1.count == itemstack.count) {
                return null;
            }

            slot.a(entityhuman, itemstack1);
        }*/

        return itemstack;
    }

    public void updateInventory(){
        for(int i = 0; i < this.listeners.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.listeners.get(i);
            icrafting.setContainerData(this, 0, 1);
            icrafting.setContainerData(this, 1, 2);
            icrafting.setContainerData(this, 2, 3);
            icrafting.setContainerData(this, 3, this.f & -16);
            icrafting.setContainerData(this, 4, this.h[0]);
            icrafting.setContainerData(this, 5, this.h[1]);
            icrafting.setContainerData(this, 6, this.h[2]);
        }
    }

    public void inventoryChanged(IInventory inventory){
        EnchantAdjust.logger.info("inventoryChanged)");
        inventory.getItem(0).get
    }

    public boolean selectedEnchant(Player player, int slot){
        return false;
    }
}

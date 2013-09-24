package shenmc.synthesis.block;

/**
* @project Synthesis
* 
* @class Block Manager
* 
* @author ShenMC
* @licence  Minecraft Mod Public License (http://www.mod-buildcraft.com/MMPL-1.0.txt)
* 
* Main class to create and register blocks and their recipes
* 
**/

import java.util.logging.Level;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import shenmc.synthesis.utility.LogHelper;
import shenmc.synthesis.utility.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class BlockManager {
    
    public static Block synthesiserCore = null;
    
    public static void registerBlocks() {
        
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "Registering Blocks");
        
        synthesiserCore = new BlockSynthesiserCore(Reference.BlockIDs.synthesiserCore);
        
        GameRegistry.registerBlock(synthesiserCore, "blockSynthesiserCore");
        
        LanguageRegistry.addName(synthesiserCore, "Synthesiser Core");
        
    }
    
    public static void registerBlockRecipes() {

        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "Registering Block Recipes");
        
        /**CraftingManager.getInstance().addRecipe(new ItemStack(multiFurnaceCore, 1),
                "XXX",
                "X X",
                "XXX", 'X', Block.brick);
        **/
    }

}

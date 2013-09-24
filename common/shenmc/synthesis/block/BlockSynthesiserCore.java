package shenmc.synthesis.block;

import java.util.logging.Level;

import shenmc.synthesis.utility.ChunksAnalyzer;
import shenmc.synthesis.utility.LogHelper;
import shenmc.synthesis.utility.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSynthesiserCore extends Block {

    public BlockSynthesiserCore(int blockID) {
        
        super(blockID, Material.anvil);
        
        setUnlocalizedName("blockSynthesiserCore");
        setStepSound(Block.soundAnvilFootstep);
        setHardness(5.0F);
        setResistance(2000.0F);
        setLightValue(0.05f);
        setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    @Override
    public void registerIcons(IconRegister icon) {
        //blockIcon = icon.registerIcon("synthesis:brick");
        blockIcon = Block.bedrock.getIcon(0, 0);
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "SynthesiserCore placed at " + x + "," + y + "," + z + " by " + entity.getEntityName());
        
        if(!world.isRemote) {
            ChunksAnalyzer ca = new ChunksAnalyzer();
            ca.analyseChunks(world,  x, y, z);
        }
        
    }

}

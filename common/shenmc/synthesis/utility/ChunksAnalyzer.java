package shenmc.synthesis.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
* @project Synthesis
* 
* @class ChunksAnalyzer
* 
* @author ShenMC
* @licence  Minecraft Mod Public License (http://www.mod-buildcraft.com/MMPL-1.0.txt)
* 
* Analyse Chunks for item rarity
* 
**/

public class ChunksAnalyzer {
    
    private Map mItems;
    public Map mRarity;
    
    public void analyseChunks(World world, int x, int y, int z) {
        
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "AnalyseChunks for world position " + x + "," + y + "," + z);
        
        mItems = new HashMap();
        
        Chunk c = world.getChunkFromBlockCoords(x, z);
        ChunkCoordIntPair chunkCoord = c.getChunkCoordIntPair();
        
        int cxMin = chunkCoord.chunkXPos - Reference.CHUNK_RADIUS;
        int cxMax = chunkCoord.chunkXPos + Reference.CHUNK_RADIUS;
        int czMin = chunkCoord.chunkZPos - Reference.CHUNK_RADIUS;
        int czMax = chunkCoord.chunkZPos + Reference.CHUNK_RADIUS;
        
        int cx = 0;
        int cz = 0;
        
        for(cx = cxMin; cx < cxMax + 1; cx++) {
            for(cz = czMin; cz < czMax + 1; cz++) {
                Map m = analyseChunk(world, cx, cz);
                
                Iterator iter = m.entrySet().iterator();
                while (iter.hasNext()) {
                    
                    Map.Entry mEntry = (Map.Entry) iter.next();
                    
                    int k = (int) mEntry.getKey();
                    int v = (int) mEntry.getValue();
                    
                    if(mItems.get(k) == null) {
                        mItems.put(k, v);
                    } else {
                        int tv = (int) mItems.get(k);
                        mItems.remove(k);
                        mItems.put(k, tv + v );
                    }
                }
            }
        }
        
        
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO,  "Chunks Analysed:");

        mRarity = new HashMap();
        int cobbleVal = (int) mItems.get(Block.cobblestone.blockID);
        
        Iterator itemsIter = mItems.entrySet().iterator();
        while (itemsIter.hasNext()) {
            
            Map.Entry me = (Map.Entry) itemsIter.next();
            Item item = Item.itemsList[(int)me.getKey()];
            
            int itemCost = (int) (cobbleVal / (int) me.getValue());
            mRarity.put(me.getKey(), itemCost);
        
            if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "Item: " + item.getUnlocalizedName() + "(" + me.getKey() + ") : " + me.getValue() + " Cost: " + itemCost);
        }

    }
    
    private Map analyseChunk(World world, int x, int z) {
        
        Map mBlocks = new HashMap();
        Map mItems  = new HashMap();
        
        int ix = 0;
        int iy = 0;
        int iz = 0;
        
        Chunk c = world.getChunkFromChunkCoords(x, z);
        ChunkCoordIntPair chunkCoord = c.getChunkCoordIntPair();
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "Analysing Chunk : " + chunkCoord.toString());
        
        // Iterate through chunk capturing frequency of Blocks
        for(ix = 0; ix < 15; ix++) {
            for(iz = 0; iz < 15; iz++) {
                for(iy = 0; iy < c.getHeightValue(ix, iz); iy++) {

                    int blockId = c.getBlockID(ix, iy, iz);
                    if(blockId == 0 ||
                       blockId == Block.bedrock.blockID ||
                       blockId == Block.waterStill.blockID ||
                       blockId == Block.waterMoving.blockID ||
                       blockId == Block.lavaMoving.blockID ||
                       blockId == Block.lavaStill.blockID 
                       ) {
                        // ignore these blocks
                    }
                    else {
                        
                        if(mBlocks.get(blockId) == null) {
                            mBlocks.put(blockId, 1);
                        } else {
                            int t = (int) mBlocks.get(blockId);
                            mBlocks.remove(blockId);
                            mBlocks.put(blockId, t+1);
                        }
                    }                    
                }
            }
        }
        
        // create Map of dropped Items for each Block
        Iterator iter = mBlocks.entrySet().iterator();
        while (iter.hasNext()) {
            
            Map.Entry mEntry = (Map.Entry) iter.next();
            
            int k = (int) mEntry.getKey();
            int v = (int) mEntry.getValue();
            
            Block block = Block.blocksList[k];
            if(block == null ||
               block.blockMaterial == Material.air ||
               block.blockMaterial == Material.anvil ||
               block.blockMaterial == Material.cactus ||
               block.blockMaterial == Material.cake ||
               block.blockMaterial == Material.circuits ||
               block.blockMaterial == Material.cloth ||
               block.blockMaterial == Material.leaves ||
               block.blockMaterial == Material.plants ||
               block.blockMaterial == Material.water ||
               block.blockMaterial == Material.wood
               ) {
                if(block == null) {
                    LogHelper.log(Level.INFO, "null block for id: " + k);
                } else {
                    if(block.blockMaterial == Material.air)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = air");
                    if(block.blockMaterial == Material.anvil)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = anvil");
                    if(block.blockMaterial == Material.cactus)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = cactus");
                    if(block.blockMaterial == Material.cake)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = cake");
                    if(block.blockMaterial == Material.circuits)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = circuits");
                    if(block.blockMaterial == Material.cloth)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = cloth");
                    if(block.blockMaterial == Material.leaves)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = leaves");
                    if(block.blockMaterial == Material.plants)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = plants");
                    if(block.blockMaterial == Material.water)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = water");
                    if(block.blockMaterial == Material.wood)
                        LogHelper.log(Level.INFO, "> Ignoring Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Material = wood");
                }
            }
            else {
                
                int id = block.idDropped(0, world.rand, 0);
                
                // only add the item to the list if it drops from the block
                if(id > 1) {
                    
                  //substitute Flint back to Gravel
                    if(id == Item.flint.itemID)
                        id = Block.gravel.blockID;
                    
                    Item item = Item.itemsList[id];
                    
                    if(mItems.get(id) == null) {
                        mItems.put(id, v);
                    } else {
                        int tv = (int) mItems.get(id);
                        mItems.remove(id);
                        mItems.put(id, tv + v );
                    }
                    //if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "> Block: " + block.getUnlocalizedName() + "(" + k + ") : " + v + " Drops: " + item.getUnlocalizedName() + "(" + id + ")");
                }                
            }
        }
        return mItems;
    }

}

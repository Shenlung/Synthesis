package shenmc.synthesis;


/**
* @project Synthesis
* 
* @class Synthesis
* 
* @author ShenMC
* @licence  Minecraft Mod Public License (http://www.mod-buildcraft.com/MMPL-1.0.txt)
* 
* Main Mod class
* 
**/

import java.io.File;
import java.util.logging.Level;

import shenmc.synthesis.block.BlockManager;
import shenmc.synthesis.proxy.CommonProxy;
import shenmc.synthesis.utility.LogHelper;
import shenmc.synthesis.utility.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.common.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class Synthesis {
	
	@Instance(Reference.MOD_ID)
	public Synthesis instance;
	
	@SidedProxy(clientSide="shenmc.synthesis.client.ClientProxy", serverSide="shenmc.synthesis.CommonProxy")
	public static CommonProxy proxy;
	
	/** 
	 * P R E I N I T
	 * 
	 * Run before anything else.
	 * Read config, create blocks, items, etc, and register them with the GameRegistry.
	 **/
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	    // Log File
	    LogHelper.init();
		
		// Config File
	    configureMod(event.getSuggestedConfigurationFile());
		
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "PreInit Done");
	}
	
    /** 
     * I N I T
     * 
     * Mod setup.
     * Build whatever data structures are required, register recipes, send FMLInterModComms messages to other Mods.
     **/
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "*** I N I T ***");
	    
	    BlockManager.registerBlocks();
	    BlockManager.registerBlockRecipes();
	    
	    if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "Init Done");
	}
	
    /** 
     * P O S T I N I T
     * 
     * Called after all Mods are initialised.
     * Handle interaction with other mods, complete setup based on this.
     **/
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	    if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "*** P O S T I N I T ***");
	    
	    if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "PostInit Done");
	}
	
    //  FMLServerAboutToStartEvent : Use if you need to handle something before the server has even been created.
    //  FMLServerStartingEvent     : Do stuff you need to do to set up the server. register commands, tweak the server.
	
    /** 
     * S E R V E R S T A R T E D 
     * 
     * Called after the server has started.
     * Check if SynthesisMaster is present and add if missing
     **/
	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
        if(Reference.DEBUG_ON) LogHelper.log(Level.INFO, "*** S E R V E R     S T A R T E D ***");
        
        // Check if SynthesisMaster Block & TileEntity is present.
	}
	
    //  FMLServerStoppingEvent : Do what you need to before the server has started it's shutdown sequence.
    //  FMLServerStoppedEvent  : Do whatever cleanup you need once the server has shutdown. Generally only useful on the integrated server.
    
    /**
     * These events are more specialised, for receiving notification of specific information.
     * FMLFingerprintViolationEvent : Sent just before FMLPreInitializationEvent if something is wrong with your mod signature
     * IMCEvent : Sent just after FMLInitializationEvent if you have IMC messages waiting from other mods
     **/
	
	// ********************************************************************************************************
	// P R I V A T E 
    // ********************************************************************************************************
	
	/**
	 * C  O  N  F  I  G  U  R  E  M  O  D
	 **/
	 private void configureMod(File configFile) {
	     
	     LogHelper.log(Level.INFO, "configureMod file: " + configFile.getAbsolutePath());
	     
	     Configuration config = new Configuration(configFile);
	     config.load();
	     
         Reference.BlockIDs.synthesiserCore  = config.getBlock("SynthesiserCore", Reference.BlockIDs.synthesiserCore).getInt();

	     // myFirstBlockID = config.getBlock("myFirstBlock", 501).getInt();
	     // myFirstItemID = config.getItem("myFirstItem", 900).getInt();
         // booleanVar = config.get(config.CATEGORY_GENERAL, "doesRandomBlockHurtYou", false).getBoolean(false);

         //Reference.BlockIDs.furnaceCore  = config.getBlock("MultiFurnaceCore", Reference.BlockIDs.furnaceCore).getInt();
         //Reference.BlockIDs.furnaceDummy = config.getBlock("MultiFurnaceDummy", Reference.BlockIDs.furnaceDummy).getInt();
	     
         if(config.hasChanged())
             config.save();
	 }

}

package shenmc.synthesis;

/**
* @project Synthesis
* 
* @class Synthesis
* 
* @author ShenMC
* @licence Lesser GNU Public Licence V3 (http://www.gnu.org/licenses/lgpl.html)
* 
* Main Mod class
* 
**/

import shenmc.synthesis.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies="required-after:FML")

public class Synthesis {
	
	@Instance(Reference.MOD_ID)
	public Synthesis instance;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry.
		
		// Logging
		
		// Localisation
		
		// Config Files
		
		// Register Handlers
		
		// Create & Register Blocks
		
		// Create & Register Items
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// Do your mod setup. Build whatever data structures you care about. Register recipes, send FMLInterModComms messages to other mods.
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Handle interaction with other mods, complete your setup based on this.
	}
	
	/**
    * These are the server lifecycle events. They are fired whenever a server is running, or about to run. Each time a server
    * starts they will be fired in this sequence.
    *  FMLServerAboutToStartEvent : Use if you need to handle something before the server has even been created.
    *  FMLServerStartingEvent : Do stuff you need to do to set up the server. register commands, tweak the server.
    *  FMLServerStartedEvent : Do what you need to with the running server.
    *  FMLServerStoppingEvent : Do what you need to before the server has started it's shutdown sequence.
    *  FMLServerStoppedEvent : Do whatever cleanup you need once the server has shutdown. Generally only useful on the integrated server.
    * 
    * The second set of events are more specialized, for receiving notification of specific information.
    *  FMLFingerprintViolationEvent : Sent just before FMLPreInitializationEvent if something is wrong with your mod signature
    *  IMCEvent : Sent just after FMLInitializationEvent if you have IMC messages waiting from other mods
    *
    * **/


}

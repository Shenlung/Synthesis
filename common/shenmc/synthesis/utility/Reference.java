package shenmc.synthesis.utility;

/**
* @project Synthesis
* 
* @class Reference
* 
* @author ShenMC
* @licence  Minecraft Mod Public License (http://www.mod-buildcraft.com/MMPL-1.0.txt)
* 
* Static references for use throughout the project
* 
**/

public class Reference {
    
    // DEBUG
    public static final boolean DEBUG_ON = true;
    
	// CONSTANTS
	public static final String MOD_ID = "ShenMCSynth";
	public static final String MOD_NAME = "Synthesis";
	public static final String MOD_VERSION = "pre 1a";
	public final static String MOD_DEPENDENCIES = "required-after:FML"; // need to add buildcraft etc. to this
	
	public static final int CHUNK_RADIUS = 5;
	
	// BLOCKS
	public static class BlockIDs
    {
        public static int synthesiserCore  = 1000;
    }
    
	//GUIIDS
    public static class GUIIDs
    {
        public static int multiFurnace = 0;
    }

}

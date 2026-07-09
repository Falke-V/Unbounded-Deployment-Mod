package coremod;

import arc.Core;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.Schematics;
import mindustry.mod.Mod;
import mindustry.world.Block;

public class Main extends Mod {

    @Override
    public void loadContent() {
        CustomBlocks.load();

        // for testing comment
        CustomBlocks.setupTechTree();
    }

    @Override
    public void init() {
        try {
            // custom schematic manager
            Schematics customSchematicsManager = new Schematics() {

                @Override
                public int getMaxLaunchSize(Block block) {
                    if (block instanceof CustomCoreBlock) {
                        return ((CustomCoreBlock) block).schematicLimit;
                    }
                    
                    return super.getMaxLaunchSize(block);
                }
            };

            customSchematicsManager.load();
            // change global schematic manager to custom
            Vars.schematics = customSchematicsManager;

            Log.info("new scheme filter set");
        } catch (Exception e) {
            Log.err("error: ", e);
        }
    }
}

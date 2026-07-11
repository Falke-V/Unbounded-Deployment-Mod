package unboundeddeployment;

import arc.Core;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.Schematics;
import mindustry.mod.Mod;
import mindustry.world.Block;
import unboundeddeployment.content.*;
import unboundeddeployment.content.blocks.*;

public class Main extends Mod {

    @Override
    public void loadContent() {
        UDUnitTypes.load();
        UDBlocks.load();

        // for testing comment
        UDBlocks.setupTechTree();
    }

    @Override
    public void init() {
        try {
            // custom schematic manager
            Schematics UDSchematicsManager = new Schematics() {

                @Override
                public int getMaxLaunchSize(Block block) {
                    if (block instanceof UDCoreBlock) {
                        return ((UDCoreBlock) block).schematicLimit;
                    }
                    
                    return super.getMaxLaunchSize(block);
                }
            };

            UDSchematicsManager.load();
            // change global schematic manager to custom
            Vars.schematics = UDSchematicsManager;

            Log.info("new scheme filter set");
        } catch (Exception e) {
            Log.err("error: ", e);
        }
    }
}

package coremod;

import arc.Core;
import arc.graphics.g2d.*;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

// class for cores with custom deployment schematic size limit
public class CustomCoreBlock extends CoreBlock {
    public int schematicLimit = 32;

    public CustomCoreBlock(String name) {
        super(name);
        this.hasColor = true;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (tile.build != null) {
            if (tile.build.block == mindustry.content.Blocks.coreNucleus) {
                return true;
            }
        }
        return super.canPlaceOn(tile, team, rotation);
    }

    @Override
    protected TextureRegion[] icons() {
        if (teamRegion != null && teamRegion.found() && teamRegions != null && teamRegions.length > 0) {
            return new TextureRegion[]{ region, teamRegions[Team.sharded.id] };
        }
        
        return new TextureRegion[]{ region };
    }
}

package unboundeddeployment.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.Items;
import mindustry.content.Blocks;
import mindustry.content.UnitTypes;
import mindustry.content.TechTree;
import mindustry.content.Planets;
import mindustry.world.Block;
import unboundeddeployment.content.blocks.UDCoreBlock;
import unboundeddeployment.content.UDUnitTypes;

public class UDBlocks {
    public static Block coreCentrum;

    public static void load() {
        coreCentrum = new UDCoreBlock("core-centrum") {{
            // to be changed, for testing comment
            requirements(Category.effect, ItemStack.with(
                Items.copper, 8000,
                Items.lead, 8000, 
                Items.silicon, 6500, 
                Items.thorium, 6500, 
                Items.phaseFabric, 6500, 
                Items.surgeAlloy, 6500));

            // for testing uncomment
            // requirements(Category.effect, new ItemStack[0]);

            unitType = UDUnitTypes.delta;
            size = 5;
            health = 6000;
            itemCapacity = 13000;
            thrusterLength = 40/4f;

            unitCapModifier = 24;
            shownPlanets.add(Planets.serpulo);

            // main new mechanic parameter
            ((UDCoreBlock)this).schematicLimit = 19; 

            // for testing comment
            // researchCostMultiplier = 4f;
            
            // for testing uncomment
            // alwaysUnlocked = true;
        }};
    }

    // for testing comment
    public static void setupTechTree() {
        // mechanicalDrill
        var parentNode = TechTree.all.find(nodes -> nodes.content == mindustry.content.Blocks.coreNucleus);
        
        if(parentNode != null) {
            var centrumNode = TechTree.node(coreCentrum, ItemStack.with(
                mindustry.content.Items.copper, 40000,
                mindustry.content.Items.lead, 40000,
                mindustry.content.Items.silicon, 32500,
                mindustry.content.Items.thorium, 32500,
                mindustry.content.Items.phaseFabric, 32500,
                mindustry.content.Items.surgeAlloy, 32500
            ), () -> {});
            parentNode.children.add(centrumNode);
        }
    }
}

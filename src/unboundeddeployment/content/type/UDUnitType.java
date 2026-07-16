package unboundeddeployment.content.type;

import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.Pixmaps;
import arc.graphics.g2d.PixmapRegion;
import mindustry.graphics.MultiPacker;
import arc.struct.ObjectMap;
import arc.func.Boolf;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

// class for mod-added units with icon generation
public class UDUnitType extends UnitType {
    
    public UDUnitType(String name) {
        super(name);
    }

    @Override
    public void createIcons(MultiPacker packer) {
        super.createIcons(packer);

        if (!packer.has(name)) return;

        try {
            PixmapRegion baseRegion = packer.get(name);

            ObjectMap<Boolf<Color>, Color> baseMap = ObjectMap.of(
                (Boolf<Color>) c -> c.equals(outlineColor), arc.graphics.Color.clear
            );
            
            Pixmap basePixmap = replaceColor(baseRegion, baseMap);

            if (constructor.get() instanceof mindustry.gen.Tankc && packer.has(name + "-treads")) {
                Pixmap treads = packer.get(name + "-treads").crop();
                basePixmap.draw(treads);
                treads.dispose();
            }

            if (packer.has(name + "-cell")) {
                PixmapRegion cellRegion = packer.get(name + "-cell");
                
                ObjectMap<Boolf<Color>, Color> colorMap = ObjectMap.of(
                    (Boolf<Color>) c -> c.equals(Color.white), Color.valueOf("ffa664"), 
                    (Boolf<Color>) c -> c.equals(Color.valueOf("dcc6c6")), Color.valueOf("dc804e")
                );
                
                Pixmap processedCell = replaceColor(cellRegion, colorMap);
                int cx = (basePixmap.width - processedCell.width) / 2;
                int cy = (basePixmap.height - processedCell.height) / 2;
                basePixmap.draw(processedCell, cx, cy, true);
                processedCell.dispose();
            }

            drawWeapons(packer, basePixmap, w -> !w.top);

            Pixmap outlined = Pixmaps.outline(new PixmapRegion(basePixmap), outlineColor, outlineRadius);
            basePixmap.dispose(); 
            basePixmap = outlined;

            drawWeapons(packer, basePixmap, w -> w.top);

            packer.add(MultiPacker.PageType.main, "unit-" + name + "-full", basePixmap);
            packer.add(MultiPacker.PageType.main, "unit-" + name + "-ui", basePixmap);

            basePixmap.dispose();
        } catch (Exception ignored) {  }
    }

    private Pixmap replaceColor(PixmapRegion pixmap, ObjectMap<Boolf<Color>, Color> map) {
        Pixmap base = new Pixmap(pixmap.width, pixmap.height);
        Color color = new Color();
        
        for (int y = 0; y < pixmap.height; ++y) {
            for (int x = 0; x < pixmap.width; ++x) {
                pixmap.get(x, y, color);
                if (arc.math.Mathf.zero(color.a)) continue;
                
                int finalColor = color.rgba8888();
                for (Boolf<Color> filter : map.keys()) {
                    if (filter.get(color)) {
                        finalColor = map.get(filter).rgba8888();
                        break;
                    }
                }
                base.set(x, y, finalColor);
            }
        }
        return base;
    }

    private void drawWeapons(MultiPacker packer, Pixmap basePixmap, java.util.function.Predicate<Weapon> filter) {
        for (Weapon weapon : weapons) {
            if (weapon.name.isEmpty() || !packer.has(weapon.name) || !filter.test(weapon)) continue;

            Pixmap weaponPixmap;
            if (weapon.top) {
                String lookupName = packer.has(weapon.name + "-preview") ? weapon.name + "-preview" : weapon.name;
                PixmapRegion weaponRegion = packer.get(lookupName);
                weaponPixmap = lookupName.endsWith("-preview") ? 
                        Pixmaps.outline(weaponRegion, outlineColor, outlineRadius) : weaponRegion.crop();
            } else {
                weaponPixmap = packer.get(weapon.name).crop();
            }

            int cx = (basePixmap.width - weaponPixmap.width) / 2;
            int cy = (basePixmap.height - weaponPixmap.height) / 2;
            int shiftX = (int) (weapon.x * 4);
            int shiftY = (int) (weapon.y * 4);

            basePixmap.draw(weaponPixmap, cx + shiftX, cy - shiftY, true);
            if (weapon.mirror) {
                Pixmap flipped = weaponPixmap.flipX();
                basePixmap.draw(flipped, cx - shiftX, cy - shiftY, true);
                flipped.dispose();
            }
            weaponPixmap.dispose();
        }
    }
}

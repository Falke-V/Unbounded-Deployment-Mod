package unboundeddeployment.content;

import arc.graphics.Color;
import arc.util.*;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.type.*;
import unboundeddeployment.content.type.UDUnitType;

public class UDUnitTypes{
    
    public static UnitType delta;
    
    public static void load(){
        //region core
        delta = new UDUnitType("delta"){{
            controller = u -> u.team.isAI() ? new BuilderAI(true, 400f) : new CommandAI();
            isEnemy = false;

            targetBuildingsMobile = false;
            lowAltitude = true;
            flying = true;
            mineSpeed = 9.5f;
            mineTier = 3;
            buildSpeed = 1.25f;
            drag = 0.05f;
            speed = 3.75f;
            rotateSpeed = 21f;
            accel = 0.13f;
            fogRadius = 0f;
            itemCapacity = 90;
            health = 300f;
            engineOffset = 8f;
            hitSize = 11f;

            weapons.add(new Weapon("small-mount-weapon"){{
                top = false;
                reload = 10f;
                x = 2.27f;
                y = 1.7f;
                shoot = new ShootSpread(){{
                    shots = 2;
                    shotDelay = 3f;
                    spread = 2f;
                }};

                inaccuracy = 3f;
                shootSound = Sounds.shootAlpha;
                shootSoundVolume = 0.4f;

                bullet = new LaserBoltBulletType(4.0f, 11){{
                    scaleKeepVelocity = true;
                    width = 1.5f;
                    height = 5f;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    trailWidth = 1.2f;
                    trailLength = 4;
                    shootEffect = Fx.shootSmallColor;
                    smokeEffect = Fx.hitLaserColor;
                    backColor = trailColor = Pal.yellowBoltFront;
                    hitColor = Pal.yellowBoltFront;
                    frontColor = Color.white;
                    lightColor = Pal.yellowBoltFront;

                    lifetime = 80f;
                    buildingDamageMultiplier = 0.01f;
                    homingPower = 0.05f;
                }};
            }});
            weapons.add(new Weapon("unbounded-deployment-small-beam-weapon"){{
                top = false;
                shake = 1.2f;
                shootY = 4f;
                x = 0f;
                mirror = false;
                reload = 100f;
                recoil = 1.5f;
                shootSound = Sounds.shootLaser;
                soundPitchMin = 2.0f;

                bullet = new LaserBulletType(){{
                    damage = 25f;
                    recoil = 0f;
                    sideAngle = 0f;
                    sideWidth = 0f;
                    sideLength = 0f;
                    length = 150f;
                    colors = new Color[]{Pal.yellowBoltFront.cpy().a(0.4f), Pal.yellowBoltFront, Color.white};
                    buildingDamageMultiplier = 0.02f;
                }};
            }});
        }};

        // omega = new UnitType("omega"){{
        //     controller = u -> u.team.isAI() ? new BuilderAI(true, 400f) : new CommandAI();
        //     isEnemy = false;

        //     targetBuildingsMobile = false;
        //     lowAltitude = true;
        //     flying = true;
        //     mineSpeed = 16f;
        //     mineTier = 3;
        //     buildSpeed = 1.6f;
        //     drag = 0.05f;
        //     speed = 5f;
        //     rotateSpeed = 25f;
        //     accel = 0.11f;
        //     fogRadius = 0f;
        //     itemCapacity = 150;
        //     health = 500f;
        //     engineOffset = 6f;
        //     hitSize = 16f;
        //     payloadCapacity = (2 * 2) * tilePayload;

        //     weapons.add(new Weapon("small-mount-weapon"){{
        //         top = false;
        //         reload = 10f;
        //         x = 1f;
        //         y = 2f;
        //         shoot = new ShootSpread(){{
        //             shots = 5;
        //             shotDelay = 3f;
        //             spread = 2f;
        //         }};

        //         inaccuracy = 3f;
        //         shootSound = Sounds.shootAlpha;

        //         bullet = new LaserBoltBulletType(3.5f, 11){{
        //             scaleKeepVelocity = true;
        //             width = 1.5f;
        //             height = 5f;
        //             hitEffect = despawnEffect = Fx.hitBulletColor;
        //             trailWidth = 1.2f;
        //             trailLength = 4;
        //             shootEffect = Fx.shootSmallColor;
        //             smokeEffect = Fx.hitLaserColor;
        //             backColor = trailColor = Pal.yellowBoltFront;
        //             hitColor = Pal.yellowBoltFront;
        //             frontColor = Color.white;
        //             lightColor = Pal.yellowBoltFront;

        //             lifetime = 70f;
        //             buildingDamageMultiplier = 0.05f;
        //             homingPower = 0.1f;
        //         }};
        //     }});
        //     weapons.add(new Weapon("beam-weapon"){{
        //         top = false;
        //         shake = 2f;
        //         shootY = 4f;
        //         x = 6.5f;
        //         reload = 55f;
        //         recoil = 4f;
        //         shootSound = Sounds.shootLancer;

        //         bullet = new LaserBulletType(){{
        //             damage = 70f;
        //             recoil = 0f;
        //             sideAngle = 45f;
        //             sideWidth = 1f;
        //             sideLength = 70f;
        //             healPercent = 10f;
        //             collidesTeam = true;
        //             length = 200f;
        //             colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
        //             buildingDamageMultiplier = 0.05f;
        //         }};
        //     }});
        //     weapons.add(
        //     new Weapon("heal-weapon-mount"){{
        //         shootSound = Sounds.shootLaser;
        //         reload = 20f;
        //         x = 8f;
        //         y = -6f;
        //         rotate = true;
        //         bullet = new LaserBoltBulletType(5.2f, 10){{
        //             lifetime = 35f;
        //             healPercent = 5f;
        //             collidesTeam = true;
        //             backColor = Pal.heal;
        //             frontColor = Color.white;
        //         }};
        //     }},
        // }};
        //endregion
    }
}
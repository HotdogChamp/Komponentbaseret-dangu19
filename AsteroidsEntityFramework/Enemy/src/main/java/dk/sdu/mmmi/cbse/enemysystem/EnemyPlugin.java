package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService{
    private Entity enemy;

    public EnemyPlugin()
    {}

    @Override
    public void start(GameData gameData, World world) {
        //Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData){
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth();
        float y = gameData.getDisplayHeight();
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x,y,radians));

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        //Remove entities
        world.removeEntity(enemy);

    }
}

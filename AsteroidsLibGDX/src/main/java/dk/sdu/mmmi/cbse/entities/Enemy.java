package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
    import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;

public class Enemy extends SpaceObject{
    private final int MAX_BULLETS = 1;
    private ArrayList<Bullet> enemyBullets;

    private float maxSpeed;
    private float acceleration;

    public Enemy(ArrayList<Bullet> enemyBullets){
        this.enemyBullets = enemyBullets;

        x = Game.WIDTH / 3;
        y = Game.WIDTH / 3;

        speed = MathUtils.random(70, 100);
        maxSpeed = 200;
        acceleration = 200;

        shapex = new float[4];
        shapey = new float[4];

        radians = MathUtils.random(2 * 31415f);
        rotationSpeed = MathUtils.random(-2,2);
    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 2 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 2 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 10;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 10;

        shapex[3] = x + MathUtils.cos(radians + 2 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 2 * 3.1415f / 5) * 8;
    }

    public void shoot()
    {
        if(enemyBullets.size() == MAX_BULLETS)
        {
            return;
        }
        enemyBullets.add(new Bullet(x,y,radians));
    }


    public void update(float dt)
    {

        //Affect how fast the object rotates
        rotationSpeed = MathUtils.random(-10,10);

        //Accelerating
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        //Affectss the actual angle of the object
        radians += rotationSpeed * dt;

        //Set position
        x += dx * dt;
        y += dy * dt;

        //Set shape
        setShape();

        //Set screen wrap
        wrap();
    }

    public void draw(ShapeRenderer sr)
    {
        sr.setColor(255, 0,0,1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for(int i = 0, j = shapex.length-1 ; i < shapex.length ; j = i++)
        {
            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
        }

        sr.end();
    }


}

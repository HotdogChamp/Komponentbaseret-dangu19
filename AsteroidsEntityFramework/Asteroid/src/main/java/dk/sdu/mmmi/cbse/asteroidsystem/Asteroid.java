package dk.sdu.mmmi.cbse.asteroidsystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private int type;
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    //Number of edges for the asteroids.
    //Pick random points in the radius of the asteroids
    private int numPoints;

    public Asteroid(int type)
    {
        this.type = type;
        if(type == SMALL)
        {
            numPoints = 8;
            setRandomShape(numPoints);
        }
        else if(type == MEDIUM)
        {
            numPoints = 10;
            setRandomShape(numPoints);
        }
        else if(type == LARGE)
        {
            numPoints = 12;
            setRandomShape(numPoints);
        }
    }

    public void setRandomShape(int numPoints)
    {
        Asteroid.super.setShapeX(new float[numPoints]);
        Asteroid.super.setShapeY(new float[numPoints]);
    }
}

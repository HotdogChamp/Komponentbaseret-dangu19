package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.entities.Asteroid;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.main.Game;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;


import java.util.ArrayList;

public class PlayState extends GameState {
	public static OrthographicCamera cam;


	private ShapeRenderer sr;
	
	private Player player;
	private ArrayList<Bullet> playerBullets;
	private Enemy enemy;
	private ArrayList<Bullet> enemyBullets;
	private ArrayList<Asteroid> asteroids;


	private int level;
	private int totalAsteroids;
	private int numAsteroidsleft;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();

		playerBullets = new ArrayList<Bullet>();
		player = new Player(playerBullets);


		enemyBullets = new ArrayList<Bullet>();
		enemy = new Enemy(enemyBullets);


		asteroids = new ArrayList<Asteroid>();
		asteroids.add(new Asteroid(100,100, Asteroid.LARGE));
		asteroids.add(new Asteroid(200,100, Asteroid.MEDIUM));
		asteroids.add(new Asteroid(300,100, Asteroid.SMALL));

		level = 1;
		spawnAsteroids();
	}

	private void spawnAsteroids()
	{
		asteroids.clear();

		int numToSpawn = 4 + level - 1;
		totalAsteroids = numToSpawn * 7;
		numAsteroidsleft = totalAsteroids;

		for(int i = 0 ; i < numToSpawn ; i++)
		{
			float x = MathUtils.random(Game.WIDTH);
			float y = MathUtils.random(Game.HEIGHT);

			float dx = x - player.getX();
			float dy = y - player.getY();
			float dist = (float) Math.sqrt(dx * dx + dy * dy);

			while(dist < 100)
			{
				x = MathUtils.random(Game.WIDTH);
				y = MathUtils.random(Game.HEIGHT);
				dx = x - player.getX();
				dy = y - player.getY();
				dist = (float) Math.sqrt(dx * dx + dy * dy);
			}

			asteroids.add(new Asteroid(x,y, Asteroid.LARGE));
		}
	}

	
	public void update(float dt) {
		handleInput();


		cam = Game.cam;
		cam.position.x = player.getX();
		cam.position.y = player.getY();
		cam.update();

		player.update(dt);


		enemy.update(dt);
		enemy.shoot();

		//Update player bullets
		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).update(dt);
			if(playerBullets.get(i).shouldRemove())
			{
				playerBullets.remove(i);
				i--;
			}
		}

		//Update player bullets
		for (int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).update(dt);
			if(enemyBullets.get(i).shouldRemove())
			{
				enemyBullets.remove(i);
				i--;
			}
		}

		//update asteroids
		for(int i = 0 ; i < asteroids.size() ; i++)
		{
			asteroids.get(i).update(dt);
			if(asteroids.get(i).shouldRemove())
			{
				asteroids.remove(i);
				i--;
			}
		}
	}
	
	public void draw() {
		player.draw(sr, cam);
		enemy.draw(sr);

		for(int i = 0 ; i < playerBullets.size() ; i++)
		{
			playerBullets.get(i).draw(sr);
		}

		for(int i = 0 ; i < enemyBullets.size() ; i++)
		{
			enemyBullets.get(i).draw(sr);
		}

		for(int i = 0 ; i < asteroids.size() ; i++)
		{
			asteroids.get(i).draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		if(Gdx.input.justTouched())
		{
			player.shoot();
			System.out.println("Pressed");
		}
/*		if(GameKeys.isPressed(GameKeys.CLICK))
		{
			player.shoot();
			System.out.println("Pressed");
		}*/
	}

	public void dispose() {}
	
}










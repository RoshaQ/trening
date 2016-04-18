package com.mygdx.trening;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Trening extends ApplicationAdapter {

		private Music music;
		private Sound sound;


		private OrthographicCamera camera;

		private SpriteBatch batch; // puszka  z farb�
		public Texture texture;
		private BitmapFont font;
		private CurrentObject currentObject1;
		private CurrentObject currentObject2;
		private float timeHelper; // zmienna do tworzenia własnego timera
		private int licznikTimeHelper=0;

	@Override
	public void create () {

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));;// zaladowanie pliku mp3

		music.play();

		sound = Gdx.audio.newSound(Gdx.files.internal("bomb.ogg")); // zaladowanie dzwieku
		camera = new OrthographicCamera(800,480);

		texture = new Texture("foto.jpg");
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);

		currentObject1 = new CurrentObject(texture);
		currentObject1.x=50;
		currentObject1.y=50;
		currentObject1.height=200;
		currentObject1.width=200;
		currentObject2 = new CurrentObject(texture);
		currentObject2.x=300;
		currentObject2.y=300;
		currentObject2.height = currentObject2.getTexture().getHeight();
		currentObject2.width = currentObject2.getTexture().getWidth();
	}

	@Override
	public void render () {
		// UPDATE LOGIKI MUSI WYST�POWAC PRZED RYSOWANIEM NA EKRANIE I CZYSZCZENIE EKRANU
		update();
		Gdx.gl.glClearColor(1, 2, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();// otwieramy puszk� z farba
		batch.draw(currentObject1.getTexture(), currentObject1.x, currentObject1.y);
		batch.draw(currentObject2.getTexture(),currentObject2.x,currentObject2.y);
		font.draw(batch,"TEXT ______________________",400,240);
		batch.end(); //zamykamy puszk� z farb�
	}

	private void update()
	{
		camera.update();
		batch.setProjectionMatrix(camera.combined);// łaczymy spritebatch z kamera
		camera.position.set(currentObject1.x + currentObject1.width/2,currentObject1.y+currentObject1.height/2,0);// ustawienie pozycji obrazka na srodku
		// zoom i rotate kamery
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
		{
 			camera.zoom += 0.02f;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT))
		{
			camera.zoom -= 0.02f;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			currentObject1.x-=500*Gdx.graphics.getDeltaTime();// DeltaTime róznica pomiędzy poprzednią, a następna klatką wyrenderowana w metodzie render
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			currentObject1.y+=500*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			currentObject1.y-=500*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			currentObject1.x+=500*Gdx.graphics.getDeltaTime();
		}
		if (currentObject1.overlaps(currentObject2))
		{
			System.out.println("KOLIZJA");
			sound.play();
			int y=MathUtils.random(10);
			System.out.println(y);// losowosc
			if(y>8)
			{
				Gdx.app.exit();
			}
			currentObject1.x=0;
			currentObject2.y=0;


		}

		// WlASNY TIMER jak np. chce stworzyć randomowo nowego przeciwnika na ekranie :)
		timeHelper+=Gdx.graphics.getDeltaTime();
		if(timeHelper>0.02)
		{
			//camera.rotate(0.20f);
		}
		if(timeHelper>1)
		{
			licznikTimeHelper++;
			System.out.println("test czas wynosi "+licznikTimeHelper);
			timeHelper=0;
		}
	}
	@Override
	public void dispose() {
		// CZYSZCZENIE PAMI�CI Spritbatcha
	//super.dispose();
		batch.dispose();
		font.dispose();
		texture.dispose();
		music.dispose();
		sound.dispose();
	System.out.println("Finished application");
	}
}

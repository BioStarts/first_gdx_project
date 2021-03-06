package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid {
    private float x, y;
    private float speed;
    private Texture texture;

    private Circle circle;//

    public Asteroid() {
        texture = new Texture("asteroids64.png");

        this.x = MathUtils.random(0, 1280);
        this.y = MathUtils.random(0, 720);
        this.speed = MathUtils.random(50.0f, 120.0f);
        circle = new Circle(this.x - 32,this.y - 32,32);
    }

    public void update(float dt, Hero hero) {
        x -= speed * dt;
        if (x < -16) {
            x = 1280.0f;
            y = MathUtils.random(0, 720);
            speed = MathUtils.random(50.0f, 120.0f);
        }
        circle.setPosition(this.x - 32, this.y - 32);//перемещаем центр окружности вслед за метором
        if (circle.overlaps(hero.getCircle())){
            this.x = MathUtils.random(0, 1280);
            this.y = MathUtils.random(0, 720);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, this.x, this.y,32,32,64,64,1.0f,1.0f,0.0f,0,0,64,64,false,false);
    }
}

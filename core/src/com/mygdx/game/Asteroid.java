package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Asteroid {
    private float x, y;
    private float speed;
    private Texture texture;

    public Asteroid() {
        texture = new Texture("asteroids64.png");
        this.x = MathUtils.random(0, 1280);
        this.y = MathUtils.random(0, 720);
        this.speed = MathUtils.random(50.0f, 120.0f);
    }

    public void update(float dt) {
        x -= speed * dt;
        if (x < -16) {
            x = 1280.0f;
            y = MathUtils.random(0, 720);
            speed = MathUtils.random(50.0f, 120.0f);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }
}

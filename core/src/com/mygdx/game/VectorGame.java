package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class VectorGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture textureAsteroids;
    TextureRegion astReg;
    Rock rock;


    private class Rock {
        Vector2 position;
        Vector2 velocity;
        TextureRegion region;
        Vector2 mousePosition;
        Vector2 tmp;

        public Rock(TextureRegion region) {
            this.position = new Vector2(640,360);
            this.velocity = new Vector2(0,0);
            this.region = region;
            this.tmp = new Vector2(0,0);
        }

        public void render(SpriteBatch batch) {
            batch.draw(region, position.x -32,position.y - 32);
        }

        public void update(float dt) {
            position.mulAdd(velocity, dt);
            velocity.y -= 400.0f * dt;


            if (position.y < 32.0f){
                position.y = 32.0f;
                velocity.y *= -0.7f;
            }

            if (Gdx.input.justTouched()){
                mousePosition.x = Gdx.input.getX();
                mousePosition.y = 720.0f - Gdx.input.getY();

                tmp.set(mousePosition);
                tmp.sub(position);
                tmp.nor();
                tmp.scl(200.0f);

                velocity.add(tmp);
            }

        }

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        textureAsteroids = new Texture("asteroids64.png");
        astReg = new TextureRegion(textureAsteroids, 0, 0, 64, 64);
        rock = new Rock(astReg);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        rock.render(batch);
        batch.end();
    }

    public void update(float dt) {
        rock.update(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

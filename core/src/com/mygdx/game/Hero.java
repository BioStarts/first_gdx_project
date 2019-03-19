package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private Circle circle;//

    public Hero() {
        texture = new Texture("ship64.png");
        position = new Vector2(640.0f, 360.0f);
        speed = 300.0f;
        circle = new Circle(position.x - 32,position.y - 32,32);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32.0f, position.y - 32.0f);

        position.x = position.x > 1251.0f ? 1251.0f : position.x; // делаем чтобы корабль не уходил за левую и правую области видимости
        position.x = position.x < 35.5f ? 35.5f : position.x;

        if (position.y < -32.0f) { //делаем так чтобы корабль появлялся с противовположной стороны при пересечении верхней/нижней области видимости
            position.y = 782.0f;
        }
        if (position.y > 782.0f) {
            position.y = 32.0f;
        }

    } // рисуем корабль по центру

    public void update(float dt) { //настраиваем управление кораблем для клавиатуры и тачскрина
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt;
        }
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > position.x) {
                position.x += speed * dt;
            }
            if (Gdx.input.getX() < position.x) {
                position.x -= speed * dt;
            }
            if (720.0f - Gdx.input.getY() > position.y) {
                position.y += speed * dt;
            }
            if (720.0f - Gdx.input.getY() < position.y) {
                position.y -= speed * dt;
            }
        }
        circle.setPosition(position.x - 32, position.y - 32);//перемещаем центр окружности вслед за кораблем

    }
}

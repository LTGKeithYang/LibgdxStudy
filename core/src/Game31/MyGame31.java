package Game31;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame31 extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture hero1;
    private Sprite sprite;
    private float xPosition;

    @Override
    public void create() {
        camera = new OrthographicCamera(1920, 1080);
        batch = new SpriteBatch();
        hero1 = new Texture(Gdx.files.internal("assets31/hero1.png"));
        hero1.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        sprite = new Sprite(hero1);
        xPosition = -1920 / 2;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        sprite.setPosition(xPosition, -60);

        batch.begin();
        sprite.draw(batch);
        batch.end();
        xPosition = xPosition + 5;
    }

    @Override
    public void dispose() {
        batch.dispose();
        hero1.dispose();
    }
}

package Game33;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game33 extends ApplicationAdapter {
    private  static final float SCENE_WIDTH=640f;
    private static final float SCENE_HEIGHT=480f;
    private static final float FRAME_DURATION=1.0f/15.0f;
    private Animation animation;
    private float animationTime;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private TextureAtlas hero1Atlas;
    private Sprite sprite;

    @Override
    public void create()
    {
        camera=new OrthographicCamera();
        viewport=new FillViewport(SCENE_WIDTH,SCENE_HEIGHT,camera);
        batch=new SpriteBatch();
        hero1Atlas=new TextureAtlas(Gdx.files.internal("assets33/hero1Atlas.pack"));
        animation=new Animation(FRAME_DURATION,hero1Atlas.getRegions(), Animation.PlayMode.LOOP);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0.39f,0.58f,0.92f,1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime+= Gdx.graphics.getDeltaTime();

        sprite=new Sprite(animation.getKeyFrame(animationTime,true));
        sprite.setPosition(0,0);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width,int height)
    {
        viewport.update(width,height,false);
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        hero1Atlas.dispose();
    }



}

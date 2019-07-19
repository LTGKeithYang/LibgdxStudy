package Game36;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input.Keys;

public class Game36 extends ApplicationAdapter implements InputProcessor {
    private OrthographicCamera camera;
    private float screenWidth, screenHeight;
    private Viewport viewport;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private int[] background = new int[]{0};
    private int[] foreground = new int[]{1};

    private static final float FRAME_DURATION = 1.0f / 15.0f;
    private float speed = 300f;
    private float xPosition = 0;
    private float deltaTime = 0;
    private float animationTime = 0;
    private State state;
    private boolean isFacingRight;
    private Animation animationWalkingLeft;
    private Animation animationWalkingRight;

    private SpriteBatch batch;
    private TextureAtlas hero1Atlas;
    private TextureRegion hero1StandingLeft;
    private TextureRegion hero1StandingRight;
    private TextureRegion hero1Frame;
    private Sprite sprite;
    private int grid = 32;

    public enum State {
        Standing, Walking
    }

    @Override
    public void create() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new FillViewport(screenWidth, screenHeight, camera);
        batch = new SpriteBatch();
        isFacingRight = true;
        state = State.Standing;

        camera.update();
        camera.setToOrtho(false, screenWidth, screenHeight);
        tiledMap = new TmxMapLoader().load("assets36/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        hero1Atlas = new TextureAtlas(Gdx.files.internal("assets36/hero1Atlas.pack"));

        hero1StandingRight = hero1Atlas.findRegion("walk000");
        TextureRegion[] frameWalkingRight = new TextureRegion[3];
        frameWalkingRight[0] = hero1Atlas.findRegion("walk000");
        frameWalkingRight[1] = hero1Atlas.findRegion("walk001");
        frameWalkingRight[2] = hero1Atlas.findRegion("walk002");
        animationWalkingRight = new Animation(FRAME_DURATION, frameWalkingRight);

        hero1StandingLeft = hero1Atlas.findRegion("walk003");
        TextureRegion[] frameWalkingLeft = new TextureRegion[3];
        frameWalkingLeft[0] = hero1Atlas.findRegion("walk003");
        frameWalkingLeft[1] = hero1Atlas.findRegion("walk004");
        frameWalkingLeft[2] = hero1Atlas.findRegion("walk005");
        animationWalkingLeft = new Animation(FRAME_DURATION, frameWalkingLeft);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        deltaTime = Gdx.graphics.getDeltaTime();
        animationTime += deltaTime;
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(background);
        tiledMapRenderer.render(foreground);

        hero1Frame = isFacingRight ? hero1StandingRight : hero1StandingLeft;
        if (state == State.Walking) {
            hero1Frame = isFacingRight ? animationWalkingRight.getKeyFrame(animationTime, true) : animationWalkingLeft.getKeyFrame(animationTime, true);
            xPosition = isFacingRight ? xPosition + (speed * deltaTime) : xPosition - (speed * deltaTime);
        }
        sprite = new Sprite(hero1Frame);
        sprite.setPosition(xPosition, grid * 3 - 1);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(background);
        tiledMapRenderer.render(foreground);

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width,int height)
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        hero1Atlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.LEFT) {
            state = State.Walking;
            isFacingRight = false;
        }else if(keycode == Keys.RIGHT){
            state = State.Walking;
            isFacingRight = true;
        }
        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        state = State.Standing;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX < screenWidth/2) {
            state = State.Walking;
            isFacingRight = false;
        }else if( screenX > screenWidth/2){
            state = State.Walking;
            isFacingRight = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        state = State.Standing;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

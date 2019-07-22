package Game42CompleteDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.Console;

public class Game42 extends ApplicationAdapter implements InputProcessor {
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
    private Vector2 position=new Vector2();
    private Vector2 velocity=new Vector2();
    private float deltaTime = 0;
    private float animationTime = 0;
    private State state;
    private boolean isFacingRight;
    private boolean canJumpAndWalk;
    private Animation animationWalkingLeft;
    private Animation animationWalkingRight;


    private SpriteBatch batch;
    private TextureAtlas hero1Atlas;
    private TextureRegion hero1StandingLeft;
    private TextureRegion hero1StandingRight;
    private TextureRegion hero1JumpingRight;
    private TextureRegion hero1JumpingLeft;
    private TextureRegion hero1Frame;
    private Sprite sprite;
    private int grid = 32;
    private float maxVelocity = 300f;

    public enum State {
        Standing, Walking, Jumping
    }

    @Override
    public void create() {
        //获取屏幕基础信息
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        viewport = new FillViewport(screenWidth, screenHeight, camera);
        batch = new SpriteBatch();
        camera.update();
        camera.setToOrtho(false, screenWidth, screenHeight);

        //载入TileMap
        tiledMap = new TmxMapLoader().load("assets42/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //获取动画信息
        isFacingRight = true;
        state = State.Standing;
        hero1Atlas = new TextureAtlas(Gdx.files.internal("assets42/hero1Atlas.pack"));
        hero1StandingRight = hero1Atlas.findRegion("walk000");
        TextureRegion[] frameWalkingRight = new TextureRegion[3];
        frameWalkingRight[0] = hero1Atlas.findRegion("walk000");
        frameWalkingRight[1] = hero1Atlas.findRegion("walk001");
        frameWalkingRight[2] = hero1Atlas.findRegion("walk002");
        animationWalkingRight = new Animation(FRAME_DURATION, frameWalkingRight);
        hero1JumpingRight = hero1Atlas.findRegion("jumpright");


        hero1StandingLeft = hero1Atlas.findRegion("walk003");
        TextureRegion[] frameWalkingLeft = new TextureRegion[3];
        frameWalkingLeft[0] = hero1Atlas.findRegion("walk003");
        frameWalkingLeft[1] = hero1Atlas.findRegion("walk004");
        frameWalkingLeft[2] = hero1Atlas.findRegion("walk005");
        animationWalkingLeft = new Animation(FRAME_DURATION, frameWalkingLeft);
        hero1JumpingLeft = hero1Atlas.findRegion("jumpleft");

        //设置位置速度
        velocity.x = 300;
        velocity.y = 300;
        position.x = 200;
        position.y = screenHeight - 640;

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
            position.x = position.x + (velocity.x * deltaTime);
        } else if (state == State.Jumping) {
            hero1Frame = isFacingRight ? hero1JumpingRight : hero1JumpingLeft;
            if (canJumpAndWalk) {
                position.x = position.x + (velocity.x * deltaTime * 0.3f);
            }
            velocity.y -= maxVelocity * deltaTime;
            if (velocity.y < -maxVelocity) {
                velocity.y = -maxVelocity;
            }

            position.y = position.y + (velocity.y * deltaTime);
            if (position.y <= grid * 3) {
                position.y = grid * 3;
                velocity.y = 0;
                state = State.Standing;
                canJumpAndWalk = false;
            }
        }




        sprite = new Sprite(hero1Frame);
        sprite.setPosition(position.x, position.y);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(background);
        tiledMapRenderer.render(foreground);

        batch.begin();
        sprite.draw(batch);
        batch.end();
        Gdx.app.log("MyTag",state.toString());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        batch.dispose();
        hero1Atlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            if (state == State.Standing) {
                state = State.Walking;
            } else if (state == State.Jumping) {
                canJumpAndWalk = true;
            }
            isFacingRight = false;
            if (velocity.x > 0) {
                velocity.x = -velocity.x;
            }
        } else if (keycode == Input.Keys.RIGHT) {
            if (state == State.Standing) {
                state = State.Walking;
            } else if (state == State.Jumping) {
                canJumpAndWalk = true;
            }
            isFacingRight = true;
            if (velocity.x < 0) {
                velocity.x = -velocity.x;
            }
        } else if ((keycode == Input.Keys.SPACE && state == State.Standing) || (keycode == Input.Keys.SPACE && state == State.Walking)) {
            velocity.y = maxVelocity;
            state = State.Jumping;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
       if(state==State.Walking)
       {
           state=State.Standing;
       }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX < screenWidth / 2) {
            state = State.Walking;
            isFacingRight = false;
        } else if (screenX > screenWidth / 2) {
            state = State.Walking;
            isFacingRight = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        state = state.Standing;
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

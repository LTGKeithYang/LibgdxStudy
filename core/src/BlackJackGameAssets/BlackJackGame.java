package BlackJackGameAssets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BlackJackGame extends ApplicationAdapter {
    private static final Color BACKGROUND_COLOR=new Color(0.39f,0.58f,0.92f,1.0f);
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture1,texture2,texture3,texture4;
    private Texture bgTexture;
    private Image cardImage1,cardImage2,buttonImage1,buttonImage2;
    private Stage stage;

    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        bgTexture = new Texture(Gdx.files.internal("BlackJackGameAssets/table2_1024_768.png"));
        texture1 = new Texture(Gdx.files.internal("BlackJackGameAssets/cardback56_82.png"));
        texture2 = new Texture(Gdx.files.internal("BlackJackGameAssets/ace56_82.png"));
        texture3=new Texture(Gdx.files.internal("BlackJackGameAssets/buttonImage1.png"));
        texture4=new Texture(Gdx.files.internal("BlackJackGameAssets/buttonImage2.png"));


        cardImage1 = new Image(texture1);
        cardImage1.setOrigin(56 / 2, 82 / 2);
        cardImage1.setScale(0,1);

        cardImage2 = new Image(texture2);
        cardImage2.setOrigin(56 / 2, 82 / 2);
        cardImage2.setScale(0,1);

        buttonImage1=new Image(texture3);
        buttonImage1.setPosition(130,113);

        buttonImage2=new Image(texture4);
        buttonImage2.setPosition(130,113);

        stage = new Stage();
        stage.addActor(cardImage1);
        stage.addActor(cardImage2);
        stage.addActor(buttonImage1);
        stage.addActor(buttonImage2);


        buttonImage2.addAction(Actions.forever(Actions.sequence(Actions.fadeIn(0.5f),Actions.fadeOut(0.6f))));

        buttonImage2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                cardImage1.addAction(Actions.scaleTo(1,1));
                cardImage2.addAction(Actions.scaleTo(0,1));
                cardImage1.setPosition(716, 606);
                cardImage2.setPosition(484, 343);
                cardImage1.addAction(Actions.sequence(Actions.moveTo(484, 343, 0.5f)));
                cardImage1.addAction(Actions.sequence(Actions.delay(0.5f), Actions.scaleTo(0.0f, 1, 0.1f)));
                cardImage2.addAction(Actions.sequence(Actions.scaleTo(0,1)));
                cardImage2.addAction(Actions.sequence(Actions.delay(0.6f),Actions.scaleTo(1,1,0.1f)));
            }
        });

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render()
    {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r,BACKGROUND_COLOR.g,BACKGROUND_COLOR.b,BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bgTexture,-512,-384);
        batch.end();
        stage.act();
        stage.draw();

    }
}

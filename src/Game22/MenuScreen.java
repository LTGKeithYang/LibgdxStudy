package Game22;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MenuScreen implements Screen {
    private Skin skin;
    private TextureAtlas textureAtlas;
    private Button startButton;
    private Button optionsButton;
    private Button creditsButton;
    private Button facebookButton;
    private Image bg;
    private Stage stage;
    private StartScreen startScreen;
    private OptionsScreen optionsScreen;
    private CreditsScreen creditsScreen;
    private FacebookLinkScreen facebookLinkScreen;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        stage = new Stage();
        textureAtlas = new TextureAtlas(Gdx.files.internal("assets22/mainmenu.pack"));
        skin = new Skin(Gdx.files.internal("assets22/mainmenu.json"), textureAtlas);
        startButton = new Button(skin, "startbutton");
        optionsButton = new Button(skin, "optionsbutton");
        creditsButton = new Button(skin, "creditsbutton");
        facebookButton = new Button(skin, "facebookbutton");
        bg = new Image(textureAtlas.findRegion("bg"));
        bg.setPosition(0, 0);
        startButton.setPosition(Gdx.graphics.getWidth() / 2 - 210 / 2f, Gdx.graphics.getHeight() / 2 + 70);
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - 210 / 2f, Gdx.graphics.getHeight() / 2 - 10);
        creditsButton.setPosition(Gdx.graphics.getWidth() / 2 - 210 / 2f, Gdx.graphics.getHeight() / 2 - 90);
        facebookButton.setPosition(Gdx.graphics.getWidth() / 2 - 337 / 2f, Gdx.graphics.getHeight() / 2 - 170);
        startScreen=new StartScreen();
        optionsScreen=new OptionsScreen();
        creditsScreen=new CreditsScreen();
        facebookLinkScreen=new FacebookLinkScreen();

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(startScreen);
            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(creditsScreen);
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(optionsScreen);
            }
        });

        facebookButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(facebookLinkScreen);
            }
        });

        stage.addActor(bg);
        stage.addActor(startButton);
        stage.addActor(creditsButton);
        stage.addActor(optionsButton);
        stage.addActor(facebookButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}

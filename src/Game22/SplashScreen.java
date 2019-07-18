package Game22;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen {
    private Image splashImage;
    private Stage stage;
    public Button backButton;

    @Override
    public void render(float delta) {
     Gdx.gl.glClearColor(0,0,0,1);
     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
     stage.act();
     stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        stage=new Stage();
        splashImage=new Image(new Texture(Gdx.files.internal("assets22/Splashscreen.png")));
        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(1.0f),Actions.delay(1),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
                    }
                })));

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

    }
}

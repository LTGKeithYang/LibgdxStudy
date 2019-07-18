package Game22;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class StartScreen extends AbstractScreen {
    private Image startImage;

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        super.show();
        startImage = new Image(textureAtlas.findRegion("levelscreen"));
        stage.addActor(startImage);
        stage.addActor(backButton);
    }

    @Override
    public void hide() {
        dispose();
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

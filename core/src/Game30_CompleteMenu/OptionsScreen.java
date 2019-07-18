package Game30_CompleteMenu;


import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class OptionsScreen extends AbstractScreen {
    private Image bg;

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
        bg = new Image(Assets.menuSkin, "optionsscreen");
        stage.addActor(bg);
        stage.addActor(backButton);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

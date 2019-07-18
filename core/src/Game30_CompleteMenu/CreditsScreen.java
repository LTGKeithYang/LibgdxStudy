package Game30_CompleteMenu;


import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CreditsScreen extends AbstractScreen {
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
        bg=new Image(Assets.menuSkin,"creditsscreen");
        stage.addActor(bg);
        stage.addActor(backButton);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        dispose();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

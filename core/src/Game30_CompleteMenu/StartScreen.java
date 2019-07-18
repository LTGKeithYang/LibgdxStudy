package Game30_CompleteMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;


public class StartScreen extends AbstractScreen{
    private Image bg;
    private Image t[]=new Image[10];
    private Image lockselectbg[]=new Image[10];

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
//        startImage = new Image(Assets.manager.get("assets30/levelatlas"),"levelscreen");
//        stage.addActor(startImage);
        LevelStatus.put();
        LevelStatus.get();
        Array<AtlasRegion> atlasArrays=new Array<AtlasRegion>(Assets.levelAltas.getRegions());
        Image bg=new Image(atlasArrays.get(0));
        bg.setPosition((Gdx.graphics.getWidth()-bg.getWidth())/2, (Gdx.graphics.getHeight()-bg.getHeight())/2);
        stage.addActor(bg);

        for(int i=0; i<10; i++) {
            if (i+1<LevelStatus.levelclear){
                int star = LevelStatus.level[i];
                t[i]=new Image(atlasArrays.get(i+11));
                switch (star) {
                    case 1:  lockselectbg[i] = new Image(atlasArrays.get(21));
                        break;
                    case 2:  lockselectbg[i]  = new Image(atlasArrays.get(22));
                        break;
                    case 3:  lockselectbg[i]  =new Image(atlasArrays.get(23));
                        break;
                    default: lockselectbg[i]  =new Image(atlasArrays.get(21));
                        break;
                }
            }
            else{
                lockselectbg[i] =new Image();
                t[i]=new Image(atlasArrays.get(i+1));
            }
            if (i<5) {
                lockselectbg[i].setPosition(50 + 80 * i, 360);
                t[i].setPosition(50 + 80 * i, 360);
            }
            else
            {
                lockselectbg[i].setPosition(50 + 80 * (i-5), 280);
                t[i].setPosition(50 + 80 * (i-5), 280);
            }
            stage.addActor(lockselectbg[i]);
            stage.addActor(t[i]);
        }


        stage.addActor(backButton);
    }

    @Override
    public void hide()
    {
        super.hide();
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
        super.dispose();
    }
}

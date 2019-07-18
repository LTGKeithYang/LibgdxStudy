package Game22;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class AbstractScreen implements Screen {
    public Stage stage;
    public Button backButton;
    public Skin skin;
    public TextureAtlas textureAtlas;

    protected Skin getSkin()
    {
        if (skin==null)
        {
            textureAtlas=new TextureAtlas("assets22/mainmenu.pack");
            skin=new Skin(Gdx.files.internal("assets22/mainmenu.json"),textureAtlas);
        }
        return skin;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void show() {
        final MenuScreen menuScreen=new MenuScreen();
        stage = new Stage();
        backButton=new Button(getSkin(),"backbutton");
        backButton.setPosition(Gdx.graphics.getWidth()/2-66/2,Gdx.graphics.getHeight()/2-190f);
        backButton.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event,float x,float y)
           {
               ((Game)Gdx.app.getApplicationListener()).setScreen(menuScreen);
           }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose ()
    {
        if(skin!=null) skin.dispose();
        if(stage!=null)stage.dispose();
    }
}

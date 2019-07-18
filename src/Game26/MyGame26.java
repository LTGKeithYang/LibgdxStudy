package Game26;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame26 extends Game {
    private SpriteBatch batch;
    private Texture texture1,texture2;
    private Camera camera;
    private int refCount=0;

    public void create(){
        batch=new SpriteBatch();
        camera=new OrthographicCamera(500,700);
        AssetsTest.load();
        AssetsTest.manager.finishLoading();
        texture1=AssetsTest.manager.get("assets22/splashscreen.png",Texture.class);
        texture2=AssetsTest.manager.get("assets22/splashscreen.png",Texture.class);
        refCount=AssetsTest.manager.getReferenceCount("assets22/splashscreen.png");
        System.out.println(refCount);
        AssetsTest.load();
        AssetsTest.manager.finishLoading();
//        texture1=AssetsTest.manager.get("assets22/splashscreen.png",Texture.class);
//        texture2=AssetsTest.manager.get("assets22/splashscreen.png",Texture.class);
        refCount=AssetsTest.manager.getReferenceCount("assets22/splashscreen.png");
        System.out.println(refCount);
        AssetsTest.load();
        AssetsTest.manager.finishLoading();
        refCount=AssetsTest.manager.getReferenceCount("assets22/splashscreen.png");
        System.out.println(refCount);
    }

    public void render(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture1,-500/2,-700/2);
        batch.end();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        batch.dispose();
        texture1.dispose();
        texture2.dispose();
        AssetsTest.dispose();
    }
}

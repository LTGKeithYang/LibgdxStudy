package Game26;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsTest {
    public static AssetManager manager=new AssetManager();

    public static void load()
    {
        manager.load("assets22/splashscreen.png",Texture.class);
    }

    public static void unload()
    {
        manager.unload("assets22/splashscreen.png");
    }

    public static boolean update()
    {
        return manager.update();
    }

    public static void dispose()
    {
        manager.dispose();
    }
}

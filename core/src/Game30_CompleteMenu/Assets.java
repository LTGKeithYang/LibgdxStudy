package Game30_CompleteMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Assets {
    public static AssetManager manager = new AssetManager();
    public static Skin menuSkin;
    public static TextureAtlas levelAltas;

    public static void load() {
        manager.load("assets30/mainmenu.pack", TextureAtlas.class);
        manager.load("assets30/levelatlas.pack",TextureAtlas.class);
    }

    public static void setMenuSkin() {
        if (menuSkin == null) {
            menuSkin = new Skin(Gdx.files.internal("assets30/mainmenu.json"), manager.get("assets30/mainmenu.pack", TextureAtlas.class));
        }
    }

    public static void setLevel()
    {
        if (levelAltas==null){
            levelAltas=manager.get("assets30/levelatlas.pack",TextureAtlas.class);
        }
    }

    public static boolean update() {
        return manager.update();
    }

    public static void dispose() {
        manager.dispose();
    }

}


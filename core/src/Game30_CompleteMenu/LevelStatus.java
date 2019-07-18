package Game30_CompleteMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class LevelStatus {
    static int level[]=new int[10];
    public static Preferences prefs;
    public static Integer levelclear;

    public static void put()
    {
        Preferences prefs =  Gdx.app.getPreferences("Game30Prefs");
        prefs.putInteger("levelclear", 7);
        prefs.putInteger("level1", 3);
        prefs.putInteger("level2", 3);
        prefs.putInteger("level3", 3);
        prefs.putInteger("level4", 3);
        prefs.putInteger("level5", 2);
        prefs.putInteger("level6", 2);
        prefs.putInteger("level7", 1);
        prefs.putInteger("level8", 1);
        prefs.putInteger("level9", 1);
        prefs.putInteger("level10",1);
        prefs.flush();

    }

    public static void get()
    {
        Preferences prefs = Gdx.app.getPreferences("Game30Prefs");
        levelclear = prefs.getInteger("levelclear",0);
        level[0] = prefs.getInteger("level1",0);
        level[1] = prefs.getInteger("level2",0);
        level[2] = prefs.getInteger("level3",0);
        level[3] = prefs.getInteger("level4",0);
        level[4] = prefs.getInteger("level5",0);
        level[5] = prefs.getInteger("level6",0);
        level[6] = prefs.getInteger("level7",0);
        level[7] = prefs.getInteger("level8",0);
        level[8] = prefs.getInteger("level9",0);
        level[9]= prefs.getInteger("level10",0);
    }
}

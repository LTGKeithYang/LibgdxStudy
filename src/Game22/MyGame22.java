package Game22;

import com.badlogic.gdx.Game;

public class MyGame22 extends Game {
    private SplashScreen splashScreen;

    @Override
    public void create() {
        splashScreen=new SplashScreen();
        setScreen(splashScreen);
    }
}

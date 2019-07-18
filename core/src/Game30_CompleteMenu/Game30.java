package Game30_CompleteMenu;
import com.badlogic.gdx.Game;

public class Game30 extends Game {
    private SplashScreen splashScreen;

    @Override
    public void create() {
        splashScreen=new SplashScreen();
        Assets.load();
        Assets.manager.finishLoading();
        setScreen(splashScreen);
    }
}

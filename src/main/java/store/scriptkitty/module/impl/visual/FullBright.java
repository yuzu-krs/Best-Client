package store.scriptkitty.module.impl.visual;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import store.scriptkitty.event.impl.update.EventUpdate;
import store.scriptkitty.module.Category;
import store.scriptkitty.module.Module;
import store.scriptkitty.module.ModuleInfo;

@ModuleInfo(
        name="FullBright",
        description = "bright=full",
        category = Category.Visual
)

public class FullBright extends Module {

    private float oldGame;

    public FullBright(){
        setKey(Keyboard.KEY_M);
    }

    @Override
    public void onEnable(){
        super.onEnable();

        oldGame=mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable(){
        super.onDisable();

        mc.gameSettings.gammaSetting=oldGame;
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate=new Listener<>(e->{
            mc.gameSettings.gammaSetting=3;
    });
}

package store.scriptkitty.module.impl.motion;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import store.scriptkitty.event.impl.update.EventUpdate;
import store.scriptkitty.module.Category;
import store.scriptkitty.module.Module;
import store.scriptkitty.module.ModuleInfo;

@ModuleInfo(
        name="Sprint",
        description = "you're gay,deal with it",
        category = Category.Motion
)

public class Sprint extends Module {

    public Sprint(){
        setKey(Keyboard.KEY_B);
    }

    @Override
    public void onEnable(){
        super.onEnable();
    }

    @Override
    public void onDisable(){
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate=new Listener<>(e->{
        if(mc.thePlayer.isCollidedHorizontally) return ;
        if(mc.thePlayer.moveForward<0) return;
        if(mc.thePlayer.isUsingItem()) return;
        if(mc.thePlayer.isSneaking())return;



        mc.thePlayer.setSprinting(true);
    });
}

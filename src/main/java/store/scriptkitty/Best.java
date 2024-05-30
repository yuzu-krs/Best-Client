package store.scriptkitty;




import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import store.scriptkitty.command.CommandManager;
import store.scriptkitty.event.impl.input.EventKey;
import store.scriptkitty.module.ModuleManager;


@Getter
public enum Best implements Subscriber {
    INSTANCE;


    public static final Minecraft mc= Minecraft.getMinecraft();



    public static final EventBus BUS= EventManager.builder()
            .setName("root/Best")
            .setSuperListeners()
            .build();

    private String
            name="Best",
            version="b001",
            commandPrefix="#",
            clientPrefix="[BEST] ",
            authors="yuzu";

    private ModuleManager mm;
    private CommandManager cm;


    public final void init(){
        BUS.subscribe(this);
        Display.setTitle(name+"->"+version);

        mm=new ModuleManager();
        cm=new CommandManager();

    }

    public final void shutdown(){
        BUS.unsubscribe(this);
    }

    @Subscribe
    private final Listener<EventKey> listener=new Listener<>(e->{
        if(this.mm != null ){
            mm.getModules().values().forEach(m-> {
                if (m.getKey() == e.getKey()) {
                    m.toggle();
                }
            });
        }
    });


    public Minecraft getMc() {
        return mc;
    }
}

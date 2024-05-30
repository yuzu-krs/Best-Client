package store.scriptkitty.module;

import lombok.Getter;
import lombok.Setter;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;
import store.scriptkitty.Best;

@Getter
public abstract class Module implements Subscriber {
    private boolean toggled;

    private final String name,description;
    private final Category category;

    @Setter
    private int key;

    protected final Minecraft mc= Best.INSTANCE.getMc();

    public Module(){
        ModuleInfo info=getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(info,"CONFUSED ANNOTATION EXCEPTION");
        this.name=info.name();
        this.description=info.description();
        this.category=info.category();
    }

    public void onEnable(){
        Best.BUS.subscribe(this);
    }

    public void onDisable(){
        Best.BUS.unsubscribe(this);
    }

    public void onToggle(){}

    public void toggle(){
        onToggle();
        if(toggled){
            toggled=false;
            onDisable();
        }else{
            toggled=true;
            onEnable();
        }
    }


    public void setToggled(boolean toggled){
        onEnable();
        if(toggled){
            this.toggled=false;
            onDisable();
        }else{
            this.toggled=true;
            onEnable();
        }
    }

}

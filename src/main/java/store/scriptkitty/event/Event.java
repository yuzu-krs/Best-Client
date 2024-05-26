package store.scriptkitty.event;

import lombok.Getter;
import lombok.Setter;
import me.zero.alpine.event.CancellableEvent;
import me.zero.alpine.event.EventPhase;


@Getter
@Setter
public abstract class Event extends CancellableEvent {

    private EventPhase eventPhase;
    private EventFlow eventFlow;

    public boolean isPre(){
        if(eventPhase==null) return false;
        return eventPhase==EventPhase.PRE;
    }
    public boolean isOn(){
        if(eventPhase==null) return false;
        return eventPhase==EventPhase.ON;
    }
    public boolean isPOST(){
        if(eventPhase==null) return false;
        return eventPhase==EventPhase.POST;
    }

    public boolean isInbound(){
        if(eventPhase==null) return false;
        return eventFlow==EventFlow.INBOUND;
    }
    public boolean isOutbound(){
        if(eventPhase==null) return false;
        return eventFlow==EventFlow.OUTBOUND;
    }


}

package store.scriptkitty.event.impl.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;
import store.scriptkitty.event.Event;

@Getter
@Setter
@AllArgsConstructor
public final class EventPacket extends Event {
    private Packet<?> packet;
}


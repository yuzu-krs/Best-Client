package store.scriptkitty.event.impl.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import store.scriptkitty.event.Event;

@Getter
@AllArgsConstructor
public class EventKey extends Event {
    private final int key;
}

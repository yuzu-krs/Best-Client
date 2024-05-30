package store.scriptkitty.exception;

import net.minecraft.util.EnumChatFormatting;
import store.scriptkitty.util.ChatUtil;

public class CommandException extends IllegalArgumentException{

    public CommandException(String message){
        super(message);
        ChatUtil.addChatMessage(EnumChatFormatting.RED+message);
    }

}

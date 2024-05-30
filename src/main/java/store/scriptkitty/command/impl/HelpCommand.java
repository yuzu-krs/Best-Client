package store.scriptkitty.command.impl;


import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import store.scriptkitty.Best;
import store.scriptkitty.command.Command;
import store.scriptkitty.command.CommandInfo;
import store.scriptkitty.exception.CommandException;
import store.scriptkitty.util.ChatUtil;
import tv.twitch.chat.Chat;

@CommandInfo(
        name="help",
        usage = "#help <command>",
        description = "Only noobs use this feature!",
        aliases = {"hlp","hlep"}

)


public final class HelpCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length > 0) {
            ChatUtil.addChatMessage("\n");
            final Command command = Best.INSTANCE.getCm().getCommand(args[0])
                    .orElseThrow(() ->
                            new CommandException(String.format(EnumChatFormatting.RED + "ERROR: Command \"%s\" not found!\n", args[0])));
            return;
        }
        ChatUtil.addChatMessage("\n");
        Best.INSTANCE.getCm()
                .getCommands()
                .values()
                .stream()

                // .filter(command -> !(command instanceof HelpCommand))
                .forEach(command -> ChatUtil.addChatMessage(
                        String.format(EnumChatFormatting.YELLOW + "%s " + EnumChatFormatting.WHITE + "- " + EnumChatFormatting.GRAY + "%s", " " + command.getName(), command.getDescription() + "\n")
                        , true));

        ChatUtil.addChatMessage("\n");
    }
}

package store.scriptkitty.command;

import lombok.Getter;
import org.reflections.Reflections;
import store.scriptkitty.Best;
import store.scriptkitty.exception.CommandException;
import store.scriptkitty.util.ChatUtil;

import java.util.*;

@Getter
public class CommandManager {
    public static final String COMMAND_PREFIX = Best.INSTANCE.getCommandPrefix();

    private Map<String, Command> commands;

    public CommandManager(){
        this.init();
    }

    private void init(){
        this.commands=new HashMap<>();
        try{
            this.register();
        }catch (CommandException e){
            throw new CommandException("Failed to register commands!");
        }
    }

    public boolean handleCommand(final String message) {
        if (message.isEmpty()) return false;
        final String[] args = message.substring(1).split(" ");

        if (message.equalsIgnoreCase(COMMAND_PREFIX)) {
            ChatUtil.addChatMessage(String
                            .format("Type %shelp to get a list of all commands and their usage \n Type %s <command> to see information for a specific command\n", COMMAND_PREFIX, COMMAND_PREFIX)
                    , true);
            return true;
        }

        try {
            this.getCommand(args[0]).orElseThrow(() ->
                            new CommandException(String
                                    .format("ERROR:\n Command \"%s\" not found. \n Use %shelp to see the full list of commands!", args[0], COMMAND_PREFIX)))
                    .execute(Arrays.copyOfRange(args,1,args.length));
        } catch (net.minecraft.command.CommandException ignored) {
        }
        return true;

    }

    private void register() throws CommandException{
        final Reflections reflections=new Reflections();
        final Set<Class<? extends Command>> classes=reflections.getSubTypesOf(Command.class);
        for(Class<? extends Command> command:classes){
            try{
                final Command comm=command.newInstance();
                this.commands.put(comm.getName(),comm);

            }catch (InstantiationException|IllegalAccessException e){
                System.err.println("Failed to initialize command: "+command.getName()+"!");
            }
        }
    }


    public Optional<Command> getCommand(final String commandName){
        final Command command=this.commands.get(commandName);
        if(command!=null){
            return Optional.of(command);
        }else{
            return Optional.ofNullable(this.commands.values()
                    .stream()
                    .filter(cmd->cmd.isAlias(commandName))
                    .findFirst()
                    .orElseThrow(()->
                            new CommandException("Couldn't find command specified!")));
        }
    }
}

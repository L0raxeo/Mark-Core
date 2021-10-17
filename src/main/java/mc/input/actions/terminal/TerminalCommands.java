package mc.input.actions.terminal;

import mc.input.actions.Action;

public class TerminalCommands
{

    /**
     * Gets the appropriate terminal command
     * @param action determines which command is being returned based on the "type" variable within the object
     * @return command as a string
     */
    public static String getCommand(Action action)
    {
        return switch (action.type)
                {
                    case "[search]" -> "cmd.exe /c start https://www.google.com/search?q=" + action.info.replace(" ",
                            "+");
                    default -> null;
                };
    }

}

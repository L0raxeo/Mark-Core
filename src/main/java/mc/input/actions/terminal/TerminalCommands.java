package mc.input.actions.terminal;

import mc.input.actions.Action;

public class TerminalCommands
{

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

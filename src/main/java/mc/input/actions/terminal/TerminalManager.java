package mc.input.actions.terminal;

import mc.input.actions.Action;

import java.io.IOException;

public class TerminalManager
{

    /**
     * Executes Command
     * @param action passed through to the function that returns the command associated with it
     */
    public static void queueCommand(Action action)
    {
        try
        {
            Runtime.getRuntime().exec(TerminalCommands.getCommand(action));
        }
        catch (IOException e)
        {
            System.out.println("[Markus]: ERROR - unable to run command. This could be a problem. (Terminal Manager)");
        }
    }

}

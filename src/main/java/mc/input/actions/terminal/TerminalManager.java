package mc.input.actions.terminal;

import mc.input.actions.Action;
import mc.utils.VersionInfo;

import java.io.IOException;

/**
 * Manages terminal/cmd commands {@link TerminalCommands}, which
 * are versions of {@link Action}.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "1.0",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
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

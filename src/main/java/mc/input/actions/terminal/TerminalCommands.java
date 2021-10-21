package mc.input.actions.terminal;

import mc.input.actions.Action;
import mc.utils.VersionInfo;

/**
 * Container for all terminal commands,
 * determined by the info of {@link Action}
 * specified in parameters.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/21/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
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

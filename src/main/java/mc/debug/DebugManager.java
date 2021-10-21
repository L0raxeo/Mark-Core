package mc.debug;

import mc.utils.FileLoader;
import mc.utils.VersionInfo;

import java.io.IOException;

/**
 * Manages debug mode, and all
 * actions associated with it.
 *
 * Debug commands handled separately
 * in {@link DebugCommands}
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
public class DebugManager
{

    /**
     * Debug command which is before all debug commands
     */
    public static String debugCommand = ".d/";

    /**
     * Whether debug mode is on.
     */
    public static boolean active;

    /**
     * initializes debugger, determining whether the program was exited while it was active or inactive.
     *
     * @throws IOException when there is an error reading the debug_info.txt file
     */
    public static void init() throws IOException
    {
        if (FileLoader.readFile("debug_info.txt").equals("true"))
            active = true;
        else if (FileLoader.readFile("debug_info.txt").equals("false"))
            active = false;
    }

    /**
     * Determines which command was invoked.
     * @param argument is the specific command.
     */
    public static void checkCommands(String argument)
    {
        try
        {
            if (argument.contains("debug_toggle"))
                DebugCommands.toggleDebugMode();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

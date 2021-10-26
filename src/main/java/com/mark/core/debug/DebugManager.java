package com.mark.core.debug;

import com.mark.core.utils.FileLoader;
import com.mark.core.utils.VersionInfo;

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
     * Determines which command was invoked.
     * @param argument is the specific command.
     */
    public static void checkCommands(String argument)
    {
        try
        {
            if (argument.contains("debug_toggle"))
                DebugCommands.toggleDebugMode();
            else if (argument.contains("force_exit"))
                DebugCommands.forceExit();
            else
                System.out.println("[Mark]: '" + argument + "' is not a debug command");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

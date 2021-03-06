package com.arkicore.mark.core.debug;

import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.VersionInfo;

import java.io.IOException;

/**
 * Handles debug commands/input.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/24/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class DebugCommands
{

    /**
     * Toggles debug mode depending on the current mod.
     *
     * @throws IOException when there is an error with reading or writing to the debug_info.txt file.
     */
    public static void toggleDebugMode() throws IOException
    {
        if (FileLoader.readFile("debug_info.txt").equals("true"))
        {
            FileLoader.writeFile("debug_info.txt", "false");
            DebugManager.active = false;
            System.out.println("[Core] debug/INFO [com.mark.core.debug]: debug mode off");
        }
        else if (FileLoader.readFile("debug_info.txt").equals("false"))
        {
            FileLoader.writeFile("debug_info.txt", "true");
            DebugManager.active = true;
            System.out.println("[Core] debug/INFO [com.mark.core.debug]: debug mode on");
        }
        else
        {
            System.out.println("[Core] debug/ERROR [com.mark.core.debug] could not toggle debug mode (Commands)");
            System.exit(-1);
        }
    }

    public static void forceExit()
    {
        System.exit(0);
    }

}

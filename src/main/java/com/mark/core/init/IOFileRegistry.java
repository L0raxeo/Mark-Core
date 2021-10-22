package com.mark.core.init;

import com.mark.core.utils.FileLoader;
import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;

/**
 * Initialization of all default files,
 * not including plugins.
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
public class IOFileRegistry
{

    /**
     * Initializes prefab files
     *
     * @throws IOException traces to MarkCore initialization sequence
     */
    public static void init() throws IOException
    {
        if (!new File("lastSpoken.txt").exists())
        {
            FileLoader.writeFile("lastSpoken.txt", "You haven't spoken before.");
        }

        if (!new File("debug_info.txt").exists())
        {
            FileLoader.writeFile("debug_info.txt", "false");
        }

        if (!new File("customResponses.txt").exists())
        {
            FileLoader.writeFile("customResponses.txt", "");
        }
    }

}

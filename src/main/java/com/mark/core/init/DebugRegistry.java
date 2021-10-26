package com.mark.core.init;

import com.mark.core.debug.DebugManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.Registry;
import com.mark.core.utils.VersionInfo;

import java.io.IOException;

/**
 * The debug registry is the
 * registry for the {@link DebugManager}.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/25/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class DebugRegistry implements Registry
{

    /**
     * initializes debugger, determining whether the program was exited while it was active or inactive.
     *
     * @throws IOException when there is an error reading the debug_info.txt file
     */
    @Override
    public void init() throws IOException
    {
        if (FileLoader.readFile("debug_info.txt").equals("true"))
            DebugManager.active = true;
        else if (FileLoader.readFile("debug_info.txt").equals("false"))
            DebugManager.active = false;
    }

}

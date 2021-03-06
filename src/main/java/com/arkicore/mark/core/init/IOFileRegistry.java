package com.arkicore.mark.core.init;

import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.Registry;
import com.arkicore.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;

/**
 * Initialization of all default files,
 * not including plugins.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "3.0",
        releaseDate = "10/21/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class IOFileRegistry implements Registry
{

    /**
     * Initializes prefab files
     *
     * @throws IOException traces to MarkCore initialization sequence
     */
    @Override
    public void preInit() throws IOException
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
            FileLoader.writeFile("customResponses.txt", "hello=Hello!",
                                                                        "hi=Hello!",
                                                                        "who:[1w]:=:[search]:",
                                                                        "what:[1w]:=:[search]:",
                                                                        "where:[1w]:=:[search]:",
                                                                        "when:[1w]:=:[search]:",
                                                                        "why:[1w]:=:[search]:",
                                                                        "how:[1w]:=:[search]:",
                                                                        "when were you born=September 24, 2021");
        }
    }

}

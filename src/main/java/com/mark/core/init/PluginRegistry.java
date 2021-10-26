package com.mark.core.init;

import com.mark.core.plugins.PluginManager;
import com.mark.core.utils.Registry;
import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Initialization of external plugins,
 * including registry and execution.
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
public class PluginRegistry implements Registry
{

    /**
     * Executes each plugin and creating
     * and indexing each associated process
     */
    @Override
    public void init() throws IOException
    {
        // Iterates through all registered plugins
        for (File f : PluginManager.allPlugins.keySet())
        {
            System.out.println("[Core] plugin init/INFO [com.mark.core.init]: attempting to build [" + f.getName() + "]");

            // Process builder buffers each process by building each step, and once that process is determined, it is executed
            // 3 args: java -jar currentPlugin.jar
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", f.getName());
            // Sets preferred working directory
            processBuilder.directory(new File("plugins"));
            // Builds/executes process builder
            Process p = processBuilder.start();

            // Indexes process with plugin in case alterations such as "exiting" is necessary
            PluginManager.allPlugins.put(f, p);

            System.out.println("[Core] plugin init/INFO [com.mark.core.init]: successfully built [" + f.getName() + "]");
        }
    }

    /**
     * Registers each plugin and implements it into Mark Core
     */
    @Override
    public void preInit()
    {
        // Handles plugins directory and its existence
        if (!Files.exists(Paths.get("plugins")))
        {
            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: plugins directory does not exist. creating new one...");

            // Creates new directory if plugins directory doesn't exist
            boolean wasSuccessful = new File("plugins").mkdirs();

            if (wasSuccessful)
            {
                System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: successfully created plugins directory");
            }
        }
        else
            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: plugins directory already exists. skipping lines...");

        // Registers plugins
        File[] files = new File("plugins").listFiles();

        assert files != null && files.length > 0 : "list variable is null or empty";
        for (File file : files)
        {
            if (file.isFile())
            {
                System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: attempting to register [" + file.getName() + "]");
                PluginManager.allPlugins.put(file, null);
                System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: successfully registered [" + file.getName() + "]");
            }
        }
    }

}

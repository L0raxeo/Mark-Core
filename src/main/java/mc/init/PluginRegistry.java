package mc.init;

import mc.plugins.PluginManager;

import java.io.File;
import java.io.IOException;

/**
 * Initialization of external plugins
 */
public class PluginRegistry
{

    /**
     * Executes each plugin
     */
    public static void init() throws IOException {
        // Iterates through all registered plugins
        for (File f : PluginManager.allPlugins.keySet())
        {
            // Process builder buffers each process by building each step, and once that process is determined, it is executed
            // 3 args: java -jar currentPlugin.jar
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", f.getName());
            // Sets preferred working directory
            processBuilder.directory(new File("plugins"));
            // Builds/executes process builder
            Process p = processBuilder.start();

            // Indexes process with plugin in case alterations such as "exiting" is necessary
            PluginManager.allPlugins.put(f, p);

            System.out.println("[INFO]: successfully built [" + f.getName() + "]");
        }
    }

    /**
     * Registers each plugin and implements it into Mark Core
     */
    public static void register()
    {
        File[] files = new File("plugins").listFiles();

        // only iterates if file array is not null
        assert files != null;
        for (File file : files)
        {
            if (file.isFile())
            {
                PluginManager.allPlugins.put(file, null);
                System.out.println("[INFO]: registered [" + file.getName() + "]");
            }
        }
    }

}

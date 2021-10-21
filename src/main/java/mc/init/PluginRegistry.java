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
        for (File f : PluginManager.allPlugins)
        {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", f.getName());
            processBuilder.directory(new File("plugins"));
            processBuilder.start();

            System.out.println("[INFO]: successfully built [" + f.getName() + "]");
        }
    }

    /**
     * Registers each plugin and implements it into Mark Core
     */
    public static void register()
    {
        File[] files = new File("plugins").listFiles();

        assert files != null;
        for (File file : files)
        {
            if (file.isFile())
            {
                PluginManager.allPlugins.add(file);
                System.out.println("[INFO]: registered [" + file.getName() + "]");
            }
        }
    }

}

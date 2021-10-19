package mc.init;

import mc.plugins.PluginManager;

import java.io.File;

/**
 * Initialization of external plugins
 */
public class PluginRegistry
{

    /**
     * Executes each plugin
     */
    public static void init()
    {

    }

    /**
     * Registers each plugin and implements it into Mark Core
     */
    public static void register()
    {
        File[] files = new File("/plugins").listFiles();

        assert files != null;
        for (File file : files)
        {
            if (file.isFile())
                PluginManager.allPlugins.add(file);
        }
    }

}

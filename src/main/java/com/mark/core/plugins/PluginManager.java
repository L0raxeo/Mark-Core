package com.mark.core.plugins;

import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages all plugins not including initialization and registry.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "2.0",
        releaseDate = "10/29/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class PluginManager
{
    /**
     * Array with all Plugins and associated processes
     */
    public static ArrayList<Plugin> allPlugins = new ArrayList<>();

    public static Plugin getPluginByName(String name)
    {
        for (Plugin plugin : allPlugins)
        {
            if (plugin.getRootDir().getName().equals(name))
                return plugin;
        }

        return null;
    }

}

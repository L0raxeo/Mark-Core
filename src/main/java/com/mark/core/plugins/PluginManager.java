package com.mark.core.plugins;

import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    /**
     * Gets plugin in allPlugins arraylist
     * by name.
     * @param name of plugin being retrieved.
     * @return plugin with associated name.
     */
    public static Plugin getPluginByName(String name)
    {
        for (Plugin plugin : allPlugins)
        {
            if (Objects.equals(plugin.NAME, name))
                return plugin;
        }

        return null;
    }

    /**
     * Gets plugin in allPlugins arraylist
     * by ID;
     *
     * @param ID of plugin being retrieved.
     * @return plugin with associated ID,
     */
    public static Plugin getPluginByID(String ID)
    {
        for (Plugin plugin : allPlugins)
        {
            if (Objects.equals(plugin.ID, ID))
                return plugin;
        }

        return null;
    }

}

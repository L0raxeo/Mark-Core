package mc.plugins;

import mc.utils.VersionInfo;

import java.io.File;
import java.util.HashMap;

/**
 * Manages all plugins not including initialization and registry.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "1.0",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class PluginManager
{
    /**
     * HashMap with all Plugins and associated processes
     */
    public static HashMap<File, Process> allPlugins = new HashMap<>();

}

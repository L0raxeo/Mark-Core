package com.arkicore.mark.core.init;

import com.arkicore.mark.core.CoreEngine;
import com.arkicore.mark.core.comms.Transceiver;
import com.arkicore.mark.core.plugins.Plugin;
import com.arkicore.mark.core.plugins.PluginManager;
import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.Registry;
import com.arkicore.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Initialization of external plugins,
 * including registry and execution.
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
public class PluginRegistry implements Registry
{

    /**
     * Registers each plugin and implements it into Mark Core
     */
    @Override
    public void preInit() throws IOException
    {
        // Checks existence of plugins folder
        System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: attempting to scan plugins directory for plugins. This includes checking the existence of the directory 'plugins'.");

        // Handles plugin dirs and its existence
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
        for (File rootDir : files)
        {
            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: attempting to register plugin [" + rootDir.getName() + "]...");

            Plugin curPlugin;

            // DIRS //
            File jarFile = null;
            File messenger = null;
            File receiver = null;

            // INFO //
            String name = null;
            String id = null;
            String version = null;

            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: looking for children of plugin [" + rootDir.getName() + "], including the jarFile, messenger, and receiver.");
            for (File child : Objects.requireNonNull(rootDir.listFiles()))
            {
                if (child.getName().contains(".jar"))
                {
                    jarFile = child;
                    System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found jarFile for plugin [" + rootDir.getName() + "].");
                }
                else if (child.getName().equals("libs"))
                {
                    System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found libs directory for plugin [" + rootDir.getName() + "].");
                    for (File transceiver : Objects.requireNonNull(child.listFiles()))
                    {
                        if (transceiver.getName().equals("send.txt"))
                        {
                            messenger = transceiver;
                            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found messenger for plugin [" + rootDir.getName() + "].");
                        }
                        else if (transceiver.getName().equals("receive.txt"))
                        {
                            receiver = transceiver;
                            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found receiver for plugin [" + rootDir.getName() + "].");
                        }
                        else if (transceiver.getName().equals("info.txt"))
                        {
                            ArrayList<String> info = FileLoader.readAllLinesFromFile(transceiver.getPath());

                            name = info.get(0);
                            id = info.get(1);
                            version = info.get(2);

                            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found info for plugin [" + rootDir.getName() + "].");
                        }
                    }

                    System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: found all children for libs for plugin [" + rootDir.getName() + "].");
                }
            }

            if (jarFile == null)
                System.out.println("[Core] plugin registry/WARN [com.mark.core.init]: could not find jar file for plugin [" + rootDir.getName() + "].");
            else if (messenger == null)
                System.out.println("[Core] plugin registry/WARN [com.mark.core.init]: could not find messenger file for plugin [" + rootDir.getName() + "].");
            else if (receiver == null)
                System.out.println("[Core] plugin registry/WARN [com.mark.core.init]: could not find receiver file for plugin [" + rootDir.getName() + "].");

            curPlugin = new Plugin(rootDir, jarFile, messenger, receiver, null, name, id, version);

            System.out.println("[Core] plugin summary/INFO [com.mark.core.init]: plugin info summary - name: " + curPlugin.NAME + ", id: " + curPlugin.ID + ", version:" + curPlugin.VERSION);

            PluginManager.allPlugins.add(curPlugin);

            System.out.println("[Core] plugin registry/INFO [com.mark.core.init]: successfully registered [" + curPlugin.ID + "]");
        }
    }

    /**
     * Executes each plugin and creating
     * and indexing each associated process
     */
    @Override
    public void init() throws IOException
    {
        // Iterates through all registered plugins
        for (Plugin plugin : PluginManager.allPlugins)
        {
            System.out.println("[Core] plugin init/INFO [com.mark.core.init]: attempting to build [" + plugin.ID + "]");
            // Process builder buffers each process by building each step, and once that process is determined, it is executed
            // 3 args: java -jar currentPlugin.jar
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", plugin.getJarFile().getName());
            // Sets preferred working directory
            processBuilder.directory(new File(plugin.getRootDir().getPath()));
            // Builds/executes process builder
            // Indexes process with plugin in case alterations such as "exiting" is necessary
            plugin.process = processBuilder.start();

            System.out.println("[Core] plugin init/INFO [com.mark.core.init]: successfully built [" + plugin.ID + "]");
        }
    }

    @Override
    public void postInit()
    {
        System.out.println("[Core] plugin post-init/INFO [com.mark.core.init]: attempting to ping all plugins");

        for (Plugin plugin : PluginManager.allPlugins)
        {
            System.out.println("[Core] plugin post-init/INFO [com.mark.core.init]: pinging plugin [" + plugin.ID + "]");

            // [.d/ping_for_pluginIdentification]
            // The plugin will check to see if the message received is "[.d/ping_for_ + Reference.ID]"
            plugin.queueMessage("[.d/ping_for_" + plugin.ID + "]");

            System.out.println("[Core] plugin post-init/INFO [com.mark.core.init]: successfully sent ping without errors to plugin [" + plugin.ID + "]");

            boolean receivedConfirmingPing = false;

            long now;
            long lastTime = System.nanoTime();
            long timer = 0;

            while (!receivedConfirmingPing)
            {
                now = System.nanoTime();
                timer += now - lastTime;
                lastTime = now;

                if (plugin.readMessage().equals("[.d/ping_received_for_" + plugin.ID + "]"))
                    receivedConfirmingPing = true;
                else if (timer >= 2147483647)
                {
                    CoreEngine.shutdownMessage = "COULD NOT RECEIVE PING AND REGISTER PLUGIN [" + plugin.ID + "]";
                    System.exit(-1);
                }
            }

            System.out.println("[Core] plugin post-init/INFO [com.mark.core.init]: successfully pinged plugin [" + plugin.NAME + "]");
        }

        System.out.println("[Core] plugin post-init/INFO [com.mark.core.init]: successfully pinged all plugins!");
    }

}

package com.mark.core;

import com.mark.core.debug.DebugManager;
import com.mark.core.init.IOFileRegistry;
import com.mark.core.init.PluginRegistry;
import com.mark.core.input.CustomResponseManager;
import com.mark.core.input.InputHandler;
import com.mark.core.nuclei.NucleusManager;
import com.mark.core.plugins.PluginManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * The base of all Mark-Core processes/plugins.
 * This class is the initial starting point of
 * the M.A.R.K.
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
public class MarkCore
{

    /**
     * Wakes Mark-Core and executes all initialization methods
     */
    public void wake()
    {
        System.out.println("[Core] init/INFO [com.mark.core]: executing wake process...");
        System.out.println("[Core] init/INFO [com.mark.core]: adding shutdown hook.");
        Runtime.getRuntime().addShutdownHook(new Thread(this::terminateProgram));
        System.out.println("[Core] init/INFO [com.mark.core]: successfully added shutdown hook to runtime, and connected it to terminate program.");

        try
        {
            preInit();
            init();
            postInit();
        }
        catch (IOException e)
        {
            System.out.println("[Core] init/WARN [com.mark.core]: fatal error during initialization sequence.");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("[Core] init/INFO [com.mark.core]: successfully completed initialization sequence");
        System.out.println("[Core] Startup Done!");
        System.out.println("[Core] waking M.A.R.K. Core...");
        System.out.println("===============================================================================");
        System.out.println("[Mark] Modular, Assistant, Registry, Kernel - M.A.R.K. is awake and listening");
        InputHandler.listen();
    }

    /**
     * For processes that do not depend on Mark-Core components
     *
     * @throws IOException handled by wake()
     */
    public void preInit() throws IOException
    {
        System.out.println("[Core] init/INFO [com.mark.core]: attempting to execute pre-init sequence");

        IOFileRegistry.init();
        PluginRegistry.register();

        System.out.println("[Core] init/INFO [com.mark.core]: successfully executed pre-init sequence");
    }

    /**
     * For processes that depend on processes initialized in the preInit() method
     *
     * @throws IOException handled by wake()
     */
    public void init() throws IOException
    {
        System.out.println("[Core] init/INFO [com.mark.core]: attempting to initialize (execute init sequence) Mark-Core");

        DebugManager.init();
        CustomResponseManager.register();
        PluginRegistry.init();

        System.out.println("[Core] init/INFO [com.mark.core]: successfully initialized (executed init sequence) Mark-Core");
    }

    /**
     * For initialization sequences that might depend on processes
     * initialized in init() method, or for registering components
     * from external files to the main program.
     *
     * @throws IOException handled by wake()
     */
    public void postInit() throws IOException
    {
        System.out.println("[Core] init/INFO [com.mark.core]: attempting to execute post-init sequence");

        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));

        System.out.println("[Core] init/INFO [com.mark.core]: successfully executed post-init sequence");
    }

    /**
     * Exits and destroys all currently running plugins.
     * Iterates through all plugins in {@link PluginManager}
     */
    private void terminateProgram()
    {
        System.out.println("[Core] shutDownHook/WARN [com.mark.core]: Mark-Core abruptly terminated. This could be a problem.\n[Core] shutdownHook/INFO [com.mark.core]: attempting to shut down...");

        for (Map.Entry<File, Process> e : PluginManager.allPlugins.entrySet())
        {
            System.out.println("[Core] shutdownHook/INFO [com.mark.core]: attempting to terminate plugin [" + e.getKey().getName() + "]");
            e.getValue().destroy();
            System.out.println("[Core] successfully terminated plugin [" + e.getKey().getName() + "]");
        }

        System.out.println("[Core] shutdownHook/INFO [com.mark.core]: successfully terminated all programs, including plugins.");
    }

}

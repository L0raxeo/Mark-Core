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
        System.out.println("[INFO]: executing wake process...");
        System.out.println("[INFO]: adding shutdown hook.");
        Runtime.getRuntime().addShutdownHook(new Thread(this::terminateProgram));
        System.out.println("[INFO]: successfully added shutdown hook to runtime, and connected it to terminate program.");

        try
        {
            preInit();
            init();
            postInit();
        }
        catch (IOException e)
        {
            System.out.println("[INFO]: init/WARN - fatal error during initialization sequence.");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("[INFO]: successfully completed initialization sequence");
        System.out.println("[INFO]: waking M.A.R.K. Core...");
        System.out.println("===============================================================================");
        System.out.println("[Mark]: Modular, Assistant, Registry, Kernel - M.A.R.K. is awake");
        InputHandler.listen();
    }

    /**
     * For processes that do not depend on Mark-Core components
     *
     * @throws IOException handled by wake()
     */
    public void preInit() throws IOException
    {
        IOFileRegistry.init();
        PluginRegistry.register();
    }

    /**
     * For processes that depend on processes initialized in the preInit() method
     *
     * @throws IOException handled by wake()
     */
    public void init() throws IOException
    {
        DebugManager.init();
        CustomResponseManager.register();
        PluginRegistry.init();
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
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

    /**
     * Exits and destroys all currently running plugins.
     * Iterates through all plugins in {@link PluginManager}
     */
    private void terminateProgram()
    {
        System.out.println("[INFO]: shutDownHook/WARN - MarkCore abruptly terminated. this could be a problem.");

        System.out.println("[INFO]: attempting to shut down...");

        for (Map.Entry<File, Process> e : PluginManager.allPlugins.entrySet())
        {
            System.out.println("[INFO]: attempting to terminate plugin [" + e.getKey().getName() + "]");
            e.getValue().destroy();
            System.out.println("[INFO]: successfully terminated plugin [" + e.getKey().getName() + "]");
        }

        System.out.println("[INFO]: successfully terminated all programs, including plugins.");
    }

}

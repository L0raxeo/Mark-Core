package com.arkicore.mark.core;

import com.arkicore.mark.core.init.*;
import com.arkicore.mark.core.plugins.Plugin;
import com.arkicore.mark.core.plugins.PluginManager;
import com.arkicore.mark.core.utils.VersionInfo;
import com.arkicore.mark.core.input.InputHandler;
import com.arkicore.mark.core.utils.Registry;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The base of all Mark-Core processes/plugins.
 * This class is the initial starting point of
 * the M.A.R.K.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/29/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class CoreEngine
{

    /**
     * Message for shutdown - in case there is an error.
     */
    public static String shutdownMessage = null;

    /**
     * All registries within program.
     */
    private final ArrayList<Registry> registries = new ArrayList<>();

    /**
     * Wakes Mark-Core and executes all initialization methods
     */
    public void wake()
    {
        System.out.println("[Core] init/INFO [com.mark.core]: executing wake process...");

        System.out.println("[Core] init/INFO [com.mark.core]: adding shutdown hook.");
        Runtime.getRuntime().addShutdownHook(new Thread(this::onTerminate));
        System.out.println("[Core] init/INFO [com.mark.core]: successfully added shutdown hook to runtime, and connected it to terminate program.");

        System.out.println("[Core] init/INFO [com.mark.core]: attempting to register registries...");
        registerRegistries();
        System.out.println("[Core] init/INFO [com.mark.core]: successfully registered all registries!");

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
     * Registers all registries in order to make
     * it easier to implement more registries in
     * the future.
     */
    private void registerRegistries()
    {
        registries.add(new CustomResponseRegistry());
        registries.add(new DebugRegistry());
        registries.add(new IOFileRegistry());
        registries.add(new NucleusRegistry());
        registries.add(new PluginRegistry());
    }

    /**
     * For processes that do not depend on Mark-Core components
     *
     * @throws IOException handled by wake()
     */
    public void preInit() throws IOException
    {
        System.out.println("[Core] init/INFO [com.mark.core]: attempting to execute pre-init sequence");

        for (Registry r : registries)
        {
            r.preInit();
        }

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

        for (Registry r : registries)
        {
            r.init();
        }

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

        for (Registry r : registries)
        {
            r.postInit();
        }

        System.out.println("[Core] init/INFO [com.mark.core]: successfully executed post-init sequence");
    }

    /**
     * Exits and destroys all currently running plugins.
     * Iterates through all plugins in {@link PluginManager}
     */
    private void onTerminate()
    {
        System.out.println("===============================================================================");

        if (shutdownMessage != null)
        {
            System.out.println("[Core] shutDownHook/WARN [com.mark.core]: Mark-Core abruptly terminated. This could be a problem.");
            System.out.println("[Core] shutDownHook/ERROR [com.mark.core]: FATAL ERROR - " + shutdownMessage);
        }

        System.out.println("[Core] shutdownHook/INFO [com.mark.core]: attempting to shut down...");

        for (Plugin plugin : PluginManager.allPlugins)
        {
            System.out.println("[Core] shutdownHook/INFO [com.mark.core]: attempting to terminate plugin [" + plugin.ID + "]");
            plugin.process().destroy();
            System.out.println("[Core] shutdownHook/INFO [com.mark.core]: successfully terminated plugin [" + plugin.ID + "]");
        }

        System.out.println("[Core] shutdownHook/INFO [com.mark.core]: successfully terminated all programs, including plugins.");
    }

}

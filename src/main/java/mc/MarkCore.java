package mc;

import mc.debug.DebugManager;
import mc.init.IOFileRegistry;
import mc.init.PluginRegistry;
import mc.input.CustomResponseManager;
import mc.input.InputHandler;
import mc.nuclei.NucleusManager;
import mc.plugins.PluginManager;
import mc.utils.FileLoader;
import mc.utils.VersionInfo;

import java.io.IOException;

/**
 * The base of all Mark-Core games. This class is the
 * initial starting point of the M.A.R.K.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/21/2021",
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
        Runtime.getRuntime().addShutdownHook(new Thread(this::exitProgram));

        try
        {
            preInit();
            init();
            postInit();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("===============================================================================");
        System.out.println("[Mark]: Modular, Assistant, Registry, Kernel - M.A.R.K. is awake");
        InputHandler.listen();
    }

    /**
     * For processes that do not depend on Mark-Core components
     * @throws IOException handled by wake()
     */
    public void preInit() throws IOException
    {
        IOFileRegistry.init();
        PluginRegistry.register();
    }

    /**
     * For processes that depend on processes initialized in the preInit() method
     * @throws IOException handled by wake()
     */
    public void init() throws IOException
    {
        DebugManager.init();
        CustomResponseManager.register();
        PluginRegistry.init();
    }

    /**
     * For initialization sequences that might depend on processes initialized in init() method, or for registering components from external files to the main program.
     * @throws IOException handled by wake()
     */
    public void postInit() throws IOException
    {
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

    /**
     * Exits and destroys all currently running plugins
     */
    private void exitProgram()
    {
        for (Process p : PluginManager.allPlugins.values())
        {
            p.destroy();
        }
    }

}

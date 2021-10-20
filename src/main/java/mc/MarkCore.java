package mc;

import mc.debug.DebugManager;
import mc.init.DefaultFiles;
import mc.init.PluginRegistry;
import mc.input.CustomResponseManager;
import mc.input.InputHandler;
import mc.nuclei.NucleusManager;
import mc.utils.FileLoader;

import java.io.IOException;

public class MarkCore
{

    /**
     * Wakes Mark-Core and executes all initialization methods
     */
    public void wake()
    {
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

        System.out.println("[Mark]: Modular, Assistant, Registry, Kernel - M.A.R.K. is awake");
        InputHandler.listen();
    }

    /**
     * For processes that do not depend on Mark-Core components
     * @throws IOException handled by wake()
     */
    public void preInit() throws IOException
    {
        DefaultFiles.init();
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
    }

    /**
     * For initialization sequences that might depend on processes initialized in init() method, or for registering components from external files to the main program.
     * @throws IOException handled by wake()
     */
    public void postInit() throws IOException
    {
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

}

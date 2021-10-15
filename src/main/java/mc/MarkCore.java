package mc;

import mc.debug.DebugManager;
import mc.init.DefaultFiles;
import mc.input.CustomResponseManager;
import mc.input.InputHandler;
import mc.nuclei.NucleusManager;
import mc.utils.FileLoader;

import java.io.IOException;

public class MarkCore
{

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

    public void preInit() throws IOException
    {
        DefaultFiles.init();
    }

    public void init() throws IOException
    {
        DebugManager.init();
        CustomResponseManager.init();
    }

    public void postInit() throws IOException
    {
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

}

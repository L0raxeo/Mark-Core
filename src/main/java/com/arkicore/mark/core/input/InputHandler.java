package com.arkicore.mark.core.input;

import com.arkicore.mark.core.input.inputHierarchy.nuclei.NucleusManager;
import com.arkicore.mark.core.plugins.Plugin;
import com.arkicore.mark.core.plugins.PluginManager;
import com.arkicore.mark.core.utils.Utils;
import com.arkicore.mark.core.utils.VersionInfo;
import com.arkicore.mark.core.debug.DebugManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles all input, and decides how
 * the input will be manipulated, or
 * what type of process it triggers.
 *
 * This temporarily includes the input
 * system. However, the input system
 * will be separated from the input
 * handler in future versions.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/21/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class InputHandler
{

    /**
     * Temporary input system through console
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Making a separate method dedicated to listening, instead of integrating it with the input handling method, is to
     * give allow the changing of the input system, without changing the input handling system.
     */
    public static void listen() throws IOException
    {
        handleInput(scanner.nextLine());
    }

    /**
     * Determines how M.A.R.K. will use/handle the input.
     *
     * @param rawInput is the user input.
     */
    private static void handleInput(String rawInput) throws IOException
    {
        // Used to make sure input isn't empty
        char[] splitInput = Utils.getStringAsCharArray(rawInput);

        if (rawInput.contains(DebugManager.debugCommand))
        {
            DebugManager.checkCommands(rawInput);
        }
        else if (CustomResponseManager.isCustomResponse(rawInput) && DebugManager.active)
        {
            System.out.println("[Core]: debug/INFO [com.mark.core.input]: invoked custom response");
        }
        else if (splitInput.length == 0)
        {
            System.out.println("[Mark]: Please stop pressing return/enter");
        }
        else
        {
            boolean isPluginResponse = false;

            for (Plugin plugin : PluginManager.allPlugins)
            {
                plugin.queueMessage(rawInput);
            }

            for (Plugin plugin : PluginManager.allPlugins)
            {
                if (plugin.readMessage() != null || plugin.readMessage().equals(""))
                    isPluginResponse = true;
            }

            if (!isPluginResponse)
                NucleusManager.triggerNucleus(rawInput);
        }

        listen();
    }

}

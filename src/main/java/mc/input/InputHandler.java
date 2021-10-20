package mc.input;

import mc.debug.DebugManager;
import mc.nuclei.NucleusManager;
import mc.utils.Utils;

import java.util.Scanner;

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
    public static void listen()
    {
        handleInput(scanner.nextLine());
    }

    /**
     * Determines how M.A.R.K. will use/handle the input.
     *
     * @param rawInput is the user input.
     */
    private static void handleInput(String rawInput)
    {
        // Used to make sure input isn't empty
        char[] splitInput = Utils.getStringAsCharArray(rawInput);

        if (rawInput.contains(DebugManager.debugCommand))
        {
            DebugManager.checkCommands(rawInput);
        }
        else if (CustomResponseManager.isCustomResponse(rawInput) && DebugManager.active)
        {
            System.out.println("[Debug]: INFO - invoked custom response");
        }
        else if (splitInput.length == 0)
        {
            System.out.println("[Mark]: Please stop pressing return/enter");
        }
        else
        {
            NucleusManager.triggerNucleus(rawInput);
        }

        listen();
    }

}

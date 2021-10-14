package mc.input;

import java.util.Scanner;

public class InputHandler
{

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
     * @param input is the user input.
     */
    private static void handleInput(String input)
    {

    }

}

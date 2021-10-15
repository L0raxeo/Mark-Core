package mc.input;

import mc.debug.DebugManager;
import mc.input.actions.ActionHandler;
import mc.utils.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomResponseManager
{

    private static final HashMap<String, String> customResponses = new HashMap<>();

    public static void init()
    {
        ArrayList<String> customResponsesRaw = null;

        try
        {
            customResponsesRaw = FileLoader.readAllLinesFromFile("customResponses.txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        assert customResponsesRaw != null;
        for (String s : customResponsesRaw)
        {
            String[] customResponseSplit = s.split("=");
            customResponses.put(customResponseSplit[0], customResponseSplit[1]);
        }
    }

    public static boolean isCustomResponse(String stimulus)
    {
        String customResponse = getCustomResponse(stimulus);

        if (customResponse != null)
        {
            // Checks whether the custom response is an action instead of a verbal response
            if (customResponse.contains(":[") && customResponse.contains("]:"))
            {
                ActionHandler.performAction(customResponse, stimulus);
                return true;
            }

            if (DebugManager.active)
                System.out.println("[Debug]: " + "[ " + customResponse + " ] - Custom Response: " + "[" + stimulus + "]");
            else
                System.out.println("[Markus]: " + customResponse);
        }

        // returns true if it is a custom response and false if it isn't
        return customResponse != null;
    }

    /**
     * Returns the response
     * @param stimulus is the string that contains the key phrase that triggers the response
     * @return the specific response of the stimulus
     */
    private static String getCustomResponse(String stimulus)
    {
        for (String keyPhrase : customResponses.keySet())
        {
            // Checks if the raw input (all lowercase) contains the phrase being iterated but only if that phrase
            // doesn't have any constraints
            if (stimulus.toLowerCase().contains(keyPhrase.toLowerCase()))
            {
                return customResponses.get(keyPhrase);
            }

            // Checks if the raw input (all lowercase) contains the phrase being iterated but with constraints in mind
            if (keyPhrase.contains("[") && keyPhrase.contains("]"))
            {
                String constraint = keyPhrase.split(":")[1];

                switch (constraint)
                {
                    case "[1w]":
                        if (stimulus.split(" ")[0].equals(keyPhrase.split(":")[0]))
                            return customResponses.get(keyPhrase);
                        break;
                    case "[2w]":
                        if (stimulus.split(" ")[0].equals(keyPhrase.split(":")[1]))
                            return customResponses.get(keyPhrase);
                }
            }
        }

        return customResponses.get(stimulus);
    }

}

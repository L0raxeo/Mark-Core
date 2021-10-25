package com.mark.core.input;

import com.mark.core.input.actions.ActionHandler;
import com.mark.core.utils.FileLoader;
import com.mark.core.debug.DebugManager;
import com.mark.core.utils.VersionInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles input, and triggers a custom
 * response depending on the stimuli (input).
 *
 * One of the paths that {@link InputHandler}
 * can choose.
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
public class CustomResponseManager
{

    /**
     * Map of all custom responses:
     * - String 1: stimulus - string that triggers the response/action
     * - String 2: result - either an action or a string that is triggered by the stimulus
     */
    private static final HashMap<String, String> customResponses = new HashMap<>();

    /**
     * Registers/initialization all the custom responses loaded in from customResponses.txt
     */
    public static void register()
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

    /**
     * Checks if input is a custom response
     * @param stimulus is the potential stimulus
     * @return whether input/potential stimulus is custom response
     */
    public static boolean isCustomResponse(String stimulus)
    {
        String customResponse = getCustomResponse(stimulus);

        if (customResponse != null)
        {
            // Checks whether the custom response is an action instead of a verbal response
            if (customResponse.contains(":[") && customResponse.contains("]:"))
            {
                ActionHandler.queueAction(customResponse, stimulus);
                return true;
            }

            if (DebugManager.active)
                System.out.println("[Core] debug/INFO [com.mark.core.input]: " + "[ " + customResponse + " ] - Custom Response: " + "[" + stimulus + "]");
            else
                System.out.println("[Mark]: " + customResponse);
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

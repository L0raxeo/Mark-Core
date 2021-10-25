package com.mark.core.nuclei;

import com.mark.core.debug.DebugManager;
import com.mark.core.paths.Path;
import com.mark.core.paths.PathManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.VersionInfo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages all nuclei {@link Nucleus} in M.A.R.K.'s core neural network.
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
public class NucleusManager
{

    /**
     * Main container for all nuclei
     *
     * Separate from connections/hashmap
     * Could be considered the actual "brain"
     */
    public static ArrayList<Nucleus> allNuclei = new ArrayList<>();

    /**
     * The last nucleus is just the last nucleus that was triggered
     *
     * It's used to make connections between the adjacently said nuclei.
     * Ensures multiple connections aren't made for the same two nuclei.
     */
    public static Nucleus lastNucleus;

    /**
     * Method is used to get the nucleus with a specific definition.
     *
     * It iterates through all the nuclei and compares the input string to the iteration's definition
     * If a definition is not found, then it will create a new definition and return the newly defined nucleus.
     *
     * @param def is the input that is converted into paths
     * @return returns the nucleus of the input string
     */
    public static Nucleus getNucleus(String def)
    {
        for (Nucleus n : allNuclei)
        {
            if (n.def.equals(def + " "))
            {
                return n;
            }
        }

        Path[] paths = PathManager.stringToPathArray(def);

        return defineNucleus(paths);
    }

    /**
     * Defines the nucleus of an undefined path sequence.
     * If debug mode is on, then it will state when a nucleus is defined.
     *
     * @param paths the nucleus in the form of paths.
     * @return the newly defined nucleus.
     */
    public static Nucleus defineNucleus(Path[] paths)
    {
        Nucleus newNucleus = new Nucleus(paths);
        allNuclei.add(newNucleus);

        if (DebugManager.active)
            System.out.println("[Core]: debug/INFO [com.mark.core.nuclei]: defining nucleus: " + newNucleus.def);

        return newNucleus;
    }

    /**
     * When a nucleus is used, it is considered what in reality is time.
     * This method allows the organization of connections in order to prioritize those that are used the most.
     *
     * @param origin is the original nucleus that the used nucleus is connected to
     * @param usedBranch the connection in the origin that was used
     */
    public static void decreaseConfidenceOfUnusedNuclei(Nucleus origin, Nucleus usedBranch)
    {
        for (Nucleus n : origin.connections.keySet())
        {
            if (n == usedBranch && origin.connections.get(n) < 100)
            {
                origin.connections.put(n, origin.connections.get(n) + 1);
            }
            else
            {
                if (origin.connections.get(n) > 0)
                {
                    origin.connections.put(n, origin.connections.get(n) - 1);
                }
            }
        }
    }

    /**
     * Triggers the connections of the nucleus of whose definition matches the user's stimulus.
     *
     * @param stimulus represents user stimulus.
     */
    public static void triggerNucleus(String stimulus)
    {
        Nucleus currentNucleus = getNucleus(stimulus);

        // add connection from the last nucleus to the current nucleus
        currentNucleus.trigger();

        if (!currentNucleus.def.equals(lastNucleus.def))
        {
            currentNucleus.connections.put(lastNucleus, -1); // change "5" value (it's a placeholder)
            lastNucleus.connections.put(currentNucleus, -1); // change "5" value (it's a placeholder)
        }

        lastNucleus = currentNucleus;

        try
        {
            FileLoader.writeFile("lastSpoken.txt", lastNucleus.def);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

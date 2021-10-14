package mc.paths;

import mc.debug.DebugManager;
import mc.utils.Utils;

import java.util.ArrayList;

public class PathManager
{

    /**
     * allPaths is the container for all paths within Markus' brain, in the form of an Array List.
     */
    public static ArrayList<Path> allPaths = new ArrayList<>();

    /**
     * Gets the path defined by the string parameter.
     * getPath iterates through all indexes in allPaths, and compares each index's definition to the parameter.
     *
     * If there is no path associated with the parameter, it creates a new path and returns that instead.
     *
     * @param def is the word that needs to be translated to a path.
     * @return the defined path.
     */
    public static Path getPath(String def)
    {
        for (Path p : allPaths)
        {
            if (p.def.equals(def))
            {
                return p;
            }
        }

        return definePath(def);
    }

    /**
     * Defines a path as the parameter.
     *
     * @param def is the definition of the path being defined.
     * @return the newly created path.
     */
    public static Path definePath(String def)
    {
        // Passes Neurons
        Path newPath = new Path(Utils.getStringAsCharArray(def));

        allPaths.add(newPath);

        if (DebugManager.active)
            System.out.println("[Debug]: defining path: " + def);

        return newPath;
    }

    /**
     * Converts the string into a path.
     * I would have put this method inside the Utilities method, however because it can't be used universally (since paths are specific to this project) I put it in the path manager.
     *
     * @param stimulus is the raw version of a nucleus.
     * @return returns the converted stimulus.
     */
    public static Path[] stringToPathArray(String stimulus)
    {
        String[] splitStrings = stimulus.split(" ");
        Path[] convertedPaths = new Path[splitStrings.length];

        for (int i = 0; i < convertedPaths.length; i++)
        {
            Path path = getPath(splitStrings[i]);
            convertedPaths[i] = path;

        }

        return convertedPaths;
    }

}

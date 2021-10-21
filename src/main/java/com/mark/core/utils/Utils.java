package com.mark.core.utils;

import com.mark.core.nuclei.Nucleus;

import java.util.*;

/**
 * Utils is a general utilities class. Methods within the Utils class is used for general algorithmic data manipulation.
 * This includes conversion algorithms, sorting algorithms... etc.
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
public class Utils
{

    /**
     * Converts the string parameter into a character array.
     *
     * @param inputLineAsString is the raw/initial version of the input.
     * @return returns the final/translated version of the input.
     */
    public static char[] getStringAsCharArray(String inputLineAsString)
    {
        char[] inputLineAsCharArray = new char[inputLineAsString.length()];

        // Copy character by character into array
        for (int i = 0; i < inputLineAsString.length(); i++)
        {
            inputLineAsCharArray[i] = inputLineAsString.charAt(i);
        }

        return inputLineAsCharArray;
    }

    /**
     * Sorts the inputted hashmap by the associated integer.
     *
     * @param hm raw/initial/unorganized version of the hashmap
     * @return the organized version of the hashmap.
     */
    public static HashMap<Nucleus, Integer> sortHashMapByValue(HashMap<Nucleus, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Nucleus, Integer> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Map.Entry.comparingByValue());

        // put data from sorted list to hashmap
        HashMap<Nucleus, Integer> temp = new LinkedHashMap<>();

        for (Map.Entry<Nucleus, Integer> aa : list)
        {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

}

package com.mark.core.init;

import com.mark.core.input.CustomResponseManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.Registry;

import java.io.IOException;
import java.util.ArrayList;

public class CustomResponseRegistry implements Registry
{

    /**
     * Registers/initialization all the custom responses loaded in from customResponses.txt
     */
    @Override
    public void init() throws IOException
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
            CustomResponseManager.customResponses.put(customResponseSplit[0], customResponseSplit[1]);
        }
    }

}

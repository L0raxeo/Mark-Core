package com.arkicore.mark.core.init;

import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.VersionInfo;
import com.arkicore.mark.core.input.CustomResponseManager;
import com.arkicore.mark.core.utils.Registry;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The custom response registry
 * is the registry for the
 * {@link CustomResponseManager}.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/25/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
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

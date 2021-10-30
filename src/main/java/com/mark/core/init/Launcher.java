package com.mark.core.init;

import com.mark.core.CoreEngine;
import com.mark.core.utils.VersionInfo;

/**
 * M.A.R.K. origin
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
public class Launcher
{

    public static void main(String[] args)
    {
        CoreEngine core = new CoreEngine();
        core.wake();
    }

}

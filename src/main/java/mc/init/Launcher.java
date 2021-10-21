package mc.init;

import mc.MarkCore;
import mc.utils.VersionInfo;

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
        MarkCore core = new MarkCore();
        core.wake();
    }

}

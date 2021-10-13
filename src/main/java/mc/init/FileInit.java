package mc.init;

import mc.utils.FileLoader;

import java.io.File;
import java.io.IOException;

public class FileInit
{

    public static void init() throws IOException
    {
        if (!new File("lastSpoken.txt").exists())
        {
            FileLoader.writeFile("lastSpoken.txt", "You haven't spoken before.");
        }

        if (!new File("debug_info.txt").exists())
        {
            FileLoader.writeFile("debug_info.txt", "false");
        }

        if (!new File("customResponses.txt").exists())
        {
            FileLoader.writeFile("customResponses.txt", "");
        }
    }

}

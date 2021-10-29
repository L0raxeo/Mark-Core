package com.mark.core.comms;

import com.mark.core.plugins.Plugin;
import com.mark.core.plugins.PluginManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Transceiver sends handles all
 * communications between programs
 * via a text file.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "2.0",
        releaseDate = "10/29/2021",
        since = "2.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Transceiver
{

    /**
     * Queues a message to be sent
     * to the specified program.
     *
     * @param program being sent the message.
     * @param message being queued/sent.
     */
    public static void queueMessage(File program, String message)
    {
        try
        {
            FileLoader.writeFile(program.getPath(), message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Queues a message to be sent
     * to the specified program.
     *
     * @param pathToFile being sent the message.
     * @param message being queued/sent.
     */
    public static void queueMessage(String pathToFile, String message)
    {
        try
        {
            FileLoader.writeFile(pathToFile, message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks inbox/any unread
     * messages.
     */
    public static List<String> checkInbox()
    {
        List<String> newMessages = new ArrayList<>();

        for (Plugin plugin : PluginManager.allPlugins)
        {
            try
            {
                String curInbox = FileLoader.readFile(plugin.getMessenger());

                if (plugin.lastReceivedMessage == null || !plugin.lastReceivedMessage.equals(curInbox))
                {
                    plugin.lastReceivedMessage = FileLoader.readFile(plugin.getMessenger().getPath());
                    newMessages.add(plugin.lastReceivedMessage);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return newMessages;
    }

    /**
     * Gets last message from
     * specified program.
     *
     * @param originID is the origin and
     *               message from program.
     */
    public static String getLastMessage(String originID)
    {
        return Objects.requireNonNull(PluginManager.getPluginByID(originID)).lastReceivedMessage;
    }

}

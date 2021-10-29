package com.mark.core.comms;

import com.mark.core.utils.VersionInfo;

import java.io.File;

/**
 * Transceiver sends handles all
 * communications between programs
 * via a text file.
 *
 * @author Lorcan Andrew Cheng
 */

@VersionInfo(
        version = "2.0",
        releaseDate = "10/28/2021",
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

    }

    /**
     * Checks inbox/any unread
     * messages.
     */
    public static String checkInbox()
    {
        return null;
    }

    /**
     * Gets last message from
     * specified program.
     *
     * @param origin is the origin and
     *               message from program.
     */
    public static String getLastMessage(File origin)
    {
        return null;
    }

}

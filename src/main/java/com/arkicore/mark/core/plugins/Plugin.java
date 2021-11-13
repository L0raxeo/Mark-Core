package com.arkicore.mark.core.plugins;

import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.VersionInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The plugin object holds the information
 * to all the attributes and children of
 * the associated plugin.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "2.0",
        releaseDate = "11/2/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Plugin
{

    /**
     * Default files, including directories.
     *
     * rootDir: root directory - directory/folder
     * that contains all contents of the plugin.
     *
     * jarFile: jar file - the jar file that is
     * executed, when the plugin is initialized.
     *
     * messenger: messenger - the container that
     * holds the last message sent by the plugin.
     *
     * receiver: inbox - the container/mailbox
     * of the associated plugin; contains the
     * last message sent to the associated
     * plugin.
     */
    private final File rootDir, jarFile, messenger, receiver;
    /**
     * Process of program, that is not final
     * so that it can be changed.
     */
    public Process process;

    // INFO //
    public final String NAME;
    public final String ID;
    public final String VERSION;

    // MESSAGES //

    /**
     * Messages that have not been
     * sent.
     */
    public List<String> queuedMessages = new ArrayList<>();

    /**
     * Last message to have been read
     * from this program.
     */
    public String lastReceivedMessage;

    /**
     * Indicates if the plugin has
     * read previous message from
     * core.
     *
     * Prevents queued messages from
     * sending, before the plugin has
     * read previously sent ones.
     *
     * If the inbox of the plugin is
     * empty, that means that the message
     * has either been read, or there
     * are no prior messages sent.
     */
    public boolean hasReadMessage = false;


    /**
     * @param rootDir root directory of plugin
     * @param jarFile jar file (main program) of plugin
     * @param messenger used for the plugin to send
     *                  messages, and for receiving
     *                  programs to read.
     * @param receiver used as the inbox for
     *                 the plugin, that stores
     *                 any receiving messages.
     * @param process of the program, that can be
     *                modified.
     */
    public Plugin(File rootDir, File jarFile, File messenger, File receiver, Process process, String name, String id, String version)
    {
        this.rootDir = rootDir;
        this.jarFile = jarFile;
        this.messenger = messenger;
        this.receiver = receiver;
        this.process = process;

        // INFO //
        this.NAME = name;
        this.ID = id;
        this.VERSION = version;
    }

    // Transceiver

    /**
     * Queues message to associated plugin.
     *
     * @param data or the message being sent.
     */
    public void queueMessage(String data) throws IOException {
        FileLoader.writeFile(this.receiver.getPath(), data);
        //queuedMessages.add(data);
        //queuedMessages.remove(0);
    }

    /**
     * Sends queued messages while the program
     * is running, and while it can find the
     * appropriate inbox file.
     */
    /*
    public void sendMessages() throws IOException
    {
        while (Files.exists(messenger.getAbsoluteFile().toPath()) && process.isAlive())
        {
            if (!hasReadMessage) {
                if (FileLoader.readFile(receiver) == null)
                    hasReadMessage = true;
            }

            try
            {
                FileLoader.writeFile(this.receiver.getPath(), queuedMessages.get(0));
                queuedMessages.remove(0);
                hasReadMessage = false;
            }
            catch (IndexOutOfBoundsException e)
            {
                return;
            }
        }
    }
     */

    /**
     * @return all content within the messenger
     * of the associated plugin.
     */
    public String readMessage()
    {
        try
        {
            lastReceivedMessage = FileLoader.readFile(this.messenger);
            FileLoader.writeFile(this.messenger.getPath(), "");

            return lastReceivedMessage;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return the last message of the associated
     * plugin, read by this program.
     */
    public String getLastReadMessage()
    {
        return lastReceivedMessage;
    }

    // Getters

    public File getRootDir()
    {
        return rootDir;
    }

    public File getJarFile()
    {
        return jarFile;
    }

    public File getMessenger()
    {
        return messenger;
    }

    public File getReceiver()
    {
        return receiver;
    }

    public Process process()
    {
        return process;
    }

}

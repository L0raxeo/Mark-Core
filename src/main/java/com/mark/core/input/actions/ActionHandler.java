package com.mark.core.input.actions;

import com.mark.core.input.actions.terminal.TerminalManager;
import com.mark.core.utils.VersionInfo;

/**
 * Manages {@link Action}.
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
public class ActionHandler
{

    /**
     * Queues (executes) action associated with the parameters
     * @param action as a string determined by customResponse.txt
     * @param info of action as a string determined by customResponse.txt
     */
    public static void queueAction(String action, String info)
    {
        Action queuedAction = new Action(action.split(":")[1], info);

        switch (queuedAction.type)
        {
            case "[search]":
                TerminalManager.queueCommand(queuedAction);
                break;
        }
    }

}

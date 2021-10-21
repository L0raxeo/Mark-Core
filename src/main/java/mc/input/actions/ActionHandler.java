package mc.input.actions;

import mc.input.actions.terminal.TerminalManager;

/**
 * Manages {@link Action}.
 *
 * @author Lorcan Andrew Cheng
 */
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

package mc.input.actions;

import mc.input.actions.terminal.TerminalManager;

public class ActionHandler
{

    public static void performAction(String action, String info)
    {
        Action queuedAction = new Action(action.split(":")[1], info);

        switch (queuedAction.type)
        {
            case "[search]":
                TerminalManager.performCommand(queuedAction);
                break;
        }
    }

}

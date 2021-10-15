package mc.input.actions;

public class Action
{

    /**
     * Types of actions:
     * - [search] = Searches the "info" on web browser
     */
    public final String type;
    public final String info;

    public Action(String type, String info)
    {
        this.type = type;
        this.info = info;
    }

}

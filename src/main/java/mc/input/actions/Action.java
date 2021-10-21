package mc.input.actions;

/**
 * An Action represents a response
 * triggered by the stimuli (input),
 * that doesn't take form in a verbal
 * response.
 *
 * Handled by {@link ActionHandler}
 *
 * @author Lorcan A. Cheng
 */
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

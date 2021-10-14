package mc.nuclei;

import mc.debug.DebugManager;
import mc.paths.Path;
import mc.utils.Utils;

import java.util.HashMap;

public class Nucleus
{

    /**
     * The definition of a nucleus represents what is known as a thought to a human.
     */
    public String def;

    /**
     * The connections of a nucleus is how markus associates some thoughts with other thoughts.
     *
     * Nucleus represents connected nucleus
     * Integer represents confidence rate of corresponding nucleus known as the confidence rate
     */
    public HashMap<Nucleus, Integer> connections = new HashMap<>();

    /**
     * The constructor initializes the nucleus by initializing its definition.
     *
     * @param paths the input that will be translated into a nucleus but in the form of multiple paths.
     */
    public Nucleus(Path[] paths)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Path p : paths)
        {
            stringBuilder.append(p.def).append(" ");
        }

        def = stringBuilder.toString();
    }

    /**
     * Triggers the connections of the nucleus.
     *
     * If debug mode is on it states the confidence rate alongside the definition.
     */
    public void trigger()
    {
        connections = Utils.sortHashMapByValue(connections);

        for (Nucleus n : connections.keySet())
        {
            if (DebugManager.active)
                System.out.println("[Debug]: [ " + n.def + "] - Confidence Rate: [" + connections.get(n) + "%]");
            else
                System.out.println("[Markus]: " + n.def);
        }
    }

}

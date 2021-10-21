package mc.paths;

import mc.utils.VersionInfo;

/**
 * Path represents an individual word.
 * These paths are built on neurons
 * which represent individual characters.
 *
 * Handled by {@link PathManager}.
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "1.0",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Path
{

    /**
     * The definition of the path represents an individual word.
     */
    public String def;

    /**
     * The initializer for a path builds the word from characters passed through as an array.
     *
     * @param neurons is the broken down version of a path in form of a character array. Each character in the array represents a letter in the word.
     */
    public Path(char[] neurons)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (char n : neurons)
        {
            stringBuilder.append(n);
        }

        def = stringBuilder.toString();
    }

}

package mc.utils;

import java.lang.annotation.Documented;

/**
 * Allows easy documentation of additions and
 * revisions to Mark-Core capabilities
 *
 * @author Lorcan Andrew Cheng
 */
@Documented
public @interface VersionInfo
{

    /**
     * Current version when this revision was
     * written.
     *
     * @return Current version
     */
    String version();

    /**
     * Date this revision was written.
     *
     * @return Date of revision
     */
    String releaseDate();

    /**
     * First version this class or function was
     * introduced.
     *
     * @return First introduction of
     * functionality
     */
    String since();

    /**
     * All contributors to this revision.
     *
     * @return All revision contributors
     */
    String[] contributors();

}

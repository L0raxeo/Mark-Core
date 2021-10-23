package com.mark.core.input.actions;

import com.mark.core.utils.VersionInfo;

/**
 * An Action represents a response
 * triggered by the stimuli (input),
 * that doesn't take form in a verbal
 * response.
 * <p>
 * Handled by {@link ActionHandler}
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/21/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public record Action(String type, String info) {}

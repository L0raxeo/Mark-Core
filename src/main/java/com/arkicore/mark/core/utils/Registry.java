package com.arkicore.mark.core.utils;

import java.io.IOException;

/**
 * The registry interface is part
 * of the easy-to-use registry system
 * developed in order to allow easy
 * implementation of new systems,
 * that require a registry (init
 * sequence).
 *
 * @author Lorcan Andrew Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "10/25/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public interface Registry
{

    default void preInit() throws IOException
    {

    }

    default void init() throws IOException
    {

    }

    default void postInit() throws IOException
    {

    }

}

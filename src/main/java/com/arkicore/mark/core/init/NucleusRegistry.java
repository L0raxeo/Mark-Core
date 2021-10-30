package com.arkicore.mark.core.init;

import com.arkicore.mark.core.input.inputHierarchy.nuclei.NucleusManager;
import com.arkicore.mark.core.utils.FileLoader;
import com.arkicore.mark.core.utils.Registry;

import java.io.IOException;

/**
 * The nucleus registry is
 * the registry for the
 * {@link NucleusManager}.
 *
 * @author Lorcan Andrew Cheng
 */
public class NucleusRegistry implements Registry
{

    @Override
    public void postInit() throws IOException
    {
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

}

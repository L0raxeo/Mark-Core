package com.mark.core.init;

import com.mark.core.nuclei.NucleusManager;
import com.mark.core.utils.FileLoader;
import com.mark.core.utils.Registry;

import java.io.IOException;

public class NucleusRegistry implements Registry
{

    @Override
    public void postInit() throws IOException
    {
        NucleusManager.lastNucleus = NucleusManager.getNucleus(FileLoader.readFile("lastSpoken.txt"));
    }

}
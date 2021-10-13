package mc.utils;

import java.io.*;
import java.util.ArrayList;

public class FileLoader
{

    /**
     * Reads the file associate with the path.
     *
     * @param path the path of the file.
     * @return the read string of the file.
     * @throws IOException if there is an "out" exception.
     */
    public static String readFile(String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));
        return br.readLine();
    }

    public static ArrayList<String> readAllLinesFromFile(String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        ArrayList<String> allLines = new ArrayList<>();

        while ((line = br.readLine()) != null)
        {
            allLines.add(line);
        }

        return allLines;
    }

    /**
     * Writes in a file associated with the path parameter.
     *
     * @param path the path of the file being modified.
     * @param data the data that is being written into the specified file.
     * @throws IOException if there is an "in" exception.
     */
    public static void writeFile(String path, String data) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(String.valueOf(data));
        bw.flush();
        bw.close();
    }

}

//NOTE: This is a NEWER version where you need the separate “HelpCode” Class included in your project
//This imports the required library of commands to access the common
//utilities and the input and output commands.
import java.io.*;
import java.util.*;

public class main {

    /**
     * For a block of comments
     */
    public static void main(String[] args) throws IOException {
        //Clears the screen on each new run.
        System.out.flush();

        //Variable used to hold which program to run.
        int iNumberOfSections = 0;
        String filename = "";
        int[][] iGradeBook;

        filename = HelpCode.getString(
            "Please enter the file name (and path) that will be used.",
            false,
            3,
            -1
        );
        while (!checkFile(filename)) {
            filename = HelpCode.getString(
                "\nThis file: '" +
                filename +
                "' does not exists -- please enter a filename (and path) that exists to be read from.",
                false,
                3,
                -1
            );
        }
    }

    /**
     * This is used to check if a file already exists.
     */
    public static boolean checkFile(String strFileToCheck) throws IOException {
        File fleTemp = new File(strFileToCheck);
        return fleTemp.exists();
    }
}

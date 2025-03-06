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

        String filename = "";

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

        int totalSections = 0;
        int totalMarks = 0;
        int totalSum = 0;
        double sumOfAverages = 0;
        int overallHighest = -1;
        int overallLowest = 101;
        int overallA = 0;
        int overallB = 0;
        int overallC = 0;
        int overallD = 0;
        int overallF = 0;

        File inputFile = new File(filename);
        Scanner input = new Scanner(inputFile);

        String outputFilePath =
            inputFile.getParent() + "/out-" + inputFile.getName();
        File outputFile = new File(outputFilePath);
        FileOutputStream fileOutS = new FileOutputStream(outputFile, false);
        BufferedWriter bWrite = new BufferedWriter(
            new OutputStreamWriter(fileOutS)
        );

        while (input.hasNextInt()) {
            totalSections++;
            int numGrades = input.nextInt();
            System.out.println(
                "In section: " +
                totalSections +
                " there will be " +
                numGrades +
                " piece(s) of data."
            );
            int sectionHighest = -1;
            int sectionLowest = 101;
            int sectionSum = 0;
            int sectionA = 0;
            int sectionB = 0;
            int sectionC = 0;
            int sectionD = 0;
            int sectionF = 0;
            for (int i = 0; i < numGrades; i++) {
                if (!input.hasNextInt()) break;
                int grade = input.nextInt();

                sectionSum += grade;
                sectionHighest = Math.max(sectionHighest, grade);
                sectionLowest = Math.min(sectionLowest, grade);

                // Categorize grade
                if (grade >= 80) sectionA++;
                else if (grade >= 70) sectionB++;
                else if (grade >= 60) sectionC++;
                else if (grade >= 50) sectionD++;
                else sectionF++;
            }
            double sectionAverage = (double) sectionSum / numGrades;
            sumOfAverages += sectionAverage;

            System.out.printf("Highest : %d%n", sectionHighest);
            System.out.printf("Lowest : %d%n", sectionLowest);
            System.out.printf("Average : %.1f%n", sectionAverage);
            System.out.printf("There were %d A's%n", sectionA);
            System.out.printf("There were %d B's%n", sectionB);
            System.out.printf("There were %d C's%n", sectionC);
            System.out.printf("There were %d D's%n", sectionD);
            System.out.printf("There were %d F's%n", sectionF);
            System.out.println();

            // Update overall statistics
            totalMarks += numGrades;
            totalSum += sectionSum;
            overallHighest = Math.max(overallHighest, sectionHighest);
            overallLowest = Math.min(overallLowest, sectionLowest);
            overallA += sectionA;
            overallB += sectionB;
            overallC += sectionC;
            overallD += sectionD;
            overallF += sectionF;
        }
        // Compute overall statistics
        double overallAverage = (double) totalSum / totalMarks;
        double avgOfAverages = sumOfAverages / totalSections;

        // Print overall results
        System.out.println("Overall Statistics:");
        System.out.println("-------------------");
        System.out.printf("There were %d sections.%n", totalSections);
        System.out.printf("The overall highest mark was: %d%n", overallHighest);
        System.out.printf("The overall lowest mark was: %d%n", overallLowest);
        System.out.printf("There were a total of %d marks.%n", totalMarks);
        System.out.printf("With an overall average of %.1f%n", overallAverage);
        System.out.printf(
            "If you averaged all of the averages, %.1f was the result.%n",
            avgOfAverages
        );
        System.out.printf("There were %d A's%n", overallA);
        System.out.printf("There were %d B's%n", overallB);
        System.out.printf("There were %d C's%n", overallC);
        System.out.printf("There were %d D's%n", overallD);
        System.out.printf("There were %d F's%n", overallF);

        // Write results to output file
        String temp;
        temp = String.format(
            "Overall Statistics for: '%s'%n",
            inputFile.getName()
        );
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Total number of sections: %d%n", totalSections);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Highest mark: %d%n", overallHighest);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Lowest mark: %d%n", overallLowest);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Total number of marks: %d%n", totalMarks);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Overall average: %.1f%n", overallAverage);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format(
            "Average of section averages: %.1f%n",
            avgOfAverages
        );
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("Grade Statistics:");
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("There were %d A's%n", overallA);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("There were %d B's%n", overallB);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("There were %d C's%n", overallC);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("There were %d D's%n", overallD);
        bWrite.write("" + temp);
        bWrite.newLine();
        temp = String.format("There were %d F's%n", overallF);
        bWrite.write("" + temp);
        bWrite.newLine();

        System.out.println("\nResults written to: " + outputFilePath);
        input.close();
        bWrite.close();
    }

    /**
     * This is used to check if a file already exists.
     */
    public static boolean checkFile(String strFileToCheck) throws IOException {
        File fleTemp = new File(strFileToCheck);
        return fleTemp.exists();
    }
}

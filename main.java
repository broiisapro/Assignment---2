//The things that need to be imported if you want to run the code, without those it does not work
import java.io.*;
import java.util.*;

public class main {

    public static void main(String[] args) throws IOException {
        //Clears the screen on each new run.
        System.out.flush();

        //New variable for the filename and path that the user will enter
        String filename = "";
        //Getting user input for the file name, referencing
        filename = HelpCode.getString(
            "Please enter the file name (and path) that will be used.",
            false,
            5,
            -1
        );

        //variable to check if we can continue
        boolean continu = false;

        //Checking to see if the user wants to quit or continue
        while (!continu) {
            //seeing if the file exists
            if (checkFile(filename)) {
                continu = true;
            }
            //checking to see if user wants to quit
            else if (filename.equals("cheeseburger")) {
                continu = true;
            }
            //getting an answer forcibly
            else if (!checkFile(filename)) {
                filename = HelpCode.getString(
                    "\nThis file: '" +
                    filename +
                    "' does not exists -- please enter a filename (and path) that exists to be read from. Or  say cheeseburger to quit",
                    false,
                    3,
                    -1
                );
            }
        }

        while (!filename.equals("cheeseburger")) {
            //overall stats
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

            int temp1 = filename.lastIndexOf('/');

            //finding section information
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
                //local variables for the stats
                int sectionHighest = -1;
                int sectionLowest = 101;
                int sectionSum = 0;
                int sectionA = 0;
                int sectionB = 0;
                int sectionC = 0;
                int sectionD = 0;
                int sectionF = 0;
                for (int i = 0; i < numGrades; i++) {
                    if (input.hasNextInt()) {
                        //getting the next grade
                        int grade = input.nextInt();

                        //adding the section sum and finding min and max values
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
                }

                //Calculate local stats
                double sectionAverage = (double) sectionSum / numGrades;
                sumOfAverages += sectionAverage;

                //output local stats
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

            // Calculate overall statistics that are neccecary
            double overallAverage = (double) totalSum / totalMarks;
            double avgOfAverages = sumOfAverages / totalSections;

            // Print overall results from all sections
            System.out.println("Overall Statistics:");
            System.out.println("-------------------");
            System.out.printf("There were %d sections.%n", totalSections);
            System.out.printf(
                "The overall highest mark was: %d%n",
                overallHighest
            );
            System.out.printf(
                "The overall lowest mark was: %d%n",
                overallLowest
            );
            System.out.printf("There were a total of %d marks.%n", totalMarks);
            System.out.printf(
                "With an overall average of %.1f%n",
                overallAverage
            );
            System.out.printf(
                "If you averaged all of the averages, %.1f was the result.%n",
                avgOfAverages
            );
            System.out.printf("There were %d A's%n", overallA);
            System.out.printf("There were %d B's%n", overallB);
            System.out.printf("There were %d C's%n", overallC);
            System.out.printf("There were %d D's%n", overallD);
            System.out.printf("There were %d F's%n", overallF);

            //creating the path for the output file
            String outputFilePath =
                filename.substring(0, (temp1 + 1)) +
                "out-" +
                filename.substring((temp1 + 1));

            //setting up the output file
            File outputFile = new File(outputFilePath);
            FileOutputStream fileOutS = new FileOutputStream(outputFile, false);
            BufferedWriter bWrite = new BufferedWriter(
                new OutputStreamWriter(fileOutS)
            );

            // Write all of the results to output file
            String temp;
            temp = String.format(
                "Overall Statistics for: '%s'%n",
                inputFile.getName()
            );
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("Total number of sections: %d", totalSections);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("Highest mark: %d", overallHighest);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("Lowest mark: %d", overallLowest);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("Total number of marks: %d", totalMarks);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("Overall average: %.1f", overallAverage);
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
            temp = String.format("There were %d A's", overallA);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("There were %d B's", overallB);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("There were %d C's", overallC);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("There were %d D's", overallD);
            bWrite.write("" + temp);
            bWrite.newLine();
            temp = String.format("There were %d F's", overallF);
            bWrite.write("" + temp);
            bWrite.newLine();

            //User message to show where the results are written to
            System.out.println("\nResults written to: " + outputFilePath);
            input.close();
            bWrite.close();

            //resetting loop variables
            continu = false;
            filename = "";

            //getting a file path and name for any rerun of the code
            filename = HelpCode.getString(
                "Please enter the file name (and path) that will be used. or enter cheeseburger to quit",
                false,
                5,
                -1
            );

            //Checking to see if the user wants to quit or continue
            while (!continu) {
                //Checking to see if the file exists
                if (checkFile(filename)) {
                    continu = true;
                    //Checking to see if the user wants to quit
                } else if (filename.equals("cheeseburger")) {
                    continu = true;
                    // Checking to see if the file exists or getting a new one
                } else if (!checkFile(filename)) {
                    filename = HelpCode.getString(
                        "\nThis file: '" +
                        filename +
                        "' does not exists -- please enter a filename (and path) that exists to be read from. Or  say cheeseburger to quit",
                        false,
                        3,
                        -1
                    );
                }
            }
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

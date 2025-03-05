import java.io.*;
import java.util.*;

/****************************************************************************************************************
 * BELOW THIS LINE IS MR. HUDSON'S 'HELPING' CODE
 * Each routine below is commented -- so read for full understanding, here is a short description of each
 * routine included below:
 * pauseToContinue - used to pause the program and then the user presses enter to continue.
 * getString - used to get String input from the user.
 * getInt - used to get a valid integer input from the user.
 * getDbl - used to get a valid double (decimal values) input from the user.
 * checkNum - used to verify a string only has numeric values in it.
 * checkIntNum - used to check if a string value is a valid number within an integer range.
 * checkDblNum - used to check if a string value is a valid number within a double range.
 ****************************************************************************************************************/
/**
 * This is a 'pause' routine to allow the user to just hit "enter" to continue with your
 * program can be used in multiple ways/locations. You can send in a message to the user
 * and whether you want to clear the screen 'flush' before continue.
 */
public class HelpCode {

    public static void pauseToContinue(
        String message,
        boolean clearBeforeContinue
    ) {
        //Scanners are used in this case for user input from the keyboard.
        Scanner keyInput = new Scanner(System.in);
        //This is just to allow the user to review anything on screen
        //  before clearing the screen and returning them to the main menu.
        getString(message, true, -1, -1);
        if (clearBeforeContinue) {
            System.out.print("\f");
        }
    }

    /**
     * This is used get a valid string input from the user, used whenever I need user input.
     * You send in the sMessage containing what you are asking the user for, next is a boolean value as to whether
     * an empty string is okay input or not (emptyOK -- true means an empty string is fine, false means need something).
     * You can also send in a minimum or maximum length needed for the string -- if -1 is sent for either then
     * the length of the string is not checked. In the end the return value is a string based on the criteria sent.
     */
    public static String getString(
        String sMessage,
        boolean emptyOK,
        int intMinChar,
        int intMaxChar
    ) {
        //Set up the scanner for user input via the keyboard.
        Scanner keyInput = new Scanner(System.in);

        //Temp string to hold user's input until it is valid
        String strTemp = "";
        //Boolean variable to know whether we can end the user input loop.
        boolean blnLeaveLoop;
        do {
            //Getting the user's input to be stored in the strTemp variable.
            System.out.println(sMessage);
            strTemp = keyInput.nextLine();
            //Make the assumption that the input is good -- switch to false if there is an issue.
            blnLeaveLoop = true;
            //Checking if empty string (and whether we need to check this).
            if (strTemp.length() == 0 && !emptyOK) {
                System.out.println("You need to enter something!");
                blnLeaveLoop = false;
            }
            //Checking if they care about how short the string is (minimum number of characters).
            if (intMinChar != -1 && strTemp.length() < intMinChar) {
                System.out.println(
                    "Your input needs to have at least " +
                    intMinChar +
                    " characters."
                );
                blnLeaveLoop = false;
            }
            //Checking if they care about how long the string is (maximum number of characters).
            if (intMaxChar != -1 && strTemp.length() > intMaxChar) {
                System.out.println(
                    "Your input needs to have less than or equal to " +
                    intMaxChar +
                    " characters."
                );
                blnLeaveLoop = false;
            }
        } while (!blnLeaveLoop);
        //Out of the input loop now -- so return the input -- it meets the requirements.
        return strTemp;
    }

    /**
     * This is used to get input from the user in the form of an integer. A message is sent to this routine
     * asking the user for integer input. A lowNum value should be sent as the lowest number allowed as well as
     * a highNum value is sent to represent the largest value allowed as input. In the end a valid integer is
     * returned based on the parameters sent in and outlined.
     */
    public static int getInt(String sMessage, int lowNum, int highNum) {
        //Temp string to hold user's input until it is valid.
        String strTemp = "";
        //Boolean to keep user in loop until input is valid
        boolean blnValidInput = true;
        //Loop to ensure the user enters an integer and in the correct range.
        do {
            //Getting input -- by calling my getString routine, I send in the message to ask the user as well as false
            //  for not allowing an empty string. The next two parameters use the length of the range values sent
            //  to determine the length of the string input.
            strTemp = getString(
                sMessage,
                false,
                1,
                Math.max(
                    Integer.toString(lowNum).length(),
                    Integer.toString(highNum).length()
                )
            );
            //Using the checkInt routine to verify a number.
            if (checkNum(strTemp, 0)) {
                //Checking the number is in the right range.
                if (checkIntNum(strTemp, lowNum, highNum)) {
                    blnValidInput = true;
                } else {
                    //It's not so let the user know.
                    System.out.println(
                        "Please enter a value between " +
                        lowNum +
                        " and " +
                        highNum +
                        "."
                    );
                    blnValidInput = false;
                }
            } else {
                //If it got here there's other issues -- like not being a valid integer.
                System.out.println(
                    "Your input: " +
                    strTemp +
                    " is not a valid integer, please read carefully and try again."
                );
                blnValidInput = false;
            }
        } while (!blnValidInput);
        //Done the input loop -- send back the valid integer input.
        return Integer.parseInt(strTemp);
    }

    /**
     * This is used to get input from the user in the form of a double. A message is sent to this routine
     * asking the user for decimal numeric input. A lowNum value should be sent as the lowest number
     * allowed as well as a highNum value is sent to represent the largest value allowed as input. In the
     * end a valid double value is returned based on the parameters sent in and outlined.
     */
    public static double getDbl(
        String sMessage,
        double lowNum,
        double highNum
    ) {
        //Temp string to hold user's input until it is valid.
        String strTemp = "";
        //Boolean to keep user in loop until input is valid
        boolean blnValidInput = true;
        //Loop to ensure the user enters an integer and in the correct range.
        do {
            //Getting input -- by calling my getString routine, I send in the message to ask the user as well as false
            //  for not allowing an empty string. The next two parameters use the length of the range values sent
            //  to determine the length of the string input.
            strTemp = getString(
                sMessage,
                false,
                1,
                3 *
                Math.max(
                    Double.toString(lowNum).length(),
                    Double.toString(highNum).length()
                )
            );
            //Using the checkInt routine to verify a number.
            if (checkNum(strTemp, 1)) {
                //Checking the number is in the right range.
                if (checkDblNum(strTemp, lowNum, highNum)) {
                    blnValidInput = true;
                } else {
                    //It's not so let the user know.
                    System.out.println(
                        "Please enter a value between " +
                        lowNum +
                        " and " +
                        highNum +
                        "."
                    );
                    blnValidInput = false;
                }
            } else {
                //If it got here there's other issues -- like not being a valid integer.
                System.out.println(
                    "Your input: " +
                    strTemp +
                    " is not a valid integer, please read carefully and try again."
                );
                blnValidInput = false;
            }
        } while (!blnValidInput);
        //Done the input loop -- send back the valid integer input.
        return Double.parseDouble(strTemp);
    }

    /**
     * This is used to error check a string value (sNum sent to the function) to verify that it is an integer
     * style number (no decimals). It will receive a string value to check and will return true or false
     * based on its validity in terms of being an integer.
     */
    public static boolean checkNum(String sNum, int numDecimals) {
        //Initialize the valid input to false -- assume bad data first and then change if all is good.
        boolean validInput = false;
        //Counters and variables to check for integer validity.
        //  posNegCount keeps track of how many + or - characters are in the string.
        //  posNegPos keeps track of the last position of a + or - found -- should only be 0.
        //  decimalCount keeps track of the number of '.' entered -- should stay at 0 for integers
        //  nonNumCount keeps track of how many non numeric characters there were -- should be 0 for numbers.
        int posNegCount = 0, posNegPos = -1, decimalCount = 0, nonNumCount = 0;

        //Loop to look at all the characters in the string input and update the counter variables as appropriate.
        for (int i = 0; i < sNum.length(); i++) {
            //Based on what the current character is do....
            switch (sNum.charAt(i)) {
                //Current character is a decimal -- so add one to that counter.
                case '.':
                    decimalCount++;
                    break;
                //Current character is a + or - sign -- so add one to that counter and update the position
                //  where it was found.
                case '-':
                case '+':
                    posNegCount++;
                    posNegPos = i;
                    break;
                //Current character is a numeric value so do nothing it's okay nothing to do here.
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                //Anything else caught here is a non-numeric character (at least for this program's purposes).
                default:
                    nonNumCount++;
            }
        }
        //Now check the results to see if it was valid -- need 1 or 0 +/- sign characters and they can only
        //  occur in position 0 of the input.
        if (posNegCount <= 1 && posNegPos <= 0) {
            //We want to ensure there were no non-numeric characters
            if (nonNumCount <= 0) {
                //We want there to be 0 decimal points for integers, maximum 1 for doubles.
                if (decimalCount <= numDecimals) {
                    //Can't allow just 1 + (or -) sign or 1 decimal point, causes program to crash.
                    if (
                        !(posNegCount == 1 && sNum.length() == 1) &&
                        !(decimalCount == 1 && sNum.length() == 1)
                    ) {
                        //If we make it here, all is good it's an integer style number.
                        validInput = true;
                    }
                }
            }
        }
        //Send back the validity of the string in terms of it being an integer or not.
        return validInput;
    }

    /**
     * This is used to verify that information sent to this function is a valid integer within a specified
     * range.  Sent to this routine are:  the string version of the number to check (sNum), the lowest value
     * allowed for the number (lowNum), the high value allowed for the number (highNum). Returns true or false
     * as to the validity of the 'sNum' sent to the function.
     */
    public static boolean checkIntNum(String sNum, int lowNum, int highNum) {
        //Temp long to hold the string's value for size checking.
        long lngTemp = 0;
        //Boolean to hold whether the input is valid.
        boolean blnValidInput = true;
        //Using the checkInt routine to verify a number.
        if (checkNum(sNum, 0)) {
            //Storing the number -- as a long temporarily to allow for overflow on the integer datatype.
            //  Hopefully the programmer using this code set appropriate limit values and by the end
            //  can successfully return an integer value.
            lngTemp = Long.parseLong(sNum);
            //Checking the number is in the right range.
            if (lngTemp < lowNum || lngTemp > highNum) {
                blnValidInput = false;
            }
            //All good -- return true
            else {
                blnValidInput = true;
            }
        }
        //Was not a valid number (integer)
        else {
            blnValidInput = false;
        }
        //return the status of the input.
        return blnValidInput;
    }

    /**
     * This is used to verify that information sent to this function is a valid double within a specified
     * range.  Sent to this routine are:  the string version of the number to check (sNum), the lowest value
     * allowed for the number (lowNum), the high value allowed for the number (highNum). Returns true or false
     * as to the validity of the 'sNum' sent to the function.
     */
    public static boolean checkDblNum(
        String sNum,
        double lowNum,
        double highNum
    ) {
        //Temp long to hold the string's value for size checking.
        double dblTemp = 0;
        //Boolean to hold whether the input is valid.
        boolean blnValidInput = true;
        //Using the checkInt routine to verify a number.
        if (checkNum(sNum, 1)) {
            //Storing the number -- as a long temporarily to allow for overflow on the integer datatype.
            //  Hopefully the programmer using this code set appropriate limit values and by the end
            //  can successfully return an integer value.
            dblTemp = Double.parseDouble(sNum);
            //Checking the number is in the right range.
            if (dblTemp < lowNum || dblTemp > highNum) {
                blnValidInput = false;
            }
            //All good -- return true
            else {
                blnValidInput = true;
            }
        }
        //Was not a valid number (integer)
        else {
            blnValidInput = false;
        }
        //return the status of the input.
        return blnValidInput;
    }
}

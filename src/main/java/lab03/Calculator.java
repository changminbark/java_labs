/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2023
* Instructor: Professor Stough
* Section: 02 10AM
* Lab Section: 10AM
*
* Name: Chang Min Bark
* Date: 09/x04/2023
*
* Lab / Assignment: lab03
*
* Description: Creating a calculator in the console.
*
* *****************************************/
package lab03;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {
    /** Common Scanner object used throughout the program */
    public static Scanner scnr;

    /** The first operand in our expression */
    private static double num1;

    /** The operator in our expression */
    private static String strOp;

    /** The second operand in our expression */
    private static double num2;

    /**
     * Main calculator program
     * 
     * @param args command line args, not used for this program
     */
    public static void main(String[] args) {
        // Setting up scanner
        scnr = new Scanner(System.in);

        // Setting up decimal format
        DecimalFormat numFormat = new DecimalFormat("###,###.##");

        // Greet user with instructions
        displayGreeting();

        // Helper variable to control when the user wants to exit the program
        boolean isDone = false;

        do {

            readExpressionFromUser();

            // Be sure to consume the rest of the input (Don't need this as scnr is already empty)
            // scnr.nextLine();

            // Perform the requested operation and print the result
            switch(strOp) {
                case "+":
                    System.out.println("The sum is " + numFormat.format(num1 + num2));
                    break;
                case "-":
                    System.out.println("The difference is " + numFormat.format(num1 - num2));
                    break;
                case "*":
                    System.out.println("The product is " + numFormat.format(num1 * num2));
                    break;
                case "/":
                    System.out.println("The quotient is " + numFormat.format(num1 / num2));
                    break;
                case "%":
                    System.out.println("The remainder is " + numFormat.format(num1 % num2));
                    break;
                case "^":
                    System.out.println("The power is " + numFormat.format(Math.pow(num1, num2)));
                    break;
                default:
                    System.out.println("ERROR: " + strOp + " is not a valid operator.");
                    break;
            } 

            // Check to see if the user wants to try again
            System.out.println("Try again? [Y | N]");
            if (scnr.nextLine().strip().equalsIgnoreCase("n")){
                isDone = true;
            }
        
        } while (!isDone);
    }

    /** 
     * Read a simple mathematical expression from the user. The user should enter a valid expression
     * in the form of:
     * 
     * num1 operator num2
     */
    private static void readExpressionFromUser() {

        // Assume the user does not have a good expression yet
        boolean isGoodExpression = false;

        while (!isGoodExpression) {
            System.out.println("Enter a simple arithmetic with spacing: ");

            // Read the entire input and set up another Scanner to parse it
            String inputFromUser = scnr.nextLine();
            Scanner strScnr = new Scanner(inputFromUser);
            

            // If we don't have a number, output an error and jump back to the top
            if (!strScnr.hasNextDouble()) {
                System.out.println("ERROR: Cannot parse operand 1!");
                continue;
            }
            num1 = strScnr.nextDouble();
            
            // Check to see if operator is valid
            strOp = strScnr.next();
            if (!(strOp.equals("+") || strOp.equals("-") || strOp.equals("*") || strOp.equals("/") || strOp.equals("%") || strOp.equals("^"))){
                System.out.println("ERROR: " + strOp + " is not a valid operator");
                continue;
            }

            // Check to see if operand 2 is valid
            if (!strScnr.hasNextDouble()) {
                System.out.println("ERROR: Cannot parse operand 2!");
                continue;
            }
            num2 = strScnr.nextDouble();

            // If we made it here, we're good
            isGoodExpression = true;
        }
        
    }


    /**
     * Display a greeting to the user
     */
    private static void displayGreeting() {
        System.out.println("Welcome to the Calculator");
        System.out.println("Enter expressions with two numeric operands");
        System.out.println("and a single operator from +, -, *, /, %, or ^");
    }
}

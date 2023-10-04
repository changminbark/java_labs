/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/3/2023
 * Time: 7:36 PM
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: HTMLMain
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lab09.ReportSortType.SORT_BY_TAG_FREQUENCY;
import static lab09.ReportSortType.SORT_BY_TAG_NAME;

/**
 * A basic program/class that counts the number of certain characters in a website
 */
public class HTMLMain {


    private static HTMLScanner htmlScanner;

    private static Scanner scnr;

    private static boolean isDone;

    /**
     * The main method that runs the whole HTML parsing program
     * @param args - Default main parameters
     */
    public static void main(String[] args) throws IOException {
        // Gets a valid URL from user and puts it in an input stream for htmlScanner
        // then it parses HTML tags and analyzes it
        parseURL();
    }

    /**
     * A method that analyzes the HTML tags in a webpage, which gets sorted and printed
     * @throws IOException - An exception thrown in sortAndPrint();
     */
    public static void analyzeHTML() throws IOException {
        htmlScanner.scanForAllTags();
        System.out.println("Number of total tags: " + htmlScanner.getTotalTagCount());
        System.out.println("Number of unique tags: " + htmlScanner.getTotalUniqueTagCount());
        sortAndPrint();
    }

    /**
     * A method that sorts and prints the TreeMap into an arraylist and prints them
     * @throws IOException A general exception thrown for file not existing
     */
    private static void sortAndPrint() throws IOException {

        // Deciding to sort by reverse freq or tag name
        boolean sortDone = false;
        boolean sortFreq = false;
        while (!sortDone) {
            System.out.println("Sort by reverse frequency or by tag name: [f|t]: ");
            String sortInput = scnr.nextLine();
            if (sortInput.equalsIgnoreCase("f")) {
                sortDone = true;
                sortFreq = true;
            } else if (sortInput.equalsIgnoreCase("t")) {
                sortDone = true;
            } else {
                System.out.println("Not a valid input. Try again.");
            }
        }

        // Deciding to send report to file or screen
        boolean outDone = false;
        boolean fileOut = false;
        while (!outDone) {
            System.out.println("Send report to a file or screen? [f|s]? ");
            String sortInput = scnr.nextLine();
            if (sortInput.equalsIgnoreCase("f")) {
                outDone = true;
                fileOut = true;
            } else if (sortInput.equalsIgnoreCase("s")) {
                outDone = true;
            } else {
                System.out.println("Not a valid input. Try again.");
            }
        }


        // Print report of tag name and to system out
        if (sortFreq == false && fileOut == false) {
            htmlScanner.printReport(System.out, SORT_BY_TAG_NAME);
        }
        // Print report of freq and to system out
        else if (sortFreq == true && fileOut == false) {
            htmlScanner.printReport(System.out, SORT_BY_TAG_FREQUENCY);
        }
        // Print report of tag name and to file
        else if (sortFreq == false && fileOut == true) {
            // Creates a file if it does not exist
            String path = "src/main/java/lab09/htmltagreport.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Prints to file
            PrintStream writeToFile = new PrintStream(new FileOutputStream("src/main/java/lab09/htmltagreport.txt"));
            htmlScanner.printReport(writeToFile, SORT_BY_TAG_NAME);
            writeToFile.close();
            System.out.println("Report sent to htmltagreport.txt");

        }
        // Print report of freq and to file
        else if (sortFreq == true && fileOut == true) {
            // Creates a file if it does not exist
            String path = "src/main/java/lab09/htmltagreport.txt";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Prints to file
            PrintStream writeToFile = new PrintStream(new FileOutputStream("src/main/java/lab09/htmltagreport.txt"));
            htmlScanner.printReport(writeToFile, SORT_BY_TAG_FREQUENCY);
            writeToFile.close();
            System.out.println("Report sent to htmltagreport.txt");

        }
    }

    /**
     * A method that validates the URL inputted by the user
     * @param inputFromUser The input URL string
     * @return a boolean value stating whether the URL is valid or not
     */
    public static boolean validateURL(String inputFromUser) {
        // Tests for URL syntax
        // REGEX matching pattern
        Pattern p = Pattern.compile("^http[s]?://[^\\.\\s]+(\\.[^\\.\\s]+)+$");
        Matcher matcher = p.matcher(inputFromUser);
        // If pattern matches
        if (matcher.matches()) {
            // If URL is valid
            // Tests whether URL is malformed or not
            URL locator = null;
            // THIS CODE WILL PROBABLY NEVER BE THROWN
            try {
                locator = new URL(inputFromUser);
            } catch (MalformedURLException e) {
                System.out.println("Not a valid URL. Try again");;
                return false;
            }

            // Tests whether URL could identify host and whether resource is on host
            try {
                // Sets up htmlScanner with the page's openStream
                htmlScanner = new HTMLScanner(locator.openStream());
                // Exception where host is not found
            } catch (UnknownHostException e) {
                System.out.println("Could not identify host!");
                return false;
                // Exception where resource is not in valid host
            } catch (FileNotFoundException e) {
                System.out.println("Could not find resource on host!");
                return false;
                // Other general exceptions
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        // If pattern does not match
        } else {
            System.out.println("Not a valid URL. Try again");
            return false;
        }
        return true;
    }

    /**
     * Gets and validates a URL from the user using Regex
     */
    public static void parseURL() throws IOException {
        scnr = new Scanner(System.in);
        isDone = false;

        while (!isDone) {
            System.out.println("Enter the URL to scan, or Q to quit: ");
            String inputFromUser = scnr.nextLine();

            // If user wants to quit
            if (inputFromUser.equalsIgnoreCase("q")){
                System.out.println("Goodbye!");
                isDone = true;
                System.exit(0);
            }

            if (validateURL(inputFromUser)){
                System.out.println("Connection established.");

                // Analyze and print results
                analyzeHTML();
            }

        }
    }
}
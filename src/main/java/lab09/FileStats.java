/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/3/2023
 * Time: 5:10 PM
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: FileStats
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * A basic method that counts the number of certain characters in a CSV file
 */
public class FileStats {
    // Initializing count variables of alphabetic, digits, and whitespace characters
    private static int alphaCh;
    private static int digitCh;
    private static int whiteCh;


    /**
     * The main method that is run
     * @param args - Default params for main
     */
    public static void main(String[] args) {
        // Initializing TreeMap to store characters that do not belong in specified categories
        Map<Character, Integer> chMap = new TreeMap<>();
        readData(chMap);
        printValues(chMap);
    }

    /**
     * Reads the data of the CSV file and parses through each character
     * @param chMap - A TreeMap that stores the counts of characters that are not alpha, digit, or whitespace
     */
    private static void readData(Map<Character, Integer> chMap) {
        // Use a try-with-resources block to get an InputStream
        try (InputStream inStream = FileStats.class.getResourceAsStream("/auto-mpg.csv")) {
            // Pass the InputStream to get a Scanner for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));


            // Scanning each character in the csv file
            Character ch = null;
            int i = 0;
            while((i = reader.read()) != -1) {
                ch = (char)i;
                // If char is alphabetic
                if (Character.isAlphabetic(ch)) {
                    alphaCh += 1;
                }
                // If char is digit
                else if (Character.isDigit(ch)) {
                    digitCh += 1;
                }
                // If char is whitespace
                else if (Character.isWhitespace(ch)) {
                    whiteCh += 1;
                }
                else {
                    // If character is in TreeMap
                    if (chMap.containsKey(ch)) {
                        chMap.put(ch, chMap.get(ch) + 1);
                    }
                    // If character is not in TreeMap
                    else {
                        chMap.put(ch, 1);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the counts/statistics of the characters
     * @param chMap - A TreeMap that stores the counts of characters that are not alpha, digit, or whitespace
     */
    private static void printValues(Map<Character, Integer> chMap) {
        System.out.println("Results on /auto-mpg.csv");
        System.out.println("Alpha: " + alphaCh);
        System.out.println("Digit: " + digitCh);
        System.out.println("Whitespace: " + whiteCh);
        for (Map.Entry<Character, Integer> entry : chMap.entrySet()) {
            System.out.println("Char: " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
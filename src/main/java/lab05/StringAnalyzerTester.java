/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: Chang Min Bark
 * Date: 9/11/23
 * Time: 2:45 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: StringAnalyzerTester
 * Description:
 * This is a simple program designed to do nothing more than test out
 * the StringAnalyzer class
 *
 * NOTE - NO CHANGE NEEDED TO THIS PROGRAM!
 * ****************************************
 */

package lab05;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that tests out the {@link StringAnalyzer} class
 *
 * @author Prof. King
 */
public class StringAnalyzerTester {
    /**
     * Helper method to set the string to check, and use the different methods to output
     * interesting info about the string
     *
     * @param analyzer - the {@link StringAnalyzer} object we're using
     * @param str      - the String to check
     */
    private static void checkString(StringAnalyzer analyzer, String str) {
        System.out.println("**** Checking...     \"" + str + "\"");
        if (!analyzer.setStringToAnalyze(str)) {
            System.out.printf("ERROR - %s has already been checked!\n", str);
            return;
        }
        char answer;
        System.out.println("Successfully set:    \"" + analyzer.getCurrentString() + "\"");
        System.out.println("First repeat char:   '" +
                                   (((answer = analyzer.firstRepeatChar()) == 0) ? "" : answer) +
                                   "'");
        System.out.println("First multiple char: '" +
                                   (((answer = analyzer.firstMultipleChar()) == 0) ? "" : answer) +
                                   "'");
        System.out.print("All multiple chars:  ");
        if (analyzer.allMultipleChars().length == 0)
            System.out.println("[]");
        else {
            String s = Arrays.stream(analyzer.allMultipleChars())
                    .map(ch -> "" + ch)
                    .collect(Collectors.joining("','", "['", "']"));
            System.out.println(s);
        }
    }

    /**
     * Our main program - this is running the program checking the results
     *
     * @param args
     */
    public static void main(String[] args) {
        StringAnalyzer analyzer = new StringAnalyzer();

        checkString(analyzer, "Pennsylvania");
        checkString(analyzer, "New York City");
        checkString(analyzer, "Massachusetts");
        checkString(analyzer, "Mississippi!!!");
        checkString(analyzer, "Maine");
        checkString(analyzer, "Massachusetts");

        System.out.println("ALL STRINGS TESTED:");
        System.out.println(analyzer.getListOfStrings());
        System.out.println("Goodbye!");
    }


}

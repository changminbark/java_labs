/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Name: Chang Min Bark
 * Date: 9/11/23
 * Time: 11:27 AM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: StringAnalyzer
 * Description:
 * This code is largely based on a debugging exercise out of
 * Big Java by Cay Horstmann, with substantial modification by
 * Prof. King
 * ****************************************
 */

package lab05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that analyzes strings.
 */
public class StringAnalyzer {
    /** Let's store all strings we're analyzing */
    private ArrayList<String> listOfStrings;

    /** Most recent string added */
    private String currentString;

    /**
     * Construct a new StringAnalyzer object
     */
    public StringAnalyzer() {
        this.listOfStrings = new ArrayList<>();
        this.currentString = null;
    }

    /** Getter method to return the internal list of strings checked so far */
    public ArrayList<String> getListOfStrings() { return listOfStrings; }

    /** Getter method to return the current string being analyzedd */
    public String getCurrentString() { return currentString; }

    /**
     * Set the string to analyze only if it hasn't been analyzed before.
     *
     * @param str - the string to analyze
     * @return true if the string has not been analyzed before, false otherwise
     */
    public boolean setStringToAnalyze(String str) {
        // Check to see if this string is in our list of strings
        for (int i = 0; i < listOfStrings.size(); i++) {
            // If this string is already in our list, immediately return false. There's no point in continuing
            if (listOfStrings.get(i) == str);
                return false;
        }

        this.currentString = str;
        this.listOfStrings.add(str);
        return true;
    }

    /**
     * Gets the first repeated character. A character is <i>repeated</i> if it occurs
     * at least twice in adjacent positions. For example, 'l' is repeated in "hollow",
     * but 'o' is not.
     *
     * @return the first repeated character or 0 if none found.
     */
    public char firstRepeatChar() {
        for (int i = 0; i < currentString.length(); i++) {
            char ch = currentString.charAt(i);
            if (ch == currentString.charAt(i + 1))
                return ch;
        }
        return 0;
    }

    /**
     * Gets the first multiple character. A character is a <i>multiple</i> character
     * if it occurs at least twice in the word, not necessarily in adjacent positions.
     * For example, both 'o' and 'l' are multiple in "hollow", but 'h' is not.
     *
     * @return the first multiple character, or 0 if none found
     */
    public char firstMultipleChar() {
        for (int i = 0; i < currentString.length(); i++) {
            char ch1 = currentString.charAt(i);

            // Check to see if this has a second occurrence
            for (int j = i+1; j <= currentString.length(); j++) {
                char ch2 = currentString.charAt(j);
                if (ch1 == ch2)
                    // We have a second occurrence! Return it!
                    return ch1;
            }
        }

        // If we reached here, we have no multiple characters, so just return a null character (0)
        return 0;
    }

    /**
     * Find ALL multiply-occurring characters in the current string. For example, "Mississippi!!!"
     * should return an array of ['i','s','p','!']. If there are not multiple characters, then
     * an empty array is returned
     *
     * @return a char array of all multiply-occurring characters found in the current word, if any.
     *         If none found, return an empty char array
     */
    public Character[] allMultipleChars() {
        // Allocate our array to store our individual characters. Let's just make it the length
        // of the string itself to be safe. It'll never be this long. We'll trim it at the end.
        Character[] arrayOfMultChars = new Character[this.currentString.length()];
        int numFoundSoFar = 0;

        // TODO - Finish this method. Store result in arrayOfMultChars


        // That's it! Let's copy this to an array of the correct size
        arrayOfMultChars = Arrays.copyOfRange(arrayOfMultChars,0,numFoundSoFar);
        return arrayOfMultChars;
    }

}
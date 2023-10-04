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
 * Class: HTMLScanner
 *
 * Description:
 *
 * ****************************************
 */
package lab09;


import lab08.Employee;

import java.io.*;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enum class for the two types of report sort type.
 */
enum ReportSortType {
    SORT_BY_TAG_NAME,
    SORT_BY_TAG_FREQUENCY
}

/**
 * The scanner that does the scanning for HTML tags
 */
public class HTMLScanner {

    /**
     * The input-stream variable used to input data
     */
    private InputStream inStream;

    /**
     * The TreeMap used to store frequency of HTML tags
     */
    private TreeMap<String, Integer> mapOfTags;

    /**
     * Keeps track of the total number of HTML tags
     */
    private int totalTagCount;

    /**
     * The constructor that creates the HTML scanner
     * @param inStream - an InputStream that is obtained from the URL
     */
    public HTMLScanner(InputStream inStream) {
        this.inStream = inStream;
    }

    /**
     * Returns number of total tags
     * @return number of total tags
     */
    public int getTotalTagCount() {
        return this.totalTagCount;
    }

    /**
     * Returns number of unique tags
     * @return number of unique tags
     */
    public int getTotalUniqueTagCount() {
        return this.mapOfTags.size();
    }

    /**
     * The method that scans the webpage for all the tags
     */
    public void scanForAllTags() {
        try {
            // Setting up bufferedreader
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.inStream));
            String inputLine;
            StringBuilder htmlContent = new StringBuilder();

            // Read HTML content and build into a string
            while ((inputLine = reader.readLine()) != null) {
                htmlContent.append(inputLine);
            }

            // Close the bufferedreader
            reader.close();

            // Convert the HTML content into a string
            String html = htmlContent.toString();

            // Set up Regex pattern for name of HTML start tag
            Pattern p = Pattern.compile("<\\s*(\\w+)[^>]*>");

            // Set up TreeMap that contains all the tags and frequncies
            this.mapOfTags = new TreeMap<>();

            // Set up matcher that finds strings that match the regex pattern
            Matcher m = p.matcher(html);
            while (m.find()) {
                String startTag = m.group(1);
                if (this.mapOfTags.containsKey(startTag)) {
                    this.mapOfTags.put(startTag, this.mapOfTags.get(startTag) + 1);
                }
                // If tag is not in TreeMap
                else {
                    this.mapOfTags.put(startTag, 1);
                }
                this.totalTagCount += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Prints the report of the HTML parsing with the selected sort type
     * @param out - The PrintStream object that outputs data into System.out or a file
     * @param sortType - The selected sort type, which can only take on one of two values
     */
    public void printReport(PrintStream out, ReportSortType sortType) {
        if (sortType == ReportSortType.SORT_BY_TAG_NAME) {
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(mapOfTags.entrySet());
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                out.println(entry);
            }
        }
        else if (sortType == ReportSortType.SORT_BY_TAG_FREQUENCY) {
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(mapOfTags.entrySet());
            sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            Collections.reverse(sortedEntries);
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                out.println(entry);
            }
        }
    }

}


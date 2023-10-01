/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: HRUtils
 * Description:
 * This is a simple utility class that encapsulates some helper methods
 * for our HR system
 * ****************************************
 */

package lab08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * A utility class to encapsulate some useful methods to assist with managing
 * the HR system
 */
public final class HRUtils {

    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html
    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Helper method to parse a date string into a date object. This is
     * really here just to show how to deal with an exception that may
     * be thrown in a method.
     *
     * @param sDate - a date string
     * @return a {@link LocalDate} object
     * @throws {@link DateTimeParseException} if the string cannot be parsed correctly.
     */
    public static LocalDate strToDate(String sDate) throws DateTimeParseException {
        return LocalDate.parse(sDate,df);
    }

    /**
     * Helper method to convert a date object to a {@link LocalDate} string.
     */
    public static String dateToStr(LocalDate date) {
        return df.format(date);
    }

}

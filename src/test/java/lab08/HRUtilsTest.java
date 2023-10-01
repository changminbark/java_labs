/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 *
 * Name: Chang Min Bark
 *
 * Project: csci205
 * Package: lab08
 * File: HRUtilsTest
 * Description:
 * Tests methods in the HRUtils class
 * ****************************************
 */

package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HRUtilsTest {

    /**
     * Confirm that strToDate indeed takes a {@link String} object
     * and returns a standard formatted date
     */
    @Test
    void strToDate() {
        String sDate = "2022-09-18";
        LocalDate dateExpected = LocalDate.parse(sDate);
        LocalDate dateActual = HRUtils.strToDate(sDate);
        assertEquals(dateExpected, dateActual);
    }

    /**
     * Confirm that dateToStr indeed takes a {@link LocalDate} object
     * and returns a standard String as a formatted date
     */
    @Test
    void dateToStr() {
        String sExpected = "2022-09-18";
        LocalDate dateExpected = LocalDate.parse(sExpected);
        String sActual = HRUtils.dateToStr(LocalDate.parse(sExpected));
        assertEquals(sExpected, sActual);
    }
}
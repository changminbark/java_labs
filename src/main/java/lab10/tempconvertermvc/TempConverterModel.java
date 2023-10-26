/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2023
 *
 * Author: Prof. King
 *
 * Name: YOUR NAME
 * Date: 10/11/2023
 * Time: 9:30 PM

 * Project: csci205_labs
 * Class: TempConverterModel
 * Description:
 *
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * This is the "model" for our temp converter program. There really is not
 * much to a model in this particular program. The only logic that can be separated
 * out are those functions that do conversions
 */
public class TempConverterModel {
    private static final String DEFAULT_FORMATTER = "%.1f";
    private String sFormatter;

    private SimpleBooleanProperty isSetForFtoC;
    private SimpleBooleanProperty isSetForCtoF;


    /**
     * Last temperature converted, standardized to Celsius
     */
    private SimpleDoubleProperty lastTempConvertedInC;

    /**
     * Initialize a new model
     */
    public TempConverterModel() {
        this.sFormatter = DEFAULT_FORMATTER;
        this.isSetForFtoC = new SimpleBooleanProperty(true);
        this.isSetForCtoF = new SimpleBooleanProperty(false);
        this.lastTempConvertedInC = new SimpleDoubleProperty(0);
    }

    /**
     * A simple function that takes a string represents a temperatre
     * in Fahrenheit, and converts it to Celsius
     * @param sFTemp
     * @return a String representing the temp in celsius.
     */
    public String convertFtoC(String sFTemp) {
        Double tempF = Double.parseDouble(sFTemp);
        Double tempC = (tempF - 32.0) * 5.0 / 9.0;
        this.lastTempConvertedInC.set(tempC);
        return String.format(this.sFormatter, tempC);
    }

    /**
     * A simple function that takes a string represents a temperatre
     * in Celsius, and converts it to Fahrenheit
     * @param sCTemp
     * @return a String representing the temp in Fahrenheit.
     */
    public String convertCtoF(String sCTemp) {
        Double tempC = Double.parseDouble(sCTemp);
        Double tempF = (tempC * 9.0 / 5.0) + 32.0;
        this.lastTempConvertedInC.set(tempC);
        return String.format(this.sFormatter, tempF);
    }

    /**
     * Use the boolean properties to determine how the temperature should be
     * converted (either from Fahrenheit to Celsius or vice versa)
     *
     * @param strTemp the {@link String} representing a temperature
     * @return the converted temperature as a String
     */
    public String strTempConvert(String strTemp) {
        if (this.isIsSetForFtoC()) {
            return this.convertFtoC(strTemp);
        } else if (this.isIsSetForCtoF()) {
            return this.convertCtoF(strTemp);
        }
        else {
            // This should never happen
            return strTemp;
        }
    }

    public boolean isIsSetForFtoC() {
        return isSetForFtoC.get();
    }

    public SimpleBooleanProperty isSetForFtoCProperty() {
        return isSetForFtoC;
    }

    public boolean isIsSetForCtoF() {
        return isSetForCtoF.get();
    }

    public SimpleBooleanProperty isSetForCtoFProperty() {
        return isSetForCtoF;
    }

    public double getLastTempConvertedInC() {
        return lastTempConvertedInC.get();
    }

    public SimpleDoubleProperty lastTempConvertedInCProperty() {
        return lastTempConvertedInC;
    }

}

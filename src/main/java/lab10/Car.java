/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/22/2023
 * Time: 10:36 PM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: Car
 *
 * Description:
 *
 * ****************************************
 */
package lab10;

import java.util.Arrays;
import java.util.List;

/**
 * A class representing a car and contains attributes of cars
 */
public class Car {

    /**
     * miles per gallon
     */
    private double mpg;

    /**
     * cylinders
     */
    private int cylinders;

    /**
     * displacement
     */
    private double displacement;

    /**
     * horsepower
     */
    private double horsepower;

    /**
     * weight
     */
    private double weight;

    /**
     * acceleration
     */
    private double accel;

    /**
     * model year
     */
    private int modelYear;

    /**
     * origin
     */
    private int origin;

    /**
     * car name
     */
    private String carName;

    /**
     *
     * @param csvRecord
     */
    public Car(String csvRecord) {
        List<String> properties = Arrays.asList(csvRecord.split("\\s*,\\s*"));
        this.mpg = Double.parseDouble(properties.get(0));
        this.cylinders = Integer.parseInt(properties.get(1));
        this.displacement = Double.parseDouble(properties.get(2));
        if (properties.get(3).trim().equalsIgnoreCase("?")) {
            this.horsepower = 0.0;
        } else {
            this.horsepower = Double.parseDouble(properties.get(3));
        }
        this.weight = Double.parseDouble(properties.get(4));
        this.accel = Double.parseDouble(properties.get(5));
        this.modelYear = Integer.parseInt(properties.get(6));
        this.origin = Integer.parseInt(properties.get(7));
        this.carName = properties.get(8);
    }

    public double getMpg() {
        return mpg;
    }

    public int getCylinders() {
        return cylinders;
    }

    public double getDisplacement() {
        return displacement;
    }

    public double getHorsepower() {
        return horsepower;
    }

    public double getWeight() {
        return weight;
    }

    public double getAccel() {
        return accel;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getOrigin() {
        return origin;
    }

    public String getCarName() {
        return carName;
    }
}
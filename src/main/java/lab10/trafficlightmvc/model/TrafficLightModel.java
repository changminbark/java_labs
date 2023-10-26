/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/25/2023
 * Time: 11:32 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.model
 * Class: TrafficLightModel
 *
 * Description:
 *
 * ****************************************
 */
package lab10.trafficlightmvc.model;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class TrafficLightModel {
    /**
     * Our array of {@link Light} objects for our traffic light
     */
    private ArrayList<Light> lights;

    /**
     * Will our lights automatically shut off?
     */
    private SimpleBooleanProperty isAutoOff;

    /**
     * A constructor for the Model
     */
    public TrafficLightModel() {
        // A traffic light will utilize an ArrayList to store the lights
        this.lights = new ArrayList<>();

        // Let's initialize their clors and add them to the array
        for (LightColorEnum light : LightColorEnum.values())
            this.lights.add(new Light(light.getColor()));

        // Initialize the auto off property
        this.isAutoOff = new SimpleBooleanProperty(false);
    }

    /**
     * Returns the light at a specified position in the array
     * @param i the index of the position
     * @return The light at index i.
     */
    public Light getLight(int i) {
        return this.lights.get(i);
    }

    /**
     * Getter for arraylist of lights
     * @return arraylist of lights
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     * Getter for a boolean value of whether auto is off or not
     * @return a boolean value
     */
    public boolean isIsAutoOff() {
        return isAutoOff.get();
    }

    /**
     * Getter for a SimpleBooleanProperty of whether auto is off or not
     * @return a SimpleBooleanProperty
     */
    public SimpleBooleanProperty isAutoOffProperty() {
        return isAutoOff;
    }
}
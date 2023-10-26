/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/25/2023
 * Time: 11:18 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.model
 * Class: Light
 *
 * Description:
 *
 * ****************************************
 */
package lab10.trafficlightmvc.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * A simple abstraction for a basic light that can turn on and off.
 * Our light will also have a color which the class will manage
 * to simulate darkening
 */
public class Light {

    /**
     * Is the light on?
     */
    private SimpleBooleanProperty isOn;

    /**
     * The current color of the light
     */
    private SimpleObjectProperty<Color> currentColor;

    /**
     * The light's on color
     */
    private Color onColor;

    /**
     * The light's off color
     */
    private Color offColor;

    public Light(Color color) {
        this.isOn = new SimpleBooleanProperty(false);
        this.onColor = color;
        this.offColor = color.darker();
        this.currentColor = new SimpleObjectProperty<>();
        this.currentColor.set(this.offColor);
    }

    /**
     * Toggle the state of the light to be either on or off
     */
    public void toggle() {
        this.isOn.set(!this.isOn.get());
        if (this.isIsOn())
            this.currentColor.set(this.onColor);
        else
            this.currentColor.set(this.offColor);
    }

    public void turnOnForMs(long timeInMs) {
        Runnable r = () -> {
            try {
                // If we're not on, then turn on
                if (!this.isIsOn())
                    toggle();
                Thread.sleep(timeInMs);
            }
            catch (InterruptedException e) {
            }
            finally {
                // If we're on, then make sure we turn off
                if (this.isIsOn())
                    toggle();
            }
        };

        // Encapsulate our Runnable in a thread and start it
        Thread t = new Thread(r);
        t.start();
    }

    public boolean isIsOn() {
        return isOn.get();
    }

    public SimpleBooleanProperty isOnProperty() {
        return isOn;
    }

    public Color getCurrentColor() {
        return currentColor.get();
    }

    public SimpleObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }
}
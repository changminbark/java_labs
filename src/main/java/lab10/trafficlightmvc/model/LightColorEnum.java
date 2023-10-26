package lab10.trafficlightmvc.model;

import javafx.scene.paint.Color;

/**
 * An enumeration of the different lights we'll use in our app.
 * Each enumeration will encapsulate a color representing an "on"
 * state.
 */
public enum LightColorEnum {
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.LAWNGREEN);

    private Color color;

    private LightColorEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

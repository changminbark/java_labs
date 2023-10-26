/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/25/2023
 * Time: 11:42 PM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.view
 * Class: TrafficLightView
 *
 * Description:
 *
 * ****************************************
 */
package lab10.trafficlightmvc.view;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import lab10.trafficlightmvc.model.Light;
import lab10.trafficlightmvc.model.TrafficLightModel;

import java.util.ArrayList;

public class TrafficLightView {
    private TrafficLightModel theModel;
    private VBox root;
    private CheckBox checkBoxAutoOff;
    private ArrayList<Circle> lights;

    public TrafficLightView(TrafficLightModel theModel) {
        this.theModel = theModel;

        initSceneGraph();
        initStyling();
    }

    private void initSceneGraph() {
        this.root = new VBox();
        this.checkBoxAutoOff = new CheckBox("Auto off");
        this.checkBoxAutoOff.setSelected(theModel.isIsAutoOff());
        this.lights = new ArrayList<>();
        for (Light modelLight : theModel.getLights()) {
            // set the initial size of the light to be 50
            Circle light = new Circle(50);

            // Set a style class so we can set additional styles in css later
            light.getStyleClass().add("light");

            // Set the fill color based on the model
            light.setFill(modelLight.getCurrentColor());

            // Add the light to our array
            lights.add(light);
        }

        this.root.getChildren().add(checkBoxAutoOff);
        this.root.getChildren().addAll(lights);
    }

    private void initStyling() {

    }

    public VBox getRoot() {
        return root;
    }

    public CheckBox getCheckBoxAutoOff() {
        return checkBoxAutoOff;
    }

    public ArrayList<Circle> getLights() {
        return lights;
    }

    public Circle getLight(int i) {
        return lights.get(i);
    }
}
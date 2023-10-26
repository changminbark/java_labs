/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/26/2023
 * Time: 12:07 AM
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightController
 *
 * Description:
 *
 * ****************************************
 */
package lab10.trafficlightmvc;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.control.CheckBox;
import javafx.scene.shape.Circle;
import lab10.trafficlightmvc.model.Light;
import lab10.trafficlightmvc.model.TrafficLightModel;
import lab10.trafficlightmvc.view.TrafficLightView;

public class TrafficLightController {

    private TrafficLightModel theModel;
    private TrafficLightView theView;

    public TrafficLightController(TrafficLightModel theModel, TrafficLightView theView) {
        this.theModel = theModel;
        this.theView = theView;
        initBindings();
        initEventHandlers();
    }

    private void initBindings() {
        // Bind isAutoOff from the model to the view
        theModel.isAutoOffProperty().bind(theView.getCheckBoxAutoOff().selectedProperty());

        // Let's create a binding that is based on whatever dimension of our window is the
        // largest
        NumberBinding radiusBinding =
                Bindings.max(theView.getRoot().heightProperty(),
                        theView.getRoot().widthProperty())
                        .divide(6)
                        .add(-15);

        // Bind our new radiusBinding of each circle
        for (Circle c : theView.getLights()) {
            c.radiusProperty().bind(radiusBinding);
        }

        // Bind the color of the light in the view to the omdel light color
        for (int i = 0; i < theModel.getLights().size(); i++) {
            Light lightModel = theModel.getLight(i);
            Circle lightView = theView.getLight(i);
            CheckBox checkBox = theView.getCheckBoxAutoOff();

            lightView.fillProperty().bind(lightModel.currentColorProperty());
            lightView.onMouseClickedProperty().setValue(event -> {
                if (checkBox.isSelected()) {
                    lightModel.turnOnForMs(1000);
                } else {
                    lightModel.toggle();
                }
            });

        }
    }

    private void initEventHandlers() {
        this.theView.getCheckBoxAutoOff().setOnAction(event -> {

        });
    }
}
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
 * Class: TempConverterController
 *
 * Description:
 *
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * This is the MVC controller class for our temperature converter app
 */
public class TempConverterController {
    private TempConverterModel theModel;
    private TempConverterView theView;

    /**
     * Construct a controller that connects the model and the view for our
     * temperature conversion program
     *
     * @param theModel
     * @param theView
     */
    public TempConverterController(TempConverterModel theModel, TempConverterView theView) {
        this.theModel = theModel;
        this.theView = theView;

        initEventHandlers();
        initBindings();
    }

    /**
     * This is an internal helper method to initialize the event handlers
     */
    private void initEventHandlers() {
        this.theView.getBtnConvert().setOnAction(this::handleActionEvent);
        this.theView.getTextFieldTempInput().setOnAction(this::handleActionEvent);
        this.theView.getRbFtoC().setOnAction(this::handleActionEvent);
        this.theView.getRbCtoF().setOnAction(this::handleActionEvent);
        theModel.lastTempConvertedInCProperty().addListener((observable, oldValue, newValue) -> {
            double hue = (1 - newValue.doubleValue() / 40.0) * 240;
            if (hue > 240) hue = 240;
            if (hue < 0) hue = 0;
            String hsbString = "hsb(" + hue + ",100%,75%);";
            this.theView.getLblResult().setStyle(
                    "-fx-border-color: " + hsbString +
                    "-fx-text-fill: " + hsbString
            );
        });
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    public void handleActionEvent(ActionEvent event) {
        try {
            String s = this.theView.getTextFieldTempInput().getText();
            // Only convert if something has been entered
            if (!s.isEmpty()){
                String result = this.theModel.strTempConvert(s);
                this.theView.getLblResult().setText(result);
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getTextFieldTempInput().getText()));
            alert.show();
        }
    }

    /**
     * Initialize the bindings used in our app
     */
    private void initBindings() {
        theModel.isSetForFtoCProperty().bind(theView.getRbFtoC().selectedProperty());
        theModel.isSetForCtoFProperty().bind(theView.getRbCtoF().selectedProperty());
        theView.getLblUnits().textProperty().bind(Bindings.when(theModel.isSetForFtoCProperty())
                .then("(F)")
                .otherwise("(C)"));
    }
}

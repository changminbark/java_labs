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
 * Class: TempConverterView
 *
 * Description:
 * This represents the basic GUI part of our temperature converter.
 * This includes the code to create the scene graph for the app, and
 * the styling of the display.
 * ****************************************
 */

package lab10.tempconvertermvc;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * This is the "view" in the MVC design for the temperature converter. A view class
 * does nothing more than initializes all nodes for the scene graph for this view.
 */
public class TempConverterView {

    /** The model that contains the data and logic behind this view */
    private TempConverterModel theModel;

    /** Root node for the scene graph */
    private BorderPane root;

    /** topPane is the {@link FlowPane} layout container for the top of the view */
    private FlowPane topPane;

    /** The {@link TextField} control where the user enters text */
    private TextField textFieldTempInput;

    /** The {@link Label} that shows the result of the temperature conversion */
    private Label lblResult;

    /** The {@link Button} that initiates a temperature conversion */
    private Button btnConvert;

    /** The {@link Label} that shows the units of the temperature */
    private Label lblUnits;

    /**
     * Radio button for Fahrenheit to Celsius
     */
    private RadioButton rbFtoC;

    /**
     * Radio button for Celsius to Fahrenheit
     */
    private RadioButton rbCtoF;

    /**
     * Left pane that contains the radio buttons
     */
    private VBox leftPane;

    /**
     * Construct a new instance of the entire scene graph for
     * this view
     */
    public TempConverterView(TempConverterModel theModel) {
        this.theModel = theModel;
        initSceneGraph();
        initStyling();
    }

    /**
     * Return the root node of the scene graph for this view
     * @return
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Get the text field that contains the TextField control where
     * the user enters the temperature
     *
     * @return the {@link TextField} instance where the user enters the temperature
     */
    public TextField getTextFieldTempInput() {
        return textFieldTempInput;
    }

    /**
     * @return the {@link Label} where the result of the conversion should be
     * shown
     */
    public Label getLblResult() {
        return lblResult;
    }

    /**
     * @return the {@link Button} that initiates the temperature conversion
     * calculation
     */
    public Button getBtnConvert() {
        return btnConvert;
    }

    /**
     * Initialize the entire scene graph
     */
    private void initSceneGraph() {
        root = new BorderPane();

        // Set up top pane container to hold the text field to
        // enter a temperature
        topPane = new FlowPane();
        topPane.setId("topPane");

        // Text Field to enter the temperature
        textFieldTempInput = new TextField();

        // Initialize label units
        lblUnits = new Label("(F)");

        // Add leaf nodes for top pane
        topPane.getChildren().add(new Label("Temperature:"));
        topPane.getChildren().add(textFieldTempInput);
        topPane.getChildren().add(lblUnits);

        // Middle section will show the result
        lblResult = new Label("");
        lblResult.setId("lblResult");

        // Set up the button to initiate the conversion
        btnConvert = new Button("Convert!");

        // The left section will show a radio button group to allow the
        // user to select the conversion to perform
        ToggleGroup group = new ToggleGroup();
        rbFtoC = new RadioButton("F to C");
        rbCtoF = new RadioButton("C to F");
        rbFtoC.setToggleGroup(group);
        rbCtoF.setToggleGroup(group);
        rbFtoC.setSelected(true);

        // Creating left pane
        leftPane = new VBox();
        leftPane.getChildren().addAll(rbFtoC, rbCtoF);

        // Add the three main sections for the VBox root container
        root.setTop(topPane);
        root.setCenter(lblResult);
        root.setBottom(btnConvert);
        root.setLeft(leftPane);
    }

    /**
     * Apply appropriate styles to all of the content in the scene graph
     * for this view
     */
    public void initStyling() {
        // root.setPrefSize(300, 150);
        // root.setPadding(new Insets(15));

        // topPane.setOrientation(Orientation.HORIZONTAL);
        // topPane.setAlignment(Pos.CENTER);
        // topPane.setHgap(10);

        // textFieldTempInput.setAlignment(Pos.CENTER);
        // textFieldTempInput.setPrefColumnCount(5);

        // lblResult.setPrefWidth(75);
        // lblResult.setPrefHeight(25);

        // Set up a border to appear around the converted temp
//        lblResult.setBorder(new Border(new BorderStroke(null,
//                                                        BorderStrokeStyle.SOLID,
//                                                        new CornerRadii(4),
//                                                        BorderWidths.DEFAULT)));
        // Let's use CSS instead!
        // lblResult.setStyle("-fx-border-style: solid; " +
        //                    "-fx-border-radius: 4");
        //
        // lblResult.setAlignment(Pos.CENTER);


        // leftPane.setSpacing(10);

        BorderPane.setAlignment(btnConvert, Pos.CENTER);
    }

    public Label getLblUnits() {
        return lblUnits;
    }

    public RadioButton getRbFtoC() {
        return rbFtoC;
    }

    public RadioButton getRbCtoF() {
        return rbCtoF;
    }
}

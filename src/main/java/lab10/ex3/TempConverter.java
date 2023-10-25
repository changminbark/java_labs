package lab10.ex3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TempConverter extends Application {

    private VBox root;
    private FlowPane topPane;
   private TextField textFieldTempInput;
   private Label lblTempOutput;
   private Button btnConvert;
   private static final String DEFAULT_FORMATTER = "%.1f";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize our scene graph
        initSceneGraph();

        // Apply styling to our scene graph nodes
        initStyling();

        // Initialize our event handlers
        initEventHandlers();

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(root));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("F to C Converter");

        // Display the stage
        primaryStage.show();
    }

    /**
     * Initialize the scene graph for the app
     */
    private void initSceneGraph() {
        root = new VBox();

        // Set up top pane container to hold the controls to get the
        // temp from the user
        topPane = new FlowPane();

        // TextField to enter the temperature
        textFieldTempInput = new TextField();

        // Text to show output temperature conversion
        lblTempOutput = new Label("");

        // Creating button
        btnConvert = new Button("Convert!");

        // Adding containers to root node/pane
        root.getChildren().add(topPane);
        root.getChildren().add(lblTempOutput);
        root.getChildren().add(btnConvert);

        // Adding label and textinput to topPane
        topPane.getChildren().add(new Label("Temperature (F):"));
        topPane.getChildren().add(textFieldTempInput);
    }

    /**
     * Initialize the styling of elements in the scene graph
     */
    private void initStyling(){
        // Styling root node
        root.setSpacing(5);
        root.setPrefWidth(250);
        root.setPadding(new Insets(10, 5, 10, 5));
        root.setAlignment(Pos.CENTER);

        // Styling top pane
        topPane.setOrientation(Orientation.HORIZONTAL);
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(10);

        // Align the temp input to be centered, and leave space for 5 characters
        textFieldTempInput.setAlignment(Pos.CENTER);
        textFieldTempInput.setPrefColumnCount(5);

        // Styling output Text
        lblTempOutput.setAlignment(Pos.CENTER);
        lblTempOutput.setPrefWidth(75);
        lblTempOutput.setPrefHeight(25);
        lblTempOutput.setBorder(new Border(new BorderStroke(null,
                BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT)));
    }

    /**
     * Initialize event handlers for elements in the scene graph
     */
    private void initEventHandlers(){
        // Set up our btnConvert event handler
        btnConvert.setOnAction(event -> {
            try {
                String inputTemp = textFieldTempInput.getText();
                Double tempF = Double.parseDouble(inputTemp);
                Double tempC = (tempF - 32) * 5.0 / 9.0;
                String outputTemp = String.format(DEFAULT_FORMATTER, tempC);
                lblTempOutput.setText(outputTemp);
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect input!");
                alert.setHeaderText("Incorrect input specified!");
                alert.setContentText(String.format("Cannot convert \"%s\"", textFieldTempInput.getText()));
                alert.showAndWait();
            }
        });

        // Set up our text field to use our button event handler
        textFieldTempInput.setOnAction(btnConvert.getOnAction());
    }
}

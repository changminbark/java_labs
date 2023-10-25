package lab10.ex2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Formattable;

public class HelloMe extends Application {

    /**
     * Private instance variables that are containers and controls.
     * For controls, we have a textfield for users to put in their names.
     * We also have a button for them to display their names.
     */
    private VBox root;
    private HBox topPane;
    private TextField textFieldInputName;
    private Button btnHello;
    private Text textNameOutput;
    private DropShadow dropShadow;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initSceneGraph();
        initStyling();
        initEventHandlers();

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(this.root));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("Hello, Me!");

        // Display the stage
        primaryStage.show();
    }

    /**
     * Initialize the scene graph for the app
     */
    private void initSceneGraph() {
        // Initialize root node
        this.root = new VBox();
        this.root.setSpacing(10);
        this.root.setPadding(new Insets(15));
        this.root.setAlignment(Pos.CENTER);

        // Initialize HBox pane
        this.topPane = new HBox();
        this.topPane.setSpacing(10);
        this.topPane.setAlignment(Pos.CENTER);
        root.getChildren().add(this.topPane);
        this.topPane.getChildren().add(new Label("Your name:"));

        // Add textfield to topPane
        this.textFieldInputName = new TextField();
        this.topPane.getChildren().add(this.textFieldInputName);

        // Add button to VBox
        this.btnHello = new Button("Show my name!");
        root.getChildren().add(this.btnHello);

        // Set up Separator
        root.getChildren().add(new Separator());

    }

    /**
     * Initialize the styling of elements in the scene graph
     */
    private void initStyling(){
        // Set text for name output
        this.textNameOutput = new Text();
        this.textNameOutput.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        this.textNameOutput.setFill(Color.FUCHSIA);
        this.root.getChildren().add(this.textNameOutput);

        // Reflection effect for textNameOutput
        Reflection reflection = new Reflection();
        reflection.setFraction(0.9);
        this.textNameOutput.setEffect(reflection);

        // Dropshadow effect for textNameOutput
        this.dropShadow = new DropShadow();
        this.dropShadow.setOffsetX(3);
        this.dropShadow.setOffsetY(3);
        this.dropShadow.setHeight(5);
        this.dropShadow.setRadius(2);
        this.dropShadow.setColor(Color.DARKGRAY);
        reflection.setInput(this.dropShadow);
    }

    /**
     * Initialize event handlers for elements in the scene graph
     */
    private void initEventHandlers(){
        //Move the shadow around as the mouse moves
        root.setOnMouseMoved(event -> {
            this.dropShadow.setOffsetX(event.getX() - root.getWidth()/3);
            this.dropShadow.setOffsetY(event.getY() - root.getHeight()/3);
        });

        // Set up the event handler for the button. Greet the name entered
        this.btnHello.setOnAction(event -> {
            String name = this.textFieldInputName.getText();
            System.out.println("Hello, " + name + "!");
            this.textNameOutput.setText(name);
        });
    }
}

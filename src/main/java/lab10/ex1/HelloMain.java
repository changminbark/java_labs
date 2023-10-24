package lab10.ex1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloMain extends Application {

    private Button btn;
    private Label lblTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the root node for our scene graph
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Initialize the controls for our scene graph
        initSceneGraph(root);

        // Set up our scene and place the root of the scene on it
        Scene scene = new Scene(root, 400, 300);

        //Set the scene on the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Hello JavaFX!");

        // Display the scene
        primaryStage.show();
    }

    /**
     * A method that initializes the controls to be placed in the scene graph {@code root}
     * @param root The root node container of the Scene Graph (in our case, it is a StackPane)
     */
    private void initSceneGraph(VBox root) {
        //Create a button and set up the event handler to report the current time
        btn = new Button();
        btn.setText("Report the date and time");

        // A label to show the current time when clicked
        lblTime = new Label();

        // Add the controls to the scene graph
        root.getChildren().add(btn);
        root.getChildren().add(lblTime);

        btn.setOnAction(event -> {
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa MM/dd/yyyy");
            // System.out.println(ldt.format(formatter));
            lblTime.setText(ldt.format(formatter));
        });



    }
}

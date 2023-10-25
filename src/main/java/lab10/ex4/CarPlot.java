/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/24/2023
 * Time: 6:00 PM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: CarPlot
 *
 * Description:
 *
 * ****************************************
 */

package lab10.ex4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lab10.Car;

public class CarPlot extends Application {

    /**
     * The name of the file used for our exercise
     */
    private static String CSV_FILE_NAME = "auto-mpg.csv";

    /**
     * Our list of {@link Car} objects
     */
    private ArrayList<Car> cars;

    /**
     * Root node of the scene graph
     */
    private BorderPane root;

    /**
     * Status bar to show some stuff at the bottom
     */
    private Label lblStatusBar;

    /**
     * A carscatter plot object that represents the scatterplot
     */
    private CarScatterPlot carScatterPlot;

    /**
     * The scene graph to be added to the stage
     */
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Ask the user for a file, then read it in
        File file = getFileFromUser(primaryStage);
        readAutoMpgFile(file);

        initSceneGraph();

        lblStatusBar.setText("Read in " + String.valueOf(cars.size()) + " cars.");

        // Add the data to the scatterplot and create the plot to be shown
        carScatterPlot.plot();

        // Setting up primary stage
        scene = new Scene(root, 500, 400);

        // Load the stylesheet and apply it to the scene
        scene.getStylesheets().add(
                getClass().getResource("/lab10/CarPlot.css").toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Car MPG Plotter");
        primaryStage.show();
    }

    /**
     * Create our scene graph for our app
     */
    private void initSceneGraph() {
        // Set up the root node
        root = new BorderPane();

        // Set up the status bar
        lblStatusBar = new Label();
        lblStatusBar.setId("lblStatusBar");
        root.setBottom(lblStatusBar);

        // Set up our CarScatterPlot
        carScatterPlot = new CarScatterPlot(cars);
        root.setCenter(carScatterPlot.getChart());

    }

    /**
     * Prompt the user with a {@link FileChooser} dialog to select the data
     * file to be used.
     *
     * @param stage is the parent window for the dialog window
     * @return the File object representing our data file
     */
    private static File getFileFromUser(Stage stage) {
        File fileChosen = null;

        // Initialize a FileChoose to where we want to open our file
        FileChooser fileChooser = new FileChooser();

        // The file is in our resources directory off of our project folder, and
        // we only want CSV files.
        fileChooser.setInitialDirectory(new File("src/main/resources"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        // Display the file open dialog window
        fileChosen = fileChooser.showOpenDialog(stage);

        // Return the file chosen
        return fileChosen;
    }


    /**
     * Read auto-mpg.csv into our internal ArrayList of Car objects
     *
     * @param file is the {@link File} object representing the file to be read
     */
    private void readAutoMpgFile(File file) {
        cars = new ArrayList<>();

        try (Scanner scnr = new Scanner(file)) {
            // Skip the header
            scnr.nextLine();

            while (scnr.hasNextLine()) {
                Car car = new Car(scnr.nextLine().strip());
                cars.add(car);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException in readAutoMpgFile! This should never happen!");
            System.exit(1);
        }
    }
}

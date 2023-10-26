package lab10.trafficlightmvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab10.trafficlightmvc.model.TrafficLightModel;
import lab10.trafficlightmvc.view.TrafficLightView;

public class TrafficLightMain extends Application {

    private TrafficLightModel theModel;
    private TrafficLightView theView;
    private TrafficLightController theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());

        // Attach a CSS file for styling our app
        scene.getStylesheets().add(
                getClass().getResource("/lab10/trafficlightmvc.css")
                        .toExternalForm());

        this.theController = new TrafficLightController(this.theModel,this.theView);

        primaryStage.setTitle("Traffic Light Sim");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new TrafficLightModel();
        this.theView = new TrafficLightView(this.theModel);

    }
}

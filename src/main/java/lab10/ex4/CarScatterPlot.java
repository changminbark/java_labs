/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Joshua Stough
 *
 * Name: Chang Min Bark
 * Section: 02
 * Date: 10/24/2023
 * Time: 8:37 PM
 *
 * Project: csci205_labs
 * Package: lab10.ex4
 * Class: CarScatterPlot
 *
 * Description:
 *
 * ****************************************
 */
package lab10.ex4;

import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import lab10.Car;

/**
 * A class that represents a ScatterPlot object, including
 * the data that will be plotted and the axes.
 */
public class CarScatterPlot {

    /**
     * A reference to the list of cars
     */
    private ArrayList<Car> cars;

    /**
     * A reference to the X axis in the scatterplot
     */
    private NumberAxis xAxis;

    /**
     * A reference to the Y axis in the scatterplot
     */
    private NumberAxis yAxis;

    /**
     * A reference to the series that encapsulates the data that will be plotted
     */
    private XYChart.Series<Number, Number> series;



    /**
     * The actual scatter plot itself
     */
    private ScatterChart<Number, Number> chart;


    public CarScatterPlot(ArrayList<Car> cars) {
        this.cars = cars;

        // Instantiate the objects needed for our ScatterChart
        this.xAxis = new NumberAxis();
        this.yAxis = new NumberAxis();
        this.series = new XYChart.Series<>();

        // Instantiate a ScatterChart object
        this.chart = new ScatterChart<>(xAxis, yAxis);
    }

    /**
     * Encapsulate the variables to be plotted into the {@link #series} and then
     * add the series to our {@link #chart}
     */
    public void plot(){
        // Set the title for the chart
        chart.setTitle("Weight vs. MPG for automobiles");

        // Set the labels
        xAxis.setLabel("Weight");
        yAxis.setLabel("MPG");

        // Add the data to the series
        cars.stream()
                .forEach(car -> {
                    XYChart.Data<Number, Number> datum = new XYChart.Data<>(car.getWeight(), car.getMpg());
                    series.getData().add(datum);
                });
        series.setName("Series 1");
        chart.getData().add(series);
    }

    public ScatterChart<Number, Number> getChart() {
        return chart;
    }

}
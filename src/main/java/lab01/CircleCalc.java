package lab01;

import java.util.Scanner;

public class CircleCalc {
    private static Scanner scnr;
    private static final double EPSILON = 1.0E-10;
    public static void main( String[] args ){
        // Set up a common scanner to be used;
        scnr = new Scanner(System.in);

        // Get the input values required
        double angleInRadians = getAngleFromUser();
        double radius = getRadiusFromUser();

        // Report the result
        reportQuadrant(angleInRadians);
        reportXYCoordinates(angleInRadians, radius);
        System.out.println("Goodbye!");

    }

    public static double getAngleFromUser(){
        double angleInDegrees = -1;

        while (!(0<=angleInDegrees && angleInDegrees<360)){
            System.out.println("Please enter the angle in degrees [0-360): ");
            // Checks whether input is a number
            if (!scnr.hasNextDouble()){
                System.out.println("Please enter a number!");
            } 
            else {
                angleInDegrees = scnr.nextDouble();
                // Checks whether input is a valid number (between 0 and 360)
                if (angleInDegrees < 0) {
                    System.out.println("Please enter an angle of at least 0 degrees");
                } 
                else if (angleInDegrees >= 360) {
                    System.out.println("Please enter an angle less than 360 degrees");
                }
            }
            // Refreshes/empties scnr input buffer.
            scnr.nextLine();
        }
        // Converts angle from degrees to rads.
        double angleRadian = Math.toRadians(angleInDegrees);
        return angleRadian;
    }


    public static double getRadiusFromUser(){
        double radiusInput = -1;

        // This method works similarly to getAngleFromUser().
        while (radiusInput < 0){
            System.out.println("Please enter the radius of the circle (> 0): ");
            if (!scnr.hasNextDouble()){
                System.out.println("Please enter a number!");
            } 
            else {
                radiusInput = scnr.nextDouble();
                if (radiusInput < 0) {
                    System.out.println("Please enter positive radius");
                } 
            }
            scnr.nextLine();
        }
        return radiusInput;
    }


    public static void reportQuadrant(double angle){
        // Checks if angle lies between quadrants.
        if ((angle % 90.0) < EPSILON) {
            System.out.println("Quadrant: IN BETWEEN QUADRANTS");
        }
        // These next statements check which quadrant the point lies in.
        else if (angle < 90) {
            System.out.println("Quadrant: 1");
        }
        else if (angle < 180) {
            System.out.println("Quadrant: 2");
        }
        else if (angle < 270) {
            System.out.println("Quadrant: 3");
        }
        else if (angle < 360) {
            System.out.println("Quadrant: 4");
        }
    }


    public static void reportXYCoordinates(double angle, double radius){
        // This calculates the x and y coordinates of the point and prints it using a printf() method.
        double xCoord = radius * Math.cos(angle);
        double yCoord = radius * Math.sin(angle);
        System.out.printf("Your coordinates are: (%f, %f)", xCoord, yCoord);
        System.out.println();
    }
}

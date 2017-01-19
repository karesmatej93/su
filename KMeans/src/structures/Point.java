package structures;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Matěj Kareš on 30.11.2016.
 */
public class Point implements Serializable {
    static int pointID = 0;

    int id;

    /** Dimension of the point. 1D, 2D, 3D, 4D, etc. */
    private int dimension;

    /** Coordinates of this point in it's dimension. */
    private double[] coordinates;

    private boolean visited;

    private boolean outlier;

    public Point(int dimension, double[] coordinates) {
        this.dimension = dimension;
        this.coordinates = coordinates;
        this.id = pointID++;
    }

    public Point(int dimension) {
        this(dimension, new double[dimension]);
    }

    public Point(double[] coordinates) {
        this(coordinates.length, coordinates);
    }

    public Point(Point point) {
        this(point.coordinates);
    }


    /**
     * Determines the Euler distance between this and given point.
     * N-Dimensional algorithm.
     *
     * @param point - target point
     * @return double - distance between said points
     */
    public double euclideanDistanceTo(Point point){
        double zeroedDim; // for each dimension we need their zeroed distance (point acts as base)
        double sumOfPartialDistances = 0;

        for(int i = 0; i < dimension; i++){
            //Transpose to the base (zero)
            zeroedDim = coordinates[i] - point.coordinates[i];

            //Square distance
            sumOfPartialDistances += zeroedDim * zeroedDim;
        }

        //Distance is the square root of the sum of partial distances
        return Math.sqrt(sumOfPartialDistances);
    }


    /**
     * Determines the Manhattan distance between this and given point.
     * N-Dimensional algorithm.
     *
     * @param point - target point
     * @return double - distance between said points
     */
    public double manhattanDistanceTo(Point point){
        //We need to sum all the distances through all dimensions.
        double sumOfPartialDistances = 0;

        for(int i = 0; i < dimension; i++){
            sumOfPartialDistances += Math.abs(this.coordinates[i] - point.coordinates[i]);
        }

        return sumOfPartialDistances;
    }



    public void moveTo(Point to){
        this.coordinates = to.coordinates;
    }


    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getSpecificCoord(int dimension){
        return coordinates[dimension];
    }

    public void setSpecificCoord(int dimension, double value){
        coordinates[dimension] = value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isOutlier() {
        return outlier;
    }

    public void setOutlier(boolean outlier) {
        this.outlier = outlier;
    }

    @Override
    public String toString() {
        return "Point " + id + " {" + Arrays.toString(coordinates) + '}';
    }

    public boolean equals(Point other) {
        boolean match = true;

        for(int i = 0; i < dimension; i++){
            if(coordinates[i] != other.coordinates[i]){
                return false;
            }
        }

        return match;
    }
}

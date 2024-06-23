import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer  414  414rerr46441414
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Start with numPoints = 0
        int numPoints = 0;
        // For each point currPt in the shape
        for(Point currPt: s.getPoints()) {
            // Update numPoints + 1 when each time iterates a new point
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Start with numPoints, totalPerim
        int numPoints = getNumPoints(s);
        double totalPerim = getPerimeter(s);
        // return AverageLength, totalPerimeter / numOfAllPoints
        double averageLength = 0;
        averageLength = totalPerim / numPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with largestSide = 0
        double largestSide = 0;
        // Start with prevPt = the last point
        Point prevPt = s.getLastPoint();
        // Iterate all sides of the Shape s
        for(Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            // Compare and store the larger distance
            if(currDist > largestSide) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        // Start with LargestX = lastPoint.getX();
        double largestX = s.getLastPoint().getX();
        // Iterate all points in shape s
        for(Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if(currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        // Start with largestPerim = 0
        double largestPerim = 0;
        // Declare dr of type DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Select files / directory
        for(File f : dr.selectedFiles()) {
            // Iterate each file selected
            FileResource fr = new FileResource(f);
            // Use file to create shape
            Shape s = new Shape(fr);
            // Calulate Perim and compare to the largestPerim, 
            // and then store the larger one
            double currPerim = getPerimeter(s);
            if(currPerim > largestPerim) {
                largestPerim = currPerim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        // Start with largestPerim = 0
        double largestPerim = 0;
        // Declare dr of type DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Select files / directory
        for(File f : dr.selectedFiles()) {
            // Iterate each file selected
            FileResource fr = new FileResource(f);
            // Use file to create shape
            Shape s = new Shape(fr);
            // Calulate Perim and compare to the largestPerim, 
            // and then store the larger one, both file and perim
            double currPerim = getPerimeter(s);
            if(currPerim > largestPerim) {
                largestPerim = currPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // Number of points
        int numPoints = getNumPoints(s);
        System.out.println("Number of points = " + numPoints);
        // Average Length of all sides
        double averageLength = getAverageLength(s);
        System.out.println("Average length of all sides = " + averageLength);
        // Largest Side of all sides
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side = " + largestSide);
        // Largest x value of all points
        double largestX = getLargestX(s);
        System.out.println("Largest X value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        // Get the largest perimeter from multiple files selected
        double largetPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largetPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        // Get the file name from multiple files selected with the largest perimeter
        String fileNameWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File Name selected with the biggest perimeter = " + fileNameWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        // pr.testFileWithLargestPerimeter();
    }
}

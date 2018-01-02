import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Main {
    List<Point> centroidList;
    List<Point> pointList;

    public Main() {
        centroidList = new ArrayList<>();
        pointList = new ArrayList<>();
        String line;

        try (RandomAccessFile input = new RandomAccessFile("input.txt", "r")) {
            line = input.readLine();
            String token1[] = line.split(" ");

            for(int i=0; i<6; i=i+2)
                centroidList.add(new Point(Double.parseDouble(token1[i]), Double.parseDouble(token1[i+1])));


            while((line = input.readLine())  != null){
                String token2[] = line.split(" ");
                pointList.add(new Point(Double.parseDouble(token2[0]), Double.parseDouble(token2[1])));
            }

            for(int i=0; i<centroidList.size(); i++)
                for(Point point1: pointList){
                    double distance = getDistance(centroidList.get(i), point1);
                    if(i == 0)
                        point1.setD1(distance);
                    else if(i ==1)
                        point1.setD2(distance);
                    else
                        point1.setD3(distance);
                }
            for(int i=0; i<pointList.size(); i++){
                Point point = pointList.get(i);
                if((point.getD1() <= point.getD2()) && (point.getD1() <= point.getD3()))
                    point.setClusterClass(1);
                else if((point.getD2() <= point.getD1()) && (point.getD2() <= point.getD3()))
                    point.setClusterClass(2);
                else
                    point.setClusterClass(3);
            }
            RandomAccessFile ra = new RandomAccessFile("output1.txt", "rw");
            for(Point point: pointList)
                ra.writeBytes(point.toString() + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile output = new RandomAccessFile("output2.txt", "rw")) {
            for(int i=0; i<pointList.size(); i++){
                output.writeBytes(pointList.get(i).getString() +"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Point> newCentroidList = new ArrayList<>();
        double sumOfXOfOneCluster = 0;
        double sumOfYOfOneCluster = 0;
        double numberOfOneCluster = 0;

        double sumOfXOfTwoCluster = 0;
        double sumOfYOfTwoCluster = 0;
        double numberOfTwoCluster = 0;

        double sumOfXOfThreeCluster = 0;
        double sumOfYOfThreeCluster = 0;
        double numberOfThreeCluster = 0;

        for(int i=0; i<pointList.size(); i++){
            Point point = pointList.get(i);

            if(point.getClusterClass() == 1){
                sumOfXOfOneCluster += point.getX();
                sumOfYOfOneCluster += point.getY();
                numberOfOneCluster += 1;
            }

            else if(point.getClusterClass() == 2){
                sumOfXOfTwoCluster += point.getX();
                sumOfYOfTwoCluster += point.getY();
                numberOfTwoCluster += 1;
            }

            else
                sumOfXOfThreeCluster += point.getX();
                sumOfYOfThreeCluster += point.getY();
                numberOfThreeCluster += 1;

        }

        newCentroidList.add(new Point(sumOfXOfOneCluster/numberOfOneCluster, sumOfYOfOneCluster/numberOfOneCluster));
        newCentroidList.add(new Point(sumOfXOfTwoCluster/numberOfTwoCluster, sumOfYOfTwoCluster/numberOfTwoCluster));
        newCentroidList.add(new Point(sumOfXOfThreeCluster/numberOfThreeCluster, sumOfYOfThreeCluster/numberOfThreeCluster));

        for(int i=0; i<newCentroidList.size(); i++)
            for(Point point1: pointList){
                double distance = getDistance(newCentroidList.get(i), point1);
                if(i == 0)
                    point1.setD1(distance);
                else if(i ==1)
                    point1.setD2(distance);
                else
                    point1.setD3(distance);
            }
        for(int i=0; i<pointList.size(); i++){
            Point point = pointList.get(i);
            if((point.getD1() <= point.getD2()) && (point.getD1() <= point.getD3()))
                point.setClusterClass(1);
            else if((point.getD2() <= point.getD1()) && (point.getD2() <= point.getD3()))
                point.setClusterClass(2);
            else
                point.setClusterClass(3);
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("output3.txt", "rw")) {
            for(Point point: pointList)
                randomAccessFile.writeBytes(point.toString()+"\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getDistance(Point point2, Point point1){
        double distance1 = (point2.getX() - point1.getX()) * (point2.getX() - point1.getX());
        double distance2 = (point2.getY() - point1.getY()) * (point2.getY() - point1.getY());
        double distance = Math.sqrt(distance1+distance2);
        return distance;
    }

    public static void main (String [] args){
        new Main();
    }
}

package KmeansClustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

//Author: Ben Burbank
//Last Update: 3MAR2023
//Resources consulted:
//https://towardsdatascience.com/k-means-clustering-introduction-to-machine-learning-algorithms-c96bf0d5d57a
//https://github.com/shephalika21/K-Means-Implementation-in-Java
//https://stanford.edu/~cpiech/cs221/handouts/kmeans.html
//https://www.geeksforgeeks.org/k-means-clustering-introduction/
//https://medium.com/analytics-vidhya/from-pseudocode-to-python-code-k-means-clustering-from-scratch-2e32aa469bef
//https://www.analyticsvidhya.com/blog/2021/11/understanding-k-means-clustering-in-machine-learningwith-examples/
//https://www.javatpoint.com/k-means-clustering-algorithm-in-machine-learning
//https://www.baeldung.com/java-k-means-clustering-algorithm

public class Main {
    public static void main(String[] args) throws IOException {

        String filename = "synthetic_control_data.txt";
        int[] fileParams = fileParameters(filename);

        int numberOfSeeds = 6;
        int[] seeds = generateRandSeeds(0, fileParams[0]-1, numberOfSeeds);
        Cluster[] myClusters = createClustersFromSeeds(filename, seeds);
        Cluster[] myClustersDone = K_Means.kMeansAlgorithm(myClusters);
        createOutputFiles(myClustersDone);
    }

    private static void createOutputFiles(Cluster[] myClustersDone) {
        String fileNames = "";
        for(int i = 0; i < myClustersDone.length; i++){
            if(i == myClustersDone.length-1){
                fileNames = fileNames.concat("OutputFile" + (i+1) + ".txt");
            }
            else{
                fileNames = fileNames.concat("OutputFile" + (i+1) + ".txt,");
            }
            
        }
        String[] fileNamesArray = fileNames.split(",");
        PrintWriter pr;
        List<Point> clusterPoints;
        String toWrite;

        for(int i = 0; i < myClustersDone.length; i++){
            try{
                File f = new File(fileNamesArray[i]);
                if(f.exists() && !f.isDirectory()) { 
                    pr = new PrintWriter(fileNamesArray[i]);
                    clusterPoints = myClustersDone[i].getPoints();
                    toWrite = "";
                    for (int j=0; j < clusterPoints.size()-1 ; j++){
                        toWrite += clusterPoints.get(j).toString() + "\n";
                    }
                    pr.print(toWrite + clusterPoints.get(clusterPoints.size()-1).toString());
                    pr.close();
                }
                else{
                    f.createNewFile();
                    pr = new PrintWriter(fileNamesArray[i]);
                    clusterPoints = myClustersDone[i].getPoints();
                    for (int j=0; j < clusterPoints.size() ; j++){
                        if(j == clusterPoints.size()-1){
                            pr.print(clusterPoints.get(j).toString());
                        }
                        else{
                            pr.println(clusterPoints.get(j).toString());
                        }
                        
                    }
                    pr.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("No such file exists.");
            }
        }
    }

    public static int[] fileParameters(String filename){
        //returns an array of 2 values: rowsCount, colsCount
        int[] rowsCols = new int[2];
        int cols = 0;
        int rows = 0;
        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
            
			while (line != null) {
				line = line.replaceAll("\\s+", ",");
                String[] temp = line.trim().split(",");
                if(temp.length > cols){
                    cols = temp.length;
                }
                rows += 1;
                line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        rowsCols[0] = rows;
        rowsCols[1] = cols;
        return rowsCols;
    }

    public static int[] generateRandSeeds(int min, int max, int numOfSeeds){
        int[] seeds = new int[numOfSeeds];
        if(numOfSeeds > max || min > max){ 
            for(int i = 0; i < seeds.length; i++){
                seeds[i] = -1;
            }
            return seeds; 
        }
        else {
            boolean flagSet = false;
            for(int i = 0; i < seeds.length; i++){
                int temp = min + (int)(Math.random() * ((max - min) + 1));
                for(int j = 0; j < seeds.length; j++){
                    if(temp == seeds[j]){
                        flagSet = true;
                    }
                }
                if(!flagSet){
                    //value not in set, add to set
                    seeds[i] = temp;
                }
                else{
                    //try again
                    i+=1;
                }
            }
        }
        sortSeeds(seeds);
        return seeds;
    }

    public static void sortSeeds(int[] seeds){
        for(int i = 0; i < seeds.length-1; i++){
            for(int j = i+1; j < seeds.length; j++){
                if(seeds[j] < seeds[i]){
                    int temp = seeds[j];
                    seeds[j] = seeds[i];
                    seeds[i] = temp;
                }
            }
        }
    }

    public static Cluster[] createClustersFromSeeds(String filename, int[] seeds){
        BufferedReader reader;
        Cluster[] completeClusters = new Cluster[seeds.length];
        int lineCount = 0;
        int lastLine = 0;
        int seedNumber = 0;
        int linesToCount = 0;


        String line = "";
        try {
            reader = new BufferedReader(new FileReader(filename));
            while(seedNumber != seeds.length) {

                // TODO read line number of times current number is - last number + 1 (think 0)
                linesToCount = seeds[seedNumber] - lastLine + 1;
                for (int i = 0; i < linesToCount; i++) {
                    line = reader.readLine();
                }

                line = line.replaceAll("\\s+", ",");
                String[] temp = line.trim().split(",");

                double[] doubleArray = Arrays.stream(temp).mapToDouble(Double::parseDouble).toArray();
                Point centroid = new Point(doubleArray);
                completeClusters[seedNumber] = new Cluster();
                completeClusters[seedNumber].setCentroid(centroid);
                lastLine = seeds[seedNumber];
                seedNumber++;
            }
        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            lineCount = 0;
            reader = new BufferedReader(new FileReader(filename));
            line = reader.readLine();
            
            while (line != null) {
                line = line.replaceAll("\\s+", ",");
                String[] temp = line.trim().split(",");
                
                if(lineCount != seeds[0] && lineCount != seeds[1] && lineCount != seeds[2] && lineCount != seeds[3] && lineCount != seeds[4] && lineCount != seeds[5]){
                    double[] doubleArray = Arrays.stream(temp).mapToDouble(Double::parseDouble).toArray();
                    Point tempPoint = new Point(doubleArray);


                    double[] distances = new double[seeds.length];
                    for(int i = 0; i < seeds.length; i++){
                        distances[i] = Point.distanceToCentroid(tempPoint, completeClusters[i].centroid);
                    }


                    double minimum = Double.MAX_VALUE;
                    int minIndex = 0;
                    for (int i = 0; i < distances.length; i++)
                    {
                        if (minimum > distances[i])
                        {
                            minimum = distances[i];
                            minIndex = i;
                        }
                    }
                    completeClusters[minIndex].addPoint(tempPoint);

                }
                lineCount += 1;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < completeClusters.length; i++){
            completeClusters[i].addPoint(completeClusters[i].centroid);
            completeClusters[i].setCentroid(calculateCentroid(completeClusters[i]));
        }
        return completeClusters;
    }

    public static Point calculateCentroid(Cluster cluster){
        double[] sumValues = new double[60];

        for(int i = 0; i < cluster.getNumberOfPoints(); i++){
            for (int j = 0; j < sumValues.length; j++)
            {
                sumValues[j] =cluster.points.get(i).getValue(j);
            }
        }

        return new Point(sumValues);
    } //end calculateCentroid method


} //end of Main method
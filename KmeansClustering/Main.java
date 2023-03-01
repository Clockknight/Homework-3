package KmeansClustering;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.Scanner;
//import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//import java.util.List;

//Author: Ben Burbank
//Last Update: 28FEB2023
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

        /**
         * synthetic_control_data.txt is a file of 60 doubles per line, 600 lines long
         */
        String filename = "synthetic_control_data.txt";
        int[] fileParams = fileParameters(filename);
        //System.out.println(filename + " is " + fileParams[0] + " rows and " + fileParams[1] + " cols.");
        int numberOfSeeds = 6;
        int[] seeds = generateRandSeeds(0, fileParams[0]-1, numberOfSeeds);
        /**
        System.out.print("Random seeds are: ");
        for(int i = 0; i < seeds.length; i++){
            System.out.print(seeds[i] + " ");
        }
        System.out.print("\n");
         */
        Cluster[] myClusters = createClustersFromSeeds(filename, seeds);
        //all clusters with centroids set are contained in an array
        /**
        for(int i = 0; i < myClusters.length; i++){
            System.out.println("Cluster " + i + " contains " + myClusters[i].getNumberOfPoints() + " points.");
        }
         */
        
        //nothing below this point is started
        int iterationLimit = 100;
        Cluster[] myClustersDone = K_Means.kMeansAlgorithm(myClusters, iterationLimit);

        outputFiles(myClustersDone);
        
        
        
    }

    private static void outputFiles(Cluster[] myClustersDone) {
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
        //Cluster tempPointHoldingCluster = new Cluster(-1000);
        int lineCount = 0;
        int seedNumber = 0;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            
            while (line != null) {
                line = line.replaceAll("\\s+", ",");
                String[] temp = line.trim().split(",");
                
                if(lineCount == seeds[seedNumber]){//change to giant OR statement and assign to cluster
                    //System.out.println("seed = " + seeds[seedNumber]);
                    double[] doubleArray = Arrays.stream(temp).mapToDouble(Double::parseDouble).toArray();
                    Point centroid = new Point(doubleArray);
                    completeClusters[seedNumber] = new Cluster(seedNumber);
                    completeClusters[seedNumber].setCentroid(centroid);
                    if(seedNumber < seeds.length-1){
                        seedNumber += 1;
                    }
                    
                }
                lineCount += 1;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //now completeClusters has seeds.length Clusters, each with a centroid
        lineCount = 0;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            
            while (line != null) {
                line = line.replaceAll("\\s+", ",");
                String[] temp = line.trim().split(",");
                
                if(lineCount != seeds[0] && lineCount != seeds[1] && lineCount != seeds[2] && lineCount != seeds[3] && lineCount != seeds[4] && lineCount != seeds[5]){
                    double[] doubleArray = Arrays.stream(temp).mapToDouble(Double::parseDouble).toArray();
                    Point tempPoint = new Point(doubleArray);
                    //assign tempPoint to one of the Clusters in completeClusters, based on distance to centroid
                    double[] distances = new double[seeds.length];
                    for(int i = 0; i < seeds.length; i++){
                        distances[i] = Point.distanceToCentroid(tempPoint, completeClusters[i].centroid);
                    }
                    if(distances[0] < distances[1] && distances[0] < distances[2] && distances[0] < distances[3] && distances[0] < distances[4] && distances[0] < distances[5]){
                        completeClusters[0].addPoint(tempPoint);
                    }
                    else if(distances[1] < distances[0] && distances[1] < distances[2] && distances[1] < distances[3] && distances[1] < distances[4] && distances[1] < distances[5]){
                        completeClusters[1].addPoint(tempPoint);
                    }
                    else if(distances[2] < distances[0] && distances[2] < distances[1] && distances[2] < distances[3] && distances[2] < distances[4] && distances[2] < distances[5]){
                        completeClusters[2].addPoint(tempPoint);
                    }
                    else if(distances[3] < distances[0] && distances[3] < distances[1] && distances[3] < distances[2] && distances[3] < distances[4] && distances[3] < distances[5]){
                        completeClusters[3].addPoint(tempPoint);
                    }
                    else if(distances[4] < distances[0] && distances[4] < distances[1] && distances[4] < distances[2] && distances[4] < distances[3] && distances[4] < distances[5]){
                        completeClusters[4].addPoint(tempPoint);
                    }
                    else{
                        completeClusters[5].addPoint(tempPoint);
                    }
                }
                lineCount += 1;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return completeClusters;
    }


} //end of Main method
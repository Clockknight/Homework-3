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

        /**
         * synthetic_control_data.txt is a file of 60 doubles per line, 600 lines long
         */
        String filename = "synthetic_control_data.txt";
        int[] fileParams = fileParameters(filename);

        int numberOfSeeds = 6;
        int[] seeds = generateRandSeeds(0, fileParams[0]-1, numberOfSeeds);
        /** testing
        System.out.print("Random seeds are: ");
        for(int i = 0; i < seeds.length; i++){
            System.out.print(seeds[i] + " ");
        }
        System.out.print("\n");
        */
        Cluster[] myClusters = createClustersFromSeeds(filename, seeds);
        //all clusters with centroids set are contained in an array
        
        //testing; use this to test files to go into RapidMiner
        /**
        for(int i = 0; i < myClusters.length; i++){
            System.out.println("Cluster " + i + " contains " + myClusters[i].getNumberOfPoints() + " points.");
        }
        createOutputFiles(myClusters);
        */
        
        Cluster[] myClustersDone = K_Means.kMeansAlgorithm(myClusters);
        createOutputFiles(myClustersDone);     
        
    } //end main method

    private static void createOutputFiles(Cluster[] myClustersDone) {
        String fileNames = "";
        for(int i = 0; i < myClustersDone.length; i++){
            if(i == myClustersDone.length-1){
                fileNames = fileNames.concat("OutputFile" + (i+1));
            }
            else{
                fileNames = fileNames.concat("OutputFile" + (i+1) + ",");
            }
            
        }
        String[] fileNamesArray = fileNames.split(",");
        
        for(int i = 0; i < myClustersDone.length; i++){
            try{
                File f = new File(fileNamesArray[i]);
                if(f.exists() && !f.isDirectory()) { 
                    PrintWriter pr = new PrintWriter(fileNamesArray[i]);    
                    List<Point> clusterPoints = myClustersDone[i].getPoints();
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
                else{
                    f.createNewFile();
                    PrintWriter pr = new PrintWriter(fileNamesArray[i]);    
                    List<Point> clusterPoints = myClustersDone[i].getPoints();
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
    } //end createOutputFiles method

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
    } //end fileParameters method

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
    } //end generateRandSeeds method

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
    } //end sortSeeds method

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
        //now have the full file read in and each line other than seeds assigned as a Point to a Cluster
        //have to add the seeds/centroids to their own clusters as Points and calculate new centroid from the complete cluster
        for(int i = 0; i < completeClusters.length; i++){
            completeClusters[i].addPoint(completeClusters[i].centroid);
            completeClusters[i].setCentroid(calculateCentroid(completeClusters[i]));
        }
        return completeClusters;
    } //end createClustersFromSeeds method

    public static Point calculateCentroid(Cluster cluster){
        double sumVal1 = 0;
        double sumVal2 = 0;
        double sumVal3 = 0;
        double sumVal4 = 0;
        double sumVal5 = 0;
        double sumVal6 = 0;
        double sumVal7 = 0;
        double sumVal8 = 0;
        double sumVal9 = 0;
        double sumVal10 = 0;
        double sumVal11 = 0;
        double sumVal12 = 0;
        double sumVal13 = 0;
        double sumVal14 = 0;
        double sumVal15 = 0;
        double sumVal16 = 0;
        double sumVal17 = 0;
        double sumVal18 = 0;
        double sumVal19 = 0;
        double sumVal20 = 0;
        double sumVal21 = 0;
        double sumVal22 = 0;
        double sumVal23 = 0;
        double sumVal24 = 0;
        double sumVal25 = 0;
        double sumVal26 = 0;
        double sumVal27 = 0;
        double sumVal28 = 0;
        double sumVal29 = 0;
        double sumVal30 = 0;
        double sumVal31 = 0;
        double sumVal32 = 0;
        double sumVal33 = 0;
        double sumVal34 = 0;
        double sumVal35 = 0;
        double sumVal36 = 0;
        double sumVal37 = 0;
        double sumVal38 = 0;
        double sumVal39 = 0;
        double sumVal40 = 0;
        double sumVal41 = 0;
        double sumVal42 = 0;
        double sumVal43 = 0;
        double sumVal44 = 0;
        double sumVal45 = 0;
        double sumVal46 = 0;
        double sumVal47 = 0;
        double sumVal48 = 0;
        double sumVal49 = 0;
        double sumVal50 = 0;
        double sumVal51 = 0;
        double sumVal52 = 0;
        double sumVal53 = 0;
        double sumVal54 = 0;
        double sumVal55 = 0;
        double sumVal56 = 0;
        double sumVal57 = 0;
        double sumVal58 = 0;
        double sumVal59 = 0;
        double sumVal60 = 0;

        for(int i = 0; i < cluster.getNumberOfPoints(); i++){
            sumVal1 += cluster.points.get(i).getVal1();
            sumVal2 += cluster.points.get(i).getVal2();
            sumVal3 += cluster.points.get(i).getVal3();
            sumVal4 += cluster.points.get(i).getVal4();
            sumVal5 += cluster.points.get(i).getVal5();
            sumVal6 += cluster.points.get(i).getVal6();
            sumVal7 += cluster.points.get(i).getVal7();
            sumVal8 += cluster.points.get(i).getVal8();
            sumVal9 += cluster.points.get(i).getVal9();
            sumVal10 += cluster.points.get(i).getVal10();
            sumVal11 += cluster.points.get(i).getVal11();
            sumVal12 += cluster.points.get(i).getVal12();
            sumVal13 += cluster.points.get(i).getVal13();
            sumVal14 += cluster.points.get(i).getVal14();
            sumVal15 += cluster.points.get(i).getVal15();
            sumVal16 += cluster.points.get(i).getVal16();
            sumVal17 += cluster.points.get(i).getVal17();
            sumVal18 += cluster.points.get(i).getVal18();
            sumVal19 += cluster.points.get(i).getVal19();
            sumVal20 += cluster.points.get(i).getVal20();
            sumVal21 += cluster.points.get(i).getVal21();
            sumVal22 += cluster.points.get(i).getVal22();
            sumVal23 += cluster.points.get(i).getVal23();
            sumVal24 += cluster.points.get(i).getVal24();
            sumVal25 += cluster.points.get(i).getVal25();
            sumVal26 += cluster.points.get(i).getVal26();
            sumVal27 += cluster.points.get(i).getVal27();
            sumVal28 += cluster.points.get(i).getVal28();
            sumVal29 += cluster.points.get(i).getVal29();
            sumVal30 += cluster.points.get(i).getVal30();
            sumVal31 += cluster.points.get(i).getVal31();
            sumVal32 += cluster.points.get(i).getVal32();
            sumVal33 += cluster.points.get(i).getVal33();
            sumVal34 += cluster.points.get(i).getVal34();
            sumVal35 += cluster.points.get(i).getVal35();
            sumVal36 += cluster.points.get(i).getVal36();
            sumVal37 += cluster.points.get(i).getVal37();
            sumVal38 += cluster.points.get(i).getVal38();
            sumVal39 += cluster.points.get(i).getVal39();
            sumVal40 += cluster.points.get(i).getVal40();
            sumVal41 += cluster.points.get(i).getVal41();
            sumVal42 += cluster.points.get(i).getVal42();
            sumVal43 += cluster.points.get(i).getVal43();
            sumVal44 += cluster.points.get(i).getVal44();
            sumVal45 += cluster.points.get(i).getVal45();
            sumVal46 += cluster.points.get(i).getVal46();
            sumVal47 += cluster.points.get(i).getVal47();
            sumVal48 += cluster.points.get(i).getVal48();
            sumVal49 += cluster.points.get(i).getVal49();
            sumVal50 += cluster.points.get(i).getVal50();
            sumVal51 += cluster.points.get(i).getVal51();
            sumVal52 += cluster.points.get(i).getVal52();
            sumVal53 += cluster.points.get(i).getVal53();
            sumVal54 += cluster.points.get(i).getVal54();
            sumVal55 += cluster.points.get(i).getVal55();
            sumVal56 += cluster.points.get(i).getVal56();
            sumVal57 += cluster.points.get(i).getVal57();
            sumVal58 += cluster.points.get(i).getVal58();
            sumVal59 += cluster.points.get(i).getVal59();
            sumVal60 += cluster.points.get(i).getVal60();
        }

        Point result = new Point(sumVal1/cluster.getNumberOfPoints(), sumVal2/cluster.getNumberOfPoints(), sumVal3/cluster.getNumberOfPoints(), sumVal4/cluster.getNumberOfPoints(), sumVal5/cluster.getNumberOfPoints(), sumVal6/cluster.getNumberOfPoints(), sumVal7/cluster.getNumberOfPoints(), sumVal8/cluster.getNumberOfPoints(), sumVal9/cluster.getNumberOfPoints(), sumVal10/cluster.getNumberOfPoints(), 
        sumVal11/cluster.getNumberOfPoints(), sumVal12/cluster.getNumberOfPoints(), sumVal13/cluster.getNumberOfPoints(), sumVal14/cluster.getNumberOfPoints(), sumVal15/cluster.getNumberOfPoints(), sumVal16/cluster.getNumberOfPoints(), sumVal17/cluster.getNumberOfPoints(), sumVal18/cluster.getNumberOfPoints(), sumVal19/cluster.getNumberOfPoints(), sumVal20/cluster.getNumberOfPoints(), 
        sumVal21/cluster.getNumberOfPoints(), sumVal22/cluster.getNumberOfPoints(), sumVal23/cluster.getNumberOfPoints(), sumVal24/cluster.getNumberOfPoints(), sumVal25/cluster.getNumberOfPoints(), sumVal26/cluster.getNumberOfPoints(), sumVal27/cluster.getNumberOfPoints(), sumVal28/cluster.getNumberOfPoints(), sumVal29/cluster.getNumberOfPoints(), sumVal30/cluster.getNumberOfPoints(),
        sumVal31/cluster.getNumberOfPoints(), sumVal32/cluster.getNumberOfPoints(), sumVal33/cluster.getNumberOfPoints(), sumVal34/cluster.getNumberOfPoints(), sumVal35/cluster.getNumberOfPoints(), sumVal36/cluster.getNumberOfPoints(), sumVal37/cluster.getNumberOfPoints(), sumVal38/cluster.getNumberOfPoints(), sumVal39/cluster.getNumberOfPoints(), sumVal40/cluster.getNumberOfPoints(),
        sumVal41/cluster.getNumberOfPoints(), sumVal42/cluster.getNumberOfPoints(), sumVal43/cluster.getNumberOfPoints(), sumVal44/cluster.getNumberOfPoints(), sumVal45/cluster.getNumberOfPoints(), sumVal46/cluster.getNumberOfPoints(), sumVal47/cluster.getNumberOfPoints(), sumVal48/cluster.getNumberOfPoints(), sumVal49/cluster.getNumberOfPoints(), sumVal50/cluster.getNumberOfPoints(),
        sumVal51/cluster.getNumberOfPoints(), sumVal52/cluster.getNumberOfPoints(), sumVal53/cluster.getNumberOfPoints(), sumVal54/cluster.getNumberOfPoints(), sumVal55/cluster.getNumberOfPoints(), sumVal56/cluster.getNumberOfPoints(), sumVal57/cluster.getNumberOfPoints(), sumVal58/cluster.getNumberOfPoints(), sumVal59/cluster.getNumberOfPoints(), sumVal60/cluster.getNumberOfPoints());
        return result;
    } //end calculateCentroid method


} //end of Main method
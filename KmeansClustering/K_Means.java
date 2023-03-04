package KmeansClustering;
/**
• Inputs -> D – dataset with n objects
• Output -> set of k clusters
• Method
(1) arbitrarily select k objects from D as the initial
cluster mean (centroids)

(2) reassign each object to the cluster to which it is most
similar, based upon the distance between the object
and the cluster centroid

(3) update cluster centroid using objects in each cluster

(4) Repeat (2) and (3) until the criterion function
converges (no redistribution of objects in any cluster)
 */

public class K_Means{
    public static Cluster[] kMeansAlgorithm(Cluster[] inputCluster){
        boolean pointChangedClusters = true;
        while(pointChangedClusters){
            pointChangedClusters = false;
            for(int i = 0; i < inputCluster.length; i++){
                for(int j = 0; j < inputCluster[i].getNumberOfPoints(); j++){
                    Point tempPoint = inputCluster[i].getPoints().get(j);
                    int closestCluster = pointBelongsToCluster(tempPoint, inputCluster);
                    if(closestCluster == i){ //already in correct cluster
                        //break;
                    }
                    else{
                        inputCluster[closestCluster].addPoint(inputCluster[i].points.get(j));
                        inputCluster[i].points.remove(j);
                        pointChangedClusters = true;
                    }   
                }    
            }
            //all points have been checked and assigned to closest centroid
            //reset centroids
            for(int i = 0; i < inputCluster.length; i++){
                inputCluster[i].setCentroid(calculateCentroid(inputCluster[i]));
            }
        } 

        return inputCluster;
    } //end kMeansAlgorithm method

    public static int pointBelongsToCluster(Point point, Cluster[] clusters){
        int closestCluster = clusters.length;
        double closestDistance = 999999999.999999999;
        double[] distances = new double[clusters.length];
        for(int i = 0; i < clusters.length-1; i++){
            distances[i] = Point.distanceToCentroid(point, clusters[i].getCentroid());
        }
        for(int i = 0; i < distances.length; i++){
            if(distances[i] < closestDistance){
                closestCluster = i;
                closestDistance = distances[i];
            }
        }
        //System.out.println(closestCluster);
        return closestCluster;
    } //end pointBelongsToCluster method

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
}

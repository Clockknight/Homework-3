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
                    if(closestCluster == i){ //point already in correct cluster
                        //break;
                    }
                    else{ //assign point to closest cluster
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
        double closestDistance = Double.MAX_VALUE;
        double[] distances = new double[clusters.length];
        for(int i = 0; i < clusters.length; i++){
            distances[i] = Point.distanceToCentroid(point, clusters[i].getCentroid());
        }
        for(int i = 0; i < distances.length; i++){
            if(distances[i] < closestDistance){
                closestCluster = i;
                closestDistance = distances[i];
            }
        }
        return closestCluster;
    }

    public static Point calculateCentroid(Cluster cluster){
        double[] sumValues = new double[60];

        for(int i = 0; i < cluster.getNumberOfPoints(); i++){
            for (int j = 0; j < sumValues.length; j++){
                sumValues[j] = sumValues[j] +   cluster.points.get(i).getValue(j);
            }
        }

        int point_count = cluster.getNumberOfPoints();
        double[] result_values = new double[sumValues.length];
        for (int i = 0; i < sumValues.length; i++){
            result_values[i] = sumValues[i]/point_count;
        }
        return new Point(result_values);
    }
}

package KmeansClustering;

public class Point {
    private double[] values;
    private int cluster;
    private int valuecount = 0;


    public Point(double[] in_values){
        this.values = in_values;
        this.valuecount = this.values.length;
    }

    public int length(){return this.valuecount;}

    public double getValue(int index) {
        return this.values[index];
    }

    public void setCluster(int n) {
        this.cluster = n;
    }
    
    public int getCluster() {
        return this.cluster;
    }

    protected static double distanceToCentroid(Point p, Point centroid) {
        double numbers = 0;

        for (int i = 0; i < centroid.length(); i++) {
            numbers += Math.pow(centroid.getValue(i) - p.getValue(i), 2);
        }

        return Math.sqrt(numbers);
    }

    public String toString()
    {
        String result = "(";
        for (int i = 0; i < valuecount-1; i++)
        {
            result += String.valueOf(values[i]) + ',';
        }

        return result + String.valueOf(values[valuecount-1]) + ")";
    }
}

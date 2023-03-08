package KmeansClustering;
import java.util.ArrayList;
import java.util.List;

public class Cluster {
    public List<Point> points;
	public Point centroid;
	public int id;

	public Cluster() {
		this.points = new ArrayList<Point>();
		this.centroid = null;
	}
    
    public List<Point> getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}

 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public int getNumberOfPoints(){
		int pointCounter = 0;
		for (Point point : points) {
			pointCounter += 1;
		}
		return pointCounter;
	}
}

package KmeansClustering;
import java.util.ArrayList;
import java.util.List;

public class Cluster {
    public List<Point> points;
	public Point centroid;
	public int id;
	//public int pointCounter;

    //Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList();
		this.centroid = null;
		//this.pointCounter = 0;
	}
    
    public List<Point> getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
		//this.pointCounter += 1;
	}
 
	public void setPoints(List<Point> points) {
		this.points = points;
		//this.pointCounter += points.size();
	}
 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}

	public int getNumberOfPoints(){
		int pointCounter = 0;
		for (Point point : points) {
			pointCounter += 1;
		}
		return pointCounter;
	}
}

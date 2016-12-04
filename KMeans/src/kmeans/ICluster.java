package kmeans;

/**
 * Created by Matěj Kareš on 01.12.2016.
 */
public interface ICluster {

    Cluster[] doClustering(Point[] data, int clusterCount);

    Cluster assignToCluster(Point point);
}

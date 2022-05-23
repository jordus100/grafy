package com.example.jgrafy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class GraphAnalyzer {

    public static class CohesionRef{
        public boolean cohesive;
        public boolean[] explored;
    }

    private static class QueueVertice{
        private double pathTo;
        private int number;
    }

    public static Path findShortestPath(int verticeTo, int verticeFrom, Graph graph) {

        class VerticeComparator implements Comparator<QueueVertice> {
            public int compare(QueueVertice vertice1, QueueVertice vertice2) {
                if (vertice2.pathTo < vertice1.pathTo)
                    return 1;
                else if (vertice2.pathTo > vertice1.pathTo)
                    return -1;
                return 0;
            }
        }
        double[] pathValues = new double[graph.getNumOfColumns() * graph.getNumOfRows()];
        QueueVertice[] qVertices = new QueueVertice[graph.getNumOfColumns() * graph.getNumOfRows()];
        int[] ancestors = new int[graph.getNumOfColumns() * graph.getNumOfRows()];
        Vertex[] verticeList = graph.getVertices();
        double inf = Double.POSITIVE_INFINITY;
        Path path = new Path();
        PriorityQueue<QueueVertice> queue = new PriorityQueue(graph.getNumOfColumns() * graph.getNumOfRows(), new VerticeComparator());
        for (int i = 0; i < graph.getNumOfColumns() * graph.getNumOfRows(); i++) {
            qVertices[i] = new QueueVertice();
            ancestors[i] = -1;
            qVertices[i].number = i;
            if (i != verticeFrom) {
                qVertices[i].pathTo = inf;
                pathValues[i] = inf;
            } else {
                qVertices[i].pathTo = 0;
                pathValues[i] = 0;
            }

            queue.add(qVertices[i]);

        }

        while (!queue.isEmpty()) {
            QueueVertice nowChecked = queue.poll();

            if (graph.getNeighbour(nowChecked.number, Direction.Up) != -1 && verticeList[nowChecked.number].weightUp != -1)
                if (verticeList[nowChecked.number].weightUp + nowChecked.pathTo < pathValues[graph.getNeighbour(nowChecked.number, Direction.Up)]) {
                    //System.out.print("Size before"+queue.size()+'\n');
                    queue.remove(qVertices[graph.getNeighbour(nowChecked.number, Direction.Up)]);
                    qVertices[graph.getNeighbour(nowChecked.number, Direction.Up)].pathTo = verticeList[nowChecked.number].weightUp + nowChecked.pathTo;
                    queue.add(qVertices[graph.getNeighbour(nowChecked.number, Direction.Up)]);

                    ancestors[graph.getNeighbour(nowChecked.number, Direction.Up)] = nowChecked.number;
                    pathValues[graph.getNeighbour(nowChecked.number, Direction.Up)] = verticeList[nowChecked.number].weightUp + nowChecked.pathTo;
                    //System.out.print("Size after"+queue.size()+'\n');

                }
            if (graph.getNeighbour(nowChecked.number, Direction.Down) != -1 && verticeList[nowChecked.number].weightDown != -1)
                if (verticeList[nowChecked.number].weightDown + nowChecked.pathTo < pathValues[graph.getNeighbour(nowChecked.number, Direction.Down)]) {
                    queue.remove(qVertices[graph.getNeighbour(nowChecked.number, Direction.Down)]);
                    qVertices[graph.getNeighbour(nowChecked.number, Direction.Down)].pathTo = verticeList[nowChecked.number].weightDown + nowChecked.pathTo;
                    queue.add(qVertices[graph.getNeighbour(nowChecked.number, Direction.Down)]);

                    ancestors[graph.getNeighbour(nowChecked.number, Direction.Down)] = nowChecked.number;
                    pathValues[graph.getNeighbour(nowChecked.number, Direction.Down)] = verticeList[nowChecked.number].weightDown + nowChecked.pathTo;
                }
            if (graph.getNeighbour(nowChecked.number, Direction.Right) != -1 && verticeList[nowChecked.number].weightRight != -1)
                if (verticeList[nowChecked.number].weightRight + nowChecked.pathTo < pathValues[graph.getNeighbour(nowChecked.number, Direction.Right)]) {
                    queue.remove(qVertices[graph.getNeighbour(nowChecked.number, Direction.Right)]);
                    qVertices[graph.getNeighbour(nowChecked.number, Direction.Right)].pathTo = verticeList[nowChecked.number].weightRight + nowChecked.pathTo;
                    queue.add(qVertices[graph.getNeighbour(nowChecked.number, Direction.Right)]);

                    ancestors[graph.getNeighbour(nowChecked.number, Direction.Right)] = nowChecked.number;
                    pathValues[graph.getNeighbour(nowChecked.number, Direction.Right)] = verticeList[nowChecked.number].weightRight + nowChecked.pathTo;
                }
            if (graph.getNeighbour(nowChecked.number, Direction.Left) != -1 && verticeList[nowChecked.number].weightLeft != -1)
                if (verticeList[nowChecked.number].weightLeft + nowChecked.pathTo < pathValues[graph.getNeighbour(nowChecked.number, Direction.Left)]) {
                    queue.remove(qVertices[graph.getNeighbour(nowChecked.number, Direction.Left)]);
                    qVertices[graph.getNeighbour(nowChecked.number, Direction.Left)].pathTo = verticeList[nowChecked.number].weightLeft + nowChecked.pathTo;
                    queue.add(qVertices[graph.getNeighbour(nowChecked.number, Direction.Left)]);

                    ancestors[graph.getNeighbour(nowChecked.number, Direction.Left)] = nowChecked.number;
                    pathValues[graph.getNeighbour(nowChecked.number, Direction.Left)] = verticeList[nowChecked.number].weightLeft + nowChecked.pathTo;
                }
        }
        int count = 0;
        int ancestorCursor = verticeTo;
        while (ancestorCursor != -1) {
            count++;
            ancestorCursor = ancestors[ancestorCursor];
        }
        path.verticesInOrder = new int[count];
        path.stepsValue = new double[count];
        count = 0;
        ancestorCursor = verticeTo;
        while (ancestorCursor != -1) {
            path.verticesInOrder[count] = ancestorCursor;
            count++;
            ancestorCursor = ancestors[ancestorCursor];
        }
        path.pathValue = pathValues[verticeTo];

        int[] swap = new int[count];
        for (int i = 0; i < count; i++) {
            swap[i] = path.verticesInOrder[count - 1 - i];
        }
        path.verticesInOrder = swap;

        System.out.print("\nWartosc drogi: ");
        System.out.print(path.pathValue);
        System.out.print("\nWierzchołki: ");
        for (int i = 0; i < count; i++) {
            System.out.print(swap[i] + " ");
        }
        System.out.print("\nWartość kroków: ");
        for (int i = 1; i < count; i++) {
            System.out.print(pathValues[path.verticesInOrder[i]] - pathValues[path.verticesInOrder[i - 1]] + " ");
            path.stepsValue[i] = pathValues[path.verticesInOrder[i]] - pathValues[path.verticesInOrder[i - 1]];
        }

        return path;
    }


    public static CohesionRef checkCohesion(Graph graph, int root) {
        Queue queue = new LinkedList<Integer>();
        CohesionRef cohesionData = new CohesionRef();
        cohesionData.explored = new boolean[graph.getNumOfVertices()];
        for(boolean vertex : cohesionData.explored)
            vertex = false;
        cohesionData.explored[root] = true;
        queue.add(root);
        while(!queue.isEmpty()){
            int vertex = (int) queue.remove();
            int neighbour;
            neighbour = graph.getNeighbour(vertex, Direction.Right);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightLeft != -1 && graph.getVertices()[vertex].weightRight != -1)
                    if(!cohesionData.explored[neighbour]) {
                        cohesionData.explored[neighbour] = true;
                        queue.add(neighbour);
                    }
            neighbour = graph.getNeighbour(vertex, Direction.Left);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightRight != -1 && graph.getVertices()[vertex].weightLeft != -1)
                    if(!cohesionData.explored[neighbour]) {
                        cohesionData.explored[neighbour] = true;
                        queue.add(neighbour);
                    }
            neighbour = graph.getNeighbour(vertex, Direction.Up);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightDown != -1 && graph.getVertices()[vertex].weightUp != -1)
                    if(!cohesionData.explored[neighbour]) {
                        cohesionData.explored[neighbour] = true;
                        queue.add(neighbour);
                    }
            neighbour = graph.getNeighbour(vertex, Direction.Down);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightUp != -1 && graph.getVertices()[vertex].weightDown != -1)
                    if (!cohesionData.explored[neighbour]) {
                        cohesionData.explored[neighbour] = true;
                        queue.add(neighbour);
                    }
        }
        boolean cohesive = true;
        for(boolean vertex : cohesionData.explored)
            if(vertex == false) cohesive = false;
        cohesionData.cohesive = cohesive;
        return cohesionData;
    }

    public static CohesionRef checkCohesion(Graph graph){
        return checkCohesion(graph, 0);
    }
}

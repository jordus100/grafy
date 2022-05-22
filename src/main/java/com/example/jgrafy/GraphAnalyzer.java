package com.example.jgrafy;

import java.util.LinkedList;
import java.util.Queue;

class GraphAnalyzer {

    public static class CohesionRef{
        public boolean cohesive;
        public boolean[] explored;
    }

    public static Path findShortestPath(int verticeTo, int verticeFrom, Graph graph) {
        Path path = new Path();


        return path;
    }

    public static CohesionRef checkCohesion(Graph graph, int root) {
        Queue queue = new LinkedList<Integer>();
        CohesionRef cohesionData = new CohesionRef();
        cohesionData.explored = new boolean[graph.getNumOfVertices()];
        for(boolean vertex : cohesionData.explored)
            vertex = false;
        cohesionData.explored[root] = true;
        while(!queue.isEmpty()){
            int vertex = (int) queue.remove();
            int neighbour;
            neighbour = graph.getNeighbour(vertex, Direction.Right);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightLeft != -1 && graph.getVertices()[vertex].weightRight != -1) {
                    cohesionData.explored[vertex] = true;
                    queue.add(neighbour);
                }
            neighbour = graph.getNeighbour(vertex, Direction.Left);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightRight != -1 && graph.getVertices()[vertex].weightLeft != -1) {
                    cohesionData.explored[vertex] = true;
                    queue.add(neighbour);
                }
            neighbour = graph.getNeighbour(vertex, Direction.Up);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightDown != -1 && graph.getVertices()[vertex].weightUp != -1) {
                    cohesionData.explored[vertex] = true;
                    queue.add(neighbour);
                }
            neighbour = graph.getNeighbour(vertex, Direction.Down);
            if(neighbour != -1)
                if(graph.getVertices()[neighbour].weightUp != -1 && graph.getVertices()[vertex].weightDown != -1) {
                    cohesionData.explored[vertex] = true;
                    queue.add(neighbour);
                }
        }
        boolean cohesive = true;
        for(boolean vertex : cohesionData.explored)
            if(vertex == false) cohesive = false;
        cohesionData.cohesive = cohesive;
        return cohesionData;
    }
}

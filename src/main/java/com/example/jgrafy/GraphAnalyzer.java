package com.example.jgrafy;

import java.util.LinkedList;
import java.util.Queue;

class GraphAnalyzer {

    public class CohesionRef{
        public boolean cohesive;
        public boolean[] explored;
    }

    public Path findShortestPath(int verticeTo, int verticeFrom, Graph graph) {
        Path path = new Path();


        return path;
    }

    public CohesionRef checkCohesion(Graph graph, int root) {
        Queue queue = new LinkedList<Integer>();
        CohesionRef cohesionData = new CohesionRef();
        cohesionData.explored = new boolean[graph.getNumOfVertices()];

        return 0;
    }
}

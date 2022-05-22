package com.example.jgrafy;


public class Main {

    public static void main(String[] args) {
        try {
            Graph graph = GraphGenerator.readGraphFromFile("C:\\Users\\Jordu\\Documents\\Programming\\F1NextRace\\JGrafy\\data\\mygraph");
            System.out.println(graph.getNumOfRows());
            System.out.println(graph.getNumOfColumns());
            for(int i=0; i<graph.getNumOfRows(); i++){
                for(int n=0; n< graph.getNumOfColumns(); n++)
                    System.out.print(" " + n + " " + graph.getVertices()[graph.getNumOfColumns()*i+n].weightRight);
                System.out.print("\n");
            }
            GraphAnalyzer.CohesionRef cohesionRef = GraphAnalyzer.checkCohesion(graph, 0);
            System.out.println("\n" + cohesionRef.cohesive);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}


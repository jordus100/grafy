package com.example.jgrafy;


import java.util.ArrayList;
import java.util.List;

public class Main {

    static private Graph graph;
    static private List<Path> pathList;
    public static void main(String[] args) {
        /*try {
            Graph graph = GraphGenerator.readGraphFromFile("C:\\Users\\Jordu\\Documents\\Programming\\F1NextRace\\JGrafy\\data\\mygraph2");
            System.out.println(graph.getNumOfRows());
            System.out.println(graph.getNumOfColumns());
            for(int i=0; i<graph.getNumOfRows(); i++){
                for(int n=0; n< graph.getNumOfColumns(); n++)
                    System.out.print(" " + n + " " + graph.getVertices()[graph.getNumOfColumns()*i+n].weightRight);
                System.out.print("\n");
            }
            GraphAnalyzer.CohesionRef cohesionRef = GraphAnalyzer.checkCohesion(graph, 0);
            System.out.println("\n" + cohesionRef.cohesive);
            //graph.saveToFile("C:\\Users\\Jordu\\Documents\\Programming\\F1NextRace\\JGrafy\\data\\mygraph3");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }*/
        try {
           // Graph graph = GraphGenerator.readGraphFromFile("C:\\Users\\posia\\Desktop\\JIMP2\\test2.txt");
            //Graph graph =GraphGenerator.generateCohesiveGraph(15,15);

            Graph graph =GraphGenerator.generateCohesiveGraph(5,5);

            System.out.print("Wczytano \n");
            GraphAnalyzer.findShortestPath(224,0,graph);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static Graph getGraph()
    {
        return graph;
    }

    public static void setGraph(Graph newGraph)
    {
        graph=newGraph;
    }

    public static void addPath(Path path)
    {
        if(pathList == null)
        {
            pathList=new ArrayList<>();
        }
        pathList.add(path);
    }

    public static void removePath(Path path)
    {
        pathList.remove(path);
    }

    public static List<Path> getPath()
    {
        return pathList;
    }

}


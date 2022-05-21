package com.example.jgrafy;


public class Main {

    public static void main(String[] args) {
        try {
            Graph graph = GraphGenerator.readGraphfromFile("C:\\Users\\Jordu\\Documents\\Programming\\F1NextRace\\JGrafy\\data\\mygraph");
            System.out.println(graph.getNumOfRows());
            System.out.println(graph.getNumOfColumns());
            System.out.println(graph.getVertices()[0].weightRight);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}


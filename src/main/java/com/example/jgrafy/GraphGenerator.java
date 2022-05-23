package com.example.jgrafy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

abstract class GraphGenerator {

    public static Graph readGraphFromFile(String filePath) throws Exception {
        Scanner sc = new Scanner(new File(filePath)).useDelimiter(Pattern.compile("\\s+"));
        int numOfRows = Integer.parseInt(sc.next(Pattern.compile("\\d+")));
        int numOfCols = Integer.parseInt(sc.next(Pattern.compile("\\d+")));
        Graph graph = new Graph(numOfRows, numOfCols);
        //now reading the rows
        sc.nextLine();
        int vertexNum = 0;
        while (vertexNum <= graph.getNumOfRows() * graph.getNumOfColumns()) {
            System.out.println(vertexNum);
            String connection = sc.findInLine(Pattern.compile("\\d+ :\\d.\\d+"));
            if (connection == null) {
                try {
                    sc.nextLine();
                } catch (NoSuchElementException e) {
                    break;
                }
                vertexNum++;
                continue;
            }
            String[] split = Pattern.compile(":").split(connection);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            int vertexConnection = Integer.parseInt(split[0]);
            double weight = Double.parseDouble(split[1]);
            graph.setVertice(vertexNum, graph.getDirection(vertexNum, vertexConnection), weight);
        }
        sc.close();
        return graph;

    }

    public static boolean connection(double chance) {
        double rand = new Random().nextFloat();
        if (chance > rand)
            return true;
        else
            return false;
    }

    public static double random() {
        double rand = new Random().nextFloat();
        return rand;
    }


    public static Graph generateRandomGraph(int numberOfRows, int numberOfColumns) {
        Graph graph = new Graph(numberOfRows, numberOfColumns);
        double connectionChance = 0.8;
        for (int i = 0; i < numberOfRows * numberOfColumns; i++) {
            if (graph.getNeighbour(i, Direction.Up) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Up, random());
            if (graph.getNeighbour(i, Direction.Down) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Down, random());
            if (graph.getNeighbour(i, Direction.Right) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Right, random());
            if (graph.getNeighbour(i, Direction.Left) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Left, random());
        }
        return graph;
    }

    public static Graph generateCohesiveGraph(int numberOfRows, int numberOfColumns) {
        Graph graph = new Graph(numberOfRows, numberOfColumns);
        for (int i = 0; i < numberOfRows * numberOfColumns; i++) {
            if (graph.getNeighbour(i, Direction.Up) != -1)
                graph.setVertice(i, Direction.Up, random());
            if (graph.getNeighbour(i, Direction.Down) != -1)
                graph.setVertice(i, Direction.Down, random());
            if (graph.getNeighbour(i, Direction.Right) != -1)
                graph.setVertice(i, Direction.Right, random());
            if (graph.getNeighbour(i, Direction.Left) != -1)
                graph.setVertice(i, Direction.Left, random());
        }
        return graph;
    }

    public static Graph generateCohesiveRandomGraph(int numberOfRows, int numberOfColumns) {
        Graph graph = new Graph(numberOfRows, numberOfColumns);
        double connectionChance = 0.8;
        for (int i = 0; i < numberOfRows * numberOfColumns; i++) {
            if (graph.getNeighbour(i, Direction.Up) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Up, random());
            if (graph.getNeighbour(i, Direction.Down) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Down, random());
            if (graph.getNeighbour(i, Direction.Right) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Right, random());
            if (graph.getNeighbour(i, Direction.Left) != -1 && connection(connectionChance) == true)
                graph.setVertice(i, Direction.Left, random());
        }
        int j = -1;
        while (false == GraphAnalyzer.checkCohesion(graph, 0).cohesive) {

            for (int i = 0; i < numberOfRows * numberOfColumns; i++) {
                j = i;
                System.out.print(GraphAnalyzer.checkCohesion(graph, 0).explored[i]);
                if (GraphAnalyzer.checkCohesion(graph, 0).explored[i] == false) {
                    System.out.print("\nNiespojny " + j);
                    break;
                }
            }
            if (graph.getNeighbour(j, Direction.Up) != -1) {
                System.out.print("\n HERE ");
                graph.setVertice(j, Direction.Up, random());
                graph.setVertice(graph.getNeighbour(j,Direction.Up), Direction.Down, random());
            }
            if (graph.getNeighbour(j, Direction.Left) != -1) {
                graph.setVertice(j, Direction.Left, random());
                graph.setVertice(graph.getNeighbour(j,Direction.Left), Direction.Right, random());

            }
        }
        return graph;
    }
        public abstract Graph generateGraph(int numOfRows, int numOfCols);

}

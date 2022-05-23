package com.example.jgrafy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
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
        while(vertexNum <= graph.getNumOfRows()* graph.getNumOfColumns()){
            System.out.println(vertexNum);
            String connection = sc.findInLine(Pattern.compile("\\d+ :\\d.\\d+"));
            if(connection == null){
                try{
                    sc.nextLine();
                } catch (NoSuchElementException e){
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
    public abstract Graph generateGraph(int numOfRows, int numOfCols);
}

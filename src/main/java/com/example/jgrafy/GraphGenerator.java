package com.example.jgrafy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

class GraphGenerator {

    public static Graph readGraphfromFile(String filePath) throws Exception {
        Scanner sc = new Scanner(new File(filePath));
        int numOfCols = Integer.parseInt(sc.next(Pattern.compile("\\d+")));
        int numOfRows = Integer.parseInt(sc.next(Pattern.compile("\\d+")));
        Graph graph = new Graph(numOfRows, numOfCols);
        //now reading the rows
        sc.nextLine();
        int vertexNum = 0;
        while(sc.hasNext(Pattern.compile("(\\d+)\\s:(\\d).(\\d+)"))){
            System.out.println(vertexNum);
            String connection = sc.findInLine(Pattern.compile("\\d+ :\\d.\\d+"));
            if(connection == null){
                sc.nextLine();
                vertexNum++;
                continue;
            }
            String[] split = Pattern.compile(":").split(connection);
            int vertexConnection = Integer.parseInt(split[0]);
            double weight = Integer.parseInt(split[1]);
            graph.setVertice(vertexNum, graph.getDirection(vertexNum, vertexConnection), weight);

        }
        return graph;
    }
}

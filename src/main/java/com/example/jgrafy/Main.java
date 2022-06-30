package com.example.jgrafy;


import java.util.ArrayList;
import java.util.List;

public class Main {

    static private Graph graph;
    static private List<Path> pathList;
    public static void main(String[] args){
        Application.Main(new String[0]);
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
        if(pathList==null)
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


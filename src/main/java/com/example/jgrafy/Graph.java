package com.example.jgrafy;

class Graph {

    int numOfColumns;
    int numOfRows;
    Vertex vertices[];

    public int getNeighbour(int verticeNum, Direction direction) {
        if (direction.compareTo(Direction.Left) == 1) {
            if ((verticeNum % numOfColumns) != 0)
                return verticeNum - 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Right) == 1) {
            if (((verticeNum + 1) % numOfColumns) != 0)
                return verticeNum + 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Up) == 1) {
            if (verticeNum >= numOfColumns)
                return verticeNum + numOfColumns;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Down) == 1) {
            if ((verticeNum / numOfColumns) + 1 != numOfRows)
                return verticeNum - numOfColumns;
            else
                return -1;
        }
        return -2;
    }


}

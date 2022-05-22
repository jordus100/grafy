package com.example.jgrafy;

class Graph {

    private int numOfColumns;

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    private int numOfRows;

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertice(int vertexNum, Direction weightDirection, double value) {
        switch (weightDirection) {
            case Down:
                vertices[vertexNum].weightDown = value;
                break;
            case Up:
                vertices[vertexNum].weightUp = value;
                break;
            case Left:
                vertices[vertexNum].weightLeft = value;
                break;
            case Right:
                vertices[vertexNum].weightRight = value;
                break;
        }
    }

    private Vertex[] vertices;

    public int getNumOfVertices(){
        return numOfRows*numOfColumns;
    }

    public Graph(int numOfRows, int numOfColumns){
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        vertices = new Vertex[numOfRows*numOfColumns];
        for(int i = 0; i<vertices.length; i++){
            vertices[i] = new Vertex();
        }
    }

    public Direction getDirection(int vertexFrom, int vertexTo){
        if(vertexFrom + 1 == vertexTo)
            return Direction.Right;
        else if(vertexFrom - 1 == vertexTo)
            return Direction.Left;
        else if(vertexFrom + numOfColumns == vertexTo)
            return Direction.Down;
        else if(vertexFrom - numOfColumns == vertexTo)
            return Direction.Up;
        else return Direction.Invalid;
    }

    public int getNeighbour(int verticeNum, Direction direction) {
        if (direction.compareTo(Direction.Left) == 0) {
            if ((verticeNum % numOfColumns) != 0)
                return verticeNum - 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Right) == 0) {
            if (((verticeNum + 1) % numOfColumns) != 0)
                return verticeNum + 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Up) == 0) {
            if (verticeNum >= numOfColumns)
                return verticeNum - numOfColumns;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Down) == 0) {
            if ((verticeNum / numOfColumns) + 1 != numOfRows)
                return verticeNum + numOfColumns;
            else
                return -1;
        }
        return -2;
    }


}

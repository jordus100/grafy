#ifndef CGRAFY_DATA_STRUCTS_H
#define CGRAFY_DATA_STRUCTS_H

typedef struct vertex{
    int vertexNumber;
    double weightLeft;
    double weightRight;
    double weightUp;
    double weightDown;
} vertex;

typedef struct graph{
    int numberOfCols;
    int numberOfRows;
    vertex* vertices;
} graph;

#endif //CGRAFY_DATA_STRUCTS_H

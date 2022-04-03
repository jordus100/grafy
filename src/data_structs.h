#ifndef CGRAFY_DATA_STRUCTS_H
#define CGRAFY_DATA_STRUCTS_H

typedef struct graph{
    int numberOfCols;
    int numberOfRows;
    vertex vertices[];
} graph;

typedef struct vertex{
    int vertexNumber;
    double weightLeft;
    double weightRight;
    double weightUp;
    double weightDown;
} vertex;

#endif //CGRAFY_DATA_STRUCTS_H

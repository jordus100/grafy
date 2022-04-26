#include <stdio.h>
#include <stdlib.h>

#include "file_handler.h"
#include "data_structs.h"

#define MAX_GRAPH_SIZE 100

int saveGraphToFile(graph *thisGraph, char *fileName) {
    FILE *file;
    file = fopen(fileName, "w");
    int totalVertices = thisGraph->numberOfCols * thisGraph->numberOfRows;
    fprintf(file, "%d %d", thisGraph->numberOfRows, thisGraph->numberOfCols);

    for (int i = 0; i < totalVertices; i++) {
        fprintf(file, "\n\t ");
        if (thisGraph->vertices[i].weightUp > 0) {
            fprintf(file, "%d :%lf ", i-thisGraph->numberOfCols, thisGraph->vertices[i].weightUp);
        }
        if (thisGraph->vertices[i].weightDown > 0) {
            fprintf(file, "%d :%lf ", i+thisGraph->numberOfCols, thisGraph->vertices[i].weightDown);
        }
        if (thisGraph->vertices[i].weightRight > 0) {
            fprintf(file, "%d :%lf ", i+1, thisGraph->vertices[i].weightRight);
        }
        if (thisGraph->vertices[i].weightLeft > 0) {
            fprintf(file, "%d :%lf ", i-1, thisGraph->vertices[i].weightLeft);
        }
    }
    return 0;
}

enum WeightDirection getDirectionOfWeight(int vertexFrom, int vertexTo, int numOfCols){
    if(vertexFrom+1 == vertexTo) return right;
    else if(vertexFrom-1 == vertexTo) return left;
    else if(vertexFrom-numOfCols == vertexTo) return up;
    else if(vertexFrom+numOfCols == vertexTo) return down;
    else return error;
}

int parseGraphFromFile(graph *thisGraph, char *fileName) {
    FILE *file = fopen(fileName, "r");
    if (file==NULL) return 1;
    char* line = malloc(1000*sizeof(line));
    line = fgets(line, 1000, file);
    sscanf(line, "%d %d", &thisGraph->numberOfRows, &thisGraph->numberOfCols);

    int totalVertices = thisGraph->numberOfCols * thisGraph->numberOfRows;
    thisGraph->vertices = malloc(totalVertices*sizeof(*(thisGraph->vertices)));
int i;
  for(i=0; i<totalVertices; i++){
    thisGraph->vertices[i].weightLeft = -1;
thisGraph->vertices[i].weightRight =-1;
    thisGraph->vertices[i].weightDown = -1;
    thisGraph->vertices[i].weightUp = -1;
  }

if(thisGraph->numberOfRows > MAX_GRAPH_SIZE || thisGraph->numberOfCols > MAX_GRAPH_SIZE)    {
        fprintf(stderr, "Error: the graph defined in file %s is too large.\n", fileName);
        return 1;
    }

    int vertex[4];
    double weight[4];
    int readConnectionsNum;
    int n;

    for (i = 0; i < totalVertices; i++) {
        line = fgets(line, 1000, file);
        if(line == NULL){
            fprintf(stderr, "Error: the graph defined in file %s doesn't contained the declared number of vertices.\n", fileName);
            return 1;
        }
        readConnectionsNum = sscanf(line, "\t %d :%lf %d :%lf %d :%lf %d :%lf", &vertex[0], &weight[0], &vertex[1], &weight[1], &vertex[2], &weight[2], &vertex[3], &weight[3]) / 2;
        for(n=0; n<readConnectionsNum; n++) {
            if (weight[n] < 0 || weight[n] > 1) {
                fprintf(stderr, "Error: the graph defined in file %s has an invalid weight of vertex number %d.\n",
                        fileName, vertex[n]);
                return 1;
            }
            switch (getDirectionOfWeight(i, vertex[n], thisGraph->numberOfCols)) {
                case right:
                    thisGraph->vertices[i].weightRight = weight[n];
                    break;
                case left:
                    thisGraph->vertices[i].weightLeft = weight[n];
                    break;
                case up:
                    thisGraph->vertices[i].weightUp = weight[n];
                    break;
                case down:
                    thisGraph->vertices[i].weightDown = weight[n];
                    break;
                case error: {
                    fprintf(stderr, "Error: the graph's vertices in the file %s are not correct.\n", fileName);
                    return 1;
                    break;
                }
            }
        }
    }
    return 0;
}
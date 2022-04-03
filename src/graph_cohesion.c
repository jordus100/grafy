#include <stdlib.h>
#include <stdio.h>
#include "data_structs.h"
#include "graph_cohesion.h"

typedef struct vertexQueue{
    int vertexNum;
    struct vertexQueue* next;
} vertexQueue;

void vertexQueueAdd(vertexQueue* verQueue, int vertexNumber){
    if(verQueue->next == NULL) {
        vertexQueue *newElement = malloc(sizeof(*newElement));
        newElement->vertexNum = vertexNumber;
        verQueue->next = newElement;
        newElement->next = NULL;
    }
    else{
        vertexQueueAdd(verQueue->next, vertexNumber);
    }
}

int vertexQueueRemove(vertexQueue* verQueue){
    int vertexNum = verQueue->vertexNum;
    verQueue = verQueue->next;
    return vertexNum;
}

int* getVertexNeighbors(graph* thisGraph, int vertex){
    int *neighbors = malloc(4*sizeof(neighbors));
    int i=0;
    int totalVertices = thisGraph->numberOfRows * thisGraph->numberOfCols;
    if((vertex+1) <= (totalVertices-1) && thisGraph->vertices[vertex+1].weightLeft>0){
        neighbors[i] = vertex+1;
        i++;
    }
    if((vertex-1 >= 0) && thisGraph->vertices[vertex-1].weightRight>0){
        neighbors[i] = vertex-1;
        i++;
    }
    if((vertex - thisGraph->numberOfCols) > 0 && thisGraph->vertices[vertex - thisGraph->numberOfCols].weightDown > 0){
        neighbors[i] = vertex - thisGraph->numberOfCols;
        i++;
    }
    if((vertex + thisGraph->numberOfCols) <= (totalVertices-1) && thisGraph->vertices[vertex + thisGraph->numberOfCols].weightUp > 0){
    neighbors[i] = vertex - thisGraph->numberOfCols;
    i++;
    }
    for(i=i+1; i<4; i++){
        neighbors[i] = -1;
    }
    return neighbors;
};

int isGraphCohesive(graph* thisGraph){
    vertexQueue *verQueue = malloc(sizeof(*verQueue));
    verQueue->vertexNum = 0;

    int *searched = malloc( thisGraph->numberOfRows * thisGraph->numberOfCols * sizeof(*searched));

    searched[0] = 1;

    int vertex;
    int *neighbors;
    int i;
    while(verQueue != NULL){
        printf("%d\n", verQueue->vertexNum);
        vertex = vertexQueueRemove(verQueue);
        neighbors = getVertexNeighbors(thisGraph, vertex);
        for(i=0; i<4; i++){
            if(neighbors[i]!=-1){
                if(searched[neighbors[i]]!=1){
                    vertexQueueAdd(verQueue, neighbors[i]);
                    searched[neighbors[i]]=1;
                }
            }
        }
    }
    for(i=0; i<thisGraph->numberOfRows * thisGraph->numberOfCols; i++){
        if(searched[i]==0)
            return 0;
    }
    return 1;
}


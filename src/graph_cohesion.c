#include <stdlib.h>
#include <stdio.h>
#include "data_structs.h"
#include "graph_cohesion.h"

typedef struct vertexQueue{
    int vertexNum;
    struct vertexQueue* next;
} vertexQueue;

void vertexQueueAdd(vertexQueue** verQueue, int vertexNumber){
    if((*verQueue) == NULL) {
        printf("adding\n");
        vertexQueue *newElement = malloc(sizeof(*newElement));
        newElement->vertexNum = vertexNumber;
        *verQueue = newElement;
        newElement->next = NULL;
    }
    else{
        vertexQueueAdd((&(*verQueue)->next), vertexNumber);
    }
}

int vertexQueueRemove(vertexQueue** verQueue){
    int vertexNum = (*verQueue)->vertexNum;
    vertexQueue *verQueueCopy = (*verQueue);
    *verQueue = (*verQueue)->next;
    free(verQueueCopy);
    return vertexNum;
}

int* getVertexNeighbors(graph* thisGraph, int vertex){
    int *neighbors = malloc(4*sizeof(*neighbors));
    int i;
    int totalVertices = thisGraph->numberOfRows * thisGraph->numberOfCols;
    for(i=0; i<4; i++){
        neighbors[i] = -1;
    }
    i=0;
    if(((vertex+1) <= (totalVertices-1) && thisGraph->vertices[vertex+1].weightLeft>0) || thisGraph->vertices[vertex].weightRight>0){
        neighbors[i] = vertex+1;
        i++;
    }
    if(((vertex-1 >= 0) && thisGraph->vertices[vertex-1].weightRight>0) || thisGraph->vertices[vertex].weightRight>0){
        neighbors[i] = vertex-1;
        i++;
    }
    if(((vertex - thisGraph->numberOfCols) > 0 && thisGraph->vertices[vertex - thisGraph->numberOfCols].weightDown > 0) || thisGraph->vertices[vertex].weightUp>0){
        neighbors[i] = vertex - thisGraph->numberOfCols;
        i++;
    }
    if(((vertex + thisGraph->numberOfCols) <= (totalVertices-1) && thisGraph->vertices[vertex + thisGraph->numberOfCols].weightUp > 0) || thisGraph->vertices[vertex].weightDown>0){
        neighbors[i] = vertex + thisGraph->numberOfCols;
    }
    return neighbors;
}

int isGraphCohesive(graph* thisGraph){
    vertexQueue *verQueue = malloc(sizeof(*verQueue));
    verQueue->vertexNum = 0;
    verQueue->next = NULL;
    int i;
    int totalVertices = thisGraph->numberOfRows * thisGraph->numberOfCols;
    int *searched = malloc( totalVertices * sizeof(*searched));
    for(i=0; i<totalVertices; i++)
        searched[i] = 0;
    searched[0] = 1;

    int vertex;
    int *neighbors;
    while(verQueue != NULL){
        vertex = vertexQueueRemove(&verQueue);
        printf("%d\n", vertex);
        neighbors = getVertexNeighbors(thisGraph, vertex);
        for(i=0; i<4; i++){
            if(neighbors[i]!=-1){
                if(searched[neighbors[i]]!=1){
                    printf("neighbor: %d", neighbors[i]);
                    vertexQueueAdd(&verQueue, neighbors[i]);
                    searched[neighbors[i]]=1;
                }
            }
        }
    }
    for(i=0; i<totalVertices; i++){
        if(searched[i]==0)
            return 0;
    }
    return 1;
}


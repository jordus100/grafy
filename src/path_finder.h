#ifndef CGRAFY_PATH_FINDER_H
#define CGRAFY_PATH_FINDER_H
#include "data_structs.h"

typedef struct queueVertice{
    int verticeNumber;
    double distanceTo;
    struct queueVertice* next;
} queueVertice;

double findShortestPath(graph* , int , int ,int );
void checkNeighbour(int*,double*,graph*,int,double,queueVertice*);
void change(queueVertice*,int,double);
void printPath(int* ,int ,int,graph*);
#endif //CGRAFY_PATH_FINDER_H

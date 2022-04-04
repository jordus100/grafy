#ifndef CGRAFY_PATH_FINDER_H
#define CGRAFY_PATH_FINDER_H

#include "data_structs.h"
void checkNeighbour(int *, double*, graph*, int, double);

double findShortestPath(graph* thisGraph, int verticeFrom, int verticeTo);

#endif //CGRAFY_PATH_FINDER_H

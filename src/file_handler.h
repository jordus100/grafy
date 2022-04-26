#ifndef CGRAFY_FILE_HANDLER_H
#define CGRAFY_FILE_HANDLER_H
#include "data_structs.h"

enum WeightDirection {right, left, up, down, error};

enum WeightDirection getDirectionOfWeight(int vertexFrom, int vertexTo, int numOfCols);
int saveGraphToFile(graph* thisGraph,char* fileName);
int parseGraphFromFile(graph* thisGraph,char* fileName);

#endif //CGRAFY_FILE_HANDLER_H


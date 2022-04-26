#include "data_structs.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#include "graph_generator.h"
#include "graph_cohesion.h"

#define PROB_OF_VERTEX_EXISTING 0.5

graph* generateGraph(int numOfRows,int numOfColumns,int mode){
  srand(0);
  graph* thisGraph;
  thisGraph=malloc(sizeof *thisGraph);
  thisGraph->numberOfCols=numOfColumns;
  thisGraph->numberOfRows=numOfRows;
  int totalVertices=thisGraph->numberOfCols*thisGraph->numberOfRows;
  thisGraph->vertices=malloc(totalVertices * sizeof (*(thisGraph->vertices)));
  int i;
  if(mode==1) /* every vertex is connected with all of its neighbours, the generated graph is cohesive */
  {
    for(i=0;i<totalVertices;i++)
    {

      if(i>numOfColumns-1)
        thisGraph->vertices[i].weightUp=(double)rand() / (double)RAND_MAX;
      else
        thisGraph->vertices[i].weightUp=-1;

      if(i%numOfColumns!=0)
        thisGraph->vertices[i].weightLeft=(double)rand() / (double)RAND_MAX;
      else
        thisGraph->vertices[i].weightLeft=-1;

      if((i+1)%numOfColumns!=0)
        thisGraph->vertices[i].weightRight=(double)rand() / (double)RAND_MAX;
      else
        thisGraph->vertices[i].weightRight=-1;

      if((totalVertices-numOfColumns)>i)
        thisGraph->vertices[i].weightDown=(double)rand() / (double)RAND_MAX;
      else
        thisGraph->vertices[i].weightDown=-1;
    }
    return thisGraph;
  }
    if (mode == 2) {
        for(i=0; i < totalVertices; i++){
            thisGraph->vertices[i].weightDown = -1;
            thisGraph->vertices[i].weightUp = -1;
            thisGraph->vertices[i].weightLeft = -1;
            thisGraph->vertices[i].weightRight = -1;
        }
        i = rand() % totalVertices;
        int n;
        for (n = 0; n < totalVertices*1.5; n++) {
            int direction = rand() % 4;
                if ((i > numOfColumns - 1) && direction==0) {
                    thisGraph->vertices[i].weightUp = (double) rand() / (double) RAND_MAX;
                    thisGraph->vertices[i - numOfColumns].weightDown = (double) rand() / (double) RAND_MAX;
                    i=i-numOfColumns;
                }
                if ((i % numOfColumns != 0) && direction==1) {
                    thisGraph->vertices[i].weightLeft = (double) rand() / (double) RAND_MAX;
                    thisGraph->vertices[i-1].weightRight = (double) rand() / (double) RAND_MAX;
                    i=i-1;
                }
                if (((i + 1) % numOfColumns != 0) && direction==2) {
                    thisGraph->vertices[i].weightRight = (double) rand() / (double) RAND_MAX;
                    thisGraph->vertices[i-1].weightLeft = (double) rand() / (double) RAND_MAX;
                    i=i+1;
                }
                if (((totalVertices - numOfColumns) > i) && direction==3) {
                    thisGraph->vertices[i].weightDown = (double) rand() / (double) RAND_MAX;
                    thisGraph->vertices[i + numOfColumns].weightUp = (double) rand() / (double) RAND_MAX;
                    i=i+numOfColumns;
                }
            }
        return thisGraph;
    }
    if (mode == 3) {
        for(i=0; i < totalVertices; i++){
            thisGraph->vertices[i].weightDown = -1;
            thisGraph->vertices[i].weightUp = -1;
            thisGraph->vertices[i].weightLeft = -1;
            thisGraph->vertices[i].weightRight = -1;
        }
        i = rand() % totalVertices;
        int n;
        for (n = 0; n < totalVertices*2.3; n++) {
            int direction = rand() % 4;
            if ((i > numOfColumns - 1) && direction==0) {
                thisGraph->vertices[i].weightUp = (double) rand() / (double) RAND_MAX;
                i=i-numOfColumns;
            }
            if ((i % numOfColumns != 0) && direction==1) {
                thisGraph->vertices[i].weightLeft = (double) rand() / (double) RAND_MAX;
                i=i-1;
            }
            if (((i + 1) % numOfColumns != 0) && direction==2) {
                thisGraph->vertices[i].weightRight = (double) rand() / (double) RAND_MAX;
                i=i+1;
            }
            if (((totalVertices - numOfColumns) > i) && direction==3) {
                thisGraph->vertices[i].weightDown = (double) rand() / (double) RAND_MAX;
                i=i+numOfColumns;
            }
        }
        return thisGraph;
    }
}
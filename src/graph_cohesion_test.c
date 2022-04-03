#include <stdio.h>
#include <stdlib.h>
#include "graph_cohesion.h"
#include "data_structs.h"

int main(){
    graph* thisGraph = malloc(sizeof(graph));
    thisGraph->numberOfRows = 2;
    thisGraph->numberOfCols = 2;
    thisGraph->vertices = malloc(4*sizeof(thisGraph->vertices));

    thisGraph->vertices[0].weightRight = 0.5;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = 0;
    thisGraph->vertices[0].weightUp = 0;

    thisGraph->vertices[1].weightRight = 0;
    thisGraph->vertices[1].weightDown = 0.3;
    thisGraph->vertices[1].weightLeft = 0.5;
    thisGraph->vertices[1].weightUp = 0;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = 0;
    thisGraph->vertices[2].weightLeft = 0;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = 0;
    thisGraph->vertices[3].weightDown = 0;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0.3;

    int i = isGraphCohesive(thisGraph);

    printf("%d", i);
}


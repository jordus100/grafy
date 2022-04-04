#include <stdio.h>
#include <stdlib.h>
#include "graph_cohesion.h"
#include "data_structs.h"

int main(){
    graph *thisGraph = malloc(sizeof(*thisGraph));
    thisGraph->numberOfRows = 2;
    thisGraph->numberOfCols = 2;
    thisGraph->vertices = malloc(4*sizeof(*(thisGraph->vertices)));
    int i;
/*/
    // one vertex has no connection from or to any other vertex - test case #1
    thisGraph->vertices[0].weightRight = 0;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = 0;
    thisGraph->vertices[0].weightUp = 0;

    thisGraph->vertices[1].weightRight = 0;
    thisGraph->vertices[1].weightDown = 0;
    thisGraph->vertices[1].weightLeft = 0;
    thisGraph->vertices[1].weightUp = 0;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = 0;
    thisGraph->vertices[2].weightLeft = 0;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = 0;
    thisGraph->vertices[3].weightDown = 0;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0;

    i = isGraphCohesive(thisGraph);
    printf("test 1: %d\n", i);

    // one vertex has only connections TO other vertices but no connections FROM other vertices - test case #2
    thisGraph->vertices[0].weightRight = 0;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = 0;
    thisGraph->vertices[0].weightUp = 0;

    thisGraph->vertices[1].weightRight = 0;
    thisGraph->vertices[1].weightDown = 0.5;
    thisGraph->vertices[1].weightLeft = 0.3;
    thisGraph->vertices[1].weightUp = 0;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = 0;
    thisGraph->vertices[2].weightLeft = 0;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = 0;
    thisGraph->vertices[3].weightDown = 0;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0;

    i = isGraphCohesive(thisGraph);
    printf("test 2: %d\n", i);

    // one vertex has only connections FROM other vertices but no connections TO other vertices - test case #2
    thisGraph->vertices[0].weightRight = 0.5;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = 0;
    thisGraph->vertices[0].weightUp = 0;

    thisGraph->vertices[1].weightRight = 0;
    thisGraph->vertices[1].weightDown = 0;
    thisGraph->vertices[1].weightLeft = 0;
    thisGraph->vertices[1].weightUp = 0;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = 0;
    thisGraph->vertices[2].weightLeft = 0;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = 0;
    thisGraph->vertices[3].weightDown = 0;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0.3;

    i = isGraphCohesive(thisGraph);
    printf("test 3: %d\n", i);

    // every vertex is connected with others in both ways - test case #3
    thisGraph->vertices[0].weightRight = 0.5;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = 0;
    thisGraph->vertices[0].weightUp = 0;

    thisGraph->vertices[1].weightRight = 0;
    thisGraph->vertices[1].weightDown = 0.5;
    thisGraph->vertices[1].weightLeft = 0.3;
    thisGraph->vertices[1].weightUp = 0;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = 0;
    thisGraph->vertices[2].weightLeft = 0;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = 0;
    thisGraph->vertices[3].weightDown = 0;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0.3;
    i = isGraphCohesive(thisGraph);
    printf("test 4: %d\n", i);*/

        thisGraph->vertices[0].weightRight = 0.5;
    thisGraph->vertices[0].weightDown = 0.3;
    thisGraph->vertices[0].weightLeft = -1;
    thisGraph->vertices[0].weightUp = -1;

    thisGraph->vertices[1].weightRight = -1;
    thisGraph->vertices[1].weightDown = 0.5;
    thisGraph->vertices[1].weightLeft = 0.3;
    thisGraph->vertices[1].weightUp = -1;

    thisGraph->vertices[2].weightRight = 0.5;
    thisGraph->vertices[2].weightDown = -1;
    thisGraph->vertices[2].weightLeft = -1;
    thisGraph->vertices[2].weightUp = 0.3;

    thisGraph->vertices[3].weightRight = -1;
    thisGraph->vertices[3].weightDown = -1;
    thisGraph->vertices[3].weightLeft = 0.5;
    thisGraph->vertices[3].weightUp = 0.3;
    double j=0;
    j = findShortestPath(thisGraph,0,2);
    printf("test 5: %lf\n", j);
    free(thisGraph->vertices);
    free(thisGraph);
}


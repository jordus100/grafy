#include "file_handler.h"
#include "graph_generator.h"
#include "path_finder.h"
#include <stdio.h>
#include <stdlib.h>

void printGraph(graph *thisGraph) {
  int i, n;
  for (int i = 0; i < thisGraph->numberOfRows; i++) {
    printf("\n");
    for (int n = 0; n < thisGraph->numberOfCols; n++) {
      printf("%d, %lf %lf", i * thisGraph->numberOfCols + n,
             thisGraph->vertices[i * thisGraph->numberOfCols + n].weightRight,
             thisGraph->vertices[i * thisGraph->numberOfCols + n].weightDown);
    }
  }
}

int main() {
  graph *Graph = malloc(sizeof(*Graph));
  char *fileName = "mygraph4";
  parseGraphFromFile(Graph, fileName);
  
  double j = findShortestPath(Graph, 0, 14, 2);
  printf("%lf\n", j);
  j = findShortestPath(Graph, 0, 99, 1);
  printf("%lf\n", j);
  printGraph(Graph);
}
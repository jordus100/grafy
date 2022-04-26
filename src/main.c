#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "graph_cohesion.h"
#include "graph_generator.h"
#include "file_handler.h"
#include "path_finder.h"

int handleGraphInspectionModeInput(int argc, char** argv){


    int graphOutputMode;
    if(strcmp(argv[1], "-p")==0)
        graphOutputMode = 1; /* basic mode */
    if(strcmp(argv[1], "-r")==0)
        graphOutputMode = 2; /* extended mode */
    char* fileName = argv[3];
    int i;
    int** vertices = malloc((argc-5)*sizeof vertices);
    for(i=0; i<argc-5; i++)
        vertices[i] = malloc(sizeof *(vertices[i]));
    for(i=5; i<argc; i+=2){
        if(strstr(argv[i], "[")!=NULL){
            vertices[(i-5)/2][0] = atoi(&argv[i][1]);
        }
        else{
            fprintf(stderr, "\nError: incorrect format of arguments.\n");
            return 1;
        }
        if(strstr(argv[i+1], "]")!=NULL){
            vertices[(i-5)/2][1] = atoi( &argv[i+1][0]);
        }
        else{
            fprintf(stderr, "\nError: incorrect format of arguments.\n");
            return 1;
        }
    }
    int verticesPairsNum = i-5;
    graph *Graph = malloc(sizeof *Graph);
    if(parseGraphFromFile(Graph, fileName)==1){
      fprintf(stderr, "Error: invalid input file");
      return 1;
    }
  int* searched;
  if(isGraphCohesive(Graph, searched)==0){
    printf("Graph is cohesive!\n");
  }
  else {
    printf("Graph is not cohesive!\n");
  }
    for(i=0; i<verticesPairsNum; i++){
        findShortestPath(Graph, vertices[i][0], vertices[i][1], graphOutputMode);
    }
    return 0;

}

int handleGraphGeneratingModeInput(int argc, char** argv){
    int rowsNum = atoi(argv[2]);
    int colsNum = atoi(argv[3]);
    int mode = atoi(argv[5]);
    if(mode<1 || mode>4){
        fprintf(stderr, "\nError: incorrect graph generating mode entered.\n");
    }
    graph* Graph = generateGraph(rowsNum, colsNum, mode);
    if(saveGraphToFile(Graph, "mygraph")==1){
        fprintf(stderr, "\nError: could not save the graph to file.\n");
        return 1;
    }
    else return 0;
}

int main(int argc, char** argv){

    if(argc<2){
        fprintf(stderr, "\nError: no arguments passed.\n");
        return 1;
    }
    if((strcmp(argv[1], "-p")==0 || strcmp(argv[1], "-r")==0) && strcmp(argv[2], "-f")==0){ /* program function nr 1 - reading a graph from file and analyzing it */
        return handleGraphInspectionModeInput(argc, argv);
    }
    if(strcmp(argv[1], "-n")==0){ /* program function nr 2 - generating a graph and saving it to a file */
        return handleGraphGeneratingModeInput(argc, argv);
    }
    else{
        fprintf(stderr, "\nError: invalid arguments passed.\n");
        return 1;
    }

}


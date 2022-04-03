#include <stdio.h>

typedef struct graph{
    int numberOfCols;
    int numberOfRows;
    vertex vertices[];
};

typedef struct vertex{
    int vertexNumber;
    double weightLeft;
    double weightRight;
    double weightUp;
    double weightDown;
};

int main(int argc, char* argv[]){
    printf("Hello, world!");
}


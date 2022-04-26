#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "data_structs.h"
#include "path_finder.h"
#ifdef NAN
#endif
#ifdef INFINITY
#endif

queueVertice* initQueue(int numberFirst)
{
  queueVertice* firstVertice;
  firstVertice = malloc(sizeof(*firstVertice));
  firstVertice->next=NULL;
  firstVertice->distanceTo=0;
  firstVertice->verticeNumber=numberFirst;
  return firstVertice;
}

queueVertice* addToQueue(queueVertice* tail,double distance, int number)
{
  queueVertice *newVertice;
  newVertice = malloc(sizeof(*newVertice));
  newVertice->next=NULL;
  newVertice->distanceTo=distance;
  newVertice->verticeNumber=number;
  tail->next=newVertice;
  return tail->next;
}

queueVertice* sortQueue(queueVertice* thisVertice){
  int count=1;
  queueVertice* vertice = thisVertice;
  
  while(vertice->next!=NULL){
    vertice=vertice->next;
    count++;
  }
  
  int i=0;
  vertice = thisVertice;
  queueVertice** verticeArray=malloc(count*sizeof(**verticeArray));
  
  while(vertice->next!=NULL){
    verticeArray[i]=malloc(sizeof(*verticeArray[i]));
    verticeArray[i]=vertice;
    i++;
    vertice=vertice->next;
  }
  
  verticeArray[i]=malloc(sizeof(*verticeArray[i]));
  verticeArray[i]=vertice;
  
  for(int j=0;j<count;j++)
    {
      for(int k=0;k<count-1;k++)
      {
        if(verticeArray[k]->distanceTo>verticeArray[k+1]->distanceTo)
        {
          vertice=verticeArray[k];
          verticeArray[k]=verticeArray[k+1];
          verticeArray[k+1]=vertice;
        }
      }
    }
  for(int k=0;k<count-1;k++)
    verticeArray[k]->next=verticeArray[k+1];
  verticeArray[count-1]->next=NULL;
  return verticeArray[0];
}

double findShortestPath(graph* thisGraph, int verticeFrom, int verticeTo,int mode)
{
  queueVertice* queueHead;
  queueVertice* queueTail;
  
  double* distances=malloc(sizeof(distances)*thisGraph->numberOfCols*thisGraph->numberOfRows);
  int* ancestors=malloc(sizeof(ancestors)*thisGraph->numberOfCols*thisGraph->numberOfRows);
  
  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++)
  {
    ancestors[i]=-1;
    if(i!=verticeFrom)
      distances[i]=INFINITY;
    else
      distances[i]=0;
  }

  queueHead = initQueue(verticeFrom);
  queueTail = queueHead;
  
  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++)
    {
      if(distances[i]!=0)
      {
        queueTail=addToQueue(queueTail,distances[i],i);
      }
    }
  queueHead=sortQueue(queueHead);
  
  while(queueHead!=NULL)
    {
      checkNeighbour(ancestors,distances,thisGraph,queueHead->verticeNumber,queueHead->distanceTo,queueHead);
      if(queueHead->next!=NULL)
      {
        queueHead=sortQueue(queueHead);
      }
      queueHead=queueHead->next;
    }
  int count=1;
  int firstVertice=verticeTo;
  
  if(distances[verticeTo]!=INFINITY)
  {
    printf("\n");
    while(firstVertice!=verticeFrom)
      {
        count++;
        firstVertice=ancestors[firstVertice];
      }
    int* orderedVertices=malloc(sizeof(orderedVertices)*count);
    int firstVertice=verticeTo;
    for(int i=0;i<count;i++)
    {
      orderedVertices[i]=firstVertice;
      firstVertice=ancestors[firstVertice];
    }
    printPath(orderedVertices,mode,count,thisGraph);
  }
  else
  {
    printf("Path not found\n");
  }
  return distances[verticeTo];
}

void change(queueVertice* Head,int numberVertice,double newValue)
{
  if(Head->verticeNumber==numberVertice)
  {
    Head->distanceTo=newValue;
  }
  else
  {
      change(Head->next,numberVertice,newValue);
  }
}

void checkNeighbour(int* ancestors,double* distances,graph* thisGraph,int whoseNeighbour,double distanceToThisVertice,queueVertice* Head)
{
  if(thisGraph->vertices[whoseNeighbour].weightDown>=0)
    if(thisGraph->vertices[whoseNeighbour].weightDown+distanceToThisVertice<distances[whoseNeighbour+thisGraph->numberOfCols])
    {
      distances[whoseNeighbour+thisGraph->numberOfCols]=thisGraph->vertices[whoseNeighbour].weightDown+distanceToThisVertice;
      ancestors[whoseNeighbour+thisGraph->numberOfCols]=whoseNeighbour;
      change(Head,whoseNeighbour+thisGraph->numberOfCols,thisGraph->vertices[whoseNeighbour].weightDown+distanceToThisVertice);
    }

  if(thisGraph->vertices[whoseNeighbour].weightUp>=0)
    if(thisGraph->vertices[whoseNeighbour].weightUp+distanceToThisVertice<distances[whoseNeighbour-thisGraph->numberOfCols])
    {
      distances[whoseNeighbour-thisGraph->numberOfCols]=thisGraph->vertices[whoseNeighbour].weightUp+distanceToThisVertice;
      ancestors[whoseNeighbour-thisGraph->numberOfCols]=whoseNeighbour;
      change(Head,whoseNeighbour-thisGraph->numberOfCols,thisGraph->vertices[whoseNeighbour].weightUp+distanceToThisVertice);
    }

  if(thisGraph->vertices[whoseNeighbour].weightRight>=0)
    if(thisGraph->vertices[whoseNeighbour].weightRight+distanceToThisVertice<distances[whoseNeighbour+1])
    {
      distances[whoseNeighbour+1]=thisGraph->vertices[whoseNeighbour].weightRight+distanceToThisVertice;
      ancestors[whoseNeighbour+1]=whoseNeighbour;
      change(Head,whoseNeighbour+1,thisGraph->vertices[whoseNeighbour].weightRight+distanceToThisVertice);
    }

  if(thisGraph->vertices[whoseNeighbour].weightLeft>=0)
    if(thisGraph->vertices[whoseNeighbour].weightLeft+distanceToThisVertice<distances[whoseNeighbour-1])
    {
      distances[whoseNeighbour-1]=thisGraph->vertices[whoseNeighbour].weightLeft+distanceToThisVertice;
      ancestors[whoseNeighbour-1]=whoseNeighbour;
      change(Head,whoseNeighbour-1,thisGraph->vertices[whoseNeighbour].weightLeft+distanceToThisVertice);
    }
}

void printPath(int* verticleNumbersInOrder,int mode,int arrayLenght,graph* thisGraph)
{
  if(mode==1)
  {
  for(int i=0;i<arrayLenght;i++)
    {
    printf("%d",verticleNumbersInOrder[arrayLenght-i-1]);
      if(i!=arrayLenght-1)
        printf("=>");
    }
  }
  else
  {
    if(mode==2)
    {
      for(int i=0;i<arrayLenght;i++)
      {
        printf("%d",verticleNumbersInOrder[arrayLenght-i-1]);
        if(i!=arrayLenght-1)
        {
          printf("=(");
          if(verticleNumbersInOrder[arrayLenght-i-1]==verticleNumbersInOrder[arrayLenght-i-2]+thisGraph->numberOfCols)
            printf("%lf",thisGraph->vertices[verticleNumbersInOrder[arrayLenght-i-1]].weightUp);
          if(verticleNumbersInOrder[arrayLenght-i-1]==verticleNumbersInOrder[arrayLenght-i-2]-thisGraph->numberOfCols)
            printf("%lf",thisGraph->vertices[verticleNumbersInOrder[arrayLenght-i-1]].weightDown);
          if(verticleNumbersInOrder[arrayLenght-i-1]==verticleNumbersInOrder[arrayLenght-i-2]+1)
            printf("%lf",thisGraph->vertices[verticleNumbersInOrder[arrayLenght-i-1]].weightLeft);
          if(verticleNumbersInOrder[arrayLenght-i-1]==verticleNumbersInOrder[arrayLenght-i-2]-1)
            printf("%lf",thisGraph->vertices[verticleNumbersInOrder[arrayLenght-i-1]].weightRight);
          printf(")>");
        }
      }
    }
    else
    {
      printf("Wrong mode format\n");
    }
  }
  printf("\n");
}

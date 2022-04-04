#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "data_structs.h"
#include "path_finder.h"
#ifdef NAN
/* NAN is supported */
#endif
#ifdef INFINITY
/* INFINITY is supported */
#endif
//KOLEJKA PRIORYTETOWA
//SORTOWANIE I DODAWANIA DO KOLEJKI
//WHILE CHECKNEIGHBOUR TAK D�UGO JAK KOLEJKA NIE JEST PUSTA
//TEST CA�O�CI
typedef struct
{
int verticeNumber;
double distanceTo;
struct priorityQueue* next;
} queueVertice;

queueVertice* initQueue(int numberFirst)
{
  queueVertice firstVertice;
  firstVertice->next=NULL;
  firstVertice->distanceTo=0;
  firstVertice->verticeNumber=numberFirst;
  return *firstVertice;
}

void addToQueue(queueVertice* tail,double distance, int number)
{
  queueVertice newVertice;
  newVertice->next=NULL;
  newVertice->distanceTo=distance;
  newVertice->verticeNumber=number;
  tail->next=*newVertice;
}

queueVertice* swapVertices(queueVertice* swapVertice)
{
  queueVertice* firstVerticeHolder=swapVertice;
  swapVertice=swapVertice->next;
  queueVertice* secoundVerticeHolder=swapVertice->next;
  swapVertice->next=firstVerticeHolder;
  firstVerticeHolder->next=secoundVerticeHolder;
  return *swapVertice;
}

void sortQueue(queueVertice* thisVertice,queueVertice* lastVertice)
{
  if(thisVertice->distanceTo<lastVertice->distanceTo)
  {
    lastVertice=swapVertices(lastVertice);
    return sortQueue(lastVertice->next,lastVertice);
  }
  else
    if(thisVertice->next!=NULL)
      return sortQueue(thisVertice->next,thisVertice);
    else
      return thisVertice*;

}

void checkNeighbour(double*, graph*, int, double);

int* findShortestPath(graph* thisGraph, int verticeFrom, int verticeTo);

int* findShortestPath(graph* thisGraph, int verticeFrom, int verticeTo)
{
  graph* queueHead;
  graph* queueTail;

  double* distances=malloc(sizeof(distances)*thisGraph->numberOfCols*thisGraph->numberOfRows);
  int* ancestors=malloc(sizeof(ancestors)*thisGraph->numberOfCols*thisGraph->numberOfRows);

  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++) //w osi X'�w
    if(i!=verticeFrom)
      distances[i]=INFINITY;
    else
      distances[i]=0;

  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++)
    {
      addToQueue(queueTail,distances[i],i);
    }
  queuetail=sortQueue(queueHead,queueHead->next);
  while(queueHead!=NULL)
    {
      checkNeighbour(ancestors,distances,thisGraph,queueHead->verticeNumber,distanceTo);
      if(queueHead->next!=NULL)
      {
        queueTail=sortqueue(queueHead,queueHead->next);
      }
      queueHead=queueHead->next;
    }

  //printPath(ancestors,verticeTo,mode);
}

void checkNeighbour(int* ancestors,double* distances,graph* thisGraph,int whoseNeighbour,double distanceToThisVertice)
{
  if(thisGraph->vertices[whoseNeighbour].weightDown>=0)
    if(thisGraph->vertices[whoseNeighbour].weightDown+distanceToThisVertice<distances[whoseNeighbour+thisGraph->numberOfCols])
    {
      distances[whoseNeighbour+thisGraph->numberOfCols]=thisGraph->vertices[whoseNeighbour].weightDown+distanceToThisVertice;
      ancestors[whoseNeighbour+thisGraph->numberOfCols]=whoseNeighbour;
    }

  if(thisGraph->vertices[whoseNeighbour].weightUp>=0)
    if(thisGraph->vertices[whoseNeighbour].weightUp+distanceToThisVertice<distances[whoseNeighbour-thisGraph->numberOfCols])
    {
      distances[whoseNeighbour-thisGraph->numberOfCols]=thisGraph->vertices[whoseNeighbour].weightUp+distanceToThisVertice;
      ancestors[whoseNeighbour-thisGraph->numberOfCols]=whoseNeighbour;
    }

  if(thisGraph->vertices[whoseNeighbour].weightRight>=0)
    if(thisGraph->vertices[whoseNeighbour].weightRight+distanceToThisVertice<distances[whoseNeighbour+1])
    {
      distances[whoseNeighbour+1]=thisGraph->vertices[whoseNeighbour].weightRight+distanceToThisVertice;
      ancestors[whoseNeighbour+1]=whoseNeighbour;
    }

  if(thisGraph->vertices[whoseNeighbour].weightLeft>=0)
    if(thisGraph->vertices[whoseNeighbour].weightLeft+distanceToThisVertice<distances[whoseNeighbour-1])
    {
      distances[whoseNeighbour-1]=thisGraph->vertices[whoseNeighbour].weightLeft+distanceToThisVertice;
      ancestors[whoseNeighbour-1]=whoseNeighbour;
    }
}

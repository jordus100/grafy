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

typedef struct queueVertice
{
int verticeNumber;
double distanceTo;
struct queueVertice* next;
} queueVertice;

queueVertice* initQueue(int numberFirst)
{
  queueVertice *firstVertice;
  firstVertice = malloc(sizeof(*firstVertice));
  firstVertice->next=NULL;
  firstVertice->distanceTo=0;
  firstVertice->verticeNumber=numberFirst;
  return firstVertice;
}

void addToQueue(queueVertice* tail,double distance, int number)
{
  queueVertice *newVertice;
  newVertice = malloc(sizeof(*newVertice));
  newVertice->next=NULL;
  newVertice->distanceTo=distance;
  newVertice->verticeNumber=number;
  tail->next=newVertice;
}

queueVertice* swapVertices(queueVertice* swapVertice)
{
  queueVertice* firstVerticeHolder=swapVertice;
  swapVertice=swapVertice->next;
    queueVertice* secoundVerticeHolder=swapVertice->next;
  swapVertice->next=firstVerticeHolder;
  firstVerticeHolder->next=secoundVerticeHolder;
  return swapVertice;
}

queueVertice* sortQueue(queueVertice* thisVertice,queueVertice* lastVertice)
{
  if((thisVertice->distanceTo)<(lastVertice->distanceTo))
  {
    lastVertice=swapVertices(lastVertice);
    return sortQueue(lastVertice->next,lastVertice);
  }
  else
    if(thisVertice->next!=NULL)
    {
      return sortQueue(thisVertice->next,thisVertice);
    }
    else
    {
      return thisVertice;
    }
}

double findShortestPath(graph* thisGraph, int verticeFrom, int verticeTo)
{
  queueVertice* queueHead;
  queueVertice* queueTail;
  queueTail = malloc(sizeof(*queueTail));
  double* distances=malloc(sizeof(distances)*thisGraph->numberOfCols*thisGraph->numberOfRows);
  int* ancestors=malloc(sizeof(ancestors)*thisGraph->numberOfCols*thisGraph->numberOfRows);

  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++) //w osi X'�w
    if(i!=verticeFrom)
      distances[i]=INFINITY;
    else
      distances[i]=0;


  queueHead = initQueue(verticeFrom);
  queueTail = queueHead;
  for(int i=0;i<(thisGraph->numberOfCols) * (thisGraph->numberOfRows);i++)
    {
      if(distances[i]!=0)
        addToQueue(queueTail,distances[i],i);

    }
  queueTail=sortQueue(queueHead->next,queueHead);
  while(queueHead!=NULL)
    {
      checkNeighbour(ancestors,distances,thisGraph,queueHead->verticeNumber,queueHead->distanceTo);

      if(queueHead->next!=NULL)
      {
        queueTail=sortQueue(queueHead->next,queueHead);
      }
      queueHead=queueHead->next;
    }

  return distances[verticeTo];
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

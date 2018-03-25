#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef struct Airport Airport;
typedef struct Route Route;
typedef struct tripNode tripNode;

struct Route {
   Airport* from;
   Airport* to;
   int reversed;
   double distance;
};

struct Airport {
   Route** routes;
   char* name;
   char* code;
   int hasBeen;
   int numRoutes;
   double lat;
   double lon;
};

typedef struct {
   Airport **airports;
   Route **routes;
   int numAirports;
   int numRoutes;
} Data;

struct tripNode {
   Route *route;
   tripNode *next;
};

/* Alexander DeMello
 * Alex Lazar
 * CPE 419
 * Parallel Traveling Salesman Problem
 */
#include "TSP.h"


void printRoute(Route r) {
  printf("From: %s, To: %s, Reversed: %d, Distance: %f\n", r.from->code, r.to->code, r.reversed, r.distance);
}

void printRoutep(Route *r) {
  printRoute(*r);
}

void printAirport(Airport a) {
  int i; 
  printf("Name: %s, code: %s, lat: %f, lon: %f, numRoutes: %d\nRoutes:\n", a.name, a.code, a.lat, a.lon, a.numRoutes);
  for (i = 0; i < a.numRoutes; i++) {
    printRoutep(a.routes[i]);
  }
}

void printAirportp(Airport *a) {
  printAirport(*a);
}

double haversine(Airport *a1, Airport *a2) {
  double R = 6371;
  double dLat = a2->lat - a1->lat;
  double dLong = a2->lon - a1->lon;
  double a = pow(sin(dLat / 2), 2) + cos(a1->lat) * cos(a2->lat) * pow(sin(dLong / 2), 2);
  double c = 2 * atan2(sqrt(a), sqrt(1 - a));
  return R * c;
}

void cleanup(Data data) {
  int i;
  for (i = 0; i < data.numAirports; i++) {
    free(data.airports[i]->routes);
    free(data.airports[i]->name);
    free(data.airports[i]->code);
    free(data.airports[i]);
  }

  free(data.airports);

  for (i = 0; i < data.numRoutes; i++) {
    free(data.routes[i]);
  }

  free(data.routes);
}

void getDistances(Route **routes, int numRoutes) {
  int i;
//#pragma omp parallel for
  for (i = 0; i < numRoutes; i++) {
    routes[i]->distance = haversine(routes[i]->from, routes[i]->to);
  }
}

void getInput(Data *data) {
  int i;
  FILE *airportsIn = fopen("airports.txt", "r");
  int numAirports;
  fscanf(airportsIn, "%d", &numAirports);
  Airport **airports = malloc(sizeof(Airport*) * numAirports);
  
  char *name = malloc(sizeof(char) * 31);
  char *code = malloc(sizeof(char) * 4);
  int index;
  float lat;
  float lon;
  int outboundRoutes;
  for (i = 0; i < numAirports; i++) {
    fscanf(airportsIn, "%s %s %d %f %f %d", name, code, &index, &lat, &lon, &outboundRoutes);
    airports[index] = malloc(sizeof(Airport));
    airports[index]->name = malloc(sizeof(char) * strlen(name) + 1);
    strcpy(airports[index]->name, name);
    airports[index]->code = malloc(sizeof(char) * strlen(code) + 1);
    airports[index]->numRoutes = 0;
    airports[index]->routes = malloc(sizeof(Route*) * outboundRoutes);
    strcpy(airports[index]->code, code);
    airports[index]->lat = (double)lat * 3.14159 / 180;
    airports[index]->lon = (double)lon * 3.14159 / 180;
  }

  free(code);
  free(name);
  fclose(airportsIn);

  FILE *routesIn = fopen("routes.txt", "r");
  int numRoutes;
  fscanf(routesIn, "%d", &numRoutes);
  Route **routes = malloc(sizeof(Route*) * numRoutes);

  char fromCode[3];
  int from;
  char toCode[3];
  int to;
  int reversed;
  for (i = 0; i < numRoutes; i++) {
    fscanf(routesIn, "%s %d %s %d %d", fromCode, &from, toCode, &to, &reversed);
    // printf("Doing %d: %s %d to %s %d\n", i, fromCode, from, toCode, to);
    routes[i] = malloc(sizeof(Route));
    routes[i]->reversed = reversed;
    routes[i]->from = airports[from];
    routes[i]->to = airports[to];
    //routes[i]->distance = haversine(airports[from], airports[to]);

    airports[from]->routes[airports[from]->numRoutes] = routes[i];
    (airports[from]->numRoutes)++;

    if (reversed) {
      airports[to]->routes[airports[to]->numRoutes] = routes[i];
      (airports[to]->numRoutes)++;
    }
  }

  fclose(routesIn);

  getDistances(routes, numRoutes);

  data->airports = airports;
  data->numAirports = numAirports;
  data->routes = routes;
  data->numRoutes = numRoutes;
}

void printSamples(Data *data) {
  Airport a = *(data->airports[1204]);
  printAirport(a);
  
  Airport a2 = *(data->airports[1538]);
  printAirport(a2);
  
  Route r = *(data->routes[6]);
  printRoute(r);
}

void travel(tripNode *finalTrip, Airport *sanLuis) {

   Airport *currCity, *nextCity;
   int citiesVisited = 0, i, random;
   double lowestDistance = 0, currDistance = 0, finalDistance = 0, tempDistance;
   tripNode *trip = NULL, *tripEnd;
   Route  *tempRoute;

//Couldnt implement parallel versoin 
//because of unsolved issues meshing IO code with algorithm code
//#pragma offload target(mic:0)
//#pragma omp parallel num_threads(28) private(citiesVisited, i, lowestDistance, trip, currCity, nextCity, currDistance, tripEnd, tempRoute)
//{
   //first city is SBP
   currCity = sanLuis;
   //Need to loop until all cities visited at least once and back home
   while((citiesVisited != 3092) /*&& (strcmp(currCity->code, "SBP") == 0)*/) {
      lowestDistance = 0;
      //Look for the shortest route out and 
      //update temp values to be used in trip calc
      for(i=0; i < currCity->numRoutes; i++) {
         if(lowestDistance == 0 || ((currCity->routes[i]->distance < lowestDistance) && !currCity->routes[i]->to->hasBeen)) {
            lowestDistance = currCity->routes[i]->distance;
            nextCity = currCity->routes[i]->to;
            printf("working %s\n" ,nextCity->code);
            tempDistance = currCity->routes[i]->distance;
            tempRoute = currCity->routes[i];
         }
      }
      //randomness to prevent getting stuck. 
      //(didnt work easily seen fi program is run in current state)
      if(nextCity->hasBeen || (strcmp(nextCity->name,currCity->name) == 0)) {
         random = rand() % (currCity->numRoutes);
         nextCity = currCity->routes[random]->to;
         tempDistance = currCity->routes[random]->distance;
         tempRoute = currCity->routes[random];
      }
      //Only add to the total number of cities 
      //if you actually havent been there before
      if(!currCity->hasBeen) {
         citiesVisited++;
         currCity->hasBeen = 1;
      }
      //append route to trip list
      if(trip == NULL) {
         trip = (tripNode*)malloc(sizeof(tripNode));
         trip->route = tempRoute;
         trip->next = NULL;
         tripEnd = trip;
      } 
      else {
         tripEnd->next = (tripNode*)malloc(sizeof(tripNode));
         tripEnd = tripEnd->next;
         tripEnd->route = tempRoute;
         tripEnd->next = NULL;
      }
      //take your flight!
      currCity = nextCity;
      currDistance += tempDistance;
   }
   //Code to take the best trip out of 
   //all of the threads trips (part of parallel sectoin not implemented)
   /*if(finalDistance == 0 || (currDistance < finalDistance)) {
      finalDistance = currDistance;
      finalTrip = trip;
   */}
//}

int main() {
   Data *data = malloc(sizeof(Data));
   tripNode *finalTrip;

   getInput(data);

   travel(finalTrip, data->airports[1287]);

   cleanup(*data);
   free(data);

  return 0;
}


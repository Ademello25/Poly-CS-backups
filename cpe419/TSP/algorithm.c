struct tripNode {
   Route *route;
   tripNode *next;
};


Airport *currCity, *nextCity;
int citiesVisted = 0, i, random;
double lowestDistance = 0, currDistance = 0, finalDistance = 0, tempDistance;
tripNode *trip, *finalTrip, tripEnd;
Route tempRoute;

#pragma omp parallel num_threads(28) private(citiesVisited, i, lowestDistance, trip, currCity, nextCity, currDistance, tripEnd, tempRoute)
{
currCity = sloAirport;
while((citiesVisited != 3142) && (strcmp(currCity->code, "SBP") != 0)) {
   lowestDistance = 0;
   for(i=0; i< currCity->numRoutes; i++) {
      if(lowestDistance == 0 || ((currCity->routes[i]->distance < lowestDistance) && !currCity->routes[i]->to->hasBeen)) {
         lowestDistace = currCity->routes[i]->distance;
         nextCity = currCity->routes[i]->to;
         tempDistance = currCity->routes[i]->distance;
         tempRoute = currCity->routes[i];
      }
   }
   if(nextCity->hasBeen) {
      random = rand() % (currCity->numRoutes);
      nextCity = currCity->routes[random]->to;
      tempDistance = currCity->routes[random]->distance;
      tempRoute = currCity->routes[random];
   }
   if(!currCity->hasBeen) {
      citiesVisited++;
      currCity->hasBeen = TRUE;
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
      tripEnd->next = NULL
   }
   currCity = nextCity;
   currDistance += tempDistance;
}
if(finalDistance == 0 || (currDistance < finalDistance)) {
   finalDistance = currDistance;
   finalTrip = trip;
}

}

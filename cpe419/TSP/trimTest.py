import csv

airportIn = open('airports.dat')
airportOut = open('airports.csv', 'w')

csv_AirIn = csv.reader(airportIn)

for row in csv_AirIn:
   if row[4] and row[2] and row[6] and row[7]:
      temp =  "%s,%s,%s,%s\n" % (row[2],row[4],row[6],row[7])
      airportOut.write(temp)

routesIn = open('routes.dat')
routesOut = open('routes.csv', 'w')

csv_routeIn = csv.reader(routesIn)

for row in csv_routeIn:
   if row[2] and row[4]:
      tempR = "%s,%s\n" % (row[2], row[4])
      routesOut.write(tempR)

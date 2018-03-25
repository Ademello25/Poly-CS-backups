import csv

deadends = set(["KKB", "KCC", "AOS", "RDC", "CDJ", "DLZ", "WWP", "SYB", "KLN",
"PIP", "KYK", "KPR", "KZB", "CMP", "BVS", "PVE", "KZI", "XMS", "TUA", "UII",
'AKB', 'BFI', 'BLD', 'BMY', 'BUX', 'CLM', 'CXH', 'DHB', 'DUT', 'ERS', 'ESD', 
'FBS', 'FRD', 'GCW', 'GEA', 'GRP', 'IKO', 'ILP', 'IRP', 'IUE', 'KCL', 'KCQ', 
'KNQ', 'KOC', 'KPV', 'KQA', 'LIF', 'LJA', 'LKE', 'LPS', 'MEB', 'MEE', 'MPA', 
'MPP', 'MQH', 'MSW', 'NDU', 'OND', 'PTH', 'PTJ', 'RCE', 'SPB', 'SSB', 'STZ', 
'SXO', 'SXX', 'TGJ', 'TOU', 'TTA', 'UVE', 'VDA', 'WSX', 'YWH', 'TTQ'])

cityCheck = open('airports.dat');
csv_cityCheck = csv.reader(cityCheck);

cityReplace = {}
codeReplace = {}

for row in csv_cityCheck:
  if row[4] and row[4] not in deadends and row[2] and row[6] and row[7]:
    if row[2] in cityReplace:
      codeReplace[row[4]] = cityReplace[row[2]]
    else:
      cityReplace[row[2]] = row[4]

cityCheck.close()

routesIn = open('routes.dat')
routesOut = open('routes.txt', 'w')
csv_routeIn = csv.reader(routesIn)

existingRoutes = {}
usedAirports = {}
numRoutes = {}

for ded in deadends:
  usedAirports[ded] = -1

for row in csv_routeIn:
   if row[2] and row[4] and row[3] != "\\N" and row[5] != "\\N":
     if row[2] in codeReplace:
       start = codeReplace[row[2]]
     else:
       start = row[2]
     if row[4] in codeReplace:
       dest = codeReplace[row[4]]
     else:
       dest = row[4]
     if start not in usedAirports:
       usedAirports[start] = len(usedAirports) - len(deadends)
     if dest not in usedAirports:
       usedAirports[dest] = len(usedAirports) - len(deadends)
     oppR = "%s %d %s %d" % (dest, usedAirports[dest], start, usedAirports[start])
     if oppR in existingRoutes:
       if existingRoutes[oppR] != oppR + " 1":
         if start in numRoutes:
           numRoutes[start] = numRoutes[start] + 1
         else:
           numRoutes[start] = 1
         existingRoutes[oppR] = oppR + " 1" 
     elif start not in deadends and dest not in deadends:
       tempR = "%s %d %s %d" % (start, usedAirports[start], dest, usedAirports[dest])
       if tempR not in existingRoutes:
         if start in numRoutes:
           numRoutes[start] = numRoutes[start] + 1
         else:
           numRoutes[start] = 1
         existingRoutes[tempR] = tempR + " 0";

routesIn.close()

routesOut.write(str(len(existingRoutes)) + "\n")

for route in existingRoutes.values():
  routesOut.write(route + "\n")

sumcheck = 0
for a in numRoutes.values():
  sumcheck += a

print sumcheck

routesOut.close()

airportIn = open('airports.dat')
airportOut = open('airports.txt', 'w')
csv_AirIn = csv.reader(airportIn)

airportOut.write(str(len(usedAirports) - len(deadends)) + "\n")
for row in csv_AirIn:
  if row[4] and row[4] in usedAirports and row[4] not in numRoutes and row[4] not in deadends:
    print row[4]
  if row[4] and row[4] in usedAirports and row[4] not in deadends and row[2] and row[6] and row[7]:
    temp = "%s %s %d %s %s %d\n" % (row[2].replace(" ", "_").replace(",", ""),row[4], usedAirports[row[4]], row[6],row[7], numRoutes[row[4]])
    airportOut.write(temp)

airportOut.close()
airportIn.close()

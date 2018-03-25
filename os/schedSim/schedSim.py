import argparse
import sys
from collections import namedtuple
from operator import attrgetter

def fifo():
   time = 0
   jobNum = 0
   while (jobNum < listSize):
      jobList[jobNum].response = (time - jobList[jobNum].arrival + 1)
      jobList[jobNum].wait = (time - jobList[jobNum].arrival)
      jobList[jobNum].turnaround = (jobList[jobNum].run + jobList[jobNum].wait)
      time = time + jobList[jobNum].run
      jobNum = jobNum + 1

def srjn():
   print 'not fully implemented'
   time = 0
   jobNum = 0
   allDone = False
   jobList.sort(key=lambda x: (x.arrival, x.run))

   i = 0
   jobQ = []
   while(i < listSize):
      jobQ.append(jobList[i])
      i = i +1
   i =0
   while (i < listSize):
      i = i +1
   #while (len(jobQ) != 0):
      
     #time = time + 1 

def rr(quantum):
   print 'not fully implemented'
   time = 0
   jobNum = 0
   allDone = False

   #while (allDone != True):
     #time = time + 1 

def printList():
   j =0
   avgResponse = 0.0
   avgWait = 0.0
   avgTurn = 0.0

   while (j < listSize):
      avgResponse = avgResponse + jobList[j].response
      avgWait = avgWait + jobList[j].wait
      avgTurn = avgTurn + jobList[j].turnaround
      print 'Job %3d -- Response: %3.2f  Turnaround %3.2f  Wait %3.2f' % (
            jobList[j].id, jobList[j].response, jobList[j].turnaround, 
            jobList[j].wait)
      j = j+1 
   
   avgResponse = avgResponse / listSize
   avgWait = avgWait / listSize
   avgTurn = avgTurn / listSize
   print 'Average -- Response: %3.2f  Turnaround %3.2f  Wait %3.2f' % (
         avgResponse, avgWait, avgTurn)

jobList = []
listSize = 0
inFile = open(sys.argv[1], 'r')

#read in the run and arrival times to a list
while True:
   line = inFile.readline()
   if not line: break
   s = line.split()

   jobList.append(namedtuple('Job', 'id arrival run response wait turnaround done timeRan'))
   jobList[listSize].run = float(s[0])
   jobList[listSize].arrival = float(s[1])

   listSize = listSize +1

#Assign my job #s
jobList.sort(key=lambda x: x.arrival)
i = 0
while i < listSize:
   jobList[i].id = i
   i = i +1

if (len(sys.argv) < 3):
   fifo()
   printList()
else:
   if (sys.argv[3] == 'FIFO'):
      fifo()
      printList()
   elif (sys.argv[3] == 'SRJN'):
      srjn()
      #printList()
   elif (sys.argv[3] == 'RR'):
      if(len(sys.argv) == 6):
         rr(sys.argv[5])
      else:
         rr(1)

      #printList()
   else:
      print 'Usage error'








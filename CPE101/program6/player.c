/*
 * A minimally implemented Battleship player.
 *
 * Find all occurances of TODO in the file and follow the
 * directions you find there!
 */
#include "battleship.h"
#include "players.h"
#include <stdio.h>
#include <stdlib.h>

/* PROVIDED - Local prototypes - DO NOT MODIFY */
static char* getName();
static Shot getShot();
static void shotResult(int result);
static void newGame(int board[SIZE][SIZE]);

/* TODO: GLOBAL VARIABLES
 *
 *   Add any global variables you want here.
 *
 *   IMPORTANT: Be sure to prepend the word "static" to all data declarations
 *   to prevent name collisions with other players in the tournament!
 */
static int offenseBoard[SIZE][SIZE];
static int xList[100] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 3, 4, 5,
   6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5,
   6, 0, 2, 7, 9, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5,
   6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9};
static int yList[100] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 0, 1, 2,
   3, 4, 5, 6, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 3, 4, 5, 6, 7, 8,
   9, 9, 9, 0, 0, 1, 4, 6, 8, 0, 2, 5, 7, 9, 1, 3, 6, 8, 2, 4, 7, 9, 0, 3, 5, 8, 1, 4, 6, 9,
   0, 2, 5, 7, 1, 3, 6, 8, 0, 2, 4, 7, 9, 1, 3, 5, 8};
static int listIndex;
static int xTraceList[17];
static int yTraceList[17];
static int traceIndex;
static int lastX;
static int lastY;

/* TODO: FUNCTION PROTOTYPES
 *
 *   Add the prototypes of any funcitons you decide to write here.
 *
 *   IMPORTANT: Be sure to prepend the word static to all function
 *   prototypes AND the functions themselves to prevent name collisions with
 *   other players in the tournament!
 */
static int hasMoreBoatsToPlace(int* a);
static int shouldShootHere(int x, int y);

/* Function: player()
 *
 * TODO: Change the name of this function from "userid" to your CalPoly userid.
 *       For example, I would change it to "kmammen" as follows:
 *
 *          PlayerFuncs kmammen()
 *
 * NOTE: DO NOT MAKE ANY OTHER MODIFICATIONS to this function. It returns
 *       all of the necessary function pointers to anyone that needs to
 *       call the functions.
 */
PlayerFuncs ademello()
{
   PlayerFuncs pf;
   
   pf.getName = getName;
   pf.newGame = newGame;
   pf.getShot = getShot;
   pf.shotResult = shotResult;
   
   return pf;
}

/* Function: getName()
 *
 * TODO: Change the name this function returns from player to your CalPoly
 *       userid. For example, I would change it to return "kmammen". 
 */
static char* getName()
{
   return "ademello";
}

/* Function: getShot()
 *
 * TODO: This function will be called each time it is your turn to take a shot.
 *       You need to return a Shot struct with the row and col members set to
 *       the coordinates of you shot. Note that your shotResult function (below)
 *       will be called with the result of your shot.
 *
 *       Don't forget that global variables retain the values between function
 *       calls. You may use any global variables you wish in your solution!
 */
static Shot getShot()
{   
   int foundShot = 0;
   Shot shot;
   while(!foundShot)
   {
		if((lastX!=-1) && (lastY!=-1) && (offenseBoard[lastX][lastY] == HIT))
      {
			traceIndex++;
			xTraceList[traceIndex]=lastX;
			yTraceList[traceIndex]=lastY;
         lastX = -1;
         lastY = -1;
         
		}
		if(traceIndex!=-1)
      {
			if(shouldShootHere(xTraceList[traceIndex] +1,yTraceList[traceIndex]))
         {
				shot.col = xTraceList[traceIndex]+1;
				shot.row = yTraceList[traceIndex];
				foundShot=1;
			} 
         else if(shouldShootHere(xTraceList[traceIndex],yTraceList[traceIndex]+1))
         {
				shot.col = xTraceList[traceIndex];
				shot.row = yTraceList[traceIndex]+1;
				foundShot=1;
			} 
         else if(shouldShootHere(xTraceList[traceIndex]-1,yTraceList[traceIndex]))
         {
				shot.col=xTraceList[traceIndex]-1;
				shot.row=yTraceList[traceIndex];
				foundShot=1;
			} 
         else if(shouldShootHere(xTraceList[traceIndex],yTraceList[traceIndex]-1))
         {
				shot.col=xTraceList[traceIndex];
				shot.row=yTraceList[traceIndex]-1;
				foundShot=1;
         } 
         else
         {
				traceIndex--;
			}
      } 
      else
      {
         if(shouldShootHere(xList[listIndex],yList[listIndex]))
         {
            shot.col=xList[listIndex];
            shot.row=yList[listIndex];
            foundShot=1;
         }
         listIndex++;
      }
      
   }
   lastX=shot.col;
   lastY=shot.row;
   return shot;
}

/* Function: shotResult(int result)
 *
 * TODO: This function will be called to tell you the results of your shot.
 *       Constants representing MISS, HIT, and SINK are defined in battleship.h
 *       and can be used by you here.
 *
 *       Don't forget that global variables retain the values between function
 *       calls. You may use any global variables you wish in your solution!
 */
static void shotResult(int result)
{
   offenseBoard[lastX][lastY] = result;
}

/* Function: newGame(int board[SIZE][SIZE])
 *
 * TODO: This function will be called once at the beginning of a new game. You
 *       must hide your ships on the board (the 2D int array passed in as a
 *       parameter). Note that your opponent cannot see where your ships are
 *       and you cannot see where theirs are.
 *
 *       The constants for the ships are defined in battleship.h and are:
 *
 *          AIRCRAFT_CARRIER
 *          BATTLESHIP
 *          DESTROYER
 *          SUBMARINE
 *          PATROL_BOAT
 *
 *       The constants for the ship sizes are also defined in battleship.h and
 *       are:
 *
 *          SIZE_AIRCRAFT_CARRIER
 *          SIZE_BATTLESHIP
 *          SIZE_DESTROYER
 *          SIZE_SUBMARINE
 *          SIZE_PATROL_BOAT
 *
 *       The ships must be places vertically or horizontally (not diagonally)
 *       and may not wrap around from one side of the board to the other.
 *
 *       To place a ship in a location you simple assign the correct number of
 *       cells in the board to the appropriate constant value. For example, a
 *       PATROL_BOAT has a size of 2, The following code would place it
 *       horizontally in the lower-left corner of the board:
 *
 *          board[9][0] = PATROL_BOAT;
 *          board[9][1] = PATROL_BOAT;
 *
 *       The first index of the 2D array is the row and the second index is
 *       the column. This means the code fragment above hides the PATROL_BOAT
 *       horizontally in row 9 in columns 0 and 1.
 *
 * NOTE: YOU MUST SET ALL array values not containing a ship TO ZERO.
 *    
 * NOTE: You may, in addition to hiding the ship, initialization any global
 *       variables you are using here.
 */

/*helper method hasMoreBoatsToPlace takes a list of ints (boatsIHavePlaced) and returns an int if
 one of them is 0. */
static int hasMoreBoatsToPlace(int* a)
{
   return !a[0] || !a[1] || !a[2] || !a[3] || !a[4];
}

static int shouldShootHere(int x, int y)
{
   if((x<10) && (y<10) && (x >=0) && (y >=0) && (offenseBoard[x][y]==0))
   {
      return 1;
	}
   else
   {
      return 0;
	}
}

static void newGame(int board[SIZE][SIZE])
{
   int i,o;
   int boatlist[5] = {5,4,3,3,2}; /*these numbers just represent the length*/
   int boatsIHavePlaced[5]={0,0,0,0,0}; /*boolean representing whether the corresponding boat was placed.*/
   int boatSymbols[5]={11,12,13,14,15};
   int direction, x, y, boat, count;
   listIndex = 0;
   lastX = -1;
   lastY = -1;
   traceIndex = -1;
   
   for (i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o ++)
      {
         board[i][o] = 0;
      }
   }
   
   for (i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o ++)
      {
         offenseBoard[i][o] = 0;
      }
   }
   
   while(hasMoreBoatsToPlace(boatsIHavePlaced) != 0)
   {
      x = rand() % 10;
      y = rand() % 10;
      direction = rand () % 2;
      boat = rand() % 5;
      if(!boatsIHavePlaced[boat])
      {
         if(direction)
         {
            for(count = 0;((count<boatlist[boat]) && (x+count < 10));count++)
            {
               if(board[x+count][y] != 0)
               {
                  break;
               }
            }
         }
         else
         {
            for(count = 0;((count<boatlist[boat]) && (y+count < 10));count++)
            {
               if(board[x][y+count] != 0)
               {
                  break;
               }
            }
         }
         if(count==boatlist[boat])
         {
            boatsIHavePlaced[boat]=1;
            if(direction)
            {
               for(count = 0;((count<boatlist[boat]) && (x+count < 10));count++)
               {
                  board[x+count][y] = boatSymbols[boat];
               }
            }
            else
            {
               for(count = 0;((count<boatlist[boat]) && (y+count < 10));count++)
               {
                  board[x][y+count] = boatSymbols[boat];
               }
            }
         }
      }
   }
   
}

/*
 * A minimally implemented Battleship player.
 *
 * Find all occurances of TODO in the file and follow the
 * directions you find there!
 */
#include "battleship.h"
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

static int board[SIZE][SIZE];
static int hisBoard[SIZE][SIZE];
static Shot lastShot;

/* TODO: FUNCTION PROTOTYPES
 *
 *   Add the prototypes of any funcitons you decide to write here.
 *
 *   IMPORTANT: Be sure to prepend the word static to all function
 *   prototypes AND the functions themselves to prevent name collisions with
 *   other players in the tournament!
 */

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
   Shot shot;
   int i,o;

   for ( i=0; i < SIZE ; i ++)
   {
      for(o=0; i < SIZE; o ++)
      {
         shot.row = i;
         shot.col = o;
      }
   }
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
   //update your 'opponent side' board at last shot fired with the result passed here
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
static void newGame(int board[SIZE][SIZE])
{
   int i,o;
   lastShot.row = -1;
   lastShot.col = -1;

   for (i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o ++)
      {
         board[i][o] = 0;
      }
   }
   
   for(i=0; i <SIZE; i++)
   {
      for(o=0; o <SIZE; o++)
      {
         hisBoard[i][o] = 0;
      }
   }
}

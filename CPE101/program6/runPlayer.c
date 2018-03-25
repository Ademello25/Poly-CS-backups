/*
 * This program is provided to help you develop your player. It allows you
 * run a single game and see the details of each shot or run more than one
 * game and see the game-level results.
 *
 * Author: Kurt Mammen
 * Change Log:
 *
 *    11/11/2011: Original Revision
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "battleship.h"
#include "players.h"

/* LOCAL PROTOTYPES */
static void copyBoardLocal(int to[SIZE][SIZE], int from[SIZE][SIZE]);
static void displayBoard(int games, int board[SIZE][SIZE]);
static void displayResults(int games,
                           int shotNumber,
                           int board[SIZE][SIZE],
                           Shot shot,
                           int result);

/* This is the one passed to the player. By making it global it should
 * reduce the likelyhood of the player corrupting my stack variables */
int temp[SIZE][SIZE];

/* 
 * The main function - This is where you should develop your tests!
 */
int main(int argc, char **argv)
{
   int board[SIZE][SIZE];
   PlayerFuncs pf = players[0]();
   Shot shot; 
   int g, i, games, max = 0, min = 100, sum = 0, wins = 0;
   int dupCount = 0;
   int sinks = 0;
   int AHits, BHits, DHits, SHits, PHits;
   double avg = 0;
   srand(time(NULL));

   printf("\nWelcome %s!\n\n", pf.getName());
   printf("This driver allows you to run 1 to N games. When you run a\n");
   printf("single game it shows you the details for each shot. When you\n");
   printf("specify more than 1 game it gives you the results for each\n");
   printf("game and a summary of the results for all games.\n\n");
   
   do
   {
      printf("How many games would you like to play? ");
      scanf("%d", &games);

      if (games < 1)
      {
         printf("You must specify 1 or more games, please try again\n\n");
      }
      else
      {
         printf("\n");
      }
   }
   while (games < 1);

   /* Flush stdin */
   while(getc(stdin) != '\n');

   for (g = 0; g < games; g++)
   {
      /* Call to allow player to initialize itself */
      pf.newGame(temp);
      copyBoardLocal(board, temp);

      /* Init some variables to keep track of performance */
      dupCount = sinks = AHits = BHits = DHits = SHits = PHits = 0;

      for (i = 0; i < SIZE * SIZE && sinks < NUMBER_OF_SHIPS; i++)
      {
         shot = pf.getShot();
   
         if (shot.row < 0 || shot.row >= SIZE
          || shot.col < 0 || shot.col >= SIZE)
         {
            displayResults(games, i+1, board, shot, MISS);
            continue;
         }

         switch(board[shot.row][shot.col])
         {
            case 'X':
               dupCount++;     
            case 0:
               board[shot.row][shot.col] = 'X';
               pf.shotResult(MISS);
               displayResults(games, i+1, board, shot, MISS);
            break;

            case AIRCRAFT_CARRIER:
               AHits++;
               board[shot.row][shot.col] *= -1;

               if (AHits == SIZE_AIRCRAFT_CARRIER)
               {
                  pf.shotResult(SINK);
                  sinks++;
                  displayResults(games, i+1, board, shot, SINK);
               }
               else
               {
                  pf.shotResult(HIT);
                  displayResults(games, i+1, board, shot, HIT);
               }
            break;

            case BATTLESHIP:
               BHits++;
               board[shot.row][shot.col] *= -1;

               if (BHits == SIZE_BATTLESHIP)
               {
                  pf.shotResult(SINK);
                  sinks++;
                  displayResults(games, i+1, board, shot, SINK);
               }
               else
               {
                  pf.shotResult(HIT);
                  displayResults(games, i+1, board, shot, HIT);
               }
            break;

            case DESTROYER:
               DHits++;
               board[shot.row][shot.col] *= -1;

               if (DHits == SIZE_DESTROYER)
               {
                  pf.shotResult(SINK);
                  sinks++;
                  displayResults(games, i+1, board, shot, SINK);
               }
               else
               {
                  pf.shotResult(HIT);
                  displayResults(games, i+1, board, shot, HIT);
               }
            break;

            case SUBMARINE:
               SHits++;
               board[shot.row][shot.col] *= -1;

               if (SHits == SIZE_SUBMARINE)
               {
                  pf.shotResult(SINK);
                  sinks++;
                  displayResults(games, i+1, board, shot, SINK);
               }
               else
               {
                  pf.shotResult(HIT);
                  displayResults(games, i+1, board, shot, HIT);
               }
            break;

            case PATROL_BOAT:
               PHits++;
               board[shot.row][shot.col] *= -1;

               if (PHits == SIZE_PATROL_BOAT)
               {
                  pf.shotResult(SINK);
                  sinks++;
                  displayResults(games, i+1, board, shot, SINK);
               }
               else
               {
                  pf.shotResult(HIT);
                  displayResults(games, i+1, board, shot, HIT);
               }
            break;

            default:
               pf.shotResult(HIT);
               dupCount++;
               displayResults(games, i+1, board, shot, HIT);
            break;
         }
      }
   
      printf("Game %3d: ", g + 1);
      printf("Sunk %d ships in %d shots with %d duplicate shots\n",
              sinks, i, dupCount);

      if (i < min)
      {
         min = i;
      }

      if (i > max)
      {
         max = i;
      }
   
      if (sinks == 5)
      {
         wins++;
      }

      sum += i;
   }

   avg = sum / games;

   printf("\nStats for %d games: Wins=%d, MAX=%d, AVG=%.0f, MIN=%d\n",
          g, wins, max, avg, min);

   return 0;
}

static void copyBoardLocal(int to[SIZE][SIZE], int from[SIZE][SIZE])
{
   int r, c;

   for (r = 0; r < SIZE; r++)
   {
      for (c = 0; c < SIZE; c++)
      {
         to[r][c] = from[r][c];
      }
   }
}

static void displayBoard(int games, int board[SIZE][SIZE])
{
   int r, c;

   if (games > 1)
   {
      return;
   }

   for (r = 0; r < SIZE; r++)
   {
      for (c = 0; c < SIZE; c++)
      {
         if (board[r][c] == 'X')
         {
            printf("  %c", board[r][c]);
         }
         else
         {
            printf("%3d", board[r][c]);
         }
      }

      printf("\n");
   }
}

static void displayResults(int games,
                           int shotNumber,
                           int board[SIZE][SIZE],
                           Shot shot,
                           int result)
{
   if (games > 1)
   {
      return;
   }

   displayBoard(games, board);

   printf("\nShot %d at row %d column %d ", shotNumber, shot.row, shot.col);

   if (shot.row < 0 || shot.row >= SIZE
    || shot.col < 0 || shot.col >= SIZE)
   {
      printf("was off the board. ");
   }
   else
   {
      switch(result)
      {
         case HIT:
           printf("was a HIT! ");
         break;

         case MISS:
           printf("was a MISS. ");
         break;

         case SINK:
           printf("was a SINK! ");
         break;

         default:
           printf("was an unknown type - weird! ");
         break;
      }
   }

   if (games == 1)
   {
      printf(" Press <enter> to continue: ");

      /* Flush stdin */
      while (getc(stdin) != '\n');

      printf("\n");
   }
}


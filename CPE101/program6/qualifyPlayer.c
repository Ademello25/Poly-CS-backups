/*
 * This code determines the quality of a player. There are three possible
 * outcomes:
 *
 *   1) The player qualifies for the tournament.
 *   2) The player qualifies for passing grade but is not sufficiently
 *      capable to compete in the tournament.
 *   3) The player does not meet the minimum requirements for a passing grade.
 *
 * To qualify for the tournament the player must:
 *
 *   1) Hide ships in a reasonably complex way to avoid prediction of their
 *      locations.
 *   2) Always sink ships in less than SHOT_LIMIT number of shots
 *
 * To qualify for a passing grade (but not the tournament) the player must:
 *
 *   1) Hide ships successfully but not very creatively.
 *      locations.
 *   2) Always sink ships in less than SIZE * SIZE (100) shots. 
 *
 * Author: Kurt Mammen
 *
 * Change Log:
 *
 *    11/12/2011: Original Revision
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "battleship.h"
#include "players.h"

#define SHOT_LIMIT_70 100 
#define SHOT_LIMIT_85  80

#define BOARD_DIFF_70 10
#define BOARD_DIFF_85 20

/* Local prototypes */
static void checkArgs(int argc, char **argv);
static void reportResults(int score);
static int test(int pass, char* msg);
static int testGetName(PlayerFuncs pf, char *userid);
static int testNewGame(PlayerFuncs pf);
static int testHideShips(PlayerFuncs pf, int minDifferences);
static int testGetShot(PlayerFuncs pf, int limit);
static int boardComparisons(int n);

/* Error message buffer used by tests */ 
static char msg[1000];

/* A bunch of boards to use for testing
 *
 * Don't forget that the turn() function modifies the board so be sure
 * to use copies of these when testing!
 */
static int bx[5][SIZE][SIZE] = {{{11,  0,  0,  0,  0,  0,  0,  0,  0, 12},
                                 {11,  0,  0,  0,  0,  0,  0,  0,  0, 12},
                                 {11,  0,  0,  0,  0,  0,  0,  0,  0, 12},
                                 {11,  0,  0,  0,  0,  0,  0,  0,  0, 12},
                                 {11,  0,  0,  0, 15,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0, 15,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
                                 {13,  0,  0,  0,  0,  0,  0,  0,  0, 14},
                                 {13,  0,  0,  0,  0,  0,  0,  0,  0, 14},
                                 {13,  0,  0,  0,  0,  0,  0,  0,  0, 14}},
                                {{11, 11, 11, 11, 11,  0, 12, 12, 12, 12},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0, 15, 15,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}, 
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 {13, 13, 13,  0,  0,  0,  0, 14, 14, 14}},
                                {{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0, 11,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0, 11, 12,  0,  0,  0,  0},
                                 { 0,  0,  0, 14, 11, 12, 13,  0,  0,  0},
                                 { 0,  0, 15, 14, 11, 12, 13,  0,  0,  0},
                                 { 0,  0, 15, 14, 11, 12, 13,  0,  0,  0}, 
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}},
                                {{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0, 15, 15,  0,  0,  0,  0,  0},
                                 { 0,  0,  0, 13, 13, 13,  0,  0,  0,  0},
                                 { 0,  0,  0, 12, 12, 12, 12,  0,  0,  0},
                                 { 0,  0,  0, 11, 11, 11, 11, 11,  0,  0},
                                 { 0,  0,  0, 14, 14, 14,  0,  0,  0,  0}, 
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}},
                                {{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0, 14,  0,  0},
                                 { 0,  0, 15, 15,  0,  0,  0, 14,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0, 14,  0,  0},
                                 { 0,  0,  0,  0, 11, 11, 11, 11, 11,  0},
                                 { 0, 12,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0, 12,  0,  0,  0,  0,  0,  0,  0,  0}, 
                                 { 0, 12,  0,  0,  0,  0, 13, 13, 13,  0},
                                 { 0, 12,  0,  0,  0,  0,  0,  0,  0,  0},
                                 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0}}};

/* 
 * The main function
 */
int main(int argc, char **argv)
{
   /* Keep vars away from the stack where corruption is more likely */
   static int pass = 1;
   static int score = 0;
   static PlayerFuncs pf;
   pf = players[0]();
   srand(time(NULL));

   printf("\nBattleship Player Qualifier\n\n");

   /* Verifies that you passed in the expected userid */
   checkArgs(argc, argv);

   pass &= testGetName(pf, argv[1]);
   pass &= testNewGame(pf);
   pass &= testHideShips(pf, BOARD_DIFF_70);
   pass &= testGetShot(pf, SHOT_LIMIT_70);

   if (pass)
   {
      score = 70;
   }

   pass &= testHideShips(pf, BOARD_DIFF_85);
   pass &= testGetShot(pf, SHOT_LIMIT_85);

   if (pass)
   {
      score = 85;
   }

   reportResults(score);
   return 0;
}

/* 
 * Reports test results - DO NOT MODIFY!
 */
static void reportResults(int score)
{
   if (score == 0)
   {
      printf("\nSorry but your player does not yet qualify for a non-zero score.\n");
      printf("I'm available for help during lab and office hours - let me know\n");
      printf("if you would like some help!\n");
   }
   else if (score == 70)
   {
      printf("\nCongratulations, your player qualifies for a non-zero grade!\n");
      printf("If you want to enter the tournament you'll have to keep working\n");
      printf("on your player - let me know if you would like some help!\n");
   }
   else if (score == 85)
   {
      printf("\nWell Done - you have developed a serious player! You have earned,\n");
      printf("at a minimum, the score shown below. If your player is not too slow\n");
      printf("it will be entered in the tournament where all players in the top\n");
      printf("half (based on wins) will earn 100%% credit for the assignment. See\n");
      printf("you at the tournament - good luck!\n");
   }
   else
   {
      printf("\nERROR: Unexpected score of %d - notify your instructor immediately\n", score);
      return;
   }
   
   printf("\nScore: %d\n", score);
}

/* 
 * Checks the arguments to the program - DO NOT MODIFY!
 */
static void checkArgs(int argc, char **argv)
{
   if (argc != 2)
   {
      printf("USAGE: %s userid\n\n Replace \"userid\" with your actual CalPoly userid\n", argv[0]);
      exit(1);
   }
}

static int testGetName(PlayerFuncs pf, char *userid)
{
   static int pass = 1;
   printf("   Testing getName()...\n");

   pass &= test(strcmp(userid, "player"),
                "You have not renamed your player to your CalPoly userid");

   sprintf(msg, "Expected \"%s\", found \"%s\"", userid, pf.getName());
   pass &= test(!strcmp(userid, pf.getName()), msg);

   return pass;
}

static int testNewGame(PlayerFuncs pf)
{
   static int pass = 1;
   int b[SIZE][SIZE];
   printf("   Testing newGame()...\n");

   pf.newGame(b);
   pass &= test(verifyBoard(b), "Invalid board");

   return pass;
}
  
static int testHideShips(PlayerFuncs pf, int minDiffs)
{
   #define NUM_BOARDS 10
   static int pass = 1;
   static int i, j, r, c, different;
   static int boards[NUM_BOARDS][SIZE][SIZE];
   static int count = 0;
   static int avg;

   printf("   Testing newGame() hides ships with at least %d differences\n", minDiffs);

   for(i = 0; i < NUM_BOARDS && pass; i++)
   {
      pf.newGame(boards[i]);

      pass &= test(verifyBoard(boards[i]), "Produced an invalid board");

      /* Compare the new board to all previous boards */
      for (j = 0; j < i && pass; j++)
      {
         different = 0;

         /* Look for a difference... */
         for (r = 0; r < SIZE; r++)
         {
            for (c = 0; c < SIZE; c++)
            {
               if( boards[i][r][c] != boards[j][r][c])
               {
                  /* Found a difference... */
                  different = 1;
                  count++;
               }
            }
         }
         
         /* There should be no identical boards */ 
         if (!different)
         {
            sprintf(msg, "Produced two identical boards out of %d boards", NUM_BOARDS);
            pass = 0;
            test(pass, msg);
         } 
      }
   }

   /* Makes sure there are an average of at least X differences per board.
    * Note that X is just a guess at a reasonable lower limit. My hiding
    * algorithm produces about 30 differences on average. The maximum number
    * of differences in 34 (17 ship spots times 2).
    */
   avg = count / boardComparisons(NUM_BOARDS);
   
   if (avg < minDiffs)
   {
      sprintf(msg, "Your boards vary by an average of %d, expected at least %d differences",
                   avg, minDiffs);
      pass = 0;
      test(pass, msg);
   }

   return pass;
}

static int testGetShot(PlayerFuncs pf, int limit)
{
   static int pass = 1;
   static int i, count;
   static PlayerStats ps;
   int junk[SIZE][SIZE];
   int b[SIZE][SIZE];
   sprintf(msg, "   Testing getShot() in less that %d shots...\n", limit);
   printf("%s", msg);

   for (i = 0; i < 5; i++)
   {
      initPlayerStats(&ps);

      /* Give player a chance to init themselves */
      pf.newGame(junk);
      
      /* Test with known boards
       * Note: Make a copy to avoid changes during the game
       */
      copyBoard(b, bx[i]);

      if (!verifyBoard(b))
      {
         printf("ERROR: Unexepected testdriver failure - ");
         printf("please notify your instructor immediately!\n");
         exit(1);
      }
      
      for (count = 0; count < 100; count++)
      {
         turn(pf, &ps, b);

         if (ps.sinks == NUMBER_OF_SHIPS)
         {
            break;
         }
      }

      if (count >= limit)
      {
         pass = 0;
         sprintf(msg,
                 "Expected less than %d shots, took %d shots", limit, count);
         test(pass, msg);
         break;
      }
   }

   return pass;
}

static int test(int pass, char* msg)
{
   if (!pass)
   {
      printf("%s%s\n", "      FAILED: ", msg);
   }

   return pass;
}

/*
 * Used to calc the number of board comparisons. For example, when comparing
 * 10 boards there would be 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 board
 * comparisons (45 comparisons).
 */
static int boardComparisons(int n)
{
   int sum = 0;
   int i;

   for (i = 1; i < n; i++)
   {
      sum += i;
   }
   
   return sum;
}

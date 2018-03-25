/*
 * Battleship game functions - supports the tournament and testing programs.
 *
 * Author: Kurt Mammen
 *
 * Change Log:
 *
 *    11/14/2010: Original Revision
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "battleship.h"

void initPlayerStats(PlayerStats *ps)
{
   ps->shotRow = -1;
   ps->shotCol = -1;
   ps->shotResult = -1;

   ps->AHits = 0;
   ps->BHits = 0;
   ps->DHits = 0;
   ps->SHits = 0;
   ps->PHits = 0;

   ps->shots = 0;
   ps->hits = 0;
   ps->misses = 0;
   ps->sinks = 0;
}

int verifyBoard(int b[SIZE][SIZE])
{
   int r, c;

   int aCnt = 0;
   int bCnt = 0;
   int dCnt = 0;
   int sCnt = 0;
   int pCnt = 0;

   /* Check and count all ship types on the board */
   for (r = 0; r < SIZE; r++)
   {
      for (c = 0; c < SIZE; c++)
      {
         switch(b[r][c])
         {
            case 0: break; /* expected value when no ship */
            case AIRCRAFT_CARRIER: aCnt++; break;
            case BATTLESHIP: bCnt++; break;
            case DESTROYER: dCnt++; break;
            case SUBMARINE: sCnt++; break;
            case PATROL_BOAT: pCnt++; break;
            default: return 0; /* unexpected value */
         }
      }
   }

   /* Check for the correct number of each expected value */
   if (aCnt != SIZE_AIRCRAFT_CARRIER 
    || bCnt != SIZE_BATTLESHIP
    || dCnt != SIZE_DESTROYER
    || sCnt != SIZE_SUBMARINE
    || pCnt != SIZE_PATROL_BOAT)
   {
      return 0;
   }
    
   /* Check each ship is contiguous */
   if ( !isContiguous(AIRCRAFT_CARRIER, SIZE_AIRCRAFT_CARRIER, b)
     || !isContiguous(BATTLESHIP, SIZE_BATTLESHIP, b)
     || !isContiguous(DESTROYER, SIZE_DESTROYER, b)
     || !isContiguous(SUBMARINE, SIZE_SUBMARINE, b)
     || !isContiguous(PATROL_BOAT, SIZE_PATROL_BOAT, b))
   {
      return 0;
   }

   return 1;
}

int isContiguous(int type, int size, int b[SIZE][SIZE])
{
   int i, r, c, vertical = 1, horizontal = 1;

   /* Find beginning of ship */
   for (r = 0; r < SIZE; r++)
   {
      for (c = 0; c < SIZE; c++)
      {
         if (b[r][c] == type)
         {
            /* Check for expected number of contiguous cells */
            for (i = 0; i < size; i++)
            {
               vertical   &= b[r+i][c] == type;
               horizontal &= b[r][c+i] == type;
            }

            /* Can't be both or count would be wrong, so this is the answer */
            return vertical || horizontal;
         }
      }
   }
   
   /* Problem if we get here! */
   printf("ERROR: isContiguous()\n");
   exit(1);
}

void copyBoard(int to[SIZE][SIZE], int from[SIZE][SIZE])
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

void turn(PlayerFuncs p, PlayerStats *s, int b[SIZE][SIZE])
{
   static Shot shot;
   static int r, c;

   s->shots++;
   shot = p.getShot();
   r = shot.row;
   c = shot.col;

   if (r < 0
    || r >= SIZE
    || c < 0
    || c >= SIZE)
   {
      /* The shot was off the board */
      s->misses++;
      s->shotResult = MISS;
   }
   else if ( b[r][c] == 0)
   {
      /* The shot missed */
      s->misses++;
      s->shotResult = MISS;
   }
   else if (b[r][c] < 0)
   {
      /* A repeat hit */
      s->hits++;
      s->shotResult = HIT;
   }
   else
   {
      /* A first hit */
      s->hits++;
      s->shotResult = HIT;

      switch(b[r][c])
      {
         case AIRCRAFT_CARRIER:
            s->AHits++;

            if (s->AHits == SIZE_AIRCRAFT_CARRIER)
            {
               s->shotResult = SINK;
               s->sinks++;
            }
         break;

         case BATTLESHIP:
            s->BHits++;

            if (s->BHits == SIZE_BATTLESHIP)
            {
               s->shotResult = SINK;
               s->sinks++;
            }
         break;

         case DESTROYER:
            s->DHits++;

            if (s->DHits == SIZE_DESTROYER)
            {
               s->shotResult = SINK;
               s->sinks++;
            }
         break;

         case SUBMARINE:
            s->SHits++;

            if (s->SHits == SIZE_SUBMARINE)
            {
               s->shotResult = SINK;
               s->sinks++;
            }
         break;

         case PATROL_BOAT:
            s->PHits++;

            if (s->PHits == SIZE_PATROL_BOAT)
            {
               s->shotResult = SINK;
               s->sinks++;
            }
         break;

         default:
            printf("ERROR: Corrupted game board\n");
            exit(1);
      
         /* Flip sign so I can tell it was hit */
         b[r][c] = -b[r][c];

      } /* end else */
   }
   
   /* Notify the player about the shot result */
   p.shotResult(s->shotResult);
}

void report(PlayerFuncs pf, PlayerStats ps)
{
   printf("%8s: shot[%d][%d], ", pf.getName(), ps.shotRow, ps.shotCol);
   
   switch (ps.shotResult)
   {
      case HIT:  printf(" HIT!, "); break;
      case MISS: printf(" Miss, "); break;
      case SINK: printf("SINK!, "); break;
      default:
         printf("ERROR: Unexpected shot result, %d\n", ps.shotResult);
         exit(1);
      break;
   }

   printf("shots:%3d, misses:%2d, hits:%2d, sunk:%d\n",
      ps.shots, ps.misses, ps.hits, ps.sinks);
}


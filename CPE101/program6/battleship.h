#ifndef BATTLESHIP_H
#define BATTLESHIP_H

/* The Battleship board size.
 * 
 * Note that the board is a 10x10 2D grid
 */
#define SIZE 10

/* Ship types */
#define AIRCRAFT_CARRIER 11 
#define BATTLESHIP 12 
#define DESTROYER 13
#define SUBMARINE 14 
#define PATROL_BOAT 15 

/* Number of ships */
#define NUMBER_OF_SHIPS 5

/* Ship sizes */
#define SIZE_AIRCRAFT_CARRIER 5
#define SIZE_BATTLESHIP 4
#define SIZE_DESTROYER 3
#define SIZE_SUBMARINE 3
#define SIZE_PATROL_BOAT 2

/* Possible shot outcomes */
#define HIT  55
#define MISS 66
#define SINK 77

/* Structure representing the coordinates of a shot */
typedef struct
{
   int row; /* The row index of a shot */
   int col; /* The column index of a shot */
}
Shot;

/* Player function types */
typedef char* (*FuncGetName)();
typedef Shot (*FuncGetShot)();
typedef void (*FuncShotResult)(int);
typedef void (*FuncNewGame)(int[SIZE][SIZE]);

/* Structure of function pointers */
typedef struct
{
   FuncGetName getName;
   FuncNewGame newGame;
   FuncGetShot getShot;
   FuncShotResult shotResult;
}
PlayerFuncs;

/* Function type
 *
 * This function returns the player's function pointers.
 */
typedef PlayerFuncs (*FuncGetPlayerFuncs)();

/* Structure to keep track of a players stats */
typedef struct
{
   /* Most recent shot information */
   int shotRow;
   int shotCol;
   int shotResult;

   /* Count number of new hits on each ship so we know when it is sunk */
   int AHits;
   int BHits;
   int DHits;
   int SHits;
   int PHits;

   /* Counts of various outcomes */
   int shots;  /* Number of shots taken */
   int hits;   /* Number of hits (includes repeat hits) */
   int misses; /* Number of misses (includes repeat misses) */
   int sinks;  /* Number of ships sunk */
}
PlayerStats;

/* Function prototypes */
void turn(PlayerFuncs funcs, PlayerStats *stats, int board[SIZE][SIZE]);
void initPlayerStats(PlayerStats *stats);
 int verifyBoard(int board[SIZE][SIZE]);
void copyBoard(int to[SIZE][SIZE], int from[SIZE][SIZE]);
 int isContiguous(int shipType, int shipSize, int board[SIZE][SIZE]);
void report(PlayerFuncs funcs, PlayerStats stats);

#endif

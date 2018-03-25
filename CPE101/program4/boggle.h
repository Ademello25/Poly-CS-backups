/*
 * Assignment: Program 4
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#ifndef BOGGLE_NIGGA_boggle_h
#define BOGGLE_NIGGA_boggle_h

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 5

typedef struct
{
   int row,
   col;
}Position;


/* FUNCTION PROTOTYPE
 *
 * Function Name: newBoggleBoard
 *
 * Description: Creates a new board with random values for letters
 *
 * Preconditions: requires a 2 dimensional array and a seed(int)
 *
 * Parameters: 
 *    board[][] - an empty board of the size defined the "SIZE"
 *    seed - an integer that will be the seed of the random values
 *
 * Return: A boggle board with randomized letters
 */
void newBoggleBoard(char board[SIZE][SIZE], int seed);

/* FUNCTION PROTOTYPE
 *
 * Function Name: boggleBoardToString
 *
 * Description: Turns the boggle board into the form of a string
 *
 * Preconditions: cannot input an empty board for propper results
 *
 * Parameters:
 *    board[][] - the filled boggle board
 *    str - the string that the board is being copied to
 *
 * Return: a string of the form of the boggle board
 */
char* boggleBoardToString(char board[SIZE][SIZE], char* str);

/* FUNCTION PROTOTYPE
 *
 * Function Name: first
 *
 * Description: finds the first forward iteration of the searched string
 *
 * Preconditions: must enter characters
 *
 * Parameters: 
 *    board[][] - the filled boggle board
 *    str - the string you are searching for
 *
 * Return: the first forward iteration of the searched string
 */
Position firstForward(char board[SIZE][SIZE], const char* str);

/* FUNCTION PROTOTYPE
 *
 * Function Name: firstBackward
 *
 * Description: finds the first backward iteration of the searched string
 *
 * Preconditions: must enter characters
 *
 * Parameters: 
 *    board[][] - the filled boggle board
 *    str - the string you are searching for
 *
 * Return: the first backward iteration of the searched string
 */
Position firstBackward(char board[SIZE][SIZE], const char* str);

/* FUNCTION PROTOTYPE
 *
 * Function Name: firstDownward
 *
 * Description: finds the first downward iteration of the searched string
 *
 * Preconditions: must enter characters
 *
 * Parameters: 
 *    board[][] - the filled boggle board
 *    str - the string you are searching for
 *
 * Return: the first downward iteration of the searched string
 */
Position firstDownward(char board[SIZE][SIZE], const char* str);

/* FUNCTION PROTOTYPE
 *
 * Function Name: firstUpward
 *
 * Description: finds the first upward iteration of the searched string
 *
 * Preconditions: must enter characters
 *
 * Parameters: 
 *    board[][] - the filled boggle board
 *    str - the string you are searching for
 *
 * Return: the first upward iteration of the searched string
 */
Position firstUpward(char board[SIZE][SIZE], const char* str);





#endif

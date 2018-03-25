/*
 * Assignment: Program 4
 * Course: CPE 101
 * Author: Alexander DeMello
 */


#include "boggle.h"


int main()
{
   int seed, i, length1;
   char search[75];
   char strrepeat[6];
   char board[SIZE][SIZE];
   char* str;
   char str1[75];
   Position fforw, fback, fup, fdown;
   printf("*\n* Welcome to Kinda-Boggle!\n*\n");
   printf("To get started, I'll need a seed value to generate\n");
   printf("psuedo-random numbers. Enter 0 for random behavior\n");
   printf("or any other integer for predictable behavior.\n\n");
   printf("Seed: ");
   scanf("%d", &seed);
   printf("\nWow, what a great seed value! I never would have thought of %d!\n\n", seed);
   newBoggleBoard(board, seed);
   str = boggleBoardToString(board, str1);
   
   while(1)
   {
      printf("The Boggle board is:\n\n");
      printf("%s", str);
      
      printf("\n\nWhat string would you like to look for? ");
      scanf("%s", search);
      length1 = (int)strlen(search);
      for(i = 0; i < length1; i++)
      {
         search[i] = toupper(search[i]);
      }
      
      printf("\nThe search results are:\n\n");
      fforw = firstForward(board, search);
      if(((fforw.row==-1)||(fforw.col==-1)))
      {
         printf(" Forward: %s does not occur in the Boggle board!\n", search);
      }
      else
      {
         printf(" Forward: %s occurs first at row %d column %d\n", search, fforw.row, fforw.col);
      }
      
      
      fback = firstBackward(board, search);
      if(((fback.row == -1) || (fback.col == -1)))
      {
         printf("Backward: %s does not occur in the Boggle board!\n", search);
      }
      else
      {
         printf("Backward: %s occurs first at row %d column %d\n", search, fback.row, fback.col);
      }
      
      
      fup = firstUpward(board, search);
      if(((fup.row==-1)||(fup.col==-1)))
      {
         printf("  Upward: %s does not occur in the Boggle board!\n", search);
      }
      else
      {
         printf("  Upward: %s occurs first at row %d column %d\n", search, fup.row, fup.col);
      }
       
      
      fdown = firstDownward(board, search);
      if(((fdown.row == -1) || (fdown.col == -1)))
      {
         printf("Downward: %s does not occur in the Boggle board!\n\n", search);
      }
      else
      {
         printf("Downward: %s occurs first at row %d column %d\n\n", search, fdown.row, fdown.col);
      }
      
      
      printf("Would you like to look for another string (yes/no)? ");
      scanf("%s", strrepeat);
      printf("\n");
      if(strcmp(strrepeat, "yes") != 0)
      {
         break;
      }
      
      
   }
   printf("Thanks for playing, see you next time!\n");
   
   
   
   return 0;
}

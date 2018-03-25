/*
 * Assignment: Program 4
 * Course: CPE 101
 * Author: Alexander DeMello
 */


#include "boggle.h"

void newBoggleBoard(char board[SIZE][SIZE], int seed)
{
   char letter;
   int i, o;
   srand(seed);
   
   for(i = 0; i < SIZE ; i ++)
   {
      for(o = 0; o < SIZE ; o ++)
      {
         letter = 'A' + (rand() % 26);
         board[i][o] = letter;
      }
   }
   
}

char* boggleBoardToString(char board[SIZE][SIZE], char* str)
{
   int i, o, count;
   count = 0;
   
   for(i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o++)
      {
         str[count] = board[i][o];
         count ++;
         str[count] = ((o == SIZE - 1) ? '\n' : ' ');
         count ++;
      }
   }
   str[49] = 0;
   return str;
}

Position firstForward(char board[SIZE][SIZE], const char* str)
{
   Position ff;
   int i, o, p, result;
   unsigned long length1;
   length1 = strlen(str);
   
   for(i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE - length1 + 1; o++)
      {
         result = 1;
         for(p = 0; p < length1; p++)
         {
            if(board[i][o + p] != str[p])
               {
                  result = 0;
                  break;
               }
         }
         if(result == 1) 
         {
            ff.row = i;
            ff.col = o;
            return ff;
         }
         
      }
   }
   ff.row = -1;
   ff.col = -1;
   return ff;
}
Position firstBackward(char board[SIZE][SIZE], const char* str)
{
   Position ff;
   int i, o, p, result, length1;
   length1 = (int)strlen(str);
   
   for(i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE - length1 + 1; o++)
      {
         result = 1;
         for(p = 0; p < length1; p++)
         {
            if(board[i][o + p] != str[length1 - p - 1])
            {
               result = 0;
               break;
            }
         }
         if(result == 1) 
         {
            ff.row = i;
            ff.col = (o + length1 - 1);
            return ff;
         }
         
      }
   }
   ff.row = -1;
   ff.col = -1;
   return ff;
}

Position firstDownward(char board[SIZE][SIZE], const char* str)
{
   Position ff;
   int i, o, p, result;
   unsigned long length1;
   length1 = strlen(str);
   
   for(i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o++)
      {
         result = 1;
         if(i+length1>SIZE)
         {
            continue;
         }
         for(p = 0; p < length1; p++)
         {
            if(board[i+p][o] != str[p])
            {
               result = 0;
               break;
            }
         }
         if(result == 1) 
         {
            ff.row = i;
            ff.col = o;
            return ff;
         }
         
      }
   }
   ff.row = -1;
   ff.col = -1;
   return ff;
}

Position firstUpward(char board[SIZE][SIZE], const char* str)
{
   Position ff;
   int i, o, p, result;
   int length1;
   length1 =  (int)strlen(str);
   
   for(i = 0; i < SIZE; i++)
   {
      for(o = 0; o < SIZE; o++)
      {
         result = 1;
         if(i+length1>SIZE)
         {
            continue;
         }
         for(p = 0; p < length1; p++)
         {
            if(board[i+p][o] != str[length1-p-1])
            {
               result = 0;
               break;
            }
         }
         if(result == 1) 
         {
            ff.row = i+length1-1;
            ff.col = o;
            return ff;
         }
         
      }
   }
   ff.row = -1;
   ff.col = -1;
   return ff;
}

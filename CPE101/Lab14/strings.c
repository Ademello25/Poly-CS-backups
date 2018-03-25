/*
 * Assignment: Lab 13
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "strings.h"

int strlength(const char *str)
{
   int length;
   
   for(length = 0; (str[length] != 0); length ++);
   
   return length;
}

int strequals(const char *str1, const char *str2)
{
   int i, length1, length2;
   
   length1 = strlength(str1);
   length2 = strlength(str2);
   
   if(length1 != length2)
   {
      return 0;
   }
   
   for(i = 0; i < length1 ; i++)
   {
      if( str1[i] != str2[i])
      {
         return 0;
      }
   }
   return 1;
}

int strcompare(const char *str1, const char *str2)
{
   int i, length1, length2;
   
   length1 = strlength(str1);
   length2 = strlength(str2);
   
   for(i =0; i < ((length1 < length2) ? length1 : length2) ; i++)
   {
      if( str1[i] < str2[i] )
      {
         return -5;
      }
      else if( str1[i] > str2[i] )
      {
         return 5;
      }
      
   }
   if(length1 != length2)
   {
      return (length1 > length2) ? -5 : 5;
   }
   return 0;
}

char* strcopy(char *to, const char *from)
{
   int i,
   length = strlength(from);
   for(i = 0; i <= length ; i ++)
   {
      to[i] = from[i];
   }
   return to;
}

char* strconcatenate(char *to, const char*from)
{
   int i, length1, length2;
   length1 = strlength(to);
   length2 = strlength(from);
   
   for(i = 0; i < length2 + 1; i++)
   {
      to[length1 + i] = from[i];
   }
   return to;
}

char* strreplace(char *str, char find, char replaceWith)
{
   int i, length1;
   length1 = strlength(str);
   
   for(i = 0; i < length1 ; i++)
   {
      if(str[i] == find)
      {
         str[i] = replaceWith;
      }
   }
   return str;
}

int strcontains(const char *str1, const char *str2)
{
  
   int i, o, length1, length2, result;
   length2 = strlength(str2);
   length1 = strlength(str1);
   
   for(i = 0; i <= length1 - length2 ; i++)
   {
      result = 1;
      for(o = 0; o < length2; o++)
      {
         if(str1[i + o] != str2[o])
         {
            result = 0;
            break;
         }
      }
      if(result == 1)
      {
         return result;
      }
   }
   return 0;
}

char* strreverse(char *str)
{
   int i, length1;
   length1 = strlength(str);
   
   for(i = 0; i < length1 / 2; i++)
   {
      str[i] = str[length1 - i - 1];
   }
   return str;
}

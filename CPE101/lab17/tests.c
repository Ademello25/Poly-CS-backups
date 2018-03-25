/*
 * Assignment: Lab 17
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "order.h"

int main (int argc, const char * argv[])
{
   int *a;
   int *b;
   int* c;
   int value1 = 5;
   int value2 = 10;
   int value3 = 15;
   
   a = &value1;
   b = &value2;
   
   swap(a, b);
   
   if(*a != 5)
   {
      printf("Error swap: Expected 5, found %d\n", *a);
   }
   
   
   swap(b,a);
   if(*b != 5)
   {
      printf("Error swap: Expected 5, found %d\n", *b);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(a, b ,c);
   
   if( (*a!=5) || (*b!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *a, *b, *c);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(b,a,c);
   
   if( (*b!=5) || (*a!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *b, *a, *c);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(b, c ,a);
   
   if( (*b!=5) || (*c!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *b, *c, *a);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(a, c ,b);
   
   if( (*a!=5) || (*c!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *a, *c, *b);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(c, a ,b);
   
   if( (*c!=5) || (*a!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *c, *a, *b);
   }
   
   
   a = &value1;
   b = &value2;
   c = &value3;
   
   order(c, b ,a);
   
   if( (*c!=5) || (*b!=10))
   {
      printf("Error order: Expected 5, 10, 15, found %d, %d, %d\n", *c, *b, *a);
   }
   
   return 0;
}


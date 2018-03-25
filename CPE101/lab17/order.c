/*
 * Assignment: Lab 17
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "order.h"

void swap(int* val1, int* val2)
{
   int temp;
   if(*val1 > *val2)
   {
      temp = *val1;
      *val1 = *val2;
      *val2 = temp;
   }

}

void order(int* value1, int * value2, int * value3)
{
   swap(value1, value2);
   swap(value2, value3);
   swap(value1, value2);
}

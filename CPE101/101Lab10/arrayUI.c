#include <stdio.h>
#include <stdlib.h>

/* Used to bound the size of the array the program works with */
#define MIN_SIZE 10
#define MAX_SIZE 100

/* Prototypes for functions you must write in arrayFuncs.c */
double sumArray(int array[], int size);
double avgArray(int array[], int size);

/* Prototypes for functions already written and used in this file */
void initArray(int array[], int size);
void printArray(int array[], int size);

/*
 * The beginning of the program!
 */
int main()
{
   int values[MAX_SIZE];
   int size;
   double sum, avg;

   /* This loop lets the user pick how big an array they want to work with.
    * The size is bounded to between MIN_SIZE and MAX_SIZE.
    */
   while(1)
   {
      printf("\nEnter size of array: ");
      scanf("%d", &size);

      if (size < MIN_SIZE || size > MAX_SIZE)
      {
         printf("The size must be between %d and %d, inclusive, please try again\n",
                MIN_SIZE, MAX_SIZE);
      }
      else
      {
         break;
      }
   }

   /* Calls to functions already written to populate the array with
    * psuedo-random values and then display those values.
    */
   initArray(values, size);
   printArray(values, size);

   /* Calls to the functions you must write for this lab.
    * Remember that you can write a minimally implemented version of the
    * functions to get a successful compile - just return some double
    * value - good place to start!
    */
   sum = sumArray(values, size);
   avg = avgArray(values, size);

   /* Display the results your functions return. */
   printf("\nThe sum of all values is %.0f\n", sum);
   printf("\nThe average of all values is %f\n\n", avg);

   return 0;
}

/* Initialized the array passed in with psuedo-random values between
 * 1 and the size (n) of the array.
 */
void initArray(int a[], int n)
{
   int i, seed;

   printf("Enter a seed value for random number generation: ");
   scanf("%d", &seed);

   srand(seed);

   for (i = 0; i < n; i++)
   {
      a[i] = (rand() % n) + 1;
   }
}

/* Printf out the values in the array passed in, one value per line.
 */
void printArray(int a[], int n)
{
   int i;

   printf("\n");

   for (i = 0; i < n; i++)
   {
      printf("   array[%2d] == %3d\n", i, a[i]);
   }
}


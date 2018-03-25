/* This code verifies the following:
 *
 *   1) The functions in landerFuncs.c.
 *
 *   2) The LMState struct in landerUI.h.
 *
 *   3) The constants in landerUI.h.
 *
 * IT DOES NOT VERIFY YOUR USER INTERFACE!
 *
 *   This is left for you to do before handing in your solution. BE SURE to
 *   diff your program's output with the provided solution's output using
 *   a variety of test inputs to make sure your program is correct!
 */
#include <stdio.h>

/* These are the header file you wrote to the assignment specfications */
#include "landerFuncs.h"
#include "landerUI.h"


/* Function prototypes the test functions in this source file */
int test(int pass, const char* msg);
int displayResults(int pass);

int testConstants();
int testStruct();
int testAcceleration();
int testAltitude();
int testVelocity();
int testFuelLevel();

int main()
{
   int pass = 1;

   printf("\nmoonLander Tests...\n\n");

   pass &= testConstants();
   pass &= testStruct();
   pass &= testAcceleration();
   pass &= testAltitude();
   pass &= testVelocity();
   pass &= testFuelLevel();

   return displayResults(pass);
}

int test(int pass, const char* msg)
{
   if (!pass)
   {
      printf("   %s\n", msg);
   }

   return pass;
}

int displayResults(int pass)
{
   if (pass)
   {
      printf("\n");
      printf("Congratulations, you passed all of the tests!\n");
      printf("\n");
      printf("Remember that you must test your user interface against the\n");
      printf("provided solution using redirection and diff. Be sure to test\n");
      printf("with a variety of fuel rates and to check all of the possible\n");
      printf("outcomes!\n");
      
      return 0;
   }
   else
   {
      printf("\n");
      printf("NOT DONE YET! You FAILED one or more of the tests!\n");
      printf("\n");
      printf("You will need to fix these errors before submitting your\n");
      printf("soluton. Don't forget to re-verify your User Interface\n");
      printf("after making any changes!\n\n");

      return 1;
   }
}

int testConstants()
{
   int pass = 1;

   printf("Testing constants...\n");

   pass &= test(G == 1.62, "G is not 1.62");
   pass &= test(INITIAL_ALTITUDE == 1300, "INITIAL_ALTITUDE is not 1300");
   pass &= test(INITIAL_VELOCITY == 0, "INITIAL_VELOCITY is not 0");
   pass &= test(INITIAL_FUEL_LEVEL == 500, "INITIAL_FUEL_LEVEL is not 500");
   pass &= test(INITIAL_FUEL_RATE == 0, "INITIAL_FUEL_RATE is not 0");

   return pass;
}

int testStruct()
{
   int pass = 1;
   LMState state;

   printf("Testing struct LMState\n");

   state.acceleration = 0.0;
   state.altitude = 0.0;
   state.velocity = 0.0;
   state.fuelLevel = 0;
   state.fuelRate = 0;

   return pass;
}

int testAcceleration()
{
   int pass = 1;
   int i;
   int rates[] = {-5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15};
   double expected[12];
   double a;
   char msg[200];

   printf("Testing acceleration function...\n");
   
   for (i = 0; i < 12; i++)
   {
      a = acceleration(rates[i]);
      expected[i] = 1.62 * ((rates[i] / 5.0) - 1);
      sprintf(msg,
              "Incorrect acceleration for fuel rate of %d,\n      expected: %.20f,\n         found: %.20f",
              rates[i], expected[i], a);

      pass &= test(expected[i] == a, msg);
   }

   return pass;
}

int testAltitude()
{
   int pass = 1;
   double a, v, acc, expected;
   char msg[200];

   printf("Testing altitude function...\n");

   a = 2.4;
   v = -0.2;
   acc = -4.2;
   expected = a + v + (acc / 2), 
   sprintf(msg,
           "Incorrect result for altitude(2.4, -0.2, -4.2)\n      expected: %.20f,\n         found: %.20f",
           expected,  
           altitude(2.4, -0.2, -4.2));

   pass &= test(expected == altitude(2.4, -0.2, -4.2), msg);

   a = 2.4;
   v = -0.2;
   acc = -4.5;
   expected = 0.0;
   sprintf(msg,
           "Incorrect result for altitude(2.4, -0.2, -4.5)\n      expected: %.20f,\n         found: %.20f",
           expected, 
           altitude(2.4, -0.2, -4.5));

   pass &= test(expected == altitude(2.4, -0.2, -4.5), msg);

   a = 2.4;
   v = 7.5;
   acc = 4.5;
   expected = a + v + (acc / 2);
   sprintf(msg,
           "Incorrect result for altitude(2.4, 7.5, 4.5)\n      expected: %.20f,\n         found: %.20f",
           expected, 
           altitude(2.4, 7.5, 4.5));

   pass &= test(expected == altitude(2.4, 7.5, 4.5), msg);

   return pass;
}

int testVelocity()
{
   int pass = 1;
   double v, acc, expected, found;
   char msg[200];
   
   printf("Testing velocity function...\n");

   v = -5.67;
   acc = 0.456;
   expected = v + acc;
   found = velocity(-5.67, 0.456);
   sprintf(msg,
           "Incorrect result for velocity(-5.67, 0.456)\n      expected: %.20f,\n         found: %.20f",
           expected,  
           found);

   pass &= test(expected == found, msg);

   v = 5.67;
   acc = -0.456;
   expected = v + acc;
   found = velocity(5.67, -0.456);
   sprintf(msg,
           "Incorrect result for velocity(5.67, -0.456)\n      expected: %.20f,\n         found: %.20f",
           expected,  
           found);

   pass &= test(expected == found, msg);

   return pass;
}

int testFuelLevel()
{
   int pass = 1;
   char msg[200];

   printf("Testing fuelLevel function...\n");

   sprintf(msg,
           "Incorrect result for fuelLevel(7, 8)\n      expected: 0,\n         found: %d",
           fuelLevel(7, 8));

   pass &= test(0 == fuelLevel(7, 8), msg);
   
   sprintf(msg,
           "Incorrect result for fuelLevel(399, -4)\n      expected: 403,\n         found: %d",
           fuelLevel(399, -4));

   pass &= test(403 == fuelLevel(399, -4), msg);

   sprintf(msg,
           "Incorrect result for fuelLevel(399, 2)\n      expected: 397,\n         found: %d",
           fuelLevel(399, 2));

   pass &= test(397 == fuelLevel(399, 2), msg);
   
   return pass;
}





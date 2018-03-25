#include <stdio.h>
#include <math.h>

/* Prototypes of functions written by the students */
double boxVolume(double l, double w, double h);
double boxWeight(double l, double w, double h, double t, double d);
double ballVolume(double r);
double ballWeight(double r, double t, double d);
int ballsFit(double r, double l, double w, double h, double t);

/* Local Prototypes (prototypes for functions found in this file). */
static int testDouble(double expect, double actual, double epsilon, int line);
static int testInt(int expect, int actual, int line);
static void results(int pass);

static int testBoxVolume();
static int testBoxWeight();
static int testBallVolume();
static int testBallWeight();
static int testBallsFit();

int main()
{
   int pass = 1;

   printf("\nPerforming tests...\n");

   pass &= testBoxVolume();
   pass &= testBoxWeight();
   pass &= testBallVolume();
   pass &= testBallWeight();
   pass &= testBallsFit();

   results(pass);

   return !pass;
}

static int testBoxVolume()
{
   int pass = 1;

   pass &= testDouble(81.782989, boxVolume(3.598, 2.981, 7.625), 0.000001, __LINE__);

   return pass;
}

static int testBoxWeight()
{
   int pass = 1;

   pass &= testDouble(0.0, boxWeight(1.0, 1.0, 1.0, 0.0, 5.0), 0.000001, __LINE__);
   pass &= testDouble(0.875, boxWeight(1.0, 1.0, 1.0, 0.25, 1.0), 0.000001, __LINE__);
   pass &= testDouble(13.7314125, boxWeight(2.5, 3.5, 4.5, 0.25, 0.8931), 0.000001, __LINE__);

   return pass;
}

static int testBallVolume()
{
   int pass = 1;

   pass &= testDouble(4.1887902, ballVolume(1.0), 0.000001, __LINE__);
   pass &= testDouble(1653.737508598, ballVolume(7.336), 0.000001, __LINE__);

   return pass;
}

static int testBallWeight()
{
   int pass = 1;

   pass &= testDouble(4.1887902, ballWeight(1.0, 1.0, 1.0), 0.000001, __LINE__);
   pass &= testDouble(0.0, ballWeight(1.0, 0.0, 8.8), 0.000001, __LINE__);
   pass &= testDouble(12.8453547, ballWeight(7.932, .377, .04521), 0.000001, __LINE__);

   return pass;
}

static int testBallsFit()
{
   int pass = 1;

   pass &= testInt(405, ballsFit(3.578, 64.605, 21.669, 107.549, 0.1), __LINE__);
   pass &= testInt(224, ballsFit(3.578, 64.532, 21.596, 107.468, 0.1), __LINE__);

   return pass;
}

static void results(int pass)
{
   if (pass)
   {
      printf("\nCongratulations - you passed all of the tests!\n\n");
      printf("Assuming you did not violate any particular requirements of the\n");
      printf("assignemnt, your grade will be based on when you hand it in, minus\n");
      printf("any deductions for the quality of you implementation. Quality includes,\n");
      printf("but is not limited to, meeting the required coding style guidelines,\n");
      printf("for the course. Quality may also includes the efficiency of your\n");
      printf("solution when an assignment has specific efficiency requirements\n"); 
      printf("and/or your solution is unusually slow, inelegant, or inappropriate.\n\n");
   }
   else 
   {
      printf("\nYou're not done yet!\n\n");
      printf("Your solution failed the test(s) identified above. Remember to fix the\n");
      printf("first error first as subsequent errors my be the result of an earlier\n");
      printf("error! You may also ask you instructor or the tutors in the Computer\n");
      printf("Science Department's Tutoring Center, as necessary, for help!\n");
   }
}

static int testDouble(double expect, double actual, double epsilon, int line)
{
   if (fabs(expect - actual) >= epsilon)
   {
      fprintf(stderr,
              "   Failed test at line %d, expected %f (+/-%f), found %.8f\n",
              line,
              expect,
              epsilon,
              actual);

      return 0;
   }

   return 1;
}

static int testInt(int expect, int actual, int line)
{
   if (expect != actual)
   {
      fprintf(stderr,
              "   Failed test at line %d, expected %d, found %d\n",
              line,
              expect,
              actual);

      return 0;
   }

   return 1;
}

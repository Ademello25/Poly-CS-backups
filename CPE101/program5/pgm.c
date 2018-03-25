#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

/* Constants */
#define MAX_SIZE 500

/* function prototypes */
void checkArgs(int argc);
void checkOption(const char input[]);
int smoothHelper(int input[MAX_SIZE][MAX_SIZE], int height, int width, int row1, int col1);

/* the main function */
int main(int argc, char *argv[])
{   
   char carry[10];
   char c;
   const char magNum[] = {'P', '2', '\0'};
   int pixel[MAX_SIZE][MAX_SIZE];
   int i, width, height, num, x, maxPix, pixNum, p, z;
   int j, k, l, m, a, b;
   int check;
   FILE *in, *out;

   x = 0;
   p = 0;
   i = 0;

   checkArgs(argc);                                            /* correct # of args? */
   checkOption(argv[1]);                                       /* correctly spelled options? */
   in = fopen(argv[2], "r");                                   /* open input file */
   if(in == NULL)
   {
      perror("pgm");
      exit(EXIT_FAILURE);
   }


   /* reading */
   /* checks if the Magic Number is correct */
   for(i = 0; i<2; i++)
   {
      fscanf(in, "%c", &c);
      carry[i] = c;
   }
   if(strcmp(carry, magNum) != 0)
   {
      fprintf(stderr, "pgm: Invalid PGM File magic number\n");
      exit(EXIT_FAILURE);
   }
   /* scanning for comments */
   for(i=0; (fscanf(in, "%c", &c) != EOF); i++)
   {
      if(c == '#')
      {
         while(fgetc(in) != '\n')
         {}
      }
      else
      {
         ungetc(c, in);
         fscanf(in, "%d", &num);
         if( (num != 0) || (x == 3) )
         {
            x++;
            if(x == 1)
            {
               width = num;                                       /* width is found */
               z = fgetc(in);
               if(z != 10)
               {
                  ungetc(z, in);
               }
               
            }
            else if(x == 2)
            {
               height = num;                                      /* height is found */
               z = fgetc(in);
               if(z != 10)
               {
                  ungetc(z, in);
               }
            }
            else if(x == 3)
            {
               maxPix = num;                                      /* max pixel value is found */
               z = fgetc(in);
               if(z != 10)
               {
                  ungetc(z, in);
               }
            }
            else if(x == 4)                           /* the first pixel value has been found */
            {
               ungetc(num, in);
               break;
            }
         }
      }
   }
   pixNum = width * height;
   for(j = 0; !feof(in); j++)
   {
      for(k = 0; k < width; k++)
      {
         if(fscanf(in, "%d", &num) == EOF)
         {
            break;
         }
         fgetc(in);
         pixel[j][k] = num;
         p++;
      }
   }
   if(pixNum != p)                                 /* check if # of values found = max * width */
   {
      fprintf(stderr, "pgm: Failed reading input\n");
   }

   fclose(in);

   /* reformat ... doesn't do anything */
   if(strcmp(argv[1], "reformat") == 0)
   {
   }
   
   /* sharpen */
   else if(strcmp(argv[1], "sharpen") == 0)
   {
      check = (maxPix/2);

      for(j = 0; j < height; j++)
      {
         for(k = 0; k < width; k++)
         {
            if(pixel[j][k] <= check)
            {
               if(pixel[j][k] != 0)
               {
                  pixel[j][k] -= 1;
               }
            }
            else if(pixel[j][k] > check)
            {
               if(pixel[j][k] != maxPix)
               {
                  pixel[j][k] += 1;
               }
            }
         }
      }
   }

   /* smooth */
   else if(strcmp(argv[1], "smooth") == 0)
   {
      pixel[0][0] = smoothHelper(pixel, 2, 2, 0, 0);  /* TL corner */
      pixel[0][width-1] = smoothHelper(pixel, 2, 2, 0, (width-2));   /* TR corner */
      pixel[height-1][width-1] = smoothHelper(pixel, 2, 2, (height-2), (width-2)); /* BR corner */
      pixel[height-1][0] = smoothHelper(pixel, 2, 2, (height-2), 0); /* BL corner */

      /* top edge */
      for(k = 0; k < (width-2); k++)
      {
         pixel[0][k+1] = smoothHelper(pixel, 2, 3, 0, k);
      }
      /* right edge */
      for(j = 0; j < (height-2); j++)
      {
         pixel[j+1][width-1] = smoothHelper(pixel, 3, 2, j, (width-2));
      }
      /* bottom edge */
      for(m = 0; m < (width-2); m++)
      {
         pixel[height-1][m+1] = smoothHelper(pixel, 2, 3, (height-2), m);
      }
      /* left edge */
      for(l = 1; l < (height-1); l++)
      {
         pixel[l][0] = smoothHelper(pixel, 3, 2, l-1, 0);
      }
      /* inside pixels */
      for(a = 1; a < (height-1); a++)
      {
         for(b = 1; b < (width-1); b++)
         {
            pixel[a][b] = smoothHelper(pixel, 3, 3, a-1, b-1);
         }
      }
   }
   
   out = fopen(argv[3], "w");
   if(out == NULL)
   {
      perror("pgm");
      exit(EXIT_FAILURE);
   }
   fprintf(out, "P2\n");
   fprintf(out, "# CREATOR: CPE 101 pgm filter Version 2.0\n");
   fprintf(out, "%d %d %d\n", width, height, maxPix);
   for(j = 0; j < height; j++)
   {
      for(k = 0; k < width; k++)
      {
         num = pixel[j][k];
         fprintf(out, "%d\n", num);
      }
   }

   return 0;
}

/* Check the argument count */
void checkArgs(int argc)
{
   if(argc != 4)
   {
      fprintf(stderr, "Usage : pgm OPTION [reformat|sharpen|smooth] inFile outFile\n");
      exit(EXIT_FAILURE);
   }
}

/* Verify the options */
void checkOption(const char input[])
{
   if( (strcmp(input, "reformat") != 0) &&
       (strcmp(input, "sharpen") != 0)  &&
       (strcmp(input, "smooth") != 0) )
   {
      fprintf(stderr, "pgm: %s is not a supported option.\n", input);
      fprintf(stderr, "Usage : pgm OPTION [reformat|sharpen|smooth] inFile outFile\n");
      exit(EXIT_FAILURE);
   }
}

/* Calculate the mean for smooth */
int smoothHelper(int input[MAX_SIZE][MAX_SIZE], int height, int width, int row1, int col1)
{
   int r, c, sum;
   double result;

   result = 0;
   for(r = row1; r < (row1 + height); r++)
   {
      for(c = col1; c < (col1 + width); c++)
      {
         result += input[r][c];
      }
   }
   sum = (result /(height * width)) + 0.5;
   return sum;
}

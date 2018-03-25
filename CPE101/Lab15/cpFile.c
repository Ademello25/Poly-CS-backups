#include <stdio.h>
#include <stdlib.h>

/*
 * Assignment: Lab15
 * Course: CPE101
 * Author: Alexander DeMello
 */


/* FUNCTION PROTOTYPE
 *
 * Function Name: checkArgs
 *
 * Description: Checks to make sure the program was run with exactly three (3)
 *              arguments. If not it prints out the following usage message
 *              using fprintf as follows:
 *
 *                 fprintf(stderr, "Usage: %s fromFile toFile\n", argv[0]);
 *
 *              and then stops the program by calling exit(EXIT_FAILURE). 
 *              ^^^^^^^^^^^^^^
 *
 *              NOTE:
 *
 *                 1) The arg[0] is always the executable's current name. Try
 *                    renaming your program and rerunning it!
 *
 *                 2) You must #include <stdlib.h> to be able to use exit.
 *
 * Precondtions: None
 *
 * Parameters:
 *    argc - The number of arguments the program was run with.
 *    argv - The actual arguments the program was run with.
 *
 * Return: void 
 */
 void checkArgs(int argc, char *argv[]);

/* FUNCTION PROTOTYPE
 *
 * Function Name: attemptClose
 *
 * Description: Using fclose, this function attempts to close the specified
 *              file. If the attempt results in an error the function calls
 *              perror(__FILE__). Note there are two underscores before and
 *              after the word FILE. It does NOT call the exit function - this
 *              is left to the caller!
 *
 * Precondtions: None
 *
 * Parameters:
 *
 *    file - A pointer to a FILE structure (defined in stdio.h).
 *
 * Return: The result of the call to the fclose function.
 */
int attemptClose(FILE *file);

/* FUNCTION PROTOTYPE
 *
 * Function Name: copyFile
 *
 * Description: Copies the file named in argv[1] to the file named in argv[2].
 *              If one or both of the files can't be opened it reports an error
 *              and exits. If any error occurs while writing to the output file
 *              it reports an error and exits.
 *
 *              NOTE:
 *
 *                 1) Before exiting due to any error the function attempts
 *                    to close any previously opened files using the
 *                    attemptClose function (specified below). Note that the
 *                    attemptClose funciton prints that error too!
 *
 *                 2) Any fopen, fclose, or fscanf errors are reported by
 *                    calling perror(__FILE__). Note that there are two under-
 *                    score characters before and after the word FILE in the
 *                    call to perror.
 *
 *                 3) Any fprintf errors are reported by printing an error
 *                    message as follows:
 *
 *                       fprintf(stderr, "%s: Failed fprintf to destination file\n", __FILE__); 
 *
 *                 3) The calls to exit look like this: exit(EXIT_FAILURE);
 *
 * Precondtions: None
 *
 * Parameters:
 *
 * Return: 
 */
void copyFile(char *argv[]);

/*
 * The main thing - Now with command line argument support!
 *
 * DON'T CHANGE THIS FUNCTION! Instead, complete the functions called from main
 * so that the program copies one file to another. See the prototype comments
 * for the functions (above) for details on how they should behave.
 */
int main(int argc, char *argv[])
{
   checkArgs(argc, argv);
   copyFile(argv);

   return 0;
}

/* 
 * TODO: Implement this function - see prototype comment for details!
 */
void checkArgs(int argc, char *argv[])
{
   if(argc != 3)
   {
      fprintf(stderr, "Usage: %s fromFile toFile\n", argv[0]);
      exit(EXIT_FAILURE);
   }
}

/* 
 * TODO: Implement this function - see prototype comment for details!
 */
int attemptClose(FILE *file)
{
   fclose(file);
   
   if(fclose(file) < 0)
   {
      perror(__FILE__);
      fprintf(stderr, "failed to close the file\n");
   }
   return 0;
   
   
}

/* 
 * TODO: Implement this function - see prototype comment for details!
 */
void copyFile(char *argv[])
{
   int i;
   char c;
   FILE *to;
   FILE *from;
   
   to = fopen(argv[2], "w");
   ferror(to);
   feof(to);
   if((ferror(to) != 0))
      {
         perror(__FILE__);
         fprintf(stderr, "%s: Failed to open in write mode file\n", __FILE__);
         exit(EXIT_FAILURE);
      }
   
   from = fopen(argv[1], "r");
   ferror(from);
   feof(from);
   if((ferror(from) != 0))
   {
      perror(__FILE__);
      fprintf(stderr, "%s: Failed to open in read mode\n", __FILE__);
      exit(EXIT_FAILURE);

   }
   
   for( i =0; fscanf(from, "%c", &c) != EOF; i ++)
   {
      fscanf(from, "%c", &c);
      ferror(from);
      feof(from);
      if((ferror(from) != 0))
      {
         perror(__FILE__);
         fprintf(stderr, "%s: Failed to scan the file\n", __FILE__);
         exit(EXIT_FAILURE);

      }
      fprintf(to, "%c", c);
      ferror(to);
      feof(to);
      if(ferror(to) != 0)
      {
      perror(__FILE__);
      fprintf(stderr, "%s: Failed printf to destination file\n", __FILE__);
      exit(EXIT_FAILURE);
      }
   }
   attemptClose(to);
   attemptClose(from);
}


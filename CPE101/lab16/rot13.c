/*
 * Assignment: Lab 16
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void encrypt(char *argv[]);
void checkArgs(int argc, char *argv[]);
int attemptClose(FILE *file);

int main (int argc, char * argv[])
{
   checkArgs(argc, argv);
   encrypt(argv);
   
   return 0;
}


void checkArgs(int argc, char *argv[])
{
   if(argc != 3)
   {
      fprintf(stderr, "Usage: %s fromFile toFile\n", argv[0]);
      exit(EXIT_FAILURE);
   }
}

void encrypt(char *argv[])
{
   int i;
   char c;
   FILE *to;
   FILE *from;
   
   to = fopen(argv[2], "w");
   if((ferror(to) != 0))
   {
      perror("");
      fprintf(stderr, "%s: Failed to open in write mode file\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   
   from = fopen(argv[1], "r");
   if((ferror(from) != 0))
   {
      perror("");
      fprintf(stderr, "%s: Failed to open in read mode\n", argv[1]);
      exit(EXIT_FAILURE);
      
   }
   while( (i=fscanf(from, "%c", &c))!=EOF)
   {
      if((ferror(from) != 0))
      {
         perror("");
         fprintf(stderr, "%s: Failed to scan the file\n", argv[1]);
         exit(EXIT_FAILURE);
         
      }
      fprintf(to, "%c", (isupper(c)? (((c-65+13)%26)+65) : (((c-97+13)%26)+97)));
      if(ferror(to) != 0)
      {
         perror("");
         fprintf(stderr, "%s: Failed printf to destination file\n", argv[2]);
         exit(EXIT_FAILURE);
      }
   }
   if(ferror(from) || !feof(from))
   {
      fprintf(stderr,"Error reading from File");
   } 
  /* attemptClose(to);
   attemptClose(from); */
}

int attemptClose(FILE *file)
{
   fclose(file);
   
   if(fclose(file) < 0)
   {
      perror("");
      fprintf(stderr, "failed to close the file\n");
   }
   return 0;
}



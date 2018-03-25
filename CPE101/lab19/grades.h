
/*
 * Assignment: LAB 19
 * Course: CPE101
 * Author: Alexander DeMello
 */

#ifndef lab_19_grades_h
#define lab_19_grades_h

#define MAX_FIRST 21
#define MAX_LAST 31

typedef struct
{
   char first[MAX_FIRST];
   char last[MAX_LAST];
   int course;
   double grade;
}CourseGrade;

int courseResults(CourseGrade * array1,int size,int course,double * maxGrade,double * avgGrade,
                  double*minGrade);
int studentGPA(CourseGrade * array1, int size, const char *firstName, const char* lastName,
               double* GPA);

#endif

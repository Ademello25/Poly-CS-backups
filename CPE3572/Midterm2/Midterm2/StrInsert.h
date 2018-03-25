/**
 * Replace every instance of 'where' in 'str' with the
 * entire contents of 'insert'.
 *
 * Example:
 *   str: 'abc'
 *   insert: 'def'
 *   where: 'b'
 *   expected output: 'adefc'
 */
char * StrInsert(char *str, char *insert, char where)
{
   int i, j, count = 0;
   char* result, *resultCursor;
   char* temp = str;

   for(i=0; i < strlen(str); i++) {
      if( (*(str + i)) == where) {
       count++;
     }
   }

   result = calloc(1, strlen(str) + 1 +(count*strlen(insert))-(count));
   resultCursor = result;

   while(*temp) {
      if( *temp == where) {   
         memmove(result, insert, strlen(insert));
         for(j = 0; j < strlen(insert); j++) {
            result++;
         }  
      }
      else {
         *result = *temp;
         result++;
      }
      temp++;
   }

   return resultCursor;
}

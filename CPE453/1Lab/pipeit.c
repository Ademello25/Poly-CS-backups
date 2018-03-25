/* Alexander DeMello
 * CPE 453 Lab 01
 */

int main (int argc, char *argv[]) {
   int pipeFD[2], childLS, childSort, output;

   pipe(pipeFD);

   /* child process for running LS*/
   if(!(childLS = fork())) {

      /* 1 is write, 0 is read for pipeFD */
      dup2(pipeFD[1], 1);
      
      /*close the excess pipe that is left when i have replaced stdout*/
      close(pipeFD[1]);

      /*need to terminate execlp iwth a NULL, need 2 ls for formatting */
      execlp("ls", "ls", NULL);

   } 
   else {
      if(!(childSort = fork())) {
         
         /* open the output file */
         output = open("outfile", O_WRONLY | O_CREAT | O_TRUNC, 0777);
         
         /*redirect pipe read*/
         dup2(pipeFD[0], 0);
         
         /*redirect pipe write */
         dup2(pipeFD[1], 1);
         
         /*close write pipe */
         close(pipeFD[1]);

         exec("sort", "sort", NULL);
      }
      else {
         /*not correct */
         wait();
      }
   }


}

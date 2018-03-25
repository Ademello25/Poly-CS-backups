#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
 
int main()
{
   int f1, f2;
   f1 = open("testFile.txt", O_RDWR | O_CREAT | O_TRUNC | O_NONBLOCK, S_IRUSR | S_IWUSR);
   printf("%d\n", f1);
   f2 = open("testadjklsfadfls;kl;.txt", O_RDWR | O_CREAT | O_TRUNC | O_NONBLOCK, S_IRUSR | S_IWUSR);
   printf("%d\n", f1);
}

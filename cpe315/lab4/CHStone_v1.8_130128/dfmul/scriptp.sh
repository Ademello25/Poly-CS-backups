gcc -pg -03 -o df-prof dfmul.c 
./df-prof
gprof ./df-prof | less

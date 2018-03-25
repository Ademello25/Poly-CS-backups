gcc -pg -03 -o df-prof dfsin.c 
./df-prof
gprof ./df-prof | less

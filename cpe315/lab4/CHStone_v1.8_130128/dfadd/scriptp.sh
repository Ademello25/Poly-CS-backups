gcc -pg -01 -o df-prof dfadd.c
./df-prof
gprof ./df-prof | less

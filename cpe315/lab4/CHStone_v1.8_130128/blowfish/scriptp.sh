gcc -pg -01 -o df-prof bf.c
./df-prof
gprof ./df-prof | less

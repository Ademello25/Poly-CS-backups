gcc -pg -O3 -o df-prof main.c
./df-prof
gprof ./df-prof | less

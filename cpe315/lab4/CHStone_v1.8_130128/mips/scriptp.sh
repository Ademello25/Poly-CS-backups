gcc -pg -O3 -o df-prof mips.c
./df-prof
gprof ./df-prof | less

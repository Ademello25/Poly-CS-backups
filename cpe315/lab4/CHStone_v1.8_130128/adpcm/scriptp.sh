gcc -pg -00 -o df-prof adpcm.c
./df-prof
gprof ./df-prof | less

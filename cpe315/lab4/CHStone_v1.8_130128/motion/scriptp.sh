gcc -pg -02 -o df-prof mpeg2.c
./df-prof
gprof ./df-prof | less

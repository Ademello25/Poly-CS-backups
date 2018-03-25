gcc -pg -03 -o df-prof gsm.c 
./df-prof
gprof ./df-prof | less

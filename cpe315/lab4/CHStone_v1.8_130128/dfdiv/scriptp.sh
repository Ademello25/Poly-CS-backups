gcc -pg -02 -o df-prof dfdiv.c  
./df-prof
gprof ./df-prof | less

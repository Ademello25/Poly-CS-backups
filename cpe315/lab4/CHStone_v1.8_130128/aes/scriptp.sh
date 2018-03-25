gcc -pg -01 -o df-prof aes.c
./df-prof
gprof ./df-prof | less

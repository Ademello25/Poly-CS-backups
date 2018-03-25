gcc -pg -02 -o df-prof sha_driver.c
./df-prof
gprof ./df-prof | less

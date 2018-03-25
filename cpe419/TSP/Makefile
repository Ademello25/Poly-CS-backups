CFLAGS = -g -qopenmp -O3

mm: TSP.o
	icc -o partsp ${CFLAGS} TSP.c

clean:
	rm -f *.o partsp 

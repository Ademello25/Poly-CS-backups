CPP = gcc -fPIC
LDFLAGS = -lm

malloc: ./libmalloc.a ./libmalloc.so
	

gdb-malloc: debug/libmalloc.so
	

all: malloc gdb-malloc intel-all
	
libmalloc.a: malloc.o
	ar r libmalloc.a malloc.o

libmalloc.so: malloc.o
	$(CPP) $(LDFLAGS) -shared -o libmalloc.so malloc.o

malloc.o: malloc.c
	$(CPP) malloc.c -c -o malloc.o

intel-all: lib/libmalloc.so lib64/libmalloc.so

lib/libmalloc.so: lib malloc32.o
	$(CPP) $(LDFLAGS) -m32 -shared -o $@ malloc32.o

lib64/libmalloc.so: lib64 malloc64.o
	$(CPP) $(LDFLAGS) -shared -o $@ malloc64.o

lib:
	mkdir lib

lib64:
	mkdir lib64

malloc32.o: malloc.c
	$(CPP) -m32 -c -o malloc32.o malloc.c

malloc64.o: malloc.c
	$(CPP) -m64 -c -o malloc64.o malloc.c


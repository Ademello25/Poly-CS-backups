CFLAGS = -O2 -Wall

ifdef COLMAJ
CFLAGS += -D COLMAJ
endif

ifdef DOUBLE
CFLAGS += -D DOUBLE
endif

mm: mm.o
	gcc -o mm ${CFLAGS} mm.c

CC=icc
CXX=icpc
NVXX=nvcc
CFLAGS=-std=c++0x -Wall -pedantic -O3 -fopenmp -qopenmp -qoffload
INC=-I$(EIGEN3_INCLUDE_DIR) -I$(GLEW_DIR)/include -I$(GLFW_DIR)/include
LIB=$(GLEW_DIR)/lib/libGLEW.a $(GLFW_DIR)/release/src/libglfw3.a -xHost -lGL -lX11 -lXi -lXrandr -lXxf86vm -lXinerama -lXcursor -lrt -lm -lcudart

#ifdef NVIDIA
#	$(NVXX) $(CFLAGS) $(INC) ../src/*.cpp $(LIB) -o cudaLab09
#else
all:
	$(CXX) $(CFLAGS) $(INC) ../src/*.cpp $(LIB) -o Lab09

clean:
	rm -f *~ *.o a.out

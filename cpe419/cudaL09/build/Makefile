CC=icc
CXX=icpc
NVXX=nvcc
CFLAGS=-std=c++0x -Wall -pedantic -O3 
INC=-I$(EIGEN3_INCLUDE_DIR) -I$(GLEW_DIR)/include -I$(GLFW_DIR)/include
LIB=$(GLEW_DIR)/lib/libGLEW.a $(GLFW_DIR)/release/src/libglfw3.a -xHost -lGL -lX11 -lXi -lXrandr -lXxf86vm -lXinerama -lXcursor -lrt -lm -lcudart -lcurand

NVFLAGS=-O3 -g -ccbin g++ -m64 -lcudart -gencode arch=compute_20,code=sm_20 -gencode arch=compute_30,code=sm_30 -gencode arch=compute_35,code=sm_35 -gencode arch=compute_37,code=sm_37 -gencode arch=compute_50,code=sm_50 -gencode arch=compute_52,code=sm_52 -gencode arch=compute_52,code=compute_52 -lcudart -lcurand

#ifdef NVIDIA
#	$(NVXX) $(CFLAGS) $(INC) ../src/*.cpp $(LIB) -o cudaLab09
#else
all:
	$(NVXX) -lcublas $(NVFLAGS) -c ../src/cudacuda.cu
	$(CXX) $(CFLAGS) $(INC) cudacuda.o ../src/*.cpp $(LIB) -o Lab09

clean:
	rm -f *~ *.o a.out

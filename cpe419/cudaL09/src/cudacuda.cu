#include <stdlib.h>
#include "cudacuda.h"
#include "cuda_runtime.h"
#include <cublas_v2.h>
#include "helper_cuda.h"

XYZ *cuLocs;
XYZ *cuVels;
double *cuMass;

XYZ *cuForces;

int partCount;

double parth, parte2;

dim3 sblocks;
dim3 sthreads;
dim3 fblocks;
dim3 fthreads;

__global__ void stepParticles(XYZ *locs, XYZ *vels, XYZ *forces, double *mass, double h, int numParts) {
    int i = blockIdx.x * 1024 + threadIdx.x;

    if (i < numParts) {
      XYZ *l = locs + i;
      XYZ *v = vels + i;
      XYZ f = forces[i];
      double m = mass[i];
    
      v->x += h * f.x / m;
      v->y += h * f.y / m;
      v->z += h * f.z / m;
    
      l->x += h * v->x;
      l->y += h * v->y;
      l->z += h * v->z;
    }
}

__global__ void calculateForces(XYZ *locs, XYZ *forces, double *mass, double e2, int numParts)
{
  int tr = blockIdx.x * 1024 + threadIdx.x;

  int i = tr / numParts;
  int j = tr % numParts;
  
  //for (int i = 0; i < parts.size(); i++)
  //for (int j = i + 1; j < parts.size(); j++)
  if (i < j && i < numParts && j < numParts) {
    double massMult = mass[i] * mass[j];
    XYZ l1 = locs[i];
    XYZ l2 = locs[j];
    XYZ disp;
    disp.x = l2.x - l1.x;
    disp.y = l2.y - l1.y;
    disp.z = l2.z - l1.z;
    double dispNorm = sqrt(pow(disp.x, 2) + pow(disp.y, 2) + pow(disp.z, 2));

    double calc = massMult / sqrt(pow(pow(dispNorm, 2) + e2, 3));

    XYZ *f1 = forces + i;
    XYZ *f2 = forces + j;
    f1->x += calc * disp.x;
    f1->y += calc * disp.y;
    f1->z += calc * disp.z;

    f2->x += calc * -disp.x;
    f2->y += calc * -disp.y;
    f2->z += calc * -disp.z;
  }
}

void cudaStepParticles(XYZ *locs) {
  checkCudaErrors(cudaMemset(cuForces, 0, sizeof(XYZ) * partCount));  

  calculateForces<<<fblocks, fthreads>>>(cuLocs, cuForces, cuMass, parte2, partCount);
  stepParticles<<<sblocks, sthreads>>>(cuLocs, cuVels, cuForces, cuMass, parth, partCount);
  checkCudaErrors(cudaMemcpy(locs, cuLocs, sizeof(XYZ) * partCount, cudaMemcpyDeviceToHost)); 
}


void cudaCleanup() {
  checkCudaErrors(cudaFree(cuLocs));
  checkCudaErrors(cudaFree(cuVels));
  checkCudaErrors(cudaFree(cuMass));
  checkCudaErrors(cudaFree(cuForces));
}

void cudaInitParticles(XYZ *locs, XYZ *vels, double *mass, 
                            int numParts, double hin, double e2in) {
  partCount = numParts;
  parth = hin;
  parte2 = e2in;
  int tCount = numParts * (numParts + 1) / 2;

  dim3 fthreadMake(1024);
  fthreads = fthreadMake;
  dim3 fblockMake(tCount / 1024 + 1);
  fblocks = fblockMake;

  dim3 sthreadMake(1024);
  sthreads = sthreadMake;
  dim3 sblockMake(numParts * numParts / 1024 + 1);
  sblocks = sblockMake;

  checkCudaErrors(cudaMalloc((void **) &cuLocs, sizeof(XYZ) * numParts));
  checkCudaErrors(cudaMemcpy(cuLocs, locs, sizeof(XYZ) * numParts, cudaMemcpyHostToDevice));
  
  checkCudaErrors(cudaMalloc((void **) &cuVels, sizeof(XYZ) * numParts));
  checkCudaErrors(cudaMemcpy(cuVels, vels, sizeof(XYZ) * numParts, cudaMemcpyHostToDevice));

  checkCudaErrors(cudaMalloc((void **) &cuMass, sizeof(double) * numParts));
  checkCudaErrors(cudaMemcpy(cuMass, mass, sizeof(double) * numParts, cudaMemcpyHostToDevice));
  
  // will get set to 0 at start of actual run
  checkCudaErrors(cudaMalloc((void **) &cuForces, sizeof(XYZ) * numParts));
}





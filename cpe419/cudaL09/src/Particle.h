#pragma once
#ifndef _PARTICLE_H_
#define _PARTICLE_H_

#include <memory>
#include <vector>
#include <list>
#include <cstdlib>
#include <cmath>
#include <limits>

#define GLEW_STATIC
#include <GL/glew.h>

#define EIGEN_DONT_ALIGN_STATICALLY
#include <Eigen/Dense>

#include "cudacuda.h"

double randRange(double l, double h);
double generateGaussianNoise(double mu, double sigma);

class MatrixStack;
class Program;
class Texture;

class Particle
{
public:
	Particle();
	virtual ~Particle();

	// OpenGL methods
	void init();
	void draw(XYZ loc, std::shared_ptr<Program> prog, std::shared_ptr<MatrixStack> MV) const;
	
	// Getters
	int getId() const { return id; }
  Eigen::Vector3f getColor() const { return color.segment<3>(0); }
	float getRadius() const { return radius; }
	
	// Setters
  void setId(int idIn) { id = idIn; }
	void setColor(const Eigen::Vector3f &c) { color.segment<3>(0) = c; }
	void setRadius(float r) { radius = r; }
	
private:
	// For physics
  int id;

	// For display only
	float radius;
	Eigen::Vector4f color;
	std::vector<float> posBuf;
	std::vector<float> texBuf;
	std::vector<unsigned int> indBuf;
	GLuint posBufID;
	GLuint texBufID;
	GLuint indBufID;
};

#endif

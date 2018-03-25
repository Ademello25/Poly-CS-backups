#define MILE_PER_KMS 1 / 1.609
#define KMS_PER_MILE 1.609

double milesToKilometers(double miles)
{
  return KMS_PER_MILE * miles;  
}
double kilometersToMiles(double kms)
{
  return MILE_PER_KMS * kms;
}

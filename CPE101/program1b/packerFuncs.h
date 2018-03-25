#define PI 3.14159265

/* FUNCTION PROTOTYPE
 * 
 * Function Name: boxVolume
 * 
 * Preconditions: The caller must use the correct units of measurement
 * 
 * Parameters:
 *    l = The length of the box
 *    w = The width of the box
 *    h = The height of the box
 *    
 * Return: This function will return the volume of the box described
 */
double boxVolume(double l, double w, double h);

/* FUNCTION PROTOTYPE
 *
 * Function Name: boxWeight
 *
 * Preconditions: The caller must use the correct units of measurement
 * 
 * Parameters:
 *    l = the length of the box
 *    w = the width of the box
 *    h = the height of the box
 *   th = the thickness of the walls of the box
 *    d = the density of the material that the box is made out of
 *
 * Return: This function will return the weight of the box described
 */
double boxWeight(double l, double w, double h , double th, double d);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: ballVolume
 * 
 * Preconditions: The caller must use the correct units of measurement
 *
 * Parameters:
 *    r = The radius of the ball(sphere)
 *
 * Return: This function will return the volume of the ball described
 */
double ballVolume(double r);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: ballWeight
 *
 * Preconditions: The caller must use the correct units of measurement
 *
 * Parameters:
 *    r = The radius of the ball(sphere)
 *   th = The thickness of the material that the ball is made out of
 *    d = The density of the material that the ball is made out of
 * 
 * Return: This function will return the weight of the ball described
 */
double ballWeight(double r, double th, double d);

/* FUNCTION PROTOTYPE
 * Function Name: ballsFit
 * 
 * Preconditions: The caller must use the correct units of measurement
 *
 * Parameters:
 *    r = The radius of the balls(sphere)
 *    l = The length of the box that balls are being placed in
 *    w = The width of the box that balls are being placed in 
 *    h = The height of the box that balls are being placed in
 *   th = The thickness of the material that the box is made out of 
 * Return:This function will return the number of balls that will fit in the box
 */
int ballsFit(double r, double l, double w, double h, double th);

/* FUNCTION PROTOTYPE
 * Function Name: usedSpace
 * 
 * Preconditions: The caller must use correct units of measurement
 *
 * Parameters:
 *    di = The diameter of the balls within the box
 *     l = The length of the box
 *     w = The width of the box 
 *     h = The height of the box
 *blsfit = The number of balls that fit within the box
 *intvolume = The volume of the interior of the box
 *
 * Return: This function will return the area that described balls will occupy 
 */
double usedSpace(double di, double l, double w, double h, int blsfit, double intvolume);

/* FUNCTION PROTOTYPE
 * Function Name: iVolume
 * 
 * Preconditions: The caller must use correct units of measurement
 *
 * Parameters:
 *    l = the length of the box
 *    w = the width of the box
 *    h = the height of the box
 *   th = the thickness of the material the box is made of
 *
 * return = The volume of the interior of the box
 */
double iVolume(double l, double w, double h, double th);

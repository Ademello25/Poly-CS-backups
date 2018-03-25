/**
 * JUnit tests for RPN
 *
 * @author Kurt Mammen
 * @version 10/07/2012 Developed for CPE 103 Program 2 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class RPNAcceptanceTests
{
   @Test
   public void verifySuperClass()
   {
      assertTrue(RPN.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces()
   {
      Class[] faces = RPN.class.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void verifyFields()
   {
      Field[] fields = RPN.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }

   @Test
   public void verifyConstructors()
   {
      Constructor[] cons = RPN.class.getDeclaredConstructors();
      assertTrue(cons.length == 1);
   }

   @Test
   public void verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = RPN.class.getDeclaredMethods();

      for (Method m : meths)
      {
         if (Modifier.isPublic(m.getModifiers()))
         {
            countPublic++;
         }
         else if (Modifier.isProtected(m.getModifiers()))
         {
            countProtected++;
         }
         else if (Modifier.isPrivate(m.getModifiers()))
         {
            countPrivate++;
         }
         else
         {
            countPackage++;
         }
      }

      assertTrue(countPublic == 3);
      assertTrue(countProtected == 0);
      assertTrue(countPackage == 0);
   }

   @Test
   public void evaluateRPNBasicAdd()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7 +"), 12.0));
      assertTrue(approx(RPN.evaluateRPN("5.1 7 +"), 12.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 +"), 12.1));
      assertTrue(approx(RPN.evaluateRPN("5.1 7.1 +"), 12.2));
      
      assertTrue(approx(RPN.evaluateRPN("5.1 -7.2 +"), -2.1));
      assertTrue(approx(RPN.evaluateRPN("-5.1 7.2 +"), 2.1));
      assertTrue(approx(RPN.evaluateRPN("-5.1 -7.2 +"), -12.3));
   }

   @Test
   public void evaluateRPNComplexAdd()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7.1 + 11 +"), 23.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 + +"), 23.1));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 + 11 + -3 +"), 20.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 -3 + + +"), 20.1));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 + -11 + 3 +"), 4.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 -11 3 + + +"), 4.1));
      
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 + 11 + 3 +"), 11.9));
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 11 3 + + +"), 11.9));
      
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 + 11 + 3 +"), 16.1));
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 11 3 + + +"), 16.1));
   }

   @Test
   public void evaluateRPNBasicSub()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7 -"), -2.0));
      assertTrue(approx(RPN.evaluateRPN("5.1 7 -"), -1.9));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 -"), -2.1));
      assertTrue(approx(RPN.evaluateRPN("5.1 7.1 -"), -2.0));
      
      assertTrue(approx(RPN.evaluateRPN("5.1 -7.2 -"), 12.3));
      assertTrue(approx(RPN.evaluateRPN("-5.1 7.2 -"), -12.3));
      assertTrue(approx(RPN.evaluateRPN("-5.1 -7.2 -"), 2.1));
   }

   @Test
   public void evaluateRPNComplexSub()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7.1 - 11 -"), -13.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 - -"), 8.9));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 - 11 - -3 -"), -10.1));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 -3 - - -"), 11.9));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 - -11 - 3 -"), 5.9));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 -11 3 - - -"), -16.1));
      
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 - 11 - 3 -"), -1.9));
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 11 3 - - -"), 20.1));
      
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 - 11 - 3 -"), -26.1));
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 11 3 - - -"), -4.1));
   }

   @Test
   public void evaluateRPNBasicMul()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7 *"), 35.0));
      assertTrue(approx(RPN.evaluateRPN("5.1 7 *"), 35.7));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 *"), 35.5));
      assertTrue(approx(RPN.evaluateRPN("5.1 7.1 *"), 36.21));
      
      assertTrue(approx(RPN.evaluateRPN("5.1 -7.2 *"), -36.72));
      assertTrue(approx(RPN.evaluateRPN("-5.1 7.2 *"), -36.72));
      assertTrue(approx(RPN.evaluateRPN("-5.1 -7.2 *"), 36.72));
   }

   @Test
   public void evaluateRPNComplexMul()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7.1 * 11 *"), 390.5));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 * *"), 390.5));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 * 11 * -3 *"), -1171.5));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 -3 * * *"), -1171.5));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 * -11 * 3 *"), -1171.5));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 -11 3 * * *"), -1171.5));
      
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 * 11 * 3 *"), -1171.5));
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 11 3 * * *"), -1171.5));
      
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 * 11 * 3 *"), -1171.5));
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 11 3 * * *"), -1171.5));
   }

   @Test
   public void evaluateRPNBasicDiv()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7 /"), 0.714285714));
      assertTrue(approx(RPN.evaluateRPN("5.1 7 /"), 0.728571429));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 /"), 0.704225352));
      assertTrue(approx(RPN.evaluateRPN("5.1 7.1 /"), 0.718309859));
      
      assertTrue(approx(RPN.evaluateRPN("5.1 -7.2 /"), -0.708333333));
      assertTrue(approx(RPN.evaluateRPN("-5.1 7.2 /"), -0.708333333));
      assertTrue(approx(RPN.evaluateRPN("-5.1 -7.2 /"), 0.708333333));
   }

   @Test
   public void evaluateRPNComplexDiv()
   {
      assertTrue(approx(RPN.evaluateRPN("5 7.1 / 11 /"), 0.064020487));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 / /"), 7.746478873));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 / 11 / -3 /"), -0.021340162));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 11 -3 / / /"), -2.582159624));
      
      assertTrue(approx(RPN.evaluateRPN("5 7.1 / -11 / 3 /"), -0.021340162));
      assertTrue(approx(RPN.evaluateRPN("5 7.1 -11 3 / / /"), -2.582159624));
      
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 / 11 / 3 /"), -0.021340162));
      assertTrue(approx(RPN.evaluateRPN("5 -7.1 11 3 / / /"), -2.582159624));
     
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 / 11 / 3 /"), -0.021340162));
      assertTrue(approx(RPN.evaluateRPN("-5 7.1 11 3 / / /"), -2.582159624));
   }

   @Test
   public void evaluateRPNExtraWhiteSpace()
   {
      assertTrue(approx(RPN.evaluateRPN("2  3 4 5  6  + - *  /"), -0.09523809));
   }

   @Test
   public void evaluateRPNMixed()
   {
      // Rotate + - * /
      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 + - * /"), -0.09523809));
      assertTrue(approx(RPN.evaluateRPN("2 3 + 4 - 5 * 6 /"),  0.83333333));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 - * / +"),  1.25));
      assertTrue(approx(RPN.evaluateRPN("2 3 - 4 * 5 / 6 +"),  5.2));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 * / + -"), -1.13333333));
      assertTrue(approx(RPN.evaluateRPN("2 3 * 4 / 5 + 6 -"),  0.5));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 / + - *"), -3.66666666));
      assertTrue(approx(RPN.evaluateRPN("2 3 / 4 + 5 - 6 *"), -1.99999999));

      // Rotate / * - +
      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 / * - +"),  1.66666666));
      assertTrue(approx(RPN.evaluateRPN("2 3 / 4 * 5 - 6 +"),  3.66666666));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 * - + /"), -0.08695665 ));
      assertTrue(approx(RPN.evaluateRPN("2 3 * 4 - 5 + 6 /"),  1.16666666));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 - + / *"),  2.0));
      assertTrue(approx(RPN.evaluateRPN("2 3 - 4 + 5 / 6 *"),  3.59999999));

      assertTrue(approx(RPN.evaluateRPN("2 3 4 5 6 + / * -"),  0.90909090));
      assertTrue(approx(RPN.evaluateRPN("2 3 + 4 / 5 * 6 -"),  0.25));
   }

   @Test
   public void toRPNPrecedenceDiv()
   {
      assertTrue(RPN.toRPN("2 / 3 / 4 / 5").equals("2 3 / 4 / 5 /"));
      assertTrue(RPN.toRPN("2 / ( 3 / ( 4 / 5 ) )").equals("2 3 4 5 / / /"));
      assertTrue(RPN.toRPN("( 2 / ( 3 / ( 4 / 5 ) ) )").equals("2 3 4 5 / / /"));
      assertTrue(RPN.toRPN("2 / ( 3 / 4 ) / 5").equals("2 3 4 / / 5 /"));
   }
   
   @Test
   public void toRPNPrecedenceSub()
   {
      assertTrue(RPN.toRPN("2 - 3 - 4 - 5").equals("2 3 - 4 - 5 -"));
      assertTrue(RPN.toRPN("2 - ( 3 - ( 4 - 5 ) )").equals("2 3 4 5 - - -"));
      assertTrue(RPN.toRPN("( 2 - ( 3 - ( 4 - 5 ) ) )").equals("2 3 4 5 - - -"));
      assertTrue(RPN.toRPN("2 - ( 3 - 4 ) - 5").equals("2 3 4 - - 5 -"));
   }
 
   @Test
   public void toRPNBasic()
   {
      assertTrue(RPN.toRPN("5 + 9").equals("5 9 +"));
      assertTrue(RPN.toRPN("5 - 9").equals("5 9 -"));
      assertTrue(RPN.toRPN("5 * 9").equals("5 9 *"));
      assertTrue(RPN.toRPN("5 / 9").equals("5 9 /"));
   }

   @Test
   public void toRPNPrecedenceMixed()
   {
      // + +, + -, + *, + /
      assertTrue(RPN.toRPN("3 + 4 + 5").equals("3 4 + 5 +"));
      assertTrue(RPN.toRPN("3 + 4 - 5").equals("3 4 + 5 -"));
      assertTrue(RPN.toRPN("3 + 4 * 5").equals("3 4 5 * +"));
      assertTrue(RPN.toRPN("3 + 4 / 5").equals("3 4 5 / +"));

      // - +, - -, - *, - / 
      assertTrue(RPN.toRPN("3 - 4 + 5").equals("3 4 - 5 +"));
      assertTrue(RPN.toRPN("3 - 4 - 5").equals("3 4 - 5 -"));
      assertTrue(RPN.toRPN("3 - 4 * 5").equals("3 4 5 * -"));
      assertTrue(RPN.toRPN("3 - 4 / 5").equals("3 4 5 / -"));

      // * +, * -, * *,  * / 
      assertTrue(RPN.toRPN("3 * 4 + 5").equals("3 4 * 5 +"));
      assertTrue(RPN.toRPN("3 * 4 - 5").equals("3 4 * 5 -"));
      assertTrue(RPN.toRPN("3 * 4 * 5").equals("3 4 * 5 *"));
      assertTrue(RPN.toRPN("3 * 4 / 5").equals("3 4 * 5 /"));

      // / +, / -, / *, / /
      assertTrue(RPN.toRPN("3 / 4 + 5").equals("3 4 / 5 +"));
      assertTrue(RPN.toRPN("3 / 4 - 5").equals("3 4 / 5 -"));
      assertTrue(RPN.toRPN("3 / 4 * 5").equals("3 4 / 5 *"));
      assertTrue(RPN.toRPN("3 / 4 / 5").equals("3 4 / 5 /"));
   }

   @Test
   public void toRPNComplex()
   {
      assertTrue(RPN.toRPN("( ( ( 5 + 4 ) - 3 ) * 6 )").equals("5 4 + 3 - 6 *"));
      assertTrue(RPN.toRPN("( ( 5 + 4 ) - 3 ) * 6").equals("5 4 + 3 - 6 *"));
      assertTrue(RPN.toRPN("( 5 * ( 4 + 3 - 7 ) / 6 )").equals("5 4 3 + 7 - * 6 /"));
      assertTrue(RPN.toRPN("5 * ( 4 + 3 - 7 ) / 6").equals("5 4 3 + 7 - * 6 /"));
   }

   private boolean approx(double a, double b)
   {
      return Math.abs(a - b) < 0.000001;
   }
}

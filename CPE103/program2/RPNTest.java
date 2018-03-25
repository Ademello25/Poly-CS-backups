import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Transmogrifying RPN value.
 * 
 * @author Alexander DeMello
 * @version Program 2
 */
public class RPNTest {

   /**
    * Test of evalutateRPN method, of class RPN.
    */
   @Test
   public void testEvalutateRPNSimple() 
   {
      System.out.println("evalutateRPN1");
      String input = "3 8 + ";
      double expResult = 11.0;
      double result = RPN.evaluateRPN(input);
      assertEquals(expResult, result,6);
   }
   
   @Test
   public void testEvalutateRPNSimple1() 
   {
      System.out.println("evalutateRPN2");
      String input = "5 3 8 + *";
      double expResult = 55.0;
      double result = RPN.evaluateRPN(input);
      assertEquals(expResult, result,6);
   }
   
   @Test
   public void testEvalutateRPNSimple2() 
   {
      System.out.println("evalutateRPN3");
      String input = "7 8 3 + * 4 2 / - ";
      double expResult = 75.0;
      double result = RPN.evaluateRPN(input);
      assertEquals(expResult, result,6);
   }
   
   @Test
   public void testEvalutateRPNComplex() 
   {
      System.out.println("evalutateRPN4");
      String input = "9 5 * 5 * 5 *";
      double expResult = 1125.0;
      double result = RPN.evaluateRPN(input);
      assertEquals(expResult, result,6);
   }
   
   @Test
   public void testInfixToRPN()
   {
      System.out.println("infix to rpn 1");
      String input = "3 + 2 - 1";
      String result = RPN.toRPN(input);
      String expResult ="3 2 + 1 -";
      assertEquals(expResult, result);
   }
   
   @Test
   public void testInfixToRPN1()
   {
      System.out.println("infix to rpn 2");
      String input = "3 + ( 7 * 2 )";
      String result = RPN.toRPN(input);
      String expResult ="3 7 2 * +";
      assertEquals(expResult, result);
   }
   
   @Test
   public void testInfixToRPN2()
   {
      System.out.println("infix to rpn 3");
      String input = "( ( 5 / 2 ) + 6 * ( 9 - 2 ) )";
      String result = RPN.toRPN(input);
      String expResult ="5 2 / 6 9 2 - * +";
      assertEquals(expResult, result);
   }
   
   @Test 
   public void testEvalInfix()
   {
      System.out.println("testing evaluation of infix");
      String input = "( ( 9 - 5 ) * 16 / ( 4 - 2 )";
      double result = RPN.evaluateInfix(input);
      double expResult = 32;
      assertEquals(expResult, result, 6);
   }
}

import java.util.Scanner;

/**
 * RPN transmogrifier and calculator.
 * 
 * @author Alexander DeMello
 * @version program 2
 */
public class RPN 
{
   public static double evaluateRPN(String input)
   {
      SimpleLinkedStack<Double> result = new SimpleLinkedStack<Double>();
      
      Scanner sc = new Scanner(input);
      
      while(sc.hasNext())
      {
         while(sc.hasNextDouble())
         {
            result.push(sc.nextDouble());
            //System.out.println(result.peek());
         }
         
         
         if(sc.hasNext())
         {
            String comp = sc.next();
            if(comp.equals("+"))
            {
               double temp = result.pop();
               double tempres = result.pop() + temp;
               result.push(tempres);              
            }
            
            else if(comp.equals("*"))
            {
               double temp = result.pop();
               double tempres = result.pop() * temp;
               result.push(tempres);
            }
            
            else if(comp.equals("-"))
            {
               double temp = result.pop();
               double tempres = result.pop() - temp;
               result.push(tempres);
            }
            
            else if(comp.equals("/"))
            {
               double temp = result.pop();
               double tempres = result.pop() / temp;
               result.push(tempres);
            }
         }
         
      }
      return result.pop();
   }
   
public static String toRPN(String input)
   {
      Scanner sc = new Scanner(input);
      SimpleLinkedStack<String> temp = new SimpleLinkedStack<String>();
      String result = "";
      String sp = " ";
      
      while(sc.hasNext())
      {
         String next = sc.next();
         
         if(next.equals("("))
         {
            temp.push(next);
         }
         
         else if(next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/"))
         {
               while(temp.size()!=0 && !temp.peek().equals("(") && !(precedence(next) > 
                       precedence(temp.peek())))
               {
                  result += temp.pop() + sp;
               }
               temp.push(next);
         }
         
         else if(next.equals(")"))
         {
            while(!temp.peek().equals("("))
            {
               result += temp.pop() +sp;
            }
            temp.pop();
         }
         
         else
         {
            result += next + sp;
         }
         
      }
      
      while(temp.size() != 0)
      {
          String ch = temp.pop();
          if(!ch.equals("(") && !ch.equals(")"))
          {
            result+=ch+sp;
          }
      }
      return result.trim();
   }
   
   private static int precedence(String in)
   {
      if(in.equals("+"))
      {
         return 1;
      }
      else if(in.equals("-"))
      {
         return 1;
      }
      else if(in.equals("*"))
      {
         return 2;
      }
      else
      {
         return 2;
      }
      
   }
   
   
   public static double evaluateInfix(String input)
   {
      String temp = toRPN(input);
      return evaluateRPN(temp);
   }
}

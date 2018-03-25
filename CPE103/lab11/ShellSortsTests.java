
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander DeMello
 */
public class ShellSortsTests
{

   @Test
   public void testShell()
   {
      Integer[] arr = new Integer[10];
      arr[0] = 15;
      arr[1] = 8;
      arr[2] = 6;
      arr[3] = 19;
      arr[4] = 81;
      arr[5] = 61;
      arr[6] = 10;
      arr[7] = 6;
      arr[8] = 9;
      arr[9] = 100;

      ShellSorts.shell(arr);


      assertEquals((int) arr[0], 6);
      assertEquals((int) arr[1], 6);
      assertEquals((int) arr[2], 8);
      assertEquals((int) arr[3], 9);
      assertEquals((int) arr[4], 10);
      assertEquals((int) arr[5], 15);
      assertEquals((int) arr[6], 19);
      assertEquals((int) arr[7], 61);
      assertEquals((int) arr[8], 81);
      assertEquals((int) arr[9], 100);



   }

   @Test
   public void testHibby()
   {
      Integer[] arr = new Integer[10];
      arr[0] = 15;
      arr[1] = 8;
      arr[2] = 6;
      arr[3] = 19;
      arr[4] = 81;
      arr[5] = 61;
      arr[6] = 10;
      arr[7] = 6;
      arr[8] = 9;
      arr[9] = 100;

      ShellSorts.hibbard(arr);

      assertEquals((int) arr[0], 6);
      assertEquals((int) arr[1], 6);
      assertEquals((int) arr[2], 8);
      assertEquals((int) arr[3], 9);
      assertEquals((int) arr[4], 10);
      assertEquals((int) arr[5], 15);
      assertEquals((int) arr[6], 19);
      assertEquals((int) arr[7], 61);
      assertEquals((int) arr[8], 81);
      assertEquals((int) arr[9], 100);



   }

   @Test
   public void testSedgy()
   {
      Integer[] arr = new Integer[10];
      arr[0] = 15;
      arr[1] = 8;
      arr[2] = 6;
      arr[3] = 19;
      arr[4] = 81;
      arr[5] = 61;
      arr[6] = 10;
      arr[7] = 6;
      arr[8] = 9;
      arr[9] = 100;


      ShellSorts.sedgewick(arr);
      for (int i = 0; i < 10; i++)
      {
         System.out.println(arr[i]);
      }
      assertEquals((int) arr[0], 6);
      assertEquals((int) arr[1], 6);
      assertEquals((int) arr[2], 8);
      assertEquals((int) arr[3], 9);
      assertEquals((int) arr[4], 10);
      assertEquals((int) arr[5], 15);
      assertEquals((int) arr[6], 19);
      assertEquals((int) arr[7], 61);
      assertEquals((int) arr[8], 81);
      assertEquals((int) arr[9], 100);



   }


}

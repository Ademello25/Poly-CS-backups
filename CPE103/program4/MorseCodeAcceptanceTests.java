/**
 * JUnit tests for Morse Code assignment.
 *
 * NOTE: Not typical JUnit test file - contains tests for multiple
 *       classes as follows:
 *
 *          1) MorseCode (provided file - just make sure it has not changed)
 *          2) MorseOrder
 *          3) CharacterOrder
 *          4) BSTTranslator (an interface)
 *          5) MorseToText
 *          6) TestToMorse
 *
 * @author Kurt Mammen
 * @version 10/13/2012 Developed for CPE 103 Program 4 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Arrays;

public class MorseCodeAcceptanceTests
{
   ////////////////////////////////////////////////////////////////////////////
   // MorseCode tests... (a provided file)
   @Test
   public void verifyMorseCodeSuperClass()
   {
      assertTrue(MorseCode.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyMorseCodeInterfaces()
   {
      Class[] faces = MorseCode.class.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void verifyMorseCodeFields()
   {
      int characterCount = 0;
      int stringCount = 0;
      int arrayCount = 0;

      Field[] fields = MorseCode.class.getDeclaredFields();

      assertTrue(fields.length == 3);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType() == String.class)
         {
            stringCount++;
         }
         else if (fields[i].getType() == Character.class)
         {
            characterCount++;
         }
         else if (fields[i].getType().toString().contains("[LMorseCode"))
         {
            arrayCount++;
         }
      }

      assertTrue(stringCount == 1);
      assertTrue(characterCount == 1);
      assertTrue(arrayCount == 1);
   }

   @Test
   public void verifyMorseCodeMethods()
   {
      verifyMethods(MorseCode.class, 4, 0, 0, -1);
   }

   ////////////////////////////////////////////////////////////////////////////
   // MorseOrder tests...
   @Test
   public void verifyMorseOrderSuperClass()
   {
      assertTrue(MorseOrder.class.getSuperclass() == MorseCode.class);
   }

   @Test
   public void verifyMorseOrderInterfaces() // points = 5
   {
      Class[] faces = MorseOrder.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Comparable.class);
   }
   
   @Test
   public void verifyMorseOrderFields()
   {
      Field[] fields = MorseOrder.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }
   
   @Test
   public void verifyMorseOrderMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(MorseOrder.class, 2, 0, 0, -1);
   }


   ////////////////////////////////////////////////////////////////////////////
   // CharacterOrder tests...
   @Test
   public void verifyCharacterOrderSuperClass()
   {
      assertTrue(CharacterOrder.class.getSuperclass() == MorseCode.class);
   }

   @Test
   public void verifyCharacterOrderInterfaces() // points = 5
   {
      Class[] faces = CharacterOrder.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Comparable.class);
   }

   @Test
   public void verifyCharacterOrderFields()
   {
      Field[] fields = CharacterOrder.class.getDeclaredFields();
      assertTrue(fields.length == 0);
   }
   
   @Test
   public void verifyCharacterOrderMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(CharacterOrder.class, 2, 0, 0, -1);
   }

   ////////////////////////////////////////////////////////////////////////////
   // BSTTranslator tests... (an interface)
   @Test
   public void verifyBSTTranslatorInterfaces()
   {
      // TODO: Future enhancement...
   }

   @Test
   public void verifyBSTTranslatorMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(BSTTranslator.class, 2, 0, 0, 0);
   }

   ////////////////////////////////////////////////////////////////////////////
   // MorseToText tests...
   @Test
   public void verifyMorseToTextSuperClass()
   {
      assertTrue(MorseToText.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyMorseToTextInterfaces()
   {
      Class[] faces = MorseToText.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == BSTTranslator.class);
   }

   @Test
   public void verifyMorseToTextMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(MorseToText.class, 2, 0, 0, -1);
   }
   
   @Test
   public void verifyMorseToTextTreeHeight()
   {
      MorseToText translator = new MorseToText();
      BST<MorseOrder> bst = translator.getBST();
      assertEquals(5, bst.treeHeight());
   }

   @Test
   public void verifyMorseToTextBasic()
   {
      MorseToText translator = new MorseToText();

      for (int i = 0; i < MorseCode.size(); i++)
      {
         String text = translator.translate(MorseCode.get(i).getCode());
         String expected = "" + MorseCode.get(i).getCharacter();
         assertTrue(text.equals(expected));
      }
   }

   @Test
   public void verifyMorseToTextAdvanced()
   {
      MorseToText translator = new MorseToText();
      String in = "-. --- .--  .. ...  - .... .  - .. -- .  ..-. --- .-.  .- .-.. .-..  --. --- --- -..  -- . -.  - ---  -.-. --- -- .  - ---  - .... .  .- .. -..  --- ..-.  - .... . .. .-.  -.-. --- ..- -. - .-. -.--"; 
      String expected = "NOW IS THE TIME FOR ALL GOOD MEN TO COME TO THE AID OF THEIR COUNTRY";
      String out = translator.translate(in);

      assertTrue(out.equals(expected));
   }

   @Test
   public void verifyMorseToTextRandomBig()
   {
      int[] array = makeRandomArray(1000, -876);
      String input = makeMorse(array);
      String expect = makeText(array);
      MorseToText translator = new MorseToText();
      String actual = translator.translate(input);

      assertEquals(actual, expect);
   }
   
   //
   // Test developed on 2.4ghz machine - occational failures at 10ms.
   // Allow 4x to account for machine load and slightly less efficient code.
   //
   @Test(timeout = 40)
   public void verifyMorseToTextBigOh()
   {
      int[] array = makeRandomArray(80, -9876);
      String input = makeMorse(array);
      MorseToText translator = new MorseToText();

      for (int i = 0; i < 6; i++)
      {
         translator.translate(input);
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // TextToMorse tests...
   @Test
   public void verifyTextToMorseSuperClass()
   {
      assertTrue(TextToMorse.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyTextToMorseInterfaces()
   {
      Class[] faces = TextToMorse.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == BSTTranslator.class);
   }

   @Test
   public void verifyTextToMorseMethods()
   {
      // You get two compareTo methods when generic
      verifyMethods(TextToMorse.class, 2, 0, 0, -1);
   }

   @Test
   public void verifyTextToMorseTreeHeight()
   {
      TextToMorse translator = new TextToMorse();
      BST<CharacterOrder> bst = translator.getBST();
      assertTrue(bst.treeHeight() == 5);
   }
   
   @Test
   public void verifyTextToMorseBasic()
   {
      TextToMorse translator = new TextToMorse();

      for (int i = 0; i < MorseCode.size(); i++)
      {
         String text = "" + MorseCode.get(i).getCharacter();
         String morse = translator.translate(text);
         
         
         assertTrue(morse.equals(MorseCode.get(i).getCode()));
      }
   }

   @Test
   public void verifyTextToMorseAdvanced()
   {
      TextToMorse translator = new TextToMorse();
      String in = "NOW IS THE TIME FOR ALL GOOD MEN TO COME TO THE AID OF THEIR COUNTRY";
      String expected = "-. --- .--  .. ...  - .... .  - .. -- .  ..-. --- .-.  .- .-.. .-..  --. --- --- -..  -- . -.  - ---  -.-. --- -- .  - ---  - .... .  .- .. -..  --- ..-.  - .... . .. .-.  -.-. --- ..- -. - .-. -.--"; 
      
      String out = translator.translate(in);
      
      System.out.println(out);
      System.out.println(expected);
             
      assertTrue(out.equals(expected));
   }

   @Test
   public void verifyTextToMorseRandomBig()
   {
      int[] array = makeRandomArray(1000, -876);
      String input = makeText(array);
      String expect = makeMorse(array);
      TextToMorse translator = new TextToMorse();
      String actual = translator.translate(input);

      assertEquals(actual, expect);
   }
   
   //
   // Test developed on 2.4ghz machine - occational failures at 10ms.
   // Allow 4x to account for machine load and slightly less efficient code.
   //
   @Test(timeout = 40)
   public void verifyTextToMorseBigOh()
   {
      int[] array = makeRandomArray(100, -9876);
      String input = makeText(array);
      TextToMorse translator = new TextToMorse();

      for (int i = 0; i < 9; i++)
      {
         translator.translate(input);
      }
   }
   
   //
   // Pass in a negative value if you aren't counting.
   //
   private void verifyMethods(Class cl,
                              int expectedPublic,
                              int expectedPackage,
                              int expectedProtected,
                              int expectedPrivate)
   {
      int countPublic = 0;
      int countPackage = 0;
      int countProtected = 0;
      int countPrivate = 0;

      Method[] meths = cl.getDeclaredMethods();

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

      if (expectedPublic > -1)
      {
         assertEquals(countPublic, expectedPublic);
      }

      if (expectedPackage > -1)
      {
         assertEquals(countPackage, expectedPackage);
      }

      if (expectedProtected > -1)
      {
         assertEquals(countProtected, expectedProtected);
      }

      if (expectedPrivate > -1)
      {
         assertEquals(countPrivate, expectedPrivate);
      }
   }
   
   private int[] makeRandomArray(int size, int seed)
   {
      int[] array = new int[size];
      Random rand = new Random(seed);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      return array;
   }
   
   private String makeText(int[] indexes)
   {
      StringBuilder sb = new StringBuilder(indexes.length);

      for (int i = 0; i < indexes.length; i++)
      {
         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCharacter());
      }

      return sb.toString().trim();
   }

   private String makeMorse(int[] indexes)
   {
      StringBuilder sb = new StringBuilder(indexes.length);

      for (int i = 0; i < indexes.length; i++)
      {
         sb.append(MorseCode.get(Math.abs(indexes[i]) % MorseCode.size()).getCode());
         sb.append(" ");
      }

      return sb.toString().trim();
   }
} 
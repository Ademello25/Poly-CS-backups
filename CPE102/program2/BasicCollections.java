/**
 * BasicCollections
 * 
 * @author Alexander DeMello
 * @version Program 2
 */

public class BasicCollections
{
   public BasicCollections()
   {
   }
   public static<T> void sort(BasicList<T> list, java.util.Comparator<? super T> comparator)
   {
      for (int i = 0; i < list.size() - 1; i++)
      {
         int minPos = minimumPosition(comparator, list, i);
         swap(list, minPos, i);
      }
   }

   private static int minimumPosition(java.util.Comparator comparator, BasicList list, int from)
   {
      int minPos = from;

      for (int i = from + 1; i < list.size(); i++)
      {
         if (comparator.compare(list.get(i), list.get(minPos))<0)
         {
            minPos = i;
         }
      }

      return minPos;
   }

   private static void swap(BasicList list, int i, int j)
   {
      Object temp = list.get(j);
      list.set(j, list.get(i));
      list.set(i,temp);
   }
}
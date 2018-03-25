public class SelectionSorter
{
   private ArrayList<String> strArr;

   public SelectionSorter(ArrayList<String> array)
   {
      this.strArr = array;
   }

  public void sort()
  {
     for (int i = 0; i < strArr.length - 1; i++)
     {
        int minPos = minimumPosition(i);
        swap(minPos, i);
     }
  }

  private int minimumPosition(int from)
  {
     int minPos = from;

     for (int i = from + 1; i < strArr.length; i++)
     {
        if (strArr[i] < strArr[minPos])
        {
           minPos = i;
        }
     }

     return minPos;
  }

   private void swap(int i, int j)
  {
     int temp = a[i];
     a[i] = a[j];
     a[j] = temp;
  }
}
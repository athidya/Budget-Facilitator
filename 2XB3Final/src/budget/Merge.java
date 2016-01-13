package budget;

public class Merge {
	
	
	public static void mergeSort(Comparable [ ] a)
	{
		Comparable[] aux = new Comparable[a.length];
		mergeSort(a, aux,  0,  a.length - 1);
	}


	private static void mergeSort(Comparable [ ] a, Comparable [ ] aux, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a, aux, left, center);
			mergeSort(a, aux, center + 1, right);
			merge(a, aux, left, center + 1, right);
		}
	}


    private static void merge(Comparable[ ] a, Comparable[ ] aux, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                aux[k++] = a[left++];
            else
                aux[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            aux[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            aux[k++] = a[right++];

        // Copy aux back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = aux[rightEnd];
    }
	
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j]  = t;
	}
	
}

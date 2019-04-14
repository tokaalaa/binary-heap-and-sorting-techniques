package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;

@SuppressWarnings("rawtypes")
public class Sorting implements ISort {
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public IHeap heapSort(ArrayList unordered) {
		MyHeap h = new MyHeap();
		if (unordered != null) {
			h.build(unordered);
			int n = h.size();
			// One by one extract an element from heap 
			for (int i = n - 1; i >= 1; i--) 
			{
				h.swap((INode)h.getArrHeap().get(0), (INode)h.getArrHeap().get(i));
				h.setSize(i);
				h.heapify((INode)h.getArrHeap().get(0));
			}
			h.setSize(n);
		}
        return h;
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public void sortSlow(ArrayList unordered) {
		//Bubble Sort
		if(unordered != null) {
			int n = unordered.size(); 
			for (int i = 0; i < n-1; i++) { 
				for (int j = 0; j < n-i-1; j++) {
					if (unordered.get(j) instanceof Integer) {
					int comp = Integer.compare((Integer) unordered.get(j), (Integer) unordered.get(j + 1));
					if (comp > 0) { 
						Object temp = unordered.get(j); 
						unordered.set(j, unordered.get(j + 1));
						unordered.set(j + 1, temp);
					} 
					}
				}
			}	
		}
	}
//java -jar HeapAndSortTester.jar
	@Override
	public void sortFast(ArrayList unordered) {
		if (unordered != null) {
			if (unordered.size() != 0)
				if (unordered.get(0) instanceof Integer)
					sort(unordered, 0, unordered.size() - 1);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void merge(ArrayList arr, int l, int m, int r) {
		int i, j, k; 
	    int n1 = m - l + 1; 
	    int n2 =  r - m; 
	    Object[] L = new Object[n1];
	    Object[] R = new Object[n2];
	  
	    /* Copy data to temp arrays L[] and R[] */
	    for (i = 0; i < n1; i++) 
	        L[i] = arr.get(l + i); 
	    for (j = 0; j < n2; j++) 
	        R[j] = arr.get(m + 1+ j); 
	  
	    i = 0;
	    j = 0;
	    k = l;
	    while (i < n1 && j < n2) 
	    { 
			int comp = Integer.compare((Integer) L[i], (Integer) R[j]);
	        if (comp <= 0) 
	        { 
	            arr.set(k, L[i]); 
	            i++; 
	        } 
	        else
	        { 
	            arr.set(k, R[j]); 
	            j++; 
	        } 
	        k++; 
	    } 
	  
	    /* Copy the remaining elements of L[], if there 
	       are any */
	    while (i < n1) 
	    { 
            arr.set(k, L[i]); 
	        i++; 
	        k++; 
	    }
	    /* Copy the remaining elements of R[], if there 
	       are any */
	    while (j < n2) 
	    { 
            arr.set(k, R[j]); 
	        j++; 
	        k++; 
	    } 
    } 
  
	private void sort(ArrayList arr, int l, int r) 
    { 
        if (l < r) { 
            int m = l + ((r - l) / 2); 
            sort(arr, l, m); 
            sort(arr , m + 1, r);
            merge(arr, l, m, r); 
        }
    } 

  

}

package exams;

public class InsertionSort implements SortAlgorithm {
	
	public InsertionSort() {}

	@Override
	public void sort(Object[] array) {
		//InsertionSort algorithm for sorting students
		 int j;                     // the number of items sorted so far
	     Student key;                // the item to be inserted
	     int i;  

	     for (j = 1; j < array.length; j++)    // Start with 1 (not 0)
	     {
	           key = (Student)array[ j ];
	           for(i = j - 1; (i >= 0) && (((Student) array[i]).compareTo(key) > 0); i--)   
	          {
	        	   array[ i+1 ] = array[ i ];
	          }
	          array[ i+1 ] = key;    // Put the key in its proper location
	     }
	}

}

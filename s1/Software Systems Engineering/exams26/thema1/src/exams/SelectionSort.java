package exams;

public class SelectionSort implements SortAlgorithm {

	public SelectionSort() {}

	@Override
	public void sort(Object[] array) {
		//SelectionSort algorithm for sorting students
		 int i, j, first;
		 Student temp;  
	     for ( i = array.length - 1; i > 0; i -- )  
	     {
	          first = 0;   //initialize to subscript of first element
	          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
	          {
	               if( ((Student) array[j]).compareTo((Student) array[first]) > 0 )         
	                 first = j;
	          }
	          temp = (Student)array[ first ];   //swap smallest found with element in position i.
	          array[ first ] = array[ i ];
	          array[ i ] = temp; 
	      }        
	}

}
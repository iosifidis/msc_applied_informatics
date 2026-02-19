package exams;

public class BubbleSort implements SortAlgorithm {
	
	public BubbleSort() {}

	@Override
	public void sort(Object[] array) {
		//BubbleSort algorithm for sorting students
		 int j;
	     boolean flag = true;   // set flag to true to begin first pass
	     Student temp;   //holding variable

	     while ( flag )
	     {
	            flag= false;    //set flag to false awaiting a possible swap
	            for( j=0;  j < array.length -1;  j++ )
	            {
	                   if ( ((Student) array[ j ]).compareTo((Student) array[j+1]) > 0 )   
	                   {
	                           temp = (Student) array[ j ];                //swap elements
	                           array[ j ] = array[ j+1 ];
	                           array[ j+1 ] = temp;
	                          flag = true;              //shows a swap occurred  
	                  } 
	            } 
	      }
	}
}

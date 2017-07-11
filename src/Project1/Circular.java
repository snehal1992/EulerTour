package Project1;

import java.util.ArrayList;

public class Circular<E> extends ArrayList<E> {
	@Override
		public E get(int index) {
		        return super.get(index % size());
		    }
		
	}
	


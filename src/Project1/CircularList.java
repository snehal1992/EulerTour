package Project1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class CircularList<T> extends SinglyLinkedList<T>{
	 
	CircularList(){
		header = new Entry<>(null, null);
        tail = header;
        size = 0;
	}
	/*public CircularList<T> merge(CircularList<T> c1,CircularList<T> c2, Iterator it){
			//c1.prev<- temp.header
					//tail(temp)<-c1.cursor 
					//c1<-c1 + temp at given pos 
		  c1.prev
		}
		Vertex v=
	}*/
	
	void add(T x) {
		tail.next= new Entry<>(x, header);
		tail = tail.next;
		size++;
	    }
	public Entry<T> getEntry(){
	     return tail;	
	}
	public Iterator<T> iterator() { return new SLLIterator<>(header); }

	    public class SLLIterator<E> implements Iterator<E> {
		Entry<E> cursor;
		Entry<E> prev;
		SLLIterator(Entry<E> head) {
		    cursor = head;
		    prev = null;
		}
		
		
		public boolean hasNext() {
			return cursor.next!= tail.next;
		}
		public E next() {
		    prev = cursor;
		    cursor = cursor.next;
		    return cursor.element;
		}

		// To do: error checking
		// What should cursor be set to after a remove?
		public void remove() {
		    prev.next = cursor.next;
		    prev = null;
		}
	   /*public CircularList<Vertex> merge(CircularList<Vertex> c1,CircularList<Vertex> c2){
		  c1.=c2.header;
		  c2.tail=cursor;
		  return c1;
	    }*/
	    }
	    public static void main(String args[]){
	    	
	    }
}

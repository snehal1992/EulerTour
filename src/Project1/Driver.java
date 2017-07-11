/** Sample driver program using the graph class
 *  @author rbk
 */
package Project1;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import Project1.SinglyLinkedList.Entry;

import java.io.File;

public class Driver {
	Map <Vertex , SinglyLinkedList<Vertex>.Entry<Vertex>> m=new HashMap<>();
	public  List<CircularList<Vertex>> breakGraphIntoTours(Graph g){
		//Timer timer = new Timer();
	     // timer.start();
		Queue<Vertex> q= new LinkedList<Vertex>();
		List<Vertex> tour= new ArrayList<Vertex>();
		List<CircularList<Vertex>> lst =new ArrayList<CircularList<Vertex>>();
		Vertex y=g.getVertex(1);
		q.add(y);
		int i;
		int j;
		int tourComplete=0;
		i=1;
		j=0;
	    y.it=y.adj.iterator();
		while(!(q.isEmpty())){
		     Vertex temp=q.peek();
			      y=temp;
			 while(y.it.hasNext()){// run end of an adj list to see an unseen edge
				 Edge iterateEdge=y.it.next();
				 if(iterateEdge.seen==false){ // unseen edge
					  tourComplete=0;
					  tour.add(y);
					  y=iterateEdge.otherEnd(y);
					 if((iterateEdge.from.name==temp.name || iterateEdge.to.name==temp.name)&& j==1){// check completion of a tour
						 Iterator<Vertex> it2=tour.iterator();
						 CircularList<Vertex> circular=new CircularList<Vertex>();
						 while(it2.hasNext()){
							 Vertex z1=it2.next();
							 circular.add(z1); 
								//Timer timer1 = new Timer();
							      //timer1.start();
							if(!m.containsKey(z1))
							 m.put(z1,circular.getEntry());
							 //timer1.end();
							 //System.out.println("Contains"+timer1);
						 }
						 lst.add(circular);
						 tour.clear();
						 tourComplete=1;
					 }// end of adding to list
					 else{
						 if(y.seen!=true){
						 q.add(y);// add to element into queue
						 y.seen=true;
						  y.it=y.adj.iterator();
						 }
					 }
					 iterateEdge.seen=true;
				 }//end of passing of an edge which was unseen
				 if(tourComplete!=1)
				 j=1;
				 else{
					 j=0;
				 }
			 } // end of adj list 
			q.remove();
				}// end of queue
	
		//timer.end();
	    //System.out.println("breakgraph into tours"+timer);
		return lst;
	}
	public  CircularList<Vertex> stitchTours(List<CircularList<Vertex>> listOfTours){
		//Timer timer = new Timer();
	     //timer.start();
		Iterator<CircularList<Vertex>> it=listOfTours.iterator();
		CircularList<Vertex> c1=it.next();
			while(it.hasNext()){
				Iterator<Vertex>itTemp=c1.iterator();
				CircularList<Vertex> c2=new CircularList<>();
				c2=it.next();
				Vertex temp=c2.header.next.element;
				while(itTemp.hasNext()){
					Vertex y=itTemp.next();
				if(y.name==temp.name){	
                    SinglyLinkedList<Vertex>.Entry<Vertex> s=m.get(y);
                    SinglyLinkedList<Vertex>.Entry<Vertex> temp5=s.next;
                    s.next=c2.header.next.next;
                    c2.header.next.next=temp5;
                    c2.tail.next=c2.header.next;
                   // c1.size=c1.size+c2.size;
                    break; 
				}
				}
			}
		//	timer.end();
		 // System.out.println("stitch  tours"+timer);
		return c1;
			    }
	public boolean VerifyTour(Graph g , CircularList<Vertex> tour){
		List<Edge> l=new ArrayList<>();
		for(Vertex u: g) {
			u.seen=false;
		    for(Edge e: u.adj) {
		    	if(e.seen!=false){
					l.add(e);
		    	}
			e.seen=false;
		    }
		}
		
		Iterator <Vertex> i=tour.iterator();
		Vertex v=i.next();
		   v.it=v.adj.iterator();
		   //q.add(v);
		   Iterator<Edge> it=l.iterator();
		while(i.hasNext()){
			Vertex x=i.next();
				//System.out.println(z);
				//System.out.println(v+" "+x);
				while(v.it.hasNext()){
					Edge z=v.it.next();
				if((z.from.name==x.name || z.from.name==v.name) && (z.to.name==x.name || z.to.name==v.name)){
					if(z.seen==true){
						return false;
					}
					//System.out.println("h");
					z.seen=true;
					break;
				}
			}
			if(x.seen!=true){
			x.it=x.adj.iterator();
			}
			//System.out.println(v+" "+x);
			v=x;
		}
		for(Vertex u: g) {
		    for(Edge e: u.adj) {
				if(e.seen==false){
					return false;
			}
			
		   }
		   
		}
		
 		
		
		return true ;
	}


    public static void main(String[] args) throws FileNotFoundException {
    	Timer timer = new Timer();
	      timer.start();
	Scanner in;
	
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
        	File inputFile=new File("mp1-big.txt");
            in = new Scanner(inputFile);
        }
        Timer timer1 = new Timer();
	      timer1.start();
	Graph g = Graph.readGraph(in);
	/*System.out.println("Input Graph:");
	for(Vertex u: g) {
	    System.out.print(u + ": ");
	    for(Edge e: u.adj) {
		System.out.print(e + " ");
	    }
	    System.out.println();
	}*/
	timer1.end();
	System.out.println("Readgraph"+timer1);
	Driver d=new Driver();
	Timer t2=new Timer();
	t2.start();
	List<CircularList<Vertex>>lst=d.breakGraphIntoTours(g);
	t2.end();
	Iterator<CircularList<Vertex>> it= lst.iterator();
	while(it.hasNext()){
		CircularList<Vertex> c=it.next();
		Iterator<Vertex> it4=c.iterator();
		while(it4.hasNext()){
			it4.next();
		//System.out.print(it4.next()+"->");
		}
		//System.out.println();
	}
	Timer t3=new Timer();
	t3.start();
	CircularList<Vertex> c =d.stitchTours(lst);
	t3.end();
	c.add(c.header.next.element);
	//c.add(c.header.next.next.element);
	Iterator<Vertex> it3=c.iterator();
	while(it3.hasNext()){
		System.out.println(it3.next());
	}
	Timer t4=new Timer();
	t4.start();
	/*boolean s=d.VerifyTour(g, c);
	t4.end();
	if(s==false){
		System.out.println("Graph is not eulerian");
	}
	else{
		System.out.println("Graph is eulerian");
	}*/
	

	/*if(g.isBipartite()) {
	    System.out.println("Bipartite graph");
	} else {
	    System.out.println("Not a bipartite graph");
	}*/
	timer.end();
    System.out.println("Total"+timer+"   readgraph"+timer1+" break"+t2+"stitch"+t3+" verify"+t4);
    
    }
    }


/* Sample runs:
$ java Driver < in.no
Input Graph:
1: (1,2) (1,3) 
2: (1,2) (2,3) 
3: (2,3) (1,3) 
Not a bipartite graph

$ java Driver < in.yes
Input Graph:
1: (1,2) (1,4) 
2: (1,2) (2,3) 
3: (2,3) (3,4) 
4: (3,4) (1,4) 
Bipartite graph
*/

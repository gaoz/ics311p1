package ics311;

/*
 * @author  Vinson Gao
 * Version 1.0    2014 3/9
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
	
	
	
	
	public static void main(String[] args) {
             
               
                ArrayList<String> inputs = new ArrayList<String>();
                String[] chosen = new String[10];
                Random generator = new Random();
                
		RedBlackTree<String, String> redBlackTree  = new RedBlackTree<String,String>("RedBlackTree");
		SkipListInterface skipList = new SkipList();
                BinarySearchTree btree = new BinarySearchTree( );
                SortedDoubleLinkedList sortedDoubleLinkedList = new SortedDoubleLinkedList();
                        
		File file = new File(args[0]);
                 
                long minimum = Integer.MAX_VALUE; 
                long maximum = Integer.MIN_VALUE;
                long sum = 0;
                long begin=0;
                long finish=0;
                long elapsed=0;	
                
                
                
                
                /****************************************************************************************************
                 *                         RedBlackTree : INSERT
                *****************************************************************************************************/
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                    
		    for(String line; (line = br.readLine()) != null; )
		    {
		    	
		    	inputs.add(line);//Filling up the array for random access later
	    	
                        begin = System.nanoTime();
		        redBlackTree.insert(line, line);
		        finish = System.nanoTime();
		        elapsed = finish - begin;
		        if(elapsed < minimum)
		        {
		        	minimum = elapsed;
		        }
		        if(elapsed > maximum)
		        {
		        	maximum = elapsed;
		        }
		        sum += elapsed;
		    }
                    
                    System.out.println("RedBlackTree:" );
                    System.out.println("insertion minimum time:"+minimum);
                    System.out.println("insertion maximum time:"+maximum);
                    System.out.println("insertion average time:"+(minimum + maximum)/2);
                    System.out.println("insertion sum time:"+sum);
                    System.out.println("");
                }catch(IOException e){
			System.out.print(e);
		}    
                
                
                
                /****************************************************************************************************
                 *                          SkipList : INSERT
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;

                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; )
		    {
                        begin = System.nanoTime();
		        skipList.insert(line, line);
		        finish = System.nanoTime();
		        elapsed = finish - begin;
		        if(elapsed < minimum)
		        {
		        	minimum = elapsed;
		        }
		        if(elapsed > maximum)
		        {
		        	maximum = elapsed;
		        }
		        sum += elapsed;
		    }
                    
                    System.out.println("SkipList:" );
                    System.out.println("insertion minimum time:"+minimum);
                    System.out.println("insertion maximum time:"+maximum);
                    System.out.println("insertion average time:"+(minimum + maximum)/2);
                    System.out.println("insertion sum time:"+sum);
                    System.out.println("");
                }catch(IOException e){
			System.out.print(e);
		}
                
                
                
                
                
                
                
                /****************************************************************************************************
                 *                          BinarySearchTree : INSERT
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;

                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; )
		    {
                        begin = System.nanoTime();
		        btree.insert(line);
		        finish = System.nanoTime();
		        elapsed = finish - begin;
		        if(elapsed < minimum)
		        {
		        	minimum = elapsed;
		        }
		        if(elapsed > maximum)
		        {
		        	maximum = elapsed;
		        }
		        sum += elapsed;
		    }
                    
                    System.out.println("BinarySearchTree:" );
                    System.out.println("insertion minimum time:"+minimum);
                    System.out.println("insertion maximum time:"+maximum);
                    System.out.println("insertion average time:"+(minimum + maximum)/2);
                    System.out.println("insertion sum time:"+sum);
                    System.out.println("");
                }catch(IOException e){
			System.out.print(e);
		}     
                
                 
                
                
                
                
                /****************************************************************************************************
                 *                          SortedDoubleLinkedList : INSERT
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;

                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; )
		    {
                        begin = System.nanoTime();
		        sortedDoubleLinkedList.insert(line);
		        finish = System.nanoTime();
		        elapsed = finish - begin;
		        if(elapsed < minimum)
		        {
		        	minimum = elapsed;
		        }
		        if(elapsed > maximum)
		        {
		        	maximum = elapsed;
		        }
		        sum += elapsed;
		    }
                    
                    System.out.println("SortedDoubleLinkedList:" );
                    System.out.println("insertion minimum time:"+minimum);
                    System.out.println("insertion maximum time:"+maximum);
                    System.out.println("insertion average time:"+(minimum + maximum)/2);
                    System.out.println("insertion sum time:"+sum);
                    System.out.println("");
                }catch(IOException e){
			System.out.print(e);
		}    
                
                
                
                
                
                
                
		/****************************************************************************************************
                 *                         pick 10 random numbers 
                *****************************************************************************************************/	 
                for(int i = 0; i < 10; i++)
                {   
                      int random = generator.nextInt(inputs.size());
                      String key = inputs.get(random);
                      chosen[i] = key;
                }

                
                
                
                
                
		/****************************************************************************************************
                 *                          REBLACKTREEE : RETRIEVE 
                *****************************************************************************************************/
                 
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   redBlackTree.retrieve(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("RedBlackTree:" );
                System.out.println("RETRIEVE minimum time:"+minimum);
                System.out.println("RETRIEVE maximum time:"+maximum);
                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
                System.out.println("RETRIEVE sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                
                
                /****************************************************************************************************
                 *                         SkipList: RETRIEVE
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   skipList.find(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("SkipList:" );
                System.out.println("RETRIEVE minimum time:"+minimum);
                System.out.println("RETRIEVE maximum time:"+maximum);
                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
                System.out.println("RETRIEVE sum time:"+sum); 
                System.out.println("");
                
                
                
                
                
                 /****************************************************************************************************
                 *                         BinarySearch: RETRIEVE
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   btree.find(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("BinarySearch:" );
                System.out.println("RETRIEVE minimum time:"+minimum);
                System.out.println("RETRIEVE maximum time:"+maximum);
                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
                System.out.println("RETRIEVE sum time:"+sum); 
                System.out.println("");
                
                
                
                 /****************************************************************************************************
                 *                         SortedDoubleLinkedList: RETRIEVE
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   sortedDoubleLinkedList.retrieve(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("SortedDoubleLinkedList:" );
                System.out.println("RETRIEVE minimum time:"+minimum);
                System.out.println("RETRIEVE maximum time:"+maximum);
                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
                System.out.println("RETRIEVE sum time:"+sum); 
                System.out.println("");
                
                
                
                
                /****************************************************************************************************
                 *                         RedBlackTree: predecessor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   redBlackTree.predecessor(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }

                    
                System.out.println("RedBlackTree:" );
                System.out.println("predecessor minimum time:"+minimum);
                System.out.println("predecessor maximum time:"+maximum);
                System.out.println("predecessor average time:"+(minimum + maximum)/2);
                System.out.println("predecessor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                
                /****************************************************************************************************
                 *                         SkipList: predecessor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   skipList.predecessors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }

                    
                System.out.println("SkipList:" );
                System.out.println("predecessor minimum time:"+minimum);
                System.out.println("predecessor maximum time:"+maximum);
                System.out.println("predecessor average time:"+(minimum + maximum)/2);
                System.out.println("predecessor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                /****************************************************************************************************
                 *                         BinarySearchTree: predecessor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   btree.predcessors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }

                    
                System.out.println("BinarySearchTree:" );
                System.out.println("predecessor minimum time:"+minimum);
                System.out.println("predecessor maximum time:"+maximum);
                System.out.println("predecessor average time:"+(minimum + maximum)/2);
                System.out.println("predecessor sum time:"+sum);  
                System.out.println("");
                
                
                
                /****************************************************************************************************
                 *                         SortedDoubleLinkedList: predecessor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   sortedDoubleLinkedList.predcessors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }

                    
                System.out.println("SortedDoubleLinkedList:" );
                System.out.println("predecessor minimum time:"+minimum);
                System.out.println("predecessor maximum time:"+maximum);
                System.out.println("predecessor average time:"+(minimum + maximum)/2);
                System.out.println("predecessor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                /****************************************************************************************************
                 *                         RedBlackTree: successor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   redBlackTree.successor(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("RedBlackTree:" );
                System.out.println("successor minimum time:"+minimum);
                System.out.println("successor maximum time:"+maximum);
                System.out.println("successor average time:"+(minimum + maximum)/2);
                System.out.println("successor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                /****************************************************************************************************
                 *                         SkipList: successor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   skipList.successors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }


                System.out.println("SkipList:" );
                System.out.println("successor minimum time:"+minimum);
                System.out.println("successor maximum time:"+maximum);
                System.out.println("successor average time:"+(minimum + maximum)/2);
                System.out.println("successor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                 /****************************************************************************************************
                 *                         BinarySearchTree: successor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   btree.successors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("BinarySearchTree:" );
                System.out.println("successor minimum time:"+minimum);
                System.out.println("successor maximum time:"+maximum);
                System.out.println("successor average time:"+(minimum + maximum)/2);
                System.out.println("successor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                
                /****************************************************************************************************
                 *                         SortedDoubleLinkedList: successor
                *****************************************************************************************************/
                minimum = Integer.MAX_VALUE; 
                maximum = Integer.MIN_VALUE;
                sum = 0;
                begin=0;
                finish=0;
                elapsed=0;
                for(int i = 0; i < 10; i++)
                {
                   begin = System.nanoTime();
                   sortedDoubleLinkedList.successors(chosen[i]);
                   finish = System.nanoTime();
                   elapsed = finish - begin;
                   if(elapsed < minimum)
                   {
                       minimum = elapsed;
                   }
                   if(elapsed > maximum)
                   {
                       maximum = elapsed;
                   }
                   sum += elapsed;
                }
                System.out.println("SortedDoubleLinkedList:" );
                System.out.println("successor minimum time:"+minimum);
                System.out.println("successor maximum time:"+maximum);
                System.out.println("successor average time:"+(minimum + maximum)/2);
                System.out.println("successor sum time:"+sum);  
                System.out.println("");
                
                
                
                
                
                
                
                
                
                /****************************************************************************************************
                 *                         RedBlackTree: minimum
                *****************************************************************************************************/
                begin = System.nanoTime();
                redBlackTree.minimum();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("RedBlackTree:" );
                System.out.println("actually time for minimum:"+elapsed);
                System.out.println("");
                
                
                
                
                /****************************************************************************************************
                 *                         SkipList: minimum
                *****************************************************************************************************/
                begin = System.nanoTime();
                skipList.first();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("SkipList:" );
                System.out.println("actually time for minimum:"+elapsed);
                System.out.println("");
                
                
                
                
                
                /****************************************************************************************************
                 *                         BinarySearchTree: minimum
                *****************************************************************************************************/
                begin = System.nanoTime();
                btree.first();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("BinarySearchTree:" );
                System.out.println("actually time for minimum:"+elapsed);
                System.out.println("");
                
                
                
                /****************************************************************************************************
                 *                         SortedDoubleLinkedList: minimum
                *****************************************************************************************************/
                begin = System.nanoTime();
                sortedDoubleLinkedList.minimum();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("SortedDoubleLinkedList:" );
                System.out.println("actually time for minimum:"+elapsed);
                System.out.println("");
                
                
                
                /****************************************************************************************************
                 *                         RedBlackTree: maximum
                *****************************************************************************************************/
                begin = System.nanoTime();
                redBlackTree.maximum();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("RedBlackTree:" );
                System.out.println("actually time for maximum:"+elapsed);
                System.out.println("");
                
                
                
                
                
                /****************************************************************************************************
                 *                         SkipList: maximum
                *****************************************************************************************************/
                begin = System.nanoTime();
                skipList.last();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("SkipList:" );
                System.out.println("actually time for maximum:"+elapsed);
                System.out.println("");
                
                
                
                
                /****************************************************************************************************
                 *                         BinarySearchTree: maximum
                *****************************************************************************************************/
                begin = System.nanoTime();
                btree.last();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("BinarySearchTree:" );
                System.out.println("actually time for maximum:"+elapsed);
                System.out.println("");
                
                
                
                
                 /****************************************************************************************************
                 *                         SortedDoubleLinkedList: maximum
                *****************************************************************************************************/
                begin = System.nanoTime();
                sortedDoubleLinkedList.maximum();
                finish = System.nanoTime();
                elapsed = finish - begin;

                System.out.println("SortedDoubleLinkedList:" );
                System.out.println("actually time for maximum:"+elapsed);
                System.out.println("");
                
                
            }// end of main

}// end of class
     




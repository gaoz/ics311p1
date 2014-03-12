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


public class Main {
	
	public static ArrayList<String> randomData(ArrayList<String> mydata, int dataSize){
            
            Random generator = new Random();
            ArrayList<String> chosen = new ArrayList<String>();
            for(int i = 0; i < dataSize; i++)
            {   
                  int random = generator.nextInt(mydata.size());
                  String key = mydata.get(random);
                  chosen.add(key);
            }
            return chosen;
        }
        
	public static ArrayList<String> loadData(String arg){
            ArrayList<String> mydata = new ArrayList<String>();
            File file = new File(arg);
            
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {

                        for(String line; (line = br.readLine()) != null; )
                        {
                             mydata.add(line);
                             
                        }
            }catch(IOException e){
			System.out.print(e);
            }
            
            return mydata;
        }
        public static long[] call(DynamicSet<String, String> set,  ArrayList<String> list,  Method method) {
            long minimum = Integer.MAX_VALUE; 
            long maximum = Integer.MIN_VALUE;
            long sum = 0;
            long begin=0;
            long finish=0;
            long elapsed=0;
            
            for(String d: list){
                        
                        
                
                
                        begin = System.nanoTime();
                        
                        switch (method) {
                            case insert:
                                set.insert(d, d);
                                break;

                            case retrieve:
                                set.retrieve(d);
                                break;

                            case predecessor:
                                set.predecessor(d);
                                break;
                            case successor:
                                set.successor(d);
                                break;
                            case minimum: 
                                set.minimum();
                                break;
                            case maximum:
                                set.maximum();
                                break;

                        }
                            
                      

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
            }// end of for loop  
            long[] pack = new long[3];
            pack[0]= minimum;
            pack[1]= maximum;
            pack[2]= sum;
            
            return pack ;
        }
        public static long call2(DynamicSet<String, String> set,  Method method) {
           
            long begin=0;
            long finish=0;
            long elapsed=0;
            begin = System.nanoTime();

            switch (method) {

                case minimum: 
                    set.minimum();
                    break;
                case maximum:
                    set.maximum();
                    break;
            }

            finish = System.nanoTime();

            elapsed = finish - begin;
            return elapsed;
        }
                
        public enum Method {
            insert, retrieve, predecessor, successor,
            minimum, maximum, delete
        }
        
        
	
	public static void main(String[] args) {
            
                ArrayList<String> datas = loadData(args[0]);
                ArrayList<String> randomedData = randomData(datas, 10);
                
                ArrayList<DynamicSet<String, String>> sets = new ArrayList<DynamicSet<String, String>>();
                long[][] timeTable = new long[4][17];
                
		DynamicSet<String, String> redBlackTree  = new RedBlackTree<String,String>("RedBlackTree");
		SkipListInterface skipList = new SkipList();
                BinarySearchTree btree = new BinarySearchTree( );
                SortedDoubleLinkedList sortedDoubleLinkedList = new SortedDoubleLinkedList();
                
                sets.add(redBlackTree);
                
                
                /****************************************************************************************************
                 *                                     run time test
                *****************************************************************************************************/
                for(DynamicSet<String, String> set: sets){
                    
                    int i =0 ;
                    int j =0 ;
                    long[] pack = new long[3];
                    for(Method m: Method.values()){

                        switch (m) {
                            case retrieve: case predecessor: case successor: case delete:
                                pack = call(set, randomedData, m);
                                
                                timeTable[i][j]= pack[0];  j=j+1;
                                timeTable[i][j]= pack[1];  j=j+1;
                                timeTable[i][j]= pack[2];  j=j+1;
                                
                                break;
                            
                            case minimum: case maximum:
                                
                                long time = call2(set, m);
                                timeTable[i][j]= time;  
                                j=j+1;
                                
                                
                                break;
                            
                            default:
                                pack = call(set, datas, m);
                                
                                timeTable[i][j]= pack[0];  j=j+1;
                                timeTable[i][j]= pack[1];  j=j+1;
                                timeTable[i][j]= pack[2];  j=j+1;
                                
                                break;
                        }
                        
                        
                        
//                        

//                        System.out.println(set.setDataStructure() );
//                        System.out.println(m.toString()+" minimum time:"+pack[0]);
//                        System.out.println(m.toString()+" maximum time:"+pack[1]);
//                        System.out.println(m.toString()+" average time:"+(pack[0] + pack[1])/2);
//                        System.out.println(m.toString()+" sum time:"+pack[2]);
//                        System.out.println("");

                    }// end of for loop of Method: insert, retrieve....
                    i=i+1;

                        
                }// end of looping tree sets
//                
                for(int i=0; i<4; i++){
                                for(int j=0; j<17; j++){
                                    System.out.print(timeTable[i][j]+" ");

                                }
                                System.out.println("");
                }                     
            }// end of main

}// end of class
               
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                          SkipList : INSERT
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//
//                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//		    for(String line; (line = br.readLine()) != null; )
//		    {
//                        begin = System.nanoTime();
//		        skipList.insert(line, line);
//		        finish = System.nanoTime();
//		        elapsed = finish - begin;
//		        if(elapsed < minimum)
//		        {
//		        	minimum = elapsed;
//		        }
//		        if(elapsed > maximum)
//		        {
//		        	maximum = elapsed;
//		        }
//		        sum += elapsed;
//		    }
//                    
//                    System.out.println("SkipList:" );
//                    System.out.println("insertion minimum time:"+minimum);
//                    System.out.println("insertion maximum time:"+maximum);
//                    System.out.println("insertion average time:"+(minimum + maximum)/2);
//                    System.out.println("insertion sum time:"+sum);
//                    System.out.println("");
//                }catch(IOException e){
//			System.out.print(e);
//		}
//                
//                
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                          BinarySearchTree : INSERT
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//
//                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//		    for(String line; (line = br.readLine()) != null; )
//		    {
//                        begin = System.nanoTime();
//		        btree.insert(line);
//		        finish = System.nanoTime();
//		        elapsed = finish - begin;
//		        if(elapsed < minimum)
//		        {
//		        	minimum = elapsed;
//		        }
//		        if(elapsed > maximum)
//		        {
//		        	maximum = elapsed;
//		        }
//		        sum += elapsed;
//		    }
//                    
//                    System.out.println("BinarySearchTree:" );
//                    System.out.println("insertion minimum time:"+minimum);
//                    System.out.println("insertion maximum time:"+maximum);
//                    System.out.println("insertion average time:"+(minimum + maximum)/2);
//                    System.out.println("insertion sum time:"+sum);
//                    System.out.println("");
//                }catch(IOException e){
//			System.out.print(e);
//		}     
//                
//                 
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                          SortedDoubleLinkedList : INSERT
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//
//                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//		    for(String line; (line = br.readLine()) != null; )
//		    {
//                        begin = System.nanoTime();
//		        sortedDoubleLinkedList.insert(line);
//		        finish = System.nanoTime();
//		        elapsed = finish - begin;
//		        if(elapsed < minimum)
//		        {
//		        	minimum = elapsed;
//		        }
//		        if(elapsed > maximum)
//		        {
//		        	maximum = elapsed;
//		        }
//		        sum += elapsed;
//		    }
//                    
//                    System.out.println("SortedDoubleLinkedList:" );
//                    System.out.println("insertion minimum time:"+minimum);
//                    System.out.println("insertion maximum time:"+maximum);
//                    System.out.println("insertion average time:"+(minimum + maximum)/2);
//                    System.out.println("insertion sum time:"+sum);
//                    System.out.println("");
//                }catch(IOException e){
//			System.out.print(e);
//		}    
                
             
                
                
//                
//                
//		/****************************************************************************************************
//                 *                         pick 10 random numbers 
//                *****************************************************************************************************/	 
//                for(int i = 0; i < 10; i++)
//                {   
//                      int random = generator.nextInt(inputs.size());
//                      String key = inputs.get(random);
//                      chosen[i] = key;
//                }
//
//                
//                
//                
//                
//                
//		/****************************************************************************************************
//                 *                          REBLACKTREEE : RETRIEVE 
//                *****************************************************************************************************/
//                 
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   redBlackTree.retrieve(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("RedBlackTree:" );
//                System.out.println("RETRIEVE minimum time:"+minimum);
//                System.out.println("RETRIEVE maximum time:"+maximum);
//                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
//                System.out.println("RETRIEVE sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SkipList: RETRIEVE
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   skipList.find(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("SkipList:" );
//                System.out.println("RETRIEVE minimum time:"+minimum);
//                System.out.println("RETRIEVE maximum time:"+maximum);
//                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
//                System.out.println("RETRIEVE sum time:"+sum); 
//                System.out.println("");
//                
//                
//                
//                
//                
//                 /****************************************************************************************************
//                 *                         BinarySearch: RETRIEVE
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   btree.find(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("BinarySearch:" );
//                System.out.println("RETRIEVE minimum time:"+minimum);
//                System.out.println("RETRIEVE maximum time:"+maximum);
//                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
//                System.out.println("RETRIEVE sum time:"+sum); 
//                System.out.println("");
//                
//                
//                
//                 /****************************************************************************************************
//                 *                         SortedDoubleLinkedList: RETRIEVE
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   sortedDoubleLinkedList.retrieve(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("SortedDoubleLinkedList:" );
//                System.out.println("RETRIEVE minimum time:"+minimum);
//                System.out.println("RETRIEVE maximum time:"+maximum);
//                System.out.println("RETRIEVE average time:"+(minimum + maximum)/2);
//                System.out.println("RETRIEVE sum time:"+sum); 
//                System.out.println("");
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         RedBlackTree: predecessor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   redBlackTree.predecessor(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//
//                    
//                System.out.println("RedBlackTree:" );
//                System.out.println("predecessor minimum time:"+minimum);
//                System.out.println("predecessor maximum time:"+maximum);
//                System.out.println("predecessor average time:"+(minimum + maximum)/2);
//                System.out.println("predecessor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SkipList: predecessor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   skipList.predecessors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//
//                    
//                System.out.println("SkipList:" );
//                System.out.println("predecessor minimum time:"+minimum);
//                System.out.println("predecessor maximum time:"+maximum);
//                System.out.println("predecessor average time:"+(minimum + maximum)/2);
//                System.out.println("predecessor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         BinarySearchTree: predecessor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   btree.predcessors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//
//                    
//                System.out.println("BinarySearchTree:" );
//                System.out.println("predecessor minimum time:"+minimum);
//                System.out.println("predecessor maximum time:"+maximum);
//                System.out.println("predecessor average time:"+(minimum + maximum)/2);
//                System.out.println("predecessor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SortedDoubleLinkedList: predecessor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   sortedDoubleLinkedList.predcessors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//
//                    
//                System.out.println("SortedDoubleLinkedList:" );
//                System.out.println("predecessor minimum time:"+minimum);
//                System.out.println("predecessor maximum time:"+maximum);
//                System.out.println("predecessor average time:"+(minimum + maximum)/2);
//                System.out.println("predecessor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         RedBlackTree: successor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   redBlackTree.successor(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("RedBlackTree:" );
//                System.out.println("successor minimum time:"+minimum);
//                System.out.println("successor maximum time:"+maximum);
//                System.out.println("successor average time:"+(minimum + maximum)/2);
//                System.out.println("successor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SkipList: successor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   skipList.successors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//
//
//                System.out.println("SkipList:" );
//                System.out.println("successor minimum time:"+minimum);
//                System.out.println("successor maximum time:"+maximum);
//                System.out.println("successor average time:"+(minimum + maximum)/2);
//                System.out.println("successor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                 /****************************************************************************************************
//                 *                         BinarySearchTree: successor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   btree.successors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("BinarySearchTree:" );
//                System.out.println("successor minimum time:"+minimum);
//                System.out.println("successor maximum time:"+maximum);
//                System.out.println("successor average time:"+(minimum + maximum)/2);
//                System.out.println("successor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SortedDoubleLinkedList: successor
//                *****************************************************************************************************/
//                minimum = Integer.MAX_VALUE; 
//                maximum = Integer.MIN_VALUE;
//                sum = 0;
//                begin=0;
//                finish=0;
//                elapsed=0;
//                for(int i = 0; i < 10; i++)
//                {
//                   begin = System.nanoTime();
//                   sortedDoubleLinkedList.successors(chosen[i]);
//                   finish = System.nanoTime();
//                   elapsed = finish - begin;
//                   if(elapsed < minimum)
//                   {
//                       minimum = elapsed;
//                   }
//                   if(elapsed > maximum)
//                   {
//                       maximum = elapsed;
//                   }
//                   sum += elapsed;
//                }
//                System.out.println("SortedDoubleLinkedList:" );
//                System.out.println("successor minimum time:"+minimum);
//                System.out.println("successor maximum time:"+maximum);
//                System.out.println("successor average time:"+(minimum + maximum)/2);
//                System.out.println("successor sum time:"+sum);  
//                System.out.println("");
//                
//                
//                
//                
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         RedBlackTree: minimum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                redBlackTree.minimum();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("RedBlackTree:" );
//                System.out.println("actually time for minimum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SkipList: minimum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                skipList.first();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("SkipList:" );
//                System.out.println("actually time for minimum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         BinarySearchTree: minimum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                btree.first();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("BinarySearchTree:" );
//                System.out.println("actually time for minimum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SortedDoubleLinkedList: minimum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                sortedDoubleLinkedList.minimum();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("SortedDoubleLinkedList:" );
//                System.out.println("actually time for minimum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                /****************************************************************************************************
//                 *                         RedBlackTree: maximum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                redBlackTree.maximum();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("RedBlackTree:" );
//                System.out.println("actually time for maximum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         SkipList: maximum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                skipList.last();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("SkipList:" );
//                System.out.println("actually time for maximum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                
//                /****************************************************************************************************
//                 *                         BinarySearchTree: maximum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                btree.last();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("BinarySearchTree:" );
//                System.out.println("actually time for maximum:"+elapsed);
//                System.out.println("");
//                
//                
//                
//                
//                 /****************************************************************************************************
//                 *                         SortedDoubleLinkedList: maximum
//                *****************************************************************************************************/
//                begin = System.nanoTime();
//                sortedDoubleLinkedList.maximum();
//                finish = System.nanoTime();
//                elapsed = finish - begin;
//
//                System.out.println("SortedDoubleLinkedList:" );
//                System.out.println("actually time for maximum:"+elapsed);
//                System.out.println("");
                

     




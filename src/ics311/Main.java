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
                    case delete:
                        set.delete(d);
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
            pack[1]= sum/list.size(); 
            pack[2]= maximum;
            
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
            insert, retrieve, predecessor, successor, delete,
            minimum, maximum
        }
        
        
	
	public static void main(String[] args) {
            
                ArrayList<String> datas = loadData(args[0]);
                ArrayList<String> randomedData = randomData(datas, 10);
                
                ArrayList<DynamicSet<String, String>> sets = new ArrayList<DynamicSet<String, String>>();
                long[][] timeTable = new long[4][17];
                
		DynamicSet<String, String> redBlackTree  = new RedBlackTree<String,String>("RBtree");
		DynamicSet<String, String> skiplist = new SkipList<String, String>("Skiplist");
                BinarySearchTree btree = new BinarySearchTree( );
                SortedDoubleLinkedList sortedDoubleLinkedList = new SortedDoubleLinkedList();
                
                sets.add(redBlackTree);
                sets.add(skiplist);
                sets.add(skiplist);
                sets.add(skiplist);
                
                
                /****************************************************************************************************
                 *                                     run time test
                *****************************************************************************************************/
                int ii =0 ;
                for(DynamicSet<String, String> set: sets){
                    
                    
                    int j =0 ;
                    long[] pack = new long[3];
                    for(Method m: Method.values()){

                        switch (m) {
                            case insert:
                                pack = new long[3];
                                pack = call(set, datas, m);
                                timeTable[ii][j]= pack[0];  j=j+1;
                                timeTable[ii][j]= pack[1];  j=j+1;
                                timeTable[ii][j]= pack[2];  j=j+1;
                                break;
                            case retrieve: case predecessor: case successor: case delete:
                                pack = new long[3];
                                pack = call(set, randomedData, m);
                                timeTable[ii][j]= pack[0];  j=j+1;
                                timeTable[ii][j]= pack[1];  j=j+1;
                                timeTable[ii][j]= pack[2];  j=j+1;
                                break;
                            case minimum:
                                long time = call2(set, m);
                                timeTable[ii][j]= time;  
                                j=j+1;
                                break;
                            case maximum: 
                                long t = call2(set, m);
                                timeTable[ii][j]= t;  
                                break;
                          
                        }// end of for loop of method
                       
                    }// end of for loop of Method: insert, retrieve....
                    ii++;
                }// end of looping tree sets
//              
                String width1 = "30s";
                String width2 = "10s";
                String width3 = "---------*";
                System.out.printf("%-15s",  "*--------------" );
                System.out.printf("%-"+width1+"","*-----------------------------");
                System.out.printf("%-"+width1+"","*-----------------------------");
                System.out.printf("%-"+width1+"","*-----------------------------");
                System.out.printf("%-"+width1+"","*-----------------------------");
                System.out.printf("%-"+width1+"","*-----------------------------*");
                System.out.printf("%-"+width2+"", width3);
                System.out.printf("%-"+width2+"", width3);
                System.out.println("");
                System.out.printf("%-15s",  "|   Output" );
                System.out.printf("%-"+width1+"", "|         Insert");
                System.out.printf("%-"+width1+"", "|         Retrieve");
                System.out.printf("%-"+width1+"", "|         Predecessor");
                System.out.printf("%-"+width1+"", "|         Successor");
                System.out.printf("%-"+width1+"", "|         Delete");
                System.out.printf("%-"+width2+"", "| Miminum");
                System.out.printf("%-"+width2+"", "| Maximum");
                System.out.printf("%-"+width2+"", "|");
                System.out.println("");
                System.out.printf("%-15s",  "*--------------" );
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------*");
                System.out.printf("%-"+width2+"", width3);
                System.out.printf("%-"+width2+"", width3);
                System.out.println("");
                for(int i=0; i<4; i++){
                    System.out.printf("%-15s",  "|   "+sets.get(i).setDataStructure()  );
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][0]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][1]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][2]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][3]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][4]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][5]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][6]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][7]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][8]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][9]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][10]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][11]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][12]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][13]);
                    System.out.printf("%-"+width2+"", "/"+timeTable[i][14]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][15]);
                    System.out.printf("%-"+width2+"", "|"+timeTable[i][16]);
                    System.out.printf("%-"+width2+"", "|");
                    System.out.println("");
                }    
                System.out.printf("%-15s",  "*--------------" );
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------");
                System.out.printf("%-"+width1+"", "*-----------------------------*");
                System.out.printf("%-"+width2+"", width3);
                System.out.printf("%-"+width2+"", width3);
                System.out.println("");
            }// end of main

}// end of class

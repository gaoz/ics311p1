package ics311;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class main2 {
	
	static final int NUMOFPICKS = 10; //number of random picks we are doing
	static ArrayList<String> inputs = new ArrayList<String>();
	static String[] randomPicks = new String[NUMOFPICKS]; 	//Array to store the random keys that are chosen
	
	public static void main(String[] args) {
		
		File file = new File(args[0]);
                
		try(BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    for(String line; (line = br.readLine()) != null; )
		    {
		    	//Filling up the ArrayList, will be use for insertion
		    	inputs.add(line);
		    }
		    
		    /*After reading in all the names and stored them into the ArrayList
		     we take random picks and store the names in to randomPicks Array. */
		    getRandomKeys();
		    
		    long[][] table = new long[4][17]; //table use to store all the timing.
		    long[] temp = new long[3]; //temporary array use to store the timing from method return
		    ArrayList <DynamicSet<String, String>> sets = new ArrayList<DynamicSet<String, String>>();
		    
		    DynamicSet<String, String> redBlackDynamicSet = new RedBlackTree<String, String>("rb");			/*******************************************/
		    DynamicSet<String, String> skipListDynamicSet = new SkipList<String, String>("skiplist");        ////////////////////////////////////////////////
		    DynamicSet<String, String> SDLListDynamicSet = new RedBlackTree<String, String>("rb");       ////Change those 3 to appropriate ADT///////////
		    DynamicSet<String, String> BinarySearchDynamicSet = new RedBlackTree<String, String>("rb");    ////////////////////////////////////////////////
		    
		    sets.add(redBlackDynamicSet);
		    sets.add(skipListDynamicSet);
		    sets.add(SDLListDynamicSet);
		    sets.add(BinarySearchDynamicSet);
		    
		    int i = 0;
		    for(DynamicSet<String, String> set : sets)
		    {
		    	temp = timeInsert(set);
		    	table[i][0] = temp[0];
		    	table[i][1] = temp[1];
		    	table[i][2] = temp[2];
		    	
		    	temp = timeRetrieve(set);
		    	table[i][3] = temp[0];
		    	table[i][4] = temp[1];
		    	table[i][5] = temp[2];
		    	
		    	temp = timePredecessor(set);
		    	table[i][6] = temp[0];
		    	table[i][7] = temp[1];
		    	table[i][8] = temp[2];
		    	
		    	temp = timeSuccessor(set);
		    	table[i][9] = temp[0];
		    	table[i][10] = temp[1];
		    	table[i][11] = temp[2];
		    	
		    	table[i][12] = timeMinimum(set);
		    	
		    	table[i][13] = timeMaximum(set);
		    	
		    	temp = timeDelete(sets.get(i));
		    	table[i][14] = temp[0];
		    	table[i][15] = temp[1];
		    	table[i][16] = temp[2];
		    	
		    	i++;
		    }
		    
		    //////PRINTING////////////////////
		    String border = "+---------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+%n";
			String leftAlignFormat = "|%-15s|%-7d/%-7d/%-7d|%-7d/%-7d/%-7d|%-7d/%-7d/%-7d|%-7d/%-7d/%-7d|%-23d|%-23d|%-7d/%-7d/%-7d|%n";
			
			System.out.printf(border);
			System.out.printf("|%-15s|%-23s|%-23s|%-23s|%-23s|%-23s|%-23s|%-23s|%n"
					, " ", "Insert", "Retrieve", "Pred", "Succ", "Minimum", "Maximum", "Delete");
			System.out.printf(border);
			System.out.printf(leftAlignFormat, "RB Tree",table[0][0],table[0][1],table[0][2],table[0][3],table[0][4],table[0][5],table[0][6],table[0][7],table[0][8],table[0][9],table[0][10],table[0][11],table[0][12],table[0][13],table[0][14],table[0][15],table[0][16]);
			System.out.printf(border);
			System.out.printf(leftAlignFormat, "Skip List",table[1][0],table[1][1],table[1][2],table[1][3],table[1][4],table[1][5],table[1][6],table[1][7],table[1][8],table[1][9],table[1][10],table[1][11],table[1][12],table[1][13],table[1][14],table[1][15],table[1][16]);
			System.out.printf(border);
			System.out.printf(leftAlignFormat, "Linked List",1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);
			System.out.printf(border);
			System.out.printf(leftAlignFormat, "Binary Tree",1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);
			System.out.printf(border);
		    
		}
		catch(IOException e){
			System.out.print(e);
		}

	}
	
	//This method will generate 10 random numbers ranging from 0 to 
	//the size of the ArrayList(which is where all the item is stored
	//before it's being insert to different DynamicSets). It will pick
	//10 random item from the ArrayList and put them into the chosen array
	//so we can reuse them in other methods.
	public static void getRandomKeys()
	{
		Random generator = new Random();
		for(int i = 0; i < NUMOFPICKS; i++)
		{
			int random = generator.nextInt(inputs.size());
			randomPicks[i] = inputs.get(random);
		}
	}
	
	//This method will take any DynamicSet with 2 String parameter and calculate
	//the time of insertions. Will return an array of timings.
	public static long[] timeInsert(DynamicSet<String, String> s)
	{
		//reset the class variables
		long insertMin = Integer.MAX_VALUE;
		long insertMax = Integer.MIN_VALUE;
		long insertSum = 0;
		
		for(int i = 0; i < inputs.size(); i++)
		{
			long begin = System.nanoTime();
			s.insert(inputs.get(i), inputs.get(i));
			long finish = System.nanoTime();
			long elapsed = finish - begin;
			if(elapsed < insertMin)
			{
				insertMin = elapsed;
			}
			if(elapsed > insertMax)
			{
				insertMax = elapsed;
			}
			insertSum += elapsed;
		}
		//System.out.println("inserMin = " + insertMin);   /////////////////test//////////////////////
		long insertAvg = insertSum / inputs.size();
		
		long[] timing = new long[3];
		timing[0] = insertMin;
		timing[1] = insertAvg;
		timing[2] = insertMax;
		return timing;
	}
	
	//This method will take any DynamicSet with 2 String parameter and calculate
	//the time of retrieval. Willing return an array of timings.
	public static long[] timeRetrieve(DynamicSet<String, String> s)
	{
		//reset the class variables
		long retrieveMin = Integer.MAX_VALUE;
		long retrieveMax = Integer.MIN_VALUE;
		long retrieveSum = 0;
		
		for(int i = 0; i < NUMOFPICKS; i++)
		{
			long begin = System.nanoTime();
			s.retrieve(randomPicks[i]);
			long finish = System.nanoTime();
			long elapsed = finish - begin;
			if(elapsed < retrieveMin)
			{
				retrieveMin = elapsed;
			}
			if(elapsed > retrieveMax)
			{
				retrieveMax = elapsed;
			}
			retrieveSum += elapsed;
		}
		long retrieveAvg = retrieveSum / NUMOFPICKS;
		
		long[] timing = new long[3];
		timing[0] = retrieveMin;
		timing[1] = retrieveAvg;
		timing[2] = retrieveMax;
		return timing;
	}
	
	//This method will take any DynamicSet with 2 String parameter and calculate
	//the time to find the successor. Willing return an array of timings..
	public static long[] timeSuccessor(DynamicSet<String, String> s)
	{
		//reset the class variables
		long successorMin = Integer.MAX_VALUE;
		long successorMax = Integer.MIN_VALUE;
		long successorSum = 0;
		
		for(int i = 0; i < NUMOFPICKS; i++)
		{
			long begin = System.nanoTime();
			s.successor(randomPicks[i]);
			long finish = System.nanoTime();
			long elapsed = finish - begin;
			if(elapsed < successorMin)
			{
				successorMin = elapsed;
			}
			if(elapsed > successorMax)
			{
				successorMax = elapsed;
			}
			successorSum += elapsed;
		}
		long successorAvg = successorSum / NUMOFPICKS;
		
		long[] timing = new long[3];
		timing[0] = successorMin;
		timing[1] = successorAvg;
		timing[2] = successorMax;
		return timing;
	}
	
	//This method will take any DynamicSet with 2 String parameters and calculate
	//the time to find the predecessor. Willing return an array of timings.
	public static long[] timePredecessor(DynamicSet<String, String> s)
	{
		//reset the class variables
		long predecessorMin = Integer.MAX_VALUE;
		long predecessorMax = Integer.MIN_VALUE;
		long predecessorSum = 0;
		
		for(int i = 0; i < NUMOFPICKS; i++)
		{
			long begin = System.nanoTime();
			s.predecessor(randomPicks[i]);
			long finish = System.nanoTime();
			long elapsed = finish - begin;
			if(elapsed < predecessorMin)
			{
				predecessorMin = elapsed;
			}
			if(elapsed > predecessorMax)
			{
				predecessorMax = elapsed;
			}
			predecessorSum += elapsed;
		}
		long predecessorAvg = predecessorSum / NUMOFPICKS;
		
		long[] timing = new long[3];
		timing[0] = predecessorMin;
		timing[1] = predecessorAvg;
		timing[2] = predecessorMax;
		return timing;
	}
	
	//This method will take any DynamicSet with 2 String parameters and calculate
	//the time to find the minimum. will return the timing.
	public static long timeMinimum(DynamicSet<String, String> s)
	{
		long begin = System.nanoTime();
		s.minimum();
		long finish = System.nanoTime();
		long minimum = finish - begin;
		return minimum;
	}
	
	//This method will take any DynamicSet with 2 String parameters and calculate
	//the time to find the maximum. will return the timing.
	public static long timeMaximum(DynamicSet<String, String> s)
	{
		long begin = System.nanoTime();
		s.maximum();
		long finish = System.nanoTime();
		long maximum = finish - begin;
		return maximum;
	}
	
	//This method will take any DynamicSet with 2 String parameters and calculate
	//the time to delete. Willing return an array of timings.
	public static long[] timeDelete(DynamicSet<String, String> s)
	{
		//reset the class variables
		long deleteMin = Integer.MAX_VALUE;
		long deleteMax = Integer.MIN_VALUE;
		long deleteSum = 0;
		
		for(int i = 0; i < NUMOFPICKS; i++)
		{
			long begin = System.nanoTime();
			s.delete(randomPicks[i]);
			long finish = System.nanoTime();
			long elapsed = finish - begin;
			if(elapsed < deleteMin)
			{
				deleteMin = elapsed;
			}
			if(elapsed > deleteMax)
			{
				deleteMax = elapsed;
			}
			deleteSum += elapsed;
		}
		//System.out.println("deleteMin = " + deleteMin);   ///////////////////test///////////////////////
		long deleteAvg = deleteSum / NUMOFPICKS;
		
		long[] timing = new long[3];
		timing[0] = deleteMin;
		timing[1] = deleteAvg;
		timing[2] = deleteMax;
		return timing;
	}

}
package ics311;

import java.util.Map.Entry;
import java.util.TreeMap;

public class RedBlackTree<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	
	private String dataStructureName;
	private TreeMap<Key, Value> tree;
   
	public RedBlackTree(String dataStructureName)
	{       
		tree = new TreeMap<Key, Value>();
		this.dataStructureName = dataStructureName;
	}
	
	@Override
	public String setDataStructure() {
		
		return dataStructureName;
	}

	@Override
	public int size() {
		
		return tree.size();
	}

	@Override
	public Value insert(Key key, Value value) {
		
		return tree.put(key, value);
	}

	@Override
	public Value delete(Key key) {
		
		return tree.remove(key);
	}

	@Override
	public Value retrieve(Key key) {
		
		return tree.get(key);
	}

	@Override
	public Entry<Key, Value> minimum() {
	
		return tree.firstEntry();
	}

	@Override
	public Entry<Key, Value> maximum() {
	
		return tree.lastEntry();
	}

	@Override
	public Entry<Key, Value> successor(Key key) {
		
		return tree.higherEntry(key);
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		
		return tree.lowerEntry(key);
	}

}
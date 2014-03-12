package ics311;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

public class SkipList<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	
	private String dataStructureName;
	private ConcurrentSkipListMap<Key, Value> tree;
        private int i;
	public SkipList(String dataStructureName)
	{       i=0;
                tree = new ConcurrentSkipListMap<Key, Value>();
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
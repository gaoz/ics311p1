package ics311;

import java.util.Map.Entry;
import java.util.TreeMap;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements DynamicSet<Key, Value> {

	private class BSTnode
        {   Key key;  
            Value value;
            BSTnode left,right,parent; 

            BSTnode ( Key k, Value v ) // Construction only of leaf nodes.
            {   this.key  = k;
                this.value= v;
                this.left = this.right = null;
            }
        } // end class BSTnode

	private String dataStructureName;
	private BSTnode root;
        private int size;
   
	public BinarySearchTree(String dataStructureName)
	{       size = 0;
		root = null;
		this.dataStructureName = dataStructureName;
	}
	
	@Override
	public String setDataStructure() {
		
		return dataStructureName;
	}

	@Override
	public int size() {
		this.print();
		return size;
	}
       
        
	@Override
	public Value insert( Key k, Value v)
        {       
                int compare = 0;
                
                if(root == null){
                    
                    root = new BSTnode(k,v);
                    
                }else{
                    BSTnode current= root;
            
                    while ( current != null )                   // Continue to failure
                    {
                        compare = current.key.compareTo(k);

                        if ( compare < 0 ){             // Down left

                            if( current.left == null){
                                current.left = new BSTnode(k, v);
                                current.left.parent = current;
                                break;

                            }else{
                                current = current.left;
                            }
                        }
                        else if ( compare > 0 )         // Down right
                        {    if( current.right == null){
                                current.right = new BSTnode(k, v);
                                current.right.parent = current;
                                break;

                            }else{
                                current = current.right;
                            }
                        }
                        else{    
                            Value oldValue = current.value;
                            current.value = v;
                            return oldValue; 
                        }   // so exit loop
                    }
                }
                size++;
                return null;
                
               
        } // end insert()
        public void print(){
            System.out.println(root.key.toString());
            System.out.println(root.left.key.toString());
            System.out.println(root.left.left.key.toString());
        }
	@Override
	public Value delete(Key key) {
		
                BSTnode current = find(key);
                
                if(current == null){
                    return null;
                }
                
                
                else if(current.left == null && current.right == null){
                    if(current.parent.right == current){
                        current.parent.right = null;
                    }else{
                        current.parent.left = null;
                    }
                    return current.value;
                    
                }else if(current.left != null && current.right == null){
                    if(current.parent.right == current){
                        current.left.parent = current.parent;
                    }else{
                        current.left.parent = current.parent;
                    }
                    return current.value;
                    
                }else if(current.right != null && current.left == null){
                     if(current.parent.left == current){
                        current.right.parent = current.parent;
                    }else{
                        current.left.parent = current.parent;
                    }
                    return current.value;
                    
                    
                }else {
                    BSTnode node  = getMinimum(current);
                    Key k = node.key;
                    Value v = node.value;
                    delete(node.key);
                    Value value = current.value;
                    current.key = k;
                    current.value = v ;
                    return value;
                    
                }
                
                
		
	}

	@Override
	public Value retrieve(Key key) {
		return null;
		//return find(key).value;
	}
        
        private BSTnode find ( Key key )
        {       
                BSTnode current = root;
                int compare = 0;
                while ( current != null )                   // Continue to failure
                {
                    compare = current.key.compareTo(key);
                    
                    if ( compare < 0 )             // Down left
                        current = current.left;
                    
                    else if ( compare > 0 )// Down right
                        current = current.right;
                    
                    else                               // We GOT it!
                        break;                             // so exit loop
                }

                return current;
        }
        /*
        BSTnode find ( BSTnode node, Object value )
        {  BSTnode found = node;

        // Node exists, but it is not the correct one.  NOTE:  it is critical that
        // the base class "equals" of Object be overridden as appropriate.
        if ( node != null && !value.equals(node.data) )
        {
            found = find ( node.left, value );   // Search left first
            if ( found == null )                 // Failed to find on left
                found = find( node.right, value );// so search right
        }

        return found;
        }
        
        */
	@Override
	public Entry<Key, Value> minimum() {
            
                BSTnode node = getMinimum(root);
              
                if(node==null){
                    return null;
                }else {
                     return new SetEntry(node.key, node.value);
                }
        	
	}
        private BSTnode getMinimum(BSTnode search){
                BSTnode current = search;
                if(current==null){
                    return null;
                }
                while(current.left != null){
                    
                    current.left = current;
                    
                    return current;
                }
                return current;
        }
        
	@Override
	public Entry<Key, Value> maximum() {
                BSTnode node = getMaximum(root);
              
                if(node==null){
                    return null;
                }else {
                     return new SetEntry(node.key, node.value);
                }
                
	}
        public BSTnode getMaximum(BSTnode search) {
               BSTnode current = search;
                if(current==null){
                    return null;
                }
                while(current.left != null){
                    
                    current.left = current;
                    
                    return current;
                }
                return current;
	}
        
        
        private Entry<Key, Value> j;
        
	@Override
	public Entry<Key, Value> successor(Key key) {
		BSTnode current = find(key) ;
                BSTnode answer;
                if(current.parent.left == current ){  // case 1 on left subtree
                     
                    if(current.right==null){
                        answer = current.parent;
                    }else{
                        answer = getMinimum(current.right);
                    }
                    
                    
                }else{                                 // case 2 on right subtree
                    if(current.right==null){
                        return null;
                    }else{
                        answer = getMinimum(current.right);
                    }
                    
                    
                }
                
		return new SetEntry(answer.key, answer.value);
	}

	@Override
	public Entry<Key, Value> predecessor(Key key) {
		
		return j;
	}

}
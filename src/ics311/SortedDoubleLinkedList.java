/*
 * @author   Timothy J. Rolfe
 * Version 2010 October 22
 */

package ics311;

/*
 *  Java Program to Implement Sorted Doubly Linked List
 */
 
import java.util.Scanner;
 
/*  Class Node  */
class Node
{
    protected Comparable data;
    protected Node next, prev;
 
    /* Constructor */
    public Node()
    {
        next = null;
        prev = null;
        data = null;
    }
    /* Constructor */
    public Node(Comparable d, Node n, Node p)
    {
        data = d;
        next = n;
        prev = p;
    }
    /* Function to set link to next node */
    public void setLinkNext(Node n)
    {
        next = n;
    }
    /* Function to set link to previous node */
    public void setLinkPrev(Node p)
    {
        prev = p;
    }    
    /* Funtion to get link to next node */
    public Node getLinkNext()
    {
        return next;
    }
    /* Function to get link to previous node */
    public Node getLinkPrev()
    {
        return prev;
    }
    /* Function to set data to node */
    public void setData(Comparable d)
    {
        data = d;
    }
    /* Function to get data from node */
    public Comparable getData()
    {
        return data;
    }
}
 
/* Class linkedList */
public class SortedDoubleLinkedList
{
    protected Node start;
    public int size;
 
    /* Constructor */
    public SortedDoubleLinkedList()
    {
        start = null;
        size = 0;
    }
    /* Function to check if list is empty */
    public boolean isEmpty()
    {
        return start == null;
    }
    /* Function to get size of list */
    public int getSize()
    {
        return size;
    }
    /* Function to insert element */
    public void insert(Comparable val)
    {
        Node nptr = new Node(val, null, null);
        Node tmp, ptr;        
        boolean ins = false;
        if(start == null)
            start = nptr;
        else if(val.compareTo(start.getData())<=0) //(val <= start.getData())
        {
            nptr.setLinkNext(start);
            start.setLinkPrev(nptr);
            start = nptr;
        }
        else
        {
            tmp = start;
            ptr = start.getLinkNext();
            while(ptr != null)
            {
                if(val.compareTo(tmp.getData())>=0 && val.compareTo(ptr.getData())<=0 )//(val >= tmp.getData() && val <= ptr.getData())
                {
                    tmp.setLinkNext(nptr);
                    nptr.setLinkPrev(tmp);
                    nptr.setLinkNext(ptr);
                    ptr.setLinkPrev(nptr);
                    ins = true;
                    break;
                }
                else
                {
                    tmp = ptr;
                    ptr = ptr.getLinkNext();
                }
            }
            if(!ins)
            {
                tmp.setLinkNext(nptr);
                nptr.setLinkPrev(tmp);
 
            }
        }
        size++;
    }
    /* Function to delete node at position */
    public void deleteAtPos(int pos)
    {        
        if (pos == 1) 
        {
            if (size == 1)
            {
                start = null;
                size = 0;
                return; 
            }
            start = start.getLinkNext();
            start.setLinkPrev(null);
            size--; 
            return ;
        }
        if (pos == size) 
        {
            Node ptr = start;
 
            for (int i = 1; i < size - 1; i++)
                ptr = ptr.getLinkNext();
            ptr.setLinkNext(null);            
            size --;
            return;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();
 
                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }        
    }
    public Comparable retrieve(Comparable val)
    {   Node ptr = start;
        while(val!=null && ptr!=null){
           if(val.compareTo(ptr.getData())==0){
               break;
           }
           ptr = ptr.getLinkNext();
       }
       return ptr.getData();
           
    }
    public Comparable maximum()
    {   Node ptr = start;
        Node pre = null;
        while(ptr!=null){
           pre = ptr;
           ptr = ptr.getLinkNext();
       }
       return pre.getData();
           
    }
    public Comparable predcessors(Comparable val)
    {   Node ptr = start;
        while(val!=null && ptr!=null){
           if(val.compareTo(ptr.getData())==0){
               break;
           }
           ptr = ptr.getLinkNext();
       }
       return ptr.getLinkPrev().getData();
           
    }
    public Comparable successors(Comparable val)
    {   Node ptr = start;
        while(val!=null && ptr!=null){
           if(val.compareTo(ptr.getData())==0){
               break;
           }
           ptr = ptr.getLinkNext();
       }
       return ptr.getLinkNext().getData();
           
    }
    public Comparable minimum(){
        return start.getData();
    }
   
    /* Function to display status of list */
    public void display()
    {
        System.out.print("Doubly Linked List = ");
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == null) 
        {
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != null)
        {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");
    }
}
 
/* Class SortedDoublyLinkedList */

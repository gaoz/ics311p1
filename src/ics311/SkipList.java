package ics311;


/*************************************************************************************************************************
*  @author  Luca Carlon
*Terms of Agreement:   
*By using this code, you agree to the following terms...   
*You may use this code in your own programs (and may compile it into a program and distribute it in compiled format for languages that allow it) freely and with no charge.
*You MAY NOT redistribute this code (for example to a web site) without written permission from the original author. Failure to do so is a violation of copyright laws.   
*You may link to this code from another website, but ONLY if it is not wrapped in a frame. 
*You will abide by any additional copyright restrictions which the author may have placed in the code or code's description.
***************************************************************************************************************************/


import java.util.Random;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;


public class SkipList implements Container, SkipListInterface
{

   private static class MyEntry implements Entry
   {
      Object key;
      Object value;
      

      public MyEntry() {}
      public MyEntry(Object key, Object value)
      {
         this.key = key;
         this.value = value;
      }
      
      public Object value() {return value;}
      public Object key() {return key;}
      public String toString() {return "(" + key + ", " + value + ")";}
   }
   

   private static class Nodo
   {
      Nodo next;
      Nodo below;
      Entry entry;
      
      public Nodo() {}
      public Nodo(Nodo next, Nodo below, Entry entry)
      {
         this.next = next;
         this.below = below;
         this.entry = entry;
      }
      
      public Entry element() {return entry;}
   }
   

   private static class DefComp implements Comparator
   {
      public int compare(Object x, Object y)
      {
         return ((Comparable)x).compareTo((Comparable)y);
      }
   }
   

   private class SkipListIterator implements Iterator
   {
      Nodo current;
      Object key;
      int i;
      
      public SkipListIterator() {this(new Integer(1), 1);}
      
      public SkipListIterator(Object key, int i) throws NullPointerException
      {
         if (key == null) throw new NullPointerException("Null keys not accepted");

         this.i = i;
         this.key = key;

         if (i == 2 || i == 1) current = buttomLeft;

         else current = beforeNode(key);
      }
      

      public boolean hasNext()
      {
         switch(i)
         {
            case 1: return current.next.element().key() != null;
            case 2: return (current.next.element().key() != null && 
                            comp.compare(current.next.element().key(), key) <= 0);
            case 3: return (current.next.element().key() != null && 
                            comp.compare(current.next.element().key(), key) >= 0);
            case 4: return (current.next.element().key() != null &&
                            comp.compare(current.next.element().key(), key) == 0);
         }
         return false;
      }
      

      public Object next() throws NoSuchElementException
      {
         if (!hasNext()) throw new NoSuchElementException("No next entry");
         current = current.next;
         return current.element();
      }
   }
   

   private int coinFlip() {return rand.nextInt(2);}
   

   private Nodo beforeNode(Object key) throws NullPointerException
   {
      if (key == null) throw new NullPointerException("Cannot search for null key");
      Nodo v = topLeft;

      while (v.below != null)
      {
         v = v.below;
         while (v.next.element().key() != null &&
                comp.compare(v.next.element().key(), key) < 0) v = v.next;
      }
      return v;
   }
   

   private Nodo insertAfterAbove(Nodo after, Nodo above, Entry entry)
   {
      Nodo n;
      if (after != null)
      {
         n = new Nodo(after.next, above, entry);
         after.next = n;
      }
      else n = new Nodo(null, above, entry);
      return n;
   }
   

   private Nodo skipSearch(Object key) throws NullPointerException
   {
      if (key == null) throw new NullPointerException("Cannot search null key");
      Nodo p = topLeft;
      while (p.below != null)
      {
         p = p.below;
         //System.out.println("Scendo in: " + p.element().key());

         while (p.next.element().key() != null &&
                comp.compare(key, p.next.element().key()) >= 0)
         {
            p = p.next;
            //System.out.println("Avanzo in: " + p.element().key());
         }
      }
      return p;
   }

   private Entry skipInsert(Object key, Object value) throws NullPointerException
   {
      if (key == null) throw new NullPointerException("Cannot insert null key or value");
      Entry e = new MyEntry(key, value);
      int i = 0;
      size++;

      while (coinFlip() == HEAD)
      {
         i++;
         if (i >= h - 1)
         {
            Nodo t = topLeft.next;
            topLeft = insertAfterAbove(null, topLeft, new MyEntry());
            insertAfterAbove(topLeft, t, new MyEntry());
            h++;
         }
      }

      Nodo p = topLeft;
      Nodo prev = new Nodo();
      Nodo nuovo = new Nodo();

      for (int j = h - 1; j >= 1; j--)
      {
         p = p.below;
         while (p.next.element().key() != null &&
                comp.compare(key, p.next.element().key()) >= 0) p = p.next;

         if (j <= i + 1)
         {

            if (j == i + 1) prev = insertAfterAbove(p, null, e);

            else
            {
               nuovo = insertAfterAbove(p, null, e);
               prev.below = nuovo;
               prev = nuovo;
            }
         }
      }

      if (prev.next.element().key() == null) buttomLast = prev;
      
      return e;
   }
   

   private Entry skipRemove(Entry entry) throws NullPointerException
   {
      if (entry == null) throw new NullPointerException("Cannot remove a null entry");
      Nodo p = topLeft;

      boolean found = false;

      while (p.below != null)
      {
         p = p.below;
         while (p.next.element().key() != null &&
                comp.compare(entry.key(), p.next.element().key()) > 0) p = p.next;

         if (p.next.element().key() != null && p.next.element() == entry)
         {
            if (p.next == buttomLast) buttomLast = p;
            p.next = p.next.next;
            found = true;
         }
      }

      if (!found) return null;

      else
      {
         size--;
         return entry;
      }
   }

   private final static int HEAD = 1;
   private Random rand = new Random();

   private Comparator comp;

   private Nodo topLeft;

   private Nodo buttomLeft;

   private Nodo buttomLast;

   private int h;
 
   private int size;

   public int size() {return size;}

   public boolean isEmpty() {return size == 0;}

   public Entry insert(Object key, Object value) {return skipInsert(key, value);}

   public Entry remove(Entry entry) {return skipRemove(entry);}
 
   public Entry first() {return isEmpty() ? null : buttomLeft.next.element();}
 
   public Entry last() {return isEmpty() ? null : buttomLast.element();}

   public Iterator entries() {return new SkipListIterator();}

   public Iterator findAll(Object key) {return new SkipListIterator(key, 4);}

   public Iterator successors(Object key) {return new SkipListIterator(key, 3);}

   public Iterator predecessors(Object key) {return new SkipListIterator(key, 2);}
   

   public Entry find(Object key)
   {
      Entry e = skipSearch(key).element();
      if (e.key() == null) return null;
      if (comp.compare(e.key(), key) == 0) return e;
      return null;
   }
 
   public String toString()
   {
      Iterator it = entries();
      String s = "";
      if (it.hasNext()) s += (Entry)it.next();
      while (it.hasNext()) s += "\n" + (Entry)it.next();
      return s;
   }
 
   public void clear()
   {
      Nodo p = topLeft;
      Nodo q = p.next;
      while (p.below != null)
      {
         p = p.below;
         q = q.below;
         p.next = q;
      }
      size = 0;
      return;
   }
   

   public SkipList()
   {
      // Inizializzo le variabili d'istanza
      this.comp = new DefComp();
      h = 2;
      size = 0;
      
      // Costruisco la struttura iniziale a quattro nodi. I nodi nella prima
      // colonna e nell'ultima colonna sono tutti nodi che contengono un
      // riferimento ad una registrazione con chiave e valore entrambi nulli.
      topLeft = new Nodo(null, null, new MyEntry());
      topLeft.next = new Nodo(null, null, new MyEntry());
      topLeft.below = buttomLeft = new Nodo(null, null, new MyEntry());
      topLeft.next.below = new Nodo(null, null, new MyEntry());
      topLeft.below.next = topLeft.next.below;
   }
   
   /**
      Questo costruttore esegue le stesse operazioni del precedente sostituendo
      pero' il comparatore di default con uno diverso passato dall'utente.
      @param comp Comparatore per le chiavi.
   */
   public SkipList(Comparator comp)
   {
      // Chiamo il costruttore senza parametri
      this();
      // Cambio il comparatore da usare con quello passato al costruttore.
      this.comp = comp;
   }
}

// Author: Luca Carlon

// Container interface
interface Container
{
   boolean isEmpty();
   int size();
} 
// Author: Luca Carlon

// OrderedDictionary interface
interface SkipListInterface
{
   public int size();
   public void clear();
   public boolean isEmpty();
   public Entry insert(Object key, Object value) throws NullPointerException;
   public Entry remove(Entry entry) throws NullPointerException;
   public Entry find(Object key) throws NullPointerException;
   public Entry first();
   public Entry last();
   public String toString();
   public Iterator entries();
   public Iterator findAll(Object key);
   public Iterator successors(Object key);
   public Iterator predecessors(Object key);
}
// Author: Luca Carlon

// Iterator interface
interface Iterator
{ 
   boolean hasNext();
   Object next();
}
// Author: Luca Carlon

// Entry interface
interface Entry
{
   public Object key();
   public Object value();
   public String toString();
}

// Author: Luca Carlon


// Author: Luca Carlon

// Comparator interface
interface Comparator
{
   int compare (Object a, Object b);
}
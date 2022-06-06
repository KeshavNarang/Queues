import java.io.*;
import java.lang.*;
import java.util.*;
public class DoubleNodeTester
{
	public static void main (String [] args)
	{
		DoublyLinkedStrings <Character> list = new DoublyLinkedStrings <Character> ();
		
		list.addEnd('C');
		list.addEnd('O');
		list.addEnd('M');
		list.addEnd('P');
		list.addEnd('U');
		list.addEnd('T');
		list.addEnd('E');
		System.out.println(list);
		
		list.addBeginning('M');
		System.out.println(list);
		
		list.addEnd('R');
		System.out.println(list);
		
		list.removeBeginning();
		System.out.println(list);
		
		list.removeEnd();
		System.out.println(list);
		
		list.insertBefore('P', 'M');
		System.out.println(list);
		
		list.insertBefore('M', 'H');
		System.out.println(list);
		
		list.insertBefore('A', 'B');
		System.out.println(list);
		
		list.insertAfter('P', 'C');
		System.out.println(list);
		
		list.insertAfter('M', 'L');
		System.out.println(list);
		
		list.removeNode('M');
		System.out.println(list);
		
		list.removeNode('G');
		System.out.println(list);
		
		list.moveBeginning('P');
		System.out.println(list);
		
		list.moveEnd('L');
		System.out.println(list);
	}
}
class DoublyLinkedStrings <Item> implements Iterable <Item>
{
	private Node first = new Node ();
	private Node last = new Node ();
	private int length = 2;
	
	private class Node
	{
		Node previous;
		Item item;
		Node next;
	}
	
	public void add (Item item, int position)
	{
		Node Before = first;
		if (position > 0)
		{
			for (int i = 0; i < position; i++)
			{
				Before = Before.next;
			}
		}
		
		Node After = last;
		if (position < length - 2)
		{
			After = Before.next;
		}
		
		Node Current = new Node ();
		Before.next = Current;
		Current.previous = Before;
		Current.item = item;
		Current.next = After;
		After.previous = Current;
		
		length++;
	}
	
	public void remove (int position)
	{
		Node Before = first;
		if (position > 0)
		{
			for (int i = 0; i < position; i++)
			{
				Before = Before.next;
			}
		}
		
		Node Delete = Before.next;
		
		Node After = last;
		if (position < length - 2)
		{
			After = Delete.next;
		}
		
		Before.next = After;
		After.previous = Before;
		
		length--;
	}

	public void addBeginning (Item item)
	{
		add(item, 0);
	}
	
	public void addEnd (Item item)
	{
		add(item, length - 2);
	}
	
	public void removeBeginning ()
	{
		remove(0);
	}
	
	public void removeEnd ()
	{
		remove(length - 3);
	}
	
	public int findNode (Item item)
	{
		Node current = first;
		for (int i = 0; i < length - 1; i++)
		{
			current = current.next; 
			if (current.item == item)
			{
				return i;
			}
		}
		return -1;
	}
	
	public void insertBefore (Item target, Item insert)
	{
		int location = findNode(target);
		if (location > -1)
		{
			add(insert, location);
		}
		else
		{
			add(insert, length - 2);
		}
	}

	public void insertAfter (Item target, Item insert)
	{
		int location = findNode(target);
		if (location > -1)
		{
			add(insert, location+1);
		}
		else
		{
			add(insert, length - 2);
		}
	}	
	
	public void removeNode (Item target)
	{
		int location = findNode(target);
		if (location > -1)
		{
			remove(location);
		}
	}
	
	public void moveBeginning (Item target)
	{
		removeNode(target);
		addBeginning(target);
	}
	
	public void moveEnd (Item target)
	{
		removeNode(target);
		addEnd(target);
	}

	public String toString()
	{		
		String toPrint = "";
		for (Item item : this)
		{
			if (item != null)
			{
				toPrint += item + " ";
			}
		}
		return toPrint;
	}
	
	public Iterator <Item> iterator ()
	{
		return new ListIterator ();
	}
	
	private class ListIterator implements Iterator <Item>
	{
		private Node current = first;
		public boolean hasNext ()
		{
			return current != null;
		}
		public Item next ()
		{
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
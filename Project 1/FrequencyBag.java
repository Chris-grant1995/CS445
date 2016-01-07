
public class FrequencyBag<T>
{
	// TO DO: Instance Variables
	int numberOfEntries;
	Node firstNode;
	/**
	 * Constructor
	 * Constructs an empty frequency bag.
	 */
	public FrequencyBag()
	{
		
		numberOfEntries=0;
	}
	
	/**
	 * Adds new entry into this frequency bag.
	 * @param aData the data to be added into this frequency bag.
	 */
	public void add(T aData)
	{
		// TO DO
		if(contains(aData))
		{
			getReferenceTo(aData).frequency++;
			
		}
		else if(numberOfEntries==0)
		{
			firstNode= new Node(aData);
			
		}
		else{
			Node newNode = new Node(aData);
			newNode.next=firstNode;
			firstNode= newNode;
		}
		numberOfEntries++;
	}
	
	/**
	 * Gets the number of occurrences of aData in this frequency bag.
	 * @param aData the data to be checked for its number of occurrences.
	 * @return the number of occurrences of aData in this frequency bag.
	 */
	public int getFrequencyOf(T aData)
	{
		// TO DO
		Node n= getReferenceTo(aData);
		if(numberOfEntries==0)
		{
			return 0;
		}
		else if(n==null)
		{
			return 0;
		}
		//System.out.println(getReferenceTo(aData).frequency);
		else
			return n.frequency;
	}

	/**
	 * Gets the maximum number of occurrences in this frequency bag.
	 * @return the maximum number of occurrences of an entry in this
	 * frequency bag.
	 */
	public int getMaxFrequency()
	{
		if(numberOfEntries==0)
			return 0;
		int max=0;
		Node temp = firstNode;
		//System.out.println(temp.frequency);
		if(temp==null)
			return 0;
		for(int n=0; n<size(); n++ )
		{
			
			if(temp.frequency>max)
			{
				max=temp.frequency;
			}
			if(temp.next!=null)
				temp=temp.next;
		}
		return max;
	}
	
	/**
	 * Gets the probability of aData
	 * @param aData the specific data to get its probability.
	 * @return the probability of aData
	 */
	public double getProbabilityOf(T aData)
	{
		// TO DO
		Node n =getReferenceTo(aData);
		if(n==null)
			return 0;
		double f = n.frequency;
		double s = size();
		return f/s;
	}

	/**
	 * Empty this bag.
	 */
	public void clear()
	{
		// TO DO
		firstNode.next=null;
		firstNode.frequency=0;
		numberOfEntries=0;
		
	}
	
	/**
	 * Gets the number of entries in this bag.
	 * @return the number of entries in this bag.
	 */
	public int size()
	{
		// TO DO
		if(numberOfEntries==0)
		{
			return 0;
		}
		int i=0; 
		Node temp=firstNode;
		while(temp.next!=null)
		{
			i+=temp.frequency;
			temp=temp.next;
		}
		i+= temp.frequency;
		return i;
	}
	public boolean contains(T anEntry)
	{
	   return getReferenceTo(anEntry) != null;
	}
	
	private Node getReferenceTo(T anEntry)
	{
	   boolean found = false;
	   Node currentNode = firstNode;
	   while(!found && (currentNode != null))
	   {
	      if(anEntry.equals(currentNode.data.f))
	      {
	         found = true;
	      }
	      else {
	         currentNode = currentNode.next;
	      }
	   }
	   return currentNode;
	}
	
	class Node
	{
		private Pair data;
		private Node next;
		int frequency=1;
		private Node(T dataPortion)
		{
			
			data = new Pair(dataPortion, frequency);
	        next = null; 
	    }

		
		private Node(T dataPortion, Node nextNode)
		{
			data = new Pair(dataPortion, frequency);
			next = nextNode;
		}
	}
}

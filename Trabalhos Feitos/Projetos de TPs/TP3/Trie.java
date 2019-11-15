class Pair
{
    public Object key;
    public Object value;
    
    public Pair(Object key, Object value)
    {
        this.key = key;
        this.value = value;
    }
}

class NoTrie
{
    private static final int hashSize = 255;
    char letter;
    boolean isLeaf;
    NoTrie[] childs;
    
    private NoTrie(char letter, boolean isLeaf, NoTrie[] childs)
    {
        this.letter = letter;
        this.isLeaf = isLeaf;
        this.childs = childs;
    }
    
    public NoTrie(char letter, boolean isLeaf)
    {
        this( letter, isLeaf, new NoTrie[hashSize] );
    }
    
    public static int hash(char c)
    {
        return (int) c;
    }
}

class Cell
{
	NoTrie node;
	String str;
	int traveledChilds;
	Cell next;
	
	public Cell(NoTrie node, String str, Cell next)
	{
		this.node = node;
		this.str = str;
		this.traveledChilds = 0;
		this.next = next;
	}
}

class Stack
{
	Cell top;
	
	public Stack()
	{
		top = null;
	}
	
	public Cell getTop()
	{
		return top;
	}
	
	public NoTrie getTopNode()
	{
		return getTop().node;
	}
	
	public Cell stack(NoTrie node, String str)
	{
		return top = new Cell(node, str, top);
	}
	
	public void unstack()
	{
		if (top != null)
		{
			Cell newTop = top.next;
			
			top.next = null;
			
			top = newTop;
		}
	}
}

class Trie
{
    NoTrie root;
    
    private Trie(NoTrie root)
    {
        this.root = root;
    }
    
    public Trie()
    {
        this( new NoTrie('\0', false) );
    }
    
    private Pair findMaxCharsOf(String str)
    {
        // guardara' o no' do ultimo caractere encontrado da string
        NoTrie lastCharNode = root;
        int numberOfFoundChars = 0;
        
        if (str != null)
        {
            NoTrie currentChild = root;
            int length = str.length();
            int i;
            
            for (i = 0; currentChild != null && i < length; i++)
            {
                currentChild = lastCharNode.childs[ NoTrie.hash(str.charAt(i)) ];
                
                if (currentChild != null)
                {
                    lastCharNode = currentChild;
                }
            }
            
            numberOfFoundChars = ( currentChild == null ? i - 1 : length );
        }
        
        return new Pair(lastCharNode, numberOfFoundChars);
    }
    
    public boolean search(String str)
    {
		boolean found = false;
		
		if (str != null)
		{
			Pair infoOfLastCharFound = findMaxCharsOf(str);
			
			NoTrie nodeOfLastCharFound = (NoTrie) infoOfLastCharFound.key;
			int numberOfFoundChars = (int) infoOfLastCharFound.value;
			
			found = ( numberOfFoundChars == str.length() && nodeOfLastCharFound.isLeaf  );
		}
        
        return found;
    }
    
    public void insert(String str)
    {
        if (str != null)
        {
			Pair infoOfLastCharFound = findMaxCharsOf(str);
			
			NoTrie nodeOfLastCharFound = (NoTrie) infoOfLastCharFound.key;
			int numberOfFoundChars = (int) infoOfLastCharFound.value;
			int length = str.length();
			
			if (numberOfFoundChars < length && !nodeOfLastCharFound.isLeaf)
			{
				NoTrie currentChild = nodeOfLastCharFound;
				char c;
				
				for (int i = numberOfFoundChars; i < length; i++)
				{
					c = str.charAt(i);
					
					currentChild = currentChild.childs[NoTrie.hash(c)] = new NoTrie(c, false);
				}
				
				currentChild.isLeaf = true;
			}
        }
    }
	
	public void printCrescent(NoTrie currentNode, String currentStr)
	{
		if (!currentNode.isLeaf)
		{
			NoTrie child;
			
			for (int i = 0; i < currentNode.childs.length; i++)
			{
				child = currentNode.childs[i];
				
				if (child != null)
				{
					printCrescent(child, currentStr + child.letter);
				}
			}
		}
		
		else
		{
			System.out.println(currentStr);
		}
	}
	
	public void printCrescent()
	{
		if (root != null)
		{
			printCrescent(root, "");
		}
	}
	
	public void printDecrescent(NoTrie currentNode, String currentStr)
	{
		if (!currentNode.isLeaf)
		{
			NoTrie child;
			
			for (int i = currentNode.childs.length - 1; i > -1; i--)
			{
				child = currentNode.childs[i];
				
				if (child != null)
				{
					printDecrescent(child, currentStr + child.letter);
				}
			}
		}
		
		else
		{
			System.out.println(currentStr);
		}
	}
	
	public void printDecrescent()
	{
		if (root != null)
		{
			printDecrescent(root, "");
		}
	}
	
	public void printCrescentAndInverted(NoTrie currentNode, String currentStr)
	{
		if (!currentNode.isLeaf)
		{
			NoTrie child;
			
			for (int i = 0; i < currentNode.childs.length; i++)
			{
				child = currentNode.childs[i];
				
				if (child != null)
				{
					printCrescentAndInverted(child, currentStr + child.letter);
				}
			}
		}
		
		else
		{
			int length = currentStr.length();
			
			for (int i = length - 1; i > -1; i--)
			{
				System.out.print(currentStr.charAt(i));
			}
			
			System.out.println();
		}
	}
	
	public void printCrescentAndInverted()
	{
		if (root != null)
		{
			printCrescentAndInverted(root, "");
		}
	}
	
	public void printCrescentIteratively()
	{
		if (root != null)
		{
			Stack stack = new Stack();
			
			Cell currentCell = stack.stack(root, "");
			NoTrie current = currentCell.node;
			NoTrie child;
			
			do
			{
				if (!current.isLeaf)
				{
					if (currentCell.traveledChilds < current.childs.length)
					{
						child = current.childs[ currentCell.traveledChilds++ ];
						
						if (child != null)
						{
							currentCell = stack.stack(child, currentCell.str + child.letter);
							current = child;
						}
					}
					
					else
					{
						stack.unstack();
						
						currentCell = stack.top;
						current = currentCell.node;
					}
				}
				
				else
				{
					System.out.println(currentCell.str);
					
					stack.unstack();
					
					currentCell = stack.top;
					current = currentCell.node;
				}
				
			} while ( !(current == root && currentCell.traveledChilds == current.childs.length) );
		}
	}
	
	public void printDecrescentIteratively()
	{
		if (root != null)
		{
			Stack stack = new Stack();
			
			Cell currentCell = stack.stack(root, "");
			NoTrie current = currentCell.node;
			NoTrie child;
			
			do
			{
				if (!current.isLeaf)
				{
					if (currentCell.traveledChilds < current.childs.length)
					{
						child = current.childs[ current.childs.length - 1 - currentCell.traveledChilds++ ];
						
						if (child != null)
						{
							currentCell = stack.stack(child, currentCell.str + child.letter);
							current = child;
						}
					}
					
					else
					{
						stack.unstack();
						
						currentCell = stack.top;
						current = currentCell.node;
					}
				}
				
				else
				{
					System.out.println(currentCell.str);
					
					stack.unstack();
					
					currentCell = stack.top;
					current = currentCell.node;
				}
				
			} while ( !(current == root && currentCell.traveledChilds == current.childs.length) );
		}
	}
	
	public void printCrescentAndInvertedIteratively()
	{
		if (root != null)
		{
			Stack stack = new Stack();
			
			Cell currentCell = stack.stack(root, "");
			NoTrie current = currentCell.node;
			NoTrie child;
			
			do
			{
				if (!current.isLeaf)
				{
					if (currentCell.traveledChilds < current.childs.length)
					{
						child = current.childs[ currentCell.traveledChilds++ ];
						
						if (child != null)
						{
							currentCell = stack.stack(child, currentCell.str + child.letter);
							current = child;
						}
					}
					
					else
					{
						stack.unstack();
						
						currentCell = stack.top;
						current = currentCell.node;
					}
				}
				
				else
				{
					int length = currentCell.str.length();
					
					for (int i = length - 1; i > -1; i--)
					{
						System.out.print(currentCell.str.charAt(i));
					}
					
					System.out.println();
					
					stack.unstack();
					
					currentCell = stack.top;
					current = currentCell.node;
				}
				
			} while ( !(current == root && currentCell.traveledChilds == current.childs.length) );
		}
	}
	
	public static void main(String[] args)
	{
		Trie trie = new Trie();
		
		trie.insert("Max");
		trie.insert("Theldo");
		trie.insert("Almeida");
		trie.insert("Fabiano");
		trie.insert("Fatima");
		trie.insert("Alexei");
		trie.insert("Cida");
		trie.insert("Cidaaa");
		trie.insert("Ilo");
		trie.insert("Adriana");
		trie.insert("Carlos");
		trie.insert("Felipe");
		
		System.out.println("search(Fabiano) = " + trie.search("Fabiano"));
		System.out.println("search(Adriana) = " + trie.search("Adriana"));
		
		trie.printCrescentIteratively();
	}
}
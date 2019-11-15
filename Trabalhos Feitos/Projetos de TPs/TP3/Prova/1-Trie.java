class Cell
{
	Object element;
	Cell next;
	
	public Cell(Object element, Cell next)
	{
		this.element = element;
		this.next = next;
	}
}

class Stack
{
	Cell top;
	int size;
	
	public Stack()
	{
		top = null;
		size = 0;
	}
	
	public Cell stack(Object element)
	{
		size++;
		
		return top = new Cell(element, top);
	}
	
	public void unstack()
	{
		if (top != null)
		{
			size--;
			
			Cell newTop = top.next;
			
			top.next = null;
			
			top = newTop;
		}
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
    
	// Partindo da raiz e a incluindo, empilha todos os nos na procura da string
    private Stack findMaxCharsOf(String str)
    {
        Stack stack = new Stack(); // pilha dos nos percorridos
        
        if (str != null)
        {
            NoTrie father = root;
            int length = str.length();
            int i;
            
            for (i = 0; father != null && i < length; i++)
            {
				stack.stack(father);
                father = father.childs[ NoTrie.hash(str.charAt(i)) ];
            }
            
			if (father != null)
			{
				stack.stack(father);
			}
        }
        
        return stack;
    }
    
    public boolean search(String str)
    {
		boolean found = false;
		
		if (str != null)
		{
			Stack searchStack = findMaxCharsOf(str);
			
			NoTrie nodeOfLastCharFound = (NoTrie) searchStack.top.element;
			
			found = ( searchStack.size - 1 == str.length() && nodeOfLastCharFound.isLeaf  );
		}
        
        return found;
    }
    
    public void insert(String str)
    {
        if (str != null)
        {
			Stack searchStack = findMaxCharsOf(str);
			
			if (searchStack.size > 0)
			{
				NoTrie nodeOfLastCharFound = (NoTrie) searchStack.top.element;
				int numberOfFoundChars = searchStack.size - 1;
				int length = str.length();
				
				if (numberOfFoundChars < length && !nodeOfLastCharFound.isLeaf)
				{
					NoTrie father = nodeOfLastCharFound;
					char c;
					
					for (int i = numberOfFoundChars; i < length; i++)
					{
						c = str.charAt(i);
						
						father = father.childs[NoTrie.hash(c)] = new NoTrie(c, false);
					}
					
					father.isLeaf = true;
				}
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
			Stack nodesStack    = new Stack();
			Stack strsStack     = new Stack();
			Stack countersStack = new Stack();
			
			Cell nodeCell = nodesStack.stack(root);
			String str = strsStack.stack("");
			countersStack.stack(0);
			
			NoTrie node = root;
			NoTrie child;
			
			do
			{
				if (!node.isLeaf)
				{
					if (counter < node.childs.length)
					{
						child = node.childs[ ( (int) countersStack.top.element )++ ];
						
						if (child != null)
						{
							currentCell = stack.stack(child, currentCell.str + child.letter);
							node = child;
						}
					}
					
					else
					{
						stack.unstack();
						
						currentCell = stack.top;
						node = currentCell.node;
					}
				}
				
				else
				{
					System.out.println(currentCell.str);
					
					stack.unstack();
					
					currentCell = stack.top;
					node = currentCell.node;
				}
				
			} while ( !(node == root && currentCell.traveledChilds == node.childs.length) );
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
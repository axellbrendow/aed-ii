class NoTP
{
    int i, j, k;
    boolean isLeaf;
    NoTP prox[];
    
    private NoTP(int i, int j, int k, boolean isLeaf, NoTP prox[])
    {
        this.i = i;
        this.j = j;
        this.k = k;
        this.isLeaf = isLeaf;
        this.prox = prox;
    }
    
    public NoTP(int i, int j, int k, boolean isLeaf)
    {
        this(i, j, k, isLeaf, new NoTP[255]);
    }
}

class TP
{
    NoTP root;
    String strs[];
    
    public TP()
    {
        root = new NoTP(0, 0, 0, false);
        strs = new String[100];
    }
    
    private String getStr(NoTP no)
    {
        return strs[no.i].substring(no.j, no.k + 1);
    }
    
	private NoTP getChildWithFirstCharBeing(char firstChar, NoTP father)
	{
		NoTP wantedChild = null;
		
		if (father != null)
		{
			boolean charFound = false;
			NoTP childNode;
			
			for (int i = 0; !charFound && i < father.prox.length; i++) // percorre os filhos do no'
			{
				childNode = node.prox[i]; // armazena o filho do no'
				
				if (childNode != null)
				{
					charFound = ( getStr(childNode).charAt(0) == firstChar );
				}
			}
			
			if (charFound)
			{
				wantedChild = childNode;
			}
		}
		
		return wantedChild;
	}
	
    public boolean pesquisar(String s)
    {
        NoTP node = root;
		boolean isOk = ( root != null );
		int length = s.length();
		int remainingLength = length;
		String nodeStr;
		int nodeStrLength;
        int i = 0;
        
        // "i" dira' quantos caracteres ja' foram encontrados
        while (isOk && i < length)
        {
            node = getChildWithFirstCharBeing(s.charAt(i), node); // busca o no' filho que tenha o caractere atual
			
			if ( isOk = ( node != null ) ) // checa se esse no' existe
			{
				nodeStr = getStr(node); // obtem a string que o no' representa
				nodeStrLength = nodeStr.length(); // obtem o tamanho dela
				
				// com base no tamanho acima, faz um corte na string de pesquisa, a partir do caractere atual,
				// do mesmo comprimento, e checa se essas duas strings sao iguais
				if ( isOk = ( remainingLength >= nodeStrLength && s.substring( i, i + nodeStrLength ).equals(nodeStr) ) )
				{
					i += nodeStrLength; // aumenta a quantidade de caracteres encontrados
					remainingLength = length - i;
				}
			}
        }
        
        return isOk && node.isLeaf;
    }
	
	public boolean pesquisarRecursivo(String s, int i, NoTP node)
	{
		boolean answer = false;
		
		if (node != null)
		{
			if (i == s.length()) // todos caracteres encontrados
			{
				answer = node.isLeaf;
			}
			
			else
			{
				node = getChildWithFirstCharBeing(s.charAt(i), node); // busca o no' filho que tenha o caractere atual
				
				if (node != null) // checa se esse no' existe
				{
					String nodeStr = getStr(node); // obtem a string que o no' representa
					int nodeStrLength = nodeStr.length(); // obtem o tamanho dela
					int remainingLength = s.length() - i;
					
					// com base no tamanho acima, faz um corte na string de pesquisa, a partir do caractere atual,
					// do mesmo comprimento, e checa se essas duas strings sao iguais
					if ( remainingLength >= nodeStrLength && s.substring(i, i + nodeStrLength).equals(nodeStr) )
					{
						answer = pesquisarRecursivo(s, i + nodeStrLength, node);
					}
				}
			}
		}
		
		return answer;
	}
	
	public boolean pesquisarRecursivo(String s)
	{
		return pesquisarRecursivo(s, 0, root);
	}
}
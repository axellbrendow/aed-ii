/*

PUC Minas - Ciencia da Computacao     Nome: Dictionary

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 26/08/2018

*/

class Pair
{
    Object key;
    Object value;
    
    public Pair(Object key, Object value)
    {
        this.key = key;
        this.value = value;
    }
    
    public Object getKey()
    {
        return key;
    }
    
    public Object getValue()
    {
        return value;
    }
}

class Dictionary
{
    Lista pairs;
    
    public Dictionary()
    {
        this( new Lista() );
    }
    
    public Dictionary(Pair initialPair)
    {
        if (initialPair == null)
        {
            //AxellIO.println("[Dictionary]: Parametro initialPair nulo no construtor");
        }
        
        else
        {
            pairs = new Lista();
            
            pairs.add(initialPair);
        }
    }
    
    public Dictionary(Object key, Object value)
    {
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo no construtor");
        }
        
        else if (value == null)
        {
            //AxellIO.println("[Dictionary]: Parametro value nulo no construtor");
        }
        
        else
        {
            pairs = new Lista();
            
            pairs.add(new Pair(key, value));
        }
    }
    
    public Dictionary(Lista pairs)
    {
        if (pairs == null)
        {
            //AxellIO.println("[Dictionary]: Parametro pairs nulo no construtor");
        }
        
        else
        {
            this.pairs = pairs;
        }
    }
    
    public int size()
    {
        return pairs.size();
    }
    
    public int findPairByKey(Object key)
    {
        int pairIndex = -1;
        
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo na funcao findPairByKey()");
        }
        
        else
        {
            int dictionarySize = size(); // obtem o tamanho do dicionario
            boolean found = false; // guardara' o status de chave encontrada
            int i;
            
            for (i = 0; i < dictionarySize && !found; i++)
            {
                found = key.equals( getPair(i).getKey() );
            }
            
            pairIndex = i - 1;
        }
        
        return pairIndex;
    }
    
    public Pair getPair(int pairIndex)
    {
        Pair pair;
        
        if (AxellIO.limitOverflow(pairIndex, size()) != 0)
        {
            //AxellIO.println("[Dictionary]: Parametro pairIndex invalido na funcao getPair()");
            
            pair = null;
        }
        
        else
        {
            pair = (Pair) pairs.get(pairIndex);
        }
        
        return pair;
    }
    
    public Pair getPair(Object key)
    {
        Pair pair;
        
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo na funcao getPair()");
            
            pair = null;
        }
        
        else
        {
            pair = getPair( findPairByKey(key) );
        }
        
        return pair;
    }
    
    public Object getPairKey(int pairIndex)
    {
        Object key;
        Pair pair = getPair(pairIndex);
        
        if (pair == null)
        {
            //AxellIO.println("[Dictionary]: Parametro pairIndex invalido na funcao getPairKey()");
            
            key = null;
        }
        
        else
        {
            key = pair.getKey();
        }
        
        return key;
    }
    
    public Object getPairValue(int pairIndex)
    {
        Object value;
        Pair pair = getPair(pairIndex);
        
        if (pair == null)
        {
            //AxellIO.println("[Dictionary]: Parametro pairIndex invalido na funcao getPairValue()");
            
            value = null;
        }
        
        else
        {
            value = pair.getValue();
        }
        
        return value;
    }
    
    public void addPair(Pair pair)
    {
        if (pair == null)
        {
            //AxellIO.println("[Dictionary]: Parametro pair nulo na funcao add()");
        }
        
        else
        {
            pairs.add(pair);
        }
    }
    
    public void add(Object key, Object value)
    {
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo na funcao add()");
        }
        
        else if (value == null)
        {
            //AxellIO.println("[Dictionary]: Parametro value nulo na funcao add()");
        }
        
        else
        {
            addPair( new Pair(key, value) );
        }
    }
    
    public void remove(int index)
    {
        pairs.remove(index);
    }
    
    public void remove(Object key)
    {
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo na funcao remove()");
        }
        
        else
        {
            int pairIndex = findPairByKey(key);
            
            if (pairIndex != -1)
            {
                remove(pairIndex);
            }
        }
    }
    
    public Object get(Object key)
    {
        Object value = null;
        
        if (key == null)
        {
            //AxellIO.println("[Dictionary]: Parametro key nulo na funcao get()");
        }
        
        else
        {
            Pair pair = getPair( findPairByKey(key) );
            
            if (pair != null)
            {
                value = pair.getValue();
            }
        }
        
        return value;
    }
    
    public boolean exists(Object key)
    {
        return findPairByKey(key) != -1;
    }
}
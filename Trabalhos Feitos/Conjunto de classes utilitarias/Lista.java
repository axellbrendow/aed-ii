/*

PUC Minas - Ciencia da Computacao     Nome: Lista

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  2.5                          Data: 03/09/2018

*/

class Lista extends ArrayClass
{
    // ----------------------- ATRIBUTOS DA CLASSE
    
    private int size;
    boolean isMoving;
    
    // ----------------------- CONSTRUTORES DA CLASSE
    
    public Lista()
    {
        this(10); // 10 = tamanho padrao da lista
    }
    
    public Lista(int length)
    {
        super(length);
        
        size = 0;
    }
    
    public Lista(Object[] list)
    {
        super(list);
        
        updateSize();
    }
    
    // ----------------------- METODOS DA CLASSE
    
    public boolean getIsMoving()
    {
        return isMoving;
    }
    
    public void setIsMoving(boolean isMoving)
    {
        this.isMoving = isMoving;
    }
    
    public boolean isMoving()
    {
        return getIsMoving();
    }
    
    public int size()
    {
        return size;
    }
    
    private void setSize(int size)
    {
        if (size < 0 || size > length())
        {
            //AxellIO.println("[Lista]: Parametro size < 0 ou maior que o tamanho do arranjo da lista (size = " + size + ") - funcao setSize(int)");
        }
        
        else
        {
            this.size = size;
        }
    }
    
    private void sumToSize(int quantity)
    {
        setSize(size() + quantity);
    }
    
    private void incrementSize()
    {
        sumToSize(1);
    }
    
    private void decrementSize()
    {
        sumToSize(-1);
    }
    
    private void updateSize()
    {
        int size = length(); // presume que a quantidade de elementos seja o tamanho maximo
        
        for (int i = 0; i < size; i++) // percorre a lista
        {
            if (get(i) == null) // checa se cada um dos elementos e' nulo
            {
                // ao encontrar um elemento nulo, a quantidade de elementos sera' o numero sucessor ao indice dele
                size = i + 1;
            }
        }
        
        setSize(size); // atualiza a quantidade de elementos
    }
    
    @Override
    public Object get(int index)
    {
        Object result;
        
        if (AxellIO.limitOverflow(index, size()) != 0)
        {
            //AxellIO.println("[Lista]: Parametro index invalido. - funcao get(int)");
            
            result = null;
        }
        
        else
        {
            result = array[index];
        }
        
        return result;
    }
    
    @Override
    public void set(Object element, int index)
    {
        if (element == null)
        {
            //AxellIO.println("[Lista]: Parametro element nulo. - funcao set(int, Object)");
        }
        
        else if (!isMoving() && AxellIO.limitOverflow(index, size()) != 0)
        {
            AxellIO.println("[Lista]: Parametro index invalido. (index = " + index + ") - funcao set(int, Object)");
        }
        
        else
        {
            array[index] = element;
        }
    }
    
    /**
     * Remove determinado elemento da lista
     * 
     * @param index indice do elemento
     * 
     * @return elemento removido
     */
    
    public Object remove(int index)
    {
        Object removedElement;
        int size = size();
        
        if (index >= size || index < 0)
        {
            //AxellIO.println("[Lista]: Parametro index invalido. - funcao remove(int)");
            removedElement = null;
        }
        
        else
        {
            removedElement = get(index); // guarda o elemento que esta' no indice
            
            if (index != size - 1) // checa se o indice de remocao nao e' o ultimo da lista
            {
                setIsMoving(true);
                moveElementsToLeftFrom(index + 1, size - index + 1);
                setIsMoving(false);
            }
            
            decrementSize();
        }
        
        return removedElement;
    }
    
    public Object removeOnStart()
    {
        return remove(0);
    }
    
    public Object removeOnEnd()
    {
        return remove(size() - 1);
    }
    
    /**
     * Adiciona um novo elemento 'a lista no indice informado. Se a lista nao
     * tiver espaco, o seu tamanho e' dobrado e entao o elemento
     * e' adicionado.
     * 
     * @param element elemento a ser adicionado
     */
    
    public void add(Object element, int index)
    {
        if (element == null)
        {
            //AxellIO.println("[Lista]: Elemento nulo. - funcao add(int, int)");
        }
        
        else
        {
            int size = size();

            if (size == length())
            {
                Object[] arrayCopyFromLeft = copyArray(0, index);
                Object[] arrayCopyFromRight = copyArray(index);

                setArray( new Object[size * 2] );

                copyElementsToClassArray(0, arrayCopyFromLeft);
                copyElementsToClassArray(index + 1, arrayCopyFromRight);
            }

            else
            {
                setIsMoving(true);
                moveElementsToRightFrom(size - 1, size - index);
                setIsMoving(false);
            }

            incrementSize();
            
            set(element, index);
        }
    }
    
    public void addOnStart(Object element)
    {
        add(element, 0);
    }
    
    public void addOnEnd(Object element)
    {
        add(element, size());
    }
    
    public void add(Object element)
    {
        addOnEnd(element);
    }
    
    /**
     * Mostra certa quantidade de elementos da lista a partir de um indice inicial
     * separando-os por uma string especifica
     * 
     * @param start indice do primeiro elemento a se mostrar
     * @param numberOfElements quantidade de elementos a se mostrar
     * @param delimitingStr string que deve ser usada para delimitar/separar os elementos
     */
    
    public void printList(int start, int numberOfElements, String delimitingStr)
    {
       if (AxellIO.limitOverflow(start, numberOfElements, size()) != 0)
       {
           //AxellIO.println("[Lista]: Parametros start e numberOfElements invalidos. (start = " + start + " | numberOfElements = " + numberOfElements + ") - funcao printList(int, int)");
       }
       
       else
       {
           print(start, numberOfElements, delimitingStr);
       }
    }
    
    /**
     * Mostra certa quantidade de elementos da lista a partir de um indice inicial
     * 
     * @param start indice do primeiro elemento a se mostrar
     * @param numberOfElements quantidade de elementos a se mostrar
     */
    
    public void printList(int start, int numberOfElements)
    {
       printList(start, numberOfElements, AxellIO.LINE_SEPARATOR);
    }
    
    /**
     * Mostra todos os elementos do arranjo da classe separando-os por uma string especifica
     * 
     * @param delimitingStr string que deve ser usada para delimitar/separar os elementos
     */
    
    public void printList(String delimitingStr)
    {
        printList(0, size(), delimitingStr);
    }
    
    /**
     * Mostra todos os elementos do arranjo da classe
     */
    
    public void printList()
    {
        printList(AxellIO.LINE_SEPARATOR);
    }
}
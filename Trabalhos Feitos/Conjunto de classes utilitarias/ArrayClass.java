/*

PUC Minas - Ciencia da Computacao     Nome: ArrayClass

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  2.0                          Data: 03/09/2018

*/

class ArrayClass
{
    protected Object array[];
    
    public ArrayClass(int length)
    {
        if (length < 0)
        {
            //AxellIO.println("[ArrayClass]: Tamanho do arranjo < 0 - construtor ArrayClass(int)");
        }
        
        else
        {
            array = new Object[length];
        }
    }
    
    public ArrayClass(Object[] array)
    {
        if (array == null)
        {
            //AxellIO.println("[ArrayClass]: Arranjo nulo - construtor ArrayClass(Object[])");
        }
        
        else
        {
            this.array = array;
        }
    }
    
    /**
     * Obtem o arranjo da classe
     * 
     * @return arranjo da classe
     */
    
    protected Object[] getArray()
    {
        return array;
    }
    
    /**
     * Define o arranjo da classe
     */
    
    protected void setArray(Object[] array)
    {
        if (array == null)
        {
            //AxellIO.println("[ArrayClass]: Arranjo nulo - set(Object[])");
        }
        
        else
        {
            this.array = array;
        }
    }
    
    /**
     * Obtem o tamanho do arranjo da classe
     * 
     * @return tamanho do arranjo da classe
     */
    
    public int length()
    {
        return getArray().length;
    }
    
    /**
     * Tenta acessar um elemento do arranjo da classe. Em caso de sucesso,
     * retorna o elemento. Em outro caso, retorna null.
     * 
     * @param index indice do elemento no arranjo
     * 
     * @return elemento no indice informado ou null se o elemento nao existir
     */
    
    public Object get(int index)
    {
        Object result;
        
        if (AxellIO.limitOverflow(index, getArray()) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametro index invalido - funcao get(int)");
            
            result = null;
        }
        
        else
        {
            result = array[index];
        }
        
        return result;
    }
    
    /**
     * Tenta acessar um indice do arranjo e colocar um objeto nesse lugar.
     * 
     * @param element elemento a ser colocado no indice especificado
     * @param index indice da posicao onde deseja-se colocar o elemento
     */
    
    public void set(Object element, int index)
    {
        if (AxellIO.limitOverflow(index, getArray()) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametro index invalido - funcao set(Object, int)");
        }
        
        else
        {
            array[index] = element;
        }
    }
    
    /**
     * A partir de um indice inicial do arranjo, move certa quantidade dos elementos
     * para a esquerda deslocando-os uma certa quantidade de posicoes.
     * 
     * @param initialIndex indice inicial
     * @param numberOfElements quantidade de elementos a serem deslocados
     * @param offset quantas posicoes os elementos devem ser deslocados
     */
    
    protected void moveElementsToLeftFrom(int initialIndex, int numberOfElements, int offset)
    {
        int length = length();
        
        if (AxellIO.limitOverflow(initialIndex - 1, offset, length, true) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametros initialIndex e offset invalidos. (initialIndex = " + initialIndex + " | offset = " + offset + ") - funcao moveElementsToLeftFrom(int, int, int)");
        }
        
        else if (AxellIO.limitOverflow(initialIndex, numberOfElements, length) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametros initialIndex e numberOfElements invalidos. (initialIndex = " + initialIndex + " | numberOfElements = " + numberOfElements + ") - funcao moveElementsToLeftFrom(int, int, int)");
        }
        
        else
        {
            for (int i = initialIndex; (i - initialIndex) < numberOfElements && i < length; i++)
            {
                set(get(i), i - offset); // coloca o elemento da posicao "i", "offset" posicoes para tras
            }
        }
    }
    
    /**
     * A partir de um indice inicial do arranjo, move certa quantidade de elementos
     * para a esquerda deslocando-os uma posicao.
     * 
     * @param initialIndex indice inicial
     * @param numberOfElements quantidade de elementos a serem deslocados
     */
    
    protected void moveElementsToLeftFrom(int initialIndex, int numberOfElements)
    {
        moveElementsToLeftFrom(initialIndex, numberOfElements, 1);
    }
    
    /**
     * A partir de um indice inicial do arranjo, move todos os elementos
     * para a esquerda deslocando-os uma posicao.
     * 
     * @param initialIndex indice inicial
     */
    
    protected void moveElementsToLeftFrom(int initialIndex)
    {
        moveElementsToLeftFrom(initialIndex, length() - initialIndex);
    }
    
    /**
     * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
     * certa quantidade dos elementos para a direita deslocando-os uma certa
     * quantidade de posicoes.
     * 
     * @param initialIndex indice inicial
     * @param numberOfElements quantidade de elementos a serem deslocados
     * @param offset quantas posicoes os elementos devem ser deslocados
     */
    
    protected void moveElementsToRightFrom(int initialIndex, int numberOfElements, int offset)
    {
        int length = length();
        
        if (AxellIO.limitOverflow(initialIndex + 1, offset, length) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametros initialIndex e offset invalidos. (initialIndex = " + initialIndex + " | offset = " + offset + ") - funcao moveElementsToRightFrom(int, int, int)");
        }
        
        else if (AxellIO.limitOverflow(initialIndex, numberOfElements, length, true) != 0)
        {
            //AxellIO.println("[ArrayClass]: Parametros initialIndex e numberOfElements invalidos. (initialIndex = " + initialIndex + " | numberOfElements = " + numberOfElements + ") - funcao moveElementsToRightFrom(int, int, int)");
        }
        
        else
        {
            for (int i = initialIndex; (initialIndex - i) < numberOfElements && i >= 0; i--)
            {
                set(get(i), i + offset); // coloca o elemento da posicao "i", "offset" posicoes para frente
            }
        }
    }
    
    /**
     * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
     * certa quantidade dos elementos para a direita deslocando-os uma posicao.
     * 
     * @param initialIndex indice inicial
     * @param numberOfElements quantidade de elementos a serem deslocados
     */
    
    protected void moveElementsToRightFrom(int initialIndex, int numberOfElements)
    {
        moveElementsToRightFrom(initialIndex, numberOfElements, 1);
    }
    
    /**
     * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
     * certa quantidade dos elementos para a direita deslocando-os uma posicao.
     * 
     * @param initialIndex indice inicial
     */
    
    protected void moveElementsToRightFrom(int initialIndex)
    {
        moveElementsToRightFrom(initialIndex, initialIndex + 1);
    }
    
    /**
     * Procurado o indice do elemento informado
     * 
     * @param element elemento a ser procurado
     * 
     * @return o indice do elemento se ele existe, caso contrario, -1
     */
    
    public int indexOf(Object element)
    {
        int index = -1, length = length();
        boolean found = false;
        
        for (int i = 0; i < length && !found; i++)
        {
            found = element.equals(get(i));
        }
        
        return index;
    }
    
    /**
     * Percorre o arranjo colocando o objeto especificado em todas as posicoes
     * 
     * @param element elemento com o qual se deseja preencher o arranjo
     */
    
    public void setAllElementsTo(Object element)
    {
        int length = length();
        
        for (int i = 0; i < length; i++)
        {
            set(element, i);
        }
    }
    
    /**
     * Limpar o arranjo da classe igualando todos os elementos a null
     */
    
    public void clearArray()
    {
        setAllElementsTo(null);
    }
    
    /**
     * Funcao que obtem a representacao de certa quantidade de elementos
     * do arranjo da classe na forma de texto
     * 
     * @param start indice do primeiro elemento a se pegar
     * @param numberOfElements quantidade de elementos a se pegar
     * @param delimitingStr string que deve ser usada para delimitar/separar os elementos
     * 
     * @return elementos concatenados entre espacos na forma de String
     */
    
    private String getArrayText(int start, int numberOfElements, String delimitingStr)
    {
        if (AxellIO.limitOverflow(start, numberOfElements, getArray()) != 0 || delimitingStr == null)
        {
            //AxellIO.println("[ArrayClass]: "Nao foi possivel obter a representacao em texto do arranjo da classe". (start = " + start + " | numberOfElements = " + numberOfElements + " | delimitingStr = " + delimitingStr + ") - funcao getArrayText(int, int, String)");
            return null;
        }
        
        String arrayText = "";
        
        for (int i = 0; i < numberOfElements; i++)
        {
            arrayText += get(start + i) + delimitingStr;
        }
        
        return arrayText;
    }
    
    /**
     * Funcao que obtem a representacao de certa quantidade de elementos
     * do arranjo da classe na forma de texto
     * 
     * @param start indice do primeiro elemento a se pegar
     * @param numberOfElements quantidade de elementos a se pegar
     * 
     * @return elementos concatenados entre espacos na forma de String
     */
    
    private String getArrayText(int start, int numberOfElements)
    {
        return getArrayText(start, numberOfElements, AxellIO.LINE_SEPARATOR);
    }
    
    /**
     * Mostra certa quantidade de elementos do arranjo da classe a partir
     * de um indice inicial separando-os por uma string especifica
     * 
     * @param start indice do primeiro elemento a se mostrar
     * @param numberOfElements quantidade de elementos a se mostrar
     * @param delimitingStr string que deve ser usada para delimitar/separar os elementos
     */
    
    public void print(int start, int numberOfElements, String delimitingStr)
    {
       if (AxellIO.limitOverflow(start, numberOfElements, length()) != 0)
       {
           //AxellIO.println("[ArrayClass]: Parametros start e numberOfElements invalidos. (start = " + start + " | numberOfElements = " + numberOfElements + ") - funcao print(int, int, String)");
       }
       
       else
       {
           String arrayText = getArrayText(start, numberOfElements, delimitingStr);
           
           if (arrayText == null) return;
           
           AxellIO.println(arrayText);
       }
    }
    
    /**
     * Mostra certa quantidade de elementos do arranjo da classe
     * a partir de um indice inicial
     * 
     * @param start indice do primeiro elemento a se mostrar
     * @param numberOfElements quantidade de elementos a se mostrar
     */
    
    public void print(int start, int numberOfElements)
    {
        print(start, numberOfElements, AxellIO.LINE_SEPARATOR);
    }
    
    /**
     * Mostra todos os elementos do arranjo da classe
     * 
     * @param delimitingStr string que deve ser usada para delimitar/separar os elementos
     */
    
    public void print(String delimitingStr)
    {
        print(0, length(), delimitingStr);
    }
    
    /**
     * Mostra todos os elementos do arranjo da classe
     */
    
    public void print()
    {
        AxellIO.println("" + this);
    }
    
    /**
     * Copia todos os elementos de um arranjo e os coloca
     * no arranjo da classe a partir de um indice inicial
     * 
     * @param start indice de comeco da gravacao
     * @param elements arranjo com os elementos a serem copiados
     * 
     * @return true se os parametros anteriores nao levarem
     * a um erro, caso contrario, false.
     */
    
    protected boolean copyElementsToClassArray(int start, Object[] elements)
    {
        // cuida de todos os possiveis casos de dados incorretos.
        if (elements == null || AxellIO.limitOverflow(start, elements.length, getArray()) != 0)
        {
            //AxellIO.println("[ArrayClass]: Nao foi possivel copiar os elementos para o arranjo da classe. - funcao copyElementsToClassArray(int, Object[])");
            return false;
        }
        
        for (int i = 0; i < elements.length; i++) // percorre a quantidade de elementos
        {
            // associa cada objeto de "elements" 'a "array" a partir da posicao "start"
            set(elements[i], start + i);
        }
        
        return true;
    }
    
    /**
     * Obtem um arranjo de objetos invertido em relacao ao arranjo da classe
     * 
     * @return arranjo de objetos invertido
     */
    
    public Object[] invertArray()
    {
        if (array == null) return null;
        
        int length = length();
        
        Object[] invertedArray = new Object[length];
        
        for (int i = 0; i < length; i++)
        {
            invertedArray[i] = get(length - 1 - i);
        }
        
        return invertedArray;
    }
    
    /**
     * Copia certa quantidade de elementos do arranjo da classe a partir
     * de um indice inicial
     *
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * 
     * @return arranjo de objetos com os objetos do arranjo da classe
     */
    
    public Object[] copyArray(int start, int numberOfElements)
    {
        if (AxellIO.limitOverflow(start, numberOfElements, getArray()) != 0) return null;
        
        Object[] copyOfClassArray = new Object[numberOfElements];
        
        for (int i = 0; i < numberOfElements; i++)
        {
            copyOfClassArray[i] = get(start + i);
        }
        
        return copyOfClassArray;
    }
    
    /**
     * Copia todos os elementos do arranjo da classe
     * a partir do indice informado.
     *
     * @param start indice inicial
     * 
     * @return arranjo de objetos com os objetos do arranjo da classe
     */
    
    public Object[] copyArray(int start)
    {
        return copyArray(start, length() - start);
    }
    
    /**
     * Cria uma copia do arranjo da classe
     * 
     * @return copia do arranjo da classe
     */
    
    public Object[] copyArray()
    {
        return copyArray(0);
    }
    
    @Override
    public ArrayClass clone() throws CloneNotSupportedException
    {
        return new ArrayClass(copyArray());
    }
    
    @Override
    public String toString()
    {
        return getArrayText(0, length());
    }
    
    public boolean equals(ArrayClass arrayClass)
    {
        if (arrayClass == null)
        {
             //AxellIO.println("[ArrayClass]: Objeto ArrayClass nulo. - funcao equals(ArrayClass)");
            return false;
        }
        
        else
        {
            return toString().equals(arrayClass.toString());
        }
    }
}
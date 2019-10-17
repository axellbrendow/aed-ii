/*

PUC Minas - Ciencia da Computacao     Nome: MyString

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 19/04/2018

*/

/**
 * Interface para tratamento de erros
 */

interface ErrorInterface
{
    boolean hasError(); /** checa existencia de erro */
    int getErrorCode(); /** obtem o codigo de erro */
    void setErrorCode(int newErrorCode); /** muda o codigo de erro */
    String getErrorMessage(); /** obtem a mensagem de erro */
    void showError(); /** mostra a mensagem de erro */
}

/**
 * Classe para tratamento de erros
 */

class ErrorClass implements ErrorInterface
{
    protected int errorCode; // armazenamento do codigo de erro da classe
    
    public ErrorClass()
    {
        errorCode = 0;
    }
    
    @Override
    public String toString()
    {
        return getErrorMessage();
    }
    
    /**
     * Codigo de erro 0 representa a nao existencia de erro
     * @return true se a classe tiver um erro, caso contrario, false
     */
    
    @Override
    public boolean hasError()
    {
        return errorCode == 0;
    }
    
    /**
     * @return codigo de erro da classe
     */
    
    @Override
    public int getErrorCode()
    {
        return errorCode;
    }
    
    /**
     * Metodo usado para mudar o codigo de erro da classe
     * @param newErrorCode novo codigo de erro
     */
    
    @Override
    public void setErrorCode(int newErrorCode)
    {
        errorCode = newErrorCode;
    }
    
    /**
     * @return Mensagem relativa ao codigo de erro da classe
     */
    
    @Override
    public String getErrorMessage()
    {
        String message;
        
        switch (errorCode)
        {
            case 0:
                message = "Nao ha' erro";
                break;
                
            default:
                message = "Nao foi possivel encontrar uma mensagem de erro";
                break;
        }
        
        return message;
    }
    
    /**
     * Mostra a mensagem de erro do respectivo codigo
     */
    
    @Override
    public void showError()
    {
        AxellIO.println(getErrorMessage());
    }
}

class MyString extends ErrorClass
{
    // ----------------------- TRATAMENTO DE ERROS
    
    /**
     * Codigos de erro e suas mensagens:
     * 0 - [MyString]: Nao ha' erro
     * 1 - [MyString]: Tentativa de acesso a um indice inexistente na cadeia
     * 2 - [MyString]: Tentativa de armazenamento de cadeia invalida no construtor
     * 3 - [MyString]: Tentativa de armazenamento de cadeia invalida no metodo set
     * 4 - [MyString]: Arranjo de caracteres invalido na funcao arrayCharToString
     * 5 - [MyString]: Objeto MyString invalido na funcao equals
     * 6 - [MyString]: Objeto MyString invalido na funcao compareTo
     * 7 - [MyString]: Objeto MyString invalido na funcao append
     * 8 - [MyString]: Objeto MyString invalido na funcao prepend
     * 
     * Se o codigo de erro nao for nenhum dos anteriores a mensagem sera':
     * [MyString]: Nao foi possivel encontrar uma mensagem de erro
     * 
     * @return mensagem de erro de um codigo de erro
     */
    
    @Override
    public String getErrorMessage()
    {
        String message = "[MyString]: ";
        
        switch (errorCode)
        {
            case 0:
                message += "Nao ha' erro";
                break;
                
            case 1:
                message += "Tentativa de acesso a um indice inexistente na cadeia";
                break;
                
            case 2:
                message += "Tentativa de armazenamento de cadeia invalida no construtor";
                break;
                
            case 3:
                message += "Tentativa de armazenamento de cadeia invalida no metodo set";
                break;
                
            case 4:
                message += "Arranjo de caracteres invalido na funcao arrayCharToString";
                break;
                
            case 5:
                message += "Objeto MyString invalido na funcao equals";
                break;
                
            case 6:
                message += "Objeto MyString invalido na funcao compareTo";
                break;
                
            case 7:
                message += "Objeto MyString invalido na funcao append";
                break;
                
            case 8:
                message += "Objeto MyString invalido na funcao prepend";
                break;
                
            default:
                message += "Nao foi possivel encontrar uma mensagem de erro";
        }
        
        return message;
    }
    
    // ----------------------- CONSTANTES DA CLASSE
    
    public static final MyString EMPTY = new MyString();
    
    // ----------------------- ATRIBUTOS DA CLASSE
    
    private String str = ""; // cadeia de caracteres da classe
    
    // ----------------------- CONSTRUTORES
    
    public MyString() {}
    
    /**
     * Inicia a classe ja' armazenando um valor para a cadeia de caracteres
     * @param start valor inicial da cadeia de caracteres
     */
    
    public MyString(String start)
    {
        if (start == null)
        {
            setErrorCode(2);
            return;
        }
        
        if (getErrorCode() == 2) setErrorCode(0); // se havia erro o remove
        
        str = start; // associa a cadeia passada 'a cadeia da classe
    }
    
    // ----------------------- METODOS E FUNCOES
    
    /**
     * @return cadeia de caracteres da classe
     */
    
    @Override
    public String toString()
    {
        return str; // retorna a cadeia da classe
    }
    
    /**
     * @return nova instancia de MyString com a mesma cadeia de caracteres dessa instancia
     */
    
    @Override
    public MyString clone()
    {
        return new MyString(str); // retorna uma nova MyString com a mesma cadeia
    }
    
    /**
     * @return true se a cadeia de caracteres da classe estiver vazia, caso contrario, false
     */
    
    public boolean isEmpty()
    {
        return this.equals(EMPTY); // compara esse objeto ao objeto (new MyString())
    }
    
    /**
     * Checa se esse objeto e' igual a outro MyString
     * @param otherMyStr objeto a se comparar
     * @return false se o objeto informado for nulo ou nao for igual a esse, caso contrario, true
     */
    
    public boolean equals(MyString otherMyStr)
    {
        if (otherMyStr == null)
        {
            setErrorCode(5);
            return false;
        }
        
        if (getErrorCode() == 5) setErrorCode(0); // se havia erro o remove
        
        int length = length(); // obtem o tamanho da cadeia da classe
        
        // ja' checa inicialmente se as strings tem o mesmo tamanho
        boolean areEqual = length == otherMyStr.length();
        
        for(int i = 0; i < length && areEqual; i++)
        {
            areEqual = getCharAt(i) == otherMyStr.getCharAt(i);
        }
        
        return areEqual;
    }
    
    /**
     * Checa se a string desse objeto e' igual 'a string passada
     * @param otherStr string a comparar
     * @return true se forem iguais, caso contrario, false
     */
    
    public boolean equals(String otherStr)
    {
        if (otherStr == null)
        {
            setErrorCode(5);
            return false;
        }
        
        if (getErrorCode() == 5) setErrorCode(0); // se havia erro o remove
        
        return equals(new MyString(otherStr));
    }
    
    /**
     * @return tamanho da cadeia de caracteres da classe
     */
    
    public int length()
    {
        return str.length();
    }
    
    /**
     * @return cadeia de caracteres da classe
     */
    
    public String get()
    {
        return toString();
    }
    
    /**
     * Substitui a cadeia de caracteres da classe por uma determinada String 
     * @param newStr nova cadeia de caracteres da classe
     */
    
    public void set(String newStr)
    {
        if (newStr == null)
        {
            setErrorCode(3); // codigo de erro 3: tentativa de armazenamento de cadeia invalida
            return;
        }
        
        if (getErrorCode() == 3) setErrorCode(0); // se havia erro o remove
        
        str = newStr;
    }
    
    /**
     * Obtem um caractere da cadeia da classe pelo seu indice
     * @param index indice do caractere
     * @return caractere no indice informado
     */
    
    public char getCharAt(int index)
    {
        if (limitWillBreak(index, 1, str.toCharArray()))
        {
            setErrorCode(1);
            return '_';
        }
        
        if (getErrorCode() == 1) setErrorCode(0); // se havia erro o remove
        
        return str.charAt(index);
    }
    
    /**
     * Obtem o ultimo caractere da cadeia da classe
     * @return o ultimo caractere da cadeia da classe
     */
    
    public char getLastChar()
    {
        int length = length();
        char lastChar;
        
        if (length == 0)
        {
            lastChar = '\0';
        }
        
        else
        {
            lastChar = getCharAt(length - 1);
        }
        
        return lastChar;
    }
    
    /**
     * Retorna um novo objeto MyString com o ultimo caractere da cadeia da classe removido
     * @return um novo objeto MyString com o ultimo caractere da cadeia da classe removido
     */
    
    public MyString removeLastChar()
    {
        int length = length();
        MyString myStrWithoutLastChar;
        
        if (length == 0)
        {
            myStrWithoutLastChar = new MyString();
        }
        
        else
        {
            myStrWithoutLastChar = substring(0, length - 1);
        }
        
        return myStrWithoutLastChar;
    }
    
    /**
     * Faz a conversao de arranjo de caracteres para String
     * @param charArray arranjo de caracteres a ser convertido
     * @return String com os mesmos caracteres do arranjo na mesma ordem
     */
    
    private String charArrayToString(char[] charArray)
    {
        if (charArray == null)
        {
            setErrorCode(4);
            return null;
        }
        
        if (getErrorCode() == 4) setErrorCode(0); // se havia erro o remove
        
        String str = ""; // inicia uma nova String vazia
        
        for (char c : charArray) // percorre cada um dos caracteres do arranjo
        {
            str += c; // concatena-os 'a String
        }
        
        return str;
    }
    
    /**
     * Subtitui determinado caractere da String por outro
     * @param index indice do caractere a se substituir
     * @param character caractere a ser colocado no lugar
     */
    
    public void setCharAt(int index, char character)
    {
        char[] charArray = str.toCharArray(); // cria um arranjo de caracteres da String
        
        if (limitWillBreak(index, 1, charArray))
        {
            setErrorCode(1);
            return;
        }
        
        if (getErrorCode() == 1) setErrorCode(0); // se havia erro o remove
        
        // substitui o caractere no indice informado pelo caractere informado
        charArray[index] = character;
        
        // associa a cadeia da classe ao novo arranjo modificado convertido para String
        str = charArrayToString(charArray);
    }
    
    /**
     * @return o primeiro caractere da cadeia da classe
     */
    
    public String getHead()
    {
        if (limitWillBreak(0, 1, str.toCharArray()))
        {
            setErrorCode(1);
            return "";
        }
        
        if (getErrorCode() == 1) setErrorCode(0); // se havia erro o remove
        
        return "" + str.charAt(0);
    }
    
    /**
     * @return String com o primeiro caractere da cadeia da classe retirado
     */
    
    public String getTail()
    {
        if (str.length() == 0) return "";
        
        char[] charArray = str.toCharArray();
        char[] tail = new char[charArray.length - 1];
        
        for (int i = 1; i < charArray.length; i++)
        {
            tail[i - 1] = charArray[i];
        }
        
        return charArrayToString(tail);
    }
    
    /**
     * @return cadeia de caracteres da classe porem sem espacos em branco
     */
    
    public MyString trim()
    {
        // obtem a quantidade de espacos em branco da cadeia da classe
        int numberOfSpaces = AxellIO.getCharTimes(' ', str);
        
        // se nao tiver pelo menos 1 espaco em branco retorna uma "String" vazia
        if (numberOfSpaces < 1) return new MyString();
        
        // cria um novo arranjo de caracteres com o tamanho adequado
        char[] newStr = new char[length() - numberOfSpaces];
        
        int counter = 0; // salvara' o menor indice de uma posicao vazia de "newStr"
        char c;
        
        for (int i = 0; i < length(); i++) // percorre a cadeia de caracteres da classe
        {
            c = getCharAt(i); // armazena o caractere atual
            
            if (c != ' ') // checa se ele nao e' um espaco em branco
            {
                newStr[counter] = c; // adiciona-o ao novo arranjo
                counter++; // aumenta o contador de posicoes para newStr
            }
        }
        
        // converte newStr para String e cria um novo objeto com essa cadeia
        return new MyString(charArrayToString(newStr));
    }
    
    /**
     * Compara esse objeto a outro MyString
     * @param otherMyStr objeto a se comparar
     * @return 0 se os dois forem iguais. 1 se esse objeto tiver uma cadeia maior
     * que a do outro. -1 se a cadeia desse for menor que a do outro.
     */
    
    public int compareTo(MyString otherMyStr)
    {
        if (otherMyStr == null)
        {
            setErrorCode(6);
            return 1;
        }
        
        if (getErrorCode() == 6) setErrorCode(0); // se havia erro o remove
        
        int difference, strLength = length(), otherMyStrLength = otherMyStr.length();
        
        if (this.equals(otherMyStr))
        {
            difference = 0;
        }
        
        else if (otherMyStr.isEmpty())
        {
            difference = getCharAt(0);
        }
        
        else if (isEmpty())
        {
            difference = -otherMyStr.getCharAt(0);
        }
        
        else if (strLength <= otherMyStrLength)
        {
            difference = 0;

            for (int i = 0; i < strLength && difference == 0; i++)
            {
                difference = getCharAt(i) - otherMyStr.getCharAt(i);
            }
            
            if (difference == 0)
            {
                difference = -otherMyStr.getCharAt(strLength);
            }
        }
        
        else
        {
            difference = 0;
            
            for (int i = 0; i < otherMyStrLength && difference == 0; i++)
            {
                difference = getCharAt(i) - otherMyStr.getCharAt(i);
            }
            
            if (difference == 0)
            {
                difference = getCharAt(otherMyStrLength);
            }
        }
        
        return difference;
    }
    
    /**
     * Concatena a cadeia desse objeto com a cadeia informada e retorna um novo
     * objeto com o resultado
     * @param otherStr cadeia a ser concatenada
     * @return novo objeto MyString com as cadeias concatenadas
     */
    
    public MyString append(String otherStr)
    {
        if (otherStr == null)
        {
            setErrorCode(7);
            return new MyString();
        }
        
        if (getErrorCode() == 7) setErrorCode(0); // se havia erro o remove
        
        return new MyString(str + otherStr);
    }
    
    /**
     * Concatena a cadeia desse objeto com a do objeto informado e retorna um novo
     * objeto com o resultado da concatenacao
     * @param otherMyStr objeto com cadeia a ser adicionada
     * @return novo objeto MyString com as cadeias concatenadas
     */
    
    public MyString append(MyString otherMyStr)
    {
        if (otherMyStr == null)
        {
            setErrorCode(7);
            return new MyString();
        }
        
        if (getErrorCode() == 7) setErrorCode(0); // se havia erro o remove
        
        return new MyString(str + otherMyStr);
    }
    
    /**
     * Concatena a cadeia informada com a cadeia desse objeto e retorna um novo
     * objeto com o resultado da concatenacao
     * @param otherStr cadeia a ser adicionada
     * @return novo objeto MyString com as cadeias concatenadas
     */
    
    public MyString prepend(String otherStr)
    {
        if (otherStr == null)
        {
            setErrorCode(8);
            return new MyString();
        }
        
        if (getErrorCode() == 8) setErrorCode(0); // se havia erro o remove
        
        return new MyString(otherStr + str);
    }
    
    /**
     * Concatena a cadeia do objeto informado com a desse objeto e retorna um novo
     * objeto com o resultado da concatenacao
     * @param otherMyStr objeto com cadeia a ser adicionada
     * @return novo objeto MyString com as cadeias concatenadas
     */
    
    public MyString prepend(MyString otherMyStr)
    {
        if (otherMyStr == null)
        {
            setErrorCode(8);
            return new MyString();
        }
        
        if (getErrorCode() == 8) setErrorCode(0); // se havia erro o remove
        
        return new MyString(otherMyStr + str);
    }
    
    /**
     * Cria uma subcadeia de caracteres a partir de um recorte da cadeia da classe
     * @param start indice de comeco do recorte
     * @param numberOfElements quantidade de elementos a se recortar
     * @return uma subcadeia da cadeia da classe (pedaco da cadeia da classe) 
     */
    
    public MyString substring(int start, int numberOfElements)
    {
        if (limitWillBreak(start, numberOfElements, str.toCharArray()))
        {
            setErrorCode(1);
            return null;
        }
        
        if (getErrorCode() == 1) setErrorCode(0); // se havia erro o remove
        
        // cria um arranjo de caracteres com o tamanho da subcadeia
        char[] newStr = new char[numberOfElements];
        
        for (int i = 0; i < numberOfElements; i++) // percorre esse arranjo
        {
            // adiciona cada caractere da cadeia da classe ao arranjo a partir de um indice inicial
            newStr[i] = getCharAt(start + i);
        }
        
        return new MyString(charArrayToString(newStr));
    }
    
    /**
     * Cria uma subcadeia de caracteres a partir de um recorte da cadeia da classe
     * @param start indice de comeco do recorte
     * @return uma subcadeia da cadeia da classe (pedaco da cadeia da classe) 
     */
    
    public MyString substring(int start)
    {
        return substring(start, length() - start);
    }
    
    /**
     * Remove todas as ocorrências do caractere informado no inicio e no fim da cadeia da classe
     * @return novo objeto MyString com os caracteres removidos
     */
    
    public MyString trimStartAndEnd(char characterToRemove)
    {
        int length = length();
        
        // retorna uma cadeia vazia se a cadeia da classe só tiver os caracteres que se deseja remover
        if (AxellIO.getCharTimes(characterToRemove, str) == length) return new MyString();
        
        int numberOfCharactersOnStart = 0, numberOfCharactersOnEnd = 0;
        boolean existCharacter = true;
        
        // checa se a cadeia comeca com no minimo um caractere que se deseja remover
        if (str.startsWith("" + characterToRemove))
        {
            numberOfCharactersOnStart++; // aumenta a quantidade de caracteres que se deseja remover do inicio
            
            // percorre a cadeia da classe a partir do segundo elemento ate' contar
            // todos os caracteres a serem removidos do inicio da cadeia
            for (int i = 1; i < length && existCharacter; i++)
            {
                // checa se o caractere atual e' o caractere a ser removido
                if (existCharacter = getCharAt(i) == characterToRemove) // se for,
                {
                    numberOfCharactersOnStart++; // aumenta a quantidade de caracteres a serem removidos do inicio
                }
            }
        }
        
        existCharacter = true; // reseta a variavel existSpace
        
        // checa se a cadeia termina com no minimo um caractere que se deseja remover
        if (str.endsWith("" + characterToRemove))
        {
            numberOfCharactersOnEnd++; // aumenta a quantidade de caracteres que se deseja remover do final
            
            // percorre a cadeia da classe da direta para a esquerda a partir do penultimo
            // elemento ate' contar todos os caracteres a serem removidos do final da cadeia
            for (int i = length - 2; i > 0 && existCharacter; i--)
            {
                // checa se o caractere atual e' um caractere a ser removido
                if (existCharacter = getCharAt(i) == characterToRemove) // se for,
                {
                    numberOfCharactersOnEnd++; // aumenta a quantidade de caracteres a serem removidos do final
                }
            }
        }
        
        return substring(numberOfCharactersOnStart, length - numberOfCharactersOnStart - numberOfCharactersOnEnd);
    }
    
    /**
     * Remove espacos em branco do inicio e do fim da cadeia da classe
     * @return novo objeto MyString com os espacos removidos
     */
    
    public MyString trimStartAndEnd()
    {
        return trimStartAndEnd(' ');
    }
    
    public Integer getInt()
    {
        return AxellIO.getInt(str);
    }
    
    public Double getDouble()
    {
        return AxellIO.getDouble(str);
    }
    
    public Boolean getBoolean()
    {
        return AxellIO.getBoolean(str);
    }
    
    public boolean contains(String substring)
    {
        if (substring == null || substring.length() > length()) return false;
        
        if (str.equals(substring)) return true;
        
        if (substring.equals("")) return false;
        
        char[] strCharArray = str.toCharArray();
        
        int length = strCharArray.length, substringCounter = 0, substringLength = substring.length();
        
        boolean substringStartWasFound = false, strSupportsSubstringSize = true;
        
        for (int i = 0; i < length && substringCounter < substringLength && strSupportsSubstringSize; i++)
        {
            if (!substringStartWasFound && getCharAt(i) == substring.charAt(0))
            {
                strSupportsSubstringSize = !limitWillBreak(i, substringLength, strCharArray);
                
                if (strSupportsSubstringSize)
                {
                    substringStartWasFound = true;
                    substringCounter = 1;
                }
            }
            
            else if (substringStartWasFound)
            {
                substringStartWasFound = getCharAt(i) == substring.charAt(substringCounter++);
            }
        }
        
        return substringStartWasFound;
    }
    
    public String toUpperCase()
    {
        String upperCaseString = "";
        
        for (char c : str.toCharArray())
        {
            if (AxellIO.isLowerCase(c))
            {
                upperCaseString += (char) (c + ('A' - 'a'));
            }
            
            else
            {
                upperCaseString += c;
            }
        }
        
        return upperCaseString;
    }
    
    public String toLowerCase()
    {
        String lowerCaseString = "";
        
        for (char c : str.toCharArray())
        {
            if (AxellIO.isUpperCase(c))
            {
                lowerCaseString += (char) (c + ('a' - 'A'));
            }
            
            else
            {
                lowerCaseString += c;
            }
        }
        
        return lowerCaseString;
    }
    
    public MyString replace(char charToFind, char charToReplace)
    {
        String replacedString = "";
        
        for (char c : str.toCharArray())
        {
            if (c == charToFind)
            {
                replacedString += charToReplace;
            }
            
            else
            {
                replacedString += c;
            }
        }
        
        return new MyString(replacedString);
    }
    
    public MyString replace(String stringToFind, String stringToReplace)
    {
        String replacedString = "";
        char firstCharOfStringToFind = stringToFind.charAt(0);
        char currentChar;
        int length = length();
        int i = 0, newIndex; // cursor
        
        while (i < length)
        {
            currentChar = getCharAt(i); // obtem o caractere na posicao atual do cursor
            
            if (currentChar == firstCharOfStringToFind) // checa se o caractere atual e' o caractere a ser procurado
            {
                // checa se existe a subcadeia procurada a partir do indice atual,
                // se existe, recebe o indice onde ela termina
                newIndex = hasSubstringOnIndex(i, stringToFind);
                
                if (newIndex == -1) // checa se a subcadeia nao foi encontrada
                {
                    replacedString += currentChar; // adiciona o caractere atual 'a string
                    
                    newIndex = i + 1; // passa o cursor para o proximo caractere
                }
                
                else
                {
                    replacedString += stringToReplace; // adiciona a subcadeia substituta
                    
                    currentChar = getCharAt(newIndex);
                    
                    
                }
            }
            
            else
            {
                replacedString += currentChar; // adiciona o caractere atual 'a string
                
                
                newIndex = i + 1; // passa o cursor para o proximo caractere
            }
            
            i = newIndex; // atualiza o cursor real
        }
        
        return new MyString(replacedString);
    }
    
    public MyString replaceAll(char charToFind, char charToReplace)
    {
        String replacedString = "";
        char currentChar;
        int length = length();
        int i = 0; // cursor
        
        while (i < length)
        {
            currentChar = getCharAt(i); // obtem o caractere na posicao atual do cursor
            
            if (currentChar == charToFind) // checa se o caractere atual e' o caractere a ser procurado
            {
                replacedString += charToReplace; // adiciona o caractere substituto 'a string
                
                i++; // passa o cursor para a proxima posicao
                
                // checa se o cursor ainda esta' dentro da string e se o 
                // caractere em sua posicao e' o caractere a ser procurado
                while (i < length && getCharAt(i) == charToFind)
                {
                    i++; // passa o cursor para a proxima posicao
                }
            }
            
            else
            {
                replacedString += currentChar; // adiciona o caractere atual 'a string
                
                i++;
            }
        }
        
        return new MyString(replacedString);
    }
    
    public MyString replaceAll(String stringToFind, String stringToReplace)
    {
        String replacedString = "";
        char firstCharOfStringToFind = stringToFind.charAt(0);
        char currentChar;
        int length = length();
        int i = 0, newIndex; // cursor
        
        while (i < length)
        {
            currentChar = getCharAt(i); // obtem o caractere na posicao atual do cursor
            
            if (currentChar == firstCharOfStringToFind) // checa se o caractere atual e' o caractere a ser procurado
            {
                // checa se existe a subcadeia procurada a partir do indice atual,
                // se existe, recebe o indice onde ela termina
                newIndex = hasSubstringOnIndex(i, stringToFind);
                
                if (newIndex == -1) // checa se a subcadeia nao foi encontrada
                {
                    replacedString += currentChar; // adiciona o caractere atual 'a string
                    
                    newIndex = i + 1; // passa o cursor para o proximo caractere
                }
                
                else
                {
                    replacedString += stringToReplace; // adiciona a subcadeia substituta
                    
                    int nextIndex = hasSubstringOnIndex(newIndex, stringToFind);
                    
                    while ( nextIndex != -1 )
                    {
                        newIndex = nextIndex;
                        
                        nextIndex = hasSubstringOnIndex(newIndex, stringToFind);
                    }
                }
            }
            
            else
            {
                replacedString += currentChar; // adiciona o caractere atual 'a string
                
                newIndex = i + 1; // passa o cursor para o proximo caractere
            }
            
            i = newIndex; // atualiza o cursor real
        }
        
        return new MyString(replacedString);
    }
    
    private String compactData(char delimiter)
    {
        char[] strCharArray = str.toCharArray();
        MyString compactedData = new MyString();
        boolean nextStrTime = false;
        
        for (int i = 0; i < strCharArray.length; i++)
        {
            if (nextStrTime)
            {
                compactedData = compactedData.append("" + delimiter);
                nextStrTime = false;
            }
            
            else if (strCharArray[i] != delimiter)
            {
                compactedData = compactedData.append("" + strCharArray[i]);
                
                if (!limitWillBreak(i + 1, 1, strCharArray) && strCharArray[i + 1] == delimiter)
                {
                    nextStrTime = true;
                }
            }
        }
        
        if (compactedData.isEmpty()) return null;
        
        else if (compactedData.getLastChar() == delimiter)
        {
            return compactedData.removeLastChar().toString();
        }
        
        else return compactedData.toString();
    }
    
    public String[] split(char delimiter)
    {
        String compactedStr = compactData(delimiter);
        
        if (compactedStr == null) return null;
        
        String[] splitedStr = new String[AxellIO.getCharTimes(delimiter, compactedStr) + 1];
        splitedStr[0] = "";
        int compactedStrLength = compactedStr.length(), delimitersCounter = 0;
        char currentChar;
        
        for (int i = 0; i < compactedStrLength; i++)
        {
            currentChar = compactedStr.charAt(i);
            
            if (currentChar == delimiter)
            {
                delimitersCounter++;
                splitedStr[delimitersCounter] = "";
            }
            
            else
            {
                splitedStr[delimitersCounter] += currentChar;
            }
        }
        
        return splitedStr;
    }
    
    public String[] split()
    {
        return split(' ');
    }
    
    private String moveStr(int rotation)
    {
        MyString encryptedStr = clone();
        int strLength = length();
        
        for (int i = 0; i < strLength; i++)
        {
            encryptedStr.setCharAt(i, (char) (getCharAt(i) + rotation));
        }
        
        return encryptedStr.toString();
    }
    
    public String encrypt()
    {
        return moveStr(3);
    }
    
    public String decrypt()
    {
        return moveStr(-3);
    }
    
    public String invert()
    {
        MyString invertedStr = clone();
        int strLength = length();
        
        for (int i = 0; i < strLength; i++)
        {
            invertedStr.setCharAt(i, getCharAt(strLength - 1 - i));
        }
        
        return invertedStr.toString();
    }
    
    public int hasSubstringOnIndex(int startIndex, String substring)
    {
        return new MyString(substring).equals(substring(startIndex, substring.length())) ? startIndex + substring.length() : -1;
    }
    
    /**
     * Checa se, a partir de um indice inicial, e' impossivel acessar certa quantidade
     * de elementos ou espacos de um arranjo
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * @param charArray arranjo a ser analisado
     * @return true se for impossivel, caso contrario, false
     */
    
    private boolean limitWillBreak(int start, int numberOfElements, char[] charArray)
    {
        if (start < 0 || numberOfElements < 1 || (start + numberOfElements) > charArray.length)
        {
            //System.out.println("\nIndice inicial (" + start + ") e a quantidade de elementos (" + numberOfElements + ") levam a erro");
            return true;
        }
        
        return false;
    }
}
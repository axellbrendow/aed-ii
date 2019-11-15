import java.io.*;
import java.nio.charset.Charset;

/*

PUC Minas - Ciencia da Computacao     Nome: ErrorClass

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 16/03/2018

*/

/* ----------------------------------------- CLASSES E INTERFACES AUXILIARES ----------------------------------------- */

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

/*

PUC Minas - Ciencia da Computacao     Nome: MyString

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 19/04/2018

*/

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

/*

PUC Minas - Ciencia da Computacao     Nome: AxellIO

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  4.0                          Data: 05/09/2018

*/

class AxellIO
{
    public static final String LINE_SEPARATOR =
            System.getProperty("java.vendor.url").equals("http://www.android.com/")
            ? "\n" + (char) 65535 : System.lineSeparator();

    public static final String LAST_LINE_CHAR = LINE_SEPARATOR.substring(LINE_SEPARATOR.length() - 1);
    
    private static PrintStream printStream = System.out;
    private static String charset = "ISO-8859-1"; // charset padrao
    private static InputStream inputStream = System.in; // corrente de entrada padrao
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)));
    private static ByteArrayOutputStream inputStreamData; // buffer para guardar os dados da corrente de entrada
    
    // ----------- FUNCOES DE REDIRECIONAMENTO DE ENTRADA E SAIDA -----------
    
    public static PrintStream getPrintStream()
    {
        return printStream;
    }
    
    public static void setPrintStream(PrintStream printStream)
    {
        if (printStream == null)
        {
            //AxellIO.println("[AxellIO]: Corrente de impressao nula. - funcao setPrintStream(PrintStream)");
        }
        
        else
        {
            AxellIO.printStream = printStream;
        }
    }
    
    public static void setPrintStream(String fileName)
    {
        if (fileName == null)
        {
            //AxellIO.println("[AxellIO]: Nome do arquivo nulo. - funcao setPrintStream(String)");
        }
        
        else
        {
            try
            {
                AxellIO.printStream = new PrintStream(fileName, charset);
            }
            
            catch (FileNotFoundException ex)
            {
                println("[AxellIO]: Arquivo nao encontrado. (fileName = " + fileName + ") - funcao setPrintStream(String)");
            }
            
            catch (UnsupportedEncodingException ex) {}
        }
    }
    
    /**
     * @return uma corrente de saida que contem os bytes da ultima entrada padrao da classe
     */
    
    public static ByteArrayOutputStream getInputStreamData()
    {
        return inputStreamData;
    }
    
    /**
     * Atribui quais sao os dados da entrada padrao da classe (para serem recuperados posteriormente)
     * @param inputStreamData corrente de saida com os bytes da corrente de entrada
     */
    
    private static void setInputStreamData(ByteArrayOutputStream inputStreamData)
    {
        if (inputStreamData == null)
        {
            //AxellIO.println("[AxellIO]: Dados da corrente de entrada nulos - funcao setInputStreamData(ByteArrayOutputStream)");
        }
        
        else
        {
            AxellIO.inputStreamData = inputStreamData;
        }
    }
    
    /**
     * Salva os dados (bytes) da corrente de entrada num campo da classe
     */
    
    public static void saveInputStreamData()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // cria uma corrente de saida
        
        int charByte = readByte(); // le um caractere da corrente de entrada da classe
        
        while (charByte != -1) // checa se o fim da corrente nao foi alcancado
        {
            outputStream.write(charByte); // escreve o caractere na corrente de saida
            
            charByte = readByte(); // le o proximo caractere da corrente de entrada da classe
        }
        
        setInputStreamData(outputStream); // guarda a corrente de saida num campo da classe
    }
    
    /**
     * Redefine o leitor da entrada padrao da classe
     */
    
    private static void redefineReader()
    {
        reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)));
    }
    
    /**
     * Define a entrada padrao da classe
     * @param inputStream nova entrada padrao da classe
     */
    
    public static void setInputStream(InputStream inputStream)
    {
        if (inputStream == null)
        {
            //AxellIO.println("[AxellIO]: Parametro inputStream nulo - funcao setInputStream(InputStream)");
        }
        
        else
        {
            AxellIO.inputStream = inputStream;
            
            redefineReader();
        }
    }
    
    /**
     * @return entrada padrao da classe
     */
    
    public static InputStream getInputStream()
    {
        return AxellIO.inputStream;
    }
    
    /**
     * Recupera os ultimos dados da entrada padrao que tenham sido salvos pela funcao saveInputStreamData()
     */
    
    public static void recoverLastInputStreamData()
    {
        setInputStream( new ByteArrayInputStream(getInputStreamData().toByteArray()) );
    }
    
    /**
     * Muda o charset padrao da classe
     * @param charset novo charset
     */
    
    public static void setCharset(String charset)
    {
        AxellIO.charset = charset;
        
        redefineReader();
    }
    
    // ----------- FUNCOES DE UTILIDADE -----------
    
    /**
     * Percorre uma String contando a quantidade de um certo caractere nela
     * @param c caractere a se procurar
     * @param str String a percorrer
     * @return quantidade de vezes que o caractere aparece na String
     */
    
    public static int getCharTimes(char c, String str)
    {
        int times = 0;
        int length = str.length();

        for (int i = 0; i < length; i++)
        {
            if (str.charAt(i) == c)
            {
                times++;
            }
        }

        return times;
    }
    
    /**
     * Percorre um arranjo de caracteres checando a existencia de algum pre' determinado
     * @param c caractere a se procurar
     * @param array arranjo de caracteres a percorrer
     * @return true se o caractere existir no arranjo e false caso nao exista
     */
    
    public static boolean contains(char c, char[] array)
    {
        boolean contains = false;
        int counter = 0;

        while (!contains && counter < array.length)
        {
            contains = array[counter] == c;

            counter++;
        }

        return contains;
    }
    
    /**
     * Checa se um determinado caractere esta' num intervalo de caracteres
     * @param input caractere a ser analisado
     * @param lowerLimit limite inferior do intervalo
     * @param upperLimit limite superior do intervalo
     * @return true se o caractere estiver no intervalo e false se nao estiver
     */
    
    public static boolean isOnInterval(char input, char lowerLimit, char upperLimit)
    {
        return input >= lowerLimit && input <= upperLimit;
    }
    
    public static boolean isDigit(char input)
    {
        return isOnInterval(input, '0', '9');
    }
    
    public static boolean isLowerCase(char input)
    {
        return isOnInterval(input, 'a', 'z');
    }
    
    public static boolean isUpperCase(char input)
    {
        return isOnInterval(input, 'A', 'Z');
    }
    
    public static boolean isLetter(char input)
    {
        return isLowerCase(input) || isUpperCase(input);
    }
    
    public static boolean isFloat(char input)
    {
        return isDigit(input) || input == '.';
    }
    
    public static boolean isString(char input)
    {
        return !newLineOnInput(input) && input != ' ';
    }
    
    /**
     * Checa se um determinado caractere esta' num intervalo especifico de caracteres
     * @param input caractere a ser analisado
     * @param accept caractere que simboliza o intervalo especifico de caracteres:
     * d - digits (0-9)
     * u - upperCases (ABC)
     * l - lowerCases (abc)
     * a - alphabet (ABCabc)
     * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
     * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
     * 
     * @return true se o caractere estiver no intervalo e false caso nao esteja
     */
    
    public static boolean isOnInterval(char input, char accept)
    {
        boolean bool = false;

        switch (accept)
        {
            case 'd':
                bool = isDigit(input);
                break;

            case 'u':
                bool = isUpperCase(input);
                break;

            case 'l':
                bool = isLowerCase(input);
                break;

            case 'a': // alphabet
                bool = isLetter(input);
                break;

            case 'f':
                bool = isFloat(input);
                break;
                    
            case 's':
                bool = isString(input);
                break;
        }

        return bool;
    }
    
    /**
     * Checa se um determinado caractere esta' em algum intervalo usual de caracteres
     * @param input caractere a ser analisado
     * @param accept arranjo de caracteres que simbolizam os intervalos
     * especificos a serem aceitos:
     * d - digits (0-9)
     * u - upperCases (ABC)
     * l - lowerCases (abc)
     * a - alphabet (ABCabc)
     * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
     * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
     * 
     * @return true se o caractere estiver no intervalo e false caso nao esteja
     */
    
    public static boolean isOnInterval(char input, char[] accept)
    {
        boolean bool = false;
        int index = 0;

        while (index < accept.length && bool == false)
        {
            switch (accept[index])
            {
                case 'd':
                    bool = isDigit(input);
                    break;

                case 'u':
                    bool = isUpperCase(input);
                    break;

                case 'l':
                    bool = isLowerCase(input);
                    break;

                case 'a': // alphabet
                    bool = isLetter(input);
                    break;

                case 'f':
                    bool = isFloat(input);
                    break;
                    
                case 's':
                    bool = isString(input);
                    break;
            }

            index++;
        }

        return bool;
    }
    
    /**
     * Checa se uma String contem apenas caracteres especificos
     * @param str String a ser analisada
     * @param accept caractere que simboliza o intervalo especifico de caracteres:
     * d - digits (0-9)
     * u - upperCases (ABC)
     * l - lowerCases (abc)
     * a - alphabet (ABCabc)
     * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
     * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
     * 
     * @return true se a String tiver apenas os caracteres especificos, caso contrario, false
     */
    
    public static boolean isSpecificString(String str, char accept)
    {
        if (str == null || str.length() == 0) return false;
        
        char firstChar = str.charAt(0);
        int length = str.length();
        
        boolean isValid = isOnInterval(firstChar, accept) ||
                ((accept == 'd' || accept == 'f') && firstChar == '-' && length > 1);
        
        for (int i = 1; i < length && isValid; i++)
        {
            isValid = isOnInterval(str.charAt(i), accept);
        }
        
        return isValid;
    }
    
    public static int getint(String str)
    {
        return !isSpecificString(str, 'd') ? 0 : Integer.parseInt(str);
    }
    
    public static Integer getInt(String str)
    {
        return !isSpecificString(str, 'd') ? null : Integer.valueOf(str);
    }
    
    public static double getdouble(String str)
    {
        if (isSpecificString(str, 'f'))
        {
            return !isAValidReal(str) ? 0.0 : Double.parseDouble(str);
        }
        
        else
        {
            return 0.0;
        }
    }
    
    public static Double getDouble(String str)
    {
        if (isSpecificString(str, 'f'))
        {
            return !isAValidReal(str) ? null : Double.valueOf(str);
        }
        
        else
        {
            return null;
        }
    }
    
    public static Boolean getBoolean(String str)
    {
        switch (str)
        {
            case "true":
            case "T":
            case "1":
                return true;
                
            case "false":
            case "F":
            case "0":
                return false;
                
            default:
                return null;
        }
    }
    
    public static boolean getboolean(String str)
    {
        return (boolean) getBoolean(str);
    }
    
    /**
     * Limpa o reader ate' a proxima linha
     */
    
    public static void cleanReader()
    {
        while (!LAST_LINE_CHAR.equals(read() + ""));
    }
    
    /**
     * Limpa ou nao o reader de acordo com a ultima entrada
     * @param lastInput ultimo caractere lido
     */
    
    public static void cleanReader(char lastInput)
    {
        // so' limpa se a ultima leitura nao for o ultimo caractere que marca a quebra de linha
        if (!LAST_LINE_CHAR.equals(lastInput + ""))
        {
            cleanReader();
        }
    }
    
    /**
     * Checa se existe algum caractere de quebra de linha num dado caractere
     * @param input caractere a ser analisado
     * @return true se o caractere for algum dos que marcam a quebra de linha
     */
    
    public static boolean newLineOnInput(char input)
    {
        return LINE_SEPARATOR.contains(input + "");
    }
    
    /**
     * Dado o tamanho de um conjunto de elementos, checa se, a partir de um
     * indice inicial, e' impossivel acessar certa quantidade deles.
     * 
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * @param setSize tamanho do conjunto
     * 
     * @return se for possivel acessar, retorna 0, se nao for, retorna enquanto o limite
     * do conjunto sera' estourado.
     * 
     * Obs.: Caso a quantidade de elementos informada for menor que 1, a funcao
     * retornara 1.
     */
    
    public static int limitOverflow(int start, int numberOfElements, int setSize)
    {
        int limitOverflow;
        
        if (start < 0)
        {
            //AxellIO.println("[AxellIO]: Parametro start < 0 na funcao limitOverflow() - (start = " + start + ")");
            
            limitOverflow = start;
        }
        
        else if (numberOfElements < 1)
        {
            //AxellIO.println("[AxellIO]: Parametro numberOfElements < 1 na funcao limitOverflow() - (numberOfElements = " + numberOfElements + ")");
            
            limitOverflow = 1; // 1 = true
        }
        
        else if (start + numberOfElements > setSize)
        {
            //AxellIO.println("[AxellIO]: Parametros start e numberOfElements quebram o limite na funcao limitOverflow() - (start = " + start + " | numberOfElements = " + numberOfElements + ")");
            
            limitOverflow = (start + numberOfElements) - setSize;
        }
        
        else
        {
            limitOverflow = 0; // 0 = false
        }
        
        return limitOverflow;
    }
    
    /**
     * Dado o tamanho de um conjunto de elementos, checa se, a partir de um
     * indice inicial, e' impossivel acessar certa quantidade deles. Caso o
     * parametro backward seja true, a checagem se da no sentido contrario ao
     * do conjunto, ou seja, para tras.
     * 
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * @param setSize tamanho do conjunto
     * @param backward tamanho do conjunto
     * 
     * @return se for possivel acessar, retorna 0, se nao for, retorna enquanto o limite
     * do conjunto sera' estourado.
     * 
     * Obs.: Caso a quantidade de elementos informada for menor que 1, a funcao
     * retornara 1.
     */
    
    public static int limitOverflow(int start, int numberOfElements, int setSize, boolean backward)
    {
        int limitOverflow;
        
        if (backward)
        {
            if (start < 0)
            {
                //AxellIO.println("[AxellIO]: Parametro start < 0 na funcao limitOverflow() - (start = " + start + ")");

                limitOverflow = start;
            }
            
            else if (start > setSize - 1)
            {
                //AxellIO.println("[AxellIO]: Parametro start > setSize - 1 na funcao limitOverflow() - (start = " + start + " | setSize = " + setSize + ")");
                
                limitOverflow = start - (setSize - 1);
            }
            
            else // se nao,
            {
                // corto e jogo fora a parte posterior ao indice inicial do conjunto,
                // pego o que sobrou e rotaciono 180 graus no sentido horario finjindo ser
                // um novo arranjo em que o o indice inicial e' na verdade o indice 0
                limitOverflow = -limitOverflow(0, numberOfElements, start + 1);
            }
        }
        
        else
        {
            limitOverflow = limitOverflow(start, numberOfElements, setSize);
        }
        
        return limitOverflow;
    }
    
    /**
     * Dado um arranjo, checa se, a partir de um indice inicial, e' impossivel
     * acessar certa quantidade de elementos dele.
     * 
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * @param objectArray arranjo
     * 
     * @return se for possivel acessar, retorna 0, se nao for, retorna enquanto o limite
     * do conjunto sera' estourado.
     * 
     * Obs.: Caso a quantidade de elementos informada for menor que 1, a funcao
     * retornara 1.
     */
    
    public static int limitOverflow(int start, int numberOfElements, Object[] objectArray)
    {
        if (objectArray == null)
        {
            //AxellIO.println("[AxellIO]: Arranjo nulo. - funcao limitOverflow(int, int, Object[])");
            return 1;
        }
        
        else
        {
            return limitOverflow(start, numberOfElements, objectArray.length);
        }
    }
    
    /**
     * Dado o tamanho de um conjunto de elementos, checa se e' impossivel acessar
     * um indice do conjunto.
     * 
     * @param index indice
     * @param setSize tamanho do conjunto
     * 
     * @return se for possivel acessar, retorna 0, se nao for, retorna enquanto o limite
     * do conjunto sera' estourado.
     */
    
    public static int limitOverflow(int index, int setSize)
    {
        return limitOverflow(index, 1, setSize);
    }
    
    /**
     * Dado um arranjo de objetos, checa se e' impossivel acessar um indice do conjunto.
     * 
     * @param index indice
     * @param objectArray arranjo
     * 
     * @return se for possivel acessar, retorna 0, se nao for, retorna enquanto o limite
     * do arranjo sera' estourado.
     */
    
    public static int limitOverflow(int index, Object[] objectArray)
    {
        return limitOverflow(index, 1, objectArray);
    }
    
    // ----------- FUNCOES DE ENTRADA DE DADOS -----------
    
    /**
     * Le um caractere da entrada padrao
     * @return retorna o caractere lido como inteiro
     */
    
    public static int readByte()
    {
        try
        {
            return reader.read();
        }
        
        catch (IOException ex)
        {
            println("Nao foi possivel ler da entrada padrao");
            
            return -1;
        }
    }
    
    /**
     * Le um caractere da entrada padrao
     * @return retorna o caractere lido
     */
    
    public static char read()
    {
        return (char) readByte();
    }
    
    /**
     * Le uma String que tenha apenas alguns caracteres especificos
     * @param text texto a ser mostrado para o usuario antes da obtencao da String
     * @param accept caractere que simboliza o intervalo especifico de caracteres:
     * d - digits (0-9)
     * u - upperCases (ABC)
     * l - lowerCases (abc)
     * a - alphabet (ABCabc)
     * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
     * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
     * 
     * @return null se o usuario nao informar os caracteres desejados ou a String
     * com os caracteres especificos
     */
    
    public static String readSpecificString(String text, char accept)
    {
        String specificString = null;
        boolean isAcceptingNumber = accept == 'd' || accept == 'f';
        char input;
        
        print(text);
        
        do
        {
            input = read();
            
        } while (newLineOnInput(input) || input == ' ');

        if (isOnInterval(input, accept) || (isAcceptingNumber && input == '-'))
        {
            specificString = "" + input;
            
            input = read();
            
            while (isOnInterval(input, accept))
            {
                specificString += input;
                
                input = read();
            }
            
            if ((!newLineOnInput(input) && input != ' ' && input != ',') || (isAcceptingNumber && specificString.equals("-")))
            {
                specificString = null;
            }
        }
        
        cleanReader(input);
        
        return specificString;
    }
    
    /**
     * Le uma String que tenha apenas alguns caracteres especificos
     * @param text texto a ser mostrado para o usuario antes da obtencao da String
     * @param accept arranjo de caracteres que simbolizam os intervalos
     * especificos a serem aceitos:
     * d - digits (0-9)
     * u - upperCases (ABC)
     * l - lowerCases (abc)
     * a - alphabet (ABCabc)
     * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
     * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
     * 
     * @return null se o usuario nao informar os caracteres desejados ou a String
     * com os caracteres especificos
     */
    
    public static String readSpecificString(String text, char[] accept)
    {
        String specificString = null;
        boolean isAcceptingNumber = contains('d', accept) || contains('f', accept);
        char input;
        
        print(text);

        do
        {
            input = read();
            
        } while (newLineOnInput(input) || input == ' ');

        if (isOnInterval(input, accept) || (isAcceptingNumber && input == '-'))
        {
            specificString = "" + input;

            input = read();

            while (isOnInterval(input, accept))
            {
                specificString += input;
                input = read();
            }

            if ((!newLineOnInput(input) && input != ' ' && input != ',') || (isAcceptingNumber && specificString.equals("-")))
            {
                specificString = null;
            }
        }
        
        cleanReader(input);
        
        return specificString;
    }
    
    public static String readLine(String text)
    {
        print(text);
        
        char input = read();
        String line = "";
        
        while (!newLineOnInput(input))
        {
            line += input;
            input = read();
        }
        
        return line;
    }
    
    public static String readString(String text)
    {
        return readSpecificString(text, 's');
    }
    
    public static int readint(String text)
    {
        String data = readSpecificString(text, 'd');
        return data == null ? 0 : Integer.parseInt(data);
    }

    public static Integer readInt(String text)
    {
        String data = readSpecificString(text, 'd');
        return data == null ? null : Integer.valueOf(data);
    }

    public static float readfloat(String text)
    {
        String data = readSpecificString(text, 'f');
        return !isAValidReal(data) ? 0 : Float.parseFloat(data);
    }

    public static Float readFloat(String text)
    {
        String data = readSpecificString(text, 'f');
        return !isAValidReal(data) ? null : Float.valueOf(data);
    }

    public static double readdouble(String text)
    {
        String data = readSpecificString(text, 'f');
        return !isAValidReal(data) ? 0 : Double.parseDouble(data);
    }

    public static Double readDouble(String text)
    {
        String data = readSpecificString(text, 'f');
        return !isAValidReal(data) ? null : Double.valueOf(data);
    }
    
    /**
     * Checa se determinada String representa um numero real possivel
     * de se converter pelas funcoes Double.parseDouble() e Float.parseFloat()
     * @param data String a ser analisada
     * @return true se for possivel converter e false caso nao
     */
    
    private static boolean isAValidReal(String data)
    {
        return data != null && /*data.charAt(0) != '.' &&*/ getCharTimes('.', data) <= 1;
    }
    
    // ----------- FUNCOES DE SAIDA DE DADOS -----------
    
    public static void print(String data)
    {
        try
        {
            new PrintStream(getPrintStream(), true, charset).print(data);
        }
        
        catch (UnsupportedEncodingException ex){}
    }
    
    public static void println()
    {
        print(LINE_SEPARATOR);
    }
    
    public static void println(String data)
    {
        print(data + LINE_SEPARATOR);
    }
    
    public static void pause()
    {
        cleanReader(read());
    }
    
    public static void pause(String text)
    {
        println(text);
        pause();
    }
}

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
     * Recebe dois indices de dois elementos e coloca um elemento no indice do outro.
     * 
     * @param firstIndex indice do primeiro elemento
     * @param secondIndex indice do segundo elemento
     */
    
    public void swap(int firstIndex, int secondIndex)
    {
        Object copy = get(firstIndex);
        
        set(get(secondIndex), firstIndex);
        set(copy, secondIndex);
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

/*

PUC Minas - Ciencia da Computacao     Nome: Instituicao

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 01/09/2018

*/

class Instituicao
{
    // ----------------------- ATRIBUTOS DA CLASSE
    
    private Integer codigo;
    private String nome;
    private String sigla;
    private Integer codigoMantenedora;
    private String mantenedora;
    private Integer categoria;
    private Integer organizacao;
    private Integer codigoMunicipio;
    private String municipio;
    private String uf;
    private String regiao;
    private Integer tecnico;
    private Integer periodico;
    private Integer livro;
    private Double receita;
    private Double transferencia;
    private Double outraReceita;
    private Double despesaDocente;
    private Double despesaTecnico;
    private Double despesaEncargo;
    private Double despesaCusteio;
    private Double despesaInvestimento;
    private Double despesaPesquisa;
    private Double despesaOutras;
    private String dadosInstituicao;
    private String dadosInstituicaoTabulado;
    
    // ----------------------- CONSTRUTORES
    
    public Instituicao()
    {
        setCodigo(0);
        setNome("");
        setSigla("");
        setCodigoMantenedora(0);
        setMantenedora("");
        setCategoria(0);
        setOrganizacao(0);
        setCodigoMunicipio(0);
        setMunicipio("");
        setUf("");
        setRegiao("");
        setTecnico(0);
        setPeriodico(0);
        setLivro(0);
        setReceita(0.0);
        setTransferencia(0.0);
        setOutraReceita(0.0);
        setDespesaDocente(0.0);
        setDespesaTecnico(0.0);
        setDespesaEncargo(0.0);
        setDespesaCusteio(0.0);
        setDespesaInvestimento(0.0);
        setDespesaPesquisa(0.0);
        setDespesaOutras(0.0);
    }
    
    public Instituicao(String dadosInstituicao)
    {
        lerDados(dadosInstituicao);
    }
    
    // ----------------------- SETTERS AND GETTERS
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getCodigoMantenedora() {
        return codigoMantenedora;
    }

    public void setCodigoMantenedora(Integer codigoMantenedora) {
        this.codigoMantenedora = codigoMantenedora;
    }

    public String getMantenedora() {
        return mantenedora;
    }

    public void setMantenedora(String mantenedora) {
        this.mantenedora = mantenedora;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Integer organizacao) {
        this.organizacao = organizacao;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Integer periodico) {
        this.periodico = periodico;
    }

    public Integer getLivro() {
        return livro;
    }

    public void setLivro(Integer livro) {
        this.livro = livro;
    }

    public Double getReceita() {
        return receita;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public Double getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Double transferencia) {
        this.transferencia = transferencia;
    }

    public Double getOutraReceita() {
        return outraReceita;
    }

    public void setOutraReceita(Double outraReceita) {
        this.outraReceita = outraReceita;
    }

    public Double getDespesaDocente() {
        return despesaDocente;
    }

    public void setDespesaDocente(Double despesaDocente) {
        this.despesaDocente = despesaDocente;
    }

    public Double getDespesaTecnico() {
        return despesaTecnico;
    }

    public void setDespesaTecnico(Double despesaTecnico) {
        this.despesaTecnico = despesaTecnico;
    }

    public Double getDespesaEncargo() {
        return despesaEncargo;
    }

    public void setDespesaEncargo(Double despesaEncargo) {
        this.despesaEncargo = despesaEncargo;
    }

    public Double getDespesaCusteio() {
        return despesaCusteio;
    }

    public void setDespesaCusteio(Double despesaCusteio) {
        this.despesaCusteio = despesaCusteio;
    }

    public Double getDespesaInvestimento() {
        return despesaInvestimento;
    }

    public void setDespesaInvestimento(Double despesaInvestimento) {
        this.despesaInvestimento = despesaInvestimento;
    }

    public Double getDespesaPesquisa() {
        return despesaPesquisa;
    }

    public void setDespesaPesquisa(Double despesaPesquisa) {
        this.despesaPesquisa = despesaPesquisa;
    }

    public Double getDespesaOutras() {
        return despesaOutras;
    }

    public void setDespesaOutras(Double despesaOutras) {
        this.despesaOutras = despesaOutras;
    }

    public String getDadosInstituicao() {
        return dadosInstituicao;
    }

    public void setDadosInstituicao(String dadosInstituicao) {
        this.dadosInstituicao = dadosInstituicao;
    }

    public String getDadosInstituicaoTabulado() {
        return dadosInstituicaoTabulado;
    }

    public void setDadosInstituicaoTabulado(String dadosInstituicaoTabulado) {
        this.dadosInstituicaoTabulado = dadosInstituicaoTabulado;
    }
    
    // ----------------------- METODOS
    
    /**
     * Recebe uma string que contem os dados da instituicao separados por tabulacoes
     * e, de acordo com esses dados, define os campos da classe
     * @param dadosInstituicao string com os dados da instituicao
     */
    
    public void lerDados(String dadosInstituicao)
    {
        if (dadosInstituicao == null)
        {
            //AxellIO.println("[Instituicao]: Parametro dadosInstituicao nulo - funcao lerDados(String)");
        }
        
        else
        {
            MyString dados = new MyString(dadosInstituicao);
            String[] splitedData = dados.split('\t');
            int cursor = 0;
            
            try
            {
                setCodigo( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setNome( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setSigla( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setCodigoMantenedora( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setMantenedora( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setCategoria( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setOrganizacao( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setCodigoMunicipio( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setMunicipio( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setUf( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setRegiao( !AxellIO.isSpecificString(splitedData[cursor], 'f') ? splitedData[cursor++] : null );
                setTecnico( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setPeriodico( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setLivro( AxellIO.isSpecificString(splitedData[cursor], 'd') ? AxellIO.getInt(splitedData[cursor++]) : null );
                setReceita( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setTransferencia( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setOutraReceita( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaDocente( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaTecnico( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaEncargo( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaCusteio( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaInvestimento( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaPesquisa( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
                setDespesaOutras( AxellIO.isSpecificString(splitedData[cursor], 'f') ? AxellIO.getDouble(splitedData[cursor++]) : null );
            }

            catch (ArrayIndexOutOfBoundsException exception)
            {
                //AxellIO.println("[Instituicao]: Dados da instituicao incompletos - funcao lerDados(String)");
            }
            
            setDadosInstituicaoTabulado( dados.toString() );
            setDadosInstituicao( concatFields() );
        }
    }
    
    /**
     * "Percorre" os campos da classe concatenando os seus valores espacadamente.
     * Campos que tenham valores nulos nao sao considerados.
     * @return string com a representacao textual dos dados da instituicao
     */
    
    private String concatFields()
    {
        String concatedFields = "";
        Integer intChecker;
        String strChecker;
        Double doubleChecker;
        
        concatedFields += ( (intChecker = getCodigo()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getNome()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getSigla()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getCodigoMantenedora()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getMantenedora()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getCategoria()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getOrganizacao()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getCodigoMunicipio()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (strChecker = getMunicipio()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getUf()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (strChecker = getRegiao()) == null ? "" : strChecker ) + " ";
        concatedFields += ( (intChecker = getTecnico()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getPeriodico()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (intChecker = getLivro()) == null ? "" : intChecker ) + " ";
        concatedFields += ( (doubleChecker = getReceita()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getTransferencia()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getOutraReceita()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaDocente()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaTecnico()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaEncargo()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaCusteio()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaInvestimento()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaPesquisa()) == null ? "" : doubleChecker ) + " ";
        concatedFields += ( (doubleChecker = getDespesaOutras()) == null ? "" : doubleChecker ) + " ";
        
        return new MyString(concatedFields).removeLastChar().toString();
    }
    
    /**
     * Le, da entrada padrao da classe AxellIO, uma string que representa textualmente
     * a instituicao e ja' define os campos da classe de acordo com os dados
     */
    
    public void ler()
    {
        lerDados( AxellIO.readLine("") );
    }
    
    /**
     * Imprime uma string que representa textualmente a instituicao da classe
     */
    
    public void imprimir()
    {
        AxellIO.println("" + this);
    }
    
    public static Instituicao convertToInstituicao(Object objectToConvert)
    {
        return (Instituicao) objectToConvert;
    }
    
    @Override
    public Instituicao clone()
    {
        return new Instituicao( getDadosInstituicaoTabulado() );
    }
    
    @Override
    public String toString()
    {
        return getDadosInstituicao();
    }
}

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
    
    public Object[] copyList()
    {
        return copyArray(0, size);
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
     * Mostra todos os elementos da lista a partir de um indice inicial
     * 
     * @param start indice do primeiro elemento a se mostrar
     */
    
    public void printList(int start)
    {
       printList(start, size() - start, AxellIO.LINE_SEPARATOR);
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

/* ----------------------------------------- CLASSES DO EXERCICIO ----------------------------------------- */

/*

PUC Minas - Ciencia da Computacao     Nome: HeapsortParcial

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 01/10/2018

*/

public class HeapsortParcial
{
    public static int[] getInstitutionsCodes(Lista institutionsList)
    {
        int size = institutionsList.size();
        int[] institutionsCodes = new int[size];
        
        for (int i = 1; i < size; i++)
        {
            institutionsCodes[i] = Instituicao.convertToInstituicao( institutionsList.get(i) ).getCodigoMunicipio();
        }
        
        return institutionsCodes;
    }
    
    /**
     * Recebe dois indices de dois elementos e coloca um elemento no indice do outro.
     * 
     * @param firstIndex indice do primeiro elemento
     * @param secondIndex indice do segundo elemento
     * @param intArray arranjo a ser acessado
     */
    
    public static void swap(int firstIndex, int secondIndex, int[] intArray)
    {
        int copy = intArray[firstIndex];
        
        intArray[firstIndex] = intArray[secondIndex];
        intArray[secondIndex] = copy;
    }
    
    /**
     * Recebe o indice de um no' da arvore de heap e faz a troca desse
     * no' com o seu no' pai.
     * 
     * @param index indice do no' que deseja-se trocar com o no' pai
     * @param intArray arranjo no formato de heap
     */
    
    public static void swapMeWithMyFather(int index, int[] intArray)
    {
        swap(index, index / 2, intArray);
    }
    
    /**
     * Recebe o indice de um no' e retorno o valor do no' pai.
     * 
     * @param index indice do no'
     * @param intArray arranjo no formato de heap
     * 
     * @return o valor do no' pai
     */
    
    public static int getFather(int index, int[] intArray)
    {
        int father = 0;
        
        if (index > 1)
        {
            father = intArray[index / 2];
        }
        
        return father;
    }
    
    /**
     * Recebe o indice do elemento a ser inserido no heap. E entao
     * o insere no heap.
     * 
     * @param index indice onde deseja-se inseri-lo
     * @param intArray arranjo no formato de heap
     * @param institutionsList lista no formato de heap
     * 
     * @return numero de comparacoes entre elementos da lista concatenado com um
     * '\t' concatenado com o numero de movimentacoes entre elementos da lista
     */
    
    public static String insertOnHeap(int index, int[] intArray, Lista institutionsList)
    {
        int numberOfComparisons = 0;
        int numberOfMovimentations = 0;
        int currentNode;
        int currentFather;
        String siglaOfInstitutionOnNode;
        String siglaOfInstitutionOnFatherNode;
        
        for (int i = index; i > 1; i /= 2)
        {
            numberOfMovimentations++;
            currentNode = intArray[i];
            currentFather = getFather(i, intArray);
            
            siglaOfInstitutionOnNode = Instituicao.convertToInstituicao(institutionsList.get(i)).getSigla();
            siglaOfInstitutionOnFatherNode = Instituicao.convertToInstituicao(institutionsList.get(i / 2)).getSigla();
            
            numberOfComparisons++;
            if (currentNode < currentFather ||
                (currentNode == currentFather &&
                siglaOfInstitutionOnNode != null &&
                siglaOfInstitutionOnFatherNode != null &&
                siglaOfInstitutionOnNode.compareTo(siglaOfInstitutionOnFatherNode)
                < 0))
            {
                swapMeWithMyFather(i, intArray);
                
                // estou considerando so' as movimentacoes das instituicoes
                numberOfMovimentations += 3;
                institutionsList.swap(i, i / 2);
            }
        }
        
        return numberOfComparisons + "\t" + numberOfMovimentations;
    }
    
    public static boolean hasChild(int index, int currentHeapSize)
    {
        return index * 2 <= currentHeapSize;
    }
    
    public static boolean hasSecondChild(int index, int currentHeapSize)
    {
        return index * 2 + 1 <= currentHeapSize;
    }
    
    /**
     * Recebe o indice de um elemento do heap e retorna o indice de
     * seu maior filho.
     * 
     * @param father indice do elemento
     * @param intArray arranjo em formato de heap
     * @param currentHeapSize quantos elementos do arranjo estao ordenados
     * a partir do indice 1
     * @param institutionsList lista de instituicoes no formato de heap
     * 
     * @return indice do maior filho do pai
     */
    
    public static int getIndexOfSmallestChild(int father, int[] intArray, int currentHeapSize, Lista institutionsList)
    {
        int greatestChild = -1;
        
        if (hasChild(father, currentHeapSize))
        {
            greatestChild = father * 2;
            
            if (hasSecondChild(father, currentHeapSize))
            {
                int secondChild = father * 2 + 1;
                
                String siglaOfInstitutionBrother1 = Instituicao.convertToInstituicao(institutionsList.get(greatestChild)).getSigla();
                String siglaOfInstitutionBrother2 = Instituicao.convertToInstituicao(institutionsList.get(secondChild)).getSigla();
                
                if (intArray[secondChild] < intArray[greatestChild] ||
                    (intArray[secondChild] == intArray[greatestChild] &&
                    siglaOfInstitutionBrother1 != null &&
                    siglaOfInstitutionBrother2 != null &&
                    siglaOfInstitutionBrother1.compareTo(siglaOfInstitutionBrother2)
                    > 0)
                   )
                {
                    greatestChild = secondChild;
                }
            }
        }
        
        return greatestChild;
    }
    
    /**
     * Recebe o indice de um elemento do heap e o remove da estrutura. Depois,
     * rearranja a estrutura do heap.
     * 
     * @param index indice do elemento
     * @param intArray arranjo em formato de heap
     * @param currentHeapSize quantos elementos do arranjo estao ordenados
     * a partir do indice 1
     * @param institutionsList lista de instituicoes no formato de heap
     * 
     * @return quantas comparacoes entre elementos da lista foram feitas,
     * concatenadas com um '\t' concatenado com a quantidade de movimentacoes
     * entre elementos do arranjo
     */
    
    public static String removeFromHeap(int index, int[] intArray, int currentHeapSize, Lista institutionsList)
    {
        int numberOfComparisons = 0;
        int numberOfMovimentations = 0;
        int smallestChild;
        String siglaOfInstitutionOnNode = null;
        String siglaOfInstitutionOnChildNode = null;
        
        // troca o elemento no indice com a ultima folha
        swap(index, currentHeapSize, intArray);
        
        // estou considerando apenas as movimentacoes da lista de instituicoes
        numberOfMovimentations += 3;
        institutionsList.swap(index, currentHeapSize--); // troca a raiz com a ultima folha
        
        // obtem o indice do menor filho
        smallestChild = getIndexOfSmallestChild(index, intArray, currentHeapSize, institutionsList);
        
        if (smallestChild != -1)
        {
            siglaOfInstitutionOnNode = Instituicao.convertToInstituicao(institutionsList.get(index)).getSigla();
            siglaOfInstitutionOnChildNode = Instituicao.convertToInstituicao(institutionsList.get(smallestChild)).getSigla();
        }
        
        int i = index;
        
        numberOfComparisons++;
        // checa se existe o filho e se ele e' menor que o pai
        while (smallestChild != -1 &&
                ( intArray[smallestChild] < intArray[i] ||
                    (intArray[smallestChild] == intArray[i] &&
                    siglaOfInstitutionOnNode != null &&
                    siglaOfInstitutionOnChildNode != null &&
                    siglaOfInstitutionOnNode.compareTo(siglaOfInstitutionOnChildNode)
                    > 0)
                )
              )
        {
            swap(i, smallestChild, intArray); // troca pai com filho
            
            numberOfMovimentations += 3; // estou considerando apenas as movimentacoes da lista
            institutionsList.swap(i, smallestChild); // troca pai com filho
            
            // o indice do pai antigo passa a ser o indice do filho antigo
            i = smallestChild;
            
            // obtem o indice do menor filho
            smallestChild = getIndexOfSmallestChild(i, intArray, currentHeapSize, institutionsList);
            numberOfComparisons++;
            
            if (smallestChild != -1)
            {
                siglaOfInstitutionOnNode = Instituicao.convertToInstituicao(institutionsList.get(i)).getSigla();
                siglaOfInstitutionOnChildNode = Instituicao.convertToInstituicao(institutionsList.get(smallestChild)).getSigla();
            }
        }
        
        return numberOfComparisons + "\t" + numberOfMovimentations;
    }
    
    /**
     * Ordena uma lista de instituicoes pelos codigos de seus municipios.
     * 
     * @param institutionsList lista a ser ordenada
     * 
     * @return quantidade de comparacoes entre elementos da lista concatenada
     * com um '\t' concatenado com a quantidade de movimentacoes entre
     * elementos da lista
     */
    
    public static String ordenateInstitutionsByCode(Lista institutionsList, int k)
    {
        int size = institutionsList.size();
        int[] codesArray = getInstitutionsCodes(institutionsList);
        String[] heapProcessResult;
        int numberOfComparisons = 0;
        int numberOfMovimentations = 0;
        int initialHeapSize = size - 1;
        int currentHeapSize = initialHeapSize;
        
        for (int i = 2; i < size; i++)
        {
            heapProcessResult = insertOnHeap(i, codesArray, institutionsList).split("\t");
            numberOfComparisons += AxellIO.getint(heapProcessResult[0]);
            numberOfMovimentations += AxellIO.getint(heapProcessResult[1]);
        }
        
        while (initialHeapSize - currentHeapSize < k)
        {
            heapProcessResult = removeFromHeap(1, codesArray, currentHeapSize--, institutionsList).split("\t");
            numberOfComparisons += AxellIO.getint(heapProcessResult[0]);
            numberOfMovimentations += AxellIO.getint(heapProcessResult[1]);
        }
        
        return numberOfComparisons + "\t" + numberOfMovimentations;
    }
    
    public static void runToLine(int lineNumber)
    {
        int currentLineNumber = 1;
        
        while (currentLineNumber++ < lineNumber)
        {
            AxellIO.readLine("");
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        long start = System.currentTimeMillis();
        
        Lista listaDeInstituicoes = new Lista(131);
        // o indice 0 da lista de instituicoes sera' uma instituicao vazia
        listaDeInstituicoes.add(new Instituicao());
        
        FileInputStream censo = new FileInputStream("/tmp/censo.dat"); // abre o arquivo censo.dat
        
        Integer lineNumber = AxellIO.readInt(""); // le, da entrada padrao, a linha da instituicao
        
        AxellIO.saveInputStreamData(); // guarda o resto dos dados da entrada padrao
        
        while (lineNumber != null && lineNumber != 0)
        {
            AxellIO.setInputStream(censo); // faz o redirecionamento de entrada para o arquivo censo.dat
            
            runToLine(lineNumber + 1); // vai ate a linha sucessora da linha informada na entrada padrao
            
            // cria uma instituicao com os dados dessa linha e adiciona-a 'a lista de instituicoes.
            listaDeInstituicoes.add( new Instituicao(AxellIO.readLine("")) );
            
            // faz o redirecionamento de entrada para o resto dos dados da entrada padrao
            AxellIO.recoverLastInputStreamData();
            
            lineNumber = AxellIO.readInt(""); // le, da entrada padrao, a linha da instituicao
            
            AxellIO.saveInputStreamData(); // guarda o resto dos dados da entrada padrao
            
            censo = new FileInputStream("/tmp/censo.dat"); // reabre o arquivo que sera' a entrada padrao
        }
        
        AxellIO.recoverLastInputStreamData();
        
        String[] ordenationResult;
        int numberOfComparisons = 0;
        int numberOfMovimentations = 0;
        int listSize = listaDeInstituicoes.size();
        int k = 10;
        
        ordenationResult = ordenateInstitutionsByCode(listaDeInstituicoes, k).split("\t");
        
        numberOfComparisons = AxellIO.getint(ordenationResult[0]);
        numberOfMovimentations = AxellIO.getint(ordenationResult[1]);
        
        for (int i = 0; i < k; i++)
        {
            AxellIO.println( listaDeInstituicoes.get(listSize - 1 - i).toString() );
        }
        
        AxellIO.setPrintStream("matrícula_heapsort.txt");
        
        AxellIO.print("631822\t" + numberOfComparisons + "\t" + numberOfMovimentations + "\t" + ( System.currentTimeMillis() - start ));
    }
}

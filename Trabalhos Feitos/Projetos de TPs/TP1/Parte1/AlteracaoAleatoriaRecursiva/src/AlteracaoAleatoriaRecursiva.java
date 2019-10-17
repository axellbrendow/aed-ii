import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;

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
        String message = "Sem mensagem de erro";
        
        switch (errorCode)
        {
            case 0:
                message = "Nao ha' erro";
                break;
        }
        
        return message;
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
     * Remove espacos em branco do inicio e do fim da cadeia da classe
     * @return novo objeto MyString com os espacos removidos
     */
    
    public MyString trimStartAndEnd()
    {
        // retorna uma cadeia vazia se a cadeia da classe tiver apenas espacos
        if (AxellIO.getCharTimes(' ', str) == length()) return new MyString();
        
        int numberOfSpacesOnStart = 0, numberOfSpacesOnEnd = 0;
        boolean existSpace = true;
        
        if (str.startsWith(" ")) // checa se a cadeia comeca com no minimo um espaco em branco
        {
            numberOfSpacesOnStart++; // aumenta a quantidade de espacos no inicio
            
            // percorre a cadeia da classe a partir do segundo elemento ate' contar
            // todos os espacos em branco no inicio da cadeia
            for (int i = 1; i < length() && existSpace; i++)
            {
                // checa se o caractere atual e' um espaco em branco
                if (existSpace = getCharAt(i) == ' ') // se for,
                {
                    numberOfSpacesOnStart++; // aumenta a quantidade de espacos no inicio
                }
            }
        }
        
        existSpace = true; // reseta a variavel existSpace
        
        if (str.endsWith(" ")) // checa se a cadeia termina com no minimo um espaco em branco
        {
            numberOfSpacesOnEnd++; // aumenta a quantidade de espacos no final
            
            // percorre a cadeia da classe da direta para a esquerda a partir do
            // penultimo elemento ate' contar todos os espacos em branco no final da cadeia
            for (int i = length() - 2; i > 0 && existSpace; i--)
            {
                // checa se o caractere atual e' um espaco em branco
                if (existSpace = getCharAt(i) == ' ') // se for,
                {
                    numberOfSpacesOnEnd++; // aumenta a quantidade de espacos no final
                }
            }
        }
        
        return substring(numberOfSpacesOnStart, length() - numberOfSpacesOnStart - numberOfSpacesOnEnd);
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
    
    public String replace(char currentChar, char newChar, int charIndex, int strLength)
    {
        String replacedString = "";
        
        if (charIndex < strLength)
        {
            char charAtThisIndex = getCharAt(charIndex);
            
            if (charAtThisIndex == currentChar)
            {
                replacedString += newChar;
            }
            
            else
            {
                replacedString += charAtThisIndex;
            }
            
            replacedString += replace(currentChar, newChar, charIndex + 1, strLength);
        }
        
        return replacedString;
    }
    
    private String compactData(char delimiter)
    {
        char[] strCharArray = str.toCharArray();
        String compactedData = "";
        boolean nextStrTime = false;
        
        for (int i = 0; i < strCharArray.length; i++)
        {
            if (nextStrTime)
            {
                compactedData += delimiter;
                nextStrTime = false;
            }
            
            else if (strCharArray[i] != delimiter)
            {
                compactedData += strCharArray[i];
                
                if (!limitWillBreak(i + 1, 1, strCharArray) && strCharArray[i + 1] == delimiter)
                {
                    nextStrTime = true;
                }
            }
        }
        
        if (compactedData.isEmpty()) return null;
        
        else if (compactedData.charAt(compactedData.length() - 1) == delimiter)
        {
            return compactedData.substring(0, compactedData.length() - 1);
        }
        
        else return compactedData;
    }
    
    public String[] split()
    {
        char delimiter = ' ';
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

Versao:  3.0                          Data: 16/08/2018

*/

/* ----------------------------------------- CLASSE AUXILIAR ----------------------------------------- */

class AxellIO
{
    public static final String LINE_SEPARATOR =
            System.getProperty("java.vendor.url").equals("http://www.android.com/")
            ? "\n" + (char) 65535 : System.lineSeparator();

    public static final String LAST_LINE_CHAR = LINE_SEPARATOR.substring(LINE_SEPARATOR.length() - 1);
    
    private static String charset = "ISO-8859-1";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
    
    public static void setCharset(String charset)
    {
        AxellIO.charset = charset;
        
        reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName(AxellIO.charset)));
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

        for (int i = 0; i < str.length(); i++)
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
        
        boolean isValid = isOnInterval(str.charAt(0), accept) ||
                ((accept == 'd' || accept == 'f') && !str.equals("-"));
        
        for (int i = 1; i < str.length() && isValid; i++)
        {
            isValid = isOnInterval(str.charAt(i), accept);
        }
        
        return isValid;
    }
    
    public static Integer getInt(String str)
    {
        return !isSpecificString(str, 'd') ? null : Integer.valueOf(str);
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
    
    /**
     * Limpa a entrada padrao (System.in)
     */
    
    public static void cleanSystemIn()
    {
        while (!LAST_LINE_CHAR.equals(read() + ""));
    }
    
    /**
     * Limpa ou nao a entrada padrao de acordo com a ultima entrada
     * @param lastInput ultimo caractere lido
     */
    
    public static void cleanSystemIn(char lastInput)
    {
        // so' limpa se a ultima leitura nao for o ultimo caractere que marca a quebra de linha
        if (!LAST_LINE_CHAR.equals(lastInput + ""))
        {
            cleanSystemIn();
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
    
    // ----------- FUNCOES DE ENTRADA DE DADOS -----------
    
    /**
     * Le um caractere da entrada padrao
     * @return retorna o caractere lido
     */
    
    public static char read()
    {
        try
        {
            return (char) reader.read();
        }
        
        catch (IOException ex)
        {
            println("Nao foi possivel ler da entrada padrao");
            
            return 0;
        }
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
        
        cleanSystemIn(input);
        
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
        
        cleanSystemIn(input);
        
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
        return data != null && data.charAt(0) != '.' && getCharTimes('.', data) <= 1;
    }
    
    // ----------- FUNCOES DE SAIDA DE DADOS -----------
    
    public static void print(String data)
    {
        try
        {
            new PrintStream(System.out, true, charset).print(data);
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
        cleanSystemIn(read());
    }
    
    public static void pause(String text)
    {
        println(text);
        pause();
    }
}

/* ----------------------------------------- CLASSE DO EXERCICIO ----------------------------------------- */

/*

PUC Minas - Ciência da Computação     Nome: AlteracaoAleatoriaRecursiva

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 28/08/2018

*/

public class AlteracaoAleatoriaRecursiva
{
    static Random random = new Random();
    
    public static char gerarMinusculaAleatoria()
    {
        return (char) ('a' + ( Math.abs( random.nextInt() ) % 26 ));
    }
    
    public static String trocarMinusculaAleatoriaPorMinusculaAleatoria(MyString myStr)
    {
        char randomCharToFind = gerarMinusculaAleatoria();
        char randomCharToReplace = gerarMinusculaAleatoria();
        
        return myStr.replace(randomCharToFind, randomCharToReplace, 0, myStr.length());
    }
    
    public static void main(String[] args)
    {
        random.setSeed(4);
        
        MyString input = new MyString(AxellIO.readLine(""));
        
        while(input != null && !input.equals("FIM"))
        {
            AxellIO.println(trocarMinusculaAleatoriaPorMinusculaAleatoria(input));
            
            input = new MyString(AxellIO.readLine(""));
        }
    }
    
}

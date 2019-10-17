import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.io.*;

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
     * Cria uma subcadeia de caracteres a partir de um recorte da cadeia da classe
     * @param start indice de comeco do recorte
     * @return uma subcadeia da cadeia da classe (pedaco da cadeia da classe) 
     */
    
    public MyString substring(int start)
    {
        return substring(start, length() - start);
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
    
    public String replace(char currentChar, char newChar)
    {
        String replacedString = "";
        
        for (char c : str.toCharArray())
        {
            if (c == currentChar)
            {
                replacedString += newChar;
            }
            
            else
            {
                replacedString += c;
            }
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

Versao:  3.5                          Data: 26/08/2018

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
		
        boolean isValid = isOnInterval(firstChar, accept) ||
                ((accept == 'd' || accept == 'f') && firstChar == '-' && str.length() > 1);
        
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
        return limitOverflow(start, numberOfElements, objectArray.length);
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

/* ----------------------------------------- CLASSES DO EXERCICIO ----------------------------------------- */

/*

PUC Minas - Ciencia da Computacao     Nome: Lista

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 23/08/2018

*/

class Lista
{
    // ----------------------- ATRIBUTOS DA CLASSE
    
    private Object[] list;
    
    // ----------------------- CONSTRUTORES DA CLASSE
    
    public Lista()
    {
        this(0);
    }
    
    public Lista(int size)
    {
        if (size < 0)
        {
            //AxellIO.println("[Lista]: Parametro size < 0 no construtor");
        }
        
        else
        {
            list = new Object[size];
        }
    }
    
    public Lista(Object[] list)
    {
        if (list == null)
        {
            //AxellIO.println("[Lista]: Parametro list nulo no construtor");
        }
        
        else
        {
            this.list = list;
        }
    }
    
    // ----------------------- METODOS DA CLASSE
    
    public int size()
    {
        return list.length;
    }
    
    public Object get(int index)
    {
        Object result;
        
        if (AxellIO.limitOverflow(index, 1, list) != 0)
        {
            //AxellIO.println("[Lista]: Parametro index invalido na funcao get()");
            
            result = null;
        }
        
        else
        {
            result = list[index];
        }
        
        return result;
    }
    
    public void set(int index, Object element)
    {
        if (AxellIO.limitOverflow(index, 1, list) != 0)
        {
            //AxellIO.println("[Lista]: Parametro index invalido na funcao set()");
        }
        
        else
        {
            list[index] = element;
        }
    }
    
    /**
     * Remove determinado elemento da lista
     * 
     * @param index indice do elemento
     */
    
    public void remove(int index)
    {
        int size = size();
        
        if (AxellIO.limitOverflow(index, 1, list) != 0) // checa se o index e' invalido
        {
            //AxellIO.println("[Lista]: Parametro index invalido na funcao remove()");
        }
        
        else if (index == 0) // checa se deseja-se remover o primeiro elemento
        {
            if (size == 1) // se o tamanho da lista for 1,
            {
                list = new String[1]; // cria uma nova lista vazia
            }
            
            else // se for maior que 1,
            {
                // cria uma nova lista com todos os elementos da atual exceto o primeiro
                list = copyArray(index + 1, size- 1, list);
            }
        }
        
        else if (index == list.length - 1) // checa se deseja-se remover o ultimo elemento
        {
            // cria uma nova lista com todos os elementos da atual exceto o ultimo
            list = copyArray(0, size - 1, list);
        }
        
        else
        {
            // cria um arranjo com 2 espacos, sendo que, em cada espaco,
            // guardarei um arranjo que representara' um pedaco da lista
            Object[][] listPieces = new Object[2][1];
            
            // preenche o primeiro espaco com o pedaco que vai do indice 0
            // ate' o indice anterior ao objeto que se deseja remover
            listPieces[0] = copyArray(0, index, list);
            
            // preenche o segundo espaco com o pedaco que vai do indice posterior
            // ao objeto que se deseja remover ate' o ultimo elemento
            listPieces[1] = copyArray(index + 1, size - listPieces[0].length - 1, list);
            
            // diminui o tamanho da lista em uma unidade e readiciona os objetos antigos
            resizeList(size - 1, listPieces);
        }
    }
    
    /**
     * Adiciona um novo elemento 'a lista
     * 
     * @param element elemento a ser adicionado
     */
    
    public void add(Object element)
    {
        int listSize = size();
        
        resizeList(listSize + 1); // cria-se mais um espaco na lista

        set(listSize, element); // adiciona o novo elemento na ultima posicao da lista
    }
    
    public int indexOf(Object element)
    {
        int index = -1, size = size();
        boolean found = false;
        
        for (int i = 0; i < size && !found; i++)
        {
            found = element.equals(get(i));
        }
        
        return index;
    }
    
    /**
     * A partir de um indice inicial, substitui os elementos
     * da lista por outros elementos de uma nova lista
     * 
     * @param start indice inicial
     * @param newList nova lista
     */
    
    public void changeList(int start, Object[] newList)
    {
        if (newList == null)
        {
            //AxellIO.println("[Lista]: Parametro newList nulo na funcao changeList()");
        }
        
        else if (AxellIO.limitOverflow(start, newList.length, list) != 0)
        {
            
        }
        
        else
        {
            for (int i = 0; i < newList.length; i++)
            {
                set(start + i, newList[i]);
            }
        }

    }
    
    public void setAllElementsTo(Object value)
    {
        int listSize = size();
        
        for (int i = 0; i < listSize; i++)
        {
            set(i, value);
        }
    }
    
    /**
     * Muda o tamanho e recoloca elementos na lista da classe
     * 
     * @param size novo tamanho da lista
     * @param listPieces pedacos de lista a serem adicionados como se fosse um append
     */
    
    private void resizeList(int size, Object[][] listPieces)
    {
        if (size < 1)
        {
            //AxellIO.println("[Lista]: Parametro size < 1 na funcao resizeList()");
        }
        
        else if (listPieces == null || listPieces.length == 0)
        {
            //AxellIO.println("[Lista]: Parametro listPieces nulo ou vazio na funcao resizeList()");
        }
        
        else if (size == size())
        {
            //AxellIO.println("[Lista]: Tentativa de criacao de uma nova lista de mesmo tamanho da atual na funcao resizeList()");
        }
        
        else
        {
            int totalSize = 0;

            for (Object[] piece : listPieces) // percorre cada pedaco de lista
            {
                totalSize += piece.length; // soma os tamanhos de cada pedaco
            }

            if (totalSize > size)
            {
                //AxellIO.println("[Lista]: O tamanho da concatenacao dos pedacos de lista ultrapassa o tamanho informado no primeiro parametro da funcao resizeList()");
            }
            
            else
            {
                // inicializa a lista com o novo tamanho
                list = new Object[size];

                // guardara' o indice livre da lista
                int freeIndexInTheList = 0;

                for (Object[] piece : listPieces) // percorre os pedacos de lista
                {
                    changeList(freeIndexInTheList, piece); // adiciona cada pedaco 'a lista da classe

                    // empurra o indice livre de acordo com o tamanho do pedaco adicionado
                    freeIndexInTheList += piece.length;
                }
            }
        }
    }
    
    /**
     * Muda o tamanho da lista de telefones da classe perdendo o mínimo
     * possivel de telefones
     * 
     * @param newSize novo tamanho da lista
     */
    
    private void resizeList(int newSize)
    {
        if (newSize < 1)
        {
            //AxellIO.println("[Lista]: Parametro size < 1 na funcao resizeList()");
        }
        
        else if (newSize == size())
        {
            //AxellIO.println("[Lista]: Tentativa de criacao de uma nova lista de mesmo tamanho da atual na funcao resizeList()");
        }
        
        else
        {
            int oldListSize = size(), appropiateSize = oldListSize;
            
            Object[] oldList = copyArray(0, oldListSize, list); // cria uma copia da lista atual

            list = new Object[newSize]; // cria-se uma nova lista com o tamanho desejado
            
            // se o novo tamanho da lista for menor que o antigo, so' sera' possivel
            // recuperar alguns elementos da lista antiga
            if (newSize < oldListSize)
            {
                appropiateSize = newSize;
            }
            
            // percorre a lista atual copiando o maximo de elementos que for possivel da lista antiga
            for (int i = 0; i < appropiateSize; i++)
            {
                set(i, oldList[i]); // adiciona elementos da antiga lista 'a atual
            }
        }
    }
    
    /**
     * Partindo de um indice incial, copia certa quantidade de elementos
     * de um arranjo de objetos
     * 
     * @param start indice inicial
     * @param numberOfElements quantidade de elementos
     * @param array arranjo de objetos a ser copiado
     * 
     * @return um novo arranjo com a quantidade de objetos desejada
     */
    
    private Object[] copyArray(int start, int numberOfElements, Object[] array)
    {
        Object[] arrayCopy;
        
        if (array == null)
        {
            //AxellIO.println("[Lista]: Parametro array nulo na funcao copyArray()");
            
            arrayCopy = null;
        }
        
        else if (AxellIO.limitOverflow(start, numberOfElements, array) != 0)
        {
            arrayCopy = null;
        }
        
        else
        {
            arrayCopy = new Object[numberOfElements];

            for (int i = 0; i < numberOfElements; i++)
            {
                arrayCopy[i] = array[start + i];
            }
        }
        
        return arrayCopy;
    }
}

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
        
        if (AxellIO.limitOverflow(pairIndex, 1, size()) != 0)
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

class OperationCompiler
{
    Tree operationsTree;
    int numberOfTreeLevels;
    int currentLevelIndex;
	boolean newLevelForNewOperation;
    int lastOperandIndex;
    LogicOperation currentOperation;
    MovementStatus movementStatus;
    
    public enum MovementStatus
    {
        STOPPED,
        GOING_UP,
        GOING_DOWN,
		FINISHED
    }
    
    public OperationCompiler()
    {
        this( new Tree() );
        
        operationsTree.addLevel(new TreeLevel()); // cria o primeiro nivel
		
		this.newLevelForNewOperation = false;
    }
    
    public OperationCompiler(Tree operationsTree)
    {
        if (operationsTree == null)
        {
            //AxellIO.println("[OperationCompiler]: Parametro operationsTree nulo no construtor");
        }
        
        else
        {
            this.operationsTree = operationsTree;
            updateNumberOfTreeLevels();
            
            currentLevelIndex = 0;
            lastOperandIndex = 0;
            
            movementStatus = MovementStatus.STOPPED;
        }
    }
    
    private MovementStatus getMovementStatus()
    {
        return movementStatus;
    }
    
    private void setMovementStatus(MovementStatus movementStatus)
    {
        this.movementStatus = movementStatus;
    }
    
	private void setNewLevelForNewOperation(boolean newLevelForNewOperation)
	{
		this.newLevelForNewOperation = newLevelForNewOperation;
	}
	
    private void updateNumberOfTreeLevels()
    {
        numberOfTreeLevels = operationsTree.getNumberOfLevels();
    }
    
    private void setCurrentLevelIndex(int currentLevelIndex)
    {
        if (currentLevelIndex < 0 || currentLevelIndex >= numberOfTreeLevels)
        {
            //AxellIO.println("[OperationCompiler]: Parametro currentLevelIndex invalido na funcao setCurrentLevelIndex() - (" + currentLevelIndex + ")");
        }
        
        else
        {
            this.currentLevelIndex = currentLevelIndex;
        }
    }
    
    public void setCurrentOperation(LogicOperation currentOperation)
    {
        if (currentOperation == null)
        {
            //AxellIO.println("[OperationCompiler]: Parametro currentOperation nulo na funcao setCurrentOperation()");
        }
        
        else
        {
            this.currentOperation = currentOperation;
        }
    }
    
    public LogicOperation getLastOperationOnCurrentLevel()
    {
		LogicOperation lastOperationOnCurrentLevel;
		TreeLevel currentLevel = getCurrentLevel();
		
        if (currentLevel == null)
        {
            //AxellIO.println("[OperationCompiler]: Nao existe um nivel na arvore com o indice atual do compilador - funcao getLastOperationOnCurrentLevel()");
			
			lastOperationOnCurrentLevel = null;
        }
        
        else
        {
            lastOperationOnCurrentLevel = (LogicOperation) currentLevel.getLastBrother();
        }
		
		return lastOperationOnCurrentLevel;
    }
    
	public TreeLevel getCurrentLevel()
	{
		return operationsTree.getLevel(currentLevelIndex);
	}
	
	public boolean treeHasNextLevel()
	{
		return currentLevelIndex < numberOfTreeLevels - 1;
	}
	
	public boolean compilerHasEnded()
	{
		return getMovementStatus() == MovementStatus.FINISHED;
	}
	
    public int moveCurrentLevel(int movimentation)
    {
        int newLevel;
        boolean backward = movimentation < 0; // movimentacao negativa representara' voltar na arvore (subir um level)
        
        int absMovimentation = backward ? -movimentation : movimentation;
        
		int levelsLimitOverflow = AxellIO.limitOverflow(currentLevelIndex + ( backward ? -1 : 1 ), absMovimentation, numberOfTreeLevels, backward);
		
		if (levelsLimitOverflow == -1)
		{
			setMovementStatus(MovementStatus.FINISHED);
			
			newLevel = currentLevelIndex;
		}
		
        else if (levelsLimitOverflow != 0)
        {
            //AxellIO.println("[OperationCompiler]: Parametro movimentation maior que o permitido na funcao moveCurrentLevel() - (movimentation = " + movimentation + ")");

            newLevel = -1;
        }

        else
        {
            newLevel = currentLevelIndex + movimentation;

            setCurrentLevelIndex(newLevel);
        }
        
        return newLevel;
    }
    
    public void changeLevel()
    {
        if (movementStatus == MovementStatus.GOING_DOWN) // se estiver descendo na arvore,
        {
            if (!treeHasNextLevel()) // checa se nao existe um proximo nivel na arvore
            {
                operationsTree.createLevel(); // cria um novo nivel
                updateNumberOfTreeLevels(); // atualiza o tamanho da arvore
            }
            
            moveCurrentLevel(1); // desce um nivel
        }
        
        else // se estiver subindo,
        {
			moveCurrentLevel(-1); // sobe um nivel
        }
    }
    
    public void addLogicOperation(String logicOperator)
    {
        if (logicOperator == null)
        {
            //AxellIO.println("[OperationCompiler]: Parametro logicOperation nulo na funcao addOperation()");
        }
        
        else
        {
            LogicOperation logicOperation;
            
			setMovementStatus(MovementStatus.GOING_DOWN); // movimento para descer na arvore
			
            if (currentLevelIndex == 0 && !newLevelForNewOperation) // no primeiro nivel da arvore, as operacoes nao tem conexoes com outras
            {
                logicOperation = new LogicOperation(logicOperator);
            }
            
            else // nos niveis mais abaixo as operacoes terao conexoes com as do nivel anterior
            {
                // cria a operacao logica informada no parametro conectando-a 'a operacao atual
                logicOperation = new LogicOperation(logicOperator, currentOperation, currentOperation.getNumberOfOperands());
                
                // avisa 'a operacao atual que uma nova operacao foi-lhe conectada
                currentOperation.addOperationConnectedToMyOperand(logicOperation);
            }
            
			if (newLevelForNewOperation)
			{
				changeLevel();
				
				setNewLevelForNewOperation(false); // avisa o compilador que, por enquanto, nao sera' necessario criar novos niveis para novas operacoes
			}
			
            operationsTree.getLevel(currentLevelIndex).addBrother(logicOperation); // adiciona a nova operacao ao nivel atual da arvore
            
            setCurrentOperation(logicOperation); // e a operacao que acabou de ser adicionada 'a arvore passa a ser a atual
        }
    }
    
    public Tree mountTree(MyString input)
    {
        String downLevelToken = "(";
        String upLevelToken = ")";
        //String nextOperandToken = ",";
        //String[] stringsToIgnore = { " ", "\t", AxellIO.LINE_SEPARATOR };
        String andOperator = "and";
        String orOperator = "or";
        String notOperator = "not";
        String firstParameter = "A";
        String secondParameter = "B";
        String thirdParameter = "C";
        int numberOfParameters = AxellIO.getInt("" + input.getCharAt(0));
        String[] parameters = new String[numberOfParameters];
        boolean error = false;
        
        switch(numberOfParameters)
        {
            case 1:
                parameters[0] = "" + input.getCharAt(2);
                break;
            
            case 2:
                parameters[0] = "" + input.getCharAt(2);
                parameters[1] = "" + input.getCharAt(4);
                break;
            
            case 3:
                parameters[0] = "" + input.getCharAt(2);
                parameters[1] = "" + input.getCharAt(4);
                parameters[2] = "" + input.getCharAt(6);
                break;
            
            default:
                error = true;
                break;
        }
        
        if (error)
        {
            //AxellIO.println("[OperationCompiler]: quantidade de parametros na entrada e' invalida");
        }
        
        else
        {
            MyString expression = input.substring(2 + numberOfParameters * 2); // retira os parametros da entrada e deixa apenas a expressao logica
            int expressionLength = expression.length();
            
            int newIndex; // a cada iteracao, indicara' a nova posicao do cursor na expressao
            
            for (int i = 0; i < expressionLength; i = i)
            {
				if ( ( newIndex = expression.hasSubstringOnIndex(i, andOperator) ) != -1 ) // checa se, a partir do indice i, existe o operador "and"
                {
                    addLogicOperation(andOperator);
                }
                
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, orOperator) ) != -1 ) // checa se, a partir do indice i, existe o operador "or"
                {
                    addLogicOperation(orOperator);
                }
                
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, notOperator) ) != -1 ) // checa se, a partir do indice i, existe o operador "not"
                {
                    addLogicOperation(notOperator);
                }
				
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, downLevelToken) ) != -1 )
                {
                    setMovementStatus(MovementStatus.GOING_DOWN); // movimento para descer na arvore
					
					setNewLevelForNewOperation(true); // avisa ao compilador que se uma nova operacao for criada, ela deve ser criada em um novo nivel
                }
                
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, upLevelToken) ) != -1 )
                {
                    currentOperation.setReceivingOperands(false); // fecha a operacao atual
                    currentOperation.doOperation(); // realiza-a
                    
                    setMovementStatus(MovementStatus.GOING_UP); // movimento para voltar na arvore
                    
					setNewLevelForNewOperation(true); // avisa ao compilador que se uma nova operacao for criada, ela deve ser criada em um novo nivel
					
                    changeLevel(); // da prosseguimento ao movimento
					
					if (compilerHasEnded()) // ao chegar no "nivel" -1 da arvore, o compilador terminara'
					{
						break;
					}
                    
                    // resgata a ultima operacao trabalhada nesse novo nivel
                    setCurrentOperation(getLastOperationOnCurrentLevel());
                }
                /*
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, nextOperandToken) ) != -1 )
                {
                    //
                }
                */
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, firstParameter) ) != -1 )
                {
                    currentOperation.addOperand(parameters[0]);
                }
                
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, secondParameter) ) != -1 )
                {
                    currentOperation.addOperand(parameters[1]);
                }
                
                else if ( ( newIndex = expression.hasSubstringOnIndex(i, thirdParameter) ) != -1 )
                {
                    currentOperation.addOperand(parameters[2]);
                }
                
                else
                {
                    newIndex = i + 1; // passa para o proximo caractere
                }
                
				//if (currentOperation != null) AxellIO.println("[" + expression.getCharAt(i) + "]: " + currentOperation + " | levelIndex: " + currentLevelIndex);
				
                i = newIndex; // atualiza o cursor da expressao
            }
        }
        
        return this.operationsTree;
    }
}

class Operation
{
    protected Lista operands;
    protected Dictionary supportedOperators = new Dictionary("NONE", 0);
    protected Pair operator; // operadores serao pares em que o primeiro elemento e' o simbolo do operador e o segundo a quantidade de operandos necessarios para o operador. O -1 indicara' quantidade indefinida.
    protected Object result;
    protected Operation connectedOperation;
    protected int connectedOperand = -1;
    protected Lista connectedsToMyOperands;
    protected boolean receivingOperands;
    protected boolean isSolved;
    
    public Operation()
    {
        this.operands = new Lista();
        this.receivingOperands = true;
        this.connectedsToMyOperands = new Lista();
    }
    
    public Operation(Dictionary supportedOperators)
    {
        this();
        
        if (supportedOperators == null) return;
        
        this.supportedOperators = supportedOperators;
    }
    
    public Operation(Dictionary supportedOperators, String operator)
    {
        this(supportedOperators);
        
        Pair operatorPair = supportedOperators.getPair(operator);
        
        if (operator == null || !supportsOperator(operatorPair)) return;
        
        this.operator = operatorPair;
    }
    
    public Operation(Dictionary supportedOperators, String operator, Operation connectedOperation)
    {
        this(supportedOperators, operator);
        
        if (connectedOperation == null) return; 
        
        this.connectedOperation = connectedOperation;
    }
    
    public Operation(Dictionary supportedOperators, String operator, Operation connectedOperation, int connectedOperand)
    {
        this(supportedOperators, operator, connectedOperation);
        
        if (connectedOperand < 0) return; 
        
        this.connectedOperand = connectedOperand;
    }
    
    public Operation(Dictionary supportedOperators, String operator, Lista operands)
    {
        this(supportedOperators, operator);
        
        if (operands == null || operands.size() < 1) return;
        
        this.operands = operands;
    }
    
    public Pair getOperator()
    {
        return operator;
    }
    
    public String getOperatorSymbol()
    {
		String operatorSymbol;
		Pair operator = getOperator();
		
		if (operator == null)
		{
			//AxellIO.println("[Operation]: A operacao ainda nao tem um operador - funcao getOperatorSymbol()");
			
			operatorSymbol = null;
		}
		
		else
		{
			operatorSymbol = (String) operator.getKey();
		}
		
        return operatorSymbol;
    }
    
    public void setOperator(Pair operator)
    {
        if (operator == null || !supportsOperator(operator)) return;
        
        this.operator = operator;
    }
    
    public boolean supportsOperator(Pair operator)
    {
        return supportedOperators.exists(operator);
    }
    
    public void setReceivingOperands(boolean receivingOperands)
    {
        this.receivingOperands = receivingOperands;
    }
    
    public int getNumberOfOperands()
    {
        return operands.size();
    }
    
    public int getNumberOfNecessaryOperands()
    {
        return (int) operator.getValue();
    }
    
    public boolean hasNecessaryOperands()
    {
        return !receivingOperands || getNumberOfOperands() == getNumberOfNecessaryOperands();
    }
    
    public Object getOperand(int index)
    {
        Object operand;
        
        int numberOfNecessaryOperands = getNumberOfNecessaryOperands();
        
        if (index < 0)
        {
            //AxellIO.println("[Operation]: Parametro index < 0 na funcao getOperand()");
            
            operand = null;
        }
        
        else if (numberOfNecessaryOperands != -1 && index + 1 > numberOfNecessaryOperands)
        {
            //AxellIO.println("[Operation]: Tentativa de acesso a operando inexistente - funcao getOperand()");
            
            operand = null;
        }
        
        else
        {
            operand = operands.get(index);
        }
        
        return operand;
    }
    
    public void setOperand(int index, Object operand)
    {
        int numberOfNecessaryOperands = getNumberOfNecessaryOperands();
        
        if (index < 0)
        {
            //AxellIO.println("[Operation]: Parametro index < 0 na funcao setOperand()");
        }
        
        else if (numberOfNecessaryOperands != -1 && index + 1 > numberOfNecessaryOperands)
        {
            //AxellIO.println("[Operation]: Tentativa de mudanca de operando desnecessario para a operacao na funcao setOperand()");
        }
        
        else if (operand == null)
        {
            //AxellIO.println("[Operation]: Parametro operand nulo na funcao setOperand()");
        }
        
        else
        {
            operands.set(index, operand);
        }
    }
    
    public void addOperand(Object operand)
    {
        if (!receivingOperands)
        {
            //AxellIO.println("[Operation]: A operacao nao esta mais recebendo operandos - funcao addOperand()");
        }
        
        else
        {
            operands.add(operand);
        }
    }
    
    public void setConnectedOperand(int connectedOperand)
    {
        if (connectedOperand < 0)
        {
            //AxellIO.println("[Operation]: Parametro connectedOperand < 0 - funcao setConnectedOperand()");
        }
        
        else
        {
            this.connectedOperand = connectedOperand;
        }
    }
    
    public int getNumberOfOperationsConnectedToMyOperands()
    {
        return connectedsToMyOperands.size();
    }
    
    public Operation getOperationConnectedToMyOperand(int operandIndex)
    {
        Operation operationConnectedToMe;
        
        if (operandIndex < 0 || operandIndex >= getNumberOfOperationsConnectedToMyOperands())
        {
            //AxellIO.println("[Operation]: Parametro operationIndex invalido - funcao getOperationConnectedToMe()");
            
            operationConnectedToMe = null;
        }
        
        else
        {
            operationConnectedToMe = (Operation) connectedsToMyOperands.get(operandIndex);
        }
        
        return operationConnectedToMe;
    }
    
    public void setOperationConnectedToMyOperand(int operandIndex, Operation operationConnectedToMyOperand)
    {
        if (operandIndex < 0 || operandIndex >= getNumberOfOperationsConnectedToMyOperands())
        {
            //AxellIO.println("[Operation]: Parametro operationIndex invalido - funcao setOperationConnectedToMe()");
        }
        
        else if (operationConnectedToMyOperand == null)
        {
            //AxellIO.println("[Operation]: Parametro operationConnectedToMe nulo - funcao setOperationConnectedToMe()");
        }
        
        else
        {
            connectedsToMyOperands.set(operandIndex, operationConnectedToMyOperand);
        }
    }
    
    public void addOperationConnectedToMyOperand(Operation operationConnectedToMyOperand)
    {
        if (operationConnectedToMyOperand == null)
        {
            //AxellIO.println("[Operation]: Parametro operationConnectedToMe nulo - funcao addOperationConnectedToMe()");
        }
        
        else
        {
            connectedsToMyOperands.add(operationConnectedToMyOperand);
        }
    }
    
    public void receiveResultConnectedToMe(int connectedOperand, Object result)
    {
        if (connectedOperand < 0)
        {
            //AxellIO.println("[Operation]: Parametro connectedOperand < 0 - funcao receiveResultConnectedToMe()");
        }
        
        else
        {
            int operandsLimitOverflow = AxellIO.limitOverflow(connectedOperand, 1, getNumberOfOperands());
            
            // checa se acontecera' o transbordamento do limite da lista dos
            // operandos ao adicionar o novo resultado como um dos operandos
            if (operandsLimitOverflow != 0)
            {
                for (int i = 0; i < operandsLimitOverflow - 1; i++)
                {
                    addOperand(null); // adiciona valores nulos para os operandos ate' antes de onde se deseja colocar o resultado
                }
                
                addOperand(result); // adiciona o resultado na posicao desejada
            }
        }
    }
    
    public boolean isConnected()
    {
        return connectedOperation != null && connectedOperand >= 0;
    }
    
    public void transferResult()
    {
        if (isConnected())
        {
			connectedOperation.receiveResultConnectedToMe(connectedOperand, result);
        }
    }
    
    public Object doOperation()
    {
        //return operations[Operator](operands);
        
        isSolved = true;
        
        transferResult();
        
        return result;
    }
    
    public boolean isSolved()
    {
        return isSolved;
    }
	
	@Override
	public String toString()
	{
		String operationAsString;
		String operatorSymbol = getOperatorSymbol();
		
		if (operatorSymbol == null)
		{
			operationAsString = null;
		}
		
		else
		{
			operationAsString = operatorSymbol + "(";
			
			int numberOfOperands = getNumberOfOperands();
			
			if (numberOfOperands > 0)
			{
				for (int i = 0; i < numberOfOperands - 1; i++)
				{
					operationAsString += getOperand(i).toString() + ", ";
				}
				
				operationAsString += getOperand(numberOfOperands - 1).toString() + ")";
			}
			
			else
			{
				operationAsString += ")";
			}
		}
		
		return operationAsString;
	}
}

class LogicOperation extends Operation
{
    public LogicOperation()
    {
        super
        (
            new Dictionary
            (
                new Lista
                (
                    new Object[]
                    {
                        new Pair("and", -1), // -1 representa que o operador pode ter um numero ilimitado de operandos
                        new Pair("or", -1),
                        new Pair("not", 1)
                    }
                )
            )
        );
    }
    
    public LogicOperation(String operator)
    {
        this();
        
        if (operator == null)
        {
            //AxellIO.println("[LogicOperation]: Parametro operator nulo no construtor");
        }
        
        else
        {
            setOperator(supportedOperators.getPair(operator));
        }
    }
    
    public LogicOperation(String operator, LogicOperation connectedOperation)
    {
        this(operator);
        
        if (connectedOperation == null)
        {
            //AxellIO.println("[LogicOperation]: Parametro connectedOperation nulo no construtor");
        }
        
        else
        {
            this.connectedOperation = connectedOperation;
        }
    }
    
    public LogicOperation(String operator, LogicOperation connectedOperation, int connectedOperand)
    {
        this(operator, connectedOperation);
        
        if (connectedOperand < 0)
        {
            //AxellIO.println("[LogicOperation]: Parametro connectedOperand < 0 no construtor");
        }
        
        else
        {
            this.connectedOperand = connectedOperand;
        }
    }
    
    @Override
    public String getOperand(int index)
    {
        String operand;
        
        int numberOfNecessaryOperands = getNumberOfNecessaryOperands();
        
        if (index < 0)
        {
            //AxellIO.println("[LogicOperation]: Parametro index < 0 na funcao getOperand()");
            
            operand = null;
        }
        
        else if (numberOfNecessaryOperands != -1 && index + 1 > numberOfNecessaryOperands)
        {
            //AxellIO.println("[LogicOperation]: Tentativa de acesso a operando inexistente - funcao getOperand()");
            
            operand = null;
        }
        
        else
        {
            operand = operands.get(index).toString();
        }
        
        return operand;
    }
    
    @Override
    public Boolean doOperation()
    {
        Boolean result = null;
        int numberOfNecessaryOperands = getNumberOfNecessaryOperands();
        
        if (operator == null)
        {
            //AxellIO.println("[LogicOperation]: Tentativa de realizar a operacao logica sem um operador especificado na funcao doOperation()");
            
            result = null;
        }
        
        else if (numberOfNecessaryOperands != -1 && getNumberOfOperands() != numberOfNecessaryOperands)
        {
            //AxellIO.println("[LogicOperation]: Tentativa de realizar a operacao logica com quantidade de operandos incorreta na funcao doOperation()");
            
            result = null;
        }
        
        else
        {
            MyString operatorSymbol = new MyString(getOperatorSymbol());
            int numberOfOperands = getNumberOfOperands();
            
            if (operatorSymbol.equals("and"))
            {
                if (numberOfOperands == 1)
                {
                    //AxellIO.println("[LogicOperation]: Tentativa de realizar a operacao logica (and) com 1 operando na funcao doOperation()");
                    
                    result = null;
                }
                
                else
                {
                    result = AxellIO.getBoolean(getOperand(0)) && AxellIO.getBoolean(getOperand(1));

                    for (int i = 2; i < numberOfOperands && result; i++)
                    {
                        result = result && AxellIO.getBoolean(getOperand(i));
                    }
                    
                    isSolved = true;
                }
            }
            
            else if (operatorSymbol.equals("or"))
            {
                if (numberOfOperands == 1)
                {
                    //AxellIO.println("[LogicOperation]: Tentativa de realizar a operacao logica (or) com 1 operando na funcao doOperation()");
                    
                    result = null;
                }
                
                else
                {
					result = AxellIO.getBoolean(getOperand(0)) || AxellIO.getBoolean(getOperand(1));
					
                    for (int i = 2; i < numberOfOperands && !result; i++)
                    {
                        result = result || AxellIO.getBoolean(getOperand(i));
                    }
                    
                    isSolved = true;
                }
            }
            
            else if (operatorSymbol.equals("not"))
            {
                result = !AxellIO.getBoolean(getOperand(0));
                
                isSolved = true;
            }
        }
        
        this.result = result;
		
        transferResult();
		
        return result;
    }
}

class TreeLevel
{
    Lista brothers = new Lista();
    int levelIndex;
    
    public TreeLevel(){}
    
    public TreeLevel(int levelIndex)
    {
        if (levelIndex < 0) return;
        
        this.levelIndex = levelIndex;
    }
    
    public TreeLevel(Lista brothers)
    {
        if (brothers == null) return;
        
        this.brothers = brothers;
    }
    
	public int getNumberOfBrothers()
	{
		return brothers.size();
	}
	
    public Lista getBrothers()
    {
        return brothers;
    }
    
    public Object getBrother(int index)
    {
        if (index < 0 || index > brothers.size() - 1) return null;
        
        return brothers.get(index);
    }
    
    public Object getLastBrother()
    {
		int numberOfBrothers = getNumberOfBrothers();
		
        if (numberOfBrothers == 0) return null;
        
        return brothers.get(numberOfBrothers - 1);
    }
    
    public void removeBrother(int index)
    {
        if (index < 0 || index > brothers.size() - 1) return;
        
        brothers.remove(index);
    }
    
    public void addBrother(Object brother)
    {
        if (brother == null) return;
        
        brothers.add(brother);
    }
    
    public int getLevelIndex()
    {
        return levelIndex;
    }
    
    public void setLevelIndex(int levelIndex)
    {
        if (levelIndex < 0) return;
        
        this.levelIndex = levelIndex;
    }
    
    public boolean hasBrother(int index)
    {
        return index < brothers.size() && index >= 0;
    }
}

class Tree
{
    private Object ancestor;
    private Lista levels;
    
    public Tree()
    {
        levels = new Lista();
    }
    
    public Tree(Object ancestor)
    {
        if (ancestor == null) return;
        
        this.ancestor = ancestor;
    }
    
    public Tree(Object ancestor, Lista levels)
    {
        this(ancestor);
        
        if (levels == null) return;
        
        this.levels = levels;
    }
    
    public Object getAncestor()
    {
        return ancestor;
    }
    
    public TreeLevel getLevel(int level)
    {
        if (levels == null || level < 0 || level >= getNumberOfLevels()) return null;
        
        return (TreeLevel) levels.get(level);
    }
    
    public int getNumberOfLevels()
    {
        return levels.size();
    }
    
    public void addLevel(TreeLevel level)
    {
        if (levels == null || level == null) return;
        
        levels.add(level);
        
        level.levelIndex = getNumberOfLevels() - 1;
    }
    
    public void createLevel()
    {
        addLevel(new TreeLevel());
    }
    
    public boolean hasLevel(int levelIndex)
    {
        return levelIndex < getNumberOfLevels() && levelIndex >= 0;
    }
}

/*

PUC Minas - Ciencia da Computacao     Nome: AlgebraBooleana

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  infinity                     Data: 26/08/2018

*/

public class AlgebraBooleana
{
    /*
    
    Max, desde ja', quero deixar claro que perdi umas duas semanas fazendo essa questao.
    Como voce pode perceber, deram mais de 3 mil linhas de codigo. So' quero te pedir
    misericordia pelas outras questoes da primeira parte do TP1 que eu nao fizer, pois
    terminei essa questao 'as 19:37 do dia 26/08/2018 (DOMINGO).
	
	Agora darei prioridade 'as questoes na linguagem C. Depois de termina'-las, se der tempo, vou voltar nas questoes de Java.
    
    */
    
    public static void main(String[] args) throws FileNotFoundException
    {
		File file = new File("pub.in");
		FileInputStream fileStream = new FileInputStream(file);
		System.setIn(fileStream);
		File outFile = new File("myOut.out");
		PrintStream fileOutStream = new PrintStream(outFile);
		System.setOut(fileOutStream);
		
		int line = 1;
		MyString input = new MyString(AxellIO.readLine("")); // le a primeira expressao booleana
        
        while(input != null && !input.equals("0")) // executa enquanto a entrada nao for nula e for diferente de "0"
        {
			//if (line == 63)
			//{
            Tree solvedTree = new OperationCompiler().mountTree(input); // monta e ja' resolve a arvore de operacoes da expressao booleana lida
        
			LogicOperation ancestorOperation = (LogicOperation) solvedTree.getLevel(0).getBrother(0); // obtem a operacao ancestral da arvore (primeira operacao)
			
			AxellIO.println( "" + ( ( (Boolean) ancestorOperation.result ) ? 1 : 0 ) ); // converte o resultado da operacao ancestral para 0 ou 1 e imprime na saida padrao
			//}
			
			input = new MyString(AxellIO.readLine("")); // le a proxima expressao booleana
			line++;
        }
    }
}

import java.io.*;
import java.nio.charset.Charset;

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

PUC Minas - Ciencia da Computacao     Nome: Cell

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 28/09/2018

*/

class Cell
{
    Object _element;
    Cell _next;
    Cell _previous;
    Cell _up;
    Cell _down;
    
    public Cell(){}
    
    public Cell(Object element)
    {
        _element = element;
    }
    
    public Cell(Cell previous)
    {
        _previous = previous;
    }
    
    public Cell(Object element, Cell previous)
    {
        this(element);
        
        _previous = previous;
    }
    
    public Cell(Object element, Cell previous, Cell next)
    {
        this(element, previous);
        
        _next = next;
    }
    
    public Cell(Object element, Cell previous, Cell next, Cell up, Cell down)
    {
        this(element, previous, next);
        
        _up = up;
        _down = down;
    }
    
    public Object getElement()
    {
        return _element;
    }
    
    public void setElement(Object element)
    {
        _element = element;
    }
    
    public Cell getPrevious()
    {
        return _previous;
    }
    
    public void setPrevious(Cell previous)
    {
        _previous = previous;
    }
    
    public Cell getNext()
    {
        return _next;
    }
    
    public void setNext(Cell next)
    {
        _next = next;
    }
    
    public Cell getUp()
    {
        return _up;
    }
    
    public void setUp(Cell up)
    {
        _up = up;
    }
    
    public Cell getDown()
    {
        return _down;
    }
    
    public void setDown(Cell down)
    {
        _down = down;
    }
}

/*

PUC Minas - Ciencia da Computacao     Nome: MatrizDinamica

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 28/09/2018

*/

class MatrizDinamica
{
    Cell _first;
    int _numberOfLines;
    int _numberOfColumns;
    
    public MatrizDinamica(int numberOfLines, int numberOfColumns)
    {
        _numberOfLines = numberOfLines;
        _numberOfColumns = numberOfColumns;
        _first = createLine(numberOfColumns);
        Cell currentLine = _first;
        Cell nextLine;
        
        for (int i = 1; i < numberOfLines; i++)
        {
            nextLine = createLine(numberOfColumns);
            
            connectLines(currentLine, nextLine, numberOfColumns);
            
            currentLine = nextLine;
        }
        
        nextLine = null;
        currentLine = null;
    }
    
    public Cell getFirst()
    {
        return _first;
    }
    
    public int getNumberOfLines()
    {
        return _numberOfLines;
    }
    
    public int getNumberOfColumns()
    {
        return _numberOfColumns;
    }
    
    /**
     * Cria uma lista dupla conectando os ponteiros de proximo e anterior
     * 
     * @param numberOfElements quantidade de elementos da lista a ser criada
     * 
     * @return primeira celula da lista
     */
    
    public final Cell createLine(int numberOfElements)
    {
        Cell firstCell = null;
        
        if (numberOfElements > 0)
        {
            firstCell = new Cell();
            Cell currentCell = firstCell;
            Cell nextCell;
            
            for (int i = 0; i < numberOfElements; i++)
            {
                nextCell = new Cell(currentCell);
                
                currentCell.setNext(nextCell);
                currentCell = nextCell;
            }
        }
        
        return firstCell;
    }
    
    /**
     * Conecta os ponteiros de up e down de duas linhas da matriz
     * 
     * @param firstCellOfLine1 primeira celula da linha que estiver mais acima
     * @param firstCellOfLine2 primeira celula da linha que estiver mais abaixo
     * @param numberOfElements quantas celulas das duas linhas devem ser conectadas
     */
    
    public final void connectLines(Cell firstCellOfLine1, Cell firstCellOfLine2, int numberOfElements)
    {
        Cell currentCellOn1 = firstCellOfLine1;
        Cell currentCellOn2 = firstCellOfLine2;
        
        for (int i = 0; i < numberOfElements; i++)
        {
            currentCellOn1.setDown(currentCellOn2);
            currentCellOn2.setUp(currentCellOn1);
            
            currentCellOn1 = currentCellOn1.getNext();
            currentCellOn2 = currentCellOn2.getNext();
        }
        
        currentCellOn1 = null;
        currentCellOn2 = null;
    }
    
    /**
     * Dados o indice da linha e da coluna de uma celula, retorna-a
     * 
     * @param lineIndex indice da linha da celula
     * @param columnIndex indice da coluna da celula
     * 
     * @return celula no ponto especificado
     */
    
    public Cell get(int lineIndex, int columnIndex)
    {
        Cell cellOnIndex = null;
        int numberOfLines = getNumberOfLines();
        int numberOfColumns = getNumberOfColumns();
        
        if ( (lineIndex < 0 || lineIndex >= numberOfLines) || (columnIndex < 0 || columnIndex >= numberOfColumns) )
        {
            AxellIO.println("[MatrizDinamica]: Indice de linha ou coluna errados. (lineIndex = " + lineIndex + " | columnIndex = " + columnIndex + ") - funcao get(int, int)");
        }
        
        else
        {
            cellOnIndex = getFirst();
            
            for (int i = 0; i < lineIndex; i++)
            {
                cellOnIndex = cellOnIndex.getDown();
            }
            
            for (int j = 0; j < columnIndex; j++)
            {
                cellOnIndex = cellOnIndex.getNext();
            }
        }
        
        return cellOnIndex;
    }
    
    public MatrizDinamica sumWith(MatrizDinamica other)
    {
        MatrizDinamica newMatrix = null;
        
        if (other != null)
        {
            int numberOfLines = getNumberOfLines();
            int numberOfColumns = getNumberOfColumns();

            if (numberOfLines == other.getNumberOfLines() && numberOfColumns == other.getNumberOfColumns())
            {
                newMatrix = new MatrizDinamica(numberOfLines, numberOfColumns);

                for (int i = 0; i < numberOfLines; i++)
                {
                    for (int j = 0; j < numberOfColumns; j++)
                    {
                        newMatrix.get(i, j).setElement( (int) get(i, j).getElement() + (int) other.get(i, j).getElement() );
                    }
                }
            }
        }
        
        return newMatrix;
    }
    
    public MatrizDinamica multiplyWith(MatrizDinamica other)
    {
        MatrizDinamica newMatrix = null;
        
        if (other != null)
        {
            int numberOfLines = getNumberOfLines();
            int numberOfColumns = getNumberOfColumns();
            int numberOfLinesOther = other.getNumberOfLines();
            int numberOfColumnsOther = other.getNumberOfColumns();

            if (numberOfColumns == numberOfLinesOther)
            {
                newMatrix = new MatrizDinamica(numberOfLines, numberOfColumnsOther);
                
                int currentElement;
                
                for (int i = 0; i < numberOfLines; i++)
                {
                    for (int columnInOther = 0; columnInOther < numberOfColumnsOther; columnInOther++)
                    {
                        currentElement = 0;
                        
                        for (int j = 0; j < numberOfColumns; j++)
                        {
                            currentElement += (int) get(i, j).getElement() * (int) other.get(j, columnInOther).getElement();
                        }
                        
                        newMatrix.get(i, columnInOther).setElement(currentElement);
                    }
                }
            }
        }
        
        return newMatrix;
    }
    
    public void showMainDiagonal()
    {
        int numberOfLines = getNumberOfLines();
        int numberOfColumns = getNumberOfColumns();
        
        if (numberOfLines == numberOfColumns)
        {
            for (int i = 0; i < numberOfLines; i++)
            {
                AxellIO.print(get(i, i).getElement() + " ");
            }
            
            AxellIO.println();
        }
    }
    
    public void showSecondaryDiagonal()
    {
        int numberOfLines = getNumberOfLines();
        int numberOfColumns = getNumberOfColumns();
        
        if (numberOfLines == numberOfColumns)
        {
            // i --> movimentacao para baixo
            // j --> movimentacao da direita para a esquerda
            for (int i = 0, j = numberOfColumns - 1; i < numberOfLines; i++, j--)
            {
                AxellIO.print(get(i, j).getElement() + " ");
            }
            
            AxellIO.println();
        }
    }
    
    public void interpretStringAndPutOnLine(int lineIndex, String intsString)
    {
        if (intsString != null)
        {
            String[] intArray = intsString.split(" ");
            Cell currentCell = get(lineIndex, 0);

            if (currentCell != null)
            {
                for (String intString : intArray)
                {
                    currentCell.setElement(AxellIO.getint(intString));
                    
                    currentCell = currentCell.getNext();
                }
            }
        }
    }
    
    public String toString()
    {
        String matrixText = "";
        int nOfLines = getNumberOfLines();
        int nOfColumns = getNumberOfColumns();
        
        for (int i = 0; i < nOfLines; i++)
        {
            for (int j = 0; j < nOfColumns; j++)
            {
                matrixText += get(i, j).getElement() + " ";
            }
            
            matrixText += "\n";
        }
        
        return matrixText;
    }
    
    public void printMatrix()
    {
        AxellIO.print( toString() );
    }
}

/*

PUC Minas - Ciencia da Computacao     Nome: MatrizFlexivel

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 28/09/2018

*/

public class MatrizFlexivel
{
    public static void main(String[] args)
    {
        int numberOfTests = AxellIO.readint("");
        int numberOfLines;
        int numberOfColumns;
        
        for (int test = 0; test < numberOfTests; test++)
        {
            MatrizDinamica[] testMatrixes = new MatrizDinamica[2];
            
            for (int count = 0; count < 2; count++)
            {
                numberOfLines = AxellIO.readint("");
                numberOfColumns = AxellIO.readint("");

                testMatrixes[count] = new MatrizDinamica(numberOfLines, numberOfColumns);

                for (int i = 0; i < numberOfLines; i++)
                {
                    testMatrixes[count].interpretStringAndPutOnLine(i, AxellIO.readLine(""));
                }
            }
            
            testMatrixes[0].showMainDiagonal();
            testMatrixes[0].showSecondaryDiagonal();
            testMatrixes[0].sumWith(testMatrixes[1]).printMatrix();
            testMatrixes[0].multiplyWith(testMatrixes[1]).printMatrix();
        }
    }
}

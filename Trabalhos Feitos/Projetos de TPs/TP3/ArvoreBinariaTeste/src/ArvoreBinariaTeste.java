import java.io.*;
import java.nio.charset.Charset;

/*

PUC Minas - Ciencia da Computacao     Nome: AxellIO

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  3.5                          Data: 26/08/2018

*/

class AxellIO
{
    public static final String LINE_SEPARATOR =
            System.getProperty("java.vendor.url").equals("http://www.android.com/")
            ? "\n" + (char) 65535 : System.lineSeparator();

    public static final String LAST_LINE_CHAR = LINE_SEPARATOR.substring(LINE_SEPARATOR.length() - 1);
    
    private static String charset = "ISO-8859-1"; // charset padrao
    private static InputStream inputStream = System.in; // corrente de entrada padrao
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)));
    private static ByteArrayOutputStream inputStreamData; // buffer para guardar os dados da corrente de entrada
    
    // ----------- FUNCOES DE REDIRECIONAMENTO DE ENTRADA E SAIDA -----------
    
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

class Node
{
    Object element;
    Node left;
    Node right;
    Lista contactsList;
    
    public Node(){}
    
    public Node(Object element)
    {
        this.element = element;
    }
    
    public Node(Node left, Node right)
    {
        this.left = left;
        this.right = right;
    }
    
    public Node(Object element, Node left, Node right)
    {
        this(left, right);
        
        this.element = element;
    }
    
    public Node(Object element, Node left, Node right, Lista contactsList)
    {
        this(element, left, right);
        
        this.contactsList = contactsList;
    }
    
    public Object getElement()
    {
        return element;
    }
    
    public void setElement(Object element)
    {
        this.element = element;
    }
    
    public Node getLeft()
    {
        return left;
    }
    
    public void setLeft(Node left)
    {
        this.left = left;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    public Lista getContactsList()
    {
        return contactsList;
    }
    
    public void setContactsList(Lista contactsList)
    {
        this.contactsList = contactsList;
    }
}

class BinarySearchTree
{
    Node root;
    TreeType treeType;
    
    public enum TreeType
    {
        Strings,
        Numbers
    };
    
    public BinarySearchTree(TreeType treeType)
    {
        this.treeType = treeType;
    }
    
    public Node getRoot()
    {
        return root;
    }
    
    public void setRoot(Node root)
    {
        this.root = root;
    }
    
    // retorna o proprio no' atual ou entao um novo no' que sera' filho do no' anterior
    private Node insert(String str, Node current)
    {
        if (current == null)
        {
            current = new Node();
            current.setElement(str);
            current.setContactsList( new Lista() );
        }
        
        else
        {
            String currentStr = (String) current.getElement();
            
            if (currentStr == null) {}
            
            else if (str.compareTo(currentStr) < 0)
            {
                current.setLeft( insert(str, current.getLeft()) );
            }
            
            else if (str.compareTo(currentStr) > 0)
            {
                current.setRight( insert(str, current.getRight()) );
            }
        }
        
        return current;
    }
    
    public void insert(Object element)
    {
        switch (treeType)
        {
            case Strings:
                insert((String) element, getRoot());
                break;
                
            /*case Numbers:
                insert((Double) element, getRoot());
                break;*/
                
            default:
                break;
        }
    }
    
    public Node search(Double num, Node current)
    {
        Node result = null;
        
        if (current != null)
        {
            Double currentNum = (Double) current.getElement();
            
            if ( num.equals(currentNum) )
            {
                result = current;
            }
            
            else if ( num.compareTo(currentNum) < 0 )
            {
                result = search(num, current.getLeft());
            }
            
            else
            {
                result = search(num, current.getRight());
            }
        }
        
        return result;
    }
    
    public Node search(String str, Node current)
    {
        Node result = null;
        
        if (current != null)
        {
            String currentStr = (String) current.getElement();
            
            if (currentStr == null) {}
            
            else if ( str.equals(currentStr) )
            {
                result = current;
            }
            
            else if ( str.compareTo(currentStr) < 0 )
            {
                result = search(str, current.getLeft());
            }
            
            else
            {
                result = search(str, current.getRight());
            }
        }
        
        return result;
    }
    
    public Node search(Object element)
    {
        switch (treeType)
        {
            case Strings:
                return search((String) element, getRoot());
                
            case Numbers:
                return search((Double) element, getRoot());
                
            default:
                return null;
        }
    }
}

class Contato
{
    public String nome;
    public String telefone;
    public String email;
    public int cpf;
    
    public Contato(){}
    
    public Contato(String nome, String telefone, String email, int cpf)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
}

class Agenda
{
    BinarySearchTree agenda;
    
    public Agenda()
    {
        agenda = new BinarySearchTree(BinarySearchTree.TreeType.Strings);
        
        agenda.setRoot( new Node("M", null, null, new Lista()) );
        
        for (char letter = 'A'; letter <= 'Z'; letter++)
        {
            agenda.insert("" + letter);
        }
    }
    
    public void insert(Contato contato)
    {
        String firstLetter = contato.nome.substring(0, 1);
        
        Node contatoNode = agenda.search(firstLetter);
        
        if (contatoNode != null)
        {
            contatoNode.getContactsList().add(contato);
        }
    }
    
    public boolean search(String nome)
    {
        boolean found = false;
        
        String firstLetter = nome.substring(0, 1);
        
        Node contatoNode = agenda.search(firstLetter);
        
        if (contatoNode != null)
        {
            Lista contactsList = contatoNode.getContactsList();
            Contato currentContact;
            int size = contactsList.size();
            
            for (int i = 0; i < size && !found; i++)
            {
                currentContact = (Contato) contactsList.get(i);
                
                found = currentContact.nome.equals(nome);
            }
        }
        
        return found;
    }
    
    public boolean search(int cpf)
    {
        boolean found = false;
        
        for (char firstLetter = 'A'; firstLetter <= 'Z' && !found; firstLetter++)
        {
            String firstLetterStr = "" + firstLetter;
            Node contatoNode = agenda.search(firstLetterStr);

            if (contatoNode != null)
            {
                Lista contactsList = contatoNode.getContactsList();
                Contato currentContact;
                int size = contactsList.size();

                for (int i = 0; i < size && !found; i++)
                {
                    currentContact = (Contato) contactsList.get(i);

                    found = currentContact.cpf == cpf;
                }
            }
        }
        
        return found;
    }
}

public class ArvoreBinariaTeste
{
    public static void main(String[] args)
    {
        Agenda myAgenda = new Agenda();
        
        myAgenda.insert( new Contato("ABC", "1111-1111", "ABC@PUCMINAS.BR", 111111111) );
        myAgenda.insert( new Contato("BCD", "1111-1111", "BCD@PUCMINAS.BR", 222222222) );
        myAgenda.insert( new Contato("CDE", "1111-1111", "CDE@PUCMINAS.BR", 333333333) );
        myAgenda.insert( new Contato("DEF", "1111-1111", "DEF@PUCMINAS.BR", 444444444) );
        myAgenda.insert( new Contato("EFG", "1111-1111", "EFG@PUCMINAS.BR", 555555555) );
        myAgenda.insert( new Contato("FGH", "1111-1111", "FGH@PUCMINAS.BR", 666666666) );
        myAgenda.insert( new Contato("GHI", "1111-1111", "GHI@PUCMINAS.BR", 777777777) );
        
        AxellIO.println("search = " + myAgenda.search(777777777));
    }
}

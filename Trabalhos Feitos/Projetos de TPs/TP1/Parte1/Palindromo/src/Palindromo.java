import java.io.IOException;

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
            return (char) System.in.read();
        }
        
        catch (IOException ex)
        {
            print("Nao foi possivel ler da entrada padrao");
            
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
        System.out.print(data);
    }
    
    public static void println()
    {
        System.out.println();
    }
    
    public static void println(String data)
    {
        System.out.println(data);
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

PUC Minas - Ciência da Computação     Nome: Palindromo

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 16/08/2018

*/

public class Palindromo
{
    
    /**
     * Checa se determinada string e' um palindromo
     * @param string string a ser analisada
     * @return true se for um palindromo e false caso nao
     */
    
    public static boolean isPalindrome(String string)
    {
        boolean isPalindrome = true;
        int length = string.length(), midLength = length / 2;

        if (length == 0) isPalindrome = false; // vou considerar que a string vazia nao e' um palindromo

        else if (length == 2) isPalindrome = string.charAt(0) == string.charAt(1);

        else
        {
            // as variaveis i e j do for serao "ponteiros" que comecaram no inicio e do fim da string
            // respectivamente e irao trabalhar juntas para saber se a string e' ou nao um palindromo
            for (int i = 0, j = length - 1; i < midLength && j >= midLength && isPalindrome; i++, j--)
            {
                //while (string.charAt(i) == ' ') i++;
                //while (string.charAt(j) == ' ') j--;
                
                isPalindrome = string.charAt(i) == string.charAt(j);
            }
        }

        return isPalindrome;
    }
    
    public static void main(String[] args)
    {
        String input = AxellIO.readLine("");
        
        while(input != null && !input.equals("FIM"))
        {
            if (isPalindrome(input))
            {
                AxellIO.println("SIM");
            }

            else
            {
                AxellIO.println("NAO");
            }
            
            input = AxellIO.readLine("");
        }
    }
    
}

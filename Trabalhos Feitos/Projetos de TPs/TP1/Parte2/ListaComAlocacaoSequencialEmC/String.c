#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <math.h>

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: String

Autor: Axell Brendow                  Matricula: 631822

Versao:  2.5                          Data: 16/09/2018

*/

// ----------------------- INICIO DA CLASSE STRING

typedef struct String String;
typedef struct StringArray StringArray;

void makeStringCopy(String *string);
void setWasCopied(String *string, int wasCopied);
void copyString(const char *string, char **destination);
void concatString(const char *string, char **destination);
void replaceAll(char *find, char *replace, char **string);

struct String
{
    char *address;
    int length;
    int wasCopied;
    String *nextString;
};

struct StringArray
{
    String *firstString;
    int numberOfStrings;
};

// ----------------------- FUNCOES DE MANIPULACAO DA STRUCT STRING

// ----------------------- SETTERS AND GETTERS

char *getAddress(String *string)
{
    return string->address;
}

void setAddress(String *string, char *address)
{
    string->address = address;
    setWasCopied(string, false);
}

int getLength(String *string)
{
    return string->length;
}

void setLength(String *string, int length)
{
    string->length = length;
    
    if (length > 0) 
    {
        makeStringCopy(string);
    }
}

String *getNextString(String *string)
{
    return string->nextString;
}

void setNextString(String *string, String *nextString)
{
    string->nextString = nextString;
}

int getWasCopied(String *string)
{
    return string->wasCopied;
}

void setWasCopied(String *string, int wasCopied)
{
    string->wasCopied = wasCopied;
}

// ----------------------- CRIACAO

void startString(String *string, char *address, int length, String *nextString)
{
    setWasCopied(string, false);
    setAddress(string, address);
    setLength(string, length);
    setNextString(string, nextString);
}

String *createString(char *address, int length, String *nextString)
{
    String *newString = (String *) malloc( sizeof(String) * 1 );
    
    startString(newString, address, length, nextString);
    
    return newString;
}

// ----------------------- UTILIDADE

void makeStringCopy(String *string)
{
    char *oldAddress = getAddress(string); // guarda o endereco atual da string
    int strLength = getLength(string); // obtem o seu tamanho
    int sizeOfChar = sizeof(char); // obtem o tamanho em bytes de um char
    int strLengthInBytes = sizeOfChar * strLength; // obtem o tamanho da string em bytes
    
    setAddress( string, (char *) malloc(strLengthInBytes + sizeOfChar) );
    
    memcpy(getAddress(string), oldAddress, strLengthInBytes);
    
    *( getAddress(string) + strLength ) = '\0';

    if (getWasCopied(string) == false) setWasCopied(string, true);

    else free(oldAddress);
}

String *getStringOnIndex(int index, String *reference)
{
    String *currentString = reference;
    int i;
    
    for (i = 0; i < index; i++)
    {
        currentString = getNextString(currentString);
    }
    
    return currentString;
}

char *getStringsText(String *currentString, int numberOfStrings, const char *delimiterString, int putDelimiterOnEnd)
{
    char *stringsText = NULL;
    
    if (currentString == NULL)
    {
        //printf("[String]: Ponteiro para String nulo. - funcao getStringsText(String *, int, const char *, int)\n");
    }
    
    else
    {
        copyString(getAddress(currentString), &stringsText);
        int i;
        
        for (i = 0; i < numberOfStrings - 1; i++)
        {
            concatString(delimiterString, &stringsText);
            
            currentString = getNextString(currentString);
            
            concatString(getAddress(currentString), &stringsText);
        }
        
        if (putDelimiterOnEnd)
        {
            concatString(delimiterString, &stringsText);
        }
    }
    
    return stringsText;
}

void printString(String *string)
{
    if (string == NULL)
    {
        //printf("[String]: Ponteiro para String nulo. - funcao printString(String *)\n");
    }
    
    else
    {
        char *stringText = getStringsText(string, 1, "", false);
        
        puts(stringText);
        
        free(stringText);
    }
}

char *copyStringWithoutEndChar(String *string, char *destination)
{
    char *endCharPos = destination;
    
    if (string == NULL)
    {
        //printf("[String]: Ponteiro para String nulo. - funcao copyStringWithoutEndChar(String *, char *)\n");
    }
    
    else
    {
        int strLength = getLength(string);
        char *stringAddress = getAddress(string);
        int i;
        
        for (i = 0; i < strLength; i++)
        {
            *endCharPos++ = stringAddress[i];
        }
    }
    
    return endCharPos;
}

// ----------------------- FINALIZACAO

void freeStrings(String *firstString)
{
    String *nextString; // guardara' a proxima string da primeira
    
    // como a cada iteracao a primeira string vai mudando, so' para quando
    // a primeira string for nula
    while (firstString != NULL)
    {
        if (getWasCopied(firstString) == true) free(getAddress(firstString));
        
        // obtem a proxima string
        nextString = getNextString(firstString);
        
        free(firstString); // libera a primeira string
        
        firstString = nextString; // a primeira string passa a ser a proxima
    }
}

// ----------------------- FUNCOES DE MANIPULACAO DA STRUCT STRING_ARRAY

// ----------------------- SETTERS AND GETTERS

String *getFirstString(StringArray *stringArray)
{
    return stringArray->firstString;
}

void setFirstString(StringArray *stringArray, String *firstString)
{
    stringArray->firstString = firstString;
}

int getNumberOfStrings(StringArray *stringArray)
{
    return stringArray->numberOfStrings;
}

void setNumberOfStrings(StringArray *stringArray, int numberOfStrings)
{
    stringArray->numberOfStrings = numberOfStrings;
}

void increaseNumberOfStrings(StringArray *stringArray)
{
    setNumberOfStrings(stringArray, getNumberOfStrings(stringArray) + 1);
}

void decreaseNumberOfStrings(StringArray *stringArray)
{
    setNumberOfStrings(stringArray, getNumberOfStrings(stringArray) - 1);
}

// ----------------------- CRIACAO

void startStringArray(StringArray *stringArray, String *firstString, int numberOfStrings)
{
    setFirstString(stringArray, firstString);
    setNumberOfStrings(stringArray, numberOfStrings);
}

StringArray *createStringArray(String *firstString, int numberOfStrings)
{
    StringArray *newStringArray = (StringArray *) malloc( sizeof(StringArray) * 1 );
    
    startStringArray(newStringArray, firstString, numberOfStrings);
    
    return newStringArray;
}

// ----------------------- UTILIDADE

void printStrings(StringArray *stringArray, const char *delimiterString)
{
    if (stringArray == NULL)
    {
        //printf("[String]: Ponteiro para StringArray nulo. - funcao printStrings(StringArray *)\n");
    }
    
    else
    {
        char *stringsText =
        getStringsText
        (
            getFirstString(stringArray),
            getNumberOfStrings(stringArray),
            delimiterString,
            false
        );
        
        puts(stringsText);
        
        free(stringsText);
    }
}

// ----------------------- FINALIZACAO

void freeStringArray(StringArray *stringArray)
{
    freeStrings(getFirstString(stringArray));
    
    free(stringArray);
}

// ----------------------- OUTROS FUNCOES DA CLASSE

/**
 * Coloca uma string a partir do ponteiro recebido, porem,
 * nao coloca o caractere '\0' no final
 *
 * @param *string string que se deseja colocar a partir do ponteiro
 * @param *destination ponteiro que indicara' onde sera' o inicio da string
 *
 * @return um ponteiro para onde seria o '\0' da string
 */

char *copyWithoutEndChar(const char *string, char *destination)
{
    // executa enquanto o ponteiro "string" nao apontar para o endereco de um '\0'
    while (*string != '\0')
    {
        // 1 - acessa o endereco de destino e copia, para ele, o caractere que
        // o ponteiro "string" estiver apontando
        // 2 - anda com ambos os ponteiro para o proximo endereco
        *destination++ = *string++;
    }
    
    // retorna o primeiro espaco livre para uma possivel futura
    // concatenacao de outras strings no destino
    return destination;
}

void copyString(const char *string, char **destination)
{
    free(*destination); // caso haja algo no endereco de destino, libera-o

    // aloca espaco para guardar o tamanho da string, incluindo o '\0'
    *destination = (char *) malloc( (strlen(string) + 1) * sizeof(char) );

    if (*destination == NULL) // checa se a alocacao falhou
    {
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao copyString(const char *, char **)");
    }

    else
    {
        strcpy(*destination, string); // copia a string para o destino
    }
}

void concatString(const char *string, char **destination)
{
    char *oldStr = *destination; // guarda o endereco de memoria da string atual

    // 1 - aloca um bloco de memoria que suporte a string antiga, a nova e o
    // caractere finalizador '\0'
    // 2 - guarda o endereco do bloco no ponteiro de destino
    *destination = (char *) malloc(strlen(oldStr) + strlen(string) + 1);
    
    if (*destination == NULL) // checa se a alocacao falhou
    {
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao concatString(const char *, char **)");
    }

    else
    {
        // 1 - copia a string antiga para o destino e retorna o proximo endereco
        // depois da string
        // 2 - coloca a segunda string a partir desse endereco e retorna o proximo
        // endereco depois da segunda string
        // 3 - coloca o '\0' nesse endereco
        *copyWithoutEndChar(string, copyWithoutEndChar(oldStr, *destination)) = '\0';
    }
    
    free(oldStr); // libera o espaco onde a string antiga esta'
}

char *create1CharString(char c)
{
    char *str = (char *) malloc(2 * sizeof(char));
    
    if (str == NULL)
    {
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao create1CharString(char)");
    }
    
    else
    {
        str[0] = c;
        str[1] = '\0';
    }

    return str;
}

void concatChar(char character, char **destination)
{
    char *characterString = create1CharString(character); // cria uma string de 1 caractere
    
    concatString(characterString, destination); // concatena ao destino a string criada
    
    free(characterString); // libera o endereco da string criada
}

void prependChar(char character, char **destination)
{
    char *characterString = create1CharString(character); // cria uma string de 1 caractere
    
    concatString(*destination, &characterString); // concatena 'a string criada a string do destino
    
    free(*destination); // libera a string do destino
    
    *destination = characterString; // atualiza o endereco do destino para o inicio da string criada
}

int getCharTimes(char c, const char *str)
{
    int charTimes = 0, i;

    for (i = 0; str[i]; str[i++] == c ? charTimes++ : 0);

    return charTimes;
}

int countCharsInStringList(int numberOfStrings, va_list list)
{
    int numberOfChars = 0, i;
    char *token;
    
    for (i = 0; i < numberOfStrings; i++)
    {
        for (token = va_arg(list, char *); *token++; numberOfChars++);
    }
    
    va_end(list);

    return numberOfChars;
}

void copyStringListTo(char *str, int numberOfStrings, va_list strings)
{
    int i;
    
    for (i = 0; i < numberOfStrings; i++)
    {
        str = copyWithoutEndChar(va_arg(strings, char *), str);
    }
    
    *str = '\0';

    va_end(strings);
}

char *searchNullCharIn(char *str)
{
    while (*str) ++str;

    return str;
}

void concatStringsDinamicallyTo(char **str, int numberOfStrings, ...)
{
    va_list strings;
    va_start(strings, numberOfStrings);
    
    if (*str == NULL)
    {
        *str = (char *) malloc(countCharsInStringList(numberOfStrings, strings) + 1);

        copyStringListTo(*str, numberOfStrings, strings);
    }
    
    else
    {
        char *oldStr = *str;

        *str = (char *) malloc(strlen(oldStr) + countCharsInStringList(numberOfStrings, strings) + 1);

        copyStringListTo(copyWithoutEndChar(oldStr, *str), numberOfStrings, strings);

        free(oldStr);
    }
    
    va_end(strings);
}

/**
 * Checa se um determinado caractere esta' num intervalo de caracteres
 * 
 * @param input caractere a ser analisado
 * @param lowerLimit limite inferior do intervalo
 * @param upperLimit limite superior do intervalo
 * 
 * @return true se o caractere estiver no intervalo e false se nao estiver
 */

int isOnInterval(int lowerLimit, int upperLimit, int number)
{
    return number >= lowerLimit && number <= upperLimit;
}

int isDigit(int x)
{
    return isOnInterval('0', '9', x);
}

int isFloat(int x)
{
    return isDigit(x) || x == '.';
}

int isLowerCase(int x)
{
    return isOnInterval('a', 'z', x);
}

int isUpperCase(int x)
{
    return isOnInterval('A', 'Z', x);
}

int isLetter(int x)
{
    return isLowerCase(x) || isUpperCase(x);
}

int isNewLineCharacter(char character)
{
    return character == '\n' || character == '\r';
}

int isString(int x)
{
    return !isNewLineCharacter(x) && x != ' ';
}

/**
 * Checa se um determinado caractere esta' num intervalo especifico de caracteres
 * 
 * @param character caractere a ser analisado
 * @param interval caractere que simboliza o intervalo especifico de caracteres:
 * d - digits (0-9)
 * u - upperCases (ABC)
 * l - lowerCases (abc)
 * a - alphabet (ABCabc)
 * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
 * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
 * 
 * @return true se o caractere estiver no intervalo e false caso nao esteja
 */

int isOnUsualInterval(char character, char interval)
{
    int isOnUsualInterval = false;

    switch (interval)
    {
        case 'd':
            isOnUsualInterval = isDigit(character);
            break;

        case 'u':
            isOnUsualInterval = isUpperCase(character);
            break;

        case 'l':
            isOnUsualInterval = isLowerCase(character);
            break;

        case 'a': // alphabet
            isOnUsualInterval = isLetter(character);
            break;

        case 'f':
            isOnUsualInterval = isFloat(character);
            break;

        case 's':
            isOnUsualInterval = isString(character);
            break;
    }

    return isOnUsualInterval;
}

/**
 * Checa se uma String contem apenas caracteres especificos
 * 
 * @param str String a ser analisada
 * @param interval caractere que simboliza o intervalo especifico de caracteres:
 * d - digits (0-9)
 * u - upperCases (ABC)
 * l - lowerCases (abc)
 * a - alphabet (ABCabc)
 * f - float (4.5) (aceita multiplos pontos ".", cuidar desse caso)
 * s - string (qualquer coisa diferente de um espaco em branco e uma quebra de linha)
 * 
 * @return true se a String tiver apenas os caracteres especificos, caso contrario, false
 */

int isSpecificString(char *str, char interval)
{
    int length = strlen(str);
    
    if (str == NULL || length == 0) return false;
    
    int i;
    char address = str[0];
    int isValid = isOnUsualInterval(address, interval) ||
            ((interval == 'd' || interval == 'f') && address == '-' && length > 1);

    for (i = 1; i < length && isValid; i++)
    {
        isValid = isOnUsualInterval(str[i], interval);
    }

    return isValid;
}

int getIntLength(int x)
{
    int length = 0, i;
    long long value = 10;

    for (i = 0; i < 17 && length == 0; i++)
    {
        if (x < value) length = i + 1;

        else value *= 10;
    }

    return length;
}

char *intToString(int x)
{
    char *str = (char *) malloc( sizeof(char) * (getIntLength(x) + 1) );
    
    sprintf(str, "%d", x);
    
    return str;
}

char *intArrayToString(int *intArray, int size, char delimiter)
{
    char *string = NULL, *intString = intToString(*intArray++), *delimiterString = create1CharString(delimiter);
    int i;
    
    copyString(intString, &string);
    
    free(intString);
    
    for (i = 1; i < size; i++)
    {
        concatString(delimiterString, &string);
        
        intString = intToString(*intArray++);
        
        concatString(intString, &string);
        
        free(intString);
    }
    
    free(delimiterString);
    
    return string;
}

int *strToIntArray(char *str, const char *delimiter)
{
    int *intArray = (int *) malloc( sizeof(int) * (getCharTimes(*delimiter, str) + 1) ), i = 0;
    char *intStr = strtok(str, delimiter);

    while (intStr)
    {
        intArray[i++] = atoi(intStr);

        intStr = strtok(NULL, delimiter);
    }

    free(intStr);

    return intArray;
}

void removeZerosInExcessAndAddMissingZeros(char **floatString)
{
    if (*floatString == NULL)
    {
        fprintf(stderr, "String nula. - funcao removeZerosInExcessAndAddMissingZeros(char **)");
    }
    
    else
    {
        char *dotCharPtr = strchr(*floatString, '.'); // procura o primeiro '.' na string
        
        if (dotCharPtr != NULL) // checa se existe algum '.' na string
        {
            if (dotCharPtr == *floatString) // checa se a string esta' comecando com um '.'
            {
                prependChar('0', floatString); // adiciona um '0' antes do ponto
            }

             // obtem um ponteiro para o ultimo caractere da string
            char *floatStringEnd = searchNullCharIn(*floatString) - 1;

            int i, floatStringLength = floatStringEnd - *floatString + 1;

            // percorre a string do fim para o inicio substituindo os zeros 'a direita por '\0's
            for (i = 0; i < floatStringLength - 1 && *floatStringEnd == '0'; i++)
            {
                *floatStringEnd-- = '\0';
            }
            
            if (*floatStringEnd == '.') // checa se a string do numero esta terminando com um '.'
            {
                concatChar('0', floatString); // adiciona um '0' depois do ponto
            }
        }
    }
}

int getDoubleLength(double x)
{
    int doubleLength = 0;
    char *buffer = (char *) malloc(sizeof(char) * 40);
    
    sprintf(buffer, "%f", x);
    
    removeZerosInExcessAndAddMissingZeros(&buffer);
    
    doubleLength = strlen(buffer);
    
    free(buffer);
    
    return doubleLength;
}

char *doubleToString(double x)
{
    char *doubleStr = NULL;
    char *buffer = (char *) malloc(sizeof(char) * 40);
    
    sprintf(buffer, "%#f", x);
    removeZerosInExcessAndAddMissingZeros(&buffer);
    
    // condicoes extraidas do metodo "public static String toString(double d)"
    // da classe Double do Java 7
    if (x >= pow(10, 7) || (x < pow(10, -3) && x != 0))
    {
        int lengthOfIntegerPart = strchr(buffer, '.') - buffer;
        int lengthOfFractionalPart = strlen(buffer) - lengthOfIntegerPart - 1;
        char *bufferCopy = strdup(buffer);
        
        free(buffer);
        
        buffer = (char *) malloc(sizeof(char) * 40);
        
        *(buffer + 0) = *bufferCopy;
        *(buffer + 1) = '.';
        
        memcpy(buffer + 2, bufferCopy + 1, (lengthOfIntegerPart - 1) * sizeof(char));
        memcpy(buffer + 2 + lengthOfIntegerPart - 1, bufferCopy + lengthOfIntegerPart + 1, lengthOfFractionalPart * sizeof(char));
        
        free(bufferCopy);
        
        *( buffer + 1 + lengthOfIntegerPart + lengthOfFractionalPart ) = '\0';
        removeZerosInExcessAndAddMissingZeros(&buffer);
        
        sprintf(searchNullCharIn(buffer), "E%d", lengthOfIntegerPart - 1);
    }
    
    copyString(buffer, &doubleStr);
    
    free(buffer);
    
    return doubleStr;
}

char *hasSubstringOnIndex(int index, const char *substring, char *string)
{
    char *substringEnd = NULL;
    char *stringOnIndex = string + index; // obtem um ponteiro para o indice
    
    // checa se, a partir desse ponteiro, existe a subcadeia procurada
    if (strstr(stringOnIndex, substring) == stringOnIndex)
    {
        // guarda o endereco do final da subcadeia, ou seja, o endereco do seu '\0'
        substringEnd = stringOnIndex + strlen(substring);
    }
    
    return substringEnd;
}

char *hasSubstringOnStart(const char *substring, char *string)
{
    return hasSubstringOnIndex(0, substring, string);
}

char *getStringFirstAddressThatDoesntHasSubstring(const char *substring, char *string)
{
    char *firstAddress = string;
    
    // se a subcadeia comecar exatamente no inicio da cadeia, obtem o endereco
    // do '\0' imaginario da subcadeia em relacao 'a cadeia
    char *substringEnd = hasSubstringOnStart(substring, string);
    
    while (substringEnd != NULL) // checa se a subcadeia foi encontrada
    {
        // o primeiro endereco sem a subcadeia passa a ser o '\0' imaginario da subcadeia
        firstAddress = substringEnd;
        
        // se, a partir desse novo endereco, existir novamente a subcadeia,
        // obtem o endereco do seu '\0' imaginario
        substringEnd = hasSubstringOnStart(substring, substringEnd);
    }
    
    return firstAddress;
}

void replace(char find, char replace, char *string)
{
    char *searchedChar = strchr(string, find); // obtem um ponteiro para a primeira ocorrencia de "find"

    while (searchedChar != NULL) // checa se realmente existe o caractere
    {
        *searchedChar = replace; // se existir, substitui-o pelo "replace"

        searchedChar = strchr(searchedChar + 1, find); // obtem um ponteiro para a primeira ocorrencia de "find" apos o caractere encontrado
    }
}

StringArray *split(char *string, const char *delimiterString)
{
    // sera' o cursor que caminhara' pela string
    char *stringCursor = getStringFirstAddressThatDoesntHasSubstring(delimiterString, string);
    char *ptrToFirstCharOfCurrentString = stringCursor;
    int delimiterLength = strlen(delimiterString);
    
    // cria a primeira string do split
    String *firstString = createString(stringCursor, 0, NULL);
    StringArray *stringArray = createStringArray(firstString, 1);
    String *currentString = firstString;
    
    do
    {
        // procura, a partir do cursor, a string delimitadora
        stringCursor = strstr(stringCursor, delimiterString);
        
        // checa se a string delimitadora nao foi encontrada
        if (stringCursor == NULL)
        {
            // o tamanho da string atual sera' o strlen a partir do seu endereco
            setLength( currentString, strlen(ptrToFirstCharOfCurrentString) );
        }
        
        else
        {
            // o tamanho da string atual sera' a diferenca entre o endereco
            // inicial da string delimitadora e o seu endereco
            setLength(currentString, stringCursor - ptrToFirstCharOfCurrentString);
            
            // passa o cursor para depois da string delimitadora
            stringCursor += delimiterLength;
            
            // procura, a partir do cursor, o primeiro endereco que nao tenha
            // a string delimitadora
            stringCursor = getStringFirstAddressThatDoesntHasSubstring(delimiterString, stringCursor);
            ptrToFirstCharOfCurrentString = stringCursor;
            
            // checa se o cursor nao foi parar no fim da string
            if (*stringCursor != '\0')
            {
                // cria a proxima string e conecta-a 'a atual
                setNextString(currentString, createString(ptrToFirstCharOfCurrentString, 0, NULL));
                
                // aumenta a quantidade de strings do arranjo
                increaseNumberOfStrings(stringArray);
                
                // a string atual passa a ser a sua proxima
                currentString = getNextString(currentString);
            }
        }
        
    } while (stringCursor != NULL && *stringCursor != '\0');
    
    return stringArray;
}

void replaceAll(char *find, char *replace, char **string)
{
    StringArray *splitResult = split(*string, find);
    
    free(*string);
    
    *string =
    getStringsText
    (
        getFirstString(splitResult),
        getNumberOfStrings(splitResult),
        replace,
        false
    );
    
    freeStringArray(splitResult);
}

void replaceAllChars(char find, char replace, char **string)
{
    char *stringToFind = create1CharString(find);
    char *stringToReplace = create1CharString(replace);
    replaceAll(stringToFind, stringToReplace, string);
    
    free(stringToFind);
    free(stringToReplace);
}

char *readLine(int maximumOfCharacters)
{
    // cria um buffer para a entrada com o tamanho maximo especificado
    char inputBuffer[maximumOfCharacters];
    char *line = NULL; // guardara' a cadeia que for lida

    if (inputBuffer == NULL)
    {
        fprintf(stderr, "Nao foi possivel alocar espaco para o buffer. Tente diminuir a quantidade maxima de caracteres. - funcao *readLine(int)");
    }

    else
    {
        fgets(inputBuffer, maximumOfCharacters, stdin); // le, da entrada padrao, uma cadeia com o tamanho maximo especificado

        replace('\n', '\0', inputBuffer); // como a funcao fgets() deixa o '\n' na string, vou substitui-lo por um '\0'

        copyString(inputBuffer, &line); // copia, dinamicamente, a string de inputBuffer para line
    }

    return line; // retorna a cadeia
}

int readInt()
{
    char *line = readLine(50);
    int result = atoi(line);
    
    free(line);
    
    return result;
}

// ----------------------- FIM DA CLASSE STRING
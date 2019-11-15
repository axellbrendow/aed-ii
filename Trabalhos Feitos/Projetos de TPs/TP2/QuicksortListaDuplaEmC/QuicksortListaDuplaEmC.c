#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <math.h>
#include <time.h>

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

// ----------------------- OUTRAS FUNCOES DA CLASSE

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
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao copyString(const char *, char **)\n");
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
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao concatString(const char *, char **)\n");
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
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao create1CharString(char)\n");
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
        fprintf(stderr, "String nula. - funcao removeZerosInExcessAndAddMissingZeros(char **)\n");
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
        fprintf(stderr, "Nao foi possivel alocar espaco para o buffer. Tente diminuir a quantidade maxima de caracteres. - funcao *readLine(int)\n");
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

/*

PUC Minas - Ciencia da Computacao     Nome: File

Autor: Axell Brendow                  Matricula: 631822

Versao:  2.0                          Data: 13/09/2018

*/

// ----------------------- INICIO DA CLASSE FILE

FILE *setSystemIn(FILE *newIn)
{
    if (newIn == NULL)
    {
        fprintf(stderr, "Arquivo de entrada padrao nulo. - funcao setSystemIn(File *)\n");
    }

    else
    {
        stdin = newIn;
    }

    return newIn;
}

FILE *openFileAndSetItToSystemIn(const char *fileName, const char *readingMode)
{
    FILE *newIn = fopen(fileName, readingMode);

    if (newIn == NULL)
    {
        fprintf(stderr, "Nao foi possivel abrir o arquivo. - funcao openFileAndSetItToSystemIn(const char *, const char *)\n");
    }

    else
    {
        setSystemIn(newIn);
    }

    return newIn;
}

FILE *setSystemOut(FILE *newOut)
{
    if (newOut == NULL)
    {
        fprintf(stderr, "Arquivo de saida padrao nulo. - funcao setSystemOut(File *)\n");
    }

    else
    {
        stdout = newOut;
    }

    return newOut;
}

FILE *openFileAndSetItToSystemOut(const char *fileName, const char *readingMode)
{
    FILE *newOut = fopen(fileName, readingMode);

    if (newOut == NULL)
    {
        fprintf(stderr, "Nao foi possivel abrir o arquivo. - funcao openFileAndSetItToSystemOut(const char *, const char *)\n");
    }

    else
    {
        setSystemOut(newOut);
    }

    return newOut;
}

int getFileSize(FILE *file)
{
    int fileSize = 0;

    if (file == NULL)
    {
        fprintf(stderr, "O endereco do arquivo nao existe. - funcao getFileSize(File *)\n");
    }

    else
    {
        int seekSuccess = fseek(file, 0, SEEK_SET) == 0; // tenta mover o cursor do arquivo para a primeira posicao
        char currentChar;

        if (seekSuccess) // checa se foi possivel mover o cursor
        {
            currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao

            while (currentChar != EOF) // enquanto o caractere atual for diferente do EOF (FIM DE ARQUIVO),
            {
                fileSize++; // aumenta uma unidade no tamanho do arquivo

                currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao
            }
        }
    }

    return fileSize;
}

char *getFileLineFrom(fpos_t cursorPosition, FILE *file)
{
    char *fileLine = NULL;
    
    if (file != NULL)
    {
        fsetpos(file, &cursorPosition); // coloca a posicao do cursor na posicao inicial

        int seekSuccess = fseek(file, 0, SEEK_CUR) == 0; // tenta mover o cursor para a posicao atual

        if (seekSuccess) // se foi possivel,
        {
            copyString("", &fileLine); // inicia uma string vazia para a linha
            
            char currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao

            while (!isNewLineCharacter(currentChar) && currentChar != EOF) // executa enquanto nao achar um fim de linha ou fim de arquivo
            {
                concatChar(currentChar, &fileLine); // vai concatenando os caracteres 'a cadeia da linha

                currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao
            }
        }

        else
        {
            fprintf(stderr, "Nao foi possivel mover o cursor do arquivo para a posicao desejada. - funcao printFileLineFrom(fpos_t, File *)\n");
        }
    }

    else
    {
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)\n");
    }
    
    return fileLine;
}

char *getCurrentFileLine(FILE *file)
{
    fpos_t filePosition;

    fgetpos(file, &filePosition); // obtem a posicao atual do cursor

    return getFileLineFrom(filePosition, file); // imprime a linha a partir dessa posicao
}

int moveCursorToLine(int lineNumber, FILE *file)
{
    int currentLineNumber = 1;
    char currentChar = 0;
    
    // tenta mover o cursor para a primeira posicao do arquivo
    if (fseek(file, 0, SEEK_SET) == 0)
    {
        currentChar = fgetc(file); // pega o primeiro caractere
        
        // roda enquanto nao chegar na linha desejada e nem no fim do arquivo
        while (currentLineNumber != lineNumber && currentChar != EOF)
        {
            // checa se o caractere atual faz parte de uma quebra de linha
            if (isNewLineCharacter(currentChar))
            {
                // o caractere atual passa a ser o proximo
                currentChar = fgetc(file);
                currentLineNumber++; // aumenta a quantidade de linhas percorrida
                
                // roda enquanto o caractere atual fizer parte de uma quebra de linha
                while (isNewLineCharacter(currentChar))
                {
                    currentChar = fgetc(file);
                }
            }
            
            else
            {
                // vai lendo os caractere ate parar no if acima
                currentChar = fgetc(file);
            }
        }
        
        // volta uma posicao com o cursor do arquivo
        fseek(file, -1, SEEK_CUR) != 0;
    }
    
    return currentLineNumber;
}

char *getFileLine(int lineNumber, FILE *file)
{
    char *line = NULL;
    
    if (moveCursorToLine(lineNumber, file) == lineNumber)
    {
        line = getCurrentFileLine(file);
    }
    
    return line;
}

void printFileLineFrom(fpos_t cursorPosition, FILE *file)
{
    if (file != NULL)
    {
        char *fileLine = getFileLineFrom(cursorPosition, file);
        
        if (strlen(fileLine) > 0)
        {
            printf( "%s\n", fileLine );
        }
        
        free(fileLine);
    }

    else
    {
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)\n");
    }
}

void printCurrentFileLine(FILE *file)
{
    fpos_t filePosition;

    fgetpos(file, &filePosition); // obtem a posicao atual do cursor

    printFileLineFrom(filePosition, file); // imprime a linha a partir dessa posicao
}

void runFileBackwardPrintingLines(FILE *file)
{
    int fileSize = getFileSize(file);
    fpos_t startPosition;
    int seekSuccess;
    char currentChar;
    int i;

    for (i = 0; i < fileSize; i++)
    {
        seekSuccess = fseek(file, fileSize - 1 - i, SEEK_SET) == 0; // coloca o cursor do arquivo no inicio e, entao, desloca-o ate o fim

        if (seekSuccess) // checa se foi possivel mover o cursor
        {
            fgetpos(file, &startPosition);
            
            currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao

            if (isNewLineCharacter(currentChar)) // checa se e' um caractere de fim de linha
            {
                printCurrentFileLine(file); // imprime a linha a partir da posicao do cursor
                
                fsetpos(file, &startPosition); // volta a posicao do cursor para a posicao inicial
            }
        }

        else
        {
            fprintf(stderr, "Nao foi possivel mover o cursor para o caractere %d do arquivo. - funcao runFileBackwardPrintingLines(File *)", fileSize - 1 - i);
        }
    }
    
    fseek(file, 0, SEEK_SET); // coloca o cursor no inicio do arquivo
    printCurrentFileLine(file); // imprime a primeira linha do arquivo
}

// ----------------------- FIM DA CLASSE FILE

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

int limitOverflow(int start, int numberOfElements, int setSize)
{
    int limitOverflow;

    if (start < 0)
    {
        //printf("[Array]: Indice inicial < 0. (start = %d) - funcao limitOverflow(int, int, int)\n", start);

        limitOverflow = start;
    }

    else if (numberOfElements < 1)
    {
        //printf("[Array]: Parametro numberOfElements < 1. (numberOfElements = %d) - funcao limitOverflow(int, int, int)\n", numberOfElements);

        limitOverflow = 1; // 1 = true
    }

    else if (start + numberOfElements > setSize)
    {
        //printf("[Array]: Indice inicial e a quantidade de elementos transbordam o limite. (start = %d | numberOfElements = %d) - funcao limitOverflow(int, int, int)\n", start, numberOfElements);

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

int backwardLimitOverflow(int start, int numberOfElements, int setSize, int backward)
{
    int currentLimitOverflow;

    if (backward)
    {
        if (start < 0)
        {
            //printf("[Array]: Indice inicial < 0. (start = %d) - funcao backwardLimitOverflow(int, int, int, int)\n", start);

            currentLimitOverflow = start;
        }

        else if (start > setSize - 1)
        {
            //printf("[Array]: Parametro start > setSize - 1. (start = %d | setSize = %d) - funcao backwardLimitOverflow(int, int, int, int)\n", start, setSize);

            currentLimitOverflow = start - (setSize - 1);
        }

        else // se nao,
        {
            // corto e jogo fora a parte posterior ao indice inicial do conjunto,
            // pego o que sobrou e rotaciono 180 graus no sentido horario finjindo ser
            // um novo arranjo em que o o indice inicial e' na verdade o indice 0
            currentLimitOverflow = -limitOverflow(0, numberOfElements, start + 1);
        }
    }

    else
    {
        currentLimitOverflow = limitOverflow(start, numberOfElements, setSize);
    }

    return currentLimitOverflow;
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

int limitOverflowOnIndex(int start, int setSize)
{
    return limitOverflow(start, 1, setSize);
}

// ----------------------- INICIO DA CLASSE CELL

/*

PUC Minas - Ciencia da Computacao     Nome: Cell

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 11/10/2018

*/

typedef struct Cell Cell;

struct Cell
{
    void *element;
    Cell *left;
    Cell *right;
};

// ----------------------- CONSTRUTORES DA CLASSE

void startCell(Cell *cell, void *element, Cell *left, Cell *right)
{
    if (cell == NULL)
    {
        fprintf(stderr, "[Cell]: Ponteiro nulo para Cell. - funcao startCell(Cell *, void *, Cell *, Cell *)\n");
    }
    
    else
    {
        cell->element = element;
        cell->left = left;
        cell->right = right;
    }
}

Cell *createCell(void *element, Cell *left, Cell *right)
{
    Cell *newCell = (Cell *) malloc(sizeof(Cell) * 1);
    
    startCell(newCell, element, left, right);
    
    return newCell;
}

// ----------------------- FIM DA CLASSE CELL

// ----------------------- INICIO DA CLASSE DOUBLE_LIST

/*

PUC Minas - Ciencia da Computacao     Nome: DoubleList

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 11/10/2018

*/

typedef struct DoubleList DoubleList;

struct DoubleList
{
    Cell *head;
    Cell *last;
    int size;
};

// ----------------------- CONSTRUTORES DA CLASSE

void startDoubleList(DoubleList *doubleList, Cell *head, Cell *last)
{
    if (doubleList == NULL)
    {
        fprintf(stderr, "[DoubleList]: Ponteiro nulo para DoubleList. - funcao startDoubleList(DoubleList *, Cell *, Cell *, int)\n");
    }
    
    else
    {
        doubleList->head = head;
        doubleList->last = last;
        doubleList->size = 0;
    }
}

DoubleList *createDoubleList()
{
    DoubleList *newDoubleList = (DoubleList *) malloc(sizeof(DoubleList) * 1);
    
    Cell *headCell = createCell(NULL, NULL, NULL);
    
    startDoubleList(newDoubleList, headCell, headCell);
    
    return newDoubleList;
}

// ----------------------- DESTRUTORES DA CLASSE

void freeDoubleList(DoubleList *doubleList)
{
    if (doubleList != NULL)
    {
        Cell *current = doubleList->head;
        Cell *next;
        
        while (current != NULL)
        {
            next = current->right;
            
            free(current);
            
            current = next;
        }
        
        free(doubleList);
    }
}

// ----------------------- OUTRAS FUNCOES DA CLASSE

int size(DoubleList *doubleList)
{
    int size = 0;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else
    {
        size = doubleList->size;
    }
    
    return size;
}

Cell *getFirst(DoubleList *doubleList)
{
    Cell *firstCell = NULL;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else
    {
        Cell *head = doubleList->head;
        
        if (head != NULL)
        {
            firstCell = head->right;
        }
    }
    
    return firstCell;
}

Cell *getCell(DoubleList *doubleList, int index)
{
    Cell *cell = NULL;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else if (limitOverflowOnIndex(index, doubleList->size) != 0)
    {
        // erro
    }
    
    else
    {
        Cell *current = getFirst(doubleList);
        int i;
        
        for (i = 0; i < index && current != NULL; i++)
        {
            current = current->right;
        }
        
        if (i == index) cell = current;
    }
    
    return cell;
}

void swap(DoubleList *doubleList, int index1, int index2)
{
    Cell *cell1 = getCell(doubleList, index1);
    Cell *cell2 = getCell(doubleList, index2);
    
    if (cell1 == NULL || cell2 == NULL)
    {
        // erro
    }
    
    else
    {
        void *copy1 = cell1->element;
        
        cell1->element = cell2->element;
        cell2->element = copy1;
    }
}

/*void *removeOnStart(DoubleList *doubleList)
{
    void *removedElement = NULL;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else if (doubleList->size > 0)
    {
        Cell *head = doubleList->head;
        removedElement = head->element;
        
        doubleList->head = head->right;
        
        head->left = head->right = NULL;
        
        free(head);
    }
    
    return removedElement;
}*/

void *removeOnEnd(DoubleList *doubleList)
{
    void *removedElement = NULL;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else if (doubleList->size > 0)
    {
        Cell *last = doubleList->last;
        Cell *previousOfLast = last->left;
        removedElement = last->element;
        
        previousOfLast->right = NULL;
        
        doubleList->last = previousOfLast;
        
        last->left = last->right = NULL;
        
        free(last);
        
        doubleList->size--;
    }
    
    return removedElement;
}

void *removeOnIndex(DoubleList *doubleList, int index)
{
    void *removedElement = NULL;
    
    if (doubleList == NULL)
    {
        // erro
    }
    
    else if (limitOverflowOnIndex(index, doubleList->size) != 0)
    {
        // erro
    }
    
    else if (doubleList->size > 0)
    {
        if (index == doubleList->size - 1)
        {
            removedElement = removeOnEnd(doubleList);
        }
        
        else
        {
            Cell *cellOnIndex = getCell(doubleList, index);
            Cell *cellOnPreviousIndex = cellOnIndex->left;
            removedElement = cellOnIndex->element;
            
            cellOnPreviousIndex->right = cellOnIndex->right;
            cellOnIndex->right->left = cellOnPreviousIndex;
            
            cellOnIndex->left = cellOnIndex->right = NULL;
            
            free(cellOnIndex);
            
            doubleList->size--;
        }
    }
    
    return removedElement;
}

void addOnEnd(DoubleList *doubleList, void *element)
{
    if (doubleList == NULL)
    {
        // erro
    }
    
    else
    {
        Cell *newCell = createCell(element, doubleList->last, NULL);
        
        doubleList->last->right = newCell;
        doubleList->last = newCell;
        
        doubleList->size++;
    }
}

void add(DoubleList *doubleList, void *element, int index)
{
    if (doubleList == NULL)
    {
        // erro
    }
    
    else if (index != doubleList->size && limitOverflowOnIndex(index, doubleList->size) != 0)
    {
        // erro
    }
    
    else
    {
        if (index == doubleList->size)
        {
            addOnEnd(doubleList, element);
        }
        
        else
        {
            Cell *cellOnPreviousIndex = ( index == 0 ? doubleList->head : getCell(doubleList, index - 1) );

            Cell *newCell = createCell(element, cellOnPreviousIndex, cellOnPreviousIndex->right);

            cellOnPreviousIndex->right = newCell;
            newCell->right->left = newCell;
            
            doubleList->size++;
        }
    }
}

// ----------------------- FIM DA CLASSE DOUBLE_LIST

// ----------------------- INICIO DA CLASSE INSTITUICAO

/*

PUC Minas - Ciencia da Computacao     Nome: Instituicao

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 13/09/2018

*/

typedef struct Instituicao Instituicao;

void lerDados(char *dadosInstituicao, Instituicao *instituicao);
void updateDadosInstituicao(Instituicao *instituicao);

struct Instituicao
{
    int codigo;
    char nome[100];
    char sigla[20];
    int codigoMantenedora;
    char mantenedora[100];
    int categoria;
    int organizacao;
    int codigoMunicipio;
    char municipio[100];
    char uf[10];
    char regiao[20];
    int tecnico;
    int periodico;
    int livro;
    double receita;
    double transferencia;
    double outraReceita;
    double despesaDocente;
    double despesaTecnico;
    double despesaEncargo;
    double despesaCusteio;
    double despesaInvestimento;
    double despesaPesquisa;
    double despesaOutras;
    char dadosInstituicao[350];
    char dadosInstituicaoTabulado[350];
};

// ----------------------- FUNCOES DE MANIPULACAO DA STRUCT INSTITUICAO

// ----------------------- SETTERS AND GETTERS

int getCodigo(Instituicao *instituicao) {
    return instituicao->codigo;
}

void setCodigo(int codigo, Instituicao *instituicao) {
    instituicao->codigo = codigo;
}

char *getNome(Instituicao *instituicao) {
    return instituicao->nome;
}

void setNome(String *nome, Instituicao *instituicao) {
    *copyStringWithoutEndChar( nome, getNome(instituicao) ) = '\0';
}

char *getSigla(Instituicao *instituicao) {
    return instituicao->sigla;
}

void setSigla(String *sigla, Instituicao *instituicao) {
    *copyStringWithoutEndChar( sigla, getSigla(instituicao) ) = '\0';
}

int getCodigoMantenedora(Instituicao *instituicao) {
    return instituicao->codigoMantenedora;
}

void setCodigoMantenedora(int codigoMantenedora, Instituicao *instituicao) {
    instituicao->codigoMantenedora = codigoMantenedora;
}

char *getMantenedora(Instituicao *instituicao) {
    return instituicao->mantenedora;
}

void setMantenedora(String *mantenedora, Instituicao *instituicao) {
    *copyStringWithoutEndChar( mantenedora, getMantenedora(instituicao) ) = '\0';
}

int getCategoria(Instituicao *instituicao) {
    return instituicao->categoria;
}

void setCategoria(int categoria, Instituicao *instituicao) {
    instituicao->categoria = categoria;
}

int getOrganizacao(Instituicao *instituicao) {
    return instituicao->organizacao;
}

void setOrganizacao(int organizacao, Instituicao *instituicao) {
    instituicao->organizacao = organizacao;
}

int getCodigoMunicipio(Instituicao *instituicao) {
    return instituicao->codigoMunicipio;
}

void setCodigoMunicipio(int codigoMunicipio, Instituicao *instituicao) {
    instituicao->codigoMunicipio = codigoMunicipio;
}

char *getMunicipio(Instituicao *instituicao) {
    return instituicao->municipio;
}

void setMunicipio(String *municipio, Instituicao *instituicao) {
    *copyStringWithoutEndChar( municipio, getMunicipio(instituicao) ) = '\0';
}

char *getUf(Instituicao *instituicao) {
    return instituicao->uf;
}

void setUf(String *uf, Instituicao *instituicao) {
    *copyStringWithoutEndChar( uf, getUf(instituicao) ) = '\0';
}

char *getRegiao(Instituicao *instituicao) {
    return instituicao->regiao;
}

void setRegiao(String *regiao, Instituicao *instituicao) {
    *copyStringWithoutEndChar( regiao, getRegiao(instituicao) ) = '\0';
}

int getTecnico(Instituicao *instituicao) {
    return instituicao->tecnico;
}

void setTecnico(int tecnico, Instituicao *instituicao) {
    instituicao->tecnico = tecnico;
}

int getPeriodico(Instituicao *instituicao) {
    return instituicao->periodico;
}

void setPeriodico(int periodico, Instituicao *instituicao) {
    instituicao->periodico = periodico;
}

int getLivro(Instituicao *instituicao) {
    return instituicao->livro;
}

void setLivro(int livro, Instituicao *instituicao) {
    instituicao->livro = livro;
}

double getReceita(Instituicao *instituicao) {
    return instituicao->receita;
}

void setReceita(double receita, Instituicao *instituicao) {
    instituicao->receita = receita;
}

double getTransferencia(Instituicao *instituicao) {
    return instituicao->transferencia;
}

void setTransferencia(double transferencia, Instituicao *instituicao) {
    instituicao->transferencia = transferencia;
}

double getOutraReceita(Instituicao *instituicao) {
    return instituicao->outraReceita;
}

void setOutraReceita(double outraReceita, Instituicao *instituicao) {
    instituicao->outraReceita = outraReceita;
}

double getDespesaDocente(Instituicao *instituicao) {
    return instituicao->despesaDocente;
}

void setDespesaDocente(double despesaDocente, Instituicao *instituicao) {
    instituicao->despesaDocente = despesaDocente;
}

double getDespesaTecnico(Instituicao *instituicao) {
    return instituicao->despesaTecnico;
}

void setDespesaTecnico(double despesaTecnico, Instituicao *instituicao) {
    instituicao->despesaTecnico = despesaTecnico;
}

double getDespesaEncargo(Instituicao *instituicao) {
    return instituicao->despesaEncargo;
}

void setDespesaEncargo(double despesaEncargo, Instituicao *instituicao) {
    instituicao->despesaEncargo = despesaEncargo;
}

double getDespesaCusteio(Instituicao *instituicao) {
    return instituicao->despesaCusteio;
}

void setDespesaCusteio(double despesaCusteio, Instituicao *instituicao) {
    instituicao->despesaCusteio = despesaCusteio;
}

double getDespesaInvestimento(Instituicao *instituicao) {
    return instituicao->despesaInvestimento;
}

void setDespesaInvestimento(double despesaInvestimento, Instituicao *instituicao) {
    instituicao->despesaInvestimento = despesaInvestimento;
}

double getDespesaPesquisa(Instituicao *instituicao) {
    return instituicao->despesaPesquisa;
}

void setDespesaPesquisa(double despesaPesquisa, Instituicao *instituicao) {
    instituicao->despesaPesquisa = despesaPesquisa;
}

double getDespesaOutras(Instituicao *instituicao) {
    return instituicao->despesaOutras;
}

void setDespesaOutras(double despesaOutras, Instituicao *instituicao) {
    instituicao->despesaOutras = despesaOutras;
}

char *getDadosInstituicao(Instituicao *instituicao) {
    return instituicao->dadosInstituicao;
}

void setDadosInstituicao(char *dadosInstituicao, Instituicao *instituicao) {
    strcpy(instituicao->dadosInstituicao, dadosInstituicao);
}

char *getDadosInstituicaoTabulado(Instituicao *instituicao) {
    return instituicao->dadosInstituicaoTabulado;
}

void setDadosInstituicaoTabulado(char *dadosInstituicaoTabulado, Instituicao *instituicao) {
    strcpy(instituicao->dadosInstituicaoTabulado, dadosInstituicaoTabulado);
}

// ----------------------- CONSTRUTORES DA CLASSE

void iniciarInstituicao(Instituicao *instituicao)
{
    instituicao->codigo = -1;
    instituicao->nome[0] = '\0';
    instituicao->sigla[0] = '\0';
    instituicao->codigoMantenedora = -1;
    instituicao->mantenedora[0] = '\0';
    instituicao->categoria = -1;
    instituicao->organizacao = -1;
    instituicao->codigoMunicipio = -1;
    instituicao->municipio[0] = '\0';
    instituicao->uf[0] = '\0';
    instituicao->regiao[0] = '\0';
    instituicao->tecnico = -1;
    instituicao->periodico = -1;
    instituicao->livro = -1;
    instituicao->receita = -1;
    instituicao->transferencia = -1;
    instituicao->outraReceita = -1;
    instituicao->despesaDocente = -1;
    instituicao->despesaTecnico = -1;
    instituicao->despesaEncargo = -1;
    instituicao->despesaCusteio = -1;
    instituicao->despesaInvestimento = -1;
    instituicao->despesaPesquisa = -1;
    instituicao->despesaOutras = -1;
    instituicao->dadosInstituicao[0] = '\0';
    instituicao->dadosInstituicaoTabulado[0] = '\0';
}

Instituicao *criarInstituicaoVazia()
{
    Instituicao *novaInstituicao = (Instituicao *) malloc( sizeof(Instituicao) * 1 );
    
    iniciarInstituicao(novaInstituicao);
    
    return novaInstituicao;
}

Instituicao *criarInstituicao(char *dadosInstituicao)
{
    Instituicao *novaInstituicao = criarInstituicaoVazia();
    
    lerDados(dadosInstituicao, novaInstituicao);
    
    return novaInstituicao;
}

// ----------------------- OUTRAS FUNCOES DA CLASSE

/**
 * Recebe uma string que contem os dados da instituicao separados por tabulacoes
 * e, de acordo com esses dados, define os campos da classe
 * 
 * @param dadosInstituicao string com os dados da instituicao
 */

void lerDados(char *dadosInstituicao, Instituicao *instituicao)
{
    if (dadosInstituicao == NULL)
    {
        //printf("[Instituicao]: Parametro dadosInstituicao nulo - funcao lerDados(char *)\n");
    }

    else
    {
        StringArray *splitResult = split(dadosInstituicao, "\t");
        String *splitedData = getFirstString(splitResult);
        String *emptyString = createString("", 0, NULL);
        int cursor = 0;
        
        setCodigo( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setNome( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setSigla( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setCodigoMantenedora( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setMantenedora( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setCategoria( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setOrganizacao( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setCodigoMunicipio( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setMunicipio( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setUf( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setRegiao( !isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? getStringOnIndex(cursor++, splitedData) : emptyString, instituicao );
        setTecnico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setPeriodico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setLivro( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'd' ) ? atoi( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setReceita( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setTransferencia( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setOutraReceita( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaDocente( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaTecnico( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaEncargo( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaCusteio( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaInvestimento( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaPesquisa( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );
        setDespesaOutras( isSpecificString( getAddress(getStringOnIndex(cursor, splitedData)), 'f' ) ? atof( getAddress(getStringOnIndex(cursor++, splitedData)) ) : -1, instituicao );

        setDadosInstituicaoTabulado( dadosInstituicao, instituicao );
        updateDadosInstituicao(instituicao);
        
        freeStrings(emptyString);
        freeStringArray(splitResult);
    }
}

/**
 * "Percorre" os campos da classe concatenando os seus valores espacadamente.
 * Campos que tenham valores invalidos nao sao considerados.
 */

void updateDadosInstituicao(Instituicao *instituicao)
{
    int intChecker;
    char *strChecker;
    double doubleChecker;
    char *lastPos = instituicao->dadosInstituicao;
    
    if ( ( intChecker = getCodigo(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getNome(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getSigla(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getCodigoMantenedora(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( strcmp( strChecker = getMantenedora(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getCategoria(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getOrganizacao(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getCodigoMunicipio(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( strcmp( strChecker = getMunicipio(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getUf(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( strcmp( strChecker = getRegiao(instituicao), "" ) != 0 ) { lastPos = copyWithoutEndChar(strChecker, lastPos); *lastPos++ = ' '; }
    if ( ( intChecker = getTecnico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getPeriodico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( intChecker = getLivro(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = intToString(intChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getReceita(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getTransferencia(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getOutraReceita(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaDocente(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaTecnico(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaEncargo(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaCusteio(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaInvestimento(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaPesquisa(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    if ( ( doubleChecker = getDespesaOutras(instituicao) ) != -1 ) { lastPos = copyWithoutEndChar(strChecker = doubleToString(doubleChecker), lastPos); free(strChecker);  *lastPos++ = ' '; }
    
    *(--lastPos) = '\0';
}

/**
 * Le, da entrada padrao da classe AxellIO, uma string que representa textualmente
 * a instituicao e ja' define os campos da classe de acordo com os dados
 */

void ler(Instituicao *instituicao)
{
    char *line = readLine(400);
    
    lerDados( line, instituicao );
    
    free(line);
}

/**
 * Imprime uma string que representa textualmente a instituicao da classe
 */

void imprimir(Instituicao *instituicao)
{
    printf("%s", getDadosInstituicao(instituicao));
}

Instituicao *clone(Instituicao *instituicao)
{
    Instituicao *clon = (Instituicao *) malloc( sizeof(Instituicao) * 1 );
    
    *clon = *instituicao; // copia superficial !! (shallow copy)
    
    return clon;
}

char *toString(Instituicao *instituicao)
{
    return getDadosInstituicao(instituicao);
}

// ----------------------- FIM DA CLASSE INSTITUICAO

/*

PUC Minas - Ciencia da Computacao     Nome: ListaComAlocacaoSequencialEmC

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 16/09/2018

*/

// ----------------------- INICIO DA CLASSE DO EXERCICIO

/**
* Ordenada um arranjo de strings de acordo com a ordem lexicografica delas.
* 
* Para isso, essa funcao divide um pedaco do arranjo em duas partes. A
* primeira parte, mais 'a esquerda, tera' strings que precedam ou sejam iguais,
* lexicograficamente, 'a string que estiver no meio do pedaco. A segunda parte,
* mais 'a direita, tera' strings que sucedam ou sejam iguais,
* lexicograficamente, 'a string que estiver no meio do pedaco.
* 
* @param esqInicial indice de comeco do pedaco do arranjo (esquerda)
* @param dirInicial indice de fim do pedaco do arranjo (direita)
* @param institutionsList lista de instituicoes a ser dividida.
* 
* @return quantidade de comparacoes entre elementos do arranjo concatenada
* com um '\t' concatenado com a quantidade de movimentacoes entre elementos
* da lista
*/

void quicksort(DoubleList *doubleList, int esqInicial, int dirInicial, char *lastResult)
{
   char *stringPivo = getNome( (Instituicao *) getCell(doubleList, (esqInicial + dirInicial) / 2)->element );
   int esquerda = esqInicial;
   int direita = dirInicial;
   char *stringEsq, *stringDir;

   StringArray *lastResultSplited = split(lastResult, "\t");
   int numberOfComparisons = atoi(getAddress(getFirstString(lastResultSplited)));
   int numberOfMovimentations = atoi(getAddress(getNextString(getFirstString(lastResultSplited))));
   
   freeStringArray(lastResultSplited);
   
   while (esquerda <= direita)
   {
       stringEsq = getNome( (Instituicao *) getCell(doubleList, esquerda)->element );
       stringDir = getNome( (Instituicao *) getCell(doubleList, direita)->element );

       numberOfComparisons++;
       while (strcmp(stringEsq, stringPivo) < 0 && esquerda < dirInicial)
       {
           esquerda++;
           stringEsq = getNome( (Instituicao *) getCell(doubleList, esquerda)->element );
           numberOfComparisons++;
       }

       numberOfComparisons++;
       while (strcmp(stringDir, stringPivo) > 0 && direita > esqInicial)
       {
           direita--;
           stringDir = getNome( (Instituicao *) getCell(doubleList, direita)->element );
           numberOfComparisons++;
       }

       /*
       Sempre, sem nenhuma excecao, todos os elementos que estiverem antes
       de "esquerda" sao os menores. Da mesma forma, todos os elementos
       depois de "direita" sao os maiores. Portanto, se "esquerda" passou
       de "direita", com certeza "esquerda" esta' em cima de um dos elementos
       maiores, entao eu nao posso pegar o elemento onde "esquerda" esta' e
       troca'-lo com outro de qualquer lugar, pois ela esta' no lugar correto
       (depois de "direita" !). Dessa forma, esse if e' necessario.
       */
       if (esquerda <= direita)
       {
           numberOfMovimentations += 3;
           swap(doubleList, esquerda++, direita--);
       }
   }
   
   sprintf(lastResult, "%d\t%d", numberOfComparisons, numberOfMovimentations);

   if (direita > esqInicial)
   {
       quicksort(doubleList, esqInicial, direita, lastResult);
   }

   if (esquerda < dirInicial)
   {
       quicksort(doubleList, esquerda, dirInicial, lastResult);
   }
}

/**
* Ordenada um arranjo de strings de acordo com a ordem lexicografica delas.
*/

char *startQuicksort(DoubleList *doubleList)
{
   char *result = (char *) malloc(sizeof(char) * 15);
   sprintf(result, "0\t0");

   quicksort(doubleList, 0, size(doubleList) - 1, result);

   return result;
}

void printInstitutions(DoubleList *doubleList)
{
    int listSize = size(doubleList);
    int i;
    
    for (i = 0; i < listSize; i++)
    {
        imprimir( (Instituicao *) getCell(doubleList, i)->element );
        puts("");
    }
}

int main()
{
    clock_t clocksOnStart = clock();
    DoubleList *doubleList = createDoubleList();
    
    FILE *censo = fopen("/tmp/censo.dat", "r"); // abre o arquivo censo.dat

    // le, da entrada padrao, o numero da linha da primeira instituicao no censo.dat
    int lineNumber = readInt();
    char *fileLine; // guardara' a linha da instituicao que for lida do censo.dat
    
    while (lineNumber != 0)
    {
        // vai ate a linha sucessora da linha informada na entrada padrao
        // e pega as informacoes
        fileLine = getFileLine(lineNumber + 1, censo);
        
        // cria a instituicao e adiciona-a 'a lista de instituicoes.
        addOnEnd( doubleList, criarInstituicao( fileLine ) );
        
        free(fileLine);
        
        // le, da entrada padrao, o numero da linha da proxima instituicao no censo.dat
        lineNumber = readInt();
    }
    
    char *ordenationResult = startQuicksort(doubleList);
    StringArray *splitedResult = split(ordenationResult, "\t");
    free(ordenationResult);
    
    int numberOfComparisons = atoi(getAddress(getFirstString(splitedResult)));
    int numberOfMovimentations = atoi(getAddress(getNextString(getFirstString(splitedResult))));
    freeStringArray(splitedResult);
    
    fclose(censo);
    printInstitutions(doubleList);
    freeDoubleList(doubleList);
    
    clock_t clocksOnEnd = clock();
    double passedSeconds = (double) (clocksOnEnd - clocksOnStart) / CLOCKS_PER_SEC;
    
    FILE *log = fopen("matrícula_quicksortemc.txt", "w");
    fprintf(log, "631822\t%d\t%d\t%f", numberOfComparisons, numberOfMovimentations, passedSeconds * 1000);
    fclose(log);
    
    return EXIT_SUCCESS;
}

// ----------------------- FIM DA CLASSE DO EXERCICIO
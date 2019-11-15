/*

PUC Minas - Ciencia da Computacao     Nome: mystring.h

Autor: Axell Brendow                  Matricula: 631822

Versao:  1.0                          Data: 12/05/2018

*/

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>

/**
 * Coloca uma string a partir do ponteiro recebido, porem,
 * nao coloca o caractere '\0' no final
 *
 * @param *string string que se deseja colocar a partir do ponteiro
 * @param *destination ponteiro que indicara' onde sera' o inicio da string
 *
 * @return um ponteiro para onde seria o '\0' da string
 */

char *copyWithoutEndChar(char *string, char *destination)
{
    while (*string) // copia os caracteres da string ate' antes do caractere \0
    {
        *destination++ = *string++;
    }
    
    // retorna o primeiro espaco livre para uma possivel futura concatenacao de outras strings
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

    // faco com que o ponteiro recebido passe a apontar para um novo espaco
    // que suporte a string antiga e a nova e o caractere finalizador '\0'
    *destination = (char *) malloc(strlen(oldStr) + strlen(string) + 1);
    
    if (*destination == NULL) // checa se a alocacao falhou
    {
        fprintf(stderr, "Nao foi possivel alocar espaco para guardar a string. - funcao concatString(const char *, char **)");
    }

    else
    {
        strcpy(*destination, oldStr); // coloca a string antiga no novo espaco

        strcat(*destination, string); // concatena a nova string ao novo espaco
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

int isLowerCase(int x)
{
    if (x >= 'a' && x <= 'z') return 1;

    else return 0;
}

int isUpperCase(int x)
{
    if (x >= 'A' && x <= 'Z') return 1;

    else return 0;
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
    char *str = (char *) malloc(getIntLength(x) + 1);
    
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

void replace(char find, char replace, char *string)
{
    char *searchedChar = strchr(string, find); // obtem um ponteiro para a primeira ocorrencia de "find"

    while (searchedChar != NULL) // checa se realmente existe o caractere
    {
        *searchedChar = replace; // se existir, substitui-o pelo "replace"

        searchedChar = strchr(searchedChar + 1, find); // obtem um ponteiro para a primeira ocorrencia de "find" apos o caractere encontrado
    }
}

char *readLine(int maximumOfCharacters)
{
    char *inputBuffer = (char *) malloc( maximumOfCharacters * sizeof(char) ); // cria um buffer para a entrada com o tamanho maximo especificado
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

        free(inputBuffer); // libera o espaco do buffer
    }

    return line; // retorna a cadeia
}

FILE *setSystemIn(FILE *newIn)
{
    if (newIn == NULL)
    {
        fprintf(stderr, "Arquivo de entrada padrao nulo. - funcao setSystemIn(File *)");
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
        fprintf(stderr, "Nao foi possivel abrir o arquivo. - funcao openFileAndSetItToSystemIn(const char *, const char *)");
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
        fprintf(stderr, "Arquivo de saida padrao nulo. - funcao setSystemOut(File *)");
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
        fprintf(stderr, "Nao foi possivel abrir o arquivo. - funcao openFileAndSetItToSystemOut(const char *, const char *)");
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
        fprintf(stderr, "O endereco do arquivo nao existe. - funcao getFileSize(File *)");
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
        fpos_t startPosition = cursorPosition; // guarda a posicao inicial

        fsetpos(file, &cursorPosition); // coloca a posicao do cursor na posicao inicial

        int seekSuccess = fseek(file, 0, SEEK_CUR) == 0; // tenta mover o cursor para a posicao atual

        if (seekSuccess) // se foi possivel,
        {
            copyString("", &fileLine); // inicia uma string vazia para a linha
            
            char currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao

            while (currentChar != '\n' && currentChar != '\r' && currentChar != EOF) // executa enquanto nao achar um fim de linha ou fim de arquivo
            {
                concatChar(currentChar, &fileLine); // vai concatenando os caracteres 'a cadeia da linha

                currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao
            }

            fsetpos(file, &startPosition); // volta a posicao do cursor para a posicao inicial
        }

        else
        {
            fprintf(stderr, "Nao foi possivel mover o cursor do arquivo para a posicao desejada. - funcao printFileLineFrom(fpos_t, File *)");
        }
    }

    else
    {
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)");
    }
    
    return fileLine;
}

char *getFileLine(FILE *file)
{
    fpos_t filePosition;

    fgetpos(file, &filePosition); // obtem a posicao atual do cursor

    return getFileLineFrom(filePosition, file); // imprime a linha a partir dessa posicao
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
    }

    else
    {
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)");
    }
}

void printFileLine(FILE *file)
{
    fpos_t filePosition;

    fgetpos(file, &filePosition); // obtem a posicao atual do cursor

    printFileLineFrom(filePosition, file); // imprime a linha a partir dessa posicao
}

void runFileBackwardPrintingLines(FILE *file)
{
    int fileSize = getFileSize(file);
    int seekSuccess;
    char currentChar;
    int i;

    for (i = 0; i < fileSize; i++)
    {
        seekSuccess = fseek(file, fileSize - 1 - i, SEEK_SET) == 0; // coloca o cursor do arquivo no inicio e, entao, desloca-o ate o fim

        if (seekSuccess) // checa se foi possivel mover o cursor
        {
            currentChar = fgetc(file); // pega o caractere na posicao do cursor e passa o cursor para a proxima posicao

            if (currentChar == '\n' || currentChar == '\r') // checa se e' um caractere de fim de linha
            {
                printFileLine(file); // imprime a linha a partir da posicao do cursor
            }
        }

        else
        {
            fprintf(stderr, "Nao foi possivel mover o cursor para o caractere %d do arquivo. - funcao runFileBackwardPrintingLines(File *)", fileSize - 1 - i);
        }
    }
	
	fseek(file, 0, SEEK_SET); // coloca o cursor no inicio do arquivo
    printFileLine(file); // imprime a primeira linha do arquivo
}

void *treatFloatNumberString(char **floatString)
{
    if (*floatString == NULL)
    {
        fprintf(stderr, "String nula. - funcao treatFloatNumberString(char *)");
    }
    
    else
    {
        char *dotChar = strchr(*floatString, '.'); // procura o primeiro '.' na string
        
        if (dotChar) // checa se algum '.' existe
        {
            if (dotChar == *floatString) // checa se a string do numero esta comecando com um '.'
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
        }
    }
}
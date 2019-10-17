#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define true 1
#define false 0

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
            fprintf(stderr, "Nao foi possivel mover o cursor do arquivo para a posicao desejada. - funcao printFileLineFrom(fpos_t, File *)");
        }
    }

    else
    {
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)");
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
        fprintf(stderr, "Arquivo nulo. - funcao printFileLineFrom(fpos_t, File *)");
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
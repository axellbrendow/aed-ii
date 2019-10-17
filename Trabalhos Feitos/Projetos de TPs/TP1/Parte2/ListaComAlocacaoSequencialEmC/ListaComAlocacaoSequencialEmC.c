#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*

PUC Minas - Ciencia da Computacao     Nome: ListaComAlocacaoSequencialEmC

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 16/09/2018

*/

// ----------------------- INICIO DA CLASSE DO EXERCICIO

void printInstitutions()
{
    int listSize = size();
    int i;
    
    for (i = 0; i < listSize; i++)
    {
        imprimir( (Instituicao *) listGet(i) );
        puts("");
    }
}

int main()
{
    StartListWithLength(130, sizeof(Instituicao));
    
    FILE *censo = fopen("censo.dat", "r"); // abre o arquivo censo.dat

    // le, da entrada padrao, o numero da linha da primeira instituicao no censo.dat
    int lineNumber = readInt();
    char *fileLine; // guardara' a linha da instituicao que for lida do censo.dat
    
    while (lineNumber != 0)
    {
        // vai ate a linha sucessora da linha informada na entrada padrao
        // e pega as informacoes
        fileLine = getFileLine(lineNumber + 1, censo);
        
        // cria a instituicao e adiciona-a 'a lista de instituicoes.
        addOnEnd( criarInstituicao( fileLine ) );
        
        free(fileLine);
        
        // le, da entrada padrao, o numero da linha da proxima instituicao no censo.dat
        lineNumber = readInt();
    }

    Instituicao *instituicaoAtual;
    int numberOfCommands = readInt();
    char *currentCommand;
    StringArray *splitResult;
    String *commandOperands;

    for (int i = 0; i < numberOfCommands; i++)
    {
        currentCommand = readLine(20);
        splitResult = split(currentCommand, " ");
        free(currentCommand);
        
        commandOperands = getFirstString(splitResult);

        if (commandOperands->address[0] == 'I')
        {
            if (strcmp(commandOperands->address, "I*") == 0)
            {
                // obtem a linha da instituicao no censo.dat
                lineNumber = atoi(commandOperands->nextString->nextString->address);
            }

            else
            {
                // obtem a linha da instituicao no censo.dat
                lineNumber = atoi(commandOperands->nextString->address);
            }
            
            fileLine = getFileLine(lineNumber + 1, censo);
            
            instituicaoAtual = criarInstituicao( fileLine );
            
            free(fileLine);
            
            if (strcmp(commandOperands->address, "II") == 0)
            {
                addOnStart(instituicaoAtual);
            }

            else if (strcmp(commandOperands->address, "IF") == 0)
            {
                addOnEnd(instituicaoAtual);
            }

            else if (strcmp(commandOperands->address, "I*") == 0)
            {
                add(instituicaoAtual, atoi(commandOperands->nextString->address));
            }
        }

        else if (commandOperands->address[0] == 'R')
        {
            if (strcmp(commandOperands->address, "RI") == 0)
            {
                printf( "(R) %s\n", getNome( (Instituicao *) removeOnStart() ) );
            }

            else if (strcmp(commandOperands->address, "RF") == 0)
            {
                printf( "(R) %s\n", getNome( (Instituicao *) removeOnEnd() ) );
            }

            else if (strcmp(commandOperands->address, "R*") == 0)
            {
                printf( "(R) %s\n", getNome( (Instituicao *) removeFrom( atoi(commandOperands->nextString->address) ) ) );
            }
        }
        
        freeStringArray(splitResult);
    }
    
    fclose(censo);
    printInstitutions();
    freeList();
    
    return EXIT_SUCCESS;
}

// ----------------------- FIM DA CLASSE DO EXERCICIO
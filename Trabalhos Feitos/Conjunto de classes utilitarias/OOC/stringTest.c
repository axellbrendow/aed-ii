#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "object.h"

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: string_test

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 02/10/2018

*/

// ----------------------- INICIO DA CLASSE STRING_TEST

#define charSize sizeof(char)

struct _String
{
    const void *myClass;
    char *strPtr;
    int length;
};

/**
 * Recebe um ponteiro para um endereco de memoria que foi alocado para
 * a estrutura da String e tem como outros dois parametros um ponteiro
 * para uma string base de onde a nova string sera' tirada e a quantidade
 * de caracteres da string base que devem ser pegos.
 * 
 * @param ptrToMemoryBlock ponteiro para bloco de memoria alocado
 * @param args ponteiro para lista de parametros com dois parametros:
 * 1 - ponteiro para uma string base de onde a nova sera' retirada
 * 2 - quantidade de caracteres da string base a serem copiados
 */

static void String_construct(void *_ptrToMemoryBlock, va_list *args)
{
    struct _String *me = (struct _String *) _ptrToMemoryBlock;

    // obtem um ponteiro para a string base
    const char *baseStr = va_arg(*args, const char *);
    
    // obtem a quantidade de caracteres da string base que deseja-se pegar
    int wantedLength = va_arg(*args, int);

    if (baseStr != NULL) // checa se a string base existe
    {
        if (wantedLength >= 0)
        {
            // aloca espaco para a string
            me->strPtr = (char *) malloc(charSize * wantedLength);
            me->length = wantedLength; // guarda o tamanho da string

            // imprime os caracteres da string base no endereco alocado
            sprintf(me->strPtr, "%.*s", wantedLength, baseStr);
        }
    }
}

static void String_destruct(void *ptrToString)
{
    struct _String *me = (struct _String *) ptrToString;
    
    free(me->strPtr);
    me->strPtr = NULL;
}

// cria a classe String
static const Class __String =
{
    sizeof(struct _String),
    String_construct,
    String_destruct
};

const void *String = &__String;

int main()
{
    struct _String *myStr = cnew(String, "abcdef", 10);
    
    printf("'%s'\n", myStr->strPtr);
    printf("'%zu'\n", sizeOf(myStr));
    
    cdelete(myStr);
    
    return EXIT_SUCCESS;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include "object.h"

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: OBJECT

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 10/10/2018

*/

// ----------------------- INICIO DA CLASSE OBJECT

const void *classOf(void *_objectPtr)
{
    if (_objectPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao classOf(void *)\n");
        
        return NULL;
    }
    
    else
    {
        return ( (struct Object *) _objectPtr )->myClass;
    }
}

void *destruct(void *_objectPtr)
{
    const struct Class *classPtr = classOf(_objectPtr);
    void *ptrToFree = NULL;
    
    if (classPtr->destruct == NULL)
    {
        fprintf(stderr, "[object]: Nao existe destrutor para o objeto. - funcao destruct(void *)\n");
    }
    
    else
    {
        ptrToFree = classPtr->destruct(_objectPtr);
    }
    
    return ptrToFree;
}

void cdelete(void *_objectPtr)
{
    if (_objectPtr != NULL)
    {
        free( destruct(_objectPtr) );
    }
}

void *construct(void *_objectPtr, va_list *args)
{
    const struct Class *classPtr = classOf(_objectPtr);
    void *ptrToUser = NULL;
    
    if (classPtr->construct == NULL)
    {
        fprintf(stderr, "[object]: Nao existe construtor para o objeto. - funcao construct(void *, va_list *)\n");
    }
    
    else
    {
        ptrToUser = classPtr->construct(_objectPtr, args);
    }
    
    return ptrToUser;
}

void *cnew(const void *_classPtr, ...)
{
    struct Object *objectPtr = NULL;

    if (_classPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo para a classe. - funcao cnew(const void *, ...)\n");
    }

    else
    {
        const struct Class *classPtr = (const struct Class *) _classPtr;
        
        objectPtr = (struct Object *) malloc(classPtr->size);

        if (objectPtr == NULL)
        {
            fprintf(stderr, "[object]: Nao foi possivel alocar espaco para o objeto. - funcao cnew(const void *, ...)\n");
        }

        else
        {
            objectPtr->myClass = classPtr;
            
            va_list args;
            va_start(args, _classPtr);

            objectPtr = construct(objectPtr, &args);

            va_end(args);
        }
    }

    return objectPtr;
}

size_t sizeOf(void *_objectPtr)
{
    size_t objectSize = 0;
    
    if (_objectPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao sizeOf(void *)\n");
    }
    
    else
    {
        const struct Class *objectClass = classOf(_objectPtr);

        if (objectClass != NULL)
        {
            objectSize = objectClass->size;
        }
    }
    
    return objectSize;
}

char *toString(void *_objectPtr)
{
    char *textRepresentation = NULL;
    
    if (_objectPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao toString(void *)\n");
    }
    
    else
    {
        int bufferSize = 100;
        char text[bufferSize];
        const struct Class *objectClass = classOf(_objectPtr);
        
        snprintf(text, bufferSize, "%s at %p", objectClass->name, _objectPtr);
        
        textRepresentation = strdup(text);
    }
    
    return textRepresentation;
}

const void *superOf(const void *_classPtr)
{
    if (_classPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao superOf(void *)\n");
        
        return NULL;
    }
    
    else
    {
        const struct Class *classPtr = (const struct Class *) _classPtr;
        
        return classPtr->superClass; // lancar excecao caso nao tenha super ?
    }
}

void *super_construct(const void *_classPtr, void *_objectPtr, va_list *args)
{
    void *ptrToUser = NULL;
    
    if (_objectPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao Super_construct(const void *, void *, va_list *)\n");
    }
    
    else
    {
        const struct Class *superClass = superOf(_classPtr);
        
        if (superClass->construct == NULL)
        {
            fprintf(stderr, "[object]: A classe pai nao tem construtor. - funcao Super_construct(const void *, void *, va_list *)\n");
        }
        
        else
        {
            ptrToUser = superClass->construct(_objectPtr, args);
        }
    }
    
    return ptrToUser;
}

void *super_destruct(const void *_classPtr, void *_objectPtr)
{
    void *ptrToUser = NULL;
    
    if (_objectPtr == NULL)
    {
        fprintf(stderr, "[object]: Ponteiro nulo. - funcao Super_destruct(const void *, void *)\n");
    }
    
    else
    {
        const struct Class *superClass = superOf(_classPtr);
        
        if (superClass->destruct == NULL)
        {
            fprintf(stderr, "[object]: A classe pai nao tem destrutor. - funcao Super_destruct(const void *, void *)\n");
        }
        
        else
        {
            ptrToUser = superClass->destruct(_objectPtr);
        }
    }
    
    return ptrToUser;
}

static void *Class_destruct(const void *_classPtr)
{
    const struct Class *classPtr = (const struct Class *) _classPtr;
    
    fprintf(stderr, "[%s]: Nao e' permitido destruir a classe.\n", classPtr->name);
    
    return NULL;
}

static void *Class_construct(void *_classPtr, va_list *args)
{
    struct Class *classPtr = (struct Class *) _classPtr;
    
    classPtr->name = va_arg(*args, char *);
    classPtr->superClass = va_arg(*args, struct Class *);
    classPtr->size = va_arg(*args, size_t);
    
    if (classPtr->superClass == NULL)
    {
        fprintf(stderr, "[%s]: Nao foi possivel herdar a classe pai.\n", classPtr->name);
    }
    
    else
    {
        size_t constructOffset = offsetof(struct Class, construct);
        
        memcpy
        (
            (char *) classPtr + constructOffset,
            (char *) classPtr->superClass + constructOffset,
            sizeOf(classPtr->superClass) - constructOffset
        ); // herda as funcoes da classe pai
        
        typedef void (*ptrToVoidFunc) ();
        ptrToVoidFunc selector;
        ptrToVoidFunc function;
        va_list argsCopy = *args; // usa uma copia para que outros construtores tenham acesso aos seletores
        
        // percorre os seletores que indicam quais funcoes da classe pai
        // deve-se sobrescrever
        while ( (selector = va_arg(argsCopy, ptrToVoidFunc)) )
        {
            function = va_arg(argsCopy, ptrToVoidFunc); // obtem a funcao a ser colocada no lugar
            
            switch (selector)
            {
                case ( (ptrToVoidFunc) construct ):
                    *((ptrToVoidFunc *) &classPtr->construct) = function;
                    break;
                    
                case ( (ptrToVoidFunc) destruct ):
                    *((ptrToVoidFunc *) &classPtr->destruct) = function;
                    break;
                    
                case ( (ptrToVoidFunc) toString ):
                    *((ptrToVoidFunc *) &classPtr->toString) = function;
                    break;
            }
        }
    }
    
    return classPtr;
}

static const Class object [] =
{
    {
        { object + 1 }, // objeto da classe
        "Object", // nome da classe
        object, // classe pai
        sizeof(struct Object),
        construct, destruct, toString
    },
    
    {
        { object + 1 }, // objeto da classe
        "Class", // nome da classe
        object, // classe pai
        sizeof(struct Class),
        Class_construct, Class_destruct, toString
    }
};

const void *Object = object;
const void *Class = object + 1;
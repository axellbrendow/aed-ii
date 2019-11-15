#include <stdio.h>
#include <stdlib.h>

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: OO_ESSENTIAL

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 29/09/2018

*/

// ----------------------- INICIO DA CLASSE OO_ESSENTIAL

typedef struct Class Class;

struct Class
{
    size_t type_size;
    void (*construct) (const void *_type, va_list *params);
    void (*destruct) (const void *_objectPointer);
};

void *cnew(const void *_type, ...)
{
    const Class *typeClass = (const Class *) type;
    
    void *newObjectPointer = malloc(typeClass->type_size);
    
    *((const Class**) newObjectPointer) = typeClass;
    
    if (typeClass->construct != NULL)
    {
        va_list args;
        va_start(args, _type);
        
        typeClass->construct(newObjectPointer, &args);
        
        va_end(args);
    }
    
    return newObjectPointer;
}

void cdelete(const void *_objectPointer)
{
    const Class **pointerToClassPointer = _objectPointer;
	
    if (_objectPointer != NULL && *classPointer != NULL && )
}

// ----------------------- FUNCOES DE MANIPULACAO DA STRUCT ERROR

int getErrorCode(Error *error)
{
    return error->errorCode;
}

void setErrorCodeOf(Error *error, int errorCode)
{
    error->errorCode = errorCode;
}

void setNextErrorOf(Error *error, Error *nextError)
{
    error->nextError = nextError;
}

Error *getNextError(Error *error)
{
    return error->nextError;
}

void setErrorFieldsTo(int errorCode, Error *nextError, Error *errorToChange)
{
    setErrorCodeOf(errorToChange, errorCode);
    setNextErrorOf(errorToChange, nextError);
}

// ----------------------- ATRIBUTOS DA CLASSE

Error firstError;
Error *lastError;
int numberOfErrors;

// ----------------------- CONSTRUTORES DA CLASSE

void StartError()
{
    setErrorFieldsTo(0, NULL, &firstError);
    lastError = &firstError;
    numberOfErrors = 0;
}

// ----------------------- GETTERS AND SETTERS

Error *getFirstError()
{
    return &firstError;
}

void setFirstError(Error *_firstError)
{
    if (_firstError == NULL)
    {
        //printf("[Error]: Ponteiro para o primeiro erro nulo. - funcao setFirstError(Error *)\n");
    }
    
    else
    {
        firstError = *_firstError;
    }
}

Error *getLastError()
{
    return lastError;
}

void setLastError(Error *_lastError)
{
    if (_lastError == NULL)
    {
        //printf("[Error]: Ponteiro para o ultimo erro nulo. - funcao setFirstError(Error *)\n");
    }
    
    else
    {
        lastError = _lastError;
    }
}

int getNumberOfErrors()
{
    return numberOfErrors;
}

void setNumberOfErrors(int _numberOfErrors)
{
    if (_numberOfErrors < 0)
    {
        //printf("[Error]: Numero de erros < 0. (_numberOfErrors = %d) - funcao setNumberOfErrors(int)\n", _numberOfErrors);
    }
    
    else
    {
        numberOfErrors = _numberOfErrors;
    }
}

void increaseNumberOfErrors()
{
    setNumberOfErrors(getNumberOfErrors() + 1);
}

void decreaseNumberOfErrors()
{
    setNumberOfErrors(getNumberOfErrors() - 1);
}

// ----------------------- OUTROS FUNCOES DA CLASSE

int hasError()
{
    return getNumberOfErrors() > 0;
}

/**
 * Percorre a lista de erros procurando o erro que esta' antes do erro que
 * tem o codigo de erro especificado.
 * 
 * @param errorCode codigo de erro a ser buscado
 * 
 * @return o erro que esta' antes do erro que tem o codigo de erro especificado.
 * Caso contrario, NULL.
 */

Error *getErrorBeforeTheErrorWithErrorCode(int errorCode)
{
    Error *searchedError = NULL;
    Error *currentError = getFirstError();
    Error *nextError;
    
    while(currentError != NULL && searchedError == NULL)
    {
        nextError = currentError->nextError;
        
        if (nextError == NULL) // checa se nao existe o proximo erro
        {
            currentError = NULL; // interrompe o while
        }
        
        // checa se o proximo erro tem o codigo a ser buscado
        else if (nextError->errorCode == errorCode)
        {
            // se sim, guarda o erro atual pois ele e' o que precede o proximo
            searchedError = currentError;
        }
        
        else
        {
            currentError = nextError; // o erro atual passa a ser o seu proximo erro
        }
    }
    
    return searchedError;
}

/**
 * Percorre a lista de erros procurando o erro que tem o codigo de erro especificado.
 * 
 * @param errorCode codigo de erro a ser buscado
 * 
 * @return o erro que tem o codigo de erro especificado. Caso contrario, NULL.
 */

Error *getErrorWithErrorCode(int errorCode)
{
    Error *searchedError = NULL;
    Error *currentError = getFirstError();
    
    while(currentError != NULL && searchedError == NULL)
    {
        if (currentError->errorCode == errorCode)
        {
            searchedError = currentError;
        }
        
        else
        {
            currentError = currentError->nextError;
        }
    }
    
    return searchedError;
}

/*
int indexOf(int errorCode)
{
    int index = 0;
    Error *currentError = getFirstError();
    
    while(currentError != NULL && currentError->errorCode != errorCode)
    {
        index++;
        
        currentError = currentError->nextError;
    }
    
    if (index == getNumberOfErrors())
    {
        index = -1;
    }
    
    return index;
}
*/

/**
 * Remove, da lista de erros, todos os erros que tenham o codigo de erro informado
 * 
 * @param errorCode codigo de erro a ser procurado
 */

void removeError(int errorCode)
{
    Error *errorBeforeTheErrorWithTheErrorCode = getErrorBeforeTheErrorWithErrorCode(errorCode);
    Error *errorWithTheErrorCode;
    
    while (errorBeforeTheErrorWithTheErrorCode != NULL)
    {
        errorWithTheErrorCode = errorBeforeTheErrorWithTheErrorCode->nextError;
        
        errorBeforeTheErrorWithTheErrorCode->nextError = errorWithTheErrorCode->nextError;
        
        decreaseNumberOfErrors();
        
        errorBeforeTheErrorWithTheErrorCode = getErrorBeforeTheErrorWithErrorCode(errorCode);
    }
}

/**
 * Adiciona um erro 'a lista de erros
 * 
 * @param errorCode codigo de erro do erro
 */

void addError(int errorCode)
{
    Error *newError = (Error *) malloc(sizeof(Error) * 1);
    
    setErrorFieldsTo(errorCode, NULL, newError);
    
    setNextErrorOf(lastError, newError);
    setLastError(newError);
    
    increaseNumberOfErrors();
}

void freeErrors()
{
    Error *_firstError = firstError.nextError;
    Error *nextError; // guardara' o proximo erro do primeiro erro
    
    // como a cada iteracao o primeiro erro vai mudando, so' para quando
    // o primeiro erro for nulo
    while (_firstError != NULL)
    {
        // obtem o proximo erro
        nextError = getNextError(_firstError);
        
        free(_firstError); // libera o primeiro erro
        
        _firstError = nextError; // o primeiro erro passa a ser o proximo
    }
}

// ----------------------- FIM DA CLASSE ERROR
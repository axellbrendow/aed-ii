#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: List

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 11/09/2018

*/

// ----------------------- INICIO DA CLASSE LIST

void updateSize();
void *listGet(int index);

// ----------------------- ATRIBUTOS DA CLASSE

int _size;
int _isMoving;

// ----------------------- CONSTRUTORES DA CLASSE

void StartListWithLength(int length, int _sizeOfElements)
{
    StartArrayWithLength(length, _sizeOfElements);
    
    _size = 0;
}

void StartList(int _sizeOfElements)
{
    StartListWithLength(10, _sizeOfElements); // 10 = tamanho padrao da lista
}

void StartListWithList(void **list, int size, int _sizeOfElements)
{
    StartArrayWithArray(list, size, _sizeOfElements);
    
    updateSize();
}

// ----------------------- FUNCOES DA CLASSE

int getIsMoving()
{
    return _isMoving;
}

void setIsMoving(int isMoving)
{
    _isMoving = isMoving;
}

int isMoving()
{
    return getIsMoving();
}

int size()
{
    return _size;
}

void setSize(int size)
{
    if (size < 0 || size > length())
    {
        //printf("[List]: Parametro size < 0 ou maior que o tamanho do arranjo da lista (size = %d) - funcao setSize(int)\n", size);
    }
    
    else
    {
        _size = size;
    }
}

void sumToSize(int quantity)
{
    setSize(size() + quantity);
}

void incrementSize()
{
    sumToSize(1);
}

void decrementSize()
{
    sumToSize(-1);
}

void updateSize()
{
    int size = length(); // presume que a quantidade de elementos seja o tamanho maximo
    int i;
    
    for (i = 0; i < size; i++) // percorre a lista
    {
        if (listGet(i) == NULL) // checa se cada um dos elementos e' nulo
        {
            // ao encontrar um elemento nulo, a quantidade de elementos sera' o numero sucessor ao indice dele
            size = i + 1;
        }
    }
    
    setSize(size); // atualiza a quantidade de elementos
}

void *listGet(int index)
{
    void *result;
    
    if (limitOverflowOnIndex(index, size()) != 0)
    {
        //printf("[List]: Parametro index invalido. - funcao *listGet(int)\n");
        
        result = NULL;
    }
    
    else
    {
        result = arrayGet(index);
    }
    
    return result;
}

void listSet(void *element, int index)
{
    if (element == NULL)
    {
        //printf("[List]: Parametro element nulo. - funcao listSet(void *, int)\n");
    }
    
    else if (!isMoving() && limitOverflowOnIndex(index, size()) != 0)
    {
        //printf("[List]: Parametro index invalido. (index = %d) - funcao listSet(void *, int)\n", index);
    }
    
    else
    {
        arraySet(element, index);
    }
}

/**
 * Remove determinado elemento da lista
 * 
 * @param index indice do elemento
 * 
 * @return elemento removido
 */

void *removeFrom(int index)
{
    void *removedElement;
    int listSize = size();
    
    if (index >= listSize || index < 0)
    {
        //printf("[List]: Parametro index invalido. (index = %d) - funcao remove(int)\n", index);
        removedElement = NULL;
    }
    
    else
    {
        removedElement = listGet(index); // guarda o elemento que esta' no indice
        
        if (index != listSize - 1) // checa se o indice de remocao nao e' o ultimo da lista
        {
            setIsMoving(true);
            moveElements1TimeToLeftFrom(index + 1, listSize - index + 1);
            setIsMoving(false);
        }
        
        decrementSize();
    }
    
    return removedElement;
}

void *removeOnStart()
{
    return removeFrom(0);
}

void *removeOnEnd()
{
    return removeFrom(size() - 1);
}

/**
 * Adiciona um novo elemento 'a lista no indice informado. Se a lista nao
 * tiver espaco, o seu tamanho e' dobrado e entao o elemento
 * e' adicionado.
 * 
 * @param element elemento a ser adicionado
 * @param index indice de insercao
 */

void add(void *element, int index)
{
    int listSize = size();
    
    if (element == NULL)
    {
        //printf("[List]: Elemento nulo. - funcao add(void *, int)\n");
    }
    
    else if (limitOverflowOnIndex(index, listSize + 1) != 0)
    {
        //printf("[List]: Parametro index invalido. (index = %d) - funcao add(void *, int)\n", index);
    }
    
    else
    {
        int arrLength = length();
        
        if (listSize == arrLength)
        {
            void **arrayCopyFromLeft = copyArrayPiece(0, index);
            void **arrayCopyFromRight = copyArrayFrom(index);
            
            StartArrayWithLength( listSize * 2, sizeof(void *) );

            copyToArray(0, arrayCopyFromLeft, index);
            copyToArray(index + 1, arrayCopyFromRight, arrLength - index);
            
            freeMyArray(arrayCopyFromLeft, index);
            freeMyArray(arrayCopyFromRight, arrLength - index);
        }

        else
        {
            setIsMoving(true);
            moveElements1TimeToRightFrom(listSize - 1, listSize - index);
            setIsMoving(false);
        }

        incrementSize();
        
        listSet(element, index);
    }
}

void addOnStart(void *element)
{
    add(element, 0);
}

void addOnEnd(void *element)
{
    add(element, size());
}

void freeList()
{
    freeMyArray(getArray(), size());
}

// ----------------------- FIM DA CLASSE LIST
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define true 1
#define false 0

/*

PUC Minas - Ciencia da Computacao     Nome: Array

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 11/09/2018

*/

// ----------------------- INICIO DA CLASSE ARRAY

void setAllElementsTo(void *element);
void freeArray();

// ----------------------- ATRIBUTOS DA CLASSE

void **array;
int arrayLength;
int sizeOfElements;

// ----------------------- CONSTRUTORES DA CLASSE

void StartArray()
{
    array = NULL;
    arrayLength = -1;
    sizeOfElements = -1;
    StartError();
}

void StartArrayWithSizeOfElements(int _sizeOfElements)
{
    StartArray();
    
    int errorCode = 1;
    
    if (_sizeOfElements < 0)
    {
        //printf("[Array]: Tamanho dos elementos do arranjo < 0. (_sizeOfElements = %d) - StartArrayWithSizeOfElements(int)\n", _sizeOfElements);
        
        addError(errorCode);
    }

    else
    {
        sizeOfElements = _sizeOfElements;
        
        removeError(errorCode);
    }
}

void StartArrayWithLength(int length, int _sizeOfElements)
{
    StartArrayWithSizeOfElements(_sizeOfElements);
    
    if (!hasError())
    {
        int errorCode = 2;

        if (length < 0)
        {
            //printf("[Array]: Tamanho do arranjo < 0. (length = %d) - StartArrayWithLength(int, int)\n", length);

            addError(errorCode);
        }

        else
        {
            freeArray(); // se o arranjo ja' existir, libera o endereco de todos elementos
            
            // cria um novo arranjo com o tamanho especificado
            void **newArray = (void **) malloc( sizeof(void *) * length );
            
            // passa o endereco do arranjo como o endereco do arranjo da classe
            array = newArray;
            
            arrayLength = length; // atualiza o tamanho do arranjo da classe
            
            setAllElementsTo(NULL); // coloca NULL em todos os elementos do arranjo
            
            removeError(errorCode);
        }
    }
}

void StartArrayWithArray(void **_array, int length, int _sizeOfElements)
{
    StartArrayWithLength(length, _sizeOfElements);
    
    if (!hasError())
    {
        int errorCode = 3;

        if (_array == NULL)
        {
            //printf("[Array]: Arranjo nulo. - ArrayClass(void **, int, int)\n");

            addError(errorCode);
        }

        else
        {
            array = _array;

            removeError(errorCode);
        }
    }
}

// ----------------------- FUNCOES DA CLASSE

/**
 * Obtem quantos bytes cada elemento do arranjo gasta
 * 
 * @return quantidade de bytes que cada elemento do arranjo gasta
 */

int getSizeOfElements()
{
    return sizeOfElements;
}

/**
 * Define quantos bytes cada elemento do arranjo gasta
 * 
 * @param _sizeOfElements bytes que cada elemento do arranjo gasta
 */

void setSizeOfElements(int _sizeOfElements)
{
    int errorCode = 1;
    
    if (_sizeOfElements < 1)
    {
        //printf("[Array]: Tamanho dos elementos < 1. (_sizeOfElements = %d) - funcao setSizeOfElements(int)\n", _sizeOfElements);
        addError(errorCode);
    }
    
    else
    {
        sizeOfElements = _sizeOfElements;
        removeError(errorCode);
    }
}

/**
 * Obtem o tamanho do arranjo da classe
 * 
 * @return tamanho do arranjo da classe
 */

int length()
{
    return arrayLength;
}

/**
 * Define o tamanho do arranjo da classe
 */

void setArrayLength(int _length)
{
    int errorCode = 2;
    
    if (_length < 1)
    {
        //printf("[Array]: Tamanho do arranjo < 1. (_length = %d) - funcao setArrayLength(int)\n", _length);
        addError(errorCode);
    }
    
    else
    {
        arrayLength = _length;
        removeError(errorCode);
    }
}

/**
 * Obtem o arranjo da classe
 * 
 * @return arranjo da classe
 */

void **getArray()
{
    return array;
}

/**
 * Define o arranjo da classe
 */

void setArray(void **_array)
{
    int errorCode = 3;
    
    if (_array == NULL)
    {
        //printf("[Array]: Arranjo nulo. - funcao setArray(void **)\n");
        addError(errorCode);
    }

    else
    {
        array = _array;
        removeError(errorCode);
    }
}

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

/**
 * Tenta acessar um elemento do arranjo da classe. Em caso de sucesso,
 * retorna o elemento. Em outro caso, retorna NULL.
 * 
 * @param index indice do elemento no arranjo
 * 
 * @return elemento no indice informado ou NULL se o elemento nao existir
 */

void *arrayGet(int index)
{
    void *result;

    if (limitOverflowOnIndex(index, length()) != 0)
    {
        //printf("[Array]: Parametro index invalido. (index = %d) - funcao arrayGet(int)\n", index);

        result = NULL;
    }

    else
    {
        result = array[index];
    }

    return result;
}

/**
 * Tenta acessar um indice do arranjo e colocar um objeto nesse lugar.
 * 
 * @param element elemento a ser colocado no indice especificado
 * @param index indice da posicao onde deseja-se colocar o elemento
 */

void arraySet(void *element, int index)
{
    if (limitOverflowOnIndex(index, length()) != 0)
    {
        //printf("[Array]: Parametro index invalido. (index = %d) - funcao arraySet(void *, int)\n", index);
    }

    else
    {
        array[index] = element;
    }
}

/**
 * Recebe dois indices de dois elementos e coloca um elemento no indice do outro.
 * 
 * @param firstIndex indice do primeiro elemento
 * @param secondIndex indice do segundo elemento
 */

void swap(int firstIndex, int secondIndex)
{
    void *copy = arrayGet(firstIndex);

    arraySet(arrayGet(secondIndex), firstIndex);
    arraySet(copy, secondIndex);
}

/**
 * A partir de um indice inicial do arranjo, move certa quantidade dos elementos
 * para a esquerda deslocando-os uma certa quantidade de posicoes.
 * 
 * @param initialIndex indice inicial
 * @param numberOfElements quantidade de elementos a serem deslocados
 * @param offset quantas posicoes os elementos devem ser deslocados
 */

void moveElementsToLeftFrom(int initialIndex, int numberOfElements, int offset)
{
    int arrLength = length();

    if (backwardLimitOverflow(initialIndex - 1, offset, arrLength, true) != 0)
    {
        //printf("[Array]: Parametros initialIndex e offset invalidos. (initialIndex = %d | offset = %d) - funcao moveElementsToLeftFrom(int, int, int)\n", initialIndex, offset);
    }

    else if (limitOverflow(initialIndex, numberOfElements, arrLength) != 0)
    {
        //printf("[Array]: Parametros initialIndex e numberOfElements invalidos. (initialIndex = %d | numberOfElements = %d) - funcao moveElementsToLeftFrom(int, int, int)\n", initialIndex, numberOfElements);
    }

    else
    {
        int i;
        
        for (i = initialIndex; (i - initialIndex) < numberOfElements && i < arrLength; i++)
        {
            arraySet(arrayGet(i), i - offset); // coloca o elemento da posicao "i", "offset" posicoes para tras
        }
    }
}

/**
 * A partir de um indice inicial do arranjo, move certa quantidade de elementos
 * para a esquerda deslocando-os uma posicao.
 * 
 * @param initialIndex indice inicial
 * @param numberOfElements quantidade de elementos a serem deslocados
 */

void moveElements1TimeToLeftFrom(int initialIndex, int numberOfElements)
{
    moveElementsToLeftFrom(initialIndex, numberOfElements, 1);
}

/**
 * A partir de um indice inicial do arranjo, move todos os elementos
 * para a esquerda deslocando-os uma posicao.
 * 
 * @param initialIndex indice inicial
 */

void moveAllElementsToLeftFrom(int initialIndex)
{
    moveElements1TimeToLeftFrom(initialIndex, length() - initialIndex);
}

/**
 * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
 * certa quantidade dos elementos para a direita deslocando-os uma certa
 * quantidade de posicoes.
 * 
 * @param initialIndex indice inicial
 * @param numberOfElements quantidade de elementos a serem deslocados
 * @param offset quantas posicoes os elementos devem ser deslocados
 */

void moveElementsToRightFrom(int initialIndex, int numberOfElements, int offset)
{
    int arrLength = length();

    if (limitOverflow(initialIndex + 1, offset, arrLength) != 0)
    {
        //printf("[Array]: Parametros initialIndex e offset invalidos. (initialIndex = %d | offset = %d) - funcao moveElementsToRightFrom(int, int, int)\n", initialIndex, offset);
    }

    else if (backwardLimitOverflow(initialIndex, numberOfElements, arrLength, true) != 0)
    {
        //printf("[Array]: Parametros initialIndex e numberOfElements invalidos. (initialIndex = %d | numberOfElements = %d) - funcao moveElementsToRightFrom(int, int, int)\n", initialIndex, numberOfElements);
    }

    else
    {
        int i;
        
        for (i = initialIndex; (initialIndex - i) < numberOfElements && i >= 0; i--)
        {
            arraySet(arrayGet(i), i + offset); // coloca o elemento da posicao "i", "offset" posicoes para frente
        }
    }
}

/**
 * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
 * certa quantidade dos elementos para a direita deslocando-os uma posicao.
 * 
 * @param initialIndex indice inicial
 * @param numberOfElements quantidade de elementos a serem deslocados
 */

void moveElements1TimeToRightFrom(int initialIndex, int numberOfElements)
{
    moveElementsToRightFrom(initialIndex, numberOfElements, 1);
}

/**
 * A partir de um indice inicial do arranjo, percorrendo-o para tras, move
 * certa quantidade dos elementos para a direita deslocando-os uma posicao.
 * 
 * @param initialIndex indice inicial
 */

void moveAllElementsToRightFrom(int initialIndex)
{
    moveElements1TimeToRightFrom(initialIndex, initialIndex + 1);
}

/**
 * Procurado o indice do elemento informado
 * 
 * @param element elemento a ser procurado
 * 
 * @return o indice do elemento se ele existe, caso contrario, -1
 */

int indexOf(void *element)
{
    int index = -1, arrLength = length();
    int found = false;
    int i;

    for (i = 0; i < arrLength && !found; i++)
    {
        found = element == arrayGet(i);
    }

    return index;
}

/**
 * Percorre o arranjo colocando o objeto especificado em todas as posicoes
 * 
 * @param element elemento com o qual se deseja preencher o arranjo
 */

void setAllElementsTo(void *element)
{
    int arrLength = length();
    int i;

    for (i = 0; i < arrLength; i++)
    {
        arraySet(element, i);
    }
}

/**
 * Limpar o arranjo da classe igualando todos os elementos a NULL
 */

void clearArray()
{
    setAllElementsTo(NULL);
}

/**
 * Copia todos os elementos de um arranjo e os coloca
 * no arranjo da classe a partir de um indice inicial
 * 
 * @param start indice de comeco da gravacao
 * @param elements arranjo com os elementos a serem copiados
 * 
 * @return true se os parametros anteriores nao levarem
 * a um erro, caso contrario, false.
 */

int copyToArray(int start, void **elements, int numberOfElements)
{
    // cuida de todos os possiveis casos de dados incorretos.
    if (elements == NULL || limitOverflow(start, numberOfElements, length()) != 0)
    {
        //printf("[List]: Nao foi possivel copiar os elementos para o arranjo da classe. - funcao copyToArray(int, void **)\n");
        return false;
    }
    
    int i;
    
    for (i = 0; i < numberOfElements; i++) // percorre a quantidade de elementos
    {
        // associa cada objeto de "elements" 'a "array" a partir da posicao "start"
        arraySet(elements[i], start + i);
    }

    return true;
}

/**
 * Copia certa quantidade de elementos do arranjo a partir
 * de um indice inicial
 *
 * @param start indice inicial
 * @param numberOfElements quantidade de elementos
 * 
 * @return um ponteiro para um novo arranjo que tem copias
 * dos elementos do arranjo
 */

void **copyArrayPiece(int start, int numberOfElements)
{
    if (limitOverflow(start, numberOfElements, length()) != 0) return NULL;

    void **arrayCopy = (void **) malloc( sizeof(void *) * numberOfElements);
    int i;
    
    for (i = 0; i < numberOfElements; i++)
    {
        arrayCopy[i] = arrayGet(start + i);
    }

    return arrayCopy;
}

/**
 * Copia todos os elementos do arranjo a partir do indice informado.
 *
 * @param start indice inicial
 * 
 * @return um ponteiro para um novo arranjo que tem copias
 * dos elementos do arranjo
 */

void **copyArrayFrom(int start)
{
    return copyArrayPiece(start, length() - start);
}

/**
 * Cria uma copia do arranjo da classe
 * 
 * @return copia do arranjo da classe
 */

void **copyArray()
{
    return copyArrayFrom(0);
}

void freeMyArray(void **myArray, int numberOfElements)
{
    int i;
    
    for (i = 0; i < numberOfElements; i++)
    {
        free( myArray[i] );
    }
    
    free(myArray);
}

void freeArray()
{
    if (getArray() != NULL)
    {
        int arrLength = length(), i;

        for (i = 0; i < arrLength; i++)
        {
            free(arrayGet(i));
        }
        
        free(getArray());
    }
    
    freeErrors();
}

// ----------------------- FIM DA CLASSE ARRAY
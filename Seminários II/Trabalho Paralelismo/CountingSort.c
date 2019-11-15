#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>
#include <time.h>

// array init modes -> "random", "crescent", "decrescent"
#define ARRAY_INIT_MODE "random"
#define ARRAY_SIZE 200000
// ordenation modes -> "sequential", "parallel"
#define ORDENATION_MODE "parallel"

int MAX_THREADS;

int getGreatestElementStartingFrom(int start, int *array, int arrayLength)
{
    int threadsGreatest[MAX_THREADS];
    int greatest = array[start];
    int threadGreatest;
    
    #pragma omp parallel
    {
        int thread_id = omp_get_thread_num();
        int current;
        int i;
        
        threadsGreatest[thread_id] = greatest;
        
        #pragma omp for
        for (i = start + 1; i < arrayLength; i++)
        {
            current = array[i];

            if (current > threadsGreatest[thread_id])
            {
                threadsGreatest[thread_id] = current;
            }
        }
    }
    
    int thread_id;
    
    for (thread_id = 0; thread_id < MAX_THREADS; thread_id++)
    {
        threadGreatest = threadsGreatest[thread_id];
        
        if (threadGreatest > greatest)
        {
            greatest = threadGreatest;
        }
    }
    
    return greatest;
}

// ------------------- COUNTING_SORT PARALELO

int *parallelCountingSort(int *array, int arrayLength)
{
    // se o arranjo tiver elementos muito grandes ha' uma grande chance
    // de não haver espaço na memoria para alocar o arranjo de contagem
    int greatest = getGreatestElementStartingFrom(0, array, arrayLength);
    int countingArray[greatest + 1];
    int *outArray = (int *) malloc(sizeof(int) * arrayLength);
    int element, i;
    
    #pragma omp parallel for
    for (i = 0; i < greatest + 1; i++)
    {
        countingArray[i] = 0;
    }
    
    #pragma omp parallel for
    for (i = 0; i < arrayLength; i++)
    {
        countingArray[ array[i] ]++;
    }
    
    for (i = 1; i < greatest + 1; i++)
    {
        countingArray[i] += countingArray[i - 1];
    }
    
    for (i = arrayLength - 1; i >= 0; i--)
    {
        element = array[i];
        
        outArray[ --countingArray[element] ] = element;
    }
    
    return outArray;
}

// ------------------- FIM DO COUNTING_SORT PARALELO

// ------------------- COUNTING_SORT SEQUENCIAL

int *sequentialCountingSort(int *array, int arrayLength)
{
    int greatest = getGreatestElementStartingFrom(0, array, arrayLength);
    int countingArray[greatest + 1];
    int *outArray = (int *) malloc(sizeof(int) * arrayLength);
    int element, i;
    
    for (i = 0; i < greatest + 1; i++)
    {
        countingArray[i] = 0;
    }
    
    for (i = 0; i < arrayLength; i++)
    {
        countingArray[ array[i] ]++;
    }
    
    for (i = 1; i < greatest + 1; i++)
    {
        countingArray[i] += countingArray[i - 1];
    }
    
    for (i = arrayLength - 1; i >= 0; i--)
    {
        element = array[i];
        
        outArray[ --countingArray[element] ] = element;
    }
    
    return outArray;
}

// ------------------- FIM DO COUNTING_SORT SEQUENCIAL

// ------------------- FIM DOS COUNTING_SORTS

// ------------------- FUNCOES DE UTILIDADE

// preenchimento do array  -> { 1, 2, 3, ..., arrayLength }
void initCrescentArray(int *array, int arrayLength)
{
    int i;
    
    #pragma omp parallel for simd
    for (i = 0; i < arrayLength; i++)
    {
        array[i] = i + 1;
    }
}

// preenchimento do array  -> { arrayLength, arrayLength - 1, ..., 2, 1 }
void initDecrescentArray(int *array, int arrayLength)
{
    int i;
    
    #pragma omp parallel for simd
    for (i = 0; i < arrayLength; i++) 
    {
        array[i] = arrayLength - i;
    }
}

void initRandomArray(int *array, int arrayLength)
{
    int i;
    srand( time(NULL) );
    
    // a paralelizacao desse looping gera problemas
    // na geracao dos numeros aleatorios
    for (i = 0; i < arrayLength; i++)
    {
        array[i] = rand() % arrayLength + 1;
    }
}

void initArray(int *array, const char *mode, int arrayLength)
{
    if (strcmp(mode, "random") == 0)
        initRandomArray(array, arrayLength);
    
    else if (strcmp(mode, "crescent") == 0)
        initCrescentArray(array, arrayLength);
    
    else if (strcmp(mode, "decrescent") == 0)
        initDecrescentArray(array, arrayLength);
}

// checa se a ordenacao do arranjo esta' incorreta
int ordenationIsWrong(int *array, int arrayLength)
{
    int wrong = 0;
    int i;
    
    #pragma omp parallel for reduction(+:wrong)
    for (i = 0; i < arrayLength - 1; i++)
    {
        wrong = ( wrong ? 1 : array[i] > array[i + 1] );
    }
    
    return wrong > 0;
}

void printArray(int *array, int arrayLength)
{
    int i;
    
    printf("%d", array[0]);
    
    for (i = 1; i < arrayLength; i++)
    {
        printf(" %d", array[i]);
    }
    
    puts("");
}

int *countingSort(int *array, const char *mode, int arrayLength)
{
    int *out = NULL;
    
    if (strcmp(mode, "sequential") == 0)
        out = sequentialCountingSort(array, arrayLength);
    
    else if (strcmp(mode, "parallel") == 0)
        out = parallelCountingSort(array, arrayLength);
    
    return out;
}

int main()
{
    int array[ARRAY_SIZE];
    int *out;
    MAX_THREADS = omp_get_max_threads();
    
    initArray(array, ARRAY_INIT_MODE, ARRAY_SIZE);
    
    out = countingSort(array, ORDENATION_MODE, ARRAY_SIZE);
    
    if (ordenationIsWrong(out, ARRAY_SIZE)) printf("Ordenacao errada\n");
    
    free(out);
    
    return EXIT_SUCCESS;
}
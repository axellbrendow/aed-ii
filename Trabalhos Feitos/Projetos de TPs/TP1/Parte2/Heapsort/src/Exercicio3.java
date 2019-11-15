/*

PUC Minas - Ciencia da Computacao     Nome: Exercicio3

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 08/10/2018

*/

public class Exercicio3
{
    
    /**
     * Recebe dois indices de dois elementos e coloca um elemento no indice do outro.
     * 
     * @param firstIndex indice do primeiro elemento
     * @param secondIndex indice do segundo elemento
     * @param intArray arranjo a ser acessado
     */
    
    public static void swap(int firstIndex, int secondIndex, int[] intArray)
    {
        int copy = intArray[firstIndex];
        
        intArray[firstIndex] = intArray[secondIndex];
        intArray[secondIndex] = copy;
    }
    
    public static void printIntArray(int[] intArray)
    {
        for (int number : intArray)
        {
            System.out.print(number + " ");
        }
        
        System.out.println();
    }
    
    public static boolean temFilho(int pai, int tamHeap)
    {
        return pai * 2 <= tamHeap;
    }
    
    public static boolean temFilho2(int pai, int tamHeap)
    {
        return pai * 2 + 1 <= tamHeap;
    }
    
    public static int menorFilho(int[] array, int pai, int tamHeap)
    {
        int menorFilho = -1;
        
        if (temFilho(pai, tamHeap)) // checa se o pai tem filho
        {
            menorFilho = pai * 2;
            
            if (temFilho2(pai, tamHeap)) // checa se o pai tem 2 filhos
            {
                int segundoFilho = pai * 2 + 1;
                
                // checa se o segundo e' menor que o primeiro
                if (array[segundoFilho] < array[menorFilho])
                {
                    menorFilho = segundoFilho;
                }
            }
        }
        
        return menorFilho;
    }
    
    public static void reorganizar(int[] array, int pai, int tamHeap)
    {
        int menorFilho = menorFilho(array, pai, tamHeap); // obtem o menor filho
        
        if (menorFilho != -1) // checa se existe
        {
            if (array[menorFilho] < array[pai]) // checa se ele e' menor que o pai
            {
                swap(menorFilho, pai, array); // troca pai e filho de lugar
                reorganizar(array, menorFilho, tamHeap); // refaz a analise a partir de onde o filho estava
            }
        }
    }
    
    public static int removerRaiz(int[] array, int k)
    {
        int raiz = array[1];
        
        // unica mudanca do codigo da prova
        swap(1, k, array); // troca o ultimo no' com a raiz
        
        reorganizar(array, 1, k - 1); // desce a arvore colocando os menores em cima
        
        return raiz;
    }
    
    public static void main(String[] args)
    {
        int[] heap = { 0, 3, 4, 6, 7, 5, 9, 8, 10 };
        /*
        
                3
              /   \
             4     6
            / \   / \
           7   5 9   8
          /
        10
        
        */
        
        printIntArray(heap);
        
        for (int i = 1; i <= heap.length - 2; i++) // remove a raiz ate' o heap acabar
        {
            removerRaiz(heap, heap.length - i);
            printIntArray(heap);
        }
    }
}

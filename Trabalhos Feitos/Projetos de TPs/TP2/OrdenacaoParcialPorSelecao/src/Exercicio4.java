public class Exercicio4
{
    /* would be a good idea, but there's no way
    public static void swap(int index1, int index2, Object array)
    {
        Object[] copyOfIndex2 = new Object[1];
        
        System.arraycopy(array, index2, copyOfIndex2, 0, 1);
        System.arraycopy(array, index1, array, index2, 1);
        System.arraycopy(copyOfIndex2, 0, array, index1, 1);
    }*/
    
    public static void swap(int index1, int index2, int[] array)
    {
        int copyOf1 = array[index1];
        
        array[index1] = array[index2];
        array[index2] = copyOf1;
    }
    
    public static void selecaoAlternado(int start, int numberOfElements, int[] intArray)
    {
        int length = intArray.length;
        int maior, indiceDoMaior;
        int menor, indiceDoMenor;
        int currentElement;
        
        for (int i = start, n = 0; n < numberOfElements && i < length; i += 2, n++)
        {
            indiceDoMaior = i;
            indiceDoMenor = i;
            maior = intArray[indiceDoMaior];
            menor = intArray[indiceDoMenor];
            
            for (int j = i + 1; j < length; j++)
            {
                currentElement = intArray[j];
                
                if (currentElement > maior)
                {
                    indiceDoMaior = j;
                    maior = currentElement;
                }
                
                else if (currentElement < menor)
                {
                    indiceDoMenor = j;
                    menor = currentElement;
                }
            }
            
            if (i + 1 < length)
            {
                swap(indiceDoMaior, i + 1, intArray);

                if (indiceDoMenor == i + 1)
                {
                    indiceDoMenor = indiceDoMaior;
                }
            }
            
            swap(i, indiceDoMenor, intArray);
        }
    }
    
    public static void selecaoAlternado(int start, int[] intArray)
    {
        selecaoAlternado(start, intArray.length - start, intArray);
    }
    
    public static void selecaoAlternado(int[] intArray)
    {
        selecaoAlternado(0, intArray);
    }
    
    public static void main(String[] args)
    {
        int[] intArray = new int[] { 4, 7, 3, 2, 8, 5, 9, 1, 0, 6 };
        selecaoAlternado(2, 4, intArray);
        
        for (int number : intArray)
        {
            AxellIO.print(number + " ");
        }
        
        AxellIO.println();
    }
}

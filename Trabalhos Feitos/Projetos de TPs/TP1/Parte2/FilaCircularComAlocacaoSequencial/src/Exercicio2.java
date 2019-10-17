/*

PUC Minas - Ciencia da Computacao     Nome: FilaCircularComAlocacaoSequencial

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 04/09/2018

*/

public class Exercicio2
{
    public static boolean binarySearchOnCircularQueue(int key, FilaCircular filaCircular)
    {
        boolean found = false;
        int esq = 2, dir = 1, meio = 0, elemMeio = 0, length = filaCircular.length(), offset = 100;
        while (offset != 0) // mudança de "while (esq != dir)" para "while (offset != 0)"
        {
            offset = 0;
            while ((esq + offset) % length != dir) offset++;
            meio = (esq + (offset / 2)) % length; elemMeio = (int) filaCircular.get(meio);
            if (key == elemMeio) { esq = dir; found = true; }
            else if (key < elemMeio){ dir = meio - 1 == -1 ? length - 1 : meio - 1; }
            else { esq = (meio + 1) % length; }
        }
        return found;
    }
    
    public static void main(String[] args)
    {
        FilaCircular fila = new FilaCircular(20);
        
        for (int i = 0; i < 20; i++)
        {
            fila.add(i);
        }
        
        fila.add(20);
        fila.add(21);
        fila.printQueue(" ");
        AxellIO.println();
        fila.print(" ");
        
        int elementToSearch = 5;
        AxellIO.println("\nfind(" + elementToSearch + ") = " + binarySearchOnCircularQueue(elementToSearch, fila));
    }
}

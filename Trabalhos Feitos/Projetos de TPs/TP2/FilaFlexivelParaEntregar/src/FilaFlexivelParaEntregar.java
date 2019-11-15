/*

PUC Minas - Ciencia da Computacao     Nome: Cell

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 19/09/2018

*/

class Cell
{
    Object _element;
    Cell _next;
    
    public Cell(){}
    
    public Cell(Object element)
    {
        _element = element;
    }
    
    public Cell(Cell next)
    {
        _next = next;
    }
    
    public Cell(Object element, Cell next)
    {
        this(element);
        
        _next = next;
    }
    
    public Object getElement()
    {
        return _element;
    }
    
    public void setElement(Object element)
    {
        _element = null;
    }
    
    public Cell getNext()
    {
        return _next;
    }
    
    public void setNext(Cell next)
    {
        _next = next;
    }
}

/*

PUC Minas - Ciencia da Computacao     Nome: Queue

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 24/09/2018

*/

class Queue
{
    // e' importante ressaltar que essa nao e' verdadeiramente a primeira celula
    // da fila, a primeira celula fica sempre depois dessa. Caso nao exista uma
    // celula depois, a fila esta' vazia.
    Cell _head;
    Cell _last;
    
    public Queue()
    {
        _head = new Cell();
        _last = _head;
    }
    
    private Cell getHead()
    {
        return _head;
    }
    
    private void setHead(Cell first)
    {
        _head = first;
    }
    
    private Cell getFirst()
    {
        return getHead().getNext();
    }
    
    private Cell getLast()
    {
        return _last;
    }
    
    private void setLast(Cell last)
    {
        _last = last;
    }
    
    public boolean isEmpty()
    {
        return getHead() == getLast();
    }
    
    public int getNumberOfElements()
    {
        int numberOfElements = 0;
        Cell currentCell = getHead();
        Cell lastCell = getLast();
        
        while (currentCell != lastCell)
        {
            numberOfElements++;
            currentCell = currentCell.getNext();
        }
        
        currentCell = null;
        lastCell = null;
        
        return numberOfElements;
    }
    
    public Cell getCellOnIndex(int index)
    {
        int numberOfElements = getNumberOfElements();
        Cell cell = null;
        
        if (index < 0 || index >= numberOfElements)
        {
            System.out.println("[Queue]: Indice invalido. (index = " + index + ") - funcao getCellOnIndex(int)");
        }
        
        else if (numberOfElements > 0)
        {
            int currentIndex = 0;
            cell = getFirst();
            
            while (currentIndex < index)
            {
                currentIndex++;
                cell = cell.getNext();
            }
        }
        
        return cell;
    }
    
    public Object removeOnStart()
    {
        Object removedElement = null;
        
        if (!isEmpty())
        {
            Cell head = getHead();
            Cell first = getFirst();
            
            removedElement = first.getElement();
            
            head.setNext(null);
            
            setHead(first);
            
            first = null;
            head = null;
        }
        
        return removedElement;
    }
    
    public Object remove()
    {
        return removeOnStart();
    }
    
    public void addOnEnd(Object element)
    {
        Cell newCell = new Cell(element, null);
        
        getLast().setNext(newCell);
        setLast(newCell);
        
        newCell = null;
    }
    
    public void add(Object element)
    {
        addOnEnd(element);
    }
    
    public void printQueue()
    {
        if (!isEmpty())
        {
            Cell currentCell = getFirst();
            Cell lastCell = getLast();

            System.out.print("[ " + currentCell.getElement());

            while (currentCell != lastCell)
            {
                currentCell = currentCell.getNext();
                
                System.out.print(", " + currentCell.getElement());
            }

            System.out.println(" ]");
        }
    }
    
    public int getGreatestElement()
    {
        int greatestElement = Integer.MIN_VALUE;
        
        if (!isEmpty())
        {
            Cell lastCell = getLast();
            Cell currentCell = getFirst();
            greatestElement = (int) currentCell.getElement();
            int currentElement;

            while (currentCell != lastCell)
            {
                currentCell = currentCell.getNext();
                
                currentElement = (int) currentCell.getElement();

                if (currentElement > greatestElement)
                {
                    greatestElement = currentElement;
                }
            }
        }
        
        return greatestElement;
    }
    
    public int getThirdElement()
    {
        return (int) getFirst().getNext().getNext().getElement();
    }
    
    public void printThirdElement()
    {
        System.out.println("3rd element = " + getThirdElement());
    }
    
    public int sumElements()
    {
        int sum = 0;
        
        if (!isEmpty())
        {
            Cell currentCell = getFirst();
            Cell lastCell = getLast();
            
            sum += (int) currentCell.getElement();

            while (currentCell != lastCell)
            {
                currentCell = currentCell.getNext();
                
                sum += (int) currentCell.getElement();
            }
        }
        
        return sum;
    }
    
    public void invertQueue()
    {
        if (!isEmpty())
        {
            Cell headCell = getHead();
            Cell lastCell = getLast();
            Cell currentCell = getFirst();
            Cell previousOfCurrent;
            Cell nextOfCurrent = currentCell.getNext();
            
            setLast(currentCell);
            currentCell.setNext(null);
            
            while (currentCell != lastCell)
            {
                previousOfCurrent = currentCell;
                currentCell = nextOfCurrent;
                nextOfCurrent = currentCell.getNext();
                
                currentCell.setNext(previousOfCurrent);
            }
            
            headCell.setNext(currentCell);
            
            headCell = null;
            lastCell = null;
            currentCell = null;
            previousOfCurrent = null;
            nextOfCurrent = null;
        }
    }
    
    public int oddAndMultiplesOf5R(Cell currentCell, int previousCount)
    {
        int count = previousCount;
        
        if (currentCell != getLast())
        {
            Object obElement = currentCell.getElement();
            int element = obElement == null ? 1 : (int) obElement;
            
            if (element % 2 == 0 && element % 5 == 0)
            {
                count++;
            }
            
            count = oddAndMultiplesOf5R(currentCell.getNext(), count);
        }
        
        else
        {
            Object obElement = currentCell.getElement();
            int element = obElement == null ? 1 : (int) obElement;
            
            if (element % 2 == 0 && element % 5 == 0)
            {
                count++;
            }
        }
        
        return count;
    }
    
    public int getOddAndMultiplesOf5()
    {
        if (!isEmpty())
        {
            return oddAndMultiplesOf5R(getFirst(), 0);
        }
        
        else
        {
            return 0;
        }
    }
}

public class FilaFlexivelParaEntregar
{
    public static void main(String[] args)
    {
        Queue test = new Queue();
        
        test.add(0);
        test.add(1);
        test.add(2);
        test.printQueue();
        
        test.remove();
        test.printQueue();
        
        test.add(3);
        test.printQueue();
        
        System.out.println("" + test.getOddAndMultiplesOf5());
        System.out.println("" + test.getGreatestElement());
    }
    
}

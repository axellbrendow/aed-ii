/*

PUC Minas - Ciencia da Computacao     Nome: ArvoreBinariaDeContatos

Autor: Axell Brendow Batista Moreira  Matricula: 631822

Versao:  1.0                          Data: 09/10/2018

*//*

class Node
{
    Object element;
    Node left;
    Node right;
    Lista contactsList;
    
    public Node(){}
    
    public Node(Object element)
    {
        this.element = element;
    }
    
    public Node(Node left, Node right)
    {
        this.left = left;
        this.right = right;
    }
    
    public Node(Object element, Node left, Node right)
    {
        this(left, right);
        
        this.element = element;
    }
    
    public Node(Object element, Node left, Node right, Lista contactsList)
    {
        this(element, left, right);
        
        this.contactsList = contactsList;
    }
    
    public Object getElement()
    {
        return element;
    }
    
    public void setElement(Object element)
    {
        this.element = element;
    }
    
    public Node getLeft()
    {
        return left;
    }
    
    public void setLeft(Node left)
    {
        this.left = left;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public void setRight(Node right)
    {
        this.right = right;
    }
    
    public Lista getContactsList()
    {
        return contactsList;
    }
    
    public void setContactsList(Lista contactsList)
    {
        this.contactsList = contactsList;
    }
}

class BinarySearchTree
{
    Node root;
    TreeType treeType;
    
    public enum TreeType
    {
        Strings,
        Numbers
    };
    
    public BinarySearchTree(TreeType treeType)
    {
        this.treeType = treeType;
    }
    
    public Node getRoot()
    {
        return root;
    }
    
    public void setRoot(Node root)
    {
        this.root = root;
    }
    
    // retorna o proprio no' atual ou entao um novo no' que sera' filho do no' anterior
    private Node insert(String str, Node current)
    {
        if (current == null)
        {
            current = new Node();
            current.setElement(str);
            current.setContactsList( new Lista() );
        }
        
        else
        {
            String currentStr = (String) current.getElement();
            
            if (currentStr == null) {}
            
            else if (str.compareTo(currentStr) < 0)
            {
                current.setLeft( insert(str, current.getLeft()) );
            }
            
            else if (str.compareTo(currentStr) > 0)
            {
                current.setRight( insert(str, current.getRight()) );
            }
        }
        
        return current;
    }
    
    public void insert(Object element)
    {
        switch (treeType)
        {
            case Strings:
                insert((String) element, getRoot());
                break;
                
            /*case Numbers:
                insert((Double) element, getRoot());
                break;*//*
                
            default:
                break;
        }
    }
    
    public Node search(Double num, Node current)
    {
        Node result = null;
        
        if (current != null)
        {
            Double currentNum = (Double) current.getElement();
            
            if ( num.equals(currentNum) )
            {
                result = current;
            }
            
            else if ( num.compareTo(currentNum) < 0 )
            {
                result = search(num, current.getLeft());
            }
            
            else
            {
                result = search(num, current.getRight());
            }
        }
        
        return result;
    }
    
    public Node search(String str, Node current)
    {
        Node result = null;
        
        if (current != null)
        {
            String currentStr = (String) current.getElement();
            
            if (currentStr == null) {}
            
            else if ( str.equals(currentStr) )
            {
                result = current;
            }
            
            else if ( str.compareTo(currentStr) < 0 )
            {
                result = search(str, current.getLeft());
            }
            
            else
            {
                result = search(str, current.getRight());
            }
        }
        
        return result;
    }
    
    public Node search(Object element)
    {
        switch (treeType)
        {
            case Strings:
                return search((String) element, getRoot());
                
            case Numbers:
                return search((Double) element, getRoot());
                
            default:
                return null;
        }
    }
}

class Contato
{
    public String nome;
    public String telefone;
    public String email;
    public int cpf;
    
    public Contato(){}
    
    public Contato(String nome, String telefone, String email, int cpf)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
}

class Agenda
{
    BinarySearchTree agenda;
    
    public Agenda()
    {
        agenda = new BinarySearchTree(BinarySearchTree.TreeType.Strings);
        
        agenda.setRoot( new Node("M", null, null, new Lista()) );
        
        for (char letter = 'A'; letter <= 'Z'; letter++)
        {
            agenda.insert("" + letter);
        }
    }
    
    public void insert(Contato contato)
    {
        String firstLetter = contato.nome.substring(0, 1);
        
        Node contatoNode = agenda.search(firstLetter);
        
        if (contatoNode != null)
        {
            contatoNode.getContactsList().add(contato);
        }
    }
    
    public boolean search(String nome)
    {
        boolean found = false;
        
        String firstLetter = nome.substring(0, 1);
        
        Node contatoNode = agenda.search(firstLetter);
        
        if (contatoNode != null)
        {
            Lista contactsList = contatoNode.getContactsList();
            Contato currentContact;
            int size = contactsList.size();
            
            for (int i = 0; i < size && !found; i++)
            {
                currentContact = (Contato) contactsList.get(i);
                
                found = currentContact.nome.equals(nome);
            }
        }
        
        return found;
    }
    
    public boolean search(int cpf)
    {
        boolean found = false;
        
        for (char firstLetter = 'A'; firstLetter <= 'Z' && !found; firstLetter++)
        {
            String firstLetterStr = "" + firstLetter;
            Node contatoNode = agenda.search(firstLetterStr);

            if (contatoNode != null)
            {
                Lista contactsList = contatoNode.getContactsList();
                Contato currentContact;
                int size = contactsList.size();

                for (int i = 0; i < size && !found; i++)
                {
                    currentContact = (Contato) contactsList.get(i);

                    found = currentContact.cpf == cpf;
                }
            }
        }
        
        return found;
    }
}*/
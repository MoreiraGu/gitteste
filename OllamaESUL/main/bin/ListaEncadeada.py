"""
Neste código irei criar duas classes que irão gerenciar Listas Encadeadas por Nós 

Esse é o passo a passo para produzir-las:
1- Criar uma classe Node(Nó em inglês) e seus respectivos atributos
2- Criar uma classe LinkedList(Lista encadeada em inglês) e seus respectivos atributos
3- Criar um método para criar um nó na lista encadeada
4- Criar um método para mostrar a lista para o usuário
5- Criar um método para contar e mostrar o numero de Nós na lista
"""

#Classe Node com os atributos dado(informação a ser armazenada no nó) 
#e o atributo next(que será o atributo a seguir no nó)
class Node:
    
    def __init__(self,dado):
        self.dado = dado
        self.next = None


#Classe Linked List
class LinkedList:

    def __init__(self):
        self.head = None
        self.size = 0
    #Função responsavel por adicionar um elemento a lista
    def append(self,elem):
        if self.head:
            pointer = self.head
            while(pointer.next):
                pointer = pointer.next
            pointer.next = Node(elem)     
        else:
            self.head = Node(elem)
    
        
    def print_list(self):
        if self.head:
            pointer = self.head
            count = 1
            while pointer:
                print(f"\n Nó {count}: {pointer.dado}")
                pointer = pointer.next
                count += 1
        else:
            raise IndexError("Não há nenhum nó na lista")
    
    def count_nodes(self):
        count = 0
        pointer = self.head
        while pointer:
            count += 1
            pointer = pointer.next
        print(count)    
                      



"""
-----------------------
ÁREA DE TESTE DO CÓDIGO
-----------------------
"""


Lista = LinkedList()
Lista.append(1)
Lista.append(5)
Lista.append(3)

Lista.count_nodes()
Lista.print_list()








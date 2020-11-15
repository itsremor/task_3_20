public class OwnQueue {

//toDo убрать хвост, сделать компоратор в качестве вводимого аргумента. Не сравнивать компоратор с единицой, а сравнивать с нулём
    //toDo сделать очередь обобщённой (либо ограничение очереди, либо ограничение компоратора)
    //toDo Poll и вынимает и удаляет
    //toDo бросаться исключениями при попытке вынуть/посмотреть в пустой очереди
    private class Node {
        Node next;
        Object obj;

        Node(Object object) {
            obj = object;
        }
    }

    private final MyComporator comporator = new MyComporator();

    private Node head;
    private int size;

    private boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void add(Object value){
        Node n = new Node(value);
        Node temp = head;
        if (isEmpty()){
            head = n;
        }
        else if(comporator.compare(temp.obj.toString(), n.obj.toString()) == 1){
                n.next = head;
                head = n;
            }

        else{
            while (comporator.compare(temp.next.obj.toString(), n.obj.toString()) == -1){
                temp = temp.next;
                if (temp.next == null)
                    break;
            }

            //...
            if (temp.next != null) {

                while (comporator.compare(temp.next.obj.toString(), n.obj.toString()) == 0) {
                    temp = temp.next;
                    if (temp.next == null)
                        break;
                }
            }
            //...

                Node nextTemp = temp.next;
                temp.next = n;
                n.next = nextTemp;
        }
                size++;
    }

    public Object peek(){
        return head.obj;
    }

    public void poll(){
        head = head.next;
        size--;
    }

    public int getSize(){
        return size;
    }


}
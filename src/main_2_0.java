import java.util.LinkedList;
import java.util.List;

public class main_2_0 {
    public static void main(String[] args) {

        OwnQueue myNewQueue = new OwnQueue();
        List<String> myNewList = new LinkedList<>();

        main.loadFromFile(myNewList);
        for(int i = 0; i < myNewList.size(); i++){
            myNewQueue.add(myNewList.get(i));
        }

        System.out.println("Size of queue is: " + myNewQueue.getSize());
        for(int i = 0; i < myNewList.size(); i++){
            System.out.println(myNewQueue.peek());
            myNewQueue.poll();
        }
    }
}

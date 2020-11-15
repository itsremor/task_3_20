import java.io.*;
import java.io.File;
import java.util.*;


public class main {

    public static void loadFromFile(Collection<String> myQueue) {

        try {
            Scanner listener = new Scanner(System.in);

            System.out.print("Enter file name: ");
            String filename = listener.nextLine();

            Scanner scn = new Scanner(new File(filename));

            scn.useDelimiter("[^а-яА-Яa-zA-Z0-9_]+");

            while (scn.hasNext()) {
                myQueue.add(scn.next());
            }
        }
        catch (FileNotFoundException t){}
    }

    public static void main(String[] args) {

        Comparator<String> comparator = new MyComporator();

        Queue<String> myQueue = new PriorityQueue<>(comparator);

        loadFromFile(myQueue);

        System.out.println("Size of queue is: " + myQueue.size());

        while (myQueue.size() != 0){
            System.out.println(myQueue.peek());
            myQueue.poll();
        }
    }
}
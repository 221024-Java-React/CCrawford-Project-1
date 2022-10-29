

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericLinkedList {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        LinkedList<Integer> llist = new LinkedList<>(arr);


        llist.addLast(llist.get(0));

        for(int i : llist){
            System.out.println(i);
        }
    }
}

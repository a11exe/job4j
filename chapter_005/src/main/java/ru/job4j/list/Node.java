package ru.job4j.list;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class Node<T> {

    public final T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasLoop(Node first) {

        if (first == null)  {
            // list does not exist..so no loop either
            return false;
        }

        Node slow, fast; // create two references.

        slow = first;
        fast = first; // make both refer to the start of the list

        while (true) {
            slow = slow.next;          // 1 hop
            if (fast.next != null) {
                fast = fast.next.next; // 2 hops
            } else {
                return false;          // next node null => no loop
            }
            if (slow == null || fast == null) {
                // if either hits null..no loop
                return false;
            }
            if (slow == fast) {
                // if the two ever meet...we must have a loop
                return true;
            }
        }
    }
}

// Двунаправленный связный список
public class List {
    Node head;
    Node tail;

    class Node {
        int value;
        Node next;
        Node previous;
    }

    public void addFirst(int value) {
        Node node = new Node();
        node.value = value;
        if (head != null) {
            head.previous = node;
            node.next = head;
        } else {
            tail = node;
        }
        head = node;
    }

    public void removeFirst() {
        if (head != null && head.next != null) {
            head.next.previous = null;
            head = head.next;
        } else {
            head = null;
            tail = null;
        }

    }

    public boolean contains(int value) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void addLast(int value) {
        Node node = new Node();
        node.value = value;
        if (tail != null) {
            node.previous = tail;
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
    }

    public void removeLast() {
        if (tail != null && tail.previous != null) {
            tail.previous.next = null;
            tail = tail.previous;
        } else {
            head = null;
            tail = null;
        }
    }

    public void listSort(List list) {
        boolean needSort = false;
        do {
            needSort = false;
            Node node = list.head;
            while (node != null && node.next != null) {
                if (node.value > node.next.value) {
                    Node before = node.previous;
                    Node after = node.next.next;
                    Node current = node;
                    Node next = node.next;
                    current.next = after;
                    current.previous = next;
                    next.next = current;
                    next.previous = before;
                    if (before != null) before.next = next;
                    else list.head = next;
                    if (after != null) after.previous = current;
                    else list.tail = current;
                    needSort = true;
                }
                node = node.next;
            }
        } while (needSort);
    }

    public void revert() {
        if (head != null && head.next != null) revert(head.next, head);
    }

    private void revert(Node currentNode, Node previousNode) {
        if (currentNode.next == null) head = currentNode;
        else revert(currentNode.next, currentNode);
        currentNode.next = previousNode;
        previousNode.next = null;
    }
}

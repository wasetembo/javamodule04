public class TestTwoWayLinkedList {
    public static void main(String[] args) {
        // Create an instance of TwoWayLinkedList
        TwoWayLinkedList<String> list = new TwoWayLinkedList<>();

        // Test adding elements
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("Initial list: ");
        printList(list);

        // Test adding at a specific index
        list.add(1, "D");
        System.out.println("After adding D at index 1: ");
        printList(list);

        // Test removing an element
        list.remove("B");
        System.out.println("After removing B: ");
        printList(list);

        // Test getting an element
        System.out.println("Element at index 2: " + list.get(2));

        // Test size
        System.out.println("Size of the list: " + list.size());
    }

    // Helper method to print the list
    private static void printList(TwoWayLinkedList<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}

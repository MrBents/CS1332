public class Testing {
    public static void main(String[] args) {
        BSTInterface<Integer> bst = new BST<Integer>();
        System.out.println(bst.size());
        bst.add(4);
        bst.add(5);
        bst.add(2);
        bst.add(1);
        bst.add(3);

        System.out.println(bst.remove(1));
        // System.out.println(bst.remove(3));
        // System.out.println(bst.size());
        // System.out.println(bst.getRoot().getData());
        // System.out.println(bst.getRoot().getLeft().getData());
        // System.out.println(bst.getRoot().getRight().getData());
    }
}

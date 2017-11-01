public class TestIt {
    static AVL<Integer> tree;

    public static void main(String[] args) {
        tree = new AVL();
        tree.add(1);
        tree.add(3);
        tree.add(2);

        // System.out.println(tree.preorder());
        // System.out.println(tree.getRoot());
        // System.out.println(tree.getRoot().getRight());
        // System.out.println(tree.getRoot().getLeft());
    }
}

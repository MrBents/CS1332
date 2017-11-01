public class Testing {
    public static void main(String[] args) {
        ArrayQueue<Integer> aQueue = new ArrayQueue<Integer>();
        LinkedStack<String> stack = new LinkedStack<String>();


        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.pop()); //d


        LinkedNode<String> current = stack.getHead();

        System.out.println(current.getData());
    }
}

public class Testing    {
    private static String letters = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args){
        SinglyLinkedList<String> list = new SinglyLinkedList<String>();
        java.util.Random random = new java.util.Random();

        list.addAtIndex(0, "test5");
        list.addAtIndex(1, "");

        list.removeAtIndex(0);

        System.out.println(list.getTail());
        System.out.println(list.getHead());



        }

}

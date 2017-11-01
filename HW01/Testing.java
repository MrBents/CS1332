public class Testing{

  public static void main(String[] args){
    ArrayList<String> list = new ArrayList<String>();
    list.addAtIndex(0, "0a");
    list.addAtIndex(1, "1a");
    list.addAtIndex(2, "2a");
    list.addAtIndex(3, "3a");
    list.addAtIndex(4, "4a");
    list.addAtIndex(5, "5a");


    System.out.println(list.removeFromFront());
    // System.out.println(list.size());
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list.get(3));
    System.out.println(list.get(4));
  }

}

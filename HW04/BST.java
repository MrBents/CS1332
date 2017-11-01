import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Your implementation of a binary search tree.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        for (T element:data) {
            add(element);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        if (size == 0) {
            root = new BSTNode<T>(data);
            this.size += 1;
        } else {
            addNode(data, root);
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        BSTNode<T> node = getNode(data, root);
        if (node == null) {
            throw new java.util.NoSuchElementException("Element was not found");
        }
        T element = node.getData();
        if (node.getRight() == null && node.getLeft() == null) { // no children
            node = null;
        } else if (node.getRight() != null ^ node.getLeft() != null) { // 1 child
            if (node.getRight() != null) {
                node.setData(node.getRight().getData());
                node.setRight(node.getRight().getRight());
            } else if (node.getLeft() != null) {
                node.setData(node.getLeft().getData());
                node.setRight(node.getLeft().getLeft());
            }
        } else if (node.getRight() != null && node.getLeft() != null) { // Both children
            BSTNode<T> nodeR = node.getRight();
            if (nodeR.getLeft() != null && nodeR.getLeft().getLeft() != null) { //There's more than one child to the left
                BSTNode<T> nodeS = getSuccessor(nodeR);
                node.setData(nodeS.getLeft().getData());
                nodeS.setLeft(null);
            } else if (nodeR.getLeft() != null) {  //only one left child
                BSTNode<T> nodeS = nodeR.getLeft();
                node.setData(nodeS.getData());
                nodeR.setLeft(null);
            } else { //no children to the left
                BSTNode<T> nodeS = nodeR;
                node.setData(nodeS.getData());
                node.setRight(nodeS.getRight());
            }

        }
        this.size -= 1;
        return element;
    }

    private BSTNode<T> getSuccessor(BSTNode<T> node){
        if (node.getLeft().getLeft() != null) {
            return getSuccessor(node.getLeft());
        } else {
            return node;
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        BSTNode<T> node = getNode(data, root);
        if (node == null) {
            throw new java.util.NoSuchElementException("Element was not found");
        }
        return node.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        if (root == null) {
            return false;
        }
        return (get(data).compareTo(data) == 0) ? true : false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public List<T> inorder() {
        LinkedList<T> list = new LinkedList<T>();
        if (root != null) {
            ino(root, list);
        }

        return list;
    }
    private void ino(BSTNode<T> node, LinkedList<T> list){
        if (node != null) {
            ino(node.getLeft(), list);
            list.add(node.getData());
            ino(node.getRight(), list);
        }
    }

    @Override
    public List<T> postorder() {
        LinkedList<T> list= new LinkedList<T>();
        if (root != null) {
            posto(root, list);
        }
        return list;
    }
    private void posto(BSTNode<T> node, LinkedList<T> list){
        if (node != null) {
            posto(node.getLeft(), list);
            posto(node.getRight(), list);
            list.add(node.getData());
        }
    }

    @Override
    public List<T> preorder() {
        LinkedList<T> list= new LinkedList<T>();
        if (root != null) {
            preo(root, list);
        }
        return list;
    }
    private void preo(BSTNode<T> node, LinkedList<T> list){
        if (node != null) {
            list.add(node.getData());
            preo(node.getLeft(), list);
            preo(node.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        LinkedList<T> list= new LinkedList<T>();
        if (root != null) {
            Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
            queue.add(root);
            while (!queue.isEmpty()) {
                BSTNode<T> tNode = queue.poll();
                list.add(tNode.getData());
                if(tNode.getLeft() != null) {
                    queue.add(tNode.getLeft());
                }
                if(tNode.getRight() != null) {
                    queue.add(tNode.getRight());
                }
            }
        }
        return list;
    }
    private void levelo(BSTNode<T> node, LinkedList<T> list){
        if (node == root) {
            list.add(node.getData());
        }
        if (node.getLeft() != null) {
            list.add(node.getLeft().getData());
        }
        if (node.getRight() != null) {
            list.add(node.getRight().getData());
        }
        if (node.getLeft() != null) {
            levelo(node.getLeft(), list);
        }
        if (node.getRight() != null) {
            levelo(node.getRight(), list);
        }

    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        return findHeight(root);
    }

    private int findHeight(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }

        int lefth = findHeight(node.getLeft());
        int righth = findHeight(node.getRight());

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
    private BSTNode<T> getNode(T data, BSTNode<T> node){
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) > 0) {
            return getNode(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return getNode(data, node.getLeft());
        } else {
            return node;
        }

    }


    private BSTNode<T> addNode(T data, BSTNode<T> node){
        if (node == null) {
            this.size += 1;
            return new BSTNode<T>(data);
        } else if ((node.getData()).compareTo(data) < 0) {
            node.setRight(addNode(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addNode(data, node.getLeft()));
        }
        return node;
    }
}

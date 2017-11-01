import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * tempA no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
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
            root = new AVLNode<T>(data);
            this.size += 1;
        } else {
            addNode(data, root);
        }
        root = rebalanceSubTree(root);
    }

    /**
     * creates new node recursively
     * @param node node to start recursive call
     * @param data to be added
     * @return updated root with subtrees
     **/
    private AVLNode<T> addNode(T data, AVLNode<T> node) {
        if (node == null) {
            this.size += 1;
            return new AVLNode<T>(data);
        } else if ((node.getData()).compareTo(data) < 0) {
            node.setRight(addNode(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addNode(data, node.getLeft()));
        }
        int balanceFactor = setHeightbalanceFactor(node);
        if (balanceFactor > 1 || balanceFactor < -1) {
            if (node == root) {
                root = rebalance(node);
            } else {
                node = rebalance(node);
            }
        }

        return node;
    }

    /**
     * sets height and balance factor of node
     * @param node node whose hieght is to be calculated
     * @return the Balance Factor
     **/
    private int setHeightbalanceFactor(AVLNode node) {
        node.setHeight(findHeight(node));
        int left = (node.getLeft() != null)
            ? node.getLeft().getHeight() + 1 : 0;
        int right = (node.getRight() != null)
            ? node.getRight().getHeight()  + 1 : 0;
        int balanceFactor = left - right;
        node.setBalanceFactor(balanceFactor);
        return balanceFactor;
    }

    /**
     * rebalances node
     * @param node to be rotated
     * @return the balanced node
     **/
    private AVLNode<T> rebalance(AVLNode node) {
        int balanceFactorLC = (node.getLeft() != null)
            ? node.getLeft().getBalanceFactor() : 0;
        int balanceFactorRC = (node.getRight() != null)
            ? node.getRight().getBalanceFactor() : 0;
        if (node.getBalanceFactor() < 0) {
            if (balanceFactorRC > 0) {
                node = rleftRotation(node);
            } else if (balanceFactorRC < 0) {
                node = leftRotation(node);
            }
        } else if (node.getBalanceFactor() > 0) {
            if (balanceFactorLC > 0) {
                node = rightRotation(node);
            } else if (balanceFactorLC < 0) {
                node = lrightRotation(node);
            }
        }
        return node;
    }

    /**
     * Right-Left rotation
     * @param node to be rotated
     * @return the rotated node
     **/
    private AVLNode<T> rleftRotation(AVLNode node) {
        AVLNode<T> tempA = node.getRight().getLeft();
        node.getRight().setLeft(null);
        setHeightbalanceFactor(node.getRight());
        tempA.setRight(node.getRight());
        node.setRight(null);
        setHeightbalanceFactor(node);
        tempA.setLeft(node);
        setHeightbalanceFactor(tempA);

        return tempA;
    }

    /**
     * Left-Right rotation
     * @param node to be rotated
     * @return the rotated node
     **/
    private AVLNode<T> lrightRotation(AVLNode node) {
        AVLNode<T> tempA = node.getLeft().getRight();
        node.getLeft().setRight(null);
        setHeightbalanceFactor(node.getLeft());
        tempA.setLeft(node.getLeft());
        node.setLeft(null);
        setHeightbalanceFactor(node);
        tempA.setRight(node);
        setHeightbalanceFactor(tempA);
        return tempA;
    }

    /**
     * Left rotation
     * @param node to be rotated
     * @return the rotated node
     **/
    private AVLNode<T> leftRotation(AVLNode node) {
        AVLNode<T> tempA = node.getRight();
        AVLNode<T> tempB = tempA.getLeft();
        node.setRight(null);
        setHeightbalanceFactor(node);
        tempA.setLeft(node);
        tempA.getLeft().setRight(tempB);
        setHeightbalanceFactor(tempA);

        return tempA;
    }

    /**
     * Right rotation
     * @param node to be rotated
     * @return the rotated node
     **/
    private AVLNode<T> rightRotation(AVLNode node) {
        AVLNode tempA = node.getLeft();
        AVLNode tempB = tempA.getRight();
        node.setLeft(null);
        setHeightbalanceFactor(node);
        tempA.setRight(node);
        tempA.getRight().setLeft(tempB);
        setHeightbalanceFactor(tempA);

        return tempA;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        AVLNode<T> node = getNode(data, root);
        if (node == null) {
            throw new java.util.NoSuchElementException("Element was not found");
        }
        T element = node.getData();
        if (size == 1 && node == root) {
            root = null;
        } else if (node.getRight() == null && node.getLeft() == null) { //0 chld
            removeIfNext(node.getData(), root);
        } else if (node.getRight() != null ^ node.getLeft() != null) { // 1 chld
            if (node.getRight() != null) {
                AVLNode<T> temp = getSuccessor(node.getRight());
                remove(temp.getData());
                node.setData(temp.getData());
                size++;
            } else if (node.getLeft() != null) {
                node = node.getLeft(); /// MAY FUCKUP something with the root
            }
        } else if (node.getRight() != null && node.getLeft() != null) { //2 chld
            AVLNode<T> temp = getSuccessor(node.getRight());
            remove(temp.getData());
            node.setData(temp.getData());
            size++;


        }


        root = rebalanceSubTree(root);

        this.size -= 1;
        return element;
    }

    /**
     * removes succesor node from tree
     * @param node to start removal
     * @param data to be removed
     **/
    public void removeIfNext(T data, AVLNode<T> node) {
        if (node == null) {
            return;
        }
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight().getData().compareTo(data) == 0) {
                if (node.getRight().getLeft() != null) {
                    node.setRight(node.getRight().getLeft());
                } else {
                    node.setRight(null);
                }
            } else {
                removeIfNext(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft().getData().compareTo(data) == 0) {
                if (node.getLeft().getRight() != null) {
                    node.setLeft(node.getLeft().getRight());
                } else {
                    node.setLeft(null);
                }
            } else {
                removeIfNext(data, node.getLeft());
            }
        }
    }

    // private void rebalanceSubTree(AVLNode<T> node) {
    //     if (node != null) {
    //         rebalanceSubTree(node.getRight());
    //         rebalanceSubTree(node.getLeft());
    //         int balanceFactor = setHeightbalanceFactor(node);
    //         if (balanceFactor > 1 || balanceFactor < -1){
    //             if(node == root) {
    //                 root = rebalance(node);
    //             } else {
    //                 node = rebalance(node);
    //             }
    //         }
    //     }
    // }

    /**
     * returns succesor of tempA node
     * @param node node to start recursive rebalancing
     * @return root
     **/
    private AVLNode<T> rebalanceSubTree(AVLNode<T> node) {
        if (node != null) {
            node.setRight(rebalanceSubTree(node.getRight()));
            node.setLeft(rebalanceSubTree(node.getLeft()));
            int balanceFactor = setHeightbalanceFactor(node);
            if (balanceFactor > 1 || balanceFactor < -1) {
                if (node == root) {
                    root = rebalance(node);
                    return root;
                } else {
                    return rebalance(node);
                }
            }
            return node;
        } else {
            return null;
        }
    }

    /**
     * returns succesor of tempA node
     * @param node to the right of wanted node
     * @return succesor node
     **/
    private AVLNode<T> getSuccessor(AVLNode<T> node) {
        if (node.getLeft() != null) {
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
        AVLNode<T> node = getNode(data, root);
        if (node == null) {
            throw new java.util.NoSuchElementException("Element was not found");
        }
        return node.getData();
    }

    /**
     * helper function to get tempA node
     * @param data to be matched
     * @param node to start search
     * @return node wanted or null
     **/
    private AVLNode<T> getNode(T data, AVLNode<T> node) {
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

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data passed is not valid");
        }
        try {
            get(data);
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
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

    /**
     * inorder traversal helper
     * @param list to be added
     * @param node to be recursed
     **/
    private void ino(AVLNode<T> node, LinkedList<T> list) {
        if (node != null) {
            ino(node.getLeft(), list);
            list.add(node.getData());
            ino(node.getRight(), list);
        }
    }


    @Override
    public List<T> postorder() {
        LinkedList<T> list = new LinkedList<T>();
        if (root != null) {
            posto(root, list);
        }
        return list;
    }

    /**
     * postorder traversal helper
     * @param list to be added
     * @param node to be recursed
     **/
    private void posto(AVLNode<T> node, LinkedList<T> list) {
        if (node != null) {
            posto(node.getLeft(), list);
            posto(node.getRight(), list);
            list.add(node.getData());
        }
    }

    @Override
    public List<T> preorder() {
        LinkedList<T> list = new LinkedList<T>();
        if (root != null) {
            preo(root, list);
        }
        return list;
    }

    /**
     * preorder traversal helper
     * @param list to be added
     * @param node to be traversed
     **/
    private void preo(AVLNode<T> node, LinkedList<T> list) {
        if (node != null) {
            list.add(node.getData());
            preo(node.getLeft(), list);
            preo(node.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        LinkedList<T> list = new LinkedList<T>();
        if (root != null) {
            Queue<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
            queue.add(root);
            while (!queue.isEmpty()) {
                AVLNode<T> tNode = queue.poll();
                list.add(tNode.getData());
                if (tNode.getLeft() != null) {
                    queue.add(tNode.getLeft());
                }
                if (tNode.getRight() != null) {
                    queue.add(tNode.getRight());
                }
            }
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return (root != null) ? root.getHeight() : -1;
    }

    /**
     * Gets height recursively
     * @param node wanted
     * @return height
     **/
    private int findHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }

        int lefth = findHeight(node.getLeft());
        int righth = findHeight(node.getRight());

        if (lefth > righth) {
            node.setHeight(lefth + 1);
            return node.getHeight();
        } else {
            node.setHeight(righth + 1);
            return node.getHeight();
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}

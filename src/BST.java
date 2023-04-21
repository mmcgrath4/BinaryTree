import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Your Name Here
 * @version: Date
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        return find(val, root);
    }

    public boolean find(int target, BSTNode current) {
        // Base cases
        if (current.getVal() == target) {
            return true;
        }
        if (target > current.getVal() && current.getRight() == null) {
            return false;
        }
        if (target < current.getVal() && current.getLeft() == null) {
            return false;
        }
        // Recursive step: if the node is greater than val, look left
        // if it's less than val, look right
        if (current.getVal() < target) {
            return find(target, current.getRight());
        }
        return find(target, current.getLeft());

    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        return inOrder(root, new ArrayList<>());
    }

    public ArrayList<BSTNode> inOrder(BSTNode node, ArrayList<BSTNode> list) {
        // Goes all the way to the left of the tree
        if (node.getLeft() != null) {
            inOrder(node.getLeft(), list);
        }
        // Adds the node
        list.add(node);
        // Traverses the right subtree
        if (node.getRight() != null) {
            inOrder(node.getRight(), list);
        }

        return list;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() { return preOrder(root, new ArrayList<>()); }

    public ArrayList<BSTNode> preOrder(BSTNode node, ArrayList<BSTNode> list) {
        // adds the root
        list.add(node);
        // Traverses to the left, adding along the way
        if (node.getLeft() != null) {
            preOrder(node.getLeft(), list);
        }
        // Traverses to the right
        if (node.getRight() != null) {
            preOrder(node.getRight(), list);
        }
        return list;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        return postOrder(root, new ArrayList<>());
    }

    public ArrayList<BSTNode> postOrder(BSTNode node, ArrayList<BSTNode> list) {
        // Traverses left
        if (node.getLeft() != null) {
            postOrder(node.getLeft(), list);
        }
        // Traverses right
        if (node.getRight() != null) {
            postOrder(node.getRight(), list);
        }
        // Adds the root
        list.add(node);
        return list;
    }
    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        BSTNode inserted = new BSTNode(val);
        put(inserted, val, root);
    }

    public BSTNode put(BSTNode inserted, int val, BSTNode node) {
        BSTNode left = node.getLeft();
        BSTNode right = node.getRight();
        // Returns same tree if it's already there
        if (node.getVal() == val) {
            return root;
        }
        if (node.getVal() > val) {
            // Inserts node if in the correct spot
            if (left == null) {
                node.setLeft(inserted);
                return root;
            }
            if (left.getVal() > val) {
                left.setLeft(inserted);
                return root;
            }
            // Recursively calls the same method
            return put(inserted, val, left);
        }
        if (node.getVal() < val) {
            // Inserts node if in correct spot
            if (right == null) {
                node.setRight(inserted);
                return root;
            }
            if (right.getVal() < val) {
                right.setRight(inserted);
                return root;
            }
            // Recursively calls same method
            return put(inserted, val, right);
        }
        return root;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

    }
}

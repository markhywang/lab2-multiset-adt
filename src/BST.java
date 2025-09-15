/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * You can also see <a href="https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">
 *     CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this assignment.
 */
public class BST {
    // we use Integer here so that we can set the root to null. This is the same idea as
    // how the Python code uses None in the BST implementation.
    private Integer root;

    private BST left;
    private BST right;

    public BST(int root) {
        this.root = root;
        this.left = new BST();
        this.right = new BST();
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        root = null;
        // left and right default to being null
    }


    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == this.root) {
            return true;
        } else if (item < this.root) {
            return this.left.contains(item);
        }
        return this.right.contains(item);
    }


    public void insert(int item) {
        if (this.isEmpty()) {
            this.root = item;
            this.left = new BST();
            this.right = new BST();
        } else if (item <= this.root) {
            this.left.insert(item);
        } else {
            this.right.insert(item);
        }
    }


    public void delete(int item) {
        if (this.isEmpty()) {
            return;
        } else if (this.root == item) {
            this.deleteRoot();
        } else if (item < this.root) {
            this.left.delete(item);
        } else {
            this.right.delete(item);
        }
    }

    private void deleteRoot() {
        if (this.left.isEmpty() && this.right.isEmpty()) {
            this.root = null;
            this.left = null;
            this.right = null;
        } else if (this.left.isEmpty()) {
            Integer tempRightRoot = this.right.root;
            BST tempRightLeft = this.right.left;
            BST tempRightRight = this.right.right;
            this.root = tempRightRoot;
            this.left = tempRightLeft;
            this.right = tempRightRight;
        } else if (this.right.isEmpty()) {
            Integer tempLeftRoot = this.left.root;
            BST tempLeftLeft = this.left.left;
            BST tempLeftRight = this.left.right;
            this.root = tempLeftRoot;
            this.left = tempLeftLeft;
            this.right = tempLeftRight;
        } else {
            int maxFromLeft = this.left.extractMax();
            this.root = Integer.valueOf(maxFromLeft);
        }
    }


    private int extractMax() {
        if (this.right.isEmpty()) {
            Integer maxItem = this.root;
            Integer tempLeftRoot = this.left.root;
            BST tempLeftLeft = this.left.left;
            BST tempLeftRight = this.left.right;
            this.root = tempLeftRoot;
            this.left = tempLeftLeft;
            this.right = tempLeftRight;
            return maxItem.intValue();
        } else {
            return this.right.extractMax();
        }
    }

    public int height() {
        if (this.isEmpty()) {
            return -1;
        } else {
            return 1 + Math.max(this.left.height(), this.right.height());
        }
    }

    public int count(int item) {
        if (this.isEmpty()) {
            return 0;
        } else if (item == this.root) {
            return 1 + this.left.count(item) + this.right.count(item);
        } else if (item < this.root) {
            return this.left.count(item);
        } else {
            return this.right.count(item);
        }
    }

    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + this.left.getSize() + this.right.getSize();
        }
    }

    public static void main(String[] args) {
        // You can also add code here to do some basic testing if you want,
        // but make sure it doesn't contain syntax errors
        // or else we won't be able to run your code on MarkUs since the file won't
        // compile. Always make sure to run the self tests on MarkUs after you update your code.
        BST bst = new BST();
        int a = 1;
        bst.insert(a);
        System.out.println(bst.contains(a));
    }

}

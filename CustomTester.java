

public class CustomTester {
    MyBST<Integer, Integer> completeTree;

    // 
    /**
     * The setup method create a complete tree with height three
     * The tree has following structure and will be used as testing purpose
     *           4
     *         /  \
     *       2     6
     *     /  |   /
     *    1   3  5
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = 
            new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = 
            new MyBST.MyBSTNode<>(5, 50, six);

        this.completeTree = new MyBST<>();
        this.completeTree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        this.completeTree.size = 6;
    }

    public void testInsert(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        completeTree.insert(10, 1);
        assertEquals((Integer)10, root.getRight().getRight().getKey());
        assertEquals(7, completeTree.size);
    }

}

import java.util.ArrayList;


public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * 
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 
     * @param key
     * @param value
     * @return
     */
    public V insert(K key, V value){
        if(key == null){
            throw new NullPointerException();
        }

        if(size == 0){
            MyBSTNode<K,V> newNode = new MyBSTNode<K,V>(key, value, null);
            root = newNode;
            size++;
            return value;
        }
        
        MyBSTNode<K, V> current = root;
        if(search(key) != null){
            while(current.key != key){
                if(current.key.compareTo(key) > 0){
                    if(current.getLeft() != null){
                        current = current.getLeft();
                    }
                    else{
                        current = current.getRight();
                    }
                }
                else{
                    if(current.getRight() != null){
                        current = current.getRight();
                    }
                    else{
                        current = current.getLeft();
                    }
                }
            }
            V orginalVal = current.getValue();
            current.setValue(value); 
            return orginalVal;
        }
        else{
            while(current != null){
                if(current.key.compareTo(key) > 0){
                    if(current.getLeft() == null){
                        MyBSTNode<K, V> newNode = new MyBSTNode<K,V>(key, value, current);
                        current.setLeft(newNode);
                        break;
                    }
                    else{
                        current = current.getLeft();
                    }
                }
                else{
                    if(current.getRight() == null){
                        MyBSTNode<K, V> newNode = new MyBSTNode<K,V>(key, value, current);
                        current.setRight(newNode);
                        break;
                    }
                    else{
                        current = current.getRight();
                    }
                }
            }
            size++;
            return value;
        }


    }

    /**
     * 
     * @param key
     * @return
     */
    public V search(K key){

        if(key == null){
            return null;
        }

        MyBSTNode<K,V> current = root;

        while(current !=null){
            if(current.getKey().compareTo(key) < 0){
                current = current.getRight();
            }
            else if(current.getKey().compareTo(key) > 0){
                current = current.getLeft();
            }
            else{
                break;
            }
        }

        if(current == null){
            return null;
        }

        if(current.getKey().compareTo(key) != 0){
            return null;
        }
        else{
            return current.getValue();
        }
    }

    public V remove(K key){

        if(search(key) == null){
            return null;
        }

        MyBSTNode<K,V> current = root;
        while(current.key != key){
            if(current.key.compareTo(key) > 0){
                if(current.getLeft() != null){
                    current = current.getLeft();
                }
                else{
                    current = current.getRight();
                }
            }
            else{
                if(current.getRight() != null){
                    current = current.getRight();
                }
                else{
                    current = current.getLeft();
                }
            }
        }

        V rtnVal = current.getValue();

        
        if(current.getLeft() == null && current.getRight() == null){
            if(current.getParent().getLeft() == current){
                current.getParent().setLeft(null);
            }
            else{
                current.getParent().setRight(null);
            }
        }
        else if(current.getLeft() == null || current.getRight() == null){
            if(current.getRight() != null){
                if(current.getParent().getLeft() == current){
                    current.getParent().setLeft(current.getRight());
                }
                else{
                    current.getParent().setRight(current.getRight());
                }
            }
            else{
                if(current.getParent().getLeft() == current){
                    current.getParent().setLeft(current.getLeft());
                }
                else{
                    current.getParent().setRight(current.getLeft());
                } 
            }
        }
        else{
            MyBSTNode<K,V> newNode = current.successor();
            remove(current.successor().getKey());
            current = newNode;
        }

        size--;
        return rtnVal;
    }
    
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K,V>> rtnList = new ArrayList<>();
        MyBSTNode<K,V> current = root;
        while(current.predecessor() != null){
            current = current.predecessor();
        }

        while(current.successor() != null){
            rtnList.add(current);
            current = current.successor();
        }

        rtnList.add(current);
        return rtnList;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){ //checkes if this node has a right node
                MyBSTNode<K,V> curr = this.getRight(); // if there is a right node  
                while(curr.getLeft() != null){         //sets the current seccosor to the right node
                    curr = curr.getLeft(); //sets the current seccosor to the furtherthest left
                }
                return curr; //returs the node
            }
            else{ //if there is a right node we need to go up the the tree
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * 
         * @return
         */
        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null){ 
                MyBSTNode<K,V> curr = this.getLeft(); 
                while(curr.getRight() != null){         
                    curr = curr.getRight(); 
                }
                return curr; 
            }
            else{ 
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
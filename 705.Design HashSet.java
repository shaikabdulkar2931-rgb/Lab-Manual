class MyHashSet {
    private static final int BASE = 769; 
    private LinkedList<Integer>[] container;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        container = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            container[i] = new LinkedList<>();
        }
    }
    
    private int hash(int key) {
        return key % BASE;
    }
    
    public void add(int key) {
        int index = hash(key);
        if (!container[index].contains(key)) {
            container[index].addFirst(key);
        }
    }
    
    public void remove(int key) {
        int index = hash(key);
        container[index].remove((Integer) key);
    }
    
    public boolean contains(int key) {
        int index = hash(key);
        return container[index].contains(key);
    }
}


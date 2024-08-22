public class IntList {
    /*build a list class from scratch*/
    public int first;
    public IntList rest;
    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
    
    public int size() {
        if (rest == null) {
            return 1;
        } else {
            return 1 + rest.size();
        }
    }

    public int iterativeSive() {
        int count = 0;
        IntList p = this;
        while (p != null) {
            count ++;
            p = p.rest;
        }
        return count;
    }
    
    public int get(int i){
        if (i == 0){
            return first;
        } else {
            return rest.get(i - 1);
        }
        
    }
    
    public int iterativeGet (int i) {
        IntList p = this;
        for (int k = 0; k < this.size(); k++) {
            if (k == i) {
                return p.first;
            }
            p = p.rest;
        }
        return 0;
    }


    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);
        
        System.out.println(L.size());
        System.out.println(L.iterativeSive());
        System.out.println(L.get(2));
        System.out.println(L.iterativeGet(2));
    }
}

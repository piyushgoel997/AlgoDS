package Misc;

/**
 * Created by piyus on 03-06-2017 at 16:41.
 */
public class UF {
    /**
     * Union Find implemented using the Quick Union(or lazy) approach.
     */

    private int[] id;

    /**
     * @param n - total number of elements.
     */
    public UF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * takes the union of the sets containing the given elements.
     * @param x - element of the first set
     * @param y - element of the second set.
     */
    public void union(int x, int y) {
        int idy = id[y];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == idy) {
                id[i] = id[x];
            }
        }
    }

    /**
     * check if two elements are connected i.e. belong to the same set.
     * @param x - first element.
     * @param y - second element.
     * @return - true if connected, else false.
     */
    public boolean connected(int x, int y) {
        return id[x] == id[y];
    }

}

package easy.graph;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 找到小镇的法官 997
     *
     * @param N
     * @param trust
     * @return
     * @data 2019/7/24
     */
    public int findJudge(int N, int[][] trust) {
        int[] in = new int[N + 1];
        int[] to = new int[N + 1];
        for (int[] t : trust) {
            to[t[0]]++;
            in[t[1]]++;
        }
        for (int i = 1; i < N + 1; i++) {
            if (to[i] == 0 && in[i] == N - 1)
                return i;
        }
        return -1;
    }


    public static void main(String[] args) {
    }
}

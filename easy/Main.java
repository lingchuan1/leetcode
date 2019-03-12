package easy;


import easy.linked_list.Solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int s = scanner.nextInt();
//        int []card = new int[n];
//        for(int i = 0;i < n;i ++){
//            card[i] = scanner.nextInt();
//        }
//
//        int up = 1,down = 1;
//        for(int i = n;i >0;i--){
//            up *= i;
//        }
//        for(int i = s;i > 0;i--){
//            down *= i;
//        }
//        for(int i = n-s;i > 0;i--){
//            down *= i;
//        }
//        int ss = up/down;
//        int res = (n-s) *2*ss;
//        System.out.println(res);

        //连续开n枪，s种颜色的气球，一个数组n个数，表示每一枪打中的气球的颜色，0表示没有打中，求打爆所有颜色的气球最少用了几枪
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int []color = new int[n];
        for(int i = 0;i < n;i++){
            color[i] = sc.nextInt();
        }
        Map map = new HashMap();
        for(int i = 0;i < n;i++){
            if(color[i] != 0){
                map.put(color[i],i);
            }
            if(map.size() == s){
                System.out.println(i);
                break;
            }
        }
        if(map.size()<s)
            System.out.println(-1);
    }

    //3 2
    //0 1 2
    //6

    //4 3
    //1 1 2 1
    //6

}

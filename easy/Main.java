package easy;


import easy.linked_list.Solution;

import java.text.DateFormat;
import java.util.*;

import static java.lang.Math.floor;

public class Main {

//    static boolean check(double x,int N,int K,double d[])
//    {
//        int num = 0;
//        for (int i=0; i<N; i++)
//        {
//            num += (int)(d[i]/x);
//        }
//        return num >= K;
//    }
//
//
//    static void solve(double left, double right,int N,int K,double d[])
//    {
//        // 2^(-100) 约等于 10^(-30)
//        for (int i=0; i<100; i++)
//        {
//            double mid = (left+right)/2;
//            if (check(mid,N,K,d))
//            {
//                left = mid;
//            }
//            else
//            {
//                right = mid;
//            }
//        }
//        System.out.println(floor(right*100)/100);
//    }
//    public static void greet(){
//        System.out.println("Hello");
//    }
//    public static void main(String[] args)
//    {
//
////        Main main = new Main();
////        System.out.println(main.test(7,"0010001"));
////        Vector<Integer> vector = new Vector<>();
////        vector.add(1);
////        vector.add(1);
////        for(int i = 0;i <vector.size();i++)
////            System.out.println(vector.get(i));
////        int i = 2;
////        if(i ++ > 2 && ++ i >=3){
////            System.out.println(i);
////        }
////        System.out.println(i);
////        DateFormat df;
////        Date date = new Date();
////        df = DateFormat
////        String s = df.format(date)
////
//    }
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt();
////        int s = scanner.nextInt();
////        int []card = new int[n];
////        for(int i = 0;i < n;i ++){
////            card[i] = scanner.nextInt();
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
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int s = sc.nextInt();
//        int []color = new int[n];
//        for(int i = 0;i < n;i++){
//            color[i] = sc.nextInt();
//        }
//        Map map = new HashMap();
//        for(int i = 0;i < n;i++){
//            if(color[i] != 0){
//                map.put(color[i],i);
//            }
//            if(map.size() == s){
//                System.out.println(i);
//                break;
//            }
//        }
//        if(map.size()<s)
//            System.out.println(-1);
//    }


    //3 2
    //0 1 2
    //6

    //4 3
    //1 1 2 1
    //6

    public int test(int n, String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println(s.charAt(i));
            list.add(Integer.parseInt(s.charAt(i) + ""));
        }
        for (int m = 0; m < list.size() - 1; m++) {
            if (list.get(m) + list.get(m + 1) == 1) {
                list.remove(m);
                list.remove(m);
                if (m == 0) m = -1;
                else {
                    m -= 2;
                }
            }
        }
        int res = list.size();
        return res;
    }


    public static void main(String[] args) {
    }

}


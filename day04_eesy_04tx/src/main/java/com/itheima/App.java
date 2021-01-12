package com.itheima;

public class App {
    public static void main(String[] args) {
        System.out.println("===");
        System.out.println(fun(0));
        System.out.println("===");
    }

    public static int fun(int i) {
        try {
            if (i == 0) {
                throw new RuntimeException("i == 0 !");
            }
            System.out.println(2);
        } catch (RuntimeException e) {
//            e.printStackTrace();
        }
        return 1;
    }
}

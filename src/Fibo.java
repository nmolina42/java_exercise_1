package src;

import java.util.Scanner;

public class Fibo implements src.Command {

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner sc) {
        System.out.println("Quel est le nombre ou le chiffre ?");
        String nb = sc.nextLine();
        int n = Integer.parseInt(nb);
        int f1 = 1;
        int f2 = 1;
        int fn = n;
        if (n == 0){
            System.out.println(n);
            return false;
        }
        if (n == 1 || n==2){
            System.out.println("1");
            return false;
        }
        for (int i = 2; i < n; i++) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        System.out.println(fn);
        return false;
    }
}

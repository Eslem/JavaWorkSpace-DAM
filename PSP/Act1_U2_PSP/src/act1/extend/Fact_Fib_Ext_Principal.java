/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act1.extend;

/**
 *
 * @author eslem
 */
public class Fact_Fib_Ext_Principal {

    public static void main(String[] args) {
        // TODO code application logic here
        Fibonacci fibonacci = new Fibonacci(10);
        fibonacci.start();

        Factorial factorial = new Factorial(10);
        factorial.start();
    }
}

class Factorial extends Thread {

    int n = 0;

    public Factorial(int limit) {
        this.n = limit;
    }

    public int factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    @Override
    public void run() {
        System.out.println("Empieza el proceso Factorial");
        System.out.println("Acabo el proceso factorial, valor: " + factorial(n));
    }

}

class Fibonacci extends Thread {

    int n = 0;

    public Fibonacci(int limit) {
        this.n = limit;
    }

    public int fibonacci(int i) {
        if (i < 2) {
            return i;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }

    @Override
    public void run() {
        System.out.println("Empieza el proceso Fibonacci");
        System.out.println("Acabo el proceso Fibonacci, valor: " + fibonacci(n));
    }

}

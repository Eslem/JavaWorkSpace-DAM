/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act1.impl;

/**
 *
 * @author eslem
 */
public class Fact_Fib_Imp_Principal {

    public static void main(String[] args) {
        // TODO code application logic here
        Thread fibonacci = new Thread(new Fibonacci(10), "Fibonacci");
        fibonacci.start();

        Thread factorial = new Thread(new Factorial(10), "Factorial");
        factorial.start();
    }
}

class Factorial implements Runnable {

    int n = 0;

    public Factorial(int limit) {
        this.n = limit;
    }

    @Override
    public void run() {
        System.out.println("Empieza el proceso FActorial");
        System.out.println("Acabo el proceso factorial, valor: " + factorial(n));
    }

    public int factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

}

class Fibonacci implements Runnable {

    int n = 0;

    public Fibonacci(int limit) {
        this.n = limit;
    }

    @Override
    public void run() {
        System.out.println("Empieza el proceso Fibonacci");
        System.out.println("Acabo el proceso Fibonacci, valor: " + fibonacci(n));

    }

    public int fibonacci(int i) {
        if (i < 2) {
            return i;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }

}

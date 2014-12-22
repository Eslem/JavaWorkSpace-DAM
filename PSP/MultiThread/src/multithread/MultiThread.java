package multithread;


/**
 *
 * @author eslem
 */
public class MultiThread {
public static void main( String args[] ) {
TestTh t1,t2,t3;

// Creamos los threads
t1 = new TestTh( "hilo 1");
t2 = new TestTh( "hilo 2");
t3 = new TestTh( "hilo 3");

// Arrancamos los threads
t1.start();
t2.start();
t3.start();
}
}
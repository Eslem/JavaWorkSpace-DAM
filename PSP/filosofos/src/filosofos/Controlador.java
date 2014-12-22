package filosofos;

public class Controlador {

    private static final int pensando = 0;
    private static final int hambriento = 1;
    private static final int comiendo = 2;
    private int n; //numero de filosofos
    private int estado[];

    public Controlador(int n) {
        this.n = n;
        estado = new int[n]; //estado del filosofo
    }
// EL FILOSOFO id intenta SUS TENEDORES

    public synchronized void tomarTenedores(int id) {

        estado[id] = hambriento; // establece que tiene hambre
        
        System.out.println("Filosofo "+ id+" hambriento");
        
        prueba(id);
        while (estado[id] != comiendo) // ESPERA x 2 TENEDORES
        {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
// EL FILOSOFO id SUELTA SUS TENEDORES

    public synchronized void soltarTenedores(int id) {
        int der, izq;
//calcula la posicion a su derecha e izq.
        der = (id + 1) % n;
        izq = id - 1;
        if (izq < 0) {
            izq = n - 1;
        }
        estado[id] = pensando;
        prueba(izq);
        prueba(der);
    }
//Prueba si los dos filosofos a su lado no estan
//comiendo

    public void prueba(int id) {
        int der, izq;
//calcula la posicion a su derecha e izq.
        der = (id + 1) % n;
        izq = id - 1;
        if (izq < 0) {
            izq = n - 1;
        }
        if (estado[id] == hambriento && estado[izq] != comiendo
                && estado[der] != comiendo) {
            estado[id] = comiendo;
            notifyAll();
        }
    }

}

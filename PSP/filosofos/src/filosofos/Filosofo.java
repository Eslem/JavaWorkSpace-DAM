package filosofos;

public class Filosofo extends Thread {

    private int id;
    private Controlador controlador;
    private int nComidas;

    public Filosofo(int id, Controlador c) {
        this.id = id;
        controlador = c;
        nComidas = 0;
    }
//el filosofo pasa un tiempo pensando

    public void pensando() {
        System.out.println("Filosofo " + id + " pensando");
        try {
            Thread.sleep((int) (Math.random() * 400));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//el filosofo toma un tiempo comiendo

    public void comer() {
        nComidas++;
        System.out.println("Filosofo " + id + " comiendo -> Numero comidas:" + nComidas);
        try {
            Thread.sleep((int) (Math.random() * 500));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        do {
            pensando(); //el filosofo pasa un tiempo pensando
            controlador.tomarTenedores(id); //intenta tomar tenedores
// Si tiene 2 tenedores;
            comer();
            controlador.soltarTenedores(id);
        } while (true);
    }

}

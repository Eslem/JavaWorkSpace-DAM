package filosofos;


/* Érase una vez cinco filósofos que vivían juntos. 
 La vida de cada filósofo consistía principalmente en pensar 
 y comer y, tras años de pensar, todos los filósofos se habían 
 puesto de acuerdo en que la única comida que contribuía a sus 
 esfuerzos pensadores eran los espaguetis.

 Los preparativos de la comida eran simples: una mesa redonda en 
 la que había una gran fuente de espaguetis, cinco platos, uno para 
 cada filósofo y cinco tenedores. Un filósofo que quiera comer irá 
 a su lugar asignado en la mesa y, usando los dos tenedores de cada 
 lado del plato, cogerá los espaguetis y se los comerá. El problema 
 es el siguiente: Inventar un ritual (algoritmo) que permita comer a 
 los filósofos. El algoritmo debe satisfacer la exclusión mutua (dos 
 filósofos no pueden emplear el mismo tenedor a la vez), además de 
 evitar el interbloqueo y la inanición */
public class Filosofos {

    public static void main(String[] args) {
        
        Controlador c = new Controlador(5); // 5 FILOSOFOS
        Filosofo f[] = new Filosofo[5];
        for (int cont = 0; cont < 5; cont++) {
            f[cont] = new Filosofo(cont, c);
            f[cont].start();
        }
//dejamos pasar un tiempo y terminamos los hilos
        try {
            Thread.sleep((int) (8000));
        } catch (Exception ex1) {
            System.out.println(ex1);
        }
        System.exit(0);
    }
}

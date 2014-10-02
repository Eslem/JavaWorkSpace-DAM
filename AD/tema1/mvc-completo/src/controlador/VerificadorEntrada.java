package controlador;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class VerificadorEntrada {

  public final static int NUMERO = 1;
  public final static int TEXTO = 0;
  public final static int EMAIL = 2;
  private Pattern pattern;
  private Matcher matcher;
  private static final String EMAIL_PATTERN =
          "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private boolean esValidoEmail(String string) {

    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(string);
    return matcher.matches();

  }

  private boolean esValidoNumero(String texto) {
    int x;
    boolean esvalido = true;

    try {
      x = Integer.parseInt(texto);

    } catch (Exception e) {
      esvalido = false;
    }
    return esvalido;


  }

  boolean esValido(Component component, String texto, boolean esobligatorio, String tipodato) {

    int tipovalidacion = TEXTO;
    if (tipodato.equals("numero")) {
      tipovalidacion = NUMERO;
    }
    if (tipodato.equals("email")) {
      tipovalidacion = EMAIL;
    }

    Boolean esvacio = texto.isEmpty();


    /* Tabla de verdad

     * ---------------|----------------|---------------
     *                |    1  |   2    |    3   |   4
     * ---------------|----------------|---------------
     *                |NUMERO | NUMERO | NUMERO | NUMERO
     * ---------------|----------------|--------|-------
     * ESVACIO        |  SI   |  SI    |  NO    |  NO
     * ESOBLIGATORIO  |  SI   |  NO    |  SI    |  NO
     * ---------------|----------------|---------------
     * ERROR          |   X   |        |        |
     * COMPROBAR      |       |        |  X     |   X
     * SALIR          |       |   X    |        |
     *
     *
     *
     * Tabla de verdad
     * ---------------|----------------|---------------
     *                |EMAIL  | EMAIL  | EMAIL  | EMAIL
     * ---------------|----------------|--------|-------
     * ESVACIO        |  SI   |  SI    |  NO    |  NO
     * ESOBLIGATORIO  |  SI   |  NO    |  SI    |  NO
     * ---------------|----------------|---------------
     * ERROR          |   X   |        |        |
     * COMPROBAR      |       |        |  X     |   X
     * SALIR          |       |   X    |        |
     */

    // Caso 1.
    if (esobligatorio && esvacio) {
      JOptionPane.showMessageDialog(component.getParent(), "Este campo " + tipodato + " es Obligatorio");
      return false;
    }

    // Caso 2.
    if (!esobligatorio && esvacio) {
      return true;
    }

    // Caso 3
    if (!esvacio) {
      switch (tipovalidacion) {
        case EMAIL:
          if (!esValidoEmail(texto)) {
            JOptionPane.showMessageDialog(component.getParent(), "Email no Valido");
            return false;
          }
          break;
        case NUMERO:
          if (!esValidoNumero(texto)) {
            JOptionPane.showMessageDialog(component.getParent(), "Número no Valido");
            return false;
          }
          break;
      } // if
    }

    return true;

  }
}

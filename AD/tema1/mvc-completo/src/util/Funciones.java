/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fichero: Funciones.java
 *
 * @date 14-feb-2014
 * @author Paco Aldarias <paco.aldarias@ceedcv.es>
 */
public class Funciones {

  private static SimpleDateFormat formato;

  public boolean invalidoEmail(String email) {
    final String PATRON_EMAIL = "[-\\w\\.]+@[\\.\\w]+\\.\\w+";
    Pattern patron = Pattern.compile(PATRON_EMAIL);
    Matcher compara = patron.matcher(email);
    return compara.matches();

  }

  public static String getFechaFormateada(Date fecha, int tipoFormato) {
    formato = new SimpleDateFormat("yyyy-MM-dd");
    if (tipoFormato == Constantes.DIA_MES_ANIO) {
      formato = new SimpleDateFormat("dd-MM-yyyy");
    }
    if (tipoFormato == Constantes.ANIO_MES_DIA) {
      formato = new SimpleDateFormat("yyyy-MM-dd");
    }
    return formato.format((Date) fecha);
  }

  /*
   * Función que devuelve la fecha del sistema
   */
  public String ahora(int tipoFormato) {
    String string = null;
    GregorianCalendar c = new GregorianCalendar();
    Date d = new Date();
    c.setTime(d);

    int dia = c.get(Calendar.DAY_OF_MONTH);
    int mes = c.get(Calendar.MONTH) + 1;
    int ano = c.get(Calendar.YEAR);
    int hora = c.get(Calendar.HOUR);
    int minutos = c.get(Calendar.MINUTE);

    if (tipoFormato == Constantes.DIA_MES_ANIO) {
      string = Integer.toString(dia) + "-"
              + Integer.toString(mes) + "-"
              + Integer.toString(ano);
    }
    if (tipoFormato == Constantes.ANIO_MES_DIA) {
      string = Integer.toString(ano) + "-"
              + Integer.toString(mes) + "-"
              + Integer.toString(dia);
    }

    return string;
  }
}

/*
 * TableModelCliente.java
 *
 * Created on 25 de febrero de 2012, 22:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package modelotablas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import vistabd.Modelo;

/**
 *
 * @author pacoaldarias
 */

/*
 * Consultar Ejemplo Swing11
 *
 */
public class TableModelProducto extends AbstractTableModel {

  Modelo modelo;
  ModeloTablas m = new ModeloTablas();
  private ArrayList datos = new ArrayList();
  private String[] columnas = {"Id", "Nombre", "Importe"};
  private Class[] types = new Class[]{
    java.lang.Integer.class, java.lang.String.class, java.lang.String.class};

  public TableModelProducto(Modelo modelo_) {
    modelo = modelo_;
    datos = m.leerTablaModeloProducto(modelo.getProductos());
  }

  /**
   * Devuelve el nombre de la columna
   *
   * @param col Indice de columna
   * @return Nombre de la columna
   */
  public String getColumnName(int col) {
    return columnas[col].toString();
  }

  /**
   * Devuelve el numero de filas
   *
   * @return numero de filas
   */
  public int getRowCount() {
    return getDatos().size();
  }

  /**
   * Devuelve el numero de columnas
   *
   * @return numero de columnas
   */
  public int getColumnCount() {
    return columnas.length;
  }

  /**
   * Devuelve el valor del objeto en la fila y columna
   *
   * @param row fila a buscar
   * @param col columna a buscar
   * @return valor del objeto
   */
  public Object getValueAt(int row, int col) {
    Object[] fila = (Object[]) getDatos().get(row);
    return fila[col];
  }

  /**
   * Devuelve la clase que corresponde al tipo de columna
   *
   * @param columnIndex columna
   * @return Clase tipo
   */
  public Class getColumnClass(int columnIndex) {
    return types[columnIndex];
  }

  /**
   * Determina si una fila y columna ha de ser editable
   *
   * @param row fila de la tabla
   * @param col columna de la tabla
   * @return valor booleano indicando si es o no editable
   */
  public boolean isCellEditable(int row, int col) {
    return false; // Nunguna columna es editable
  }

  /**
   * Actualiza un objeto de una fila y columna
   *
   * @param value Objeto a actualizar
   * @param row fila de la tabla
   * @param col columna de la tabla
   */
  public void setValueAt(Object value, int row, int col) {
    Object[] fila = (Object[]) getDatos().get(row);
    fila[col] = value;
    fireTableCellUpdated(row, col);
  }

  /**
   * A�ade una fila al modelo
   *
   * @param fila fila a a�adir
   */
  public void addRow(Object[] fila) {
    getDatos().add(fila);
    fireTableDataChanged();

  }

  /**
   * Elimina una fila del modelo
   *
   * @param fila indice de la fila a eliminar
   */
  public void removeRow(int fila) {
    getDatos().remove(fila);
    fireTableDataChanged();
  }

  /**
   * @return the datos
   */
  public ArrayList getDatos() {
    return datos;
  }
}

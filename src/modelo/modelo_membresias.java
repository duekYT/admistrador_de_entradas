/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexionBaseDatos.conexionBaseDatos;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class modelo_membresias extends conexionBaseDatos {
  
  private int id;
  private String nombre;
  
  public modelo_membresias(){
    
  }
  
  public modelo_membresias( Connection _conexion ){
    
    conexion = _conexion;
    
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String toString() {
    return this.nombre ;
  }
  
  public Vector<modelo_membresias> mostrarmodelo(){
  
    Vector<modelo_membresias> datos = new Vector<modelo_membresias>();
   
    modelo_membresias valores = null;
   
    valores = new modelo_membresias();
    valores.setId(0);
    valores.setNombre("Selecciona membresia ");
    datos.add(valores);
    
    try {
      
      String nombre_tabla = "membresia_datos";
      String selecionar = "id_menbresia_datos,nombre";
      String sql_query = "SELECT " + selecionar + " FROM " + nombre_tabla;
      
      PreparedStatement declaracion = conexion.prepareStatement(sql_query);
      ResultSet resultado = declaracion.executeQuery();
      
      while(resultado.next()){
        
        valores = new modelo_membresias();
        valores.setId(resultado.getInt("id_menbresia_datos"));
        valores.setNombre(resultado.getString("nombre"));
        datos.add(valores);
        
      }
      
      
    } catch (Exception e) {
      
      
    }
   
    return datos;
}
  

  @Override
  public boolean ingresar(Map<String, Object> values) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean borrar(int id_fila) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean actualizar(int id_fila, Map<String, Object> values) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public DefaultTableModel selecionarLista() throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Object> Seleccion(int id_fila) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  
  
  
}

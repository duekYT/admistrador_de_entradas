/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbaseDatos;

import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public abstract class conexionBaseDatos {
  
  protected String url = "";
  protected String usuario = "";
  protected String contasena = "";
  protected Connection conexion = null;
  
  /*preparamos los metodos de captura de la conexion, extrallendo de configuracion xml */
  public Connection getConexion() {
    return conexion;
  }

  public void setConexion(Connection conexion) {
    this.conexion = conexion;
  }
  
  /*
  creamos la conexion a la base de datos pasando los datos recoletados de la clase de configuracion  
  */
  
  public void baseDatosConexion
  //pasamos los datos de la conexion
  (
    String host,
    String db_name,
    String user,
    String password
  )throws SQLException, ClassNotFoundException 
  {
    //creamos la conexion
   Class.forName("com.mysql.jdbc.Driver");
   this.usuario = user;
   this.contasena = password;
   this.url = String.format("jdbc:mysql://%s/%s", host, db_name + "?useSSL=false");
   this.conexion = iniciarConexion();
  }
  
  //iniciamos la conexion a la base de datos 
  public Connection iniciarConexion() throws SQLException{
    return DriverManager.getConnection(url, usuario, contasena);
  }
  
  //se crean los metodos abtractos para las clases hijas.
  public abstract boolean ingresar( Map<String, Object> values )throws SQLException;
  public abstract boolean borrar( int id_fila ) throws SQLException;
  public abstract boolean actualizar( int id_fila, Map<String,Object> values ) throws SQLException;
  public abstract DefaultTableModel selecionarLista() throws SQLException;
  public abstract List<Object> Seleccion( int id_fila) throws SQLException;
  
  //se cierra la conexion a la base de datos
  public void cerrarConexion() throws SQLException 
  {
    this.conexion.close();
  }
  
}


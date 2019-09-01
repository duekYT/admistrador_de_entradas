/*
 * configuracion y lectura del xml v 1.0
 * creado por luis Alberto Coba Ventura 
*/
package utilidades;

import conexionBaseDatos.conexionBaseDatos;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.Utilidades;

public class configuracionXml extends JFrame {
  
  //mandamos a traer e iniciamos la clase de utilidades donde pasa el archivo de xml
  Utilidades utilidades = new Utilidades();
  // mandamos a traer e inicamo la clase conexionBaseDatos del paquete de conexion
  conexionBaseDatos conexion = new conexionBaseDatos() {
    
    //los metodos son nesesario dentro de la conexion ya que los hereda de la clase padre en conexionBaseDatos
    @Override
    public boolean ingresar(Map<String, Object> values) throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean borrar(int id_fila) throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizar(int id_fila, Map<String, Object> values) throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DefaultTableModel selecionarLista() throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> Seleccion(int id_fila) throws SQLException {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  };
  
  public configuracionXml()
  {
    
    String path = System.getProperty( "user.dir" );
    System.out.println( "JAVA corre desde: " + path );
    utilidades.setPath( path );
  
    boolean resultado_inicio_configuraciones = iniciarConfiguraciones();
      if( resultado_inicio_configuraciones )
      {
        System.out.println(
        "La conexión a la base de datos \"" + 
        utilidades.getTagValue( "DATABASE_NAME" ).toString() + 
        "\" fue exitosa" 
        );
      }
    
  }
   
  private boolean iniciarConfiguraciones() {
    
  //Leer Archivo de Configuraciones
  boolean resultado_leer = utilidades.leerXml();
  
    if (resultado_leer == false) {
      mostrarMensajeErrorUsuario("Favor de verificar el archivo de configuración e intente reconectar: \n"
        + utilidades.ARCHIVO_CONFIGURACION
      );
      return resultado_leer;
    } else {
      boolean resultado_bd = true;
      try {
        /*pamos los parametros a la conexion */
        conexion.baseDatosConexion(
          utilidades.getTagValue("DATABASE_HOST").toString(),
          utilidades.getTagValue("DATABASE_NAME").toString(),
          utilidades.getTagValue("DATABASE_USER").toString(),
          utilidades.getTagValue("DATABASE_PASS").toString()
        );
        
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        resultado_bd = false;
      }
      if (resultado_bd == false) {
        mostrarMensajeErrorUsuario("No se pudo realizar la conexión a la Base de datos: \n\n"
          + "Servidor: " + utilidades.getTagValue("DATABASE_HOST").toString() + "\n"
          + "Base de datos: " + utilidades.getTagValue("DATABASE_NAME").toString() + "\n"
          + "Usuario: " + utilidades.getTagValue("DATABASE_USER").toString() + "\n"
          + "Contraseña: " + utilidades.getTagValue("DATABASE_PASS").toString() + "\n"
        );
        return resultado_bd;
      }
      return true;
    }
  }
  
  public conexionBaseDatos getConexion() {
    return conexion;
  }

  public void setConexion(conexionBaseDatos conexion) {
    this.conexion = conexion;
  }
    
  //mensaje de alerta
  private void mostrarMensajeErrorUsuario( String mensaje )
  {
    mostrarMensajeUsuario( mensaje,	"Error", JOptionPane.ERROR_MESSAGE );
  }

  //metodo de salida de mansajes 
  private void mostrarMensajeUsuario(String mensaje, String titulo, int tipo_mensaje ) {
    JOptionPane.showMessageDialog( this, mensaje, titulo, tipo_mensaje );
    System.out.println( mensaje );
  }
  
  
}

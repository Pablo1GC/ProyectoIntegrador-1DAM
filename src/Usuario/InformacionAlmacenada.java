package Usuario;

/**
 * @author Brian Da Silva
 */

import java.sql.*;
import java.util.ArrayList;

public class InformacionAlmacenada {


    public static void setId_user(int id_user) {
        InformacionAlmacenada.id_user = id_user;
    }

    private static int   id_user;
    private String username;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;




    public InformacionAlmacenada(String username){
        this.username = username;


        actualizarDatosDeUsuario();

    }




    public String getUsername() {
        return username;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void actualizarDatosDeUsuario(){
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://bx6upwoiubkc4felso1w-mysql.services.clever-cloud.com:21400/bx6upwoiubkc4felso1w", "uflpbetwquqnmjoz",
                    "apjpBAssEuhauYy6mUP");
            String procedimiento = "{call Nombre_Apellidos_User(?)}";
            CallableStatement ctmt = conexion.prepareCall(procedimiento);
            ctmt.setInt(1, id_user);
            ctmt.execute();
            ResultSet rset = ctmt.getResultSet();
            rset.next();

            nombre = rset.getString("nombre");
            apellido1 = rset.getString("apellido1");
            apellido2 = rset.getString("apellido2");

            rset.close();
            ctmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}

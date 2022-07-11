package mvc;

import Usuario.InformacionAlmacenada;
import clubUnico.CajaProyectosClub;
import clubes.CartaClubsUniversidad;
import herramientasAdministrador.Usuario;
import login.Correo;
import login.CorreoCrearClub;
import login.VentanaLogin;
import proyectoUnico.PaginaProyectos;
import usuarioRegistrado.CartaProyectoAbierto;
import usuarioRegistrado.TarjetaResumenNoticias;
import utilidades.Paginas;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModeloImpl implements Modelo {
    //--------------------------------//
    // CLUB ÚNICO
    protected String tituloPaginaClub;
    private boolean admin = false;
    private Connection conexion;
    private int cod_registrado;
    private Vista vista;
    // LOGIN
    private String usr;
    private ResultadoLogin casosLogin;
    private int fallos;
    private VentanaLogin login;
    private InformacionAlmacenada informacionAlmacenada;
    // CARTAS DE CLUBS
    private ArrayList<CartaClubsUniversidad> cartasClubs;
    // --------------------------- //

    private int nombreClubDevuelto;
    private int nombreUsrDevuelto;

    private int nombreProyDevuelto;

    // --------------------------- //

    // ---------------------------------------//


    public boolean userIsAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }


    // --------------------------- //

    public void getSolicitudesPendientesProyecto() {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Solicitudes_Pendientes_Proyecto(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.execute();
            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                int cod_proyecto = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String descripcion = resultado.getString(3);
                String clubRealizador = resultado.getString(4);
                Paginas.SOLICITUDES_PENDIENTES_PROYECTOS.crearSoli(nombre, descripcion, clubRealizador, cod_proyecto, this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSolicitudesPendientesClubs() {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Solicitudes_Pendientes_Club(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.execute();
            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                int cod_Club = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String descripcion = resultado.getString(3);
                Paginas.SOLICITUDES_PENDIENTES_CLUBS.crearSoliClub(nombre, descripcion, cod_Club, this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUsuariosEnClub(int cod_club) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Usuarios_Pertenecen_Club(?)}");

            sentencia.setInt(1, cod_club);
            sentencia.executeQuery();

            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                String username = resultado.getString("username");
                String nombre = resultado.getString("nombre");
                String fecha_alta = resultado.getString("fecha_alta");
                boolean es_admin = resultado.getBoolean("es_admin");
                int cod_registrado = resultado.getInt("cod_registrado");

                usuarios.add(new Usuario(cod_registrado, username, nombre, fecha_alta, es_admin));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            // TODO Auto-generated catch block
        }

        Paginas.ADMINISTRAR_USUARIOS.setUsuarios(usuarios);
    }

    public void getUsuariosEnProyecto(int cod_proyecto) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Usuarios_Pertenecen_Proyecto(?)}");
            sentencia.setInt(1, cod_proyecto);
            sentencia.executeQuery();

            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                String username = resultado.getString("username");
                String nombre = resultado.getString("nombre");
                String fecha_alta = resultado.getString("fecha_alta");
                boolean es_admin = resultado.getBoolean("es_admin");
                int cod_registrado = resultado.getInt("cod_registrado");

                usuarios.add(new Usuario(cod_registrado, username, nombre, fecha_alta, es_admin));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            // TODO Auto-generated catch block
        }

        Paginas.ADMINISTRAR_USUARIOS.setUsuarios(usuarios);
    }

    public HashMap<String, Integer> getClubsAdministrados() {
        HashMap<String, Integer> clubs = new HashMap<>();
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Todos_Clubs_Administrados(?)}");

            sentencia.setInt(1, cod_registrado);
            sentencia.executeQuery();

            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                clubs.put("CLUB: " + resultado.getString(1), resultado.getInt(2));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            // TODO Auto-generated catch block
        }
        return clubs;
    }

    public HashMap<String, Integer> getProyectosAdministrados() {
        HashMap<String, Integer> proyectos = new HashMap<>();
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Todos_Proyectos_Administrados(?)}");

            sentencia.setInt(1, cod_registrado);
            sentencia.executeQuery();

            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                proyectos.put("PROYECTO: " + resultado.getString(1), resultado.getInt(2));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            // TODO Auto-generated catch block
        }
        return proyectos;
    }


    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }


    /**Imprime la tabla de solicitudes de unión de un club*/

    public void imprimirTablaUnionClub() {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Solicitudes_Union_Club(?)}");
            operacion.setInt(1, cod_registrado);
            operacion.execute();
            ResultSet resultado = operacion.getResultSet();
            Paginas.ADMINISTRAR_SOLICITUDES.reiniciar();
            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String estudios = resultado.getString("estudios");
                String club = resultado.getString("club");
                int cod_club = resultado.getInt("cod_club");
                int cod_user = resultado.getInt("cod_registrado");
                String username = resultado.getString("r.username");
                Paginas.ADMINISTRAR_SOLICITUDES.pintarTabla(username, nombre, estudios, club, "21/05/2022", cod_club, cod_user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aceptarUsuariosClub(int[] seleccionadosUsr, int[] seleccionadosClub) {
        for (int i = 0; i < seleccionadosUsr.length; i++) {
            nombreClubDevuelto = seleccionadosClub[i];
            nombreUsrDevuelto = seleccionadosUsr[i];
            try {
                CallableStatement operacion2 = conexion.prepareCall("{call Aceptar_Miembro_Club(?,?)}");
                operacion2.setInt(1, nombreUsrDevuelto);
                operacion2.setInt(2, nombreClubDevuelto);
                operacion2.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void ascenderUsuarioClub(int cod_registrado, int cod_club) {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Ascender_A_Admin_Club(?, ?)}");
            operacion.setInt(1, cod_registrado);
            operacion.setInt(2, cod_club);
            operacion.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ascenderUsuarioProyecto(int cod_registrado, int cod_proyecto) {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Ascender_A_Admin_Proyecto(?, ?)}");
            operacion.setInt(1, cod_registrado);
            operacion.setInt(2, cod_proyecto);
            operacion.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void solicitarUnirseClub(int idClub) {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Solicitar_Union_Club(?,?)}");
            operacion.setInt(1, cod_registrado);
            operacion.setInt(2, idClub);
            operacion.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paginas.SOLICITUDES_PENDIENTES_CLUBS.reiniciarSolicitudes();
        getSolicitudesPendientesClubs();
    }

    @Override
    public void solicitarUnirseProyecto(int idProyecto) {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Solicitar_Union_Proyecto(?,?)}");
            operacion.setInt(1, cod_registrado);
            operacion.setInt(2, idProyecto);
            operacion.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paginas.SOLICITUDES_PENDIENTES_PROYECTOS.reiniciarSolicitudes();
        getSolicitudesPendientesProyecto();
    }


    public void rechazarUsuariosClub(int[] seleccionadosUsr, int[] seleccionadosClub) {
        for (int i = 0; i < seleccionadosUsr.length; i++) {
            nombreClubDevuelto = seleccionadosClub[i];
            nombreUsrDevuelto = seleccionadosUsr[i];
            try {
                CallableStatement operacion2 = conexion.prepareCall("{call Eliminar_User_Club(?,?)}");
                operacion2.setInt(1, nombreUsrDevuelto);
                operacion2.setInt(2, nombreClubDevuelto);
                operacion2.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void imprimirTablaUnionProyecto() {
        try {
            CallableStatement operacion = conexion.prepareCall("{call Solicitudes_Union_Proyecto(?)}");
            operacion.setInt(1, cod_registrado);
            operacion.execute();
            ResultSet resultado = operacion.getResultSet();
            Paginas.ADMINISTRAR_SOLICITUDES.reiniciar();
            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String estudios = resultado.getString("estudios");
                String proyecto = resultado.getString("proyecto");
                int cod_proyecto = resultado.getInt("cod_proyecto");
                int cod_user = resultado.getInt("cod_registrado");
                String username = resultado.getString("r.username");
                Paginas.ADMINISTRAR_SOLICITUDES.pintarTabla(username, nombre, estudios, proyecto, "21/05/2022", cod_proyecto, cod_user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void aceptarUsuariosProyecto(int[] seleccionadosUsr, int[] seleccionadosProyecto) {
        for (int i = 0; i < seleccionadosUsr.length; i++) {
            nombreProyDevuelto = seleccionadosProyecto[i];
            nombreUsrDevuelto = seleccionadosUsr[i];
            try {
                CallableStatement operacion2 = conexion.prepareCall("{call Aceptar_Miembro_Proyecto(?,?)}");
                operacion2.setInt(1, nombreUsrDevuelto);
                operacion2.setInt(2, nombreProyDevuelto);
                operacion2.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rechazarUsuariosProyecto(int[] seleccionadosUsr, int[] seleccionadosProyecto) {
        for (int i = 0; i < seleccionadosUsr.length; i++) {
            nombreProyDevuelto = seleccionadosProyecto[i];
            nombreUsrDevuelto = seleccionadosUsr[i];
            try {
                CallableStatement operacion2 = conexion.prepareCall("{call Eliminar_User_Proyecto(?,?)}");
                operacion2.setInt(1, nombreUsrDevuelto);
                operacion2.setInt(2, nombreProyDevuelto);
                operacion2.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void obtenerDatosClub(int idClub) {
        Paginas.PAG_CLUB_ADMINISTRADOR.reiniciarNoticias();
        Paginas.PAG_CLUB_ADMINISTRADOR.reiniciarProyectos();
        Paginas.PAG_CLUB_REGISTRADO_SOLICITA.reiniciarProyectos();

        //  Establece el nombre, la descripción y todos los datos genéricos del proyecto
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Datos_Club(?)}");

            sentencia.setInt(1, idClub);
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            while (resultado.next()) {
                tituloPaginaClub = resultado.getString("nombre");

                //  SOLICITUDES CLUB
                String descripcionPaginaClub = resultado.getString("descripcion");
                String localizacionClub = resultado.getString("localizacion");
                String contactoClub = resultado.getString("contacto");
                InputStream imagen = resultado.getBinaryStream("banner");
                int nIntegrantes = resultado.getInt("num_integrantes");
                Paginas.PAG_CLUB_REGISTRADO_SOLICITA.establecerInfoClub(tituloPaginaClub, descripcionPaginaClub, contactoClub, localizacionClub, imagen, nIntegrantes);
                Paginas.PAG_CLUB_ADMINISTRADOR.establecerInfoClub(tituloPaginaClub, descripcionPaginaClub, contactoClub, localizacionClub, imagen, nIntegrantes);
            }
            //  Imprime todas las noticias del club
            CallableStatement sentencia2 = conexion.prepareCall("{call Noticias_Club(?)}");

            sentencia2.setInt(1, idClub);
            sentencia2.execute();

            ResultSet resultSet2 = sentencia2.getResultSet();

            while (resultSet2.next()) {
                String titulo = resultSet2.getString("titulo");
                String contenido = resultSet2.getString("contenido");
                String fecha = resultSet2.getString("fecha");
                String autor = resultSet2.getString("autor");
                Paginas.PAG_CLUB_ADMINISTRADOR.crearNoticia(titulo, contenido, autor, fecha);
            }

            //  Imprime todos los proyectos de un club
            CallableStatement sentencia3 = conexion.prepareCall("{call Proyectos_Club(?)}");

            sentencia3.setInt(1, idClub);
            sentencia3.execute();
            ResultSet resultSet3 = sentencia3.getResultSet();

            while (resultSet3.next()) {
                String nombre = resultSet3.getString("nombre");
                int codProyecto = resultSet3.getInt("cod_proyecto");
                String contenido = resultSet3.getString("descripcion");
                Paginas.PAG_CLUB_ADMINISTRADOR.crearProyecto(nombre, contenido, codProyecto);
            }
            /** Imprime solo los proyectos públicos del club*/

            CallableStatement sentencia4 = conexion.prepareCall("{call Proyectos_Club(?)}");

            sentencia4.setInt(1, idClub);
            sentencia4.execute();

            ResultSet resultSet4 = sentencia4.getResultSet();

            while (resultSet4.next()) {
                String titulo = resultSet4.getString("Nombre");
                String contenido = resultSet4.getString("Descripcion");
                int idProyecto = resultSet4.getInt("cod_proyecto");
                int esPrivado = resultSet4.getInt("privado");
                if (esPrivado == 0) {
                    Paginas.PAG_CLUB_REGISTRADO_SOLICITA.agregarProyecto(titulo, contenido, idProyecto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
            // TODO Auto-generated catch block
        }

    }

    public void obtenerDatosProyecto(int idProyecto) {
        Paginas.PAGINA_PROYECTO_MIEMBRO.reiniciarNoticias();
        /**Establece el nombre, la descripción y todos los datos genéricos del proyecto*/

        try {
            CallableStatement sentencia = conexion.prepareCall("{call Datos_Proyecto(?)}");
            sentencia.setInt(1, idProyecto);
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            String clubPadre = "";
            while (resultado.next()) {
                // Cartas de proyectos//
                String tituloPaginaProyecto = resultado.getString("nombre");
                String descripcionPaginaProyecto = resultado.getString("descripcion");
                String contacto = resultado.getString("contacto");
                clubPadre = resultado.getString("Club");
                int nMiembros = resultado.getInt("num_integrantes");
                Paginas.PAGINA_PROYECTO_MIEMBRO.establecerInfoProyecto(tituloPaginaProyecto, clubPadre, descripcionPaginaProyecto, contacto, nMiembros);
                Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.establecerInfoProyectoAbierto(tituloPaginaProyecto, descripcionPaginaProyecto, clubPadre, contacto, nMiembros);
                PaginaProyectos.setIdProyecto(idProyecto);
            }
            /** Imprime todas las noticias del proyecto*/
            CallableStatement sentencia2 = conexion.prepareCall("{call Noticias_Proyecto(?)}");

            sentencia2.setInt(1, idProyecto);
            sentencia2.execute();

            ResultSet resultSet2 = sentencia2.getResultSet();

            while (resultSet2.next()) {
                String titulo = resultSet2.getString("titulo");
                String contenido = resultSet2.getString("contenido");
                String fecha = resultSet2.getString("fecha");
                String autor = resultSet2.getString("autor");
                Paginas.PAGINA_PROYECTO_MIEMBRO.crearNoticia(titulo, clubPadre, autor, fecha, contenido);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // LOGIN
    public void login(String usr, String passwd, boolean permanecerLogueado) {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://bx6upwoiubkc4felso1w-mysql.services.clever-cloud.com:21400/bx6upwoiubkc4felso1w", "uflpbetwquqnmjoz",
                    "apjpBAssEuhauYy6mUP");
        } catch (Exception e) {
            e.printStackTrace();
        }
        obtenerCartasSQL();

        try {

            CallableStatement sentencia = conexion.prepareCall("{call Login_User(?, ?)}");

            sentencia.setString(1, usr);
            sentencia.setString(2, passwd);


            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();

            if (resultado.next()) {
                this.usr = resultado.getString("username");
                String pass = resultado.getString("contrasena");
                this.cod_registrado = resultado.getInt("cod_registrado");
                InformacionAlmacenada.setId_user(cod_registrado);


                if (usr.equals(this.usr) && passwd.equals(pass)) {
                    casosLogin = ResultadoLogin.CORRECTO;
                    fallos = 0;
                    this.cod_registrado = resultado.getInt("cod_registrado");
                    sentencia = conexion.prepareCall("{call Is_Admin_Club(?)}");
                    sentencia.setInt(1, cod_registrado);
                    sentencia.execute();
                    resultado = sentencia.getResultSet();

                    //  Si es admin de club
                    if (resultado.next()) {
                        admin = true;

                    } else {


                        sentencia = conexion.prepareCall("{call Is_Admin_Proyecto(?)}");
                        sentencia.setInt(1, cod_registrado);
                        sentencia.execute();

                        resultado = sentencia.getResultSet();

                        //  Si es admin de proyecto
                        if (resultado.next()) {
                            admin = true;
                        }
                    }


                    obtenerDatosSQL();

                }
            } else {
                fallos++;
                if (fallos == 3) {
                    casosLogin = ResultadoLogin.CERRAR;
                } else {

                    casosLogin = ResultadoLogin.INCORRECTO;
                }
            }
            login.actualizar();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ResultadoLogin getResultado() {
        return this.casosLogin;
    }

    public void setLogin(VentanaLogin login) {
        this.vista = login;
        this.login = login;
    }
    // -----------------------------------------------------------

    // CARTAS DE CLUBS
    public ArrayList<CartaClubsUniversidad> getCartasClubs() {
        return cartasClubs;
    }


    @Override
    public void actualizarDatosUsuario(String email, String estudios) {
        try {
            // Obtener Nombre y codigo de Club
            CallableStatement sentencia = conexion.prepareCall("{call Cambiar_Email_Estudios(?,?,?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.setString(2, email);
            sentencia.setString(3, estudios);
            sentencia.executeUpdate();
            Paginas.PERFIL_DE_USUARIO.getGuardar().setVisible(true);
        } catch (SQLIntegrityConstraintViolationException e) {
            /*
             * Captamos el error de entrada duplicada y avisariamos al usuario, en este caso
             * como la autonumeracion no está lista todavia puede dar error de primary key
             * lo que seria cosa nuestra, una vez arreglado eso solo daria error con los
             * campos de usuario, email y los que hayamos puesto UNIQUE
             *
             *
             * @autor franco
             */
            String mensaje = e.getMessage().replace("Duplicate entry '", "El dato '");
            for (int i = 11; i < mensaje.length(); i++) {
                if (mensaje.charAt(i) == ' ') {
                    mensaje = mensaje.substring(0, i);
                }
            }
            mensaje = mensaje + "' ingresado no es valido";
            Paginas.PERFIL_DE_USUARIO.datoExistente(mensaje);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // -----------------------------------------------------------


    @Override
    public String getNombreUsuario() {
        return informacionAlmacenada.getNombre();
    }
    // -----------------------------------------------------------

    // USUARIO LABEL BARRA LATERAL
    public String getUsername() {
        return usr;
    }

    @Override
    public String getApellido1() {
        return informacionAlmacenada.getApellido1();
    }

    @Override
    public String getApellido2() {
        return informacionAlmacenada.getApellido2();
    }
    // -----------------------------------------------------------


    // -------- Informacion de la base de datos -------------- //
    // CARTAS DE CLUBS
    public void obtenerCartasSQL() {
        cartasClubs = new ArrayList<>();

        try {
            CallableStatement procedimiento = conexion.prepareCall("{call Todos_Clubs()}");
            procedimiento.execute();
            ResultSet resultSet = procedimiento.getResultSet();

            while (resultSet.next()) {

                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                InputStream banner = resultSet.getBinaryStream("banner");
                int id_club = resultSet.getInt("cod_club");
                cartasClubs.add(new CartaClubsUniversidad(nombre, descripcion, banner, id_club));

            }


            resultSet.close();
            procedimiento.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CLUB ÚNICO

    @Override
    public void crearCuenta(String usr, String nombre, String ape1, String ape2, String email, String pass, java.sql.Date fecha) {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://bx6upwoiubkc4felso1w-mysql.services.clever-cloud.com:21400/bx6upwoiubkc4felso1w", "uflpbetwquqnmjoz",
                    "apjpBAssEuhauYy6mUP");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * Falta la autonumeración de COD_REGISTRADO, asi se puede eliminar de la
         * select, y falta conseguir de alguna manera se hizo el insert, o no para
         * avisarle al usuario que ya puede iniciar sesión. He pensado en algún procedimiento
         * de la base de datos que devuelva true o false, en caso de ser verdadero se lo
         * envía al login, en caso de ser falso se le pide que lo intente nuevamente.
         *
         * También faltaría arreglar el calendario que tenemos para la entrada de la
         * fecha de cumpleaños, porque da errores con el mes al elegir fecha.
         *
         * @author franco
         */

        try {
            CallableStatement sentencia = conexion.prepareCall("{call Registrar_User(?,?,?,?,?,?,?)}");
            sentencia.setString(1, usr);
            sentencia.setString(7, pass);
            sentencia.setString(2, nombre);
            sentencia.setString(3, ape1);
            sentencia.setDate(5, fecha);
            sentencia.setString(4, ape2);
            sentencia.setString(6, email);
            sentencia.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            /*
             * Captamos el error de entrada duplicada y avisaríamos al usuario, en este caso
             * como la autonumeración no está lista todavía puede dar error de primary key
             * lo que seria cosa nuestra, una vez arreglado eso solo daria error con los
             * campos de usuario, email y los que hayamos puesto UNIQUE
             *
             *
             * @autor franco
             */
            String mensaje = e.getMessage().replace("Duplicate entry '", "El dato '");
            for (int i = 11; i < mensaje.length(); i++) {
                if (mensaje.charAt(i) == ' ') {
                    mensaje = mensaje.substring(0, i);
                }
            }
            mensaje = mensaje + " ingresado no es valido";
            Paginas.CREAR_CUENTA.datoExistente(mensaje);
        } catch (SQLException e) {
            /*
             * error génerico en caso de cualquier problema
             */
            e.printStackTrace();
        }

    }

    private void obtenerDatosSQL() {
        getSolicitudesPendientesClubs();
        getSolicitudesPendientesProyecto();

        obtenerResumenMisClubs();
        obtenerResumenMisProyectos();

        Paginas.CLUBS_UNIVERSIDAD.establecerCartas();

        Paginas.ADMINISTRAR_USUARIOS.agregarClubsAdministrados(getClubsAdministrados());
        Paginas.ADMINISTRAR_USUARIOS.agregarProyectosAdministrados(getProyectosAdministrados());
        Paginas.NUEVO_POST.agregarClubsAdministrados(getClubsAdministrados());
        Paginas.NUEVO_POST.agregarProyectosAdministrados(getProyectosAdministrados());
        Paginas.ADMINISTRAR_USUARIOS.obtenerTablaPorPrimeraVez();

        establecerDatosUsuario();
        obtenerCartaProyectosAbiertos();

    }

    public void obtenerTipoDeUsuarioClub(int idClub) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Es_Miembro_Club(?,?)}");
            statement.setInt(1, cod_registrado);
            statement.setInt(2, idClub);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                String usr = resultSet.getString("usuario");
                TipoDeUsuario tipo = switch (usr){
                    case "M" -> TipoDeUsuario.MIEMBRO;
                    case "R" -> TipoDeUsuario.OBSERVADOR;
                    case "S" -> TipoDeUsuario.SOLICITA;
                    default ->  TipoDeUsuario.MIEMBRO;
                };
                CartaClubsUniversidad.setTipoDeUsuario(tipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void obtenerTipoDeUsuarioProyecto(int idProyecto) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Es_Miembro_Proyecto(?,?)}");
            statement.setInt(1, cod_registrado);
            statement.setInt(2, idProyecto);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                String usr = resultSet.getString("usuario");

                TipoDeUsuario tipo = switch (usr){
                    case "M" -> TipoDeUsuario.MIEMBRO;
                    case "R" -> TipoDeUsuario.OBSERVADOR;
                    case "S" -> TipoDeUsuario.SOLICITA;
                    default ->  TipoDeUsuario.MIEMBRO;
                };

                CajaProyectosClub.setTipoDeUsuario(tipo);
                CartaProyectoAbierto.setTipoDeUsuario(tipo);
                TarjetaResumenNoticias.setTipoDeUsuario(tipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void esAdminClub(int idClub) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Es_Admin_Club(?,?)}");
            statement.setInt(1, cod_registrado);
            statement.setInt(2, idClub);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                String usr = resultSet.getString("usuario");

                boolean esAdmin = usr.equals("A");

                CartaClubsUniversidad.setEsAdmin(esAdmin);
                TarjetaResumenNoticias.setEsAdmin(esAdmin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarUsuarioDeClub(int cod_registrado, int cod_club) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Eliminar_User_Club(?,?)}");
            statement.setInt(1, cod_registrado);
            statement.setInt(2, cod_club);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarUsuarioDeProyecto(int cod_registrado, int cod_proyecto) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Eliminar_User_Proyecto(?,?)}");
            statement.setInt(1, cod_registrado);
            statement.setInt(2, cod_proyecto);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void crearProyecto(String titulo, String lbl_descripcion, String email, boolean privado, int idClub) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Crear_Proyecto (?,?,?,?,?)}");
            statement.setString(1, titulo);
            statement.setString(2, lbl_descripcion);
            statement.setString(3, email);
            if (privado) {
                statement.setInt(4, 1);
            } else {
                statement.setInt(4, 0);
            }
            statement.setInt(5, idClub);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Manda un correo con una contraseña generada de forma aleatoria.
     * Te permitirá iniciar sesión para que puedas cambiar tu contraseña.
     *
     * @param email
     * @return true si se pudo enviar el correo, false si ocurrió un error.
     */
    @Override
    public boolean recuperarContrasena(String email) {
        try {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://bx6upwoiubkc4felso1w-mysql.services.clever-cloud.com:21400/bx6upwoiubkc4felso1w", "uflpbetwquqnmjoz",
                        "apjpBAssEuhauYy6mUP");
            } catch (Exception e) {
                e.printStackTrace();
            }
            CallableStatement statement = conexion.prepareCall("{call Cambiar_Contrasena_Random(?)}");
            statement.setString(1, email);
            statement.execute();
            CallableStatement sentencia = conexion.prepareCall("{call Solicitar_Contrasena(?)}");
            sentencia.setString(1, email);
            sentencia.execute();
            ResultSet ejemplo = sentencia.getResultSet();
            ejemplo.next();
            String contrasena = ejemplo.getString("contrasena");
            Correo emailContrasena = new Correo("clubsuem@gmail.com", email, "Cambio de contraseña en la plataforma ClubsUEM", "La nueva contraseña es: " + contrasena, "jrsynnvwlpwjtirx");
            emailContrasena.envioDeCorreos();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void escribirFicheroLoguear(String usr, String pass) {
        try{
            File test = new File(System.getProperty("user.dir") + "/src/PermanecerLogueado/Datos.txt");
            FileOutputStream ejem = new FileOutputStream(test);
            ejem.write((usr + "\n" + pass).getBytes());

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void borrarFicheroLoguear() {
        try {
            File ficheroLogin = new File(System.getProperty("user.dir") + "/src/PermanecerLogueado/Datos.txt");
            PrintWriter sobreescribir = new PrintWriter(ficheroLogin);
            Scanner in = new Scanner(ficheroLogin);
            while (in.hasNextLine()) {
                sobreescribir.flush();
            }
            in.close();
            sobreescribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void comprobarPermanecerLogueado() {
        File test = new File(System.getProperty("user.dir") + "/src/PermanecerLogueado/Datos.txt");
        if (test.exists()) {
            try {
                Scanner in = new Scanner(test);
                String usr = in.nextLine();
                String pass = in.nextLine();
                login(usr, pass, true);
            } catch (Exception e) {
                this.login.setVisible(true);
            }
        } else {
            this.login.setVisible(true);

        }
    }

    @Override
    public void enviarEmailCrearClub(String email, String nombre, String descripcion, Object localizacion, String urLimagen) {
        try {
            CorreoCrearClub emailContrasena = new CorreoCrearClub("clubsuem@gmail.com", "clubsuem@gmail.com", "Solicitud para creacion de Club UEM", "Responder al email: " + email + "\nNombre club: " + nombre + "\nDescripcion: " + descripcion + "\nLocalizacion: " + localizacion ,urLimagen, "jrsynnvwlpwjtirx");
            emailContrasena.envioDeCorreos();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean enviarConsulta(String email, String consulta) {
        try {
            Correo emailContrasena = new Correo("clubsuem@gmail.com", "clubsuem@gmail.com", "Consulta en la plataforma ClubsUEM", "Responder al email: " + email + "\nConsulta: " + consulta, "jrsynnvwlpwjtirx");
            emailContrasena.envioDeCorreos();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void crearNuevoPostClub(int cod_club, String titulo, String contenido) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Nuevo_Post_Club(?, ?, ? , ?)}");
            statement.setInt(3, cod_club);
            statement.setInt(4, cod_registrado);

            statement.setString(1, titulo);
            statement.setString(2, contenido);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void crearNuevoPostProyecto(int cod_proyecto, String titulo, String contenido) {
        try {
            CallableStatement statement = conexion.prepareCall("{call Nuevo_Post_Proyecto(?, ?, ? , ?)}");
            statement.setInt(3, cod_proyecto);
            statement.setInt(4, cod_registrado);
            statement.setString(2, contenido);
            statement.setString(1, titulo);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void obtenerInfoPerfilUsuario(String username) {
        this.informacionAlmacenada = new InformacionAlmacenada(username);
    }

    @Override
    public void obtenerCartaProyectosAbiertos() {
        try {
            CallableStatement procedimiento = conexion.prepareCall("{call Todos_Proyectos_Abiertos()}");
            procedimiento.execute();
            ResultSet resultSet = procedimiento.getResultSet();

            while (resultSet.next()) {

                int cod_proyecto = resultSet.getInt("cod_proyecto");
                String nombre = resultSet.getString("Nombre");
                String descripcion = resultSet.getString("Descripcion");
                String autor = resultSet.getString("autor");
                Paginas.PROYECTOS_ABIERTOS.agregarCarta(new CartaProyectoAbierto(cod_proyecto, nombre, autor, descripcion));
            }


            resultSet.close();
            procedimiento.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Paginas.PAG_PROYECTO_REGISTRADO_SOLICITA.establecerInfoProyectoAbierto(nombre,descripcion);

    @Override
    public void establecerDatosUsuario() {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Perfil_User(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.executeUpdate();
            ResultSet resultado = sentencia.getResultSet();
            if (resultado.next()) {
                String username = resultado.getString("username");
                String nombre = resultado.getString("nombre");
                String apellido1 = resultado.getString("apellido1");
                String apellido2 = resultado.getString("apellido2");
                String email = resultado.getString("email");
                String estudios = resultado.getString("estudios");
                InputStream foto = resultado.getBinaryStream("foto");

                Paginas.PERFIL_DE_USUARIO.establecerDatos(username, nombre + " " + apellido1 + " " + apellido2, email, foto, estudios);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean comprobarContrasena(String contrasena) {
        boolean esCorrecta = false;

        try {
            CallableStatement sentencia = conexion.prepareCall("{call Login_User(?,?)}");
            sentencia.setString(1, informacionAlmacenada.getUsername());
            sentencia.setString(2, contrasena);
            sentencia.executeUpdate();
            ResultSet resultado = sentencia.getResultSet();
            esCorrecta = resultado.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return esCorrecta;
    }


    @Override
    public void actualizarContrasena(String nuevaContrasena) {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Cambiar_contrasena(?,?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.setString(2, nuevaContrasena);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void eliminarCuenta() {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Eliminar_User(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.execute();
            int ej = sentencia.getUpdateCount();
            if (ej > 0) {
                vista.getControlador().logout();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abandonarClub(int cod_club) {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Eliminar_User_Club(?,?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.setInt(2, cod_club);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abandonarProyecto(int cod_proyecto) {
        try {
            CallableStatement sentencia = conexion.prepareCall("{call Eliminar_User_Proyecto(?,?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.setInt(2, cod_proyecto);
            sentencia.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerResumenMisClubs() {
        try {

            // Obtener Nombre y código de Club
            CallableStatement sentencia = conexion.prepareCall("{call Mis_Clubs(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.execute();

            ResultSet resultSet = sentencia.getResultSet();


            while (resultSet.next()) {

                int id_club = resultSet.getInt("cod_club");
                String nombre = resultSet.getString("nombre");

                // Obtener noticias
                CallableStatement sentencia2 = conexion.prepareCall("{call Mis_Clubs_Noticias(?,?)}");


                sentencia2.setInt(1, id_club);
                sentencia2.setInt(2, cod_registrado);
                sentencia2.execute();

                ResultSet resultSet2 = sentencia2.getResultSet();

                String titulo = null, contenido = null, fecha = null, autor = null, titulo2 = null, contenido2 = null, fecha2 = null, autor2 = null;

                if (resultSet2.next()) {

                    titulo = resultSet2.getString("titulo");
                    contenido = resultSet2.getString("contenido");
                    fecha = resultSet2.getString("fecha");
                    autor = resultSet2.getString("autor");


                    if (resultSet2.next()) {

                        titulo2 = resultSet2.getString("titulo");
                        contenido2 = resultSet2.getString("contenido");
                        fecha2 = resultSet2.getString("fecha");
                        autor2 = resultSet2.getString("autor");


                    }


                }

                Paginas.MIS_CLUBS.crearClub(nombre, titulo, contenido, fecha, autor, titulo2, contenido2, fecha2, autor2, id_club);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void obtenerResumenMisProyectos() {
        try {

            // Obtener Nombre y codigo de Club
            CallableStatement sentencia = conexion.prepareCall("{call Mis_Proyectos(?)}");
            sentencia.setInt(1, cod_registrado);
            sentencia.execute();

            ResultSet resultSet = sentencia.getResultSet();

            String titulo = null, contenido = null, fecha = null, autor = null, titulo2 = null, contenido2 = null, fecha2 = null, autor2 = null;


            while (resultSet.next()) {

                int id_proyecto = resultSet.getInt("cod_proyecto");
                String nombre = resultSet.getString("nombre");
                String club_propietario = resultSet.getString("Club_Propietario");

                // Obtener noticias

                CallableStatement sentencia2 = conexion.prepareCall("{call Mis_Proyectos_Noticias(?,?)}");

                sentencia2.setInt(1, id_proyecto);
                sentencia2.setInt(2, cod_registrado);
                sentencia2.execute();

                ResultSet resultSet2 = sentencia2.getResultSet();


                if (resultSet2.next()) {


                    titulo = resultSet2.getString("titulo");
                    contenido = resultSet2.getString("contenido");
                    fecha = resultSet2.getString("fecha");
                    autor = resultSet2.getString("autor");


                    if (resultSet2.next()) {

                        titulo2 = resultSet2.getString("titulo");
                        contenido2 = resultSet2.getString("contenido");
                        fecha2 = resultSet2.getString("fecha");
                        autor2 = resultSet2.getString("autor");


                    }


                }
                Paginas.MIS_PROYECTOS.crearProyecto(id_proyecto, nombre, titulo, contenido, fecha, autor, titulo2, contenido2, fecha2, autor2, club_propietario);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void cerrarConexion() {
        if (conexion != null){
            try {
                this.conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

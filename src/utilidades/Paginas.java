package utilidades;

import clubUnico.PaginaClubClubDeAdministrador;
import clubUnico.PaginaClubRegistradoSolicita;
import clubes.ClubsUniversidad;
import faqs.Faqs;
import formularios.FormularioClub;
import formularios.FormularioProyecto;
import formularios.NuevoPost;
import herramientasAdministrador.AdministrarSolicitudes;
import herramientasAdministrador.AdministrarUsuarios;
import login.*;
import proyectoUnico.PaginaProyectoMiembro;
import proyectoUnico.PaginaProyectoRegistradoSolicita;
import usuarioRegistrado.MisClubs;
import usuarioRegistrado.MisProyectos;
import usuarioRegistrado.PerfilUsuario;
import usuarioRegistrado.ProyectosAbiertos;
import usuarioRegistrado.SolicitudesPendientesClubs;
import usuarioRegistrado.SolicitudesPendientesProyectos;

public class Paginas {
	
	// Navegación Login

    public static  Inicia_Sesion 	   INICIAR_SESION                = new Inicia_Sesion();
	public static  FaqSinRegistro 	   FAQS_SIN_LOGUEADO		     = new FaqSinRegistro();
	public static  RecuperarContrasena RECUPERAR_CONTRASENA          = new RecuperarContrasena();
	public static  CrearCuenta		   CREAR_CUENTA                  = new CrearCuenta();
	public static  Contenido           CONTENIDO_LOGIN			     = new Contenido();
	
	// Navegación Principal

    public static  PerfilUsuario                PERFIL_DE_USUARIO 			    = new PerfilUsuario();
    public static  MisClubs                     MIS_CLUBS 					    = new MisClubs();
    public static  SolicitudesPendientesClubs   SOLICITUDES_PENDIENTES_CLUBS	= new SolicitudesPendientesClubs();
	public static  ClubsUniversidad             CLUBS_UNIVERSIDAD				= new ClubsUniversidad();
    public static  ProyectosAbiertos            PROYECTOS_ABIERTOS			    = new ProyectosAbiertos();
    public static  Faqs                         FAQS_LOGEADO					= new Faqs();
    public static  NuevoPost                    NUEVO_POST					    = new NuevoPost();
    public static  AdministrarSolicitudes       ADMINISTRAR_SOLICITUDES 		= new AdministrarSolicitudes();
    public static  AdministrarUsuarios          ADMINISTRAR_USUARIOS			= new AdministrarUsuarios();
    
    // Navegación Secundaria
    
    public static  MisProyectos                     MIS_PROYECTOS						= new MisProyectos();
    public static  PaginaClubRegistradoSolicita     PAG_CLUB_REGISTRADO_SOLICITA		= new PaginaClubRegistradoSolicita();
    public static  PaginaClubClubDeAdministrador    PAG_CLUB_ADMINISTRADOR			    = new PaginaClubClubDeAdministrador();
    public static  FormularioClub                   FORMULARIO_CLUB					    = new FormularioClub();
    public static  FormularioProyecto               FORMULARIO_PROYECTO				    = new FormularioProyecto();
    public static  PaginaProyectoMiembro            PAGINA_PROYECTO_MIEMBRO			    = new PaginaProyectoMiembro();
    public static  PaginaProyectoRegistradoSolicita PAG_PROYECTO_REGISTRADO_SOLICITA	= new PaginaProyectoRegistradoSolicita();
    public static  SolicitudesPendientesProyectos   SOLICITUDES_PENDIENTES_PROYECTOS	= new SolicitudesPendientesProyectos();


    public static void restablecerPaginas(){


        //Navegación Principal
        PERFIL_DE_USUARIO 			    = new PerfilUsuario();
        MIS_CLUBS 					    = new MisClubs();
        SOLICITUDES_PENDIENTES_CLUBS	= new SolicitudesPendientesClubs();
        CLUBS_UNIVERSIDAD				= new ClubsUniversidad();
        PROYECTOS_ABIERTOS			    = new ProyectosAbiertos();
        FAQS_LOGEADO					= new Faqs();
        NUEVO_POST					    = new NuevoPost();
        ADMINISTRAR_SOLICITUDES 		= new AdministrarSolicitudes();
        ADMINISTRAR_USUARIOS			= new AdministrarUsuarios();

        // Navegación Secundaria
        MIS_PROYECTOS						= new MisProyectos();
        PAG_CLUB_REGISTRADO_SOLICITA		= new PaginaClubRegistradoSolicita();
        PAG_CLUB_ADMINISTRADOR			    = new PaginaClubClubDeAdministrador();
        FORMULARIO_CLUB					    = new FormularioClub();
        FORMULARIO_PROYECTO				    = new FormularioProyecto();
        PAGINA_PROYECTO_MIEMBRO			    = new PaginaProyectoMiembro();
        PAG_PROYECTO_REGISTRADO_SOLICITA	= new PaginaProyectoRegistradoSolicita();
        SOLICITUDES_PENDIENTES_PROYECTOS	= new SolicitudesPendientesProyectos();
    }
    
	

}

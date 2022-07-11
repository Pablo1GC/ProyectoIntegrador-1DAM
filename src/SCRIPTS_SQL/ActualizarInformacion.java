package SCRIPTS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActualizarInformacion {

    public static void main(String[] args) {
        subirImagen(1, "/img_Clubs/club_formula.png");
        subirImagen(2, "/img_Clubs/club_informatica.jpg");
        subirImagen(3, "/img_Clubs/club_irl.jpg");
        subirImagen(4, "/img_Clubs/club_game_studio.png");
        subirImagen(5, "/img_Clubs/club_kinespana.jpg");
        subirImagen(6, "/img_Clubs/club_robotics.png");
        subirImagen(7, "/img_Clubs/club_criptoclub.jpg");
        subirImagen(8, "/img_Clubs/club_ue-e-sports-group.jpg");
        subirImagen(9, "/img_Clubs/clun_airdiv.jpg");
        subirImagen(10, "/img_Clubs/club_running_club.png");
        subirImagen(11, "/img_Clubs/club_workout.jpg");
        subirImagen(12, "/img_Clubs/club_chess-club.jpg");
        subirImagen(13, "/img_Clubs/club_asociacion-odontologica.jpg");
        subirImagen(14, "/img_Clubs/club_aemuem.jpg");
        subirImagen(15, "/img_Clubs/club_uepsa.jpg");
        subirImagen(16, "/img_Clubs/club_dental_life.png");
        subirImagen(17, "/img_Clubs/club_phyxs.jpg");
        subirImagen(18, "/img_Clubs/club_cludeciencias.jpg");
        subirImagen(19, "/img_Clubs/club_ue-mun.jpg");
        subirImagen(20, "/img_Clubs/club_pride.jpg");
        subirImagen(21, "/img_Clubs/club_club-rol-uem.jpg");
        subirImagen(22, "/img_Clubs/club_club-teatro-la-tarantella.jpg");
        subirImagen(23, "/img_Clubs/club_club-fotografia.jpg");
        subirImagen(24, "/img_Clubs/club_cine.jpg");
        subirImagen(25, "/img_Clubs/club_psyclub.jpg");
    }

    public static boolean subirImagen(int idClub, String ruta) {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://bx6upwoiubkc4felso1w-mysql.services.clever-cloud.com:21400/bx6upwoiubkc4felso1w", "uflpbetwquqnmjoz",
                    "apjpBAssEuhauYy6mUP");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String insert = "{call Subir_Imagen(?, ?)}";

        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);


            ps = conexion.prepareStatement(insert);
            ps.setInt(1, idClub);
            ps.setBinaryStream(2, ActualizarInformacion.class.getResourceAsStream(ruta));
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ActualizarInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(ActualizarInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}

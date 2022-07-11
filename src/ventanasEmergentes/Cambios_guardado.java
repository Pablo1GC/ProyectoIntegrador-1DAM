/**
 * @author: SamuelOrtega
 */
package ventanasEmergentes;

import utilidades.Boton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cambios_guardado extends La_Ventana_Emergente_Abstract {
    public JLabel getLblNewLabel() {
        return lblNewLabel;
    }

    private JLabel lblNewLabel;
    private Boton cerrar;

    public JLabel getLblNewLabel_1() {
        return lblNewLabel_1;
    }

    private  JLabel lblNewLabel_1;


    public Cambios_guardado() {
        cerrar = new Boton("Cerrar", 50, 59, 133, 28);
        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cerrar.setLocation(182, 202);
        getContentPane().add(cerrar);

        lblNewLabel = new JLabel("Cambios guardados correctamente\r\n");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
        lblNewLabel.setBounds(10, 145, 486, 28);
        getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Cambios_guardado.class.getResource("/img/muneco.png")));
        lblNewLabel_1.setBounds(185, 11, 120, 137);
        getContentPane().add(lblNewLabel_1);
    }
}
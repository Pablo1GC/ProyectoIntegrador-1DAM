package ventanasEmergentes;

import java.awt.*;

import javax.swing.*;

public class La_Ventana_Emergente_Abstract extends JDialog  {
    private final JPanel jp_ContentPane = new JPanel();

    public La_Ventana_Emergente_Abstract() {
        this.tamanoVentana();
    }

    public void tamanoVentana() {
        setResizable(false);
        setSize(500, 300);

        /**Centra la ventana emergente en la pantalla*/

        JFrame jf = new JFrame();
        jf.setBounds(0, 0, 1190, 720);
        setLocationRelativeTo(jf);

        jp_ContentPane.setBackground(Color.WHITE);
        setContentPane(jp_ContentPane);
        jp_ContentPane.setLayout(null);

        /**Bloquea todo hasta que se cierre el PopUp*/
        setModal(true);
    }

}

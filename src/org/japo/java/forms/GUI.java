/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.japo.java.events.KEM;
import org.japo.java.events.MEM;
import org.japo.java.events.MMEM;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL = "look_and_feel";
    public static final String PRP_FAVICON = "favicon";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL = UtilesSwing.LNF_NIMBUS;
    public static final String DEF_FAVICON = "img/favicon.png";

    private int xIni;
    private int yIni;

    // Referencias
    private Properties prp;

    // Constructor
    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción - GUI
    private void initComponents() {

        JLabel lblRotulo = new JLabel("Arrástrame (ESC - Salir)");
        lblRotulo.setForeground(Color.white);
        lblRotulo.setFont(new Font("Cambria", Font.PLAIN, 32));

        // Panel Principal
        JPanel pnlPpal = new JPanel();

        // Ventana Principal
        setTitle("Swing Manual #02");
        setContentPane(pnlPpal);
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        addKeyListener(new KEM(this));
        addMouseMotionListener(new MMEM(this));
        addMouseListener(new MEM(this));

        pnlPpal.setBackground(Color.MAGENTA);
        pnlPpal.setLayout(new GridBagLayout());
        pnlPpal.add(lblRotulo);

    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnF(prp.getProperty(PRP_LOOK_AND_FEEL, DEF_LOOK_AND_FEEL));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_FAVICON, DEF_FAVICON));
    }

    public void gestionarTeclas(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            UtilesSwing.terminarPrograma(this);
        }
    }

    public void iniciarArrastre(MouseEvent e) {
        xIni = e.getXOnScreen();
        yIni = e.getYOnScreen();
    }

    public void gestionarArrastre(MouseEvent e) {
        int xFin = e.getXOnScreen();
        int xOff = xFin - xIni;
        xIni = xFin;

        int yFin = e.getYOnScreen();
        int yOff = yFin - yIni;
        yIni = yFin;

        int xWin = getLocation().x;
        int yWin = getLocation().y;

        setLocation(xWin + xOff, yWin + yOff);
    }
}

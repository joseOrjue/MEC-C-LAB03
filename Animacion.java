import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Animacion extends JFrame {
    
    private Timer timer;
    private int velocidad;
    private int indiceImagen;
    private ImageIcon[] imagenes;
    private JLabel etiquetaImagen;
    
    public Animacion() {
        super("Animación");
        
        // Configurar las imágenes
        imagenes = new ImageIcon[3];
        imagenes[0] = new ImageIcon("imagen1.png");
        imagenes[1] = new ImageIcon("imagen2.png");
        imagenes[2] = new ImageIcon("imagen3.png");
        indiceImagen = 0;
        
        // Configurar la etiqueta de imagen
        etiquetaImagen = new JLabel(imagenes[indiceImagen]);
        add(etiquetaImagen, BorderLayout.CENTER);
        
        // Configurar el control deslizante de velocidad
        JSlider controlVelocidad = new JSlider(1, 10, 5);
        controlVelocidad.addChangeListener(e -> {
            velocidad = controlVelocidad.getValue() * 100;
            timer.cancel();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    mostrarSiguienteImagen();
                }
            }, 0, velocidad);
        });
        JPanel panelControlVelocidad = new JPanel();
        panelControlVelocidad.add(controlVelocidad);
        add(panelControlVelocidad, BorderLayout.SOUTH);
        
        // Configurar el temporizador
        velocidad = controlVelocidad.getValue() * 100;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mostrarSiguienteImagen();
            }
        }, 0, velocidad);
        
        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }
    
    private void mostrarSiguienteImagen() {
        indiceImagen++;
        if (indiceImagen == imagenes.length) {
            indiceImagen = 0;
        }
        etiquetaImagen.setIcon(imagenes[indiceImagen]);
    }
    
    public static void main(String[] args) {
        new Animacion();
    }
}
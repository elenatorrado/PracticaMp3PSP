package es.studium.PracticaMp3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaReproductor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ReproductorAudio reproductor;
    private JList<String> listaArchivos;
    private DefaultListModel<String> modelArchivos;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaReproductor frame = new VistaReproductor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VistaReproductor() {
        setTitle("Mi Musica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 376);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        reproductor = new ReproductorAudio();

        modelArchivos = new DefaultListModel<>();
        listaArchivos = new JList<>(modelArchivos);
        listaArchivos.setBounds(10, 11, 414, 275);
        contentPane.add(listaArchivos);

        JButton btnReproducir = new JButton("Reproducir");
        btnReproducir.setBounds(90, 303, 117, 23);
        contentPane.add(btnReproducir);

        JButton btnParar = new JButton("Parar");
        btnParar.setBounds(226, 302, 108, 23);
        contentPane.add(btnParar);

        // Cargar los archivos de música (esto es solo un ejemplo)
        cargarArchivosMusica();

        // Acción de reproducir
        btnReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String archivoSeleccionado = listaArchivos.getSelectedValue();
                if (archivoSeleccionado != null) {
                    reproductor.reproducir(archivoSeleccionado);
                }
            }
        });

        // Acción de parar
        btnParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reproductor.detener();
            }
        });
    
    // Doble clic en el archivo de la lista para reproducir
    listaArchivos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) { // Verifica si es doble clic
                String archivoSeleccionado = listaArchivos.getSelectedValue();
                if (archivoSeleccionado != null) {
                    reproductor.reproducir(archivoSeleccionado);
                }
            }
        }
    });
}

    // Método para cargar archivos de música
    private void cargarArchivosMusica() {
        BuscadorArchivos buscador = new BuscadorArchivos();
        java.util.List<String> archivos = buscador.buscarArchivosMusica();

        for (String archivo : archivos) {
            modelArchivos.addElement(archivo);
        }
    }
}
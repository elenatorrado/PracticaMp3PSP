package es.studium.PracticaMp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.JOptionPane;

public class ReproductorAudio {
    private Player player;
    private Thread reproduccionThread;
    private String archivoSeleccionado;

    // Reproducir el archivo MP3
    public void reproducir(String archivo) {
        if (archivo == null || archivo.isEmpty()) {
            // Mostrar mensaje en la interfaz si no se ha seleccionado un archivo
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo para reproducir.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Detener ejecución si no hay archivo
        }
        // Mostrar mensaje de éxito en la interfaz
        JOptionPane.showMessageDialog(null, "Reproducción iniciada.", 
                                      "Información", JOptionPane.INFORMATION_MESSAGE);

        // Guardar el archivo seleccionado
        this.archivoSeleccionado = archivo;

        try {
            // Abrir el archivo MP3
            FileInputStream archivoMP3 = new FileInputStream(archivo);
            this.player = new Player(archivoMP3);
            
            // Iniciar la reproducción en un nuevo hilo
            reproduccionThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                }
            });
            reproduccionThread.start();
        } catch (FileNotFoundException | JavaLayerException ex) {
            // Mostrar mensaje en caso de error al intentar reproducir el archivo
            JOptionPane.showMessageDialog(null, "Error al intentar reproducir el archivo: " + ex.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Detener la reproducción
    public void detener() {
        if (archivoSeleccionado == null || archivoSeleccionado.isEmpty()) {
            // Mostrar mensaje en la interfaz si no se ha seleccionado un archivo
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo para detener la reproducción.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Detener ejecución si no hay archivo
        }

        if (player != null) {
            player.close();  // Detener la reproducción
            if (reproduccionThread != null && reproduccionThread.isAlive()) {
                reproduccionThread.interrupt();  // Detener el hilo si está en ejecución
            }
            // Mostrar mensaje de éxito en la interfaz
            JOptionPane.showMessageDialog(null, "Reproducción detenida.", 
                                          "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

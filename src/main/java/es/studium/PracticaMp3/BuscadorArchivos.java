package es.studium.PracticaMp3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BuscadorArchivos {
	private static final List<String> EXTENSIONES_VALIDAS = List.of("mp3", "wav");

	// Buscar archivos de música en el sistema
	public List<String> buscarArchivosMusica() {
		List<String> archivos = new ArrayList<>();
		File[] drives = File.listRoots(); // Obtener las unidades de disco

		for (File drive : drives) {
			buscarArchivosEnDirectorio(drive, archivos);
		}

		return archivos;
	}

	// Buscar archivos en un directorio y subdirectorios
	private void buscarArchivosEnDirectorio(File directorio, List<String> archivos) {
		if (directorio.isDirectory()) {
			File[] listaArchivos = directorio.listFiles();
			if (listaArchivos != null) {
				for (File archivo : listaArchivos) {
					if (archivo.isDirectory()) {
						buscarArchivosEnDirectorio(archivo, archivos); // Recursión para subdirectorios
					} else {
						String extension = obtenerExtension(archivo.getName());
						if (EXTENSIONES_VALIDAS.contains(extension)) {
							archivos.add(archivo.getAbsolutePath());
						}
					}
				}
			}
		}
	}

	// Obtener la extensión del archivo
	private String obtenerExtension(String nombreArchivo) {
		int idx = nombreArchivo.lastIndexOf('.');
		if (idx > 0) {
			return nombreArchivo.substring(idx + 1).toLowerCase();
		}
		return "";
	}
}

package hilos;

	// Cargar un clip de audio y reproducirlo.
	
	
	
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
	
	public class MusicaFondo extends Thread{
		
		private File musica;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			
			
		}
		/**
		 * @param musica
		 */
		public MusicaFondo(File musica) {
			super();
			this.musica = musica;
		}
		
		
	}


package componentesvisuales;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BotonRosa extends BotonAzul{
	public BotonRosa(String s) {
		super(s);
		
		estilosPorDefecto();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.pink);
				setBackground(Color.CYAN);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			
			@Override
			public void mouseExited(MouseEvent e) {
				estilosPorDefecto();
			}
		});
		
	
	}
	
	
	
	private void estilosPorDefecto() {
		;
	}
}

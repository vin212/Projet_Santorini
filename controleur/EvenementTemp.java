package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EvenementTemp implements ActionListener {
	GestionUser control;

	public EvenementTemp(GestionUser c) {
		control = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		control.tictac();
	}
}

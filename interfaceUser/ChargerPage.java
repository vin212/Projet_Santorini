package interfaceUser;

import javax.imageio.ImageIO;
import javax.swing.*;
/*import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;*/
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.border.Border;

import modele.*;
import structure.*;
import controleur.*;

public class ChargerPage extends JPanel {

	JFrame frame;
	Fenetres f;

	public ChargerPage(JFrame frame, Fenetres f) {
		//counter = 1;
		this.frame = frame;
		this.f = f;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
		 super.paintComponent(g); 


		String [] nomSave;
		nomSave = new String[10];
		nomSave[0] = "Save 1";
		nomSave[1] = "Save 2";
		nomSave[2] = "Save 3";
		nomSave[3] = "Save 4";
		nomSave[4] = "Save 5";
		nomSave[5] = "Save 6";
		nomSave[6] = "Save 7";
		nomSave[7] = "Save 8";
		nomSave[8] = "Save 9";
		nomSave[9] = "Save 10";

		JPanel container = new JPanel();
		JPanel containerMain = new JPanel();
		JScrollPane scrPane = new JScrollPane(container);
		container.setBackground(new Color(150,150,150));

		container.setLayout(new GridBagLayout());
		containerMain.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		JPanel save;
		for (int i = 0; i < nomSave.length; i++)
		{
			Border lineborder = BorderFactory.createLineBorder(Color.black, 2);


    		//associer à JLabel
			save = new JPanel();
			gbc.gridx = 0;
			gbc.gridy = i*getSize().height/5;
			save.add(new JLabel(nomSave[i]),gbc);
			save.addMouseListener(new EcouteurDeSouris(nomSave[i]));
			save.setBorder(lineborder);
			save.setPreferredSize(new Dimension(frame.getSize().width * 7/8, frame.getSize().height/5));

			container.add(save,gbc);
			
		}
		/*JScrollPane scroll1 = new JScrollPane();
		container.add(scroll1);*/
		containerMain.setPreferredSize(new Dimension(frame.getSize().width,frame.getSize().height));
		container.setPreferredSize(new Dimension(frame.getSize().width - frame.getSize().width/10,frame.getSize().height/5*nomSave.length));
		scrPane.setPreferredSize(new Dimension(frame.getSize().width, 3*frame.getSize().height/4));
		gbc.gridx = 0;
		gbc.gridy = 0;
		containerMain.add(scrPane,gbc);
		JButton bouton = new JButton ("Retour Menu");
		bouton.addActionListener(new GestionBouton(Bouton.RETOUR_MENU, f));
		gbc.gridx = 0;
		gbc.gridy = frame.getSize().height/2;
		containerMain.add (bouton,gbc);
		add(containerMain);
		setVisible(true);

		System.out.println("c'est bon ?");
		super.paintComponent(g); 
	}
}

package interfaceUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.util.Scanner;

import modele.*;

public class AireDeDessin extends JComponent {

	Jeu j;
	Image test;

	public AireDeDessin(Jeu j) {
		this.j = j;

		try {
			InputStream in = new FileInputStream("ressource/texture/test.JPG");
			test = ImageIO.read(in);
		} catch (Exception e) {
			System.out.print("erreur \n");
			System.exit(1);
		}
		//counter = 1;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;

		int width = getSize().width;
		int height = getSize().height;

		drawable.clearRect(0, 0, width, height);
		drawable.drawImage(test,50,50, 50,50,null);
		drawable.drawImage(test,50,25, 50,50,null);

	}
}
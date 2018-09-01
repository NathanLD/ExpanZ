package utils;

import java.awt.Color;
import java.util.Random;

public enum Couleurs {
	ROUGE (new Color(229, 71, 71)),
	VERT (new Color(39, 183, 36)),
	BLEU (new Color(86, 88, 216)),
	JAUNE (new Color(234, 242, 84)),
	VIOLET (new Color(65, 5, 114)),
	ORANGE (new Color(226, 125, 36));

	private Color color;
	private static Random numberGenerator = new Random();

	private Couleurs(Color color){
		this.color = color;
	}

	public Color getColor(){
		return this.color;
	}

	public static Couleurs randomColor(){
		Couleurs[] allColors = Couleurs.values();
		Couleurs randomColor = randomElement(allColors);
		return randomColor;
	}

	private static <T> T randomElement(T[] elements){
		return elements[numberGenerator.nextInt(elements.length)];
	}
}

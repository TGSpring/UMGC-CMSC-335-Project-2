package project_2;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
/**
 * @author tyler spring 
 * project 2 
 * CMSC 335 
 * This is project 2. This is the Drawings class. Here is where the objects that are selected are created. When the user selects an object and enters the correct input,
 * this class is called to construct the shape. Each shape is created differently in the sense of their fill methods. When this class is called it is looking for the string name
 * of the object that was selected and a small array. The array contains the input the user has given, after being validated, and then with another try catch, locates which
 * object it is to either create, or which file it is to read in and display.
 */
@SuppressWarnings("serial")
public class Drawings extends JPanel {

	// Class variables width and length created in order to be reused for
	// calculations when drawing the shapes.
	// Private versions of the dimensions and the array of them are created to
	// prevent overlapping.

	static int shapeWidth;
	static int shapeLength;

	private String shapeName;
	private int dimens[];
	private int width, height, radi, length, base;

//Constructor method looking for name of shape and input array.
	public Drawings(String shapeName, int[] dimenList) {
		this.shapeName = shapeName;
		dimens = dimenList;
	}

	// paint method. This is where the try catch is used in order find and create
	// the correct shape.
	@Override
	public void paint(Graphics paints) {
		super.paint(paints);

		try {
			if (shapeName.equalsIgnoreCase("Circle")) {
				radi = dimens[0];

				paints.setColor(Color.BLACK);

				paints.fillOval((shapeWidth / 2 - radi / 4), (shapeLength / 2 - radi / 4), (2 * radi), (2 * radi));
			} else if (shapeName.equalsIgnoreCase("Rectangle")) {
				length = dimens[0];
				width = dimens[1];

				paints.setColor(Color.BLACK);
				paints.fillRect((shapeWidth / 2 - length / 4), (shapeLength / 2 - length / 4), (2 * width),
						(2 * length));
			} else if (shapeName.equalsIgnoreCase("Square")) {
				length = dimens[0];
				paints.setColor(Color.BLACK);
				paints.fillRect((shapeWidth / 2 - length / 4), (shapeLength / 2 - length / 4), (2 * length),
						(2 * length));
			} else if (shapeName.equalsIgnoreCase("Triangle")) {
				height = dimens[0];
				base = dimens[1];
				paints.setColor(Color.BLACK);
				int[] triBase = { (shapeWidth / 2) - (base / 4) - (height / 2), (shapeWidth / 2) + (2 * base),
						(shapeWidth / 2) + (base / 4) + (shapeLength / 2) };
				int[] triHeight = { (shapeLength / 2) + (2 * height), (shapeLength / 2) - (base / 2) - (height / 2),
						(shapeLength / 2) + (2 * height) };
				paints.fillPolygon(triBase, triHeight, 3);
			} else {
				// Image variable created to be used when the correct 3D object is found.
				Image img = null;

				if (shapeName.equalsIgnoreCase("Sphere")) {
					img = ImageIO.read(new File("Sphere.jfif"));
				} else if (shapeName.equalsIgnoreCase("Cube")) {
					img = ImageIO.read(new File("Cube.jfif"));

				} else if (shapeName.equalsIgnoreCase("Cone")) {
					img = ImageIO.read(new File("Cone.jfif"));
				} else if (shapeName.equalsIgnoreCase("Cylinder")) {
					img = ImageIO.read(new File("Cylinder.jfif"));
				} else if (shapeName.equalsIgnoreCase("Torus")) {
					img = ImageIO.read(new File("Torus.jfif"));
				}
				paints.drawImage(img, shapeWidth / 4, shapeLength / 4, null);

			}
		} catch (Exception e)
		// Catch in case the objects image is not found.
		{
			JOptionPane.showMessageDialog(this, "Image not found.", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}

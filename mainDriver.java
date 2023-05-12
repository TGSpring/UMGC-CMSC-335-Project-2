package project_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author tyler spring 
 * project 2 
 * CMSC 335 
 * This is project 2. It uses the same shapes and a similar main class, but uses a GUI and action listeners, along with new input validation.
 * When the program is ran, the GUI appears with all 9 shapes as buttons that the user can pick. When a button of a non 3D shape is selected,
 * the user is prompted by another GUI asking for input of the dimensions of the selected object. If the user enters a number greater than 800,
 * the max sizes for the main GUI, the user is told their input is too large and must reEnter. This will continue to prompt them until they enter
 * the correct input. Or cancel. Once the user inputs the correct numbers, the shape is drawn using the Drawings class, and displayed in the 
 * main GUI. If a user selects a 3D object, they will have a smaller GUI appear saying that they have selected a 3D shape and to select OK 
 * in order to have an image of the object displayed in the main GUI.
 */
@SuppressWarnings("serial")
public class mainDriver extends JFrame implements ActionListener {
//Array for buttons, JPanel object, object of Drawings class, inputs and dimensions of the objects created here.
	private JButton[] All_Shapes;
	JPanel drawP;
	Drawings draws;
	String in1, in2;
	int base, heig, side, width, len, radi;

//mainDriver is the method that generates the GUI, prompts the user, validates input, and displays output.
	public mainDriver() {
		draws = new Drawings(" ", new int[] { 0 });
		Drawings.shapeLength = 500;
		Drawings.shapeWidth = 500;
//Buttons, GUI, shapes names are created here for use in the action listener method.
		drawP = new JPanel(new BorderLayout(250, 250));
		JPanel buttonP = new JPanel();
		
		drawP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		drawP.setBackground(Color.GRAY);

		setLayout(new BorderLayout());

		buttonP.setLayout(new GridLayout(3, 3));
		
		String[] shapeNameIn = { "Circle", "Square", "Rectangle", "Triangle", "Sphere", "Cone", "Cube", "Cylinder",
				"Torus" };
		// Buttons created then added to ButtonGroup through forLoop.
		All_Shapes = new JButton[shapeNameIn.length];
		
		for (int i = 0; i < All_Shapes.length; i++) 
		{
			All_Shapes[i] = new JButton(shapeNameIn[i]);
			All_Shapes[i].setBackground(Color.RED);
			All_Shapes[i].addActionListener(this);
			buttonP.add(All_Shapes[i]);
		}
		
		add(buttonP, BorderLayout.SOUTH);
		buttonP.setPreferredSize(new Dimension(120,100));
		add(drawP, BorderLayout.CENTER);
		
	}

//ActionPerformed method. Here a try catch is used to detect what the user has selected. Once that is found the user is prompted accordingly.
	public void actionPerformed(ActionEvent a) 
	{
		String select = a.getActionCommand();
		try 
		{
			if (select.equalsIgnoreCase("Circle")) 
			{
				in1 = JOptionPane.showInputDialog("You selected Circle, enter its radius");
				radi = Integer.parseInt(in1);
				if (radi > 800) 
				{
					// Here is where the validation of the input is done. If the radius is greater
					// than 800. The do while loop will be used until the enter something less than
					// 800.
					do 
					{

						in1 = JOptionPane.showInputDialog("Too large of radius. ReEnter.");
						radi = Integer.parseInt(in1);
					} while (radi > 800);
				}
				drawP.remove(draws);
				draws = new Drawings("Circle", new int[] { radi });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();
			} 
			else if (select.equalsIgnoreCase("Triangle")) 
			{
				in1 = JOptionPane.showInputDialog("You selected Triangle, enter the base");
				in2 = JOptionPane.showInputDialog("Enter the height");

				base = Integer.parseInt(in1);
				heig = Integer.parseInt(in2);
				if (base > 800 || heig > 800) 
				{
					// Here is where the validation of the input is done. If the base or height is
					// greater than 800. The do while loop will be used until the enter something
					// less than 800 for both.
					do 
					{

						in1 = JOptionPane.showInputDialog("Too large of base. ReEnter.");
						in2 = JOptionPane.showInputDialog("Too large of height. Enter the height");
						base = Integer.parseInt(in1);
						heig = Integer.parseInt(in2);
					} while (base > 800 || heig > 800);
				}
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { base, heig });

				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();

			} 
			else if (select.equalsIgnoreCase("Square")) 
			{
				in1 = JOptionPane.showInputDialog("You have selected Square, enter the side length");
				side = Integer.parseInt(in1);
				if (side > 800) 
				{
					// Here is where the validation of the input is done. If the side is greater
					// than 800. The do while loop will be used until the enter something less than
					// 800.
					do 
					{

						in1 = JOptionPane.showInputDialog("Too large of length. ReEnter.");
						side = Integer.parseInt(in1);
					} while (side > 800);
				}
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { side });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
			}
			else if (select.equalsIgnoreCase("Rectangle")) 
			{
				in1 = JOptionPane.showInputDialog("You have selected Rectangle, enter the width");
				in2 = JOptionPane.showInputDialog("Enter the length");

				width = Integer.parseInt(in1);
				len = Integer.parseInt(in2);
				if (width > 800 || len > 800) 
				{
					// Here is where the validation of the input is done. If the width or length is
					// greater than 800. The do while loop will be used until the enter something
					// less than 800 for both.
					do 
					{

						in1 = JOptionPane.showInputDialog("Too large of width. ReEnter.");
						in2 = JOptionPane.showInputDialog("Too large of length. Enter the height");
						width = Integer.parseInt(in1);
						len = Integer.parseInt(in2);
					}
					while (width > 800 || len > 800);
				}
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { width, len });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();

				// Here is where the 3D shapes start. If the user selects one, a message will
				// appear telling the user they have selected one and need to click ok in order
				// to make the image appear.
			} 
			else if (select.equalsIgnoreCase("Sphere")) 
			{
				in1 = JOptionPane.showInputDialog(
						"You have selected the 3D object sphere. Select ok to display an image of the object.");
				// Could I have made a JFrame Object and just make this a showMessageDialog,
				// yes. Did I feel like it when I could copy and paste and just leave the cancel
				// option, which is actually better in case of clicking by accident? No. I did
				// not. So I did it this way.
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { radi });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();
			} 
			else if (select.equalsIgnoreCase("Cone")) 
			{
				in1 = JOptionPane.showInputDialog(
						"You have selected the 3D object cone. Select ok to display an image of the object.");
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { len });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();
			}
			else if (select.equalsIgnoreCase("Cylinder")) 
			{
				in1 = JOptionPane.showInputDialog(
						"You have selected the 3D object cylinder. Select ok to display an image of the object.");
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { len });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();

			}
			else if (select.equalsIgnoreCase("Cube")) 
			{
				in1 = JOptionPane.showInputDialog(
						"You have selected the 3D object cube. Select ok to display an image of the object.");
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { len });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();

			} 
			else if (select.equalsIgnoreCase("Torus")) 
			{
				in1 = JOptionPane.showInputDialog(
						"You have selected the 3D object Torus. Select ok to display an image of the object.");
				drawP.remove(draws);
				draws = new Drawings(select, new int[] { len });
				drawP.add(draws, BorderLayout.CENTER);
				drawP.revalidate();
				drawP.repaint();
			}
		}
		catch (Exception e) 
		{
			// Catch for is a user hits cancel, or somehow clicks the wrong object.
			JOptionPane.showMessageDialog(this, "Invalid input. Try again.");
		}
	}

//Main method with object of mainDriver created.
	public static void main(String[] args) {
		mainDriver test = new mainDriver();
		test.setSize(800, 800);
		test.setResizable(true);
		test.setTitle("Shape GUI");
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}

import homework1;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.*;

/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Animator extends JFrame implements ActionListener {

	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, insertMenu, helpMenu;
	private JMenuItem newItem, exitItem,
						rectangleItem, roundedRectangleItem, ovalItem,
						numberedOvalItem, sectorItem, aboutItem;
	private JCheckBoxMenuItem animationCheckItem;
	private JPanel mainPanel;
	private ArrayList<Shape> shapes;
	private int numOfNumberdOvals;
	private Random rand;


	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Animator() {
		super("Animator");

		// create main panel and menubar
		mainPanel = (JPanel)createMainPanel();
		getContentPane().add(mainPanel);
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);
        shapes = new ArrayList<>();
		numOfNumberdOvals = 0;
		rand = new Random();

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                	// TODO: Add code for making one animation step for all
                	// 		 shapes in this
					ListIterator iter = shapes.listIterator();

					while(iter.hasNext()){
						Animatable nextShape = (Animatable) iter.next();
						nextShape.step(getContentPane().getBounds());
					}
                	

            		repaint();	// make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
	}


	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setPreferredSize(
    			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    	mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    	mainPanel.setBackground(Color.WHITE);

    	return mainPanel;
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();

    	fileMenu = new JMenu("File");
    	newItem = new JMenuItem("New");
    	newItem.addActionListener(this);
    	fileMenu.add(newItem);
    	animationCheckItem = new JCheckBoxMenuItem("Animation");
    	fileMenu.add(animationCheckItem);
    	exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(this);
    	fileMenu.add(exitItem);
    	menuBar.add(fileMenu);

    	insertMenu = new JMenu("Insert");
    	rectangleItem = new JMenuItem("Rectangle");
    	rectangleItem.addActionListener(this);
    	insertMenu.add(rectangleItem);
    	roundedRectangleItem = new JMenuItem("Rounded Rectangle");
    	roundedRectangleItem.addActionListener(this);
    	insertMenu.add(roundedRectangleItem);
    	ovalItem = new JMenuItem("Oval");
    	ovalItem.addActionListener(this);
    	insertMenu.add(ovalItem);
    	numberedOvalItem = new JMenuItem("Numbered Oval");
    	numberedOvalItem.addActionListener(this);
    	insertMenu.add(numberedOvalItem);
    	sectorItem = new JMenuItem("Sector");
    	sectorItem.addActionListener(this);
    	insertMenu.add(sectorItem);
    	menuBar.add(insertMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}


	/**
	 * @modifies g
	 * @effects Paint this including all its shapes to g. This method is
	 * 			invoked by Swing to draw components. It should not be invoked
	 * 			directly, but the repaint method should be used instead in
	 * 			order to schedule the component for redrawing.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		for(Shape shape : shapes){
			Graphics2D contentPaneGraphics = (Graphics2D) getContentPane().getGraphics();
			contentPaneGraphics.setColor(shape.getColor());
			shape.draw(contentPaneGraphics);
		}
		
	}


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		// File->New : clear all shapes
		if (source.equals(newItem)) {
			shapes.clear();
			repaint();
			numOfNumberdOvals = 0;
			//TODO  Add code for number of LocationChangingNumerOval = 0
		}

		// File->Exit: close application
		else if (source.equals(exitItem)) {
        	dispose();
        }

		// Insert a shape
		else if ((source.equals(rectangleItem)) ||
      		 	 (source.equals(roundedRectangleItem)) ||
      		 	 (source.equals(ovalItem)) ||
      		 	 (source.equals(numberedOvalItem)) ||
      		 	 (source.equals(sectorItem))) {
			Shape newShape;
			int randWidth = (int)(WINDOW_WIDTH * (0.1 + (0.3 - 0.1) * rand.nextDouble()));
			int randHeight = (int)(WINDOW_HEIGHT * (0.1 + (0.3 - 0.1) * rand.nextDouble()));
			int xMax = getContentPane().getWidth() - randWidth;
			int yMax = getContentPane().getHeight() - randHeight;
			int xPos = -1;
			int yPos = -1;
			Rectangle newShapeBound= new Rectangle(xPos , yPos , randWidth , randHeight);
			while(!getContentPane().getBounds().contains(newShapeBound)){
				xPos = rand.nextInt(xMax);
				yPos = rand.nextInt(yMax);
				newShapeBound.setLocation(xPos , yPos);
			}
			Color shapeColor = new Color(rand.nextInt(255) , rand.nextInt(255) , rand.nextInt(255));
			Point shapeLoc = newShapeBound.getLocation();
			Dimension shapeSize = newShapeBound.getSize();
			if(source.equals(ovalItem)) {
				newShape = new LocationChangingOval(shapeLoc, shapeSize, shapeColor);
				shapes.add(newShape);
			}
			else if(source.equals(numberedOvalItem)){
				newShape = new LocationChangingNumberdOval(shapeLoc , shapeSize, shapeColor , numOfNumberdOvals);
				shapes.add(newShape);
				numOfNumberdOvals++;
			}
			else if(source.equals(sectorItem)){
				int startAngle = rand.nextInt(360);
				int arcAngle = rand.nextInt(360);
				newShape = new AngleChangingSector(shapeLoc , shapeSize , shapeColor , startAngle , arcAngle);
				shapes.add(newShape);
			}
			else if(source.equals(rectangleItem)){
				newShape = new LocationChangingRectangle(shapeLoc, shapeSize, shapeColor);
				shapes.add(newShape);
			}
			else if(source.equals(roundedRectangleItem)){
				newShape = new LocationChangingRoundedRetangle(shapeLoc, shapeSize, shapeColor);
				shapes.add(newShape);
			}

			// TODO: Add code for creating the appropriate shape such that:
			// 		 it is completely inside the window's bounds &&
			//		 its location and size are randomly selected &&
			//		 1/10*WINDOW_WIDTH <= shape.width < 3/10*WINDOW_WIDTH &&
			//		 1/10*WINDOW_HEIGHT <= shape.height < 3/10*WINDOW_HEIGHT
		
			
			repaint();
		}

		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Animator - 1st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }



	/**
	 * @effects Animator application.
	 */
	public static void main(String[] args) {
		Animator application = new Animator();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setResizable(false);
		application.pack();
		application.setVisible(true);
	}
}

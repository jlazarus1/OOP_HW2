package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

	private Point location;
	private Color color;
	private Dimension size;

	
	// TODO: Write Abstraction Function
    // Abs. Function:
    //  represents a shape with a top left corner at this.location and with a color as in this.color.
    //
    // Rep .Invariant:
    //  location != null && locatiom.X >= 0 && locatiom.Y >= 0
    //  color != null.
	
	// TODO: Write Representation Invariant
	
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Dimension dimension , Color color) {
    	setLocation(location);
    	setColor(color);
    	try {
            setSize(dimension);
        }
    	catch (ImpossibleSizeException e){
    	    size = e.getSize();
        }
    }
    private void checkRep(){
        assert color != null: "Color must not be null.";
        assert location != null: "Location must not be null.";
        assert location.getX() >= 0: "Cooridnates must be non negative.";
        assert location.getY() >= 0: "Cooridnates must be non negative.";
    }

    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
    	// TODO: Implement this method
       return (Point) location.clone();
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {

        this.location = (Point)location.clone();
        checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
        if(dimension.getHeight() <= 0 || dimension.getWidth() <= 0){
            throw new ImpossibleSizeException();
        }
        size = new Dimension(dimension);
    };

    
    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds(){
        Dimension size = new Dimension(this.size);
        return new Rectangle(getLocation() , size);
    }
  

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {

        return getBounds().contains(point);
    }
        

    /**
     * @return color of this.
     */
    public Color getColor() {

        return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {

        this.color = color;
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
    	// TODO: Implement this method
        checkRep();
        Shape newShape = null;
        try{
            newShape = (Shape) super.clone();
        }
        catch (CloneNotSupportedException e){
            assert false: "CloneNotSupportedException for Shape.clone()";
        }
        newShape.location = (Point) this.location.clone();
        newShape.color = this.color;
        newShape.size = new Dimension(size);

        return  newShape;
    }
}
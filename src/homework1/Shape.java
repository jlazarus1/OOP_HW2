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

	
	// TODO: Write Abstraction Function
    // Abs. Function:
    //  represents a shape with a top left corner at this.location and with a color as in this.color.
    //
    // Rep .Invariant:
    //  location != null
    //  color != null.
	
	// TODO: Write Representation Invariant
	
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
    	setLocation(location);
    	setColor(color);
        checkRep();
    }
    private void checkRep(){
        assert color != null: "Color must not be null.";
        assert location != null: "Location must not be null.";
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
    public abstract void setSize(Dimension dimension) throws ImpossibleSizeException;

    
    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();
  

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
        newShape.color = (Color) this.color;

        return  newShape;
    }
}
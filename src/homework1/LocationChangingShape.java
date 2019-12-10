package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

	// TODO: Write Abstraction Function
	// TODO: Write Representation Invariant

	private int xVelocity;
	private int yVelocity;

	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
    	// TODO: Implement this constructor
        super(location , color);
        Random rand = new Random();
        xVelocity = 0;
        yVelocity = 0;
        while(xVelocity == 0) xVelocity = rand.nextInt(10) - 5;
        while(yVelocity == 0) yVelocity = rand.nextInt(10) - 5;


    
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
    	// TODO: Implement this method
        return  xVelocity;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
    	// TODO: Implement this method
    	return yVelocity;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
    	// TODO: Implement this method
        this.xVelocity = velocityX;
        this.yVelocity = velocityY;

    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
        // TODO: Implement this method
        Point newLocX = getLocation();
        Point newLocY = getLocation();
        Point finalLoc = getLocation();
        newLocX.translate(xVelocity, 0);
        newLocY.translate(0, yVelocity);
        Rectangle newBoundX = getBounds();
        Rectangle newBoundY = getBounds();
        newBoundX.setLocation(newLocX);
        newBoundY.setLocation(newLocY);
        if(!bound.contains(getBounds()) || !bound.contains(newBoundX) || !bound.contains(newBoundY)) {
            if (!bound.contains(newBoundX)) {
                xVelocity = -xVelocity;
            }
            if (!bound.contains(newBoundY)) {
                yVelocity = -yVelocity;
            }
        }
        finalLoc.translate(xVelocity , yVelocity);
        setLocation(finalLoc);
    }
}

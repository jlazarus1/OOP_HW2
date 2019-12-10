package homework1;

import java.awt.*;

public class LocationChangingRectangle extends LocationChangingShape {


    /**
     * @param location
     * @param color
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingRectangle(Point location,  Dimension dimension , Color color) {
        super(location, dimension , color);
    }

    @Override
    public void draw(Graphics g) {

    }
}

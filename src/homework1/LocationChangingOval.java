package homework1;

import java.awt.*;

public class LocationChangingOval extends LocationChangingShape {

    private Rectangle size;
    /**
     * @param dim
     * @param location
     * @param color
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */

    LocationChangingOval(Point location, Color color , Dimension dim) {
        super(location, color);
        size = new Rectangle(dim);
        size.setLocation(getLocation());
    }

    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        if(dimension.getWidth() < 0 || dimension.getHeight() < 0)throw new ImpossibleSizeException();
         size.setSize(dimension);
    }

    @Override
    public Rectangle getBounds() {
        return size.getBounds();
    }

    @Override
    public void draw(Graphics g) {
        int x = (int)getLocation().getX();
        int y = (int) getLocation().getY();
        int height = (int) getBounds().getHeight();
        int width = (int) getBounds().getWidth();
        g.fillOval(x , y , width , height);
        g.setColor(getColor());
    }
}


import homework1;
import java.awt.*;

public class LocationChangingRoundedRetangle extends LocationChangingRectangle {

    /**
     * @param location
     * @param color
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingRoundedRetangle(Point location,  Dimension dimension , Color color) {
        super(location, dimension , color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle shapeBound = getBounds();
        int x = (int) shapeBound.getX();
        int y = (int) shapeBound.getY();
        int height = (int) shapeBound.getHeight();
        int width = (int) getBounds().getWidth();
        g2d.fillRoundRect(x , y , width , height,20,20);
        g2d.setColor(getColor());
    }






}
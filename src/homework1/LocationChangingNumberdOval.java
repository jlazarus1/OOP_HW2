package homework1;

import java.awt.*;

public class LocationChangingNumberdOval extends LocationChangingOval {

    private Integer id;
    /**
     * @param location
     * @param color
     * @param dim
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingNumberdOval(Point location, Color color, Dimension dim , int id) {
        super(location, color, dim);
        this.id = id;
    }

    @Override
    public void draw(Graphics g) {
        Rectangle bounds = getBounds();
        int x = (int)bounds.getX();
        int y = (int) bounds.getY();
        int height = (int) bounds.getHeight();
        int width = (int) bounds.getWidth();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());
        g2d.fillOval(x , y , width , height);
        x = x + (width / 2);
        y = y + (height / 2);
        g2d.setColor(Color.WHITE);
        g2d.drawString(id.toString() , x , y);
    }
}

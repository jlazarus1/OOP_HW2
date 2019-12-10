package homework1;

import java.awt.*;

public class LocationChangingNumberdOval extends LocationChangingOval {


    private Integer number;
    /**
     * @param location
     * @param color
     * @param dim
     * @effects Initializes this with a a given location and color. Each
     * of the horizontal and vertical velocities of the new
     * object is set to a random integral value i such that
     * -5 <= i <= 5 and i != 0
     */
    LocationChangingNumberdOval(Point location, Color color, Dimension dim , int Num) {
        super(location, color, dim);
        number = Num;
        number++;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int)getLocation().getX();
        int y = (int) getLocation().getY();
        int height = (int) getBounds().getHeight();
        int width = (int) getBounds().getWidth();

        g.fillOval(x , y , width , height);
        g.setColor(getColor());
//        x = x + (width/2);
//        y = y + (height/2);
        g.drawString(number.toString() , x , y);
    }
}

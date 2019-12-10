package homework1;

import java.awt.*;

public class AngleChangingSector extends Shape implements Animatable {
    private int startAngle;
    private int arcAngle;
    /**
     * @param location
     * @param dimension
     * @param color
     * @effects Initializes this with a a given location and color.
     */
    public AngleChangingSector(Point location, Dimension dimension, Color color , int startAngle , int arcAngle) {
        super(location, dimension, color);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    @Override
    public void step(Rectangle bound) {
        if(arcAngle == 360) arcAngle = 0;
        arcAngle++;
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Rectangle shapeBound = getBounds();

        int x = (int) shapeBound.getX();
        int y = (int) shapeBound.getY();
        int height = (int) shapeBound.getHeight();
        int width = (int) getBounds().getWidth();
        g2d.fillArc(x , y , width , height , startAngle , arcAngle);
    }
}

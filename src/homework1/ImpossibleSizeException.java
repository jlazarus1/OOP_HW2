package homework1;

import java.awt.*;


public class ImpossibleSizeException extends Exception {
    Dimension size;
    /**
     * @effects Initializes this with a a zero size dimension.
     */
    public ImpossibleSizeException(){
        super();
        size = new Dimension();

    }
    /**
     * @return height = 0 width = 0
     */
    public Dimension getSize(){
        return size;
    }

}

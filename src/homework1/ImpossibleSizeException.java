import homework1;

import java.awt.*;


public class ImpossibleSizeException extends Exception {
    public Dimension size;

    /**
     * @effects Initializes this with a 20*20 size dimension.
     */
    public ImpossibleSizeException(){

        size = new Dimension(20,20);

    }
    /**
     * @return height = 20 width = 20
     */
    public Dimension getSize(){
        return size;
    }

}

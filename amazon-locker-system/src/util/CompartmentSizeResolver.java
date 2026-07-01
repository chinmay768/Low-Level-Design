package util;

import enums.CompartmentSize;
import model.Dimensions;

public class CompartmentSizeResolver {

    public CompartmentSize resolve(Dimensions dimensions) {

        double volume =
                dimensions.getLength()
                        * dimensions.getWidth()
                        * dimensions.getHeight();

        if(volume <= 1000)
            return CompartmentSize.SMALL;

        if(volume <= 5000)
            return CompartmentSize.MEDIUM;

        if(volume <= 15000)
            return CompartmentSize.LARGE;

        return CompartmentSize.EXTRA_LARGE;
    }
}
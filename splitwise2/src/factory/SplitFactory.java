package factory;

import enums.SplitType;
import strategy.EqualSplit;
import strategy.ExactSplit;
import strategy.PercentageSplit;
import strategy.SplitStrategy;

public class SplitFactory {
    public static SplitStrategy getSplitStrategy(SplitType type) {
        switch (type) {
            case EQUAL:
                return new EqualSplit();
            case EXACT:
                return new ExactSplit();
            case PERCENTAGE:
                return new PercentageSplit();
            default:
                return new EqualSplit();
        }
    }
}
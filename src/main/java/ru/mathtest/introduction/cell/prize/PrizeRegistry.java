package ru.mathtest.introduction.cell.prize;

import java.util.HashMap;
import java.util.Map;

public class PrizeRegistry {

    private Map<PrizeType, Prize> prizes = new HashMap<>();

    public PrizeRegistry() {
        prizes.put(PrizeType.MINI, new MiniPrize());
        prizes.put(PrizeType.MINOR, new MinorPrize());
        prizes.put(PrizeType.MAJOR, new MajorPrize());
        prizes.put(PrizeType.GRAND, new GrandPrize());
    }

    public Prize getPrize(PrizeType prizeType) {
        return prizes.get(prizeType);
    }
}

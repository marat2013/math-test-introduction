package ru.mathtest.introduction.unit.cell.prize;

import org.junit.jupiter.api.Test;
import ru.mathtest.introduction.cell.prize.GrandPrize;
import ru.mathtest.introduction.cell.prize.MajorPrize;
import ru.mathtest.introduction.cell.prize.MiniPrize;
import ru.mathtest.introduction.cell.prize.MinorPrize;
import ru.mathtest.introduction.cell.prize.PrizeRegistry;
import ru.mathtest.introduction.cell.prize.PrizeType;

import static org.junit.jupiter.api.Assertions.*;

public class PrizeRegistryTest {

    @Test
    public void getPrize_shouldReturnCorrectPrizeInstances() {
        PrizeRegistry registry = new PrizeRegistry();

        assertTrue(registry.getPrize(PrizeType.MINI) instanceof MiniPrize);
        assertTrue(registry.getPrize(PrizeType.MINOR) instanceof MinorPrize);
        assertTrue(registry.getPrize(PrizeType.MAJOR) instanceof MajorPrize);
        assertTrue(registry.getPrize(PrizeType.GRAND) instanceof GrandPrize);
    }

    @Test
    public void getPrize_shouldNotReturnNull_forExistingTypes() {
        PrizeRegistry registry = new PrizeRegistry();

        for (PrizeType type : PrizeType.values()) {
            assertNotNull(registry.getPrize(type));
        }
    }
}
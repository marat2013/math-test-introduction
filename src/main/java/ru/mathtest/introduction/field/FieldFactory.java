package ru.mathtest.introduction.field;

import ru.mathtest.introduction.handler.ReelsHandler;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.handler.holdandwin.HoldAndWinBonusReelsHandler;
import ru.mathtest.introduction.handler.holdandwin.HoldAndWinPrizeReelsHandler;
import ru.mathtest.introduction.handler.usual.BonusReelsHandler;
import ru.mathtest.introduction.handler.usual.SymbolLineReelsHandler;
import ru.mathtest.introduction.generator.layout.FieldLayoutGenerator;

import java.util.ArrayList;
import java.util.List;

public class FieldFactory {

    public Field getUsualField(FieldLayoutGenerator layoutGenerator) {
        List<ReelsHandler> usualReelsHandlers = new ArrayList<>();
        usualReelsHandlers.add(new SymbolLineReelsHandler());
        usualReelsHandlers.add(new BonusReelsHandler());
        return new UsualField(layoutGenerator, new ReelsHandlerManager(usualReelsHandlers));
    }

    public Field getHoldAndWinField(FieldLayoutGenerator layoutGenerator) {
        List<ReelsHandler> holdAndWinReelsHandlers = new ArrayList<>();
        holdAndWinReelsHandlers.add(new HoldAndWinBonusReelsHandler());
        holdAndWinReelsHandlers.add(new HoldAndWinPrizeReelsHandler());
        return new HoldAndWinField(layoutGenerator, new ReelsHandlerManager(holdAndWinReelsHandlers));
    }
}

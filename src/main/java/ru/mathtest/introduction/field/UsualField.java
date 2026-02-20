package ru.mathtest.introduction.field;

import ru.mathtest.introduction.cell.Cell;
import ru.mathtest.introduction.handler.ReelsHandlerManager;
import ru.mathtest.introduction.dto.SpinContext;
import ru.mathtest.introduction.generator.layout.FieldLayoutGenerator;

public class UsualField extends AbstractField {
    private final FieldLayoutGenerator randomFieldLayoutGenerator;
    private final ReelsHandlerManager reelsHandlerManager;

    public UsualField(FieldLayoutGenerator randomFieldLayoutGenerator, ReelsHandlerManager reelsHandlerManager) {
        this.randomFieldLayoutGenerator = randomFieldLayoutGenerator;
        this.reelsHandlerManager = reelsHandlerManager;
    }

    @Override
    public Cell[][] spin(SpinContext ctx) {
        Cell[][] reels = randomFieldLayoutGenerator.fill(this.getCurrentReels(), ctx);
        setCurrentReels(reels);
        return reels;
    }

    @Override
    public void checkResults(Cell[][] reels, SpinContext ctx) {
        reelsHandlerManager.handle(reels, ctx);
    }


}

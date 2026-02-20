package ru.mathtest.introduction.handler;

import java.util.ArrayList;
import java.util.List;

public class LineLayouts {

    public static final int[][] HORIZONTAL_FIRST;
    public static final int[][] HORIZONTAL_SECOND;
    public static final int[][] HORIZONTAL_THIRD;

    public static final int[][] VERTICAL_FIRST;
    public static final int[][] VERTICAL_SECOND;
    public static final int[][] VERTICAL_THIRD;

    public static final int[][] DIAGONAL_LEFT_TOP_TO_RIGHT_BOTTOM;
    public static final int[][] DIAGONAL_LEFT_BOTTOM_TO_RIGHT_TOP;

    public static final List<int[][]> LINES = new ArrayList<>();

    static {
        HORIZONTAL_FIRST = new int[][]{
                {1, 1, 1},
                {0, 0, 0},
                {0, 0, 0}
        };

        HORIZONTAL_SECOND = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };

        HORIZONTAL_THIRD = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {1, 1, 1}
        };

        VERTICAL_FIRST = new int[][]{
                {1, 0, 0},
                {1, 0, 0},
                {1, 0, 0}
        };

        VERTICAL_SECOND = new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        VERTICAL_THIRD = new int[][]{
                {0, 0, 1},
                {0, 0, 1},
                {0, 0, 1}
        };

        DIAGONAL_LEFT_TOP_TO_RIGHT_BOTTOM = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        DIAGONAL_LEFT_BOTTOM_TO_RIGHT_TOP = new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };

        LINES.add(HORIZONTAL_FIRST);
        LINES.add(HORIZONTAL_SECOND);
        LINES.add(HORIZONTAL_THIRD);

        LINES.add(VERTICAL_FIRST);
        LINES.add(VERTICAL_SECOND);
        LINES.add(VERTICAL_THIRD);

        LINES.add(DIAGONAL_LEFT_TOP_TO_RIGHT_BOTTOM);
        LINES.add(DIAGONAL_LEFT_BOTTOM_TO_RIGHT_TOP);
    }
}

package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {
    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage largeSquare;
    private GrayscaleImage largeWide;
    private GrayscaleImage smallTall;
    private GrayscaleImage largeTall;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
        largeSquare = new GrayscaleImage(new double[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}
        });
        largeWide = new GrayscaleImage(new double[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
        });
        smallTall = new GrayscaleImage(new double[][]{{1,2},{3,4},{5,6}});
        largeTall = new GrayscaleImage(new double[][]{
                {2, 3, 4, 5, 6},
                {8, 9, 10, 11, 12},
                {14, 15, 16, 17, 18},
                {20, 21, 22, 23, 24},
                {26, 27, 28, 29, 30},
                {32, 33, 34, 35, 36}
        });
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);

        assertEquals(largeSquare.getPixel(3,4), 28);
        assertEquals(largeSquare.getPixel(5,5),36);
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void mirrored2() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3},{6,5}});
        assertEquals(smallTall.mirrored(), expected);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void cropped2() {
        var cropped = largeSquare.cropped(3,3,2,2);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{22,23},{28,29}}));
    }


    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }

    @Test
    void squarified2(){
        var squared = largeWide.squarified();
        var expected = new GrayscaleImage(new double[][]{
                { 2, 3, 4, 5 },
                { 8, 9, 10, 11 },
                { 14, 15, 16, 17 },
                { 20, 21, 22, 23 },
        });
        assertEquals(squared, expected);
    }

    @Test
    void squarified3(){
        var squared = largeTall.squarified();
        var expected = new GrayscaleImage(new double[][]{
                { 2, 3, 4, 5, 6},
                { 8, 9, 10, 11, 12 },
                { 14, 15, 16, 17, 18 },
                { 20, 21, 22, 23, 24 },
                {26, 27, 28, 29, 30}
        });
        assertEquals(squared, expected);
    }

    @Test
    void squarified4() {
        var squared = smallTall.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(squared, expected);
    }

    @Test
    void testGetPixelThrowsOnNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> { smallSquare.getPixel(-1,0);});
    }


}
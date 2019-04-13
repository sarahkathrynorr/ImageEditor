import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

import java.awt.*;
import java.util.List;

public class PPMImage {
    public Pixel[][] pixelArray;
    public int width;
    public int height;
    public int maxColorValue;
    public String magicNumber;

    public PPMImage(String mN, int w, int h, int maxCV) {
        magicNumber = mN;
        width = w;
        height = h;
        maxColorValue = maxCV;

        pixelArray = new Pixel[width][height];
    }

    public void addPixel(int x, int y, int red, int green, int blue){
        Pixel newPixel = new Pixel(red, green, blue);
        pixelArray[x][y] = newPixel;
    }

    public void grayscale() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int gray = pixelArray[x][y].grayScale;
                Pixel grayPixel = new Pixel(gray,gray,gray);
                pixelArray[x][y] = grayPixel;
            }
        }
    }

    public void invert() {
        /* every color value is inverted using max value*/
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++){
               int newRed = maxColorValue - pixelArray[x][y].redValue;
               int newGreen = maxColorValue - pixelArray[x][y].greenValue;
               int newBlue = maxColorValue - pixelArray[x][y].blueValue;

               Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
               pixelArray[x][y] = newPixel;
            }
        }
    }

    public void emboss() {
        for (int y = height - 1; y >= 0; y--) {
            for (int x = width - 1; x >= 0; x--) {

                int redDiff;
                int greenDiff;
                int blueDiff;
                if (x > 0 && y > 0) {
                    Pixel current = pixelArray[x][y];
                    Pixel next = pixelArray[x - 1][y - 1];
                    redDiff = current.redValue - next.redValue;
                    greenDiff = current.greenValue - next.greenValue;
                    blueDiff = current.blueValue - next.blueValue;

                    int maxDiff;

                    if (Math.abs(redDiff) >= Math.abs(greenDiff)) {
                        maxDiff = redDiff;
                    }
                    else {
                        maxDiff = greenDiff;
                    }

                    if (Math.abs(blueDiff) > Math.abs(maxDiff)) {
                        maxDiff = blueDiff;
                    }

                    int v = 128 + maxDiff;

                    if (v > 255) {
                        v = 255;
                    }
                    else if (v < 0) {
                        v = 0;
                    }

                    Pixel newPix = new Pixel(v, v, v);
                    pixelArray[x][y] = newPix;
                }
                else if (x == 0 || y == 0) {
                    int v = 128;

                    Pixel newPix = new Pixel(v, v, v);
                    pixelArray[x][y] = newPix;
                }
            }
        }
    }

    public void motionblur(int blurlength) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int avRed = 0;
                int avGreen = 0;
                int avBlue = 0;

                if (x + blurlength < width) {
                    for (int b = blurlength; b > 0; b--) {
                        Pixel averagePix = pixelArray[x+b-1][y];

                        avRed += averagePix.redValue;
                        avGreen += averagePix.greenValue;
                        avBlue += averagePix.blueValue;
                    }
                    int currentRed = avRed/blurlength;
                    int currentGreen = avGreen/blurlength;
                    int currentBlue = avBlue/blurlength;

                    Pixel newPixel = new Pixel(currentRed, currentGreen, currentBlue);
                    pixelArray[x][y] = newPixel;
                }
                else {
                    int tempBlurLength = width - x;
                    for (int b = tempBlurLength; b > 0; b--) {
                        Pixel averagePix = pixelArray[x+b-1][y];

                        avRed += averagePix.redValue;
                        avGreen += averagePix.greenValue;
                        avBlue += averagePix.blueValue;
                    }
                    int currentRed = avRed/tempBlurLength;
                    int currentGreen = avGreen/tempBlurLength;
                    int currentBlue = avBlue/tempBlurLength;

                    Pixel newPixel = new Pixel(currentRed, currentGreen, currentBlue);
                    pixelArray[x][y] = newPixel;
                }
            }
        }
    }
}

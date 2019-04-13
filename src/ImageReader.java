import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ImageReader {
    public ImageReader(File inputFile, String selection, int blurLength, File outFileName) throws IOException {

        PPMImage myImage;
        Scanner sc = new Scanner(inputFile);
        sc.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");


        while (sc.hasNext()) {
            String MagicNumber = sc.next(); //P3 or P6

            int width = sc.nextInt(); //or double?

            int height = sc.nextInt();

            int maxColorValue = sc.nextInt();

            myImage = new PPMImage(MagicNumber, width, height, maxColorValue);
            //CREATE PPM IMAGE HERE

            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++) {
                    int redVal = sc.nextInt();

                    int greenVal = sc.nextInt();

                    int blueVal = sc.nextInt();

                    myImage.addPixel(x, y, redVal, greenVal, blueVal);
                    //Call function in PPM image with x,y,red,green, and blue.
                }
            //there should be nothing else to read in at this point

            switch (selection) {
                case "grayscale":
                    myImage.grayscale();
                    break;
                case "invert":
                    myImage.invert();
                    break;
                case "emboss":
                    myImage.emboss();
                    break;
                case "motionblur":
                    myImage.motionblur(blurLength);
                    break;
                default:
                    break;
            }

            new ImageWriter(outFileName, myImage.magicNumber, myImage.width, myImage.height, myImage.maxColorValue, myImage.pixelArray);
                // ^^^ yeah this is questionable
        }
        // THE IMAGE IS COMPLETE!

    }
}

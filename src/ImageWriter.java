import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImageWriter {
    public ImageWriter(File outFile, String magicNumber, int width, int height, int maxColorValue, Pixel[][] pixelArray) {
        BufferedWriter BW = null;
        try {
            BW = new BufferedWriter(new FileWriter(outFile));

            BW.write(magicNumber + "\n");
            BW.write(width + "\n" + height + "\n");
            BW.write(maxColorValue + "\n");


            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Pixel current = pixelArray[x][y];

                    BW.write(current.redValue + " ");
                    BW.write(current.greenValue + " ");
                    BW.write(current.blueValue + "\t");
                }
                BW.write("\n");
            }
            BW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//DO THE TYPE OF SEPARATORS MATTER???
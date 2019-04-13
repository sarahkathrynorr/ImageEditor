import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // read in arguments
        File inFile = new File(args[0]);
        File outFile = new File(args[1]);
        String selection = args[2];
        int blurLength = 2000;
        if (selection.equals("motionblur")) {
            String stringBlurLength = args[3];
            blurLength = Integer.parseInt(stringBlurLength);
        }

        //call FileReader class
        new ImageReader(inFile, selection, blurLength, outFile); //fix this

    }
}

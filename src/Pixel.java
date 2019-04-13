public class Pixel {
    public int grayScale;
    int redValue;
    int greenValue;
    int blueValue;

    public Pixel(int red, int green, int blue) {
        redValue = red;
        greenValue = green;
        blueValue = blue;

        grayScale = (redValue + greenValue + blueValue) / 3;
    }
}


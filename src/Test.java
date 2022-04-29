import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            BufferedImage inputImg = ImageIO.read(new File("img\\Mouse Sad.jpg"));

            BufferedImage result = processImage(inputImg);

            ImageIO.write(result, "png", new File("img\\output.png") );

            System.out.println("image created");
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
    public static BufferedImage processImage(BufferedImage image) {
        BufferedImage imageIn = image;
        BufferedImage imageOut =
                new BufferedImage(imageIn.getWidth(), imageIn.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        int[] imageInPixels = imageIn.getRGB(0, 0, width, height, null, 0, width);
        int[] imageOutPixels = new int[imageInPixels.length];
        for (int i = 0; i < imageInPixels.length; i++) {
            int alpha = (imageInPixels[i] & 0xFF000000) >> 24;
            int red = (imageInPixels[i] & 0x00FF0000) >> 16;
            int green = (imageInPixels[i] & 0x0000FF00) >> 8;
            int blue = (imageInPixels[i] & 0x000000FF) >> 0;

            // Make any change to the colors.
            if(i > imageInPixels.length / 6 && i < imageInPixels.length / 3)
            {
                blue = 0;
                green = 0;
            }

            // At last, store in output array:
            imageOutPixels[i] = (alpha & 0xFF) << 24
                    | (red & 0xFF) << 16
                    | (green & 0xFF) << 8
                    | (blue & 0xFF);

        }
        imageOut.setRGB(0, 0, width, height, imageOutPixels, 0, width);
        return imageOut;
    }
}

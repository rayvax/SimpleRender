import Camera.SimpleCamera;
import Geometry.CirclePlane;
import Geometry.Sphere;
import Light.ILight;
import Light.PointLight;
import OpticalProperties.LambertOpticalProperties;
import Scene.Scene;
import Scene.SceneObject;
import utils.Color;
import utils.Vector3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Test
{
    public static void main(String[] args)
    {
        int width = 700;
        int height = 700;

        SimpleCamera camera = new SimpleCamera(new Vector3(-5, 5, 0), -15, width, height);

        Scene scene = new Scene(camera);

        ILight[] lights = {
                new PointLight(new Vector3(7, 15, 20), new Color(1, 1, 1)),
                new PointLight(new Vector3(-7, 15, 20), new Color(1, 1, 1)),
        };

        SceneObject[] sceneObjects = {
                new SceneObject(
                        new Sphere(new Vector3(0,0,35), 5),
                        new LambertOpticalProperties(new Color(3, 3, 3))),
                new SceneObject(
                        new Sphere(new Vector3(4,4,30), 1f),
                        new LambertOpticalProperties(new Color(2, 2, 2))),
                new SceneObject(
                        new Sphere(new Vector3(0,-105,75), 100),
                        new LambertOpticalProperties(new Color(6,6,6))),
        };

        for (ILight light : lights)
            scene.AddSceneLight(light);
        for (SceneObject obj : sceneObjects)
            scene.AddSceneObject(obj);

        Color[] screenColors = scene.Render();
        int[] pixels = GetPixels(screenColors);

        BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        imageOut.setRGB(0, 0, width, height, pixels, 0, width);

//JFRAME////////////////////////////////////
        JFrame frame = new JFrame();
        frame.setSize(imageOut.getWidth(), imageOut.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyCanvas canvas = new MyCanvas(imageOut);
        frame.add(canvas);

        frame.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();

                Vector3 movement = Vector3.ZERO;
                switch( keyCode )
                {
                    case KeyEvent.VK_UP:
                        // handle up
                        movement = new Vector3(0,1,0);
                        break;
                    case KeyEvent.VK_DOWN:
                        // handle down
                        movement = new Vector3(0,-1,0);
                        break;
                    case KeyEvent.VK_LEFT:
                        // handle left
                        movement = new Vector3(1,0,0);
                        break;
                    case KeyEvent.VK_RIGHT :
                        // handle right
                        movement = new Vector3(-1,0,0);
                        break;
                }

                sceneObjects[0].Move(movement);
                canvas.ChangeImage(GetRenderedImage());
            }

            public BufferedImage GetRenderedImage()
            {
                Color[] screenColors = scene.Render();
                int[] pixels = GetPixels(screenColors);
                imageOut.setRGB(0, 0, width, height, pixels, 0, width);
                return imageOut;
            }
        });
////////////////////////////////////////////
    }

    public static void DisplayImage(BufferedImage img)
    {
        JFrame frame = new JFrame();
        frame.setSize(img.getWidth(), img.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyCanvas canvas = new MyCanvas(img);
        frame.add(canvas);

        frame.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                switch( keyCode )
                {
                    case KeyEvent.VK_UP:
                        // handle up
                        break;
                    case KeyEvent.VK_DOWN:
                        // handle down
                        break;
                    case KeyEvent.VK_LEFT:
                        // handle left
                        break;
                    case KeyEvent.VK_RIGHT :
                        // handle right
                        break;
                }
            }
        });
    }

    public static int[] GetPixels(Color[] pixelsColors)
    {
        int[] pixels = new int[pixelsColors.length];

        float max = 0;
        for (int i = 0; i < pixelsColors.length; i++)
        {
            if(pixelsColors[i].Red > max)
                max = pixelsColors[i].Red;

            if(pixelsColors[i].Green > max)
                max = pixelsColors[i].Green;

            if(pixelsColors[i].Blue > max)
                max = pixelsColors[i].Blue;
        }

        for (int i = 0; i < pixels.length; i++)
        {
//            int red =   Math.max((int)(pixelsColors[i].Red/max*255), 0);
//            int green = Math.max((int)(pixelsColors[i].Green/max*255), 0);
//            int blue =  Math.max((int)(pixelsColors[i].Blue/max*255), 0);

            int red =   (int)(pixelsColors[i].Red/max*255);
            int green = (int)(pixelsColors[i].Green/max*255);
            int blue =  (int)(pixelsColors[i].Blue/max*255);

            pixels[i] = (0xFF) << 24 //alfa
                    | (red & 0xFF) << 16
                    | (green & 0xFF) << 8
                    | (blue & 0xFF);
        }

        return pixels;
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

    public static class MyCanvas extends Canvas
    {
        private BufferedImage bufferedImage;

        public MyCanvas(BufferedImage bi)
        {
            bufferedImage = bi;
        }

        public void paint(Graphics g)
        {
            g.drawImage(bufferedImage, 0,0,this);
        }

        public void ChangeImage(BufferedImage bi)
        {
            bufferedImage = bi;
        }
    }
}

/*
                new SceneObject(
                        new Sphere(new Vector3(0,0,25), 5),
                        new LambertOpticalProperties(new Color(3, 3, 3))),
                new SceneObject(
                        new Sphere(new Vector3(4,4,22), 1f),
                        new LambertOpticalProperties(new Color(2, 2, 2))),
                new SceneObject(
                        new Sphere(new Vector3(8,8,30), 5),
                        new LambertOpticalProperties(new Color(1, 1, 1))),
 */
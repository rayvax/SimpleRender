package utils;

public class Color
{
    public float red;
    public float green;
    public float blue;

    public Color(float r, float g, float b)
    {
        red = r;
        green = g;
        blue = b;
    }

    public Color Multiply(Color other)
    {
        return new Color(red * other.red, green * other.green, blue * other.blue);
    }

    public Color Multiply(float multi)
    {
        return new Color(multi * red, multi * green, multi * blue);
    }
}

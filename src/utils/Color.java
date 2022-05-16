package utils;

public class Color
{
    public float Red;
    public float Green;
    public float Blue;

    public Color()
    {
        this(0, 0, 0);
    }

    public Color(float r, float g, float b)
    {
        Red = r;
        Green = g;
        Blue = b;
    }

    public Color Multiply(Color other)
    {
        return new Color(Red * other.Red, Green * other.Green, Blue * other.Blue);
    }

    public Color Multiply(float multi)
    {
        return new Color(multi * Red, multi * Green, multi * Blue);
    }

    public void SumSelf(Color other)
    {
        Red += other.Red;
        Green += other.Green;
        Blue += other.Blue;
    }
}

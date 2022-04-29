package utils;

public class Ray
{
    public Vector3 origin;
    public Vector3 direction;

    public int dinstMin;
    public int dinstMax;

    public Color color;

    public Ray(Vector3 origin, Vector3 direction)
    {
        this.origin = origin;
        this.direction = direction;
        dinstMin = 0;
        dinstMax = Integer.MAX_VALUE;
    }

    public void SetMaxDistance(int value)
    {
        dinstMax = value;
    }
}

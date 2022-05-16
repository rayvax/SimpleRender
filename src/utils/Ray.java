package utils;

public class Ray
{
    public Vector3 origin;
    public Vector3 direction;

    public float distMin;
    public float distMax;

    public Color color;

    public Ray(Vector3 origin, Vector3 direction, float distMin, float distMax)
    {
        this(origin, direction);
        this.distMin = distMin;
        this.distMax = distMax;
    }

    public Ray(Vector3 origin, Vector3 direction)
    {
        this.origin = origin;
        this.direction = direction.normalize();
        distMin = 0;
        distMax = Integer.MAX_VALUE;
    }

    public Vector3 GetRayEnd()
    {
        return origin.add(direction.scale(distMax));
    }
}

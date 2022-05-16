package utils;

public class VectorMath
{
    public static float GetCos(Vector3 v1, Vector3 v2)
    {
        return v1.dot(v2) / (v1.length() * v2.length());
    }
}

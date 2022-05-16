package Light;

import utils.Color;
import utils.Vector3;

public class PointLight implements ILight
{
    private Vector3 origin;
    private Color flux;

    public PointLight(Vector3 origin, Color flux)
    {
        this.origin = origin;
        this.flux = flux;
    }

    public Color GetIllumination(Vector3 point, Vector3 normal)
    {
        Vector3 pointToOrigin = origin.subtract(point);

        Color intensivity = GetIntensity(pointToOrigin);
        float lengthSquared = pointToOrigin.lengthSquared();
        float cos = pointToOrigin.dot(normal) / pointToOrigin.length(); //косинус угла между вектором к точке и нормалью

        return intensivity.Multiply(cos/lengthSquared);
    }

    public Color GetIntensity(Vector3 direction)
    {
        return flux.Multiply(1/(4*(float)Math.PI));
    }

    public Vector3 GetOrigin()
    {
        return origin;
    }
}

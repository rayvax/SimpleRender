package Light;

import utils.Color;
import utils.Vector3;

public class PointLight implements ILight
{
    private Vector3 origin;
    private Color flux;

    public Color GetIllumination(Vector3 point, Vector3 normal)
    {
        return null;
    }

    public Color GetIntensity(Vector3 direction)
    {
        return flux.Multiply(1/(float)Math.PI);
    }
}

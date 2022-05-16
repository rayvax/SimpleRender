package Scene;

import Geometry.IGeometry;
import OpticalProperties.IOpticalProperties;
import utils.Color;
import utils.Ray;
import utils.Vector3;

public class SceneObject implements IGeometry, IOpticalProperties
{
    private IGeometry geometry;
    private IOpticalProperties opticalProperties;

    public SceneObject(IGeometry geometry, IOpticalProperties opticalProperties)
    {
        this.geometry = geometry;
        this.opticalProperties = opticalProperties;
    }

    public boolean Intersect(Ray ray)
    {
        return geometry.Intersect(ray);
    }

    public Vector3 GetNormal(Vector3 point)
    {
        return geometry.GetNormal(point);
    }

    public Color GetBrightness(Color illumination)
    {
        return opticalProperties.GetBrightness(illumination);
    }

    public void Move(Vector3 movement)
    {
        geometry.Move(movement);
    }
}

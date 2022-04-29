package Scene;

import Geometry.IGeometry;
import OpticalProperties.IOpticalProperties;
import utils.Color;
import utils.Ray;

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

    public Color GetBrightness(Color illumination)
    {
        return opticalProperties.GetBrightness(illumination);
    }

}

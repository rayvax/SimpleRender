package OpticalProperties;

import utils.Color;

public class LambertOpticalProperties implements IOpticalProperties
{
    private Color diffusionCoef;

    public LambertOpticalProperties(Color diffusionCoef)
    {
        this.diffusionCoef = diffusionCoef;
    }

    public Color GetBrightness(Color illumination)
    {
        float multiplier = (float)(1/Math.PI);
        return diffusionCoef.Multiply(multiplier).Multiply(illumination);
    }
}

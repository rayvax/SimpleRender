package Light;

import utils.Color;
import utils.Vector3;

public interface ILight
{
    Color GetIllumination(Vector3 point, Vector3 normal); //ещё возращает V_L

    //F/4pi - точечный
    //F/pi - ламбертовый
    Color GetIntensity(Vector3 direction);

    Vector3 GetOrigin();
}

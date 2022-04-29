package Camera;

import utils.Ray;

public interface ICamera
{
    Ray GetLookDirection(int ix, int iy);
}

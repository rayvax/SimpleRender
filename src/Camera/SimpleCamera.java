package Camera;

import utils.Ray;
import utils.Vector3;

public class SimpleCamera implements ICamera
{
    private Vector3 leftTopPoint;
    private int cameraPosZ;
    private int resolutionX;
    private int resolutionY;
    private Vector3 cellSize;

    public SimpleCamera(Vector3 leftTopPoint, int cameraPosZ, int resolutionX, int resolutionY)
    {
        this.leftTopPoint = leftTopPoint;
        this.cameraPosZ = cameraPosZ;

        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;

        cellSize = new Vector3(
                Math.abs(2 * leftTopPoint.getX() / resolutionX),
                Math.abs(2 * leftTopPoint.getY() / resolutionY),
                0);
    }

    public Ray GetLookDirection(int ix, int iy)
    {
        Vector3 matrixPoint  = new Vector3(
                leftTopPoint.getX() + ix * cellSize.getX(),
                leftTopPoint.getY() - iy * cellSize.getY(),
                0);

        Vector3 cameraPos = new Vector3(0, 0, cameraPosZ);
        Vector3 direction = matrixPoint.subtract(cameraPos).normalize();

        return new Ray(cameraPos, direction);
    }

    public int GetResolutionX()
    {
        return resolutionX;
    }

    public int GetResolutionY()
    {
        return resolutionY;
    }
}


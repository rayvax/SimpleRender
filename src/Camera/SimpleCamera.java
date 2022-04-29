package Camera;

import utils.Ray;
import utils.Vector3;

public class SimpleCamera implements ICamera
{
    private Vector3 leftTopPoint;
    private int cameraPosZ;
    private Vector3 resolution;
    private Vector3 matrixSize;

    public SimpleCamera(Vector3 leftTopPoint, int cameraPosZ, Vector3 resolution)
    {
        this.leftTopPoint = leftTopPoint;
        this.cameraPosZ = cameraPosZ;
        this.resolution = resolution;

        matrixSize = new Vector3(
                2 * leftTopPoint.getX() / resolution.getX(),
                2 * leftTopPoint.getY() / resolution.getY(),
                0);
    }

    public Ray GetLookDirection(int ix, int iy)
    {
        Vector3 matrixPoint  = new Vector3(
                ix * matrixSize.getX() - leftTopPoint.getX(),
                -iy * matrixSize.getY() + leftTopPoint.getY(),
                -cameraPosZ);

        Vector3 cameraPos = new Vector3(0, 0, -cameraPosZ);
        Vector3 direction = cameraPos.subtract(matrixPoint).normalize();

        return new Ray(cameraPos, direction);
    }
}


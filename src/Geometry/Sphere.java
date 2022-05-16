package Geometry;

import utils.Ray;
import utils.Vector3;

import static utils.MathConstants.EPSILON;

public class Sphere implements IGeometry
{
    private Vector3 center;
    private float radius;

    public Sphere(Vector3 center, float radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public boolean Intersect(Ray ray)
    {
        Vector3 p = ray.origin.subtract(center);

        float discremenant =(float) Math.pow(p.dot(ray.direction), 2) - p.dot(p) + radius*radius;

        int discrSign = Float.compare(discremenant, 0); //сравнение дискриминанта с нулём

        if(discrSign < 0)
            return false;

        float intersectDist;
        if(discrSign == 0)
        {
            intersectDist = p.scale(-1).dot(ray.direction);
        }
        else
        {
            intersectDist = p.scale(-1).dot(ray.direction) - (float)Math.sqrt(discremenant);

            if(intersectDist <= EPSILON)
                intersectDist = p.scale(-1).dot(ray.direction) + (float)Math.sqrt(discremenant);
        }

        if(intersectDist <= EPSILON)
            return false;

        if(intersectDist > ray.distMin && intersectDist < ray.distMax)
        {
            ray.distMax = intersectDist;
            return true;
        }

        return false;
    }

    public Vector3 GetNormal(Vector3 point)
    {
        return point.subtract(center).normalize();
    }

    public void Move(Vector3 movement)
    {
        center = center.add(movement);
    }
}

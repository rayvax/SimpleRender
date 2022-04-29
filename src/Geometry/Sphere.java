package Geometry;

import utils.Ray;
import utils.Vector3;

public class Sphere implements IGeometry
{
    private Vector3 center;
    private int radius;

    public Sphere(Vector3 center, int radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public boolean Intersect(Ray ray)
    {
        Vector3 p = ray.origin.subtract(center);

        float discremenant =(float) Math.pow(p.dot(ray.direction), 2) - p.dot(p);

        float intersectDinst = p.scale(-1).dot(ray.direction) - Math.sqrt(discremenant)

        return false;
    }
}

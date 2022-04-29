package Geometry;

import utils.Ray;

public interface IGeometry
{
    boolean Intersect(Ray ray); //меняем ray.dinstMax и возвращаем нормаль
}

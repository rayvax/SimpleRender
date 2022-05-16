package Geometry;

import utils.Ray;
import utils.Vector3;

public interface IGeometry
{
//    public static class IntersectionResult
//    {
//        boolean hasIntersected;
//        Vector3 normal;
//
//        private IntersectionResult(boolean hasIntersected, Vector3 normal)
//        {
//            this(hasIntersected);
//            this.normal = normal;
//        }
//
//        private IntersectionResult(boolean hasIntersected)
//        {
//            this.hasIntersected = hasIntersected;
//        }
//
//        public static IntersectionResult HasIntersected(Vector3 normal)
//        {
//            return new IntersectionResult(true, normal);
//        }
//
//        public static IntersectionResult NotIntersected()
//        {
//            return new IntersectionResult(false);
//        }
//    }

    boolean Intersect(Ray ray); //меняем ray.dinstMax и возвращаем нормаль
    Vector3 GetNormal(Vector3 point);
    void Move(Vector3 movement);
}

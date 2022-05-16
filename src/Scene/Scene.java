package Scene;

import Camera.SimpleCamera;
import Light.ILight;
import utils.Color;
import utils.Ray;
import utils.Vector3;

import java.util.LinkedList;
import java.util.List;

public class Scene
{
    private SimpleCamera camera;
    private List<SceneObject> sceneObjects;
    private List<ILight> lights;

    private final static Color BACKGROUND_COLOR = new Color(1.5e-4f,2e-4f,1.5e-4f);

    public Scene(SimpleCamera camera)
    {
        this.camera = camera;
        sceneObjects = new LinkedList<>();
        lights = new LinkedList<>();
    }

    public void AddSceneLight(ILight light)
    {
        lights.add(light);
    }

    public void AddSceneObject(SceneObject obj)
    {
        sceneObjects.add(obj);
    }

    public Color[] Render()
    {
        int resX = camera.GetResolutionX();
        int resY = camera.GetResolutionY();
        Color[] result = new Color[resX * resY];

        for (int i = 0; i < result.length; i++)
        {
            result[i] = new Color();
        }

        for (int ix = 0; ix < resX; ix++)
        {
            for (int iy = 0; iy < resY; iy++)
            {
                Ray cameraLook = camera.GetLookDirection(ix, iy);
                SceneObject intersectObj = FindIntersectionWithSceneObjects(cameraLook);
                int pointCount = iy * resX + ix;

                if(intersectObj == null)
                {
                    result[pointCount] = BACKGROUND_COLOR;
                    continue;
                }

                Vector3 intersectPoint = cameraLook.GetRayEnd();

                Color brightness;
                for (ILight light : lights)
                {
                    brightness =GetBrightness(intersectObj, light, intersectPoint);
                    result[pointCount].SumSelf(brightness);
                }
            }
        }

        return result;
    }

    //Returns nearest intersected object
    private SceneObject FindIntersectionWithSceneObjects(Ray ray)
    {
        SceneObject resultObj = null;

        for (SceneObject sceneObject : sceneObjects)
        {
            if (sceneObject.Intersect(ray))
                resultObj = sceneObject;
        }

        return resultObj;
    }

    private boolean IntersectsWithSceneObject(Ray ray)
    {
        for (SceneObject sceneObject : sceneObjects)
        {
            if (sceneObject.Intersect(ray))
                return true;
        }

        return false;
    }

    private Color GetBrightness(SceneObject sceneObject, ILight light, Vector3 point)
    {
        Vector3 toLight = light.GetOrigin().subtract(point);
        Ray pointToLight = new Ray(point, toLight, 0, toLight.length());

        if(!IntersectsWithSceneObject(pointToLight)) //not in shadow
        {
            Vector3 geometryNormal = sceneObject.GetNormal(point);
            Color illumination = light.GetIllumination(point, geometryNormal);

            return sceneObject.GetBrightness(illumination);
        }

        return new Color();
    }
}

package Scene;

import Camera.ICamera;
import Light.ILight;
import utils.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene
{
    private ICamera camera;
    private List<SceneObject> sceneObjects;
    private ILight light;

    public Scene(ICamera camera, ILight light)
    {
        this.camera = camera;
        this.light = light;
        sceneObjects = new LinkedList<SceneObject>();
    }

    public void AddSceneObject(SceneObject obj)
    {
        sceneObjects.add(obj);
    }

    public Color[] Render()
    {
        return null;
    }
}

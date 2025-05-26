package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private Map<Class<?>, Object> components = new HashMap<>();

    public String getID() {
        return ID.toString();
    }

    public <T> void addComponent(T component) {
        components.put(component.getClass(), component);
    }

    public <T> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }
    public <T> boolean hasComponent(Class<T> componentClass) {
        return components.containsKey(componentClass);
    }

    public <T> void removeComponent(Class<T> componentClass) {
        components.remove(componentClass);
    }

}

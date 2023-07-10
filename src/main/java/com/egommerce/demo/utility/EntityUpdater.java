package com.egommerce.demo.utility;

import org.springframework.stereotype.Component;
import com.egommerce.demo.annotation.ExcludeUpdate;

import java.lang.reflect.Field;

@Component
public class EntityUpdater {
    public <T> T updateEntity(T entity, T updates) {
        Field[] fields = updates.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(updates);

                if (value != null && !field.isAnnotationPresent(ExcludeUpdate.class)) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }
}

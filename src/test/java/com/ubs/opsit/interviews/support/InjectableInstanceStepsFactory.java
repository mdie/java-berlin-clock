package com.ubs.opsit.interviews.support;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.plexus.util.ReflectionUtils;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.opsit.interviews.BerlinClockConverter;
import com.ubs.opsit.interviews.HelloWorld;
import com.ubs.opsit.interviews.HelloWorldImpl;
import com.ubs.opsit.interviews.TimeConverter;

/**
 * @author mdie
 * 
 * overridden implementation of InstanceStepsFactory class 
 * injects new instance of classes into property (using reflection) of setpsInstances
 * 
 * you can extend list of classes (to be injected) by putting into injectionMap
 */
public class InjectableInstanceStepsFactory extends InstanceStepsFactory {
	
    private static final Logger LOG = LoggerFactory.getLogger(InjectableInstanceStepsFactory.class);
    
    private final static Map<Class<?>, Class<?>> injectionMap =  new HashMap<Class<?>, Class<?>>();
    static {
    	injectionMap.put(HelloWorld.class, HelloWorldImpl.class);
    	injectionMap.put(TimeConverter.class, BerlinClockConverter.class);
    }

    public InjectableInstanceStepsFactory(Configuration configuration, Object... stepsInstances) {
		super(configuration, stepsInstances);
		for (Object instance: stepsInstances) {
	    	injectPropertyIntoInstanceStep(instance);
		}
    }

    private void injectPropertyIntoInstanceStep(Object stepInstance) {
    	Iterator<Field> fields = ReflectionUtils.getFieldsIncludingSuperclasses(stepInstance.getClass()).iterator();
    	while (fields.hasNext()) {
			Field field = (Field) fields.next();
			findFieldTypeInMapAndInjectInstanceIfFound(field, stepInstance);
		}
	}
    
    private void findFieldTypeInMapAndInjectInstanceIfFound(Field field, Object stepInstance) {
    	Objenesis objenesis = new ObjenesisStd();
    	Iterator<Class<? extends Object>> iterPropertiesTypes = injectionMap.keySet().iterator();
    	while (iterPropertiesTypes.hasNext()) {
			Class<? extends Object> typeOfPropertyToSet = (Class<? extends Object>) iterPropertiesTypes.next();
			if (typeOfPropertyToSet.equals(field.getType())) {
				try {
					ReflectionUtils.setVariableValueInObject(stepInstance, field.getName(), 
							objenesis.newInstance(injectionMap.get(typeOfPropertyToSet)));
					LOG.info(String.format("I have just injected the new instance of %s class into instance (%s) of %s (by setting property %s).", 
							injectionMap.get(typeOfPropertyToSet), stepInstance, stepInstance.getClass(), field.getName()));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
    }
}

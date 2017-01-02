package borisov.spring.internals.quater;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

public class RetentionAnnotationBeanPostProccessor implements BeanPostProcessor{

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		final Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			if(annotation!=null) {
				int min = annotation.min();
				int max = annotation.max();
				Random random = new Random();
				int i = min + random.nextInt(max-min);
				field.setAccessible(true);
				ReflectionUtils.setField(field, bean, i);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		return bean;
	}

}

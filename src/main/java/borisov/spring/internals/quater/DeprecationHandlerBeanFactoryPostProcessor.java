package borisov.spring.internals.quater;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			final BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			final String beanClassName = beanDefinition.getBeanClassName();
			try {
				final Class<?> beanClass = Class.forName(beanClassName);
				final DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
				if (annotation != null) {
					beanDefinition.setBeanClassName(annotation.newImpl().getName()); // replace bean definition with new class instead deprecate class
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

package borisov.spring.base;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
	private Map<String, Class<?>> map = new HashMap<>();
	private ProfilingController controller = new ProfilingController();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = bean.getClass();
		if (beanClass.isAnnotationPresent(Profiling.class)) {
			map.put(beanName, beanClass);
		}
		return bean;
	}

	public ProfilingHandlerBeanPostProcessor() throws Exception {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		mBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = map.get(beanName);
		if (beanClass != null) {
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
					(Object proxy, Method method, Object[] args) -> {
						if (controller.isEnabled()) {
							System.out.println("Профилирую ....");
							long before = System.nanoTime();
							Object retVal = method.invoke(bean, args);
							long after = System.nanoTime();
							System.out.println("Method profiling time : " + (after - before));
							System.out.println("Все.");
							return retVal;
						} else {
							return method.invoke(bean, args);
						}
					});
		}
		return bean;
	}

}

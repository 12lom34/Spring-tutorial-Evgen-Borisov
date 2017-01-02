package borisov.spring.base;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//		context.getBean(Quoter.class).sayQuater();
		context.close();
	}

}

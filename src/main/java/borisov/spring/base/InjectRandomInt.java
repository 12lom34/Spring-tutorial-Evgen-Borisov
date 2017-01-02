package borisov.spring.base;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
	int min();
	int max();
}

package borisov.spring.internals.quater;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
	int min();
	int max();
}

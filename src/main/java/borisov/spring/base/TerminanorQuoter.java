package borisov.spring.base;

import javax.annotation.PostConstruct;

@Profiling
public class TerminanorQuoter implements Quoter {
	@InjectRandomInt(min = 2, max = 7)
	private int repeat;

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		System.out.println("Phase 2 : " + repeat);
	}

	public TerminanorQuoter() {
		System.out.println("Phase 1 : " + repeat);
	}

	@Override
	@PostProxy
	public void sayQuater() {
		System.out.println("Phase 3 : " + repeat);
		for (int i = 0; i < repeat; i++) {
			System.out.println("message : " + message);
		}
	}

}

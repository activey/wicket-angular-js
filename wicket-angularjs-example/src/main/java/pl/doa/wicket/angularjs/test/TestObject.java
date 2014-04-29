package pl.doa.wicket.angularjs.test;

import java.io.Serializable;

public class TestObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String test1;

	private String test2;

	public TestObject() {
		
	}
	
	public TestObject(String test1, String test2) {
		this.test1 = test1;
		this.test2 = test2;
	}
	
	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}

}

package edu.louisville.cecs640.assignment3_3;

/* Assignment3_Bean class used as JavaBean.
 * This program will perform either an add or subtract operation on two float variables.
 */

public class Assignment3_Bean {
	// Declare private variables.
	private String operation;
	private float var_1;
	private float var_2;
	
	/* Method to perform add or subtract.
	 * Return value defaults to 0 if operation is not add or subtract.
	 */
	public float calculate() {
		if (operation.equalsIgnoreCase("add")) {
			return var_1 + var_2;
		}
		else if (operation.equalsIgnoreCase("subtract")) {
			return var_1 - var_2;
		}
		else {
			System.out.println("Unexpected operation string");
			return 0;
		}
	}
	// Second calculate implementation.
	public float calculate(float one, float two, String op) {
		setVar1(one);
		setVar2(two);
		setOperation(op);
		return calculate();
	}
	// operation getter
	public String getOperation() {
		return operation;
	}
	// operation setter
	public void setOperation(String operation) {
		this.operation = operation;
	}
	// var_1 getter
	public float getVar1() {
		return var_1;
	}
	// var_1 setter
	public void setVar1(float var_1) {
		this.var_1 = var_1;
	}
	// var_2 getter
	public float getVar2() {
		return var_2;
	}
	// var_2 setter
	public void setVar2(float var_2) {
		this.var_2 = var_2;
	}
}

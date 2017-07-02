package com.cube.rule;

public class Expression {
	int id;
	String LHS;
	String operator;
	String RHS;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLHS() {
		return LHS;
	}
	public void setLHS(String lHS) {
		LHS = lHS;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRHS() {
		return RHS;
	}
	public void setRHS(String rHS) {
		RHS = rHS;
	}
}

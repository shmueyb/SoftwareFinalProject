package edu.umn.csci5801.studentrecord.transcript;

public enum Semester {
	SPRING, SUMMER, FALL;

	public Semester next() {
		return this.ordinal() < Semester.values().length - 1 ? Semester.values()[this.ordinal() + 1] : null;
	}
}

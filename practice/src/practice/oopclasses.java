package practice;

import java.util.Arrays;

public class oopclasses {

	public static void main(String[] args) {
	
		Student [] students = new Student[5];
		Student Arenaho = new Student();
		Arenaho.name= "Arenaho";
		Arenaho.studnum = 13;
		Arenaho.marks = 4.0f;
		System.out.println(Arenaho.studnum);
		System.out.println(Arenaho.name);
		System.out.println(Arenaho.marks);

	}
}
	// create a class 
	class Student{
		int  studnum ;
		String  name ;
		float marks ;
	}



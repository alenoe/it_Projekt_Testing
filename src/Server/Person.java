package Server;

import java.io.Serializable;

public class Person implements Serializable{
	
	private String name;
	private int alter;
	
	public Person(String name, int alter){
		this.name = name;
		this.alter = alter;
	}
	
	public String toString(){
		return "Name: "+this.name+" Alter: "+this.alter;
	}

}

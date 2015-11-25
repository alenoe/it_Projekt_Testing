package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Dice.*;

public class Dice {
	
	private ArrayList<String> face = new ArrayList<String>();
	private Image img;
	private Image img2;
	
	private String attack = "application.Dice.dice_attack.png";
	private String heal = "application.Dice.dice_heart.png";
	private String one = "application.Dice.dice_one.png";
	private String two = "application.Dice.dice_two.png";
	private String three = "application.Dice.dice_three.png";
	
	private String attackOK = "application.Dice.dice_attack_ok.png";
	private String healOK = "application.Dice.dice_heart_ok.png";
	private String oneOK = "application.Dice.dice_one_ok.png";
	private String twoOK = "application.Dice.dice_two_ok.png";
	private String threeOK = "application.Dice.dice_three_ok.png";
	private ArrayList<String> faceOK = new ArrayList<String>();
	
	int k = 0;
	
	public	Dice(){
	this.face.add(attack);
	this.face.add(heal);
	this.face.add(one);
	this.face.add(two);
	this.face.add(three);
	
	
	this.faceOK.add(attackOK);
	this.faceOK.add(healOK);
	this.faceOK.add(oneOK);
	this.faceOK.add(twoOK);
	this.faceOK.add(threeOK);
		
		
	}
	
	public Image roll(){
		Random rand = new Random();
		int i = rand.nextInt(5);
		k = i;
	return this.img = new Image(getClass().getResourceAsStream(face.get(i)));
	
	}
	public Image keepFace(){
		 faceOK.get(k);
		return this.img2 = new Image(getClass().getResourceAsStream(faceOK.get(k)));
	}
	
	public Image removeCheck(){
		return img = new Image(getClass().getResourceAsStream(face.get(k)));
	}
	public String upFace(){
		return face.get(k);
	}
}

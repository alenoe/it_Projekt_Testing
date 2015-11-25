package gameLogic;

import java.util.ArrayList;
import java.util.List;

import Server.UserM;
import interfaces.Observer;
import interfaces.Subject;

public class PlayerInGameM implements Subject{
	
	//Magic Numbers m�ssen �ber SL und config geladen werden.
	
	public static final int MAX_HP = 10;
	public static final int MAX_VP = 20;
	
	//Klassenvariablen
	
	private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX= new Object();
	
	private UserM user;		//soll ein UserM Objekt enthalten.
	private int avatar;
	private int healthPoints;
	private int victoryPoints;
	private boolean position;
	private int playerNumber;
	
	//Constructor wird vom Server aufgerufen und �bergibt die Spielvariablen.
	
	public PlayerInGameM(UserM user, int avatar, int playerNumber){
		this.user = user;
		this.avatar = avatar;
		this.healthPoints = MAX_HP;
		this.victoryPoints = 0;
		this.position = false;
		this.playerNumber = playerNumber;
	}
	
	//getter und setter methoden
	
	public UserM getUserName(){	//setzt getUserName() implementation in UserM vorraus.
		return this.user;
	}

	public int getAvatar(){
		return this.avatar;
	}
	
	public void setAvatar(int avatar){
		this.avatar = avatar;
	}
	
	public int getHealthPoints(){
		return this.healthPoints;
	}
	
	public void getHealed(int heal){
		if(this.healthPoints + heal > MAX_HP){
			this.healthPoints = MAX_HP;
		} else {
			this.healthPoints = this.healthPoints + heal;
		}
	}
	
	public void setHealthPoints(int hp){
		this.healthPoints = hp;
	}
	
	public void setDamage(int damage){
		if(this.healthPoints - damage <= 0){
			this.healthPoints = 0;
			this.loose();
		} else {
			this.healthPoints = this.healthPoints - damage;			
		}
	}
	
	public int getVictoryPoints(){
		return this.victoryPoints;
	}
	
	public void setVictoryPoints(int vp){
		if(this.victoryPoints + vp >= MAX_VP){
			this.victoryPoints = MAX_VP;
		} else {
			this.victoryPoints = this.victoryPoints + vp;
		}
	}
	
	public boolean getPosition(){
		return this.position;
	}
	
	public void setPositionTokyo(){
		this.position = true;
	}
	
	public void setPositionNotTokyo(){
		this.position = false;
	}
	
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	public void setPlayerNumber(int playerNr){
		this.playerNumber = playerNr;
	}
	

	//diese Methoden m�ssen noch mit dem Server implementiert werden.
	
	public void trashPlayer(){
		
	}
	
	public void win(){
		
	}
	
	public void loose(){
		
	}
	
	//__________________________________________________________________
	//Observerimplementation
	//http://www.journaldev.com/1739/observer-design-pattern-in-java-example-tutorial

	@Override
	public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
        if(!observers.contains(obj)) observers.add(obj);
        }
	}

	@Override
	public void unregister(Observer obj) {
        synchronized (MUTEX) {
        observers.remove(obj);
        }
	}

	@Override
	public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update(this, this);
        }
	}

	@Override
	public Object getUpdate(Observer obj) {
        return this;

	}
	
    //method to post message to the topic
    public void postMessage(String msg){
        System.out.println("Message Posted to Topic:"+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }
    //_______________________________________________________________________
}

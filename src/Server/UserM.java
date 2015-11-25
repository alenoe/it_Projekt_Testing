package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class UserM implements Serializable{

	private String username;
	private UserM um;
	
	
	
	public UserM(String username){
			this.setUsername(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String toString(){
		return "username: " + this.username;
	}
	
}

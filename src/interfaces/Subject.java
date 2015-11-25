package interfaces;

import java.io.Serializable;

public interface Subject extends Serializable {
	
	public void register(Observer obj);
	public void unregister(Observer obj);
	public void notifyObservers();
	public Object getUpdate(Observer obj);

}

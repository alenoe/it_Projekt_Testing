package interfaces;

import java.io.Serializable;

public interface Observer extends Serializable {
	
	public void update(Subject o, Object arg);
	public void setSubject(Subject sub);

}

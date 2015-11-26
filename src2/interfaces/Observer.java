package interfaces;

public interface Observer {
	
	public void update(Subject o, Object arg);
	public void setSubject(Subject sub);

}

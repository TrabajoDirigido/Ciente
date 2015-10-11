
public interface IController {
	public void register(String ip, String name1, String name2);
	public void unregister();
	public int getPort();
	public String getIp();
	public void socketMessage(String message);
	public void statusMessage(String message);
	public void disposeFrame();
}

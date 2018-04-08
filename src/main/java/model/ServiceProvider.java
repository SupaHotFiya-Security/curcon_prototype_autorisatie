package model;

public class ServiceProvider {
	
	
	private static CursusService cursusService = new CursusService();
	
	public static CursusService getCursusService() {
		return cursusService;
	}
	

}
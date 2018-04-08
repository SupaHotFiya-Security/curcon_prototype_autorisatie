package model;

import java.util.ArrayList;

import persistence.CursusDAO;

public class CursusService {

	private CursusDAO cursusDAO = new CursusDAO();

	public ArrayList<Cursus> getAllCursussen() {
		return cursusDAO.GetallCursussen();
	}
	
	public boolean addCursus(Cursus c){
		System.out.println("adding"+c.getId());
		 return cursusDAO.addCursus(c);
	}
	public boolean deleteCursus(Cursus c){
		 return cursusDAO.deleteCursus(c);
	}
	
	
	
	
}

package model;

import java.util.ArrayList;

import persistence.CursusDAO;

public class CursusService {

	private CursusDAO cursusDAO = new CursusDAO();

	public ArrayList<Cursus> getAllCursussen() {
		return cursusDAO.GetallCursussen();
	}
	
	
	
	
}

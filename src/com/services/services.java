package com.services;

import java.util.ArrayList;

import com.DAO.DAO;

public class services {
	public void addKeys(String public_key, String private_key, String modulo, String p, String q, String dmp1, String dmq1, String coeff)
	{
		DAO dao = new DAO();
		dao.addKeys(public_key,private_key, modulo, p, q, dmp1, dmq1, coeff);
	}
	
	public ArrayList<String> getPrivateKey(String public_key, String modulo)
	{
		DAO dao = new DAO();
		return dao.getPrivateKey(public_key, modulo);
	}

}

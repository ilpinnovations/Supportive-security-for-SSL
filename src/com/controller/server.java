package com.controller;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.script.*;

import com.google.gson.Gson;
import com.services.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.RSA;

import java.io.UnsupportedEncodingException;

/**
 * Servlet implementation class server
 */
//@WebServlet("/server")
public class server extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public server() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = null;
		action = request.getParameter("action");	
		//System.out.println("action is :" + action);
		
		//Server side key generation
		if(action==null)
		{
			System.out.println("null action");
		}
		else if(action.equals("clientcall"))
		{
			System.out.println("entered client call");
			int bitLength = 2048;
			SecureRandom r = new SecureRandom();
			
		    BigInteger p_server = new BigInteger(bitLength/2, 100, r);
		    BigInteger q_server = new BigInteger(bitLength/2, 100, r);
		    BigInteger n_server = p_server.multiply(q_server);
		    BigInteger m_server = (p_server.subtract(BigInteger.ONE)).multiply(q_server.subtract(BigInteger.ONE));
		    BigInteger e_server = new BigInteger("17");
		    while (m_server.gcd(e_server).intValue() > 1) {
		    	e_server = e_server.add(new BigInteger("100"));
		    }
//		    BigInteger e_server = BigInteger.probablePrime(bitLength/2, r);
//		    while (m_server.gcd(e_server).compareTo(BigInteger.ONE)>0 && e_server.compareTo(m_server)<0) {
//		      e_server.add(BigInteger.ONE);
//		    }
		    BigInteger d_server = e_server.modInverse(m_server);
		    BigInteger dmp1_server = d_server.mod(p_server.subtract(BigInteger.ONE));
		    BigInteger dmq1_server = d_server.mod(q_server.subtract(BigInteger.ONE));
		    BigInteger coeff_server = q_server.modInverse(p_server);
		    
		    //printing server related data to console
		   
		    
		    //String plain = "560e5408b023366c91d410ced068ddda0a5831afe5e266204149b4f81a82f7ad58ca91aac17b4ac3995a1d96a5fd5d02b276de5b30202de7eaf8cbfda1421ce3";
//		    RSA rs =new RSA();
//		    byte [] encrypted = rs.encrypt(plain.getBytes(), e_server, n_server);
//		    System.out.println("encrypted##@@$$  :" +encrypted);
//		    byte [] decrypted = rs.decrypt(encrypted, d_server, n_server);
//		    System.out.println("decrypted##@@$$  :" + decrypted);
//		   // System.out.println("Decrypting Bytes##$$@@  : " + bytesToString(decrypted));
//	        System.out.println("Decrypted String##$$@@  : " + new String(decrypted));
		   // byte [] encrypted = 
		    
		    String e_string = e_server.toString();
		    BigInteger e_toHex = new BigInteger(e_string, 10);
		    String e_hexString = e_toHex.toString(2);
		    
		    
		    String d_string = d_server.toString();
		    BigInteger d_toHex = new BigInteger(d_string, 10);
		    String d_hexString = d_toHex.toString(16);
		   
		    
		    String n_string = n_server.toString();
		    BigInteger n_toHex = new BigInteger(n_string, 10);
		    String n_hexString = n_toHex.toString(16);
		    
		    
		    String p_string = p_server.toString();
		    BigInteger p_toHex = new BigInteger(p_string, 10);
		    String p_hexString = p_toHex.toString(16);
		    
		    
		    String q_string = q_server.toString();
		    BigInteger q_toHex = new BigInteger(q_string, 10);
		    String q_hexString = q_toHex.toString(16);
		    
		    
		    String dmp1_string = dmp1_server.toString();
		    BigInteger dmp1_toHex = new BigInteger(dmp1_string, 10);
		    String dmp1_hexString = dmp1_toHex.toString(16);
		    
		    
		    String dmq1_string = dmq1_server.toString();
		    BigInteger dmq1_toHex = new BigInteger(dmq1_string, 10);
		    String dmq1_hexString = dmq1_toHex.toString(16);
		    
		    
		    String coeff_string = coeff_server.toString();
		    BigInteger coeff_toHex = new BigInteger(coeff_string, 10);
		    String coeff_hexString = coeff_toHex.toString(16);
		    
		    
		    
//		    System.out.println("p_server :" + p_hexString);
//		    System.out.println("q_server :" + q_hexString);
//		    System.out.println("n_server :" + n_hexString);
//		   // System.out.println("m_server :" + m_hex);
//		    System.out.println("e_server :" + e_hexString);
//		    System.out.println("d_server :" + d_hexString);
//		    System.out.println("dmp1_server :" + dmp1_hexString);
//		    System.out.println("dmq1_server : " + dmq1_hexString);
//		    System.out.println("coeff_server :" + coeff_hexString);
		    
		    //Storing key pair data into database
		    services serv = new services();
		   serv.addKeys(e_hexString, d_hexString, n_hexString,p_hexString, q_hexString, dmp1_hexString,dmq1_hexString,coeff_hexString);
		    
		    
		    
//		    System.out.println("Server public key is :" + e_hexString);//Printing server public key in hexadecimal form.
//		    System.out.println("Server private key is :" + d_hexString);//Printing server private key in hexadecimal from.
//		    System.out.println("Server modulo is :" + n_hexString);
//		    
		    JSONObject obj = new JSONObject();

		      obj.put("n", n_hexString);
		      obj.put("e", e_hexString);
		      response.getWriter().print(obj);
		     // out.print(obj);
	      
		      System.out.println("server modulus : (BIG INT) :"+ n_server);
		      System.out.println("server public key(BIG INT) : "+ e_server);
		      System.out.println("server private key(BIG INT): "+ d_server);
		      System.out.println("Server P                   : " + p_server);
		      System.out.println("Server Q                   : " + q_server);
		      System.out.println("Server dmp1                : " + dmp1_server);
		      System.out.println("Server dmq1                : " + dmq1_server);
		      System.out.println("Server coeff               : " + coeff_server);
		      
//		      System.out.println("server modulus (HEX) : "+ n_hexString);
//		      System.out.println("server public key(HEX) : "+ e_hexString);		     
//		      System.out.println("server priavte key(HEX) : "+ d_hexString);
		      
		    
		}
		
	    	
		if (action.equals("server")) {
			//Cient related data#######################
			String ciphertext = request.getParameter("ciphertext");
			//String plaintext = request.getParameter("plaintext");
			String modulus = request.getParameter("n");
			//String decrypted = request.getParameter("decrypted");
			String privateExponent = request.getParameter("d");
			String publicExponent = request.getParameter("e");
			String P = request.getParameter("p");
			String Q = request.getParameter("q");
			String DP = request.getParameter("dmp1");
			String DQ = request.getParameter("dmq1");
			String COEFF = request.getParameter("coeff");
			
			
			//Server related data######################
			String DECiphertext = request.getParameter("double_encryption");
			String server_public_key = request.getParameter("server_key");
			String server_modulo = request.getParameter("server_modulo");
			
			//System.out.println("recieved");
			//retrieving server related data from database
			services serv = new services();
			String server_private_key = null;
			String server_p = null;
			String server_q = null;
			String server_dmp1 = null;
			String server_dmq1 = null;
			String server_coeff = null;
			//System.out.println("Calling services class");
			ArrayList<String> serverInfo = serv.getPrivateKey(server_public_key,server_modulo);
			if(serverInfo.size() == 0)
			{
				System.out.println("Invalid server key..!");
			}
			else
			{
			//System.out.println("Called services class");
			int i = 0;
			for(i=0; i<serverInfo.size();i++)
			{
				//System.out.println("inside loop");
				server_private_key = serverInfo.get(0);
				server_p = serverInfo.get(1);
				server_q = serverInfo.get(2);
				server_dmp1 = serverInfo.get(3);
				server_dmq1 = serverInfo.get(4);
				server_coeff = serverInfo.get(5);
			}
			
//			System.out.println("recieved public key :" + server_public_key);
//			System.out.println("recieved private key :" + server_private_key);
//			System.out.println("recieved modulo :" + server_modulo);
//			System.out.println("recived DECipher text :" + DECiphertext);
//			System.out.println("recieved p :" + server_p);
//			System.out.println("recieved q :" + server_q);
//			System.out.println("recieved dmp1 :" + server_dmp1);
//			System.out.println("recieved dmq1 :" + server_dmq1);
//			System.out.println("recieved coeff :" + server_coeff);
			
			BigInteger n_server = new BigInteger(server_modulo, 16);
			BigInteger p_server = new BigInteger(server_p, 16);
			BigInteger q_server = new BigInteger(server_q, 16);
			BigInteger dmp1_server = new BigInteger(server_dmp1, 16);
			BigInteger dmq1_server = new BigInteger(server_dmq1, 16);
			BigInteger coeff_server = new BigInteger(server_coeff, 16);
			BigInteger e_server = new BigInteger(server_public_key, 16);
			BigInteger d_server = new BigInteger(server_private_key, 16);
			
			
			System.out.println("recieved modulus (BIG INT)    :" + n_server);
			System.out.println("recieved public key (BIG INT  :" + e_server);
			System.out.println("recieved private key (BIG INT :" + d_server);
			System.out.println("recieved P                    :" + p_server);
			System.out.println("recieved Q                    :" + q_server);
			System.out.println("recieved dmp1                 :" + dmp1_server);
			System.out.println("recieved dmq1                 :" + dmq1_server);
			System.out.println("recieved ceff                 :" + coeff_server);
			
			
			
			
			//server encryption
			
			
			
			//server decryption
			//#1
			BigInteger c1 = new BigInteger(DECiphertext, 16);
			
			
			//#2
			RSA rsa1 = new RSA();
			BigInteger m1 = rsa1.RSADoPrivate(c1, n_server, p_server, q_server, d_server, dmp1_server, dmq1_server, coeff_server);			
			System.out.println("m1 :    " + m1);
			
			//#3			
			String result1 = rsa1.pkcs1unpad3(m1, (n_server.bitLength()+7)>>3, ciphertext  );
			
			System.out.println("server decryted value :" + result1);
			if(result1.equals(ciphertext)){
				System.out.println("First decryption is successfully completed");
			}
			else
			{
				System.out.println("First decryption is unsuccessfull");
			}
			
			//Trying to store string value to BigInteger
			//Hexadecimal string value is changing to decimal BigInteger for RSA decryption.
			//BigInteger cpr = new BigInteger(ciphertxt,16);
			BigInteger n = new BigInteger(modulus, 16);
			BigInteger p = new BigInteger(P, 16);
			BigInteger q = new BigInteger(Q, 16);
			BigInteger d = new BigInteger(privateExponent, 16);
			BigInteger e = new BigInteger(publicExponent, 16);
			BigInteger dmp1 = new BigInteger(DP, 16);
			BigInteger dmq1 = new BigInteger(DQ,16);
			BigInteger coeff = new BigInteger(COEFF,16);
			System.out.println("public exponent : "+ publicExponent);
			System.out.println("");
			//Client Data Decryption

			// #1
			BigInteger c = new BigInteger(result1, 16);
			System.out.println("Converted bginteger value :" + c);
			
			
			// #2
			RSA rsa = new RSA();
			BigInteger m = rsa.RSADoPrivate(c, n, p, q, d, dmp1, dmq1, coeff);
			System.out.println("m    :" +m);
			
			// #3
			String result = rsa.pkcs1unpad2(m, (n.bitLength()+7)>>3);
			
			System.out.println("The decrypted value is ### : " + result);
			
		

			
			
			
		
			
			//System.out.println("BINARY :" + bigIntString);
			//System.out.println();
			//BigInteger bigInteger = new BigInteger(bigIntString, 2);
			//System.out.println("DECIMAL :" + bigInteger);
			//encryption
			
			
			
			
			
			
			
//			byte [] encrypted =(new BigInteger (plaintext.getBytes())).modPow(e, n).toByteArray();
//			System.out.println("Encrypted text is :");
//			String test1 = null;
//			for(byte b : encrypted)
//			{
//				test1 += Byte.toString(b);
//			}
//			System.out.println(test1);
//			//BigInteger cip = new BigInteger(encrypted, 16);
//			//Decryption
//			byte [] decrypted = (new BigInteger(encrypted)).modPow(d, n).toByteArray();
//			
//			System.out.println("Decrypted text in bytes is :");
//			String test = null;
//			for(byte b : decrypted)
//			{
//				test += Byte.toString(b);
//			}
//			System.out.println(test);
//			System.out.println("The decrypted string is :"+ new String(decrypted));
			//String decrypted = cip.modPow(newPrivateExp, newModulo).toString();
			
			//String test = null;
//			for(byte b : decrypted)
//			{
//				test += Byte.toString(b);
//			}
//			//System.out.println("The decrypted message is : " + decrypted);	
//			
			
			
			
			
			
			
			
		}//End of if
//		else
//		{
//			System.out.println("Action is null");
//		}
		}
	}//End of doPost

}

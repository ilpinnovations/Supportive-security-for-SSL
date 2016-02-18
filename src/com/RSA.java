package com;

import java.math.BigInteger;
import java.util.Random;
import java.io.*;
public class RSA {
	public BigInteger RSADoPrivate(BigInteger x, BigInteger n, BigInteger p, BigInteger q, BigInteger d,  BigInteger dmp1, BigInteger dmq1, BigInteger coeff) {
		  if(p == null || q == null)
		    return x.modPow(d, n);

		  // TODO: re-calculate any missing CRT params
		  BigInteger xp = x.mod(p).modPow(dmp1,p);
		  BigInteger xq = x.mod(q).modPow(dmq1,q);

		  while(xp.compareTo(xq) < 0)
		    xp = xp.add(p);
		  return xp.subtract(xq).multiply(coeff).mod(p).multiply(q).add(xq);
		}
	
	
	//Unpadding the received cipher text
	public String pkcs1unpad2(BigInteger d,int n) {
		  byte [] b = d.toByteArray();
		 // System.out.println("byte array length :"+b.length);
		 // System.out.println("value of n :" +n);
		  int i = 0;
		  while(i < b.length && b[i] == 0) ++i;
		  System.out.println("value of i:" +i);
		  if(b.length-i != n-1 || b[i] != 2){
		   // return null;
			  //System.out.println("inside if####");
		  }
		  ++i;
		  //System.out.println("outside if###");
		  while(b[i] != 0)
		    if(++i >= b.length) return null;
		  System.out.println("value of i :" + i);
		  String ret = "";
		  while(++i < b.length) {
			  //System.out.println("inside while");
		    int c = b[i] & 255;
		    if(c < 128) { // utf-8 decode
		      ret += String.valueOf((char)c);
		    }
		    else if((c > 191) && (c < 224)) {
		      ++i;
		    }
		    else {
		      ret += String.valueOf(((char)((c & 15) << 12) |(char) ((b[i+1] & 63) << 6) |(char) (b[i+2] & 63)));
		      i += 2;
		    }
		  }
		  return ret;
		}
	
	
	public String pkcs1unpad3(BigInteger d,int n, String rst) {
		char [] c = rst.toCharArray();
		String ret = "";
		int i = 0;
		if(++i >= c.length){
			return null;
		}
		int e = c[i];
		if(i > c.length){
			
		    if(e < 128) { // utf-8 decode
		      ret += String.valueOf(((char)e & 15 << 12) | (char) ((c[i+1] & 63) <<6) | (char) (c[i+2] & 63));
		    }
		    else if((e > 191) && (e < 224)) {
		      ++i;
		    }
		}
		else if(i>c.length-1)
		{
			ret += String.valueOf((char)e & 15 << 12);
		}
		else {
			ret = new String(c);
		}
		return ret;
	}
	
	
	
	
	 public byte[] encrypt(byte[] message, BigInteger e, BigInteger N)
	    {
	        return (new BigInteger(message)).modPow(e, N).toByteArray();
	    }
	 
	 public byte[] decrypt(byte[] message, BigInteger d, BigInteger N)
	    {
	        return (new BigInteger(message)).modPow(d, N).toByteArray();
	    }
	 
	 private static String bytesToString(byte[] encrypted)
	    {
	        String test = "";
	        for (byte b : encrypted)
	        {
	            test += Byte.toString(b);
	        }
	        return test;
	    }
	
	//############################################################
	
//	public String pkcs1unpad2server(BigInteger d)
//	{
//		String ret="";
//		byte [] b = d.toByteArray();
//		int i = 0;
//		while(++i < b.length)
//		{
//			int c = b[i] & 255;
//			ret += String.valueOf((char)c);
//			++i;
//		}
//	}
}

package com.nearGroup.security;
import com.nearGroup.util.Helper;

public class testencryp {
  public static void main(String[] args)
  {
	 String strEncryp=Helper.encrypt("rajesh", "ecwVenM)bil3D0(ecwGir");
     System.out.println(strEncryp);
     System.out.println(Helper.decrypt(strEncryp, "ecwVenM)bil3D0(ecwGir"));     
     
  }
}

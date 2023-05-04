package com.joun.sosmall.common.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
  private String iv;
  private Key keySpec;

  /**
   * 16자리의 키값을 입력하여 객체를 생성
   * 
   * @param key16 암/복호화를 위한 키값
   * @throws UnsupportedEncodingException 키값의 길이가 16이하일 경우
   */
  private final String key16 = "abcdefghijklmnop";

  public AES256Util() throws UnsupportedEncodingException {
    this.iv = key16.substring(0, 16);
    byte[] basicBytes = new byte[16];
    byte[] keyBytes = key16.getBytes("UTF-8");
    int len = keyBytes.length;
    if (len > basicBytes.length) {
      len = basicBytes.length;
    }
    System.arraycopy(keyBytes, 0, basicBytes, 0, len);
    SecretKeySpec keySpec = new SecretKeySpec(basicBytes, "AES");
    this.keySpec = keySpec;
  }

  public String encrypt(String plainStr) throws NoSuchAlgorithmException,
      GeneralSecurityException, UnsupportedEncodingException {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] encrypted = cipher.doFinal(plainStr.getBytes("UTF-8"));
    String enStr = new String(Base64.getEncoder().encodeToString(encrypted));
    return enStr;
  }

  public String decrypt(String encryptedStr) throws NoSuchAlgorithmException,
      GeneralSecurityException, UnsupportedEncodingException {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    byte[] byteStr = Base64.getDecoder().decode(encryptedStr.getBytes());
    return new String(cipher.doFinal(byteStr), "UTF-8");
  }
}
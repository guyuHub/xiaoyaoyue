package cn.com.doit.test.jsse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.junit.Test;

public class StudyJsse {
	/* 初始化SSLContext */
	public void initializeSSLContextWithDefault()
			throws NoSuchAlgorithmException {
		// This method creates a default SSLContext with a default KeyManager,
		// TrustManager, and SecureRandom
		// (a secure random number
		// generator).等同于SSLContext.getInstance("Default")
		SSLContext ssl = SSLContext.getDefault();
		// 定制化
		// SSLContext ssldz=SSLContext.getInstance("TLS1.2");
		// ssldz.init(km, tm, random);
	}

	@Test
	public void getAndSetKeyStore() throws Exception {
		KeyStore ksKeys = KeyStore.getInstance("pkcs12");
		char[] password = "guyu717250".toCharArray();
		FileInputStream store = new FileInputStream(new File(
				"D:/OwnStudy/tryByGY/src/main/resources/gykey.p12"));
		ksKeys.load(store, password);
	  //获取所有别名
		Enumeration bb = ksKeys.aliases();
		if(bb.hasMoreElements()){
			System.out.println(bb.nextElement().toString());
		}
		
		// 获取私钥
		PasswordProtection passwprds = new PasswordProtection(password);
		KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ksKeys.getEntry("gykey", passwprds);
		PrivateKey myPrivateKey = pkEntry.getPrivateKey();
		System.out.println(myPrivateKey);
		//创建secretKey
		KeyGenerator kg = KeyGenerator.getInstance("DES","SunJCE");  
		SecretKey	secretKey = null; 
		KeyStore.SecretKeyEntry secretKeyEntry=new SecretKeyEntry(secretKey);
       Certificate  ct  =     ksKeys.getCertificate("gykey");System.out.println(ct);

	}

	public void createSSLEngine() throws Exception {
		String hostname = "127.0.0.1";
		int port = 121;
		// Create and initialize the SSLContext with key material
		char[] passphrase = "passphrase".toCharArray();

		// First initialize the key and trust material
		KeyStore ksKeys = KeyStore.getInstance("JKS");
		ksKeys.load(new FileInputStream("testKeys"), passphrase);
		KeyStore ksTrust = KeyStore.getInstance("JKS");
		ksTrust.load(new FileInputStream("testTrust"), passphrase);

		// KeyManagers decide which key material to use
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ksKeys, passphrase);

		// TrustManagers decide whether to allow connections
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(ksTrust);

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		// Create the engine
		SSLEngine engine = sslContext.createSSLEngine(hostname, port);
		// Use as client
		engine.setUseClientMode(true);
	}

	@Test
	public void testPrintlnProvider() {
		// 打印provider ，provider可能实现的服务包括： 算法（如 DSA、RSA、MD5 或 SHA-1）。
		// 密钥的生成、转换和管理设施（如用于特定于算法的密钥）。
		Provider[] providers = Security.getProviders();
		for (Provider provider : providers) {
			System.out.println(provider);
		}

	}
}

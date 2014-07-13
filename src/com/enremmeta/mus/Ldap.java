package com.enremmeta.mus;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Ldap implements MusAuthenticator {

	public static String TEST_USERNAME = "gregory.golberg";
	public static String TEST_PASSWORD = "";

	private String ldapHost;

	private String base;

	public Ldap(String ldapHost, String base) {
		this.ldapHost = ldapHost;
		this.base = base;
	}

	public void auth(String user, String password) throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		String ldapUrl = "ldap://" + this.ldapHost + ":389";
		env.put(Context.PROVIDER_URL, ldapUrl);

		// Authenticate as S. User and password "mysecret"
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		String principal = "uid=" + user + "," + base;
		System.out.println(principal);
		env.put(Context.SECURITY_PRINCIPAL, principal);
		env.put(Context.SECURITY_CREDENTIALS, password);
		System.out.println(env);
		// Create the initial context
		DirContext ctx = new InitialDirContext(env);
		ctx.close();
	}

	public static void main(String[] argv) throws Exception {
		Ldap ldap = new Ldap("access.adotube.com",
				"ou=Adotube,dc=adotube,dc=com");
		ldap.auth(TEST_USERNAME, TEST_PASSWORD);
	}
}

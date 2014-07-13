package com.enremmeta.mus;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.naming.NamingException;

import com.github.mpjct.jmpjct.Engine;
import com.github.mpjct.jmpjct.JMP;

/**
 * 
 * @author Gregory Golberg <grisha@alum.mit.edu>
 */
public class MusLdapAuthProxy extends MusAuthProxy {

	private Ldap ldap;

	public void auth(String username, String password) throws MusException {
		try {
			ldap.auth(username, password);
		} catch (NamingException e) {
			throw new MusException(e);
		}
	}

	public void init(Engine context) throws IOException, UnknownHostException {
		super.init(context);
		String ldapHost = JMP.config.getProperty("mus_ldap_host");
		String ldapBase = JMP.config.getProperty("mus_ldap_base");
		ldap = new Ldap(ldapHost, ldapBase);
	}
}

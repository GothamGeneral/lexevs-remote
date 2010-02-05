/*******************************************************************************
 * Copyright: (c) 2004-2009 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 * 
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 *   
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *   
 *  		http://www.eclipse.org/legal/epl-v10.html
 * 
 *  		
 *******************************************************************************/
package org.LexGrid.LexBIG.caCore.applicationservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import org.LexGrid.LexBIG.Impl.helpers.MyClassLoader;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.CodebaseAwareObjectInputStream;

/**
 * Ues the LexEVS Classloader (for loading extensions, SQL drivers, etc).
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 *
 */
public class LexEvsHttpInvokerServiceExporter extends HttpInvokerServiceExporter {

	@Override
	protected ObjectInputStream createObjectInputStream(InputStream is)
			throws IOException {
		 return new CodebaseAwareObjectInputStream(is, MyClassLoader.instance(), null);
	}
}

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
package org.LexGrid.LexBIG.caCore.test.query.hql;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * The Class AllTests_HQLQuery.
 */
public class HQLTestSuite {
    
    /**
     * Suite.
     * 
     * @return the test
     * 
     * @throws Exception the exception
     */
    public static Test suite() throws Exception
    {
        TestSuite mainSuite = new TestSuite("LexEVS caCORE Query HQL Tests");
  
        mainSuite.addTestSuite(HQLSupportedAssociation.class);
        mainSuite.addTestSuite(HQLSupportedAssociationQualifier.class);
        mainSuite.addTestSuite(HQLCodingScheme.class);
        
        return mainSuite;
    }
}

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
package org.LexGrid.LexBIG.distributed.test.bugs;

import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.Association;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;

public class GForge29695 extends ServiceTestCase
{
	private final String test_id = "GForge29695";
	
	@Override
	protected String getTestID() {
		return test_id;
	}
	
	public void testCheckForDuplicates() throws Exception {
		LexBIGService lbsi = LexEVSServiceHolder.instance().getLexEVSAppService();
    	CodedNodeGraph cng = lbsi.getNodeGraph(THES_SCHEME, null, null);
    	
    	cng = cng.restrictToAssociations(Constructors.createNameAndValueList("A8"), null);
    	
    	CodedNodeSet cns = lbsi.getNodeSet(THES_SCHEME, null, null).restrictToCodes(Constructors.createConceptReferenceList("C2851"));

    	cng = cng.restrictToSourceCodes(cns);

    	ResolvedConceptReferenceList rcrl = cng.resolveAsList(
    			Constructors.createConceptReference(	
    			"C54450", THES_SCHEME), false, true, 0, 1, null, null, null, -1);
    	
    	assertEquals(1,rcrl.getResolvedConceptReferenceCount());
    	
    	ResolvedConceptReference ref = rcrl.getResolvedConceptReference(0);
    	
    	assertTrue(ref.getSourceOf() == null);
    	assertEquals(1,ref.getTargetOf().getAssociationCount());
    	
    	Association cis = ref.getTargetOf().getAssociation(0);
    	
    	assertEquals("A8", cis.getAssociationName());
    	
    	assertEquals(1,cis.getAssociatedConcepts().getAssociatedConceptCount());
	}		
}

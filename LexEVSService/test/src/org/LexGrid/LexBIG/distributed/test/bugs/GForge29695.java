/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
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
    	
    	cng = cng.restrictToAssociations(Constructors.createNameAndValueList("Concept_In_Subset"), null);
    	
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
    	
    	assertEquals("Concept_In_Subset", cis.getAssociationName());
    	
    	assertEquals(1,cis.getAssociatedConcepts().getAssociatedConceptCount());
	}		
}

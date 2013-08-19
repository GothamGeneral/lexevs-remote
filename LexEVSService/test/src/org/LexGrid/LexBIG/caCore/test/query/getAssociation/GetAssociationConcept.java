/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
package org.LexGrid.LexBIG.caCore.test.query.getAssociation;

import java.util.List;

import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.caCore.applicationservice.QueryOptions;
import org.LexGrid.LexBIG.caCore.interfaces.LexEVSApplicationService;
import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.LexGrid.concepts.Entity;

public class GetAssociationConcept extends ServiceTestCase
{
	private final String test_id = "CQLTests";
	
	@Override
	protected String getTestID() {
		return test_id;
	}
	
	public void testGetConceptPresentations() throws Exception {
		LexEVSApplicationService service = LexEVSServiceHolder.instance().getLexEVSAppService();
		
		QueryOptions options = new QueryOptions();
		options.setCodingScheme(ServiceTestCase.THES_SCHEME);
		CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
		csvt.setVersion(ServiceTestCase.THES_VERSION);
		options.setCodingSchemeVersionOrTag(csvt);	
		
		Entity concept = new Entity();
		concept.setEntityCode("C34824");
		
		List<Object> presentations = service.getAssociation(concept, "_presentationList", options);
		assertTrue(presentations.size() > 0);
		 
		//Get the first one... there may be more than one return results depending
		//on how many different versions are loaded
		Object results = presentations.get(0);
		
		assertTrue(results instanceof List);
		
		//see if they're all there
		List pres = (List)results;
		assertTrue(pres.size() == 4);		
	}
	
	public void testGetConceptDefinitions() throws Exception {
		LexEVSApplicationService service = LexEVSServiceHolder.instance().getLexEVSAppService();
		
		QueryOptions options = new QueryOptions();
		options.setCodingScheme(ServiceTestCase.THES_SCHEME);
		CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
		csvt.setVersion(ServiceTestCase.THES_VERSION);
		options.setCodingSchemeVersionOrTag(csvt);		
		
		Entity concept = new Entity();
		concept.setEntityCode("C34824");
		
		List<Object> definitions = service.getAssociation(concept, "_definitionList", options);
		assertTrue(definitions.size() > 0);
		 
		//Get the first one... there may be more than one return results depending
		//on how many different versions are loaded
		Object results = definitions.get(0);
		
		assertTrue(results instanceof List);
		
		//see if they're all there
		List defs = (List)results;
		assertTrue(defs.size() == 1);	
	}	
}

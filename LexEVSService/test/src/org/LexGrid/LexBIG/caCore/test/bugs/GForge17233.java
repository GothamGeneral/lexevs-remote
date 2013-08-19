/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
package org.LexGrid.LexBIG.caCore.test.bugs;

import java.util.List;

import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.caCore.applicationservice.QueryOptions;
import org.LexGrid.LexBIG.caCore.interfaces.LexEVSDataService;
import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.LexGrid.concepts.Entity;

public class GForge17233 extends ServiceTestCase
{
	private final String test_id = "GForge17233";
	
	@Override
	protected String getTestID() {
		return test_id;
	}
	
	public void testGetEntityCodesPrefixedByNamespaceWithColon() throws Exception {
		LexEVSDataService service = LexEVSServiceHolder.instance().getLexEVSAppService();
		
		QueryOptions qo = new QueryOptions();
		qo.setCodingScheme(ServiceTestCase.THES_SCHEME);
		CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
		csvt.setVersion(ServiceTestCase.THES_VERSION);
		qo.setCodingSchemeVersionOrTag(csvt);
		
		Entity entity = new Entity();
		entity.setEntityCodeNamespace("Thesaurus");
		entity.setEntityCode("Thesaurus:*");
				
		List<Entity> results = service.search(Entity.class, entity, qo);
		assertTrue(results.size() == 0);
	}		
	
	public void testGetEntityCodesPrefixedByNamespaceWithHash() throws Exception {
		LexEVSDataService service = LexEVSServiceHolder.instance().getLexEVSAppService();
		
		QueryOptions qo = new QueryOptions();
		qo.setCodingScheme(ServiceTestCase.THES_SCHEME);
		CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
		csvt.setVersion(ServiceTestCase.THES_VERSION);
		qo.setCodingSchemeVersionOrTag(csvt);
		
		Entity entity = new Entity();
		entity.setEntityCodeNamespace("Thesaurus");
		entity.setEntityCode("Thesaurus#*");
				
		List<Entity> results = service.search(Entity.class, entity, qo);
		assertTrue(results.size() == 0);
	}		
}

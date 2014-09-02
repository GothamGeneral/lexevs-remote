/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
package org.LexGrid.LexBIG.caCore.test.query.cql;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLGroup;
import gov.nih.nci.system.query.cql.CQLLogicalOperator;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.List;

import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.concepts.Entity;
import org.apache.commons.lang.ArrayUtils;

public class CQLPredicates extends ServiceTestCase
{
	private final String test_id = "CQLTests";

	@Override
	protected String getTestID() {
		return test_id;
	}
/*
	public void testEqualToPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("149164001");
		at1.setPredicate(CQLPredicate.EQUAL_TO);	

		target.setAttribute(at1);
		query.setTarget(target);
		List<Concept> results = service.query(query);

		assertTrue(results.size() > 0);
		for (Concept concept : results){
			assertTrue(concept.getEntityCode().equals("149164001"));
			assertTrue(concept.getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}
	}	

	public void testLikePredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("14916400%");
		at1.setPredicate(CQLPredicate.LIKE);	

		target.setAttribute(at1);
		query.setTarget(target);
		List<Concept> results = service.query(query);

		assertTrue(results.size() > 0);
		for (Concept concept : results){
			assertTrue(concept.getEntityCode().equals("149164001"));
			assertTrue(concept.getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}
	}	

	//TODO
	public void testGreaterThanPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("99998006");
		at1.setPredicate(CQLPredicate.GREATER_THAN);

		CQLAttribute at2 = new CQLAttribute();
		at2.setName("_entityCodeNamespace");
		at2.setValue(ServiceTestCase.SNOMED_SCHEME);
		at2.setPredicate(CQLPredicate.EQUAL_TO);

		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.AND);
		group.addAttribute(at1);
		group.addAttribute(at2);

		target.setGroup(group);

		query.setTarget(target);
		List<Concept> results = service.query(query);

		assertTrue(results.size() == 1);
		for (Concept concept : results){
			assertTrue(concept.getEntityCode().equals("99999003"));
			assertTrue(concept.getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}
	}	

	*/
	//TODO
	public void testGreaterThanEqualToPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("10000006");
		at1.setPredicate(CQLPredicate.GREATER_THAN_EQUAL_TO);
		
		CQLAttribute at1p = new CQLAttribute();
		at1p.setName("_entityCode");
		at1p.setValue("10001066");
		at1p.setPredicate(CQLPredicate.LESS_THAN_EQUAL_TO);

		CQLAttribute at2 = new CQLAttribute();
		at2.setName("_entityCodeNamespace");
		at2.setValue(ServiceTestCase.SNOMED_SCHEME);
		at2.setPredicate(CQLPredicate.EQUAL_TO);

		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.AND);
		group.addAttribute(at1);
		group.addAttribute(at1p);
		group.addAttribute(at2);

		target.setGroup(group);

		query.setTarget(target);
		List<Entity> results = service.query(query);

		assertTrue(results.size() == 2);
		
		String[] expectedCodes = new String[]{"10000006", "10001005"};
		String[] returnedCodes = new String[2];
		for (int i = 0; i < results.size(); i++){
			returnedCodes = (String[])ArrayUtils.add(returnedCodes, results.get(i).getEntityCode());
			assertTrue(results.get(i).getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}

		for(String code : expectedCodes){
			assertTrue(ArrayUtils.contains(returnedCodes, code));
		}

	}

	//TODO
	public void testIsNotNullPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("76880004");
		at1.setPredicate(CQLPredicate.EQUAL_TO);

		CQLAttribute at2 = new CQLAttribute();
		at2.setName("_entityCodeNamespace");
		at2.setValue(ServiceTestCase.SNOMED_SCHEME);
		at2.setPredicate(CQLPredicate.IS_NOT_NULL);

		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.AND);
		group.addAttribute(at1);
		group.addAttribute(at2);

		target.setGroup(group);

		query.setTarget(target);
		List<Entity> results = service.query(query);

		assertTrue(results.size()+"",results.size() == 1);
		String expectedCodes[] = new String[]{"76880004"};
		String returnedCodes[] = new String[1];
		for (int i = 0;i < results.size(); i++){
			returnedCodes = (String[])ArrayUtils.add(returnedCodes, results.get(i).getEntityCode());
			assertTrue(results.get(i).getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}

		for(int i = 0; i < expectedCodes.length; i++){
			assertTrue(ArrayUtils.contains(returnedCodes, expectedCodes[i]));
		}

	}		

	//TODO
	public void testLessThanPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("10001004");
		at1.setPredicate(CQLPredicate.LESS_THAN);

		CQLAttribute at2 = new CQLAttribute();
		at2.setName("_entityCodeNamespace");
		at2.setValue(ServiceTestCase.SNOMED_SCHEME);
		at2.setPredicate(CQLPredicate.EQUAL_TO);

		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.AND);
		group.addAttribute(at1);
		group.addAttribute(at2);

		target.setGroup(group);

		query.setTarget(target);
		List<Entity> results = service.query(query);

		assertTrue(results.size() == 1);
		for (Entity concept : results){
			assertTrue(concept.getEntityCode().equals("10000006"));
			assertTrue(concept.getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}
	}	

	//TODO
	public void testLessThanEqualToPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.concepts.Entity");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_entityCode");
		at1.setValue("10001005");
		at1.setPredicate(CQLPredicate.LESS_THAN_EQUAL_TO);

		CQLAttribute at2 = new CQLAttribute();
		at2.setName("_entityCodeNamespace");
		at2.setValue(ServiceTestCase.SNOMED_SCHEME);
		at2.setPredicate(CQLPredicate.EQUAL_TO);

		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.AND);
		group.addAttribute(at1);
		group.addAttribute(at2);

		target.setGroup(group);

		query.setTarget(target);
		List<Entity> results = service.query(query);

		assertTrue(results.size() == 2);
		String expectedCodes[] = new String[]{"10000006", "10001005"};
		String returnedCodes[] = new String[2];
		for (int i = 0; i < results.size(); i++){
			returnedCodes = (String[])ArrayUtils.add(returnedCodes, results.get(i).getEntityCode());
			assertTrue(results.get(i).getEntityCodeNamespace().equals(ServiceTestCase.SNOMED_SCHEME));
		}

		for(String code : expectedCodes){
			assertTrue(ArrayUtils.contains(returnedCodes, code));
		}
	}	

	//TODO
	public void testNotEqualToPredicate() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();

		CQLQuery query = new CQLQuery();	
		CQLObject target = new CQLObject();

		target.setName("org.LexGrid.codingSchemes.CodingScheme");

		CQLAttribute at1 = new CQLAttribute();
		at1.setName("_codingSchemeName");
		at1.setValue(ServiceTestCase.SNOMED_SCHEME);
		at1.setPredicate(CQLPredicate.NOT_EQUAL_TO);	

		target.setAttribute(at1);
		query.setTarget(target);
		List<CodingScheme> results = service.query(query);

		assertTrue(results.size() > 0);
		for (CodingScheme cs : results){
			assertFalse(cs.getCodingSchemeName().equals(
					ServiceTestCase.SNOMED_SCHEME));
		}
	}	
}

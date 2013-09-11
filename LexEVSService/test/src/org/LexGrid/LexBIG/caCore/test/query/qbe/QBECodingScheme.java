/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
package org.LexGrid.LexBIG.caCore.test.query.qbe;

import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.List;

import org.LexGrid.LexBIG.testUtil.LexEVSServiceHolder;
import org.LexGrid.LexBIG.testUtil.ServiceTestCase;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.naming.Mappings;
import org.LexGrid.naming.SupportedAssociation;
import org.LexGrid.naming.SupportedCodingScheme;

public class QBECodingScheme extends ServiceTestCase
{
	private final String test_id = "QBE Tests";
	
	@Override
	protected String getTestID() {
		return test_id;
	}
	
	public void testRetrieveCodingSchemes() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		
		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
		
		assertTrue(sourceList != null);
		assertTrue(sourceList.size() > 0);
	}
	
	public void testRetrieveNCIThesByName() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName(ServiceTestCase.THES_SCHEME);
		cs.setRepresentsVersion(ServiceTestCase.THES_VERSION);
		
		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
		
		assertTrue(sourceList != null);
		System.out.println(sourceList.size());
		assertTrue(sourceList.size() == 1);
		
		CodingScheme nciThes = sourceList.get(0);
			
		assertTrue(nciThes.getCodingSchemeName().equals(ServiceTestCase.THES_SCHEME));
		assertTrue(nciThes.getCodingSchemeURI().equals(ServiceTestCase.THES_URN));
		assertTrue(nciThes.getFormalName().equals(ServiceTestCase.THES_FORMAL));
		assertTrue(nciThes.getRepresentsVersion().equals(ServiceTestCase.THES_VERSION));	
		assertTrue(nciThes.getDefaultLanguage().equals(ServiceTestCase.THES_DEFAULT_LANG));
		assertNotNull(nciThes.getApproxNumConcepts());
	
		assertTrue(nciThes.getEntityDescription().getContent().equals(ServiceTestCase.THES_ENT_DESC));		
	}	
	
	/*
	 * Should only return one Coding Scheme
	 */
	public void testGetMultipleCodingSchemeVersions() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName(ServiceTestCase.META_SCHEME);
		cs.setRepresentsVersion(ServiceTestCase.META_VERSION);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() == 1);
				
		CodingScheme scheme = sourceList.get(0);
		assertTrue(scheme.getCodingSchemeName().equals(ServiceTestCase.META_SCHEME));
		assertTrue(scheme.getRepresentsVersion().equals(ServiceTestCase.META_VERSION));
	}
	
	public void testSearchCodingSchemeBySupportedCodingScheme() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		
		Mappings mappings = new Mappings();
		SupportedCodingScheme scs = new SupportedCodingScheme();
		scs.setLocalId("zebrafish_anatomical_ontology");
		
		mappings.addSupportedCodingScheme(scs);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() >=1);
				
		CodingScheme zebrafish = sourceList.get(0);
		assertTrue(zebrafish.getCodingSchemeName().equals(ServiceTestCase.ZEBRAFISH_SCHEME));
		assertTrue(zebrafish.getRepresentsVersion().equals(ServiceTestCase.ZEBRAFISH_VERSION));
	}
	
	public void testSearchCodingSchemeBySupportedCodingSchemeWrongName() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		
		Mappings mappings = new Mappings();
		SupportedCodingScheme scs = new SupportedCodingScheme();
		scs.setIsImported(false);
		scs.setLocalId("ZebrafishWRONG_NAME");
		
		mappings.addSupportedCodingScheme(scs);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() == 0);
	}
	
	public void testSearchCodingSchemeBySupportedCodingSchemeWildCard() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		
		Mappings mappings = new Mappings();
		SupportedCodingScheme scs = new SupportedCodingScheme();
		scs.setLocalId("Zeb*ish");
		
		mappings.addSupportedCodingScheme(scs);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() >= 1);
				
		CodingScheme zebrafish = sourceList.get(0);
		assertTrue(zebrafish.getCodingSchemeName().equals(ServiceTestCase.ZEBRAFISH_SCHEME));
		assertTrue(zebrafish.getRepresentsVersion().equals(ServiceTestCase.ZEBRAFISH_VERSION));
	}
	
	public void testSearchCodingSchemeBySupportedAssociation() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName(ServiceTestCase.THES_SCHEME);
		cs.setRepresentsVersion(ServiceTestCase.THES_VERSION);
		
		Mappings mappings = new Mappings();
		
		SupportedAssociation sa = new SupportedAssociation();
		sa.setLocalId("PAL-STATEMENT");
		
		mappings.addSupportedAssociation(sa);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() == 1);
				
		CodingScheme scheme = sourceList.get(0);
		assertTrue(scheme.getCodingSchemeName().equals(ServiceTestCase.THES_SCHEME));
		assertTrue(scheme.getRepresentsVersion().equals(ServiceTestCase.THES_VERSION));
	}
	
	public void testSearchCodingSchemeBySupportedAssociationWrongName() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeURI(ServiceTestCase.THES_SCHEME);
		cs.setRepresentsVersion(ServiceTestCase.THES_VERSION);
		
		Mappings mappings = new Mappings();
		
		SupportedAssociation sa = new SupportedAssociation();
		sa.setLocalId("Chemical_Or_Drug_Plays_Role_In_Biological_Process_WRONG_NAME");
		
		mappings.addSupportedAssociation(sa);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() == 0);		
	}
	
	public void testSearchCodingSchemeBySupportedAssociationWildCard() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName(ServiceTestCase.THES_SCHEME);
		cs.setRepresentsVersion(ServiceTestCase.THES_VERSION);
		
		Mappings mappings = new Mappings();
		
		SupportedAssociation sa = new SupportedAssociation();
		sa.setLocalId("PAL-STATEME*T");
		
		mappings.addSupportedAssociation(sa);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	
		assertTrue(sourceList.size() == 1);
				
		CodingScheme scheme = sourceList.get(0);
		assertTrue(scheme.getCodingSchemeName().equals(ServiceTestCase.THES_SCHEME));
		assertTrue(scheme.getRepresentsVersion().equals(ServiceTestCase.THES_VERSION));
	}
	
	public void testSearchCodingSchemeBySupportedAssociationWildCard2() throws Exception {
		ApplicationService service = LexEVSServiceHolder.instance().getAppService();
		
		CodingScheme cs = new CodingScheme();	
		
		Mappings mappings = new Mappings();
		
		SupportedAssociation sa = new SupportedAssociation();
		sa.setLocalId("*s*");
		
		mappings.addSupportedAssociation(sa);
		cs.setMappings(mappings);

		List<CodingScheme> sourceList = service.search(CodingScheme.class, cs);	
	
		assertTrue(sourceList != null);	

		assertTrue("Size: " + sourceList.size(), sourceList.size() >= 1);	
	}
}
/*
* Copyright: (c) Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Distributed under the OSI-approved BSD 3-Clause License.
* See http://ncip.github.com/lexevs-remote/LICENSE.txt for details.
*/
package org.LexGrid.LexBIG.caCore.dao.orm.selectionStrategy;

import gov.nih.nci.system.dao.Request;

import java.util.ArrayList;
import java.util.List;

import org.LexGrid.LexBIG.caCore.applicationservice.QueryOptions;
import org.LexGrid.LexBIG.caCore.dao.orm.LexEVSDAO;
import org.LexGrid.LexBIG.caCore.dao.orm.LexEVSDAO.DAOType;
import org.apache.log4j.Logger;

public class HistoryOrCodingSchemeStrategy implements DAOSelectionStrategy {

	private static Logger log = Logger.getLogger(HistoryOrCodingSchemeStrategy.class.getName());
	
	private List<String> historyClasses;
	
	public List<LexEVSDAO> getDAOList(List<LexEVSDAO> daoList, Request request){
		return getDAOList(daoList, request, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.LexGrid.LexBIG.caCore.dao.orm.selectionStrategy.DAOSelectionStrategy#getDAOList(java.util.List, gov.nih.nci.system.dao.Request, java.util.HashMap, org.LexGrid.LexBIG.caCore.applicationservice.CodingSchemeVersionPair)
	 *
	 * CodingSchemeVersionPair is ignored
	 */
	public List<LexEVSDAO> getDAOList(List<LexEVSDAO> daoList, Request request, QueryOptions queryOptions){
		//If the Request object is null, return back all DAOs -- we can't determine if it is a History or CodingScheme request.
		if(request == null){
			log.warn("Request to Filter CodingScheme DAO list cannot be done without a Request object.");
			return daoList;
		}
		
		String searchClassName = request.getDomainObjectName();
		boolean isHistorySearch = historyClasses.contains(searchClassName);
		
		List<LexEVSDAO> usableDAOS = new ArrayList<LexEVSDAO>();
		
		for(LexEVSDAO dao : daoList){
			DAOType type = dao.getDaoType();	
			if(isHistorySearch){
				if(type == DAOType.HISTORY){
					usableDAOS.add(dao);
				}
			} else {
				if(type == DAOType.CODING_SCHEME){
					usableDAOS.add(dao);
				}
			}		
		}
		
		return usableDAOS;
	}

	public List<String> getHistoryClasses() {
		return historyClasses;
	}

	public void setHistoryClasses(List<String> historyClasses) {
		this.historyClasses = historyClasses;
	}
}

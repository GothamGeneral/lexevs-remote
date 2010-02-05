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
package org.LexGrid.LexBIG.caCore.hibernate.compositeKeys;

import java.io.Serializable;


public class URIMapKey implements Serializable {

	private String codingSchemeName;
	private String supportedAttributeTag;
	private String id;
	private String idValue;
	private String val1;
	
	public String getCodingSchemeName() {
		return codingSchemeName;
	}
	public void setCodingSchemeName(String codingSchemeName) {
		this.codingSchemeName = codingSchemeName;
	}
	public String getSupportedAttributeTag() {
		return supportedAttributeTag;
	}
	public void setSupportedAttributeTag(String supportedAttributeTag) {
		this.supportedAttributeTag = supportedAttributeTag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdValue() {
		return idValue;
	}
	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codingSchemeName == null) ? 0 : codingSchemeName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idValue == null) ? 0 : idValue.hashCode());
		result = prime
				* result
				+ ((supportedAttributeTag == null) ? 0 : supportedAttributeTag
						.hashCode());
		result = prime * result + ((val1 == null) ? 0 : val1.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final URIMapKey other = (URIMapKey) obj;
		if (codingSchemeName == null) {
			if (other.codingSchemeName != null)
				return false;
		} else if (!codingSchemeName.equals(other.codingSchemeName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idValue == null) {
			if (other.idValue != null)
				return false;
		} else if (!idValue.equals(other.idValue))
			return false;
		if (supportedAttributeTag == null) {
			if (other.supportedAttributeTag != null)
				return false;
		} else if (!supportedAttributeTag.equals(other.supportedAttributeTag))
			return false;
		if (val1 == null) {
			if (other.val1 != null)
				return false;
		} else if (!val1.equals(other.val1))
			return false;
		return true;
	}
	
}
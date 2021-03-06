<!DOCTYPE stylesheet [
<!ENTITY sp "<xsl:text> </xsl:text>">
<!ENTITY ind "<xsl:text>    </xsl:text>">
<!ENTITY cr "<xsl:text>
</xsl:text>">
]>

<xsl:stylesheet version="2.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
        xmlns:xlink="http://www.w3.org/1999/xlink">
        
    <xsl:output method="text"/>
   
    <xsl:template match="/">
    
        <xsl:text>{</xsl:text>&cr;
        
        &ind;<xsl:text>'recordCounter':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/recordCounter"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'start':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/start"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'end':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/end"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'pageCount':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/pages/@count"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'results':[</xsl:text>&cr;
        <xsl:apply-templates select="/xlink:httpQuery/queryResponse/class"/>&cr;
        &ind;<xsl:text>]</xsl:text>&cr;
        
        <xsl:text>}</xsl:text>&cr;
        
    </xsl:template>
    
    <xsl:template match="/xlink:httpQuery/queryResponse/class">
    
        <xsl:if test="position()!=1">
            <xsl:text>,</xsl:text>&cr;
        </xsl:if>
            
        &ind;&ind;<xsl:text>{</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'name':'</xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:text>',</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'recordNumber':</xsl:text>
        <xsl:value-of select="@recordNumber"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'fields' : {</xsl:text>&cr;
        <xsl:apply-templates select="field"/>&cr;
        &ind;&ind;&ind;<xsl:text>}</xsl:text>&cr;
        
        &ind;&ind;<xsl:text>}</xsl:text>
        
    </xsl:template>

    <xsl:template match="field">
            
        <xsl:if test="not(@xlink:type = 'simple')">
        
	        <xsl:if test="position()!=1">
	            <xsl:text>,</xsl:text>&cr;
	        </xsl:if>
	        
            &ind;&ind;&ind;&ind;<xsl:text>'</xsl:text>
            <xsl:value-of select="@name"/>
            <xsl:text>' : </xsl:text>
		    <xsl:call-template name="escape-string">
                <xsl:with-param name="s" select="."/>
		    </xsl:call-template>
		    
        </xsl:if>
        
    </xsl:template>

<!-- 
  The character escaping code below is taken from the xml2json project.
  The original license follows:

  Copyright (c) 2006,2008 Doeke Zanstra
  All rights reserved.

  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:

  Redistributions of source code must retain the above copyright notice, this 
  list of conditions and the following disclaimer. Redistributions in binary 
  form must reproduce the above copyright notice, this list of conditions and the 
  following disclaimer in the documentation and/or other materials provided with 
  the distribution.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
  BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF 
  THE POSSIBILITY OF SUCH DAMAGE.
 -->
 
    <!-- Main template for escaping strings; used by above template and for object-properties 
    	Responsibilities: placed quotes around string, and chain up to next filter, escape-bs-string -->
    <xsl:template name="escape-string">
    	<xsl:param name="s" />
    	<xsl:text>"</xsl:text>
    	<xsl:call-template name="escape-bs-string">
    		<xsl:with-param name="s" select="$s" />
    	</xsl:call-template>
    	<xsl:text>"</xsl:text>
    </xsl:template>

    <!-- Escape the backslash (\) before everything else. -->
    <xsl:template name="escape-bs-string">
    	<xsl:param name="s" />
    	<xsl:choose>
    		<xsl:when test="contains($s,'\')">
    			<xsl:call-template name="escape-quot-string">
    				<xsl:with-param name="s"
    					select="concat(substring-before($s,'\'),'\\')" />
    			</xsl:call-template>
    			<xsl:call-template name="escape-bs-string">
    				<xsl:with-param name="s"
    					select="substring-after($s,'\')" />
    			</xsl:call-template>
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:call-template name="escape-quot-string">
    				<xsl:with-param name="s" select="$s" />
    			</xsl:call-template>
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>

    <!-- Escape the double quote ("). -->
    <xsl:template name="escape-quot-string">
    	<xsl:param name="s" />
    	<xsl:choose>
    		<xsl:when test="contains($s,'&quot;')">
    			<xsl:call-template name="encode-string">
    				<xsl:with-param name="s"
    					select="concat(substring-before($s,'&quot;'),'\&quot;')" />
    			</xsl:call-template>
    			<xsl:call-template name="escape-quot-string">
    				<xsl:with-param name="s"
    					select="substring-after($s,'&quot;')" />
    			</xsl:call-template>
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:call-template name="encode-string">
    				<xsl:with-param name="s" select="$s" />
    			</xsl:call-template>
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>

    <!-- Replace tab, line feed and/or carriage return by its matching escape code. Can't escape backslash
    	or double quote here, because they don't replace characters (&#x0; becomes \t), but they prefix 
    	characters (\ becomes \\). Besides, backslash should be seperate anyway, because it should be 
    	processed first. This function can't do that. -->
    <xsl:template name="encode-string">
    	<xsl:param name="s" />
    	<xsl:choose>
    		<!-- tab -->
    		<xsl:when test="contains($s,'&#x9;')">
    			<xsl:call-template name="encode-string">
    				<xsl:with-param name="s"
    					select="concat(substring-before($s,'&#x9;'),'\t',substring-after($s,'&#x9;'))" />
    			</xsl:call-template>
    		</xsl:when>
    		<!-- line feed -->
    		<xsl:when test="contains($s,'&#xA;')">
    			<xsl:call-template name="encode-string">
    				<xsl:with-param name="s"
    					select="concat(substring-before($s,'&#xA;'),'\n',substring-after($s,'&#xA;'))" />
    			</xsl:call-template>
    		</xsl:when>
    		<!-- carriage return -->
    		<xsl:when test="contains($s,'&#xD;')">
    			<xsl:call-template name="encode-string">
    				<xsl:with-param name="s"
    					select="concat(substring-before($s,'&#xD;'),'\r',substring-after($s,'&#xD;'))" />
    			</xsl:call-template>
    		</xsl:when>
    		<xsl:otherwise>
    			<xsl:value-of select="$s" />
    		</xsl:otherwise>
    	</xsl:choose>
    </xsl:template>

</xsl:stylesheet>

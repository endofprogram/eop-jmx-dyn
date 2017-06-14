package org.eop.jmx.dyn.builder.xml;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.xml.IXNode;
/**
 * lixinjie 2016-12-26
 */
public interface IDXNode extends IXNode {

	String getPath();
	
	void setPath(String path);
	
	void fetchParentClaw();
	
	IClaw getParentClaw();
}

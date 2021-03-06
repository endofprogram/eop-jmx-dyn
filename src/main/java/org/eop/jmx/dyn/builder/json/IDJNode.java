package org.eop.jmx.dyn.builder.json;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.json.IJNode;
/**
 * lixinjie 2016-12-26
 */
public interface IDJNode extends IJNode {

	String getPath();
	
	void setPath(String path);
	
	void fetchParentClaw();
	
	IClaw getParentClaw();
}

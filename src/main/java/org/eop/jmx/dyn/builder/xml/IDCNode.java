package org.eop.jmx.dyn.builder.xml;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.xml.ICNode;
/**
 * lixinjie 2016-12-26
 */
public interface IDCNode extends ICNode, IDXNode {

	void setSelfClaw();
	
	IClaw getSelfClaw();
}

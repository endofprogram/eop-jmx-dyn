package org.eop.jmx.dynamic.builder.json;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.json.ICNode;
/**
 * lixinjie 2016-12-26
 */
public interface IDCNode extends IDJNode, ICNode {

	void setSelfClaw();
	
	IClaw getSelfClaw();
}

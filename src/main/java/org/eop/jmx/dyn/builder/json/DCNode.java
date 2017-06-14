package org.eop.jmx.dyn.builder.json;

import java.util.List;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;
/**
 * lixinjie 2016-12-26
 */
public abstract class DCNode extends DJNode implements IDCNode {
	
	protected IClaw claw;
	
	protected DCNode(IJNode parent, String name, String path) {
		super(parent, name, path);
	}

	@Override
	public void addChild(IJNode child) {
		getSelf().addChild(child);
	}

	@Override
	public void setSelfClaw() {
		claw = getParentClaw().getResult(getPath()).getClaw();
	}
	
	@Override
	public IClaw getSelfClaw() {
		return claw;
	}
	
	@Override
	public List<IJNode> getChildren() {
		return getSelf().getChildren();
	}
	
	@Override
	public IJNode deepClone(IJNode parent) {
		IDCNode dself = cloneDSelf(parent);
		for (IJNode child : getChildren()) {
			dself.addChild(child.deepClone(dself));
		}
		return dself;
	}
	
	public abstract IDCNode cloneDSelf(IJNode parent);
	
	public abstract ICNode getSelf();
}

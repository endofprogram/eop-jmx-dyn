package org.eop.jmx.dynamic.builder.xml;

import java.util.List;

import org.eop.claw.Claw;
import org.eop.jmx.builder.xml.ICNode;
import org.eop.jmx.builder.xml.IXNode;
/**
 * lixinjie 2016-12-26
 */
public abstract class DCNode extends DXNode implements IDCNode {

	protected Claw claw;
	
	protected DCNode(IXNode parent, String name, String path) {
		super(parent, name, path);
	}

	@Override
	public void setSelfClaw() {
		claw = getParentClaw().getClaw(getPath());
	}

	@Override
	public Claw getSelfClaw() {
		return claw;
	}

	@Override
	public void addChild(IXNode child) {
		getSelf().addChild(child);
	}

	@Override
	public List<IXNode> getChildren() {
		return getSelf().getChildren();
	}
	
	@Override
	public IXNode deepClone(IXNode parent) {
		IDCNode dself = cloneDSelf(parent);
		for (IXNode child : getChildren()) {
			dself.addChild(child.deepClone(dself));
		}
		return dself;
	}

	abstract IDCNode cloneDSelf(IXNode parent);
	
	abstract ICNode getSelf();
}

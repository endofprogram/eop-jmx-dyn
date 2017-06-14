package org.eop.jmx.dyn.builder.xml;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.xml.IXNode;
import org.eop.jmx.builder.xml.XNode;
/**
 * lixinjie 2016-12-26
 */
public abstract class DXNode extends XNode implements IDXNode {

	private String path;
	private IClaw parentClaw;
	
	protected DXNode(IXNode parent, String name, String path) {
		super(parent, name);
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public void fetchParentClaw() {
		IXNode parent = getParent();
		while (!(parent instanceof IDCNode)) {
			parent = parent.getParent();
		}
		parentClaw = ((IDCNode)parent).getSelfClaw();
	}

	@Override
	public IClaw getParentClaw() {
		return parentClaw;
	}

	abstract void prepare();
}

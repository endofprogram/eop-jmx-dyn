package org.eop.jmx.dyn.builder.json;

import org.eop.claw.IClaw;
import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.JNode;
/**
 * lixinjie 2016-12-26
 */
public abstract class DJNode extends JNode implements IDJNode {

	protected String path;
	protected IClaw parentClaw;
	
	protected DJNode(IJNode parent, String name, String path) {
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
		IJNode parent = getParent();
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

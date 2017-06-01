package org.eop.jmx.dynamic.builder.json;

import org.eop.claw.Claw;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.Root;
/**
 * lixinjie 2016-12-26
 */
public class DRoot extends DCNode {

	private Root root;
	
	public DRoot(IJNode parent, String name, String path, Claw claw) {
		super(parent, name, path);
		this.claw = claw;
		root = new Root(null, name);
	}
	
	Root getRoot() {
		return root;
	}

	@Override
	void prepare() {
		
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		root.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		root.toJson(sb, indent);
	}

	@Override
	public IDCNode cloneDSelf(IJNode parent) {
		return new DRoot(parent, getName(), getPath(), getSelfClaw());
	}

	@Override
	public ICNode getSelf() {
		return root;
	}

}

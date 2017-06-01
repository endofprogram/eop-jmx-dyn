package org.eop.jmx.dynamic.builder.json;

import org.eop.jmx.builder.json.Fork;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;

/**
 * lixinjie 2016-12-26
 */
public class DFork extends DCNode {

	private Fork fork;
	
	public DFork(IJNode parent, String name, String path) {
		super(parent, name, path);
		fork = new Fork(null, name);
	}
	
	Fork getFork() {
		return fork;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		setSelfClaw();
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		fork.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		fork.toJson(sb, indent);
	}

	@Override
	public IDCNode cloneDSelf(IJNode parent) {
		return new DFork(parent, getName(), getPath());
	}

	@Override
	public ICNode getSelf() {
		return fork;
	}
	
}

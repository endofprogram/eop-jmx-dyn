package org.eop.jmx.dynamic.builder.json;

import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.Leaf;

/**
 * lixinjie 2016-12-26
 */
public class DLeaf extends DJNode {

	private Leaf leaf;
	
	public DLeaf(IJNode parent, String name, String path) {
		super(parent, name, path);
	}
	
	Leaf getLeaf() {
		return leaf;
	}
	
	@Override
	void prepare() {
		fetchParentClaw();
		leaf = new Leaf(null, getName(), getParentClaw().get(getPath()));
	}
	
	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		leaf.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		leaf.toJson(sb, indent);
	}

	@Override
	public IJNode deepClone(IJNode parent) {
		return new DLeaf(parent, getName(), getPath());
	}

}

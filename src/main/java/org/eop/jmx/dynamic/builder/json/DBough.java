package org.eop.jmx.dynamic.builder.json;

import org.eop.jmx.builder.json.Bough;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;
/**
 * lixinjie 2016-12-26
 */
public class DBough extends DCNode {

	private Bough bough;
	
	public DBough(IJNode parent, String path) {
		super(parent, "", path);
		bough = new Bough(null);
	}
	
	Bough getBough() {
		return bough;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		setSelfClaw();
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		bough.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		bough.toJson(sb, indent);
	}

	@Override
	public IDCNode cloneDSelf(IJNode parent) {
		return new DBough(parent, getPath());
	}

	@Override
	public ICNode getSelf() {
		return bough;
	}

}

package org.eop.jmx.dynamic.builder.json;

import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.Stick;

/**
 * lixinjie 2016-12-26
 */
public class DStick extends DJNode {

	private Stick stick;
	
	public DStick(IJNode parent, String name, String path) {
		super(parent, name, path);
	}
	
	Stick getStick() {
		return stick;
	}
	
	@Override
	void prepare() {
		fetchParentClaw();
		stick = new Stick(null, getName(), getParentClaw().getList(getPath()).toArray());
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		stick.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		stick.toJson(sb, indent);
	}

	@Override
	public IJNode deepClone(IJNode parent) {
		return new DStick(parent, getName(), getPath());
	}

}

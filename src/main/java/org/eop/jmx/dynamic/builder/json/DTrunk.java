package org.eop.jmx.dynamic.builder.json;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.Trunk;
/**
 * lixinjie 2016-12-26
 */
public class DTrunk extends DCNode {

	private Trunk trunk;
	
	public DTrunk(IJNode parent, String path) {
		super(parent, "", path);
		trunk = new Trunk(null);
	}
	
	Trunk getTrunk() {
		return trunk;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		setSelfClaw();
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		processClone();
		trunk.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		processClone();
		trunk.toJson(sb, indent);
	}

	@Override
	public IDCNode cloneDSelf(IJNode parent) {
		return new DTrunk(parent, getPath());
	}

	@Override
	public ICNode getSelf() {
		return trunk;
	}

	void processClone() {
		int size = TypeUtil.asListType(getSelfClaw().getUnderlyingData()).size();
		if (size < 1) {
			getChildren().clear();
		} else if (size > 1) {
			List<IJNode> children = getChildren();
			IJNode child = ListUtil.getFirst(children);
			for (int i = 1; i< size; i++) {
				IDJNode clonedChild = (IDJNode)child.deepClone(this);
				clonedChild.setPath(i + "()");
				children.add(clonedChild);
			}
		}
	}
}

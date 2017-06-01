package org.eop.jmx.dynamic.builder.json;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;
import org.eop.jmx.builder.json.Bole;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.IJNode;
/**
 * lixinjie 2016-12-26
 */
public class DBole extends DCNode {

	private Bole bole;
	
	public DBole(IJNode parent, String name, String path) {
		super(parent, name, path);
		bole = new Bole(null, name);
	}
	
	Bole getBole() {
		return bole;
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
		bole.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		processClone();
		bole.toJson(sb, indent);
	}

	@Override
	public IDCNode cloneDSelf(IJNode parent) {
		return new DBole(parent, getName(), getPath());
	}

	@Override
	public ICNode getSelf() {
		return bole;
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

package org.eop.jmx.dynamic.builder.xml;

import java.util.List;

import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;
import org.eop.jmx.builder.xml.IXNode;
/**
 * lixinjie 2016-12-26
 */
public class DElements extends DElement {

	public DElements(IXNode parent, String namespace, String name, String path, boolean selfClosing) {
		super(parent, namespace, name, path, selfClosing);
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		processClone();
		getElement().toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		processClone();
		getElement().toXml(sb, indent);
	}
	
	void processClone() {
		int size = TypeUtil.asListType(getSelfClaw().getUnderlyingData()).size();
		if (size < 1) {
			getChildren().clear();
		} else if (size > 1) {
			List<IXNode> children = getChildren();
			IXNode child = ListUtil.getFirst(children);
			for (int i = 1; i < size; i++) {
				IDXNode clonedChild = (IDXNode)child.deepClone(this);
				clonedChild.setPath(i + "()");
				children.add(clonedChild);
			}
		}
	}
}

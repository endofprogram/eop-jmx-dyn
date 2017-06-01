package org.eop.jmx.dynamic.builder.xml;

import org.eop.jmx.builder.xml.IXNode;
import org.eop.jmx.builder.xml.Text;

/**
 * lixinjie 2016-12-26
 */
public class DText extends DXNode {

	private Text text;
	
	public DText(IXNode parent, String path) {
		super(parent, "", path);
	}
	
	Text getText() {
		return text;
	}
	
	@Override
	void prepare() {
		fetchParentClaw();
		text = new Text(null, getParentClaw().get(getPath()));
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		text.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		text.toXml(sb, indent);
	}

	@Override
	public DText deepClone(IXNode parent) {
		return new DText(parent, getPath());
	}

}

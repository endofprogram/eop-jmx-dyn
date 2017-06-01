package org.eop.jmx.dynamic.builder.xml;

import org.eop.jmx.builder.xml.CData;
import org.eop.jmx.builder.xml.IXNode;

/**
 * lixinjie 2016-12-26
 */
public class DCData extends DXNode {

	private CData cdata;
	
	public DCData(IXNode parent, String path) {
		super(parent, "", path);
	}
	
	CData getCData() {
		return cdata;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		cdata = new CData(null, getParentClaw().get(getPath()));
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		cdata.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		cdata.toXml(sb, indent);
	}

	@Override
	public DCData deepClone(IXNode parent) {
		return new DCData(parent, getPath());
	}

}

package org.eop.jmx.dynamic.builder.xml;

import org.eop.jmx.builder.xml.CDatas;
import org.eop.jmx.builder.xml.IXNode;

/**
 * lixinjie 2016-12-26
 */
public class DCDatas extends DXNode {

	private String namespace;
	private CDatas cdatas;
	
	public DCDatas(IXNode parent, String name, String path) {
		this(parent, null, name, path);
	}
	
	public DCDatas(IXNode parent, String namespace, String name, String path) {
		super(parent, name, path);
		this.namespace = namespace;
	}
	
	String getNamespace() {
		return namespace;
	}
	
	CDatas getCDatas() {
		return cdatas;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		cdatas = new CDatas(this, namespace, getName(), getParentClaw().getList(getPath()).toArray());
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		cdatas.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		cdatas.toXml(sb, indent);
	}

	@Override
	public DCDatas deepClone(IXNode parent) {
		return new DCDatas(parent, namespace, getName(), getPath());
	}

}

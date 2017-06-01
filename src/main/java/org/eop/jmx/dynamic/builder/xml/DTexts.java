package org.eop.jmx.dynamic.builder.xml;

import org.eop.jmx.builder.xml.IXNode;
import org.eop.jmx.builder.xml.Texts;

/**
 * lixinjie 2016-12-26
 */
public class DTexts extends DXNode {

	private String namespace;
	private Texts texts;
	
	public DTexts(IXNode parent, String name, String path) {
		this(parent, null, name, path);
	}
	
	public DTexts(IXNode parent, String namespace, String name, String path) {
		super(parent, name, path);
		this.namespace = namespace;
	}
	
	String getNamespace() {
		return namespace;
	}
	
	Texts getTexts() {
		return texts;
	}
	
	@Override
	void prepare() {
		fetchParentClaw();
		texts = new Texts(this, namespace, getName(), getParentClaw().getList(getPath()).toArray());
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		texts.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		texts.toXml(sb, indent);
	}

	@Override
	public DTexts deepClone(IXNode parent) {
		return new DTexts(parent, namespace, getName(), getPath());
	}

}

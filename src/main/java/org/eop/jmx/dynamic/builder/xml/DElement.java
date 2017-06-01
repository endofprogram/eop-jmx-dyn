package org.eop.jmx.dynamic.builder.xml;

import java.util.List;

import org.eop.jmx.builder.xml.Attribute;
import org.eop.jmx.builder.xml.Element;
import org.eop.jmx.builder.xml.ICNode;
import org.eop.jmx.builder.xml.IXNode;
import org.eop.jmx.builder.xml.Namespace;
/**
 * lixinjie 2016-12-26
 */
public class DElement extends DCNode implements IDElement {

	private Element element;
	
	public DElement(IXNode parent, String namespace, String name, String path, boolean selfClosing) {
		super(parent, name, path);
		this.element = new Element(parent, namespace, name, selfClosing);
	}

	@Override
	public void addNamespace(Namespace namespace) {
		element.addNamespace(namespace);
	}
	
	@Override
	public List<Namespace> getNamespaces() {
		return element.getNamespaces();
	}

	@Override
	public void addAttribute(Attribute attribute) {
		element.addAttribute(attribute);
	}
	
	@Override
	public List<Attribute> getAttributes() {
		return element.getAttributes();
	}
	
	Element getElement() {
		return element;
	}

	@Override
	ICNode getSelf() {
		return element;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		setSelfClaw();
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		element.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		element.toXml(sb, indent);
	}

	@Override
	IDCNode cloneDSelf(IXNode parent) {
		return new DElement(parent, element.getQName().getPrefix(), getName(), getPath(), element.getSelfClosing());
	}

}

package org.eop.jmx.dynamic.builder;

import org.eop.claw.Claw;
import org.eop.jmx.builder.xml.Attribute;
import org.eop.jmx.builder.xml.CData;
import org.eop.jmx.builder.xml.CDatas;
import org.eop.jmx.builder.xml.Comment;
import org.eop.jmx.builder.xml.Declaration;
import org.eop.jmx.builder.xml.DocType;
import org.eop.jmx.builder.xml.Element;
import org.eop.jmx.builder.xml.ICNode;
import org.eop.jmx.builder.xml.IDocument;
import org.eop.jmx.builder.xml.IElement;
import org.eop.jmx.builder.xml.Namespace;
import org.eop.jmx.builder.xml.Text;
import org.eop.jmx.builder.xml.Texts;
import org.eop.jmx.dynamic.builder.xml.DCData;
import org.eop.jmx.dynamic.builder.xml.DCDatas;
import org.eop.jmx.dynamic.builder.xml.DComment;
import org.eop.jmx.dynamic.builder.xml.DDocument;
import org.eop.jmx.dynamic.builder.xml.DElement;
import org.eop.jmx.dynamic.builder.xml.DElements;
import org.eop.jmx.dynamic.builder.xml.DText;
import org.eop.jmx.dynamic.builder.xml.DTexts;
/**
 * lixinjie 2016-12-26
 */
public class XmlDynamicBuilder {

	private char declQuote;
	private char doctQuote;
	private char attrQuote;
	private int capacity;
	
	private IDocument document;
	private ICNode currentElement;
	
	public XmlDynamicBuilder(Claw claw) {
		this(1024, claw);
	}
	
	public XmlDynamicBuilder(int capacity, Claw claw) {
		this('"', '"', '"', capacity, claw);
	}
	
	public XmlDynamicBuilder(char declQuote, char doctQuote, char attrQuote, Claw claw) {
		this(declQuote, doctQuote, attrQuote, 1024, claw);
	}
	
	public XmlDynamicBuilder(char declQuote, char doctQuote, char attrQuote, int capacity, Claw claw) {
		this.declQuote = declQuote;
		this.doctQuote = doctQuote;
		this.attrQuote = attrQuote;
		this.capacity = capacity;
		document = new DDocument(null, "", "", claw);
	}
	
	public XmlDynamicBuilder declaration() {
		return declaration("1.0", "UTF-8");
	}
	
	public XmlDynamicBuilder declaration(String version, String encoding) {
		document.setDeclaration(new Declaration(document, version, encoding, declQuote));
		return this;
	}
	
	public XmlDynamicBuilder docType(String name, String publicID, String systemID, String... externalDTDs) {
		document.setDocType(new DocType(document, name, doctQuote, publicID, systemID, externalDTDs));
		return this;
	}
	
	public XmlDynamicBuilder rootElement(String name) {
		return rootElement(null, name);
	}
	
	public XmlDynamicBuilder rootElement(String namespace, String name) {
		return rootElement(namespace, name, false);
	}
	
	public XmlDynamicBuilder rootElement(String namespace, String name, boolean selfClosing) {
		Element root = new Element(document, namespace, name, selfClosing);
		document.setRoot(root);
		currentElement = root;
		return this;
	}
	
	public XmlDynamicBuilder element(String name) {
		return element(null, name);
	}
	
	public XmlDynamicBuilder element(String namespace, String name) {
		return element(namespace, name, false);
	}
	
	public XmlDynamicBuilder element(String namespace, String name, boolean selfClosing) {
		Element element = new Element(currentElement, namespace, name, selfClosing);
		currentElement.addChild(element);
		currentElement = element;
		return this;
	}
	
	public XmlDynamicBuilder namespace(String prefix, String uri) {
		((IElement)currentElement).addNamespace(new Namespace(currentElement, prefix, uri, attrQuote));
		return this;
	}
	
	public XmlDynamicBuilder attribute(String name, String value) {
		return attribute(null, name, value);
	}
	
	public XmlDynamicBuilder attribute(String namespace, String name, String value) {
		((IElement)currentElement).addAttribute(new Attribute(currentElement, namespace, name, value, attrQuote));
		return this;
	}
	
	public XmlDynamicBuilder text(Object text) {
		currentElement.addChild(new Text(currentElement, text));
		return this;
	}
	
	public XmlDynamicBuilder text(String name, Object text) {
		return text(null, name, text);
	}
	
	public XmlDynamicBuilder text(String namespace, String name, Object text) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new Text(element, text));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynamicBuilder texts(String name, Object[] texts) {
		return texts(null, name, texts);
	}
	
	public XmlDynamicBuilder texts(String namespace, String name, Object[] texts) {
		currentElement.addChild(new Texts(currentElement, namespace, name, texts));
		return this;
	}
	
	public XmlDynamicBuilder cdata(Object cdata) {
		currentElement.addChild(new CData(currentElement, cdata));
		return this;
	}
	
	public XmlDynamicBuilder cdata(String name, Object cdata) {
		return cdata(null, name, cdata);
	}
	
	public XmlDynamicBuilder cdata(String namespace, String name, Object cdata) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new CData(element, cdata));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynamicBuilder cdatas(String name, Object[] cdatas) {
		return cdatas(null, name, cdatas);
	}
	
	public XmlDynamicBuilder cdatas(String namespace, String name, Object[] cdatas) {
		currentElement.addChild(new CDatas(currentElement, namespace, name, cdatas));
		return this;
	}
	
	public XmlDynamicBuilder comment(Object comment) {
		currentElement.addChild(new Comment(currentElement, comment));
		return this;
	}
	
	public XmlDynamicBuilder end() {
		currentElement = (ICNode)currentElement.getParent();
		return this;
	}
	
	public XmlDynamicBuilder drootElement(String name, String path) {
		return drootElement(null, name, path);
	}
	
	public XmlDynamicBuilder drootElement(String namespace, String name, String path) {
		return drootElement(namespace, name, path, false);
	}
	
	public XmlDynamicBuilder drootElement(String namespace, String name, String path, boolean selfClosing) {
		DElement droot = new DElement(document, namespace, name, path, selfClosing);
		document.setRoot(droot);
		currentElement = droot;
		return this;
	}
	
	public XmlDynamicBuilder delement(String name, String path) {
		return delement(null, name, path);
	}
	
	public XmlDynamicBuilder delement(String namespace, String name, String path) {
		return delement(namespace, name, path, false);
	}
	
	public XmlDynamicBuilder delement(String namespace, String name, String path, boolean selfClosing) {
		DElement delement = new DElement(currentElement, namespace, name, path, selfClosing);
		currentElement.addChild(delement);
		currentElement = delement;
		return this;
	}
	
	public XmlDynamicBuilder delements(String name, String path) {
		return delements(null, name, path);
	}
	
	public XmlDynamicBuilder delements(String namespace, String name, String path) {
		return delements(namespace, name, path, false);
	}
	
	public XmlDynamicBuilder delements(String namespace, String name, String path, boolean selfClosing) {
		DElements delements = new DElements(currentElement, namespace, name, path, selfClosing);
		currentElement.addChild(delements);
		currentElement = delements;
		return this;
	}
	
	public XmlDynamicBuilder dtext(String path) {
		currentElement.addChild(new DText(currentElement, path));
		return this;
	}
	
	public XmlDynamicBuilder dtext(String name, String path) {
		return dtext(null, name, path);
	}
	
	public XmlDynamicBuilder dtext(String namespace, String name, String path) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new DText(element, path));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynamicBuilder dtexts(String name, String path) {
		return dtexts(null, name, path);
	}
	
	public XmlDynamicBuilder dtexts(String namespace, String name, String path) {
		currentElement.addChild(new DTexts(currentElement, namespace, name, path));
		return this;
	}
	
	public XmlDynamicBuilder dcdata(String path) {
		currentElement.addChild(new DCData(currentElement, path));
		return this;
	}
	
	public XmlDynamicBuilder dcdata(String name, String path) {
		return dcdata(null, name, path);
	}
	
	public XmlDynamicBuilder dcdata(String namespace, String name, String path) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new DCData(element, path));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynamicBuilder dcdatas(String name, String path) {
		return dcdatas(null, name, path);
	}
	
	public XmlDynamicBuilder dcdatas(String namespace, String name, String path) {
		currentElement.addChild(new DCDatas(currentElement, namespace, name, path));
		return this;
	}
	
	public XmlDynamicBuilder dcomment(String path) {
		currentElement.addChild(new DComment(currentElement, path));
		return this;
	}
	
	public String toXml() {
		return toXml(false);
	}
	
	public String toXml(boolean format) {
		StringBuilder sb = new StringBuilder(capacity);
		if (format) {
			document.toXml(sb, 0);
		} else {
			document.toXml(sb);
		}
		return sb.toString();
	}
}

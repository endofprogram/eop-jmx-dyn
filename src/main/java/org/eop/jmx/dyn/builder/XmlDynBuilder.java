package org.eop.jmx.dyn.builder;

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
import org.eop.jmx.dyn.builder.xml.DCData;
import org.eop.jmx.dyn.builder.xml.DCDatas;
import org.eop.jmx.dyn.builder.xml.DComment;
import org.eop.jmx.dyn.builder.xml.DDocument;
import org.eop.jmx.dyn.builder.xml.DElement;
import org.eop.jmx.dyn.builder.xml.DElements;
import org.eop.jmx.dyn.builder.xml.DText;
import org.eop.jmx.dyn.builder.xml.DTexts;
/**
 * lixinjie 2016-12-26
 */
public class XmlDynBuilder {

	private char declQuote;
	private char doctQuote;
	private char attrQuote;
	private int capacity;
	
	private IDocument document;
	private ICNode currentElement;
	
	public XmlDynBuilder(Claw claw) {
		this(1024, claw);
	}
	
	public XmlDynBuilder(int capacity, Claw claw) {
		this('"', '"', '"', capacity, claw);
	}
	
	public XmlDynBuilder(char declQuote, char doctQuote, char attrQuote, Claw claw) {
		this(declQuote, doctQuote, attrQuote, 1024, claw);
	}
	
	public XmlDynBuilder(char declQuote, char doctQuote, char attrQuote, int capacity, Claw claw) {
		this.declQuote = declQuote;
		this.doctQuote = doctQuote;
		this.attrQuote = attrQuote;
		this.capacity = capacity;
		document = new DDocument(null, "", "", claw);
	}
	
	public XmlDynBuilder declaration() {
		return declaration("1.0", "UTF-8");
	}
	
	public XmlDynBuilder declaration(String version, String encoding) {
		document.setDeclaration(new Declaration(document, version, encoding, declQuote));
		return this;
	}
	
	public XmlDynBuilder docType(String name, String publicID, String systemID, String... externalDTDs) {
		document.setDocType(new DocType(document, name, doctQuote, publicID, systemID, externalDTDs));
		return this;
	}
	
	public XmlDynBuilder rootElement(String name) {
		return rootElement(null, name);
	}
	
	public XmlDynBuilder rootElement(String namespace, String name) {
		return rootElement(namespace, name, false);
	}
	
	public XmlDynBuilder rootElement(String namespace, String name, boolean selfClosing) {
		Element root = new Element(document, namespace, name, selfClosing);
		document.setRoot(root);
		currentElement = root;
		return this;
	}
	
	public XmlDynBuilder element(String name) {
		return element(null, name);
	}
	
	public XmlDynBuilder element(String namespace, String name) {
		return element(namespace, name, false);
	}
	
	public XmlDynBuilder element(String namespace, String name, boolean selfClosing) {
		Element element = new Element(currentElement, namespace, name, selfClosing);
		currentElement.addChild(element);
		currentElement = element;
		return this;
	}
	
	public XmlDynBuilder namespace(String prefix, String uri) {
		((IElement)currentElement).addNamespace(new Namespace(currentElement, prefix, uri, attrQuote));
		return this;
	}
	
	public XmlDynBuilder attribute(String name, String value) {
		return attribute(null, name, value);
	}
	
	public XmlDynBuilder attribute(String namespace, String name, String value) {
		((IElement)currentElement).addAttribute(new Attribute(currentElement, namespace, name, value, attrQuote));
		return this;
	}
	
	public XmlDynBuilder text(Object text) {
		currentElement.addChild(new Text(currentElement, text));
		return this;
	}
	
	public XmlDynBuilder text(String name, Object text) {
		return text(null, name, text);
	}
	
	public XmlDynBuilder text(String namespace, String name, Object text) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new Text(element, text));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynBuilder texts(String name, Object[] texts) {
		return texts(null, name, texts);
	}
	
	public XmlDynBuilder texts(String namespace, String name, Object[] texts) {
		currentElement.addChild(new Texts(currentElement, namespace, name, texts));
		return this;
	}
	
	public XmlDynBuilder cdata(Object cdata) {
		currentElement.addChild(new CData(currentElement, cdata));
		return this;
	}
	
	public XmlDynBuilder cdata(String name, Object cdata) {
		return cdata(null, name, cdata);
	}
	
	public XmlDynBuilder cdata(String namespace, String name, Object cdata) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new CData(element, cdata));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynBuilder cdatas(String name, Object[] cdatas) {
		return cdatas(null, name, cdatas);
	}
	
	public XmlDynBuilder cdatas(String namespace, String name, Object[] cdatas) {
		currentElement.addChild(new CDatas(currentElement, namespace, name, cdatas));
		return this;
	}
	
	public XmlDynBuilder comment(Object comment) {
		currentElement.addChild(new Comment(currentElement, comment));
		return this;
	}
	
	public XmlDynBuilder end() {
		currentElement = (ICNode)currentElement.getParent();
		return this;
	}
	
	public XmlDynBuilder drootElement(String name, String path) {
		return drootElement(null, name, path);
	}
	
	public XmlDynBuilder drootElement(String namespace, String name, String path) {
		return drootElement(namespace, name, path, false);
	}
	
	public XmlDynBuilder drootElement(String namespace, String name, String path, boolean selfClosing) {
		DElement droot = new DElement(document, namespace, name, path, selfClosing);
		document.setRoot(droot);
		currentElement = droot;
		return this;
	}
	
	public XmlDynBuilder delement(String name, String path) {
		return delement(null, name, path);
	}
	
	public XmlDynBuilder delement(String namespace, String name, String path) {
		return delement(namespace, name, path, false);
	}
	
	public XmlDynBuilder delement(String namespace, String name, String path, boolean selfClosing) {
		DElement delement = new DElement(currentElement, namespace, name, path, selfClosing);
		currentElement.addChild(delement);
		currentElement = delement;
		return this;
	}
	
	public XmlDynBuilder delements(String name, String path) {
		return delements(null, name, path);
	}
	
	public XmlDynBuilder delements(String namespace, String name, String path) {
		return delements(namespace, name, path, false);
	}
	
	public XmlDynBuilder delements(String namespace, String name, String path, boolean selfClosing) {
		DElements delements = new DElements(currentElement, namespace, name, path, selfClosing);
		currentElement.addChild(delements);
		currentElement = delements;
		return this;
	}
	
	public XmlDynBuilder dtext(String path) {
		currentElement.addChild(new DText(currentElement, path));
		return this;
	}
	
	public XmlDynBuilder dtext(String name, String path) {
		return dtext(null, name, path);
	}
	
	public XmlDynBuilder dtext(String namespace, String name, String path) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new DText(element, path));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynBuilder dtexts(String name, String path) {
		return dtexts(null, name, path);
	}
	
	public XmlDynBuilder dtexts(String namespace, String name, String path) {
		currentElement.addChild(new DTexts(currentElement, namespace, name, path));
		return this;
	}
	
	public XmlDynBuilder dcdata(String path) {
		currentElement.addChild(new DCData(currentElement, path));
		return this;
	}
	
	public XmlDynBuilder dcdata(String name, String path) {
		return dcdata(null, name, path);
	}
	
	public XmlDynBuilder dcdata(String namespace, String name, String path) {
		Element element = new Element(currentElement, namespace, name, false);
		element.addChild(new DCData(element, path));
		currentElement.addChild(element);
		return this;
	}
	
	public XmlDynBuilder dcdatas(String name, String path) {
		return dcdatas(null, name, path);
	}
	
	public XmlDynBuilder dcdatas(String namespace, String name, String path) {
		currentElement.addChild(new DCDatas(currentElement, namespace, name, path));
		return this;
	}
	
	public XmlDynBuilder dcomment(String path) {
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

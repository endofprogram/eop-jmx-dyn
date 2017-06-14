package org.eop.jmx.dyn.builder;

import org.eop.claw.Claw;
import org.eop.jmx.builder.json.Bole;
import org.eop.jmx.builder.json.Bough;
import org.eop.jmx.builder.json.Fork;
import org.eop.jmx.builder.json.ICNode;
import org.eop.jmx.builder.json.Leaf;
import org.eop.jmx.builder.json.Stick;
import org.eop.jmx.builder.json.Trunk;
import org.eop.jmx.builder.json.Twig;
import org.eop.jmx.dyn.builder.json.DBole;
import org.eop.jmx.dyn.builder.json.DBough;
import org.eop.jmx.dyn.builder.json.DFork;
import org.eop.jmx.dyn.builder.json.DLeaf;
import org.eop.jmx.dyn.builder.json.DRoot;
import org.eop.jmx.dyn.builder.json.DStick;
import org.eop.jmx.dyn.builder.json.DTrunk;
import org.eop.jmx.dyn.builder.json.DTwig;
/**
 * lixinjie 2016-12-26
 */
public class JsonDynBuilder {

	private ICNode root;
	private ICNode currentNode;
	private int capacity;
	
	public JsonDynBuilder(Claw claw) {
		this(claw, 512);
	}
	
	public JsonDynBuilder(Claw claw, int capacity) {
		this.capacity = capacity;
		root = new DRoot(null, "", "", claw);
		currentNode = root;
	}
	
	public JsonDynBuilder leaf(String name, Object value) {
		currentNode.addChild(new Leaf(currentNode, name, value));
		return this;
	}
	
	public JsonDynBuilder stick(String name, Object... values) {
		currentNode.addChild(new Stick(currentNode, name, values));
		return this;
	}
	
	public JsonDynBuilder fork(String name) {
		Fork fork = new Fork(currentNode, name);
		currentNode.addChild(fork);
		currentNode = fork;
		return this;
	}
	
	public JsonDynBuilder bole(String name) {
		Bole bole = new Bole(currentNode, name);
		currentNode.addChild(bole);
		currentNode = bole;
		return this;
	}
	
	public JsonDynBuilder twig(Object... values) {
		currentNode.addChild(new Twig(currentNode, values));
		return this;
	}
	
	public JsonDynBuilder trunk() {
		Trunk trunk = new Trunk(currentNode);
		currentNode.addChild(trunk);
		currentNode = trunk;
		return this;
	}
	
	public JsonDynBuilder bough() {
		Bough bough = new Bough(currentNode);
		currentNode.addChild(bough);
		currentNode = bough;
		return this;
	}
	
	public JsonDynBuilder end() {
		currentNode = (ICNode)currentNode.getParent();
		return this;
	}
	
	public JsonDynBuilder dleaf(String name, String path) {
		currentNode.addChild(new DLeaf(currentNode, name, path));
		return this;
	}
	
	public JsonDynBuilder dstick(String name, String path) {
		currentNode.addChild(new DStick(currentNode, name, path));
		return this;
	}
	
	public JsonDynBuilder dfork(String name, String path) {
		DFork dfork = new DFork(currentNode, name, path);
		currentNode.addChild(dfork);
		currentNode = dfork;
		return this;
	}
	
	public JsonDynBuilder dbole(String name, String path) {
		DBole dbole = new DBole(currentNode, name, path);
		currentNode.addChild(dbole);
		currentNode = dbole;
		return this;
	}
	
	public JsonDynBuilder dtwig(String path) {
		currentNode.addChild(new DTwig(currentNode, path));
		return this;
	}
	
	public JsonDynBuilder dtrunk(String path) {
		DTrunk dtrunk = new DTrunk(currentNode, path);
		currentNode.addChild(dtrunk);
		currentNode = dtrunk;
		return this;
	}
	
	public JsonDynBuilder dbough(String path) {
		DBough dbough = new DBough(currentNode, path);
		currentNode.addChild(dbough);
		currentNode = dbough;
		return this;
	}
	
	public String toJson() {
		return toJson(false);
	}
	
	public String toJson(boolean format) {
		StringBuilder sb = new StringBuilder(capacity);
		if (format) {
			root.toJson(sb, 0);
		} else {
			root.toJson(sb);
		}
		return sb.toString();
	}
	
}

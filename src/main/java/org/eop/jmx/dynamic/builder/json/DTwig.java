package org.eop.jmx.dynamic.builder.json;

import org.eop.jmx.builder.json.IJNode;
import org.eop.jmx.builder.json.Twig;

/**
 * lixinjie 2016-12-26
 */
public class DTwig extends DJNode {

	private Twig twig;
	
	public DTwig(IJNode parent, String path) {
		super(parent, "", path);
	}
	
	Twig getTwig() {
		return twig;
	}
	
	@Override
	void prepare() {
		fetchParentClaw();
		twig = new Twig(null, getParentClaw().getList(getPath()).toArray());
	}

	@Override
	public void toJson(StringBuilder sb) {
		prepare();
		twig.toJson(sb);
	}

	@Override
	public void toJson(StringBuilder sb, int indent) {
		prepare();
		twig.toJson(sb, indent);
	}

	@Override
	public IJNode deepClone(IJNode parent) {
		return new DTwig(parent, getPath());
	}

}

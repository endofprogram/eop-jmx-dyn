package org.eop.jmx.dynamic.builder.xml;

import org.eop.jmx.builder.xml.Comment;
import org.eop.jmx.builder.xml.IXNode;

/**
 * lixinjie 2016-12-26
 */
public class DComment extends DXNode {

	private Comment comment;
	
	public DComment(IXNode parent, String path) {
		super(parent, "", path);
	}
	
	Comment getComment() {
		return comment;
	}

	@Override
	void prepare() {
		fetchParentClaw();
		comment = new Comment(null, getParentClaw().get(getPath()));
	}

	@Override
	public void toXml(StringBuilder sb) {
		prepare();
		comment.toXml(sb);
	}

	@Override
	public void toXml(StringBuilder sb, int indent) {
		prepare();
		comment.toXml(sb, indent);
	}

	@Override
	public DComment deepClone(IXNode parent) {
		return new DComment(parent, getPath());
	}

}

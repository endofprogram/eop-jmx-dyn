package org.eop.jmx.builder.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eop.claw.Claw;
import org.eop.jmx.builder.XmlBuilder;

import junit.framework.TestCase;

public class XmlBuilderTest extends TestCase {

	Claw claw;
	
	@Override
	protected void setUp() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("res_code", "0");
		map.put("res_desc", "Processing the request succeeded!");
		
		Map<String, Object> result = new HashMap<>();
		map.put("result", result);
		
		List<Map<String, Object>> list1 = new ArrayList<>();
		result.put("MEALINFOINLIST", list1);
		
		Map<String, Object> map1 = new HashMap<>();
		list1.add(map1);
		map1.put("Col_1", "008W");
		map1.put("Col_2", "和校园赠20分钟本地主叫");
		
		Map<String, Object> map2 = new HashMap<>();
		list1.add(map2);
		map2.put("Col_1", "06HC");
		map2.put("Col_2", "石家庄2011畅聊卡");
		
		Map<String, Object> map3 = new HashMap<>();
		list1.add(map3);
		map3.put("Col_1", "008W");
		map3.put("Col_2", "石家庄2012畅聊卡");
		
		Map<String, Object> map4 = new HashMap<>();
		list1.add(map4);
		map4.put("Col_1", "06HC");
		map4.put("Col_2", "石家庄2013畅聊卡");
		
		result.put("MEALINFOUPLIST", Arrays.asList("1111", "2222", "3333", "4444"));
		
		Map<String, Object> map5 = new HashMap<>();
		result.put("MEALINFOGPRSLIST", map5);
		map5.put("Col_1", "1");
		map5.put("Col_2", "2");
		map5.put("Col_3", "3");
		map5.put("Col_4", "4");
		
		claw = new Claw(map);
	}
	
	public void testToXml1() {
		XmlBuilder xb = new XmlBuilder();
		xb.document().rootElement("a").element("b").text("bbb")
												   .end()
									  .element("c").cdata("ccc")
									  			   .end()
									  .comment("ddd");
		assertEquals("<a><b>bbb</b><c><![CDATA[ccc]]></c><!--ddd--></a>", xb.toXml());
	}
	
	public void testToXml2() {
		XmlBuilder xb = new XmlBuilder();
		xb.document().rootElement("a").element("b").text("1111")
												   .end()
									  .element("b").text("2222")
									  			   .end()
									  .element("b").text("3333")
									  			   .end();
		assertEquals("<a><b>1111</b><b>2222</b><b>3333</b></a>", xb.toXml());
	}
	
	public void testToXml3() {
		XmlBuilder xb = new XmlBuilder();
		xb.document().rootElement("a").texts("b", new Object[]{"1111", "2222", "3333"});
		assertEquals("<a><b>1111</b><b>2222</b><b>3333</b></a>", xb.toXml());
	}
	
	public void testToXml4() {
		XmlBuilder xb = new XmlBuilder();
		xb.document().rootElement("a").cdatas("b", new Object[]{"1111", "2222", "3333"});
		assertEquals("<a><b><![CDATA[1111]]></b><b><![CDATA[2222]]></b><b><![CDATA[3333]]></b></a>", xb.toXml());
	}
	
	public void testToXml5() {
		XmlBuilder xb = new XmlBuilder();
		xb.ddocument(claw).rootElement("response").element("res_code").dtext("res_code<>")
															  		  .end()
												  .dtext("res_desc", "res_desc<>");
		assertEquals("<response><res_code>0</res_code><res_desc>Processing the request succeeded!</res_desc></response>", xb.toXml());
	}
	
	public void testToXml6() {
		XmlBuilder xb = new XmlBuilder();
		xb.ddocument(claw).drootElement("response", "result{}").element("aa").dtexts("a", "MEALINFOUPLIST[]")
															   .end();
		assertEquals("<response><aa><a>1111</a><a>2222</a><a>3333</a><a>4444</a></aa></response>", xb.toXml());
	}
	
	public void testToXml7() {
		XmlBuilder xb = new XmlBuilder();
		xb.ddocument(claw).drootElement("response", "result{}").delement("aa", "MEALINFOGPRSLIST{}").dtext("a", "Col_1<>")
																									.dtext("b", "Col_2<>")
																									.dtext("c", "Col_3<>")
																									.dtext("d", "Col_4<>")
																									.end()
															   .end();
		assertEquals("<response><aa><a>1</a><b>2</b><c>3</c><d>4</d></aa></response>", xb.toXml());
	}
	
	public void testToXml8() {
		XmlBuilder xb = new XmlBuilder();
		xb.ddocument(claw).drootElement("response", "result{}").delements("aaa", "MEALINFOINLIST[]").delement("aa", "0()").dtext("b", "Col_1<>")
																														  .dtext("c", "Col_2<>")
																														  .end()
																									 .end()
																									
															   .end();
		assertEquals("<response><aaa><aa><b>008W</b><c>和校园赠20分钟本地主叫</c></aa><aa><b>06HC</b><c>石家庄2011畅聊卡</c></aa><aa><b>008W</b><c>石家庄2012畅聊卡</c></aa><aa><b>06HC</b><c>石家庄2013畅聊卡</c></aa></aaa></response>", xb.toXml());
	}
}

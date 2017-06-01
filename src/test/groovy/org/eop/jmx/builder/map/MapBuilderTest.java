package org.eop.jmx.builder.map;

import java.util.List;
import java.util.Map;

import org.eop.jmx.builder.MapBuilder;

import junit.framework.TestCase;

public class MapBuilderTest extends TestCase {

	public void testToMap1() {
		MapBuilder mb = new MapBuilder();
		mb.root().keyval("res_code", "0")
				 .keyval("res_desc", "Processing the request succeeded!")
				 .keymap("result").keymaps("MEALINFOINLIST").keymap("MEALINFOIN").keyval("Col_1", "008W")
				 																 .keyval("Col_2", "和校园赠20分钟本地主叫")
				 																 .end()
				 										    .keymap("MEALINFOIN").keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2011畅聊卡")
				 																 .end()
				 											.keymap("MEALINFOIN").keyval("Col_1", "008W")
				 																 .keyval("Col_2", "石家庄2012畅聊卡")
				 																 .end()
				 											.keymap("MEALINFOIN").keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2013畅聊卡")
				 																 .end()
				 											.end()
				 				 .keyvals("MEALINFOUPLIST", "1111", "2222", "3333", "4444")
				 				 .keymap("MEALINFOGPRSLIST").keyval("", "")
				 				 							.keyval("", "")
				 				 							.keyval("", "")
				 				 							.keyval("", "")
				 				 							.end()
				 				 .end();
		assertEquals("0", mb.toMap().get("res_code"));
	}
	public void testToMap2() {
		MapBuilder mb = new MapBuilder();
		mb.root().keyval("res_code", "0")
				 .keyval("res_desc", "Processing the request succeeded!")
				 .keymap("result").keymaps("MEALINFOINLIST").keymap("MEALINFOIN").keyval("Col_1", "008W")
				 																 .keyval("Col_2", "和校园赠20分钟本地主叫")
				 																 .end()
				 										    .keymap("MEALINFOIN").keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2011畅聊卡")
				 																 .end()
				 											.keymap("MEALINFOIN").keyval("Col_1", "008W")
				 																 .keyval("Col_2", "石家庄2012畅聊卡")
				 																 .end()
				 											.keymap("MEALINFOIN").keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2013畅聊卡")
				 																 .end()
				 											.end()
				 				 .keyvals("MEALINFOUPLIST", "1111", "2222", "3333", "4444")
				 				 .keymap("MEALINFOGPRSLIST").keyval("", "")
				 				 							.keyval("", "")
				 				 							.keyval("", "")
				 				 							.keyval("", "")
				 				 							.end()
				 				 .end();
		assertEquals("Processing the request succeeded!", mb.toMap().get("res_desc"));
	}
	
	@SuppressWarnings("unchecked")
	public void testToMap3() {
		MapBuilder mb = new MapBuilder();
		mb.root().keyval("res_code", "0")
				 .keyval("res_desc", "Processing the request succeeded!")
				 .keymap("result").keymaps("MEALINFOINLIST").itemmap().keyval("Col_1", "008W")
				 																 .keyval("Col_2", "和校园赠20分钟本地主叫")
				 																 .end()
				 										    .itemmap().keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2011畅聊卡")
				 																 .end()
				 											.itemmap().keyval("Col_1", "008W")
				 																 .keyval("Col_2", "石家庄2012畅聊卡")
				 																 .end()
				 											.itemmap().keyval("Col_1", "06HC")
				 																 .keyval("Col_2", "石家庄2013畅聊卡")
				 																 .end()
				 											.end()
				 				 .keyvals("MEALINFOUPLIST", "1111", "2222", "3333", "4444")
				 				 .keymap("MEALINFOGPRSLIST").keyval("Col_1", "1")
				 				 							.keyval("Col_2", "2")
				 				 							.keyval("Col_3", "3")
				 				 							.keyval("Col_4", "4")
				 				 							.end()
				 				 .end();
		assertEquals("[1111, 2222, 3333, 4444]", ((List<String>)((Map<String, Object>)mb.toMap().get("result")).get("MEALINFOUPLIST")).toString());
	}
	
	@SuppressWarnings("unchecked")
	public void testToMap4() {
		MapBuilder mb = new MapBuilder();
		mb.root().keyval("res_code", "0")
				 .keyval("res_desc", "Processing the request succeeded!")
				 .keymap("result").keymaps("MEALINFOINLIST").itemmap().keyval("Col_1", "008W")
				 													  .keyval("Col_2", "和校园赠20分钟本地主叫")
				 													  .end()
				 										    .itemmap().keyval("Col_1", "06HC")
				 													  .keyval("Col_2", "石家庄2011畅聊卡")
				 													  .end()
				 											.itemmap().keyval("Col_1", "008W")
				 													  .keyval("Col_2", "石家庄2012畅聊卡")
				 													  .end()
				 											.itemmap().keyval("Col_1", "06HC")
				 													  .keyval("Col_2", "石家庄2013畅聊卡")
				 													  .end()
				 											.end()
				 				 .keyvals("MEALINFOUPLIST", "1111", "2222", "3333", "4444")
				 				 .keymap("MEALINFOGPRSLIST").keyval("Col_1", "1")
				 				 							.keyval("Col_2", "2")
				 				 							.keyval("Col_3", "3")
				 				 							.keyval("Col_4", "4")
				 				 							.end()
				 				 .end();
		assertEquals("{Col_2=石家庄2013畅聊卡, Col_1=06HC}", ((List<Map<String, Object>>)((Map<String, Object>)mb.toMap().get("result")).get("MEALINFOINLIST")).get(3).toString());
	}
}

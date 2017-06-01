package org.eop.jmx.builder

import org.eop.chassis.test.AbstractCommonTest
import org.eop.jmx.builder.JsonBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author lixinjie
 */
class JsonBuilderTest extends AbstractCommonTest {

	
	@Before
	void init() {
		claw = new Claw([res_code:'0', res_desc:'Processing the request succeeded!', result:[MEALINFOINLIST:[[Col_1:'008W', Col_2:'和校园赠20分钟本地主叫'], [Col_1:'06HC', Col_2:'石家庄2011畅聊卡'], [Col_1:'008W', Col_2:'石家庄2012畅聊卡'], [Col_1:'06HC', Col_2:'石家庄2013畅聊卡']], MEALINFOUPLIST:["1111", "2222", "3333", "4444"], MEALINFOGPRSLIST:[Col_1:'1', Col_2:'2', Col_3:'3', Col_4:'4'], list:[[[a:"a", b:"b"], [a:"b", b:"b"]], [[a:"c", b:'d'], [a:"e", b:'f']]], lst:[[[1, 2],[3, 4]] ,[[5, 6],[7, 8]]], lst1:[[[[1, 2], [3, 4]],[[5, 6],[7, 8]]] ,[[[9, 10], [11, 12]],[[13, 14], [15, 16]]]], lst2:[[[[[1,1], [2,2]], [[3,3], [4,4]]],[[[5,5], [6,6]],[[7,7], [8,8]]]] ,[[[[9,9], [10,10]], [[11,11], [12,12]]],[[[13,13], [14,14]], [[15,15], [16,16]]]]], lst3:[[[[[a:1, b:1], [a:2, b:2]], [[a:10, b:10], [a:20, b:20]]], [[[a:11, b:11], [a:21, b:21]], [[a:12, b:12], [a:22, b:22]]]], [[[[a:13, b:13], [a:23, b:23]], [[a:14, b:14], [a:24, b:24]]], [[[a:15, b:15], [a:25, b:25]], [[a:16, b:16], [a:26, b:26]]]]]]])
	}
	
	@Test
	void testToJson1() {
		def jb = new JsonBuilder();
		jb.leaf("a", "a")
				 .leaf("b", "b")
				 .stick("c", "c", "c", "c")
				 .fork("d").leaf("e", "e")
							.end()
				 .bole("f").bough().leaf("g", "g")
									.end()
							.bough().leaf("h", "h")
									   .end()
							.end()
				 .bole("g").trunk().bough().leaf("g", "g")
				 							.end()
								   .bough().leaf("g", "g")
								   		   .end()
								   .end()
						   .trunk().bough().leaf("g", "g")
						   				   .end()
									.end()
						   .end()
				.bole("h").twig(1, 2)
						  .twig("3", "4")
				 .end()
		Assert.assertEquals('{"a":"a","b":"b","c":["c","c","c"],"d":{"e":"e"},"f":[{"g":"g"},{"h":"h"}],"g":[[{"g":"g"},{"g":"g"}],[{"g":"g"}]],"h":[[1,2],["3","4"]]}', jb.toJson())
	}
	
	@Test
	void testToJson2() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dleaf("code", "res_code<>")
					  .dleaf("desc", "res_desc<>")
					  .dstick("up", "result{}.MEALINFOUPLIST[]")
					  .dfork("gprs", "result{}.MEALINFOGPRSLIST{}").dleaf("c1", "Col_1<>")
																	 .dleaf("c2", "Col_2<>")
																	 .dleaf("c3", "Col_3<>")
																	 .dleaf("c4", "Col_4<>")
																	 .end()
					  .end()
		Assert.assertEquals('{"code":"0","desc":"Processing the request succeeded!","up":["1111","2222","3333","4444"],"gprs":{"c1":"1","c2":"2","c3":"3","c4":"4"}}', jdb.toJson())
	}
	
	@Test
	void testToJson3() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.MEALINFOINLIST[]").dbough("0()").dleaf("c1", "Col_1<>")
																			.dleaf("c2", "Col_2<>")
																			.end()
															   .end()
					  .end()
		Assert.assertEquals('{"a":[{"c1":"008W","c2":"和校园赠20分钟本地主叫"},{"c1":"06HC","c2":"石家庄2011畅聊卡"},{"c1":"008W","c2":"石家庄2012畅聊卡"},{"c1":"06HC","c2":"石家庄2013畅聊卡"}]}', jdb.toJson())
	}
	
	@Test
	void testToJson4() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.bole("a").dtwig("result{}.MEALINFOUPLIST[]")
					 .dtwig("result{}.MEALINFOUPLIST[]")
		   .end()
	    Assert.assertEquals('{"a":[["1111","2222","3333","4444"],["1111","2222","3333","4444"]]}', jdb.toJson())
	}
	
	@Test
	void testToJson5() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.list[]").dtrunk("0()").dbough("0()").dleaf("a", "a<>")
																	 .dleaf("b", "b<>")
																	 .end()
													   .end()
										 .end()
		   .end()
		Assert.assertEquals('{"a":[[{"a":"a","b":"b"},{"a":"b","b":"b"}],[{"a":"c","b":"d"},{"a":"e","b":"f"}]]}', jdb.toJson())
	}
	
	@Test
	void testToJson6() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.lst[]").dtrunk("0()").dtwig("0()")
										.end()
		   .end()
		Assert.assertEquals('{"a":[[[1,2],[3,4]],[[5,6],[7,8]]]}', jdb.toJson())
	}
	
	@Test
	void testToJson7() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.lst1[]").dtrunk("0()").dtrunk("0()").dtwig("0()")
													  .end()
										 .end()
		   .end()
		Assert.assertEquals('{"a":[[[[1,2],[3,4]],[[5,6],[7,8]]],[[[9,10],[11,12]],[[13,14],[15,16]]]]}', jdb.toJson())
	}
	
	@Test
	void testToJson8() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.lst2[]").dtrunk("0()").dtrunk("0()").dtrunk("0()").dtwig("0()")
																	 .end()
													  .end()
										 .end()
		   .end()
		Assert.assertEquals('{"a":[[[[[1,1],[2,2]],[[3,3],[4,4]]],[[[5,5],[6,6]],[[7,7],[8,8]]]],[[[[9,9],[10,10]],[[11,11],[12,12]]],[[[13,13],[14,14]],[[15,15],[16,16]]]]]}', jdb.toJson())
	}
	
	@Test
	void testToJson9() {
		JsonDynamicBuilder jdb = new JsonDynamicBuilder(claw)
		jdb.dbole("a", "result{}.lst3[]").dtrunk("0()").dtrunk("0()").dtrunk("0()").dbough("0()").dleaf("a", "a<>")
																								 .dleaf("b", "b<>")
																				   .end()
																	 .end()
													  .end()
										 .end()
		   .end()
		Assert.assertEquals('{"a":[[[[{"a":1,"b":1},{"a":2,"b":2}],[{"a":10,"b":10},{"a":20,"b":20}]],[[{"a":11,"b":11},{"a":21,"b":21}],[{"a":12,"b":12},{"a":22,"b":22}]]],[[[{"a":13,"b":13},{"a":23,"b":23}],[{"a":14,"b":14},{"a":24,"b":24}]],[[{"a":15,"b":15},{"a":25,"b":25}],[{"a":16,"b":16},{"a":26,"b":26}]]]]}', jdb.toJson())
	}
}

package org.jbug.devping.tag;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.algs4.TST;
import edu.princeton.cs.introcs.StdIn;

public class TestTST {

	public static final String INPUT_WORD = "./src/test/resources/words.txt";
	static String TestData;
	static TST<Integer> st;

	@BeforeClass
	public static void setUp() throws Exception {
		TestData = INPUT_WORD;
		System.setIn(new FileInputStream(TestData));

		// build symbol table from standard input
		st = new TST<Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}

	}

	@Test
	public void testTST() throws Exception {

		// print results
		// for (String key : st.keys()) {
		// StdOut.println(key + " " + st.get(key));
		// }
		assertThat(st.get("zounds"), is(20065));

	}

	@Test
	public void testTST_prefixMatch() throws Exception {
		Iterator<String> actualData = st.prefixMatch("test").iterator();
		String[] expectedTestData = { "test", "testament", "testamentary",
				"testate", "testbed", "testes", "testicle", "testicular",
				"testify", "testimonial", "testimony", "testy" };
		List<String> expectedData = Arrays.asList(expectedTestData);

		// test testament testamentary testate testbed testes testicle
		// testicular testify testimonial testimony testy
		// test testament testamentary testate testbed testes testicle
		// testicular testify testimonial testimony testy
		int index=0;
		while(actualData.hasNext()){
			assertThat(actualData.next(), is(expectedData.get(index++)));
		}
	}

}

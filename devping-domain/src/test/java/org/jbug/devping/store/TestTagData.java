package org.jbug.devping.store;

import org.junit.Test;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class TestTagData {
	final String TEST_DATA_FILE = "/Users/jhouse/dev/git/devping/devping-domain/src/test/resources/tagTestData.txt";
	final int MAX_USER = 1000;
	final boolean GENERATE = false;
	String[] alphabet = new String[52];

	@Test
	public void CreateTestData() throws Exception {
		Properties dataFile = new Properties();
		Character character = 65;
		if (!Files.exists(Paths.get(TEST_DATA_FILE)) || GENERATE) {
			// Create string arrays which contains upper case and low case
			// alphabet.
			// It is the basic character to make test data.
			for (int alphabetIndex = 0; alphabetIndex < 52; alphabetIndex++) {
				if (character == 91) {
					character = 97;
				}
				String temp = character.toString();
				alphabet[alphabetIndex] = temp;
				// System.out.println("alphabet["+alphabetIndex+"] = " +
				// alphabet[alphabetIndex]);
				character++;
			}
			
			for (int i = 0; i < MAX_USER; i++)
				dataFile.put(makeTag(randomNum(1, 20)), getPersonTagData());
			dataFile.store(new FileOutputStream(TEST_DATA_FILE),
					"TestTagData_For_individual");
			
		}else{
			System.out.println("TestData exist : " +TEST_DATA_FILE);
		}
		
		assertNotNull(dataFile);
	}

    @Test
    public void testPerf() throws Exception{

    }

	private String getPersonTagData() {
		/*
		 * For individual, there are 5~20 tags. As for name, the length wil be 1
		 * to 20. As for store, the length will be 4 to 20. Test data include
		 * 100000 people.
		 */

		HashSet<String> tagList = new HashSet<>();
		int listSize = randomNum(5, 20);
		while (listSize-- > 0) {
			tagList.add(makeTag(randomNum(4, 20)));
		}

		StringBuilder personDataBuilder = new StringBuilder();
		for (String t : tagList) {
			personDataBuilder.append(t).append(",");
		}
		personDataBuilder.deleteCharAt(personDataBuilder.lastIndexOf(","));
		String personData = personDataBuilder.toString();
//		System.out.println(personData);
		return personData;
	}

	private int randomNum(int min, int max) {
		int num = min + (int) (Math.random() * max);
		// System.out.println(num);
		return num;
	}

	private String makeTag(int length) {
		StringBuilder tagBuilder = new StringBuilder();
		int index;
		while (length-- > 0) {
			index = randomNum(0, 52);
			tagBuilder.append(alphabet[index]);
		}
		String tag = tagBuilder.toString();
		// System.out.println(store);
		return tag;
	}
}

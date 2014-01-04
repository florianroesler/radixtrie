package de.snrlx.radix.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import de.snrlx.radix.RadixTrie;


class RadixTrieTest {

	@Test
	public void testAddWord() {
		RadixTrie radix = new RadixTrie();
		radix.addWord("ROTEBEETE");
		assertTrue(radix.contains("ROTEBEETE"));
		assertFalse(radix.contains("ROTEBEET"));
		assertFalse(radix.contains("R"));
		assertTrue(radix.count()==1);
	}
	
	@Test
	public void testCount() {
		RadixTrie radix = new RadixTrie();
		radix.addWord("ROTEBEETE");
		radix.addWord("ROTEBEETE");
		radix.addWord("ROTE");
		radix.addWord("RÖTEBEETE");
		radix.addWord("RÖTE");
		assertTrue("Count is "+String.valueOf(radix.count()),radix.count()==4);
	}

}

package de.snrlx.radix;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


class RadixBranch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Character, RadixBranch> subBranches;
	private boolean isEnd = false;
	
	public RadixBranch addBranch(char character, boolean isEnd){
		if(subBranches == null){
			subBranches = new HashMap<Character, RadixBranch>();
		}
		RadixBranch newBranch = new RadixBranch();
		newBranch.setEnd(isEnd);
		subBranches.put(character, newBranch);
		
		return newBranch;
	}
	
	public RadixBranch getSubBranch(char character) {
		if(subBranches == null){
			return null;
		}
		return subBranches.get(character);
	}
	
	public Set<Character> getAllSubBranches(){
		if(subBranches == null){
			return new HashSet<Character>();
		}
		return subBranches.keySet();
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}

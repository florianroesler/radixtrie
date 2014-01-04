package de.snrlx.radix;
import java.io.Serializable;
import java.util.Set;
import java.util.Stack;


public class RadixTrie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private RadixBranch root = new RadixBranch();
	private boolean removeNonAlphabets = true;

	public void addWord(String word){
		char[] characters = word.toCharArray();
		Stack<Character> charStack = new Stack<Character>();
		for(int i=characters.length-1;i>=0;i--){
			if(this.removeNonAlphabets && Character.isAlphabetic(characters[i])){
				charStack.add(characters[i]);
			}
		}
		if(charStack.size()==0) return;

		addTheWord(charStack, root);


	}

	private void addTheWord(Stack<Character> characters, RadixBranch branch){		
		char firstChar = characters.pop();
		boolean isEnd = characters.isEmpty();

		RadixBranch newBranch = branch.getSubBranch(firstChar);

		if(newBranch == null){
			newBranch = branch.addBranch(firstChar, isEnd);
		}else{
			newBranch.setEnd(isEnd);
		}


		if(isEnd){
			return;
		}

		addTheWord(characters, newBranch);
	}

	public void printTree(){
		printBranch("", root);
	}

	private void printBranch(String prefix, RadixBranch branch){
		Set<Character> keys = branch.getAllSubBranches();
		for(Character character:keys){
			RadixBranch subBranch = branch.getSubBranch(character);
			String newPrefix = prefix+character;
			if(subBranch.isEnd()){
				System.out.println(newPrefix);
			}
			printBranch(newPrefix, subBranch);
		}
	}

	public int count(){
		return countBranchElements(root);
	}

	private int countBranchElements(RadixBranch branch) {
		int sum = 0;
		Set<Character> keys = branch.getAllSubBranches();
		for(Character character:keys){
			RadixBranch subBranch = branch.getSubBranch(character);
			if(subBranch.isEnd()){
				sum++;
			}
			sum+=countBranchElements(subBranch);
		}
		return sum;
	}

	public boolean contains(String word){
		RadixBranch branch = root;
		for(char character:word.toCharArray()){
			RadixBranch subBranch = branch.getSubBranch(character);
			if(subBranch==null){
				return false;
			}
			branch = subBranch;
		}
		return branch.isEnd();
	}
}

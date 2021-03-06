package kruskal;

public class PairwiseDisjointSets {
	
	private static final int INITIAL_SINGLETON_SET_SIZE = 1;
	private int _numberOfElements;
	private int[] _parentTree;
	public PairwiseDisjointSets(int givenNumberOfElements) {
        this.setNumberOfElements(givenNumberOfElements);
        this.setParentTree(new int[this.getNumberOfElements()]);
        for (int rootOfSingletonSet = 0; rootOfSingletonSet < this.getNumberOfElements(); ++rootOfSingletonSet) {
            this.setSizeOfSetFor(rootOfSingletonSet, INITIAL_SINGLETON_SET_SIZE);
        }
    }
//	public int find(int aMember) {
//		if(!this.parentDoesExist(aMember)) {
//			return aMember;
//		}
//		return find(this.getParentTree()[aMember]);
//	}

	public int find(int aMember) {
		int rootCandidate = aMember;
		while(this.parentDoesExist(rootCandidate)) {
			rootCandidate = this.parentOf(rootCandidate);
		}
		int root = rootCandidate;
		
		int child = aMember;
		int parent = this.parentOf(child);
		if(parent >= 0) {
			while(parent !=root) {
				this.setParentOf(child, root);
				child = parent;
				parent = this.parentOf(child);
			}
		}
		return root;
	}
	
	public void union(int aMemberA, int aMemberB) {
		
		int rootOfSetA = find(aMemberA);
		int rootOfSetB = find(aMemberB);
		
		int sizeOfSetA = this.sizeOfSetFor(rootOfSetA);
		int sizeOfSetB = this.sizeOfSetFor(rootOfSetB);

        if(sizeOfSetA < sizeOfSetB) {
			this.setParentOf(rootOfSetA, rootOfSetB);
			this.setSizeOfSetFor(rootOfSetB, (sizeOfSetA + sizeOfSetB));
		}else {
			this.setParentOf(rootOfSetB, rootOfSetA);
			this.setSizeOfSetFor(rootOfSetA, (sizeOfSetA + sizeOfSetB));
		}
	}
//	public boolean union(int aMemberA, int aMemberB) {
//		int rootA = find(aMemberA);
//		int rootB = find(aMemberB);
//		if(rootA == rootB) {
//			return false;
//		}else{
//			if(this.sizeOfSetFor(rootA) > this.sizeOfSetFor(rootB)) {
//				this.setParentOf(rootB, rootA);
//			}else {
//				this.setParentOf(rootA, rootB);
//			}
//			return true;
//		}
//	}
	public int getNumberOfElements() {
		return _numberOfElements;
	}
	public void setNumberOfElements(int _numberOfElements) {
		this._numberOfElements = _numberOfElements;
	}
	public int[] getParentTree() {
		return _parentTree;
	}
	private int parentOf(int aMember) {
		return this.getParentTree()[aMember];
	}
	private void setParentOf(int aChildMember, int newParentMember) {
		this.getParentTree()[aChildMember] = newParentMember;
	}
	private boolean parentDoesExist(int aMember) {
		return (this.parentOf(aMember) >= 0);
	}
	private int sizeOfSetFor(int aRoot) {
		return (-this.parentOf(aRoot));
	}
	private void setSizeOfSetFor(int aRoot, int newSize) {
		this.setParentOf(aRoot, -newSize);
	}
	public void setParentTree(int[] _parentTree) {
		this._parentTree = _parentTree;
	}
}

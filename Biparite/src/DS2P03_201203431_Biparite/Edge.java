package DS2P03_201203431_Biparite;

public class Edge {
	private int _tailVertex;
	private int _headVertex;

	//getter �ȿ��� �ڽ��� getter�� ����ϸ� ������ �����ϱ�?
	// ����� ������ �����ϴ� 
	public Edge(int givenTailVertex, int givenHeadVertex) {
		this._tailVertex = givenTailVertex;
		this._headVertex = givenHeadVertex;
	}

	public void setTailVertex(int newTailVertex) {
		this._tailVertex = newTailVertex;
	}

	public int tailVertex() {
		return this._tailVertex;
	}

	public void setHeadVertex(int newHeadVertex) {
		this._headVertex = newHeadVertex;
	}
	public int headVertex() {
		return this._headVertex;
	}
}

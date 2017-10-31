package DS2P03_201203431_Biparite;

public class Edge {
	private int _tailVertex;
	private int _headVertex;

	//getter 안에서 자신의 getter를 사용하면 무엇이 문자일까?
	// 재귀의 오류에 빠집니다 
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

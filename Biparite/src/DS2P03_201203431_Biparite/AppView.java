package DS2P03_201203431_Biparite;

import java.util.Scanner;

public final class AppView { // final 이 상속을 막는다.
	private static Scanner scanner = new Scanner(System.in);

	private AppView() {

	}

	public static int inputNumberOfVertices() {
		int numberOfVertices;
		String scannedToken;
		while (true) {
			AppView.output("? vertex 수를 입력하시오");
			scannedToken = AppView.scanner.next();
			try {
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) Vertex 수 입력에 오류가 있습니다.: " + scannedToken);
			}
		}
	}
	public static int inputNumberOfEdges() {
		int numberOfVertices;
		String scannedToken;
		while (true) {
			AppView.output("? Edge 수를 입력하시오");
			scannedToken = AppView.scanner.next();
			try {
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) Edge 수 입력에 오류가 있습니다.: " + scannedToken);
			}
		}
	}
	public static int inputTailVertex() {
		int tailVertex;
		String scannedToken;
		while (true) {
			AppView.output("? tail vertex 수를 입력하시오");
			scannedToken = AppView.scanner.next();
			try {
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) tail vertex 수 입력에 오류가 있습니다.: " + scannedToken);
			}
		}
	}
	public static int inputHeadVertex() {
		int headVertex;
		String scannedToken;
		while (true) {
			AppView.output("? head vertex 수를 입력하시오");
			scannedToken = AppView.scanner.next();
			try {
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			} catch (NumberFormatException e) {
				AppView.outputLine("(오류) head vertex 수 입력에 오류가 있습니다.: " + scannedToken);
			}
		}
	}
	public static void outputLine(String string) {
		System.out.println(string);
	}

	public static void output(String string) {
		System.out.print(string);
	}

}

import org.junit.Test;

/**
 * テスト
 * @author tmftake
 */
public class Solve610Test {
	@Test
	public void testExample() {
		System.out.println("答え：" + solve(7,3) + "回");
	}
	@Test
	public void test1() {
		System.out.println("答え：" + solve(20,3) + "回");
	}
	@Test
	public void test2() {
		System.out.println("答え：" + solve(100,5) + "回");
	}
	/**
	 * 棒の切り分け問題を解く
	 * @param n 長さ(cm)
	 * @param m 人数
	 * @return 回数
	 */
	public int solve(int n, int m) {
		// 切り分けられた棒の数(最大)
		int bar = 1;
		// 回数
		int count = 0;
		// 切り分けられる最大の棒の数が長さを超えた場合の回数を求める
		while (n > bar) {
			count++;
			bar += (bar < m) ? bar : m;
			System.out.println(count +"回目で切り分けられた棒の数(最大):" + bar);
		}
		return count;
	}
}

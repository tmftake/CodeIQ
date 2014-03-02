import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Solve646Test {

	@Test
	public void test() throws Exception {
		System.out.println("答え：" + solve646());
	}

	public int solve646() throws Exception {
		// 上の句かなが一意となるための最大文字数
		int kaminokuKanaMaxLength = 0;
		// 下の句かなが一意となるための最大文字数
		int shimonokuKanaMaxLength = 0;
		// かるたをファイルから読込
		List<Card> all = readCardsFromFile();
		int kanaLength;
		for (Card e : all) {
			// 上の句かなが一意となる文字数を求める
			kanaLength = getUniqueKanaLength(e, all, Card.KAMINOKU, 0);
			kaminokuKanaMaxLength += kanaLength;

			// 下の句かなが一意となる文字数を求める
			kanaLength = getUniqueKanaLength(e, all, Card.SHIMONOKU, 0);
			shimonokuKanaMaxLength += kanaLength;
		}
		System.out.println("上の句が一意となる文字数:" + kaminokuKanaMaxLength);
		System.out.println("下の句が一意となる文字数:" + shimonokuKanaMaxLength);
		return kaminokuKanaMaxLength + shimonokuKanaMaxLength;
	}

	/**
	 * 句(かな)が一意となる文字数を求める
	 *
	 * @param e
	 *            対象かるた
	 * @param all
	 *            全かるた
	 * @param kamishimo
	 *            上の句/下の句
	 * @param length
	 *            チェック文字数
	 * @return 文字数
	 */
	private int getUniqueKanaLength(Card e, List<Card> all, boolean kamishimo,
			int length) {
		int count = 0;
		String substr = e.getKana(kamishimo).substring(0, length);
		for (Card c : all) {
			// 先頭一致するかるたが2枚以上ある場合、チェック文字数を増やして再帰チェック
			if (c.getKana(kamishimo).startsWith(substr)) {
				if (++count >= 2) {
					return getUniqueKanaLength(e, all, kamishimo, length + 1);
				}
			}
		}
		// 対象かるたが一意に特定できた場合、文字数を返却する
		System.out.println(e + "," + (kamishimo ? "上" : "下") + "," + substr
				+ "," + length + "文字");
		return length;
	}

	private List<Card> readCardsFromFile() throws Exception {
		List<Card> all = new ArrayList<Card>();
		BufferedReader br = new BufferedReader(new FileReader("hyakunin.csv"));
		// ヘッダ行を読み飛ばす
		String line = br.readLine();
		// データ行を読込
		while ((line = br.readLine()) != null) {
			all.add(new Card(line));
		}
		return all;
	}
}

/**
 * かるた.
 */
class Card {
	/** 上の句 */
	public static boolean KAMINOKU = true;
	/** 下の句 */
	public static boolean SHIMONOKU = false;
	/** 番号 */
	public int number;
	/** 上の句 */
	public String kaminoku;
	/** 下の句 */
	public String shimonoku;
	/** 上の句かな */
	public String kaminokuKana;
	/** 下の句かな */
	public String shimonokuKana;
	/** 作者 */
	public String sakusha;

	public Card(String line) {
		String[] split = line.split(",");
		if (split.length >= 5) {
			int count = 0;
			number = Integer.parseInt(split[count++]);
			kaminoku = split[count++];
			shimonoku = split[count++];
			kaminokuKana = split[count++];
			shimonokuKana = split[count++];
			sakusha = split[count++];
		}
	}

	public String getKana(boolean kamishimo) {
		return kamishimo == KAMINOKU ? kaminokuKana : shimonokuKana;
	}

	public String toString() {
		return "No." + number + ":" + kaminoku + " " + shimonoku + " "
				+ sakusha;
	}
}
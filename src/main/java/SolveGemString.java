import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SolveGemString {

	@Test
	public void testMinis_NoTarget() throws Exception {
		solveGemString("minis.txt", "minia.txt", null);
	}

	@Test
	public void testMinis_Target() throws Exception {
		solveGemString("minis.txt", "minia.txt", "minip.txt");
	}

	@Test
	public void testPrincess() throws Exception {
		solveGemString("gems.txt", "gema.txt", "princess.txt");
	}

	public void solveGemString(String inFile, String outFile, String targetFile) throws Exception {
		List<String> input = readBytesFromFile(inFile);
		Long count = 0L;
		String target = null;
		if (targetFile != null) {
			target = readFromFile(targetFile);
		}
		try {
			checkGemPatterns(input, count, "", target);
		} catch (Throwable t) {
			System.out.println(count);
		}
	}

	private Long checkGemPatterns(List<String> input, Long count, String prefixPattern, String target) {
		if (input.size() <= 0) {
			return count;
		}
		String prev = null;

		for (String p : input) {
			// 対象文字列を発見したら処理を抜ける
			if (count == null) {
				break;
			}
			// 入力より重複を省く
			if (prev != null && prev.equals(p)) {
				continue;
			}
			prev = p;
			String newPattern = prefixPattern + p;
			++count;
			// System.out.println(count + ":" + newPattern);
			// 対象文字列を発見したら処理を抜ける
			if (target != null && target.equals(newPattern)) {
				System.out.println(count + ":" + newPattern);
				return null;
			}
			List<String> newInput = new ArrayList<String>();
			newInput.addAll(input);
			newInput.remove(p);
			count = checkGemPatterns(newInput, count, newPattern, target);
		}
		return count;
	}

	private String readFromFile(String inFile) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		String line = br.readLine();
		return line;
	}

	private List<String> readBytesFromFile(String inFile) throws Exception {
		List<String> all = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		String line = br.readLine();
		for (char c : line.trim().toCharArray()) {
			all.add(new String(new char[]{c}));
		}
		return all;
	}

}

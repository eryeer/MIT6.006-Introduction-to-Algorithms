package document_distance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FindDistance {
	public static void main(String[] args) {
		test1();

	}

	private static void test1() {
		FindDistance f = new FindDistance();
		String path = "C://Users/eryeer/Desktop/MIT6.006 Introduction to Algorithms/problems/code_template/articles";
		Map<String, String> articles = f.getArticles(
				new HashMap<String, String>(), path);
		Map<String, Map<String, Integer>> documents = new HashMap<String, Map<String, Integer>>();
		for (String fileName : articles.keySet()) {
			Map<String, Integer> document = f.getWordDocument(articles
					.get(fileName));
			documents.put(fileName, document);
		}
		Map<String, Integer> target = documents.get("Algorithm");
		documents.remove("Algorithm");
		String closestArticle = f.findClosestArticle(target, documents);
		System.out.println(closestArticle);
	}

	private Map<String, String> getArticles(Map<String, String> map, String path) {
		File file = new File(path);
		if (file.exists()) {
			File[] childrenFiles = file.listFiles();
			if (childrenFiles != null && childrenFiles.length != 0) {
				for (File childFile : childrenFiles) {

					if (childFile.isFile()) {
						try {
							BufferedReader br = new BufferedReader(
									new FileReader(childFile));
							String content = "";
							String str = null;
							while ((str = br.readLine()) != null) {
								content += str + " ";
							}
							content = content.replaceAll("\\W+", " ")
									.toLowerCase();
							map.put(childFile.getName(), content);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						getArticles(map, childFile.getAbsolutePath());
					}
				}
			}
		}
		return map;
	}

	// O(n)
	public Map<String, Integer> getWordDocument(String s) {
		HashMap<String, Integer> document = new HashMap<String, Integer>();
		String[] words = s.split(" ");
		for (String word : words) {
			if (document.containsKey(word)) {
				document.put(word, document.get(word) + 1);
			} else {
				document.put(word, 1);
			}
		}
		return document;
	}

	// O(n)
	private long getInnerProduct(Map<String, Integer> d1,
			Map<String, Integer> d2) {
		long innerProduct = 0L;
		for (String word : d1.keySet()) {
			if (d2.containsKey(word)) {
				innerProduct += d1.get(word) * d2.get(word);
			}
		}
		return innerProduct;
	}

	public String findClosestArticle(Map<String, Integer> targetDocument,
			Map<String, Map<String, Integer>> documents) {
		long maxProduct = 0;
		String closestFile = null;
		for (String fileName : documents.keySet()) {
			long product = getInnerProduct(targetDocument,
					documents.get(fileName));
			if (product > maxProduct) {
				maxProduct = product;
				closestFile = fileName;
			}
		}
		return closestFile;
	}
}

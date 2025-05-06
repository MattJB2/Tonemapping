package matt.tonemap.config;

import java.io.*;

public class TonemapConfig {
	public static boolean enabled = true;

	private static final File CONFIG_FILE = new File("config/tonemap_config.txt");

	public static void load() {
		if (!CONFIG_FILE.exists()) return;

		try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
			String line = reader.readLine();
			if (line != null) {
				enabled = Boolean.parseBoolean(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
			writer.write(Boolean.toString(enabled));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

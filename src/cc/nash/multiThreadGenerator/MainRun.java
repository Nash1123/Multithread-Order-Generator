package cc.nash.multiThreadGenerator;

import java.io.*;
import java.util.*;

public class MainRun {		
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Get the inputs numThread & numOrders from scanner.
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the number of Threads:");
		int numThread = Integer.parseInt(in.nextLine());
		System.out.println("Please enter the number of Orders:");
		int numOrder = Integer.parseInt(in.nextLine());
		
		// Create insList and proList to store the whole information from the 2 input files to memory.
		List<Instrument> insList = new ArrayList<Instrument>();
		List<ProductGroup> proList = new ArrayList<ProductGroup>();
		
		String path = "C:/Users/user/Desktop/";
		FileReader fr = new FileReader(path + "instrument.csv");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			String[] strs = line.split(",");
			insList.add(new Instrument(strs[0], strs[1], Integer.parseInt(strs[2])));
		}
		fr = new FileReader(path + "product.csv");
		br = new BufferedReader(fr);
		while((line = br.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			String[] strs = line.split(",");
			proList.add(new ProductGroup(Integer.parseInt(strs[0]), strs[1]));
		}		
		if (br != null) br.close();
		if (fr != null) fr.close();
		
		// Create Threads and start it.
		for (int i = 0; i < numThread; i++) {
			OrderGenerator orderGenerator = new OrderGenerator(numOrder, insList, proList);
			Thread thread = new Thread(orderGenerator);
			thread.start();			
		}
		
		// Wait for all threads died.
		while (true) {
			if (Thread.activeCount() == 1) {
				break;
			}
			Thread.sleep(1000);
		}
		
		System.out.println("Done!");
		
		// Write the results to orders.csv from the result map.
		FileWriter fw = new FileWriter(path + "orders.csv");
		BufferedWriter bw = new BufferedWriter(fw);
		for (String key: Synchronize.result.keySet()) {
			bw.write(key + "\r\n");
			bw.write("Buy: " + Synchronize.result.get(key)[0].toString() + "\r\n");
			bw.write("Sell: " + Synchronize.result.get(key)[1].toString() + "\r\n");
		}
		if (bw != null) bw.close();
		if (fw != null) fw.close();		
	}
}

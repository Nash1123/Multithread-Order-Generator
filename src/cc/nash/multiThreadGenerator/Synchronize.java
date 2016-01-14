package cc.nash.multiThreadGenerator;

import java.util.*;

public class Synchronize {
	
	static Map<String, StringBuilder[]> result = new HashMap<String, StringBuilder[]>();
	static int curOrderNumber = 0;
	
	synchronized static void addOrder(String name, String order, int orderNumber) {
		if (curOrderNumber == orderNumber) return;
		if (!result.containsKey(name)) {
			StringBuilder[] sbs = new StringBuilder[2];
			sbs[0] = new StringBuilder();
			sbs[1] = new StringBuilder();
			result.put(name, sbs);
		}
		if (Math.random() < 0.5) {
			result.get(name)[0].append(order + ",");
		}else {
			result.get(name)[1].append(order + ",");
		}
		curOrderNumber++;
		return;
	}
}

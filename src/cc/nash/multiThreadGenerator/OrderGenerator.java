package cc.nash.multiThreadGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class OrderGenerator implements Runnable{
	
	int numOrder;
	List<Instrument> insList;
	List<ProductGroup> proList;
	
	public OrderGenerator(int numOrder, List<Instrument> insList, List<ProductGroup> proList) {
		super();
		this.numOrder = numOrder;
		this.insList = insList;
		this.proList = proList;
	}

	@Override
	public void run() {
		StringBuilder sb = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("#0.00");     
		sb.append(formatter.format(Math.random() * 20) + ":");
		sb.append(rand(50, 200));
		while(Synchronize.curOrderNumber < numOrder) {
			Synchronize.addOrder(insList.get(rand(0, insList.size() - 1)).getSymbol(), sb.toString(), numOrder);
		}
	}
	
	int rand(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

}

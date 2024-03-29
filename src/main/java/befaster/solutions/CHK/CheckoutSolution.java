package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        System.out.println(skus);
        int total = 0;
        Map<String, Integer> itens = new HashMap<>();
        for (int i = 0; i < skus.length(); i++) {
            String iten = String.valueOf(i);
            if (itens.get(iten) != null) {
                int quant = itens.get(iten);
                quant++;
                itens.put(iten, quant);
            } else {
                itens.put(iten, 1);
            }
        }
        for (String sku : itens.keySet()) {
            System.out.println(sku);
            System.out.println(itens.get(sku));
            int quantSpecialOffers;
            int quantNormalValue;
            switch (sku) {
                case "A" -> {
                    quantSpecialOffers = itens.get(sku) / 3;
                    quantNormalValue = itens.get(sku) % 3;
                    total += quantSpecialOffers * 130 + quantNormalValue * 50;
                }
                case "B" -> {
                    quantSpecialOffers = itens.get(sku) / 2;
                    quantNormalValue = itens.get(sku) % 2;
                    total += quantSpecialOffers * 45 + quantNormalValue * 30;
                }
                case "C" -> total += itens.get(sku) * 20;
                case "D" -> total += itens.get(sku) * 15;
            }
            System.out.println(total);
        }
        return total;
    }
}

package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        System.out.println("SKUS: " + skus);
        int total = 0;
        Map<String, Integer> itens = new HashMap<>();
        for (int i = 0; i < skus.length(); i++) {
            String iten;
            try {
                iten = String.valueOf(skus.charAt(i));
            } catch (Exception e) {
                return -1;
            }
            if (itens.get(iten) != null) {
                int quant = itens.get(iten);
                quant++;
                itens.put(iten, quant);
            } else {
                itens.put(iten, 1);
            }
        }
        for (String sku : itens.keySet()) {
            System.out.println(sku + ": " + itens.get(sku));
            int quantSpecialOffersOne;
            int quantSpecialOffersTwo;
            int quantNormalValue;
            switch (sku) {
                case "A" -> {
                    quantSpecialOffersOne = itens.get(sku) / 5;
                    quantSpecialOffersTwo = (itens.get(sku) - quantSpecialOffersOne * 5) / 3;
                    quantNormalValue = itens.get(sku) - quantSpecialOffersOne * 5 - quantSpecialOffersTwo * 3;
                    total += quantSpecialOffersOne * 200 + quantSpecialOffersTwo * 130 + quantNormalValue * 50;
                }
                case "B" -> {
                    quantSpecialOffersOne = itens.get(sku) / 2;
                    quantNormalValue = itens.get(sku) - quantSpecialOffersOne * 2;
                    total += quantSpecialOffersOne * 45 + quantNormalValue * 30;
                }
                case "C" -> total += itens.get(sku) * 20;
                case "D" -> total += itens.get(sku) * 15;
                case "E" -> {
                    quantNormalValue = itens.get(sku) % 2;
                    total += quantNormalValue * 40;
                }
                default -> {
                    return -1;
                }
            }

        }
        System.out.println("total: " + total);
        return total;
    }
}


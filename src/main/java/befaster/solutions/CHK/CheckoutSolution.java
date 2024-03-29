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
            switch (sku) {
                case "A" -> {
                    int quantSpecialOffersOne = itens.get(sku) / 5;
                    int quantSpecialOffersTwo = (itens.get(sku) - quantSpecialOffersOne * 5) / 3;
                    int quantNormalValue = itens.get(sku) - quantSpecialOffersOne * 5 - quantSpecialOffersTwo * 3;
                    total += quantSpecialOffersOne * 200 + quantSpecialOffersTwo * 130 + quantNormalValue * 50;
                }
                case "B" -> {
                    int quantSpecialOffers = itens.get(sku) / 2;
                    int quantNormalValue = itens.get(sku) - quantSpecialOffers * 2;
                    total += quantSpecialOffers * 45 + quantNormalValue * 30;
                }
                case "C" -> total += itens.get(sku) * 20;
                case "D" -> total += itens.get(sku) * 15;
                case "E" -> {
                    int quantBFree = itens.get(sku) % 2;
                    total += itens.get(sku) * 40;
                    if (itens.get("B") != null) {
                        if (quantBFree > itens.get("B")) {
                            total -= itens.get("B") * 30;
                        } else total -= quantBFree * 30;
                    }
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




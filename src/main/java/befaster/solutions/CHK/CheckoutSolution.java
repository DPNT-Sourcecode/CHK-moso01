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
                case "A" -> total += this.doubleSpecialOffer(itens.get(sku), 5, 3, 50, 200, 130);
                case "B" -> {
                    int quantBFree = itens.get("E") != null ? itens.get("E") / 2 : 0;
                    int quant = itens.get(sku) >= quantBFree ? itens.get(sku) - quantBFree : 0;
                    total += this.specialOffer(quant, 2, 30, 45);
                }
                case "C" -> total += itens.get(sku) * 20;
                case "D" -> total += itens.get(sku) * 15;
                case "E" -> total += itens.get(sku) * 40;
                case "F" -> {
                    if (itens.get(sku) >= 3) {
                        total += this.specialOffer(itens.get(sku), 3, 10, 20);
                    } else {
                        total += itens.get(sku) * 10;
                    }
                }
                default -> {
                    return -1;
                }
            }
            System.out.println("total: " + total);
        }
        System.out.println("total final: " + total);
        return total;
    }

    private Integer specialOffer(int quant, int quantOffer, int price, int offerPrice) {
        int quantSpecialOffers = quant / quantOffer;
        int quantNormalValue = quant - quantSpecialOffers * quantOffer;
        return quantSpecialOffers * offerPrice + quantNormalValue * price;
    }

    private Integer doubleSpecialOffer(
            int quant,
            int quantOfferOne,
            int quantOfferTwo,
            int price,
            int offerPriceOne,
            int offerPriceTwo
    ) {
        int quantSpecialOffersOne = quant / quantOfferOne;
        int quantSpecialOffersTwo = (quant - quantSpecialOffersOne * quantOfferOne) / quantOfferTwo;
        int quantNormalValue = quant - quantSpecialOffersOne * quantOfferOne - quantSpecialOffersTwo * quantOfferTwo;
        return quantSpecialOffersOne * offerPriceOne + quantSpecialOffersTwo * offerPriceTwo + quantNormalValue * price;
    }

}


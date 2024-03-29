package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
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
            switch (sku) {
                case "A" -> total += this.doubleSpecialOffer(itens.get(sku), 5, 3, 50, 200, 130);
                case "B" -> {
                    int quantFree = itens.get("E") != null ? itens.get("E") / 2 : 0;
                    int quant = itens.get(sku) >= quantFree ? itens.get(sku) - quantFree : 0;
                    total += this.specialOffer(quant, 2, 30, 45);
                }
                case "C" -> total += itens.get(sku) * 20;
                case "D" -> total += itens.get(sku) * 15;
                case "E" -> total += itens.get(sku) * 40; // Offer in B
                case "F" -> {
                    if (itens.get(sku) >= 3) {
                        total += this.specialOffer(itens.get(sku), 3, 10, 20);
                    } else {
                        total += itens.get(sku) * 10;
                    }
                }
                case "G" -> total += itens.get(sku) * 20;
                case "H" -> total += this.doubleSpecialOffer(itens.get(sku), 10, 5, 10, 80, 45);
                case "I" -> total += itens.get(sku) * 35;
                case "J" -> total += itens.get(sku) * 60;
                case "K" -> total += this.specialOffer(itens.get(sku), 2, 70, 120);
                case "L" -> total += itens.get(sku) * 90;
                case "M" -> {
                    int quantFree = itens.get("N") != null ? itens.get("N") / 3 : 0;
                    int quant = itens.get(sku) >= quantFree ? itens.get(sku) - quantFree : 0;
                    total += quant * 15;
                }
                case "N" -> total += itens.get(sku) * 40;  // Offer in M
                case "O" -> total += itens.get(sku) * 10;
                case "P" -> total += this.specialOffer(itens.get(sku), 5, 50, 200);
                case "Q" -> {
                    int quantFree = itens.get("R") != null ? itens.get("R") / 3 : 0;
                    int quant = itens.get(sku) >= quantFree ? itens.get(sku) - quantFree : 0;
                    total += this.specialOffer(quant, 3, 30, 80);
                }
                case "R" -> total += itens.get(sku) * 50;  // Offer in Q
                case "U" -> {
                    if (itens.get(sku) >= 4) {
                        total += this.specialOffer(itens.get(sku), 4, 40, 120);
                    } else {
                        total += itens.get(sku) * 40;
                    }
                }
                case "V" -> total += this.doubleSpecialOffer(itens.get(sku), 3, 2, 50, 130, 90);
                case "W" -> total += itens.get(sku) * 20;
                case "S", "T", "X", "Y", "Z" -> total += 0;
                default -> {
                    return -1;
                }
            }
        }
        total += this.verifyDescount(itens);
        return total;
    }

    private int verifyDescount(Map<String, Integer> itens) {
        int value = 0;
        StringBuilder sku = new StringBuilder();
        sku.append("Z".repeat(itens.get("Z") != null ? itens.get("Z") : 0));
        sku.append("S".repeat(itens.get("S") != null ? itens.get("S") : 0));
        sku.append("T".repeat(itens.get("T") != null ? itens.get("T") : 0));
        sku.append("Y".repeat(itens.get("Y") != null ? itens.get("Y") : 0));
        sku.append("X".repeat(itens.get("X") != null ? itens.get("X") : 0));
        System.out.println(sku);
        int groups = sku.length() / 3;
        value += groups * 45;
        String newSku = groups > 0 ? sku.substring(sku.length()) : sku.toString();
        for (int i = 0; i < newSku.length(); i++) {
            String iten = String.valueOf(newSku.charAt(i));
            switch (iten) {
                case "S" -> value += 20;
                case "T" -> value += 20;
                case "X" -> value += 17;
                case "Y" -> value += 20;
                case "Z" -> value += 21;
            }
        }
        return value;
    }

    private int specialOffer(
            int quant,
            int quantOffer,
            int price,
            int offerPrice
    ) {
        int quantSpecialOffers = quant / quantOffer;
        int quantNormalValue = quant - quantSpecialOffers * quantOffer;
        return quantSpecialOffers * offerPrice + quantNormalValue * price;
    }

    private int doubleSpecialOffer(
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





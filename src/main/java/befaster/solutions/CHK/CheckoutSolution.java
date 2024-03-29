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
            boolean priceUpdate = false;
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
                case "K" -> total += this.specialOffer(itens.get(sku), 2, 80, 150);
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
                case "S" -> {
                    priceUpdate = itens.get(sku) != null;
                    if (priceUpdate) {
                        total += this.specialOffer(itens.get(sku), 3, 20, 45);
                    } else {
                        total += itens.get(sku) * 30;
                    }
                }
                case "T" -> {
                    priceUpdate = itens.get(sku) != null;
                    if (priceUpdate) {
                        total += this.specialOffer(itens.get(sku), 3, 20, 45);
                    } else {
                        total += itens.get(sku) * 20;
                    }
                }
                case "U" -> {
                    if (itens.get(sku) >= 4) {
                        total += this.specialOffer(itens.get(sku), 4, 40, 120);
                    } else {
                        total += itens.get(sku) * 40;
                    }
                }
                case "V" -> total += this.doubleSpecialOffer(itens.get(sku), 3, 2, 50, 130, 90);
                case "W" -> total += itens.get(sku) * 20;
                case "X" -> {
                    priceUpdate = itens.get(sku) != null;
                    if (priceUpdate) {
                        total += this.specialOffer(itens.get(sku), 3, 17, 45);
                    } else {
                        total += itens.get(sku) * 17;
                    }
                }
                case "Y" -> {
                    priceUpdate = itens.get(sku) != null;
                    if (priceUpdate) {
                        total += this.specialOffer(itens.get(sku), 3, 20, 45);
                    } else {
                        total += itens.get(sku) * 20;
                    }
                }
                case "Z" -> {
                    priceUpdate = itens.get(sku) != null;
                    if (priceUpdate) {
                        total += this.specialOffer(itens.get(sku), 3, 21, 45);
                    } else {
                        total += itens.get(sku) * 21;
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




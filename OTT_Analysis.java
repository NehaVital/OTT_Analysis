import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OTTPlatformAnalysisTool {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Map<String, String>> platforms = new ArrayList<>();
        
        // Enter platform details
        System.out.print("Enter the number of OTT platforms to analyze: ");
        int numPlatforms = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        for (int i = 0; i < numPlatforms; i++) {
            Map<String, String> platform = new HashMap<>();
            
            System.out.printf("Enter the name of platform %d: ", i + 1);
            String name = scanner.nextLine();
            platform.put("name", name);
            
            System.out.printf("Enter the price per month for %s: $", name);
            String price = scanner.nextLine();
            platform.put("price", price);
            
            System.out.printf("Enter the number of original content titles available on %s: ", name);
            String numTitles = scanner.nextLine();
            platform.put("numTitles", numTitles);
            
            System.out.printf("Enter the average user rating (out of 5) for %s: ", name);
            String rating = scanner.nextLine();
            platform.put("rating", rating);
            
            platforms.add(platform);
        }
        
        // Analyze platforms and recommend the best one
        System.out.println("\nAnalyzing OTT platforms...");
        Map<String, String> recommendedPlatform = null;
        double maxScore = Double.MIN_VALUE;
        
        for (Map<String, String> platform : platforms) {
            double score = calculatePlatformScore(platform);
            System.out.printf("\n%s score: %.2f", platform.get("name"), score);
            
            if (score > maxScore) {
                maxScore = score;
                recommendedPlatform = platform;
            }
        }
        
        System.out.printf("\n\nBased on your preferences, we recommend subscribing to %s for $%s per month.",
                recommendedPlatform.get("name"), recommendedPlatform.get("price"));
        
        scanner.close();
    }
    
    /**
     * Calculates a score for a given OTT platform based on its price, number of original content titles,
     * and average user rating.
     */
    private static double calculatePlatformScore(Map<String, String> platform) {
        double price = Double.parseDouble(platform.get("price"));
        int numTitles = Integer.parseInt(platform.get("numTitles"));
        double rating = Double.parseDouble(platform.get("rating"));
        
        double priceScore = (20.0 - price) / 20.0;
        double titleScore = numTitles / 10000.0;
        double ratingScore = rating / 5.0;
        
        return priceScore + titleScore + ratingScore;
    }
}

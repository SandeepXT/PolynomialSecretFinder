import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PolynomialSecretFinder {

    
    public static long decodeBaseValue(int base, String value) {
        return Long.parseLong(value, base);
    }

    
    public static JsonObject readInputFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             JsonReader reader = Json.createReader(fis)) {
            return reader.readObject();
        }
    }

    
    public static BigInteger findSecret(JsonObject data) {
        JsonObject keys = data.getJsonObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        List<BigInteger> decodedValues = new ArrayList<>();

        
        for (int i = 1; i <= n; i++) {
            JsonObject entry = data.getJsonObject(String.valueOf(i));
            int base = Integer.parseInt(entry.getString("base"));
            String value = entry.getString("value");

            long decodedValue = decodeBaseValue(base, value);
            decodedValues.add(BigInteger.valueOf(decodedValue));
        }

        
        System.out.println("Decoded values: " + decodedValues);

        BigInteger secret = BigInteger.ZERO;  

        return secret;
    }

    public static void main(String[] args) {
        
        String filePath = "testcase.json";

        
        try {
            JsonObject data = readInputFile(filePath);

            
            BigInteger secret = findSecret(data);
            System.out.println("The secret is: " + secret);
        } catch (IOException e) {
            System.out.println("Error: Unable to read input file.");
            e.printStackTrace();
        }
    }
}

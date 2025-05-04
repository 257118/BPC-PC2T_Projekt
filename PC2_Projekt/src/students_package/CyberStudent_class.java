package students_package;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CyberStudent_class extends Student_class {

    public CyberStudent_class(int id, String firstName, String lastName, int birthYear) {
        super(id, firstName, lastName, birthYear);
    }

    @Override
    public void useSkill() {
        String fullName = getFullName();
        String hash = hashString(fullName);
        System.out.println("vyjádření jména a příjmení ve formě hashe " + fullName + ":");
        System.out.println(hash);
    }

    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            return "Chyba heshovaní: " + e.getMessage();
        }
    }
}

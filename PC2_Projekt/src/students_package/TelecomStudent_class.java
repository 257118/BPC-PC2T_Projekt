package students_package;

public class TelecomStudent_class extends Student_class {

    public TelecomStudent_class(int id, String firstName, String lastName, int birthYear) {
        super(id, firstName, lastName, birthYear);
    }

    @Override
    public void useSkill() {
        String fullName = getFullName().toUpperCase();
        System.out.println("Převedení jména a příjmení na Morseovu abecedu " + fullName + ":");
        for (char c : fullName.toCharArray()) {
            if (morseMap.containsKey(c)) {
                System.out.print(morseMap.get(c) + " ");
            } else {
                System.out.print("? ");
            }
        }
        System.out.println();
    }

    
    private static final java.util.Map<Character, String> morseMap = new java.util.HashMap<>();
    static {
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put(' ', "/");
    }
}

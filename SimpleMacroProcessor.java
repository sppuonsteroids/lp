import java.util.*;

public class SimpleMacroProcessor {

    // Macro Name Table (MNT): macro name -> index in MDT
    static Map<String, Integer> MNT = new LinkedHashMap<>();

    // Macro Definition Table (MDT): index -> macro line
    static List<String> MDT = new ArrayList<>();

    public static void main(String[] args) {

        // Sample program with macro
        String program[] = {
                "START 100",
                "INCR MACRO X",
                "ADD X, ONE",
                "MOVER A, X",
                "MEND",
                "INCR ALPHA",
                "STOP",
                "ALPHA DC 1",
                "ONE DC 1",
                "END"
        };

        // ----- PASS I: Build MNT, MDT and Intermediate Code -----
        boolean inMacro = false;
        String macroName = "";
        List<String> intermediate = new ArrayList<>();
        int mdtIndex = 0;

        for (String line : program) {
            String parts[] = line.split("\\s+|,");
            if (parts.length == 0) continue;

            if (parts[0].equals("MACRO")) continue; // Skip MACRO header

            if (parts[0].equals("MEND")) {
                MDT.add("MEND");
                inMacro = false;
                continue;
            }

            if (inMacro) {
                MDT.add(line);
            } else if (parts.length > 1 && parts[1].equals("MACRO")) {
                macroName = parts[0];
                MNT.put(macroName, MDT.size());
                inMacro = true;
            } else {
                intermediate.add(line); // Lines outside macro
            }
        }

        // ----- Display Pass-I Output -----
        System.out.println("--- PASS-I OUTPUT ---");

        System.out.println("\nMacro Name Table (MNT):");
        System.out.println("Macro\tMDT Index");
        for (Map.Entry<String, Integer> e : MNT.entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }

        System.out.println("\nMacro Definition Table (MDT):");
        for (int i = 0; i < MDT.size(); i++) {
            System.out.println(i + "\t" + MDT.get(i));
        }

        System.out.println("\nIntermediate Code (without macros):");
        for (String line : intermediate) {
            System.out.println(line);
        }

        // ----- PASS II: Expand macros -----
        System.out.println("\n--- PASS-II OUTPUT: Expanded Code ---");

        for (String line : intermediate) {
            String parts[] = line.split("\\s+|,");
            if (parts.length == 0) continue;

            if (MNT.containsKey(parts[0])) {
                int idx = MNT.get(parts[0]);
                String arg = parts[1]; // Macro argument
                while (!MDT.get(idx).equals("MEND")) {
                    String expanded = MDT.get(idx).replaceAll("X", arg);
                    System.out.println(expanded);
                    idx++;
                }
            } else {
                System.out.println(line);
            }
        }
    }
}

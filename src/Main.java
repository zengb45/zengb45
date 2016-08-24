import java.util.*;

/**
 * Created by SiyuanZeng's on 8/24/2016.
 */
public class Main {

    public static final String DEPEND = "DEPEND";
    public static final String INSTALL = "INSTALL";
    public static final String REMOVE = "REMOVE";
    public static final String LIST = "LIST";
    public static final String EMPTY_SPACE = " ";
    public static final String TAB = "  ";
    private static Map<String, LinkedList> dependecies = new HashMap<String, LinkedList>();

    private static Map<String, Boolean> installations = new HashMap<String, Boolean>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isToContinue = true;
        StringBuilder stringBuilder = new StringBuilder();

        while (isToContinue) {
            String command = scanner.nextLine();

            if (command.startsWith(DEPEND)) {
                String[] strings = command.substring(command.indexOf(EMPTY_SPACE) + 1).split(EMPTY_SPACE);
                LinkedList<String> linkedList = new LinkedList<String>();
                for (int i = 1; i < strings.length; i++) {
                    linkedList.add(strings[i]);
                    installations.put(strings[i], false);
                }
                dependecies.put(strings[0], linkedList);
                installations.put(strings[0], false);

                System.out.println(command);
            } else if (command.startsWith(INSTALL)) {
                stringBuilder = new StringBuilder();
                String[] string = command.substring(command.indexOf(EMPTY_SPACE) + 1).split(EMPTY_SPACE);
                if (string.length == 1) {
                    if (!installations.containsKey(string[0]) || !installations.get(string[0])) {
                        if (dependecies.containsKey(string[0])) {
                            LinkedList<String> linkedList = dependecies.get(string[0]);
                            for (int i = 0; i < linkedList.size(); i++) {
                                String d = linkedList.get(i);
                                if (!installations.get(linkedList.get(i))) {
                                    installations.put(linkedList.get(i), true);
                                    stringBuilder.append(System.getProperty("line.separator"));
                                    stringBuilder.append(TAB + INSTALL + "ing" + EMPTY_SPACE + linkedList.get(i));
                                    break;
                                }
                            }
                            installations.put(string[0], true);
                            stringBuilder.append(System.getProperty("line.separator"));
                            stringBuilder.append(TAB + "Installing" + EMPTY_SPACE + string[0]);
                        } else {
                            installations.put(string[0], true);
                            stringBuilder.append(System.getProperty("line.separator"));
                            stringBuilder.append(TAB + "Installing" + EMPTY_SPACE + string[0]);
                        }
                    }
                    System.out.println(stringBuilder.toString());
                } else {
                    System.out.println("Warning: please install application one by one.");
                }
            } else if (command.startsWith(REMOVE)) {
                String[] strings = command.substring(command.indexOf(EMPTY_SPACE) + 1).split(EMPTY_SPACE);

                if (strings.length == 1) {

                    boolean isStillNeeded = false;
                    stringBuilder = new StringBuilder();

                    if (installations.get(strings[0])) {
                        Iterator it = dependecies.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            if (installations.get(pair.getKey())) {
                                LinkedList<String> linkedList = dependecies.get(pair.getKey());
                                for (int i = 0; i < linkedList.size(); i++) {
                                    String d = linkedList.get(i);
                                    if (d.equalsIgnoreCase(strings[0])) {
                                        isStillNeeded = true;
                                        break;
                                    }
                                }
                            }
                            if (isStillNeeded) break;
                        }

                        if (isStillNeeded) {
                            System.out.println(TAB + strings[0] + EMPTY_SPACE + "is still needed");
                        } else {
                            installations.put(strings[0], false);
                            System.out.println("Removing " + strings[0]);
                        }
                    } else {
                        System.out.println("Warning: " + strings[0] + EMPTY_SPACE + "is not installed yet");
                    }
                } else {
                    System.out.println("Warning: Please remove 1 component at a time");

                }
        } else if (command.startsWith(LIST)) {
            Iterator it = installations.entrySet().iterator();
            stringBuilder = new StringBuilder();
            while (it.hasNext()) {
                Map.Entry<String, Boolean> pair = (Map.Entry<String, Boolean>) it.next();
                 if (pair.getValue()) {
                    if (stringBuilder.toString().isEmpty()) {
                        stringBuilder.append(LIST);
                        stringBuilder.append(System.getProperty("line.separator"));
                    }
                    stringBuilder.append(TAB+pair.getKey());
                    stringBuilder.append(System.getProperty("line.separator"));
                }
            }
            System.out.println(stringBuilder);
        }
            System.out.println();
    }

    System.exit(0);
    }

}

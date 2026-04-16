package assignment1.ex1;

import java.util.List;
import java.util.Map;
import java.util.*;

public class TuringMachine {
    protected List<String> tape;
    protected int head;
    protected String state;
    protected Map<String, Transition> transitions;
    protected final String BLANK = "B";
    protected final int MAX_STEPS = 1000;

    protected static class Transition {
        String writeChar;
        String move;
        String nextState;

        Transition(String writeChar, String move, String nextState) {
            this.writeChar = writeChar;
            this.move = move;
            this.nextState = nextState;
        }
    }

    public TuringMachine(String input) {
        tape = new ArrayList<>();
        for (char c : input.toCharArray()) {
            tape.add(String.valueOf(c));
        }
        head = 0;
        state = "q0";
        transitions = new HashMap<>();
    }

    protected void addTransition(String currentState, String readChar,
                                 String writeChar, String move, String nextState) {
        String key = currentState + "," + readChar;
        transitions.put(key, new Transition(writeChar, move, nextState));
    }

    protected void expandTapeIfNeeded() {
        if (head < 0) {
            tape.add(0, BLANK);
            head = 0;
        } else if (head >= tape.size()) {
            tape.add(BLANK);
        }
    }

    protected boolean step() {
        expandTapeIfNeeded();

        String currentChar = tape.get(head);
        String key = state + "," + currentChar;

        if (!transitions.containsKey(key)) {
            return false;
        }

        Transition trans = transitions.get(key);
        tape.set(head, trans.writeChar);

        if (trans.move.equals("R")) {
            head++;
        } else if (trans.move.equals("L")) {
            head--;
        }

        state = trans.nextState;
        return true;
    }

    public String run() {
        int steps = 0;
        while (steps < MAX_STEPS) {
            if (!step()) {
                break;
            }
            steps++;
        }

        StringBuilder result = new StringBuilder();
        for (String s : tape) {
            if (!s.equals(BLANK)) {
                result.append(s);
            }
        }
        return result.length() == 0 ? "" : result.toString();
    }

    public void display() {
        System.out.println("State: " + state);
        System.out.print("Tape: ");
        for (int i = 0; i < tape.size(); i++) {
            if (i == head) {
                System.out.print("[" + tape.get(i) + "]");
            } else {
                System.out.print(" " + tape.get(i) + " ");
            }
        }
        System.out.println();
        System.out.println("Head position: " + head);
    }
}
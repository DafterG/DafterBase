package a01;

import edu.princeton.cs.algs4.Queue;
import java.util.Stack;

/**
 * Exercises to practice the use of stacks and queues.
 * 
 * @author CSIS 2420 Starter Code + Brandon Torres
 * @see <a href="https://github.com/DafterG/DafterBase/blob/main/NumberList.java">NumberList.java</a>
 * @see <a href="https://github.com/DafterG/DafterBase/blob/main/StackQueueChallenges.java">StackQueueChallenges.java</a>
 */
public class StackQueueChallenges {

    /**
     * Checks if the brackets in the input queue are balanced.
     * A sequence is balanced if each opening bracket has a corresponding
     * closing bracket in the correct order.
     *
     * @param q the input queue containing the characters of the expression
     * @return true if the brackets are balanced, false otherwise
     */
    public static boolean balanceBrackets(Queue<Character> q) {
        Stack<Character> stack = new Stack<>();

        while (!q.isEmpty()) {
            char ch = q.dequeue();
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (!isMatchingPair(open, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    /**
     * Reverses the order of words in the input queue.
     * Words are defined as any sequence of characters that does not include spaces.
     *
     * @param q the input queue containing the characters of the sentence
     * @return the queue with the order of words reversed
     */
    public static Iterable<Character> reverseWords(Queue<Character> q) {
        Stack<String> words = new Stack<>();
        StringBuilder currentWord = new StringBuilder();

        while (!q.isEmpty()) {
            char ch = q.dequeue();
            if (ch != ' ') {
                currentWord.append(ch);
            } else if (currentWord.length() > 0) {
                words.push(currentWord.toString());
                currentWord.setLength(0);
            }
        }

        if (currentWord.length() > 0) {
            words.push(currentWord.toString());
        }

        Queue<Character> result = new Queue<>();
        while (!words.isEmpty()) {
            String word = words.pop();
            for (char c : word.toCharArray()) {
                result.enqueue(c);
            }
            if (!words.isEmpty()) {
                result.enqueue(' ');
            }
        }

        return result;
    }

    /* * * * * * * * Test Client * * * * * * */
    public static void main(String[] args) {
        // StackQueueChallenges tests
        Queue<Character> q = new Queue<>();
        q.enqueue('(');
        q.enqueue('{');
        q.enqueue('[');
        q.enqueue(']');
        q.enqueue('}');
        q.enqueue(')');
        System.out.println("Balanced Brackets: " + balanceBrackets(q)); // Expected: true

        Queue<Character> reverseQueue = new Queue<>();
        reverseQueue.enqueue('H');
        reverseQueue.enqueue('e');
        reverseQueue.enqueue('l');
        reverseQueue.enqueue('l');
        reverseQueue.enqueue('o');
        reverseQueue.enqueue(' ');
        reverseQueue.enqueue('W');
        reverseQueue.enqueue('o');
        reverseQueue.enqueue('r');
        reverseQueue.enqueue('l');
        reverseQueue.enqueue('d');
        System.out.println("Reversed Words: " + reverseWords(reverseQueue)); // Expected: "World Hello"
    }
}



public class PostfixStack {

    private int[] stack;
    private int top;

    public PostfixStack(int size) {
        stack = new int[size];
        top = -1;
    }

    public void push(int value) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full.");
            return;
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }

        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }

        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void displayStack() {
        System.out.print("Stack: ");

        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }

        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }

        System.out.println();
    }

    public static int evaluatePostfix(String expression) {

        String[] tokens = expression.split(" ");
        PostfixStack stack = new PostfixStack(tokens.length);

        for (int i = 0; i < tokens.length; i++) {

            String token = tokens[i];

            if (token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/")) {

                int second = stack.pop();
                int first = stack.pop();

                int result = 0;

                if (token.equals("+")) {
                    result = first + second;
                } else if (token.equals("-")) {
                    result = first - second;
                } else if (token.equals("*")) {
                    result = first * second;
                } else if (token.equals("/")) {
                    result = first / second;
                }

                stack.push(result);

            } else {
                int number = Integer.parseInt(token);
                stack.push(number);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("          POSTFIX STACK TEST");
        System.out.println("==========================================");

        String expression = "5 3 + 2 *";

        System.out.println("Postfix expression: " + expression);
        System.out.println();

        PostfixStack stack = new PostfixStack(10);

        System.out.println("Push 5");
        stack.push(5);
        stack.displayStack();

        System.out.println("Push 3");
        stack.push(3);
        stack.displayStack();

        System.out.println("Pop two values and add them");

        int second = stack.pop();
        int first = stack.pop();

        int result = first + second;

        stack.push(result);
        stack.displayStack();

        System.out.println("Push 2");
        stack.push(2);
        stack.displayStack();

        System.out.println("Pop two values and multiply them");

        second = stack.pop();
        first = stack.pop();

        result = first * second;

        stack.push(result);
        stack.displayStack();

        int finalResult = stack.pop();

        System.out.println();
        System.out.println("Final result: " + finalResult);
        System.out.println("Expected result: 16");
    }
}
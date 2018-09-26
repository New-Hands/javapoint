package thread.threadloop;

/**
 * @author 李尚庭
 */
public class Tool {
    private final String toolName;

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool{" + "toolName='" + toolName + '\'' + '}';
    }
}

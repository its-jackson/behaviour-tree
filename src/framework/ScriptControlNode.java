package framework;

public class ScriptControlNode implements Node {
    private int failCount;

    private final Node node;
    private final int maxTries;

    public ScriptControlNode(Node node, int maxTries) {
        this.node = node;
        this.maxTries = maxTries;
    }

    public ScriptControlNode(Node node) {
        this(node, 10);
    }

    @Override
    public NodeStatus tick() {
        if (failCount >= maxTries) {
            return NodeStatus.KILL;
        }

        NodeStatus status = node.tick();

        if (status == NodeStatus.SUCCESS) {
            failCount = 0;
        } else if (status == NodeStatus.FAILURE) {
            failCount++;
        }
        else {
            failCount = maxTries;
        }

        return status;
    }
}

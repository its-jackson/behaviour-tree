package framework;

import java.util.function.BooleanSupplier;

public class RepeatUntilNode implements Node {
    private final Node root;
    private final BooleanSupplier condition;

    private NodeStatus currentStatus;

    public RepeatUntilNode(BooleanSupplier condition, Node root) {
        this.condition = condition;
        this.root = root;
    }

    public RepeatUntilNode(NodeStatus untilStatus, Node root) {
        this.root = root;
        this.condition = () -> untilStatus == currentStatus;
    }

    @Override
    public NodeStatus tick() {
        do { currentStatus = root.tick(); }
        while (!condition.getAsBoolean());
        return currentStatus;
    }
}

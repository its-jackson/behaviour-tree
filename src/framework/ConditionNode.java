package framework;

import java.util.function.BooleanSupplier;

public class ConditionNode implements Node {
    private final BooleanSupplier condition;

    public ConditionNode(BooleanSupplier condition) {
        this.condition = condition;
    }

    @Override
    public NodeStatus tick() {
        return condition.getAsBoolean() ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
    }
}

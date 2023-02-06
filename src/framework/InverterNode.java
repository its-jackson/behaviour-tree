package framework;

import java.util.function.BooleanSupplier;

public class InverterNode implements Node {
    private final BooleanSupplier supplier;
    private final Node node;

    public InverterNode(BooleanSupplier supplier) {
        this.supplier = supplier;
        this.node = null;
    }

    public InverterNode(Node node) {
        this.node = node;
        this.supplier = null;
    }

    @Override
    public NodeStatus tick() {
        if (supplier != null) {
            if (supplier.getAsBoolean()) {
                return NodeStatus.FAILURE;
            }
            return NodeStatus.SUCCESS;
        }

        if (node != null) {
            NodeStatus status = node.tick();
            if (status == NodeStatus.FAILURE) {
                return NodeStatus.SUCCESS;
            }
            if (status == NodeStatus.SUCCESS) {
                return NodeStatus.FAILURE;
            }
        }

        return NodeStatus.RUNNING;
    }
}

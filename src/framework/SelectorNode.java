package framework;

import java.util.*;

public class SelectorNode implements Node {
    private final List<Node> children = new ArrayList<>();

    public SelectorNode(Node... children) {
        Collections.addAll(this.children, children);
    }

    @Override
    public NodeStatus tick() {
        for (Node child : children) {
            NodeStatus status = child.tick();
            if (status == NodeStatus.SUCCESS) {
                return NodeStatus.SUCCESS;
            }
            if (status == NodeStatus.RUNNING) {
                return NodeStatus.RUNNING;
            }
        }
        return NodeStatus.FAILURE;
    }
}

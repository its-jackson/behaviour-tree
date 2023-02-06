package framework;

import java.util.*;

public class SequenceNode implements Node {
    private final List<Node> children = new ArrayList<>();

    public SequenceNode(Node... children) {
        Collections.addAll(this.children, children);
    }

    @Override
    public NodeStatus tick() {
        for (Node child : children) {
            NodeStatus status = child.tick();
            if (status == NodeStatus.FAILURE) {
                return NodeStatus.FAILURE;
            }
            if (status == NodeStatus.RUNNING) {
                return NodeStatus.RUNNING;
            }
        }
        return NodeStatus.SUCCESS;
    }
}


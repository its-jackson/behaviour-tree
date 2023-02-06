package framework;

public class BehaviourTree implements Node {
    private final Node root;

    public BehaviourTree(Node root) {
        this.root = root;
    }

    @Override
    public NodeStatus tick() {
        return root.tick();
    }
}

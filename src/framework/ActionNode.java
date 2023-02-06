package framework;

public class ActionNode implements Node {
    private final Runnable action;

    public ActionNode(Runnable action) {
        this.action = action;
    }

    @Override
    public NodeStatus tick() {
        action.run();
        return NodeStatus.SUCCESS;
    }
}

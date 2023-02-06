import framework.*;

public class Main {
    public static void main(String[] args) {
        BehaviourTree tree = new BehaviourTree(new RepeatUntilNode(NodeStatus.FAILURE, mainSequenceNode()));
        tree.tick();
    }

    public static SequenceNode mainSequenceNode() {
        return new SequenceNode(
                new ActionNode(() -> System.out.println("ActionNode 1")),
                new SelectorNode(
                        new ConditionNode(() -> true),
                        new ActionNode(() -> System.out.println("ActionNode 2"))
                ),
                new ActionNode(() -> System.out.println("ActionNode 3"))
        );
    }
}
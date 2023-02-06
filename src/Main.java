import framework.*;

public class Main {
    public static void main(String[] args) {

        BehaviourTree tree = new BehaviourTree(
                new SequenceNode(
                        new ActionNode(() -> System.out.println("One")),
                        new SelectorNode(
                                new InverterNode(() -> true),
                                new ActionNode(() -> System.out.println("Two"))
                        ),
                        new ActionNode(() -> System.out.println("Three"))
                )
        );

        NodeStatus status = tree.tick();

        if (status == NodeStatus.SUCCESS) {
            BehaviourTree[] behaviourTrees = { tree, tree, tree };
            for (BehaviourTree t : behaviourTrees) {
                if (t.tick() == NodeStatus.FAILURE) {
                    break;
                }
            }
        }

    }
}

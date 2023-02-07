import framework.*;

class ScriptContext {
    private final String name;
    private final int age;
    private final boolean male;

    private int count;

    public ScriptContext(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return male;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}

class ScriptRunner {
    private final BehaviourTree tree;

    public ScriptRunner(BehaviourTree tree) {
        this.tree = tree;
    }

    public NodeStatus run() {
        NodeStatus status;

        do {
            status = tree.tick();
        } while (status != NodeStatus.KILL);

        return status;
    }
}

class Script {
    private final ScriptContext scriptContext;
    private final NodeStatus result;

    public Script(ScriptContext scriptContext) {
        this.scriptContext = scriptContext;
        BehaviourTree tree = getTree();
        ScriptRunner treeScriptRunner = new ScriptRunner(tree);
        this.result = treeScriptRunner.run();
    }

    public NodeStatus getResult() {
        return result;
    }

    private BehaviourTree getTree() {
        return new BehaviourTree(
                new ScriptControlNode(
                        new SequenceNode(
                                new ActionNode(() -> System.out.printf("Name: %s\n", this.scriptContext.getName())),
                                new SelectorNode(
                                        new InverterNode(() -> true),
                                        new ActionNode(() -> System.out.printf("Age: %d\n", this.scriptContext.getAge()))
                                ),
                                new ActionNode(() -> System.out.printf("Is male: %s\n",this.scriptContext.isMale())),
                                new ActionNode(this::incAndPrintCount),
                                new ConditionNode(() -> false)
                        )
                )
        );
    }

    private void incAndPrintCount() {
        scriptContext.incrementCount();
        System.out.printf("Count: %d\n", scriptContext.getCount());
    }
}

public class ScriptDemo {
    public static void main(String[] args) {
        // Expected output: print the state of the script context 10 times, then kill it.
        Script script = new Script(new ScriptContext("Jackson", 24, true));
        NodeStatus result = script.getResult();
        System.out.println(result);
    }
}

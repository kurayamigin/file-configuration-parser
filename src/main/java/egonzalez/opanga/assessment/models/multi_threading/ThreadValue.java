package egonzalez.opanga.assessment.models.multi_threading;

import java.util.function.Supplier;

public class ThreadValue<Value> extends Thread {
    private Value value;
    private final Supplier<Value> supplier;
    public ThreadValue(Supplier<Value> supplier) {
        super();
        this.supplier = supplier;
    }

    @Override
    public void run() {
        this.value = supplier.get();
    }

    public Value getValue() {
        return value;
    }
}

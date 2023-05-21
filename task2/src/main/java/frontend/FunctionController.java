package frontend;

import backend.functions.BaseFunction;

public class FunctionController {
    private FunctionDomainController functionDomainController;
    private BaseFunction function;

    public BaseFunction getFunction() {
        return function;
    }

    public void setFunction(BaseFunction function) {
        this.function = function;
    }
}

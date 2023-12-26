package christmas.view;

import christmas.exception.ExceptionResolver;
import java.util.List;

public class InputViewProxy implements InputViewable {

    private final InputViewable subjectView;

    public InputViewProxy(final InputViewable subjectView) {
        this.subjectView = subjectView;
    }

    @Override
    public int inputVisitDate() {
        return ExceptionResolver.resolveInput(subjectView::inputVisitDate);
    }

    @Override
    public List<String> inputMenus() {
        return ExceptionResolver.resolveInput(subjectView::inputMenus);
    }
}

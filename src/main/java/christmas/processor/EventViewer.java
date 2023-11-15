package christmas.processor;

import christmas.dto.badge.BadgeResult;
import christmas.dto.benefitdetail.BenefitDetails;
import christmas.dto.payment.PaymentAmountResult;
import christmas.dto.reservation.ReservedResults;
import christmas.view.InputViewable;
import christmas.view.OutputView;
import java.util.List;

public class EventViewer {

    private final InputViewable inputView;
    private final OutputView outputView;

    public EventViewer(final InputViewable inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int inputVisitDate() {
        return inputView.inputVisitDate();
    }

    public List<String> inputMenus() {
        return inputView.inputMenus();
    }

    public void printReservationResults(final ReservedResults reservedResults) {
        outputView.printReservationResults(reservedResults);
    }

    public void printBenefitDetails(final BenefitDetails benefitDetails) {
        outputView.printBenefitDetails(benefitDetails);
    }

    public void printPaymentAmount(final PaymentAmountResult paymentAmountResult) {
        outputView.printPaymentAmount(paymentAmountResult);
    }

    public void printEventBadge(final BadgeResult badgeResult) {
        outputView.printEventBadge(badgeResult);
    }
}
